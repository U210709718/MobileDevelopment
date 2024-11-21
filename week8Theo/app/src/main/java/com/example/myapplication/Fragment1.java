package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";
    private static final String ARG_SURNAME = "surname";

    // TODO: Rename and change types of parameters
    private String name;
    private String surname;

    public Fragment1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String name, String surname) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_SURNAME, surname);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //life circle method . started!
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            surname = getArguments().getString(ARG_SURNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //layout created in another call back method!
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_1, container, false);
//        view.findViewById(R.id.name).setText(name);
//        return view;

        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText txtName = view.findViewById(R.id.name);
        txtName.setText(name);
        EditText txt2Name = view.findViewById(R.id.surname);
        txt2Name.setText(surname);

    }
}