package com.syzible.boop.fragments.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.boop.network.Endpoints;
import com.syzible.boop.network.RestClient;
import com.syzible.boop.persistence.LocalPrefs;
import com.syzible.boop.ui.ContactItemDecoration;
import com.syzible.boop.activities.MainActivity;
import com.syzible.boop.R;
import com.syzible.boop.objects.Contact;
import com.syzible.boop.utils.JSONUtils;
import com.syzible.boop.utils.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ed on 22/11/2017.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Contact> contacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = view.findViewById(R.id.contact_list_holder);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ContactItemDecoration(getActivity(), 16));
        recyclerView.setLayoutManager(layoutManager);

        TextView userName = view.findViewById(R.id.contacts_header_title);
        userName.setText(LocalPrefs.getFullName(getActivity()));

        TextView userNumber = view.findViewById(R.id.contacts_header_number);
        userNumber.setText(LocalPrefs.getPhoneNumber(getActivity()));

        generateContactsList();

        return view;
    }

    private void setupAdapter() {
        RecyclerView.Adapter adapter = new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    private void generateContactsList() {
        contacts = new ArrayList<>();
        RestClient.post(getActivity(), Endpoints.GET_USER_CONTACTS, JSONUtils.getIdPayload(getActivity()), new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Contact contact = new Contact(response.getJSONObject(i));
                        contacts.add(contact);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setupAdapter();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {

            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }

    public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
        private List<Contact> dataset;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView contactForename, contactSurname, contactNumber, contactTimeLastRung;

            ViewHolder(View v) {
                super(v);
                this.contactForename = v.findViewById(R.id.contact_list_item_forename);
                this.contactSurname = v.findViewById(R.id.contact_list_item_surname);
                this.contactNumber = v.findViewById(R.id.contact_list_item_number);
                this.contactTimeLastRung = v.findViewById(R.id.contact_list_item_last_called);
            }
        }

        private ContactsAdapter(ArrayList<Contact> dataset) {
            this.dataset = dataset;
        }

        @Override
        public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_contact_list_item, parent, false);
            return new ContactsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
            Contact contact = dataset.get(position);
            holder.contactForename.setText(contact.getForename());
            holder.contactSurname.setText(contact.getSurname());
            holder.contactNumber.setText(contact.getNumber());

            if (contact.getLastTimeRung() != 0)
                holder.contactTimeLastRung.setText(TimeUtils.getDate(contact.getLastTimeRung()));

            holder.itemView.setOnClickListener(view -> {
                Fragment quotesFragment = new CallFragment();
                MainActivity.setFragmentBackstack(getFragmentManager(), quotesFragment);
            });
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }
    }
}
