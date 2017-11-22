package com.syzible.boop.fragments.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.syzible.boop.R;

import java.util.ArrayList;

/**
 * Created by ed on 22/11/2017.
 */

public class DetailsFragment extends Fragment {
    private String[] countryCodes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding_details, container, false);

        prepareCountryList();

        Spinner spinner = view.findViewById(R.id.onboarding_call_country_code);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, countryCodes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
                String itemSelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void prepareCountryList() {
        countryCodes = new String[]{
                "+353", "+44", "+49"
        };
    }
}
