package com.example.foolishfan.user_v10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<Person> list;
    private LayoutInflater inflater;

    public ListAdapter(List<Person> list, Context context) {
        this.list = list;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<Person> list) {
        this.list = list;
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_main_list1, null);
        TextView idView = (TextView) convertView.findViewById(R.id.text_id);
        TextView nameView = (TextView) convertView.findViewById(R.id.text_name);
        TextView ageView = (TextView) convertView.findViewById(R.id.text_age);
        TextView sexView = (TextView) convertView.findViewById(R.id.text_sex);
        Person person = list.get(position);
        idView.setText(String.valueOf(person.get_id()));
        nameView.setText(person.getName());
        ageView.setText(String.valueOf(person.getAge()));
        sexView.setText(String.valueOf(person.getSex()));
        return convertView;
    }

}
