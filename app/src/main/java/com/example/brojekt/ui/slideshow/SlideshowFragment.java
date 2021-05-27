package com.example.brojekt.ui.slideshow;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.R;

public class SlideshowFragment extends Fragment {

    TextView carResDisplay;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        carResDisplay = root.findViewById(R.id.editTextTextMultiLineAdminCarRes);
        String carReservations = "";
        final DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
        Cursor c = dataBaseHelper.getAllCustomers();

        while(c.moveToNext()){
            if(!c.getString(9).equals("")) {
                carReservations = carReservations + "Customer:" + c.getString(0) + "\n" + c.getString(9);
            }
        }
        carResDisplay.setText(carReservations.replace("%","\n\n"));
        return root;
    }
}
