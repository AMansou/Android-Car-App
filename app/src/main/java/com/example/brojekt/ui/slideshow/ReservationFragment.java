package com.example.brojekt.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        ConstraintLayout lay=(ConstraintLayout)root.findViewById(R.id.lay);
        lay.removeView(f);
        f.setText(customer.getCars().replace("%","\n\n") );
        ScrollView scroll=new ScrollView(container.getContext());
        LinearLayout line=new LinearLayout(container.getContext());
        line.addView(f);
        scroll.addView(line);
        lay.addView(scroll);

        return root;
    }
}
