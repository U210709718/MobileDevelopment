package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArrayAdapterActivity extends ListActivity {
    static final String [] ANIMALS = new String[] {"Dog", "Cat", "Bear", "Butterfly"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Adappter classes is a bridge between view and data
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_array_adapter, ANIMALS));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        //When click a row view message
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),((TextView)view).getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static class AnimalAdapter {
    }
}