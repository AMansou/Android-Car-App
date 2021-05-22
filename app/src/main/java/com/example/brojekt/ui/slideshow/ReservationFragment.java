package com.example.brojekt.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.brojekt.R;

public class ReservationFragment extends Fragment {

    private ReservationViewFragment reservationViewFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reservationViewFragment =
                ViewModelProviders.of(this).get(ReservationViewFragment.class);
        View root = inflater.inflate(R.layout.fragment_reservation, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        reservationViewFragment.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
