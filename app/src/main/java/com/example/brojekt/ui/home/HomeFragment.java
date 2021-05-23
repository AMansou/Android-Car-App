package com.example.brojekt.ui.home;

import android.content.Context;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.brojekt.AdminHome_1172631_1171821;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Login_1172631_1171821;
import com.example.brojekt.R;

public class HomeFragment extends Fragment {

    private EditText emailcustomer;
    private Button removecustomer;
    Context thiscontext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        emailcustomer = root.findViewById(R.id.editTextRemoveCustomer);
        removecustomer = root.findViewById(R.id.button_remove_customer);
        thiscontext = container.getContext();

        removecustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(thiscontext,"CUSTOMER",null,1);
                String email = emailcustomer.getText().toString();
                Cursor allCustomersCursor = db.getAllCustomers();
                while (allCustomersCursor.moveToNext()){

                    if(email.trim().equalsIgnoreCase(allCustomersCursor.getString(0))){
                        break;
                    }
                    if(allCustomersCursor.isLast())
                    {
                        Toast.makeText(thiscontext, " This customer does not exist",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                db.deleteCustomer(email);
                Toast.makeText(thiscontext, email + " has been removed", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}
