package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);

        final List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Dog", R.mipmap.ic_launcher));
        animals.add(new Animal("Butterfly", R.mipmap.ic_launcher));

        final ListView listView = findViewById(R.id.listView);
        AnimalAdapter animalAdapter = new AnimalAdapter(this, animals);
        listView.setAdapter(animalAdapter);



    }
}