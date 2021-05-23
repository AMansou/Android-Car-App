package com.example.brojekt.ui.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.brojekt.R;
public class LogoutFragment extends Fragment {
    TextView text_logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        text_logout = root.findViewById(R.id.text_logout);
        text_logout.setText("Are you sure you want to logout?");
        return root;
    }
}
