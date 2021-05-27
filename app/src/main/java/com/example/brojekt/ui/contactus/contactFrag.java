package com.example.brojekt.ui.contactus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.brojekt.Login_1172631_1171821;
import com.example.brojekt.R;

public class contactFrag extends Fragment {
    Button callus;
    Button findus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        callus = view.findViewById(R.id.button_callus);
        findus = view.findViewById(R.id.button_findus);


        callus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent dial = new Intent();
                dial.setAction(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+970598156800"));
                startActivity(dial);
            }
        });
        findus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mapsIntent =new Intent();
                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:29.688954192770034, -82.32113984506283"));
                startActivity(mapsIntent);
            }
        });
        return view;
    }
}