package com.syzible.boop.fragments.onboarding;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.syzible.boop.R;

import java.util.ArrayList;

/**
 * Created by ed on 22/11/2017.
 */

public class DetailsFragment extends Fragment {
    private String[] countryCodes;
    private EditText forename, surname, number;
    private Spinner countryCode;
    private String countryCodeValue;

    private static final int PERMISSION_REQ_CODE = 123;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding_details, container, false);

        askPermissionGrant();
        prepareCountryList();

        countryCode = view.findViewById(R.id.onboarding_country_code);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, countryCodes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCode.setAdapter(spinnerArrayAdapter);
        countryCode.setSelection(0);

        forename = view.findViewById(R.id.input_forename);
        surname = view.findViewById(R.id.input_surname);
        number = view.findViewById(R.id.input_user_number);

        countryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryCodeValue = adapterView.getItemAtPosition(i).toString();
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

    public boolean isReadyToProceed() {
        return getForename().length() > 2 && getSurname().length() > 2 &&
                getNumber().length() > 6 && getCountryCodeValue().length() > 2;
    }

    public String getForename() {
        return forename.getText().toString().trim();
    }

    public String getSurname() {
        return surname.getText().toString().trim();
    }

    public String getNumber() {
        return number.getText().toString().trim();
    }

    public String getCountryCodeValue() {
        return countryCodeValue;
    }

    private void askPermissionGrant() {
        if (!wasContactPermissionGranted() || wasCameraPermissionGranted() || wasSmsPermissionGranted()) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_SMS
                    }, PERMISSION_REQ_CODE);
        }
    }

    private boolean wasContactPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean wasCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean wasSmsPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED;
    }
}
