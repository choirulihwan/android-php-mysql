package com.example.android_php_mysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter {

    List list = new ArrayList<>();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Contact object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;

        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            contactHolder = new ContactHolder();
            contactHolder.txt_name = row.findViewById(R.id.txt_name);
            contactHolder.txt_email = row.findViewById(R.id.txt_email);
            contactHolder.txt_contact = row.findViewById(R.id.txt_contact);
            //contactHolder.txt_password = row.findViewById(R.id.txt_password);
            row.setTag(contactHolder);
        } else {
            contactHolder = (ContactHolder) row.getTag();
        }

        Contact contact = (Contact) this.getItem(position);
        contactHolder.txt_name.setText(contact.getName());
        contactHolder.txt_email.setText(contact.getEmail());
        contactHolder.txt_contact.setText(contact.getContact());
        //contactHolder.txt_password.setText(contact.getPassword());

        return row;
    }

    static class ContactHolder {
        TextView txt_name, txt_email, txt_contact, txt_password;
    }
}
