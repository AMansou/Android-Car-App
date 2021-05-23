package com.example.brojekt.ui.logout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.brojekt.AdminHome_1172631_1171821;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Login_1172631_1171821;
import com.example.brojekt.R;
public class LogoutFragment extends Fragment {
    private Button logout;
    Context thiscontext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        thiscontext = container.getContext();
        logout = root.findViewById(R.id.button_logout_admin);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( thiscontext, Login_1172631_1171821.class);
                thiscontext.startActivity(intent);
            }
        });
        return root;
    }
}
