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

import com.syzible.boop.ui.ContactItemDecoration;
import com.syzible.boop.activities.MainActivity;
import com.syzible.boop.R;
import com.syzible.boop.objects.Contact;
import com.syzible.boop.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

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

        generateContactsList();
        setupAdapter();

        return view;
    }

    private void setupAdapter() {
        RecyclerView.Adapter adapter = new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    private void generateContactsList() {
        contacts = new ArrayList<>();

        Contact contact1 = new Contact("Alexey", "Kuznetsov", "+353 86 736 0400");
        Contact contact2 = new Contact("Emma", "Sheeran", "+353 86 736 0401");
        Contact contact3 = new Contact("Adrian", "Chojnacki", "+353 86 736 0402");
        Contact contact4 = new Contact("Aaron", "Barry", "+353 86 736 0403");
        Contact contact5 = new Contact("Maciej", "Grabowski", "+353 86 736 0404");

        contact1.setLastTimeRung(System.currentTimeMillis());

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
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
