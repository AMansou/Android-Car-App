package com.example.brojekt.ui.profile;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.brojekt.Customer;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.R;

import java.util.regex.Pattern;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_profile,container, false);
        String[] GOptions = { "Male", "Female" };
        String[] COptions = { "Japan", "France", "Russia","Tunisia" };
        final Spinner genderSpinner =(Spinner) root.findViewById(R.id.gender);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, GOptions);
        genderSpinner.setAdapter(objGenderArr);
        final Spinner countrySpinner =(Spinner) root.findViewById(R.id.country);
        ArrayAdapter objCountryArr = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, COptions);
        countrySpinner.setAdapter(objCountryArr);
        final Spinner citySpinner =(Spinner) root.findViewById(R.id.city);
        final EditText email = (EditText)root.findViewById(R.id.email);
        final EditText fname = (EditText)root.findViewById(R.id.fname);
        final EditText lname = (EditText)root.findViewById(R.id.lname);
        final EditText pass = (EditText)root.findViewById(R.id.pass);
        final EditText cpass = (EditText)root.findViewById(R.id.cpass);
        final EditText phone = (EditText)root.findViewById(R.id.phone);
        final TextView code = (TextView)root.findViewById(R.id.code);
        Button addCustomerButton = (Button) root.findViewById(R.id.button_Add_Customer);
        countrySpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        addCustomerButton.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {
            DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
            Customer newCustomer =new Customer();
            Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
            while (allCustomersCursor.moveToNext()){

                if(email.getText().toString().equals(allCustomersCursor.getString(0))) {
                    Toast.makeText(container.getContext(), " E-mail Address taken",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            //allCustomersCursor.moveToFirst();
            //fname.setText(allCustomersCursor.getString(0)+"\n");
            if(email.getText().toString().isEmpty()) {
                Toast.makeText(container.getContext(), "Invalid E-mail Address",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            else
                newCustomer.setEmail(email.getText().toString());
            if(fname.getText().toString().isEmpty() || Pattern.matches("[0-9]+",fname.getText().toString())) {
                Toast.makeText(container.getContext(), "Invalid First Name",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setFirstName(fname.getText().toString());
            if(lname.getText().toString().isEmpty()){
                Toast.makeText(container.getContext(), "Invalid Last Name",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setLastName(lname.getText().toString());
            if(pass.getText().toString().isEmpty()) {
                Toast.makeText(container.getContext(), "Invalid Password",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if(!cpass.getText().toString().equals(pass.getText().toString()) ) {
                Toast.makeText(container.getContext(), "Passwords Don't Match!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setPassword(pass.getText().toString());
            if(phone.getText().toString().isEmpty())
                Toast.makeText(container.getContext(), "Invalid Phone",
                        Toast.LENGTH_SHORT).show();
            else
                newCustomer.setPhone(code.getText().toString()+phone.getText().toString());


            newCustomer.setGender(genderSpinner.getSelectedItem().toString());
            newCustomer.setCountry(countrySpinner.getSelectedItem().toString());
            newCustomer.setCity(citySpinner.getSelectedItem().toString());
            newCustomer.setCars("");
            newCustomer.setFavorites("");
            newCustomer.setAdmin(false);
            dataBaseHelper =new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
            dataBaseHelper.insertCustomer(newCustomer);
        } });
        Button back=(Button) root.findViewById(R.id.back);
        return root;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final TextView code = (TextView) adapterView.findViewById(R.id.code);
        final Spinner countrySpinner =(Spinner) adapterView.findViewById(R.id.country);
        final Spinner citySpinner =(Spinner) adapterView.findViewById(R.id.city);

        String[] CiOptions = new String[0];
        if (countrySpinner.getSelectedItem().toString().equals("Japan") ) {
            CiOptions = new String[]{"Tokyo", "Hiroshima", "Nagasaki"};
            code.setText("00970");
        }
        if (countrySpinner.getSelectedItem().toString().equals("France") ) {
            CiOptions = new String[]{"Paris", "Marseille", "Lyon"};
            code.setText("00971");
        }
        if (countrySpinner.getSelectedItem().toString().equals("Russia") ) {
            CiOptions = new String[]{"Moscow", "Volgograd", "Irkutsk"};
            code.setText("00972");
        }
        if (countrySpinner.getSelectedItem().toString().equals( "Tunisia") ) {
            CiOptions = new String[]{"Tunis", "Sfax", "Sousse"};
            code.setText("00973");
        }
        ArrayAdapter<String> objCityArr = new ArrayAdapter<>(container.getContext(),android.R.layout.simple_spinner_item, CiOptions);
        citySpinner.setAdapter(objCityArr);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
