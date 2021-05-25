package com.example.brojekt.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.brojekt.R;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_profile,container, false);
        final TextView textEmail=(TextView) root.findViewById(R.id.textEmail);
        final TextView textFname=(TextView) root.findViewById(R.id.textFname);
        final TextView textLname=(TextView) root.findViewById(R.id.textLname);
        final TextView textPass=(TextView) root.findViewById(R.id.textPass);
        final TextView textPhone=(TextView) root.findViewById(R.id.textPhone);
        final TextView textGender=(TextView) root.findViewById(R.id.textGender);
        final TextView textCountry=(TextView) root.findViewById(R.id.textCountry);
        final TextView textCity=(TextView) root.findViewById(R.id.textCity);
        textEmail.setText(customer.getEmail());
        textFname.setText(customer.getFirstName());
        textLname.setText(customer.getLastName());
        textPass.setText(customer.getPassword());
        textPhone.setText(customer.getPhone());
        textGender.setText(customer.getGender());
        textCountry.setText(customer.getCountry());
        textCity.setText(customer.getCity());


        return root;
    }
}
