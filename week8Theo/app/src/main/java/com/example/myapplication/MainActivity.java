package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements Fragment2.StudentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //make button switch between activities:
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager(); //fragment manager is used to switch between fragments.
                if (fm.findFragmentByTag("first_fragment")== null){
                    FragmentTransaction ft = fm.beginTransaction();//update UI with new fragment.
                    //change smth in UI.
                    ft.replace(R.id.fragmentContainerView, Fragment1.newInstance("Seham", "Othman"), "first_fragment");
                    ft.addToBackStack("first"); //back to Fragment 2!! implement back button!
                    ft.commit();
                    btn.setText("Previous");

                }else{
                    fm.popBackStack(); //remove lastly added fragment.
                    btn.setText("Next");

                }


                //check if fragments exist!

            }
        });

    }
    //ovvired method!
    public void StudentChanged(String student){
        this.student = student;
    }
    //write name in main !
//student name ,select , (studentchanged method) , click next ! mehmet will go to next fragment!
}