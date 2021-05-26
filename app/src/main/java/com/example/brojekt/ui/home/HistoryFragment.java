package com.example.brojekt.ui.home;

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

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        historyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("This Car Agency is a family owned and operated business in operation since 1979. For over 40 years the Agency has been providing a commission free, no hassle alternative for buyers in search of clean, well-maintained, late model vehicles. We offer a large and diverse inventory of cars, trucks, and vans comprised of mostly low mileage vehicles. As the winner of the “Ethics in Business Award” and FIADA dealer of the year, we look forward providing the highest level of service, quality, and value to your next car purchase. We are located in Gainesville, FL with easy access to the surrounding areas of Ocala, Jacksonville, Orlando, Tampa, and southern Georgia.");
            }
        });
        return root;
    }
}
