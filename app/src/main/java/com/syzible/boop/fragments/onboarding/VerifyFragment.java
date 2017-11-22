package com.syzible.boop.fragments.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.boop.R;

/**
 * Created by ed on 22/11/2017.
 */

public class VerifyFragment extends Fragment {
    private boolean wasUserVerified = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding_verify, container, false);

        return view;
    }
}
