package com.example.brojekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarFrag2 extends Fragment {
    private String s;
    public CarFrag2() {

    }

    public void setText(String s){
        this.s=s;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_car, container, false);

        //set the text of your text view
        TextView textView = (TextView) view.findViewById(R.id.hola2);
        textView.setText(s);
        // Inflate the layout for this fragment
        return view;
    }



}
