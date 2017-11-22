package com.syzible.boop.activities;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.syzible.boop.R;
import com.syzible.boop.fragments.onboarding.DetailsFragment;
import com.syzible.boop.fragments.onboarding.GreetingFragment;
import com.syzible.boop.fragments.onboarding.PermissionsFragment;
import com.syzible.boop.fragments.onboarding.ReadyToStartFragment;
import com.syzible.boop.fragments.onboarding.VerifyFragment;

public class OnboardingActivity extends AppCompatActivity {

    private Fragment greetingFragment, detailsFragment, verifyFragment, permissionsFragment, readyToStartFragment;
    private static final int NUM_PAGES = 5;
    private int currentPage = 0;

    private int[] indicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        greetingFragment = new GreetingFragment();
        detailsFragment = new DetailsFragment();
        verifyFragment = new VerifyFragment();
        permissionsFragment = new PermissionsFragment();
        readyToStartFragment = new ReadyToStartFragment();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        ImageButton backButton = findViewById(R.id.intro_btn_back);
        ImageButton nextButton = findViewById(R.id.intro_btn_next);
        Button finishButton = findViewById(R.id.intro_btn_finish);

        int color1 = ContextCompat.getColor(this, R.color.blue500);
        int color2 = ContextCompat.getColor(this, R.color.blue500);
        int color3 = ContextCompat.getColor(this, R.color.purple500);
        int color4 = ContextCompat.getColor(this, R.color.green500);
        int color5 = ContextCompat.getColor(this, R.color.green500);

        int[] colorList = new int[]{color1, color2, color3, color4, color5};
        indicators = new int[]{
                R.id.intro_indicator_0,
                R.id.intro_indicator_1,
                R.id.intro_indicator_2,
                R.id.intro_indicator_3,
                R.id.intro_indicator_4
        };

        updateIndicators(currentPage);
        backButton.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(view -> {
            if (currentPage > 0) {
                viewPager.setCurrentItem(currentPage - 1);
            }
        });

        nextButton.setOnClickListener(view -> {
            if (currentPage < NUM_PAGES - 1) {
                viewPager.setCurrentItem(currentPage + 1);
            }
        });

        finishButton.setOnClickListener(view -> startActivity(new Intent(OnboardingActivity.this, MainActivity.class)));

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position],
                        colorList[position == NUM_PAGES - 1 ? position : position + 1]);
                viewPager.setBackgroundColor(colorUpdate);
                setUiBarColours(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updateIndicators(currentPage);

                position = position < 0 ? 0 : position;
                position = position > NUM_PAGES ? NUM_PAGES : position;

                viewPager.setBackgroundColor(colorList[position]);
                setUiBarColours(colorList[position]);

                backButton.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
                nextButton.setVisibility(position == NUM_PAGES - 1 ? View.GONE : View.VISIBLE);
                finishButton.setVisibility(position == NUM_PAGES - 1 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setUiBarColours(int colour) {
        getWindow().setNavigationBarColor(colour);
        getWindow().setStatusBarColor(colour);
    }

    private void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            findViewById(indicators[i]).setBackgroundResource(i == position ?
                    R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return greetingFragment;
                case 1:
                    return detailsFragment;
                case 2:
                    return verifyFragment;
                case 3:
                    return permissionsFragment;
                case 4:
                    return readyToStartFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
