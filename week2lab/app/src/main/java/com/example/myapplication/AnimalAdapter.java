package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.zip.Inflater;

public class AnimalAdapter extends BaseAdapter {
    private List<Animal> animals;
    private LayoutInflater mInflater;

    public AnimalAdapter(Activity activity, List<Animal> animals) {
        mInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.animals = animals;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        //make row for each animal
        rowView = mInflater.inflate(R.layout.listview_row, null);
        TextView textView = rowView.findViewById(R.id.label);
        ImageView imageView = rowView.findViewById(R.id.pic);
        Animal animal = animals.get(position);
        textView.setText(animal.getType());
        imageView.setImageResource(animal.getPicId());

        return rowView;
    }
}
