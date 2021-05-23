package com.example.brojekt.ui.gallery;

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

import com.example.brojekt.Customer;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.R;

public class GalleryFragment extends Fragment {
    private EditText emailcustomer;
    private Button removecustomer;
    Context thiscontext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        emailcustomer = root.findViewById(R.id.editTextAddAdmin);
        removecustomer = root.findViewById(R.id.button_add_admin);
        thiscontext = container.getContext();

        removecustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(thiscontext, "CUSTOMER", null, 1);
                String email = emailcustomer.getText().toString();
                Cursor allCustomersCursor = db.getAllCustomers();
                Customer customer = new Customer();
                while (allCustomersCursor.moveToNext()) {

                    if (email.trim().equalsIgnoreCase(allCustomersCursor.getString(0))) {
                        customer.setEmail(allCustomersCursor.getString(0));
                        customer.setFirstName(allCustomersCursor.getString(1));
                        customer.setLastName(allCustomersCursor.getString(2));
                        customer.setPhone(allCustomersCursor.getString(3));
                        customer.setPassword(allCustomersCursor.getString(4));
                        customer.setGender(allCustomersCursor.getString(5));
                        customer.setCountry(allCustomersCursor.getString(6));
                        customer.setCity(allCustomersCursor.getString(7));
                        customer.setAdmin(true);
                        customer.setCars(allCustomersCursor.getString(9));
                        customer.setFavorites(allCustomersCursor.getString(10));
                        break;
                    }
                    if (allCustomersCursor.isLast()) {
                        Toast.makeText(thiscontext, " This customer does not exist",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                db.updateCustomer(email, customer);
                Toast.makeText(thiscontext, email + " has been set as admin", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }
}
