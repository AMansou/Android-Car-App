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

import static com.example.brojekt.Login_1172631_1171821.customer;

public class ReservationFragment extends Fragment {

    private ReservationViewFragment reservationViewFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_reservation,container, false);
        TextView f=(TextView) root.findViewById(R.id.text_slideshow);
        f.setText(customer.getCars().replace("%","\n\n") );
        return root;
    }
}
