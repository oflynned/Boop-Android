package com.syzible.boop.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.syzible.boop.R;
import com.syzible.boop.fragments.main.CallFragment;
import com.syzible.boop.fragments.main.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(getFragmentManager(), new CallFragment());
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null)
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, fragment)
                    .commit();
    }

    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null)
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
    }

    public static void removeFragment(FragmentManager fragmentManager) {
        if (fragmentManager != null)
            fragmentManager.popBackStack();
    }

    public static void clearBackstack(FragmentManager fragmentManager) {
        if (fragmentManager != null)
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
