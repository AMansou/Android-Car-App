package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp_1172631_1171821 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_1172631_1171821);
        String[] GOptions = { "Male", "Female" };
        String[] COptions = { "Japan", "France", "Russia","Tunisia" };
        final Spinner genderSpinner =(Spinner) findViewById(R.id.gender);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, GOptions);
        genderSpinner.setAdapter(objGenderArr);
        final Spinner countrySpinner =(Spinner) findViewById(R.id.country);
        ArrayAdapter<String> objCountryArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, COptions);
        countrySpinner.setAdapter(objCountryArr);
        final Spinner citySpinner =(Spinner) findViewById(R.id.city);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText fname = (EditText)findViewById(R.id.fname);
        final EditText lname = (EditText)findViewById(R.id.lname);
        final EditText pass = (EditText)findViewById(R.id.pass);
        final EditText cpass = (EditText)findViewById(R.id.cpass);
        final EditText phone = (EditText)findViewById(R.id.phone);
        Button addCustomerButton = (Button) findViewById(R.id.button_Add_Customer);
        addCustomerButton.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {
            Customer newCustomer =new Customer();
            if(email.getText().toString().isEmpty())
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid E-mail Adress",
                        Toast.LENGTH_SHORT).show();
            else
                newCustomer.setEmail(email.getText().toString());
            if(fname.getText().toString().isEmpty() || Pattern.matches("[0-9]+",fname.getText().toString())) {
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid First Name",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setFirstName(fname.getText().toString());
            if(lname.getText().toString().isEmpty()){
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid Last Name",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setLastName(lname.getText().toString());
            if(pass.getText().toString().isEmpty()) {
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid Password",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if(cpass.getText().toString()!=pass.getText().toString()) {
                Toast.makeText(SignUp_1172631_1171821.this, "Passwords don't match",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setPassword(pass.getText().toString());
            if(email.getText().toString().isEmpty())
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid Phone",
                        Toast.LENGTH_SHORT).show();
            else
                newCustomer.setPhone(phone.getText().toString());
            String[] CiOptions = new String[0];
            if (countrySpinner.getSelectedItem().toString()== "Japan")
                CiOptions = new String[]{"Tokyo", "Hiroshima", "Nagasaki"};
            if (countrySpinner.getSelectedItem().toString()== "France")
                CiOptions = new String[]{"Paris", "Marseille", "Lyon"};
            if (countrySpinner.getSelectedItem().toString()== "Russia")
                CiOptions = new String[]{"Moscow", "Volgograd", "Irkutsk"};
            if (countrySpinner.getSelectedItem().toString()== "Tunisia")
                CiOptions = new String[]{"Tunis", "Sfax", "Sousse"};
            Toast.makeText(SignUp_1172631_1171821.this, CiOptions[0],
                    Toast.LENGTH_SHORT).show();
            ArrayAdapter<String> objCityArr = new ArrayAdapter<>(SignUp_1172631_1171821.this,android.R.layout.simple_spinner_item, CiOptions);
            citySpinner.setAdapter(objCityArr);
            newCustomer.setGender(genderSpinner.getSelectedItem().toString());
            newCustomer.setCountry(countrySpinner.getSelectedItem().toString());
            newCustomer.setCity(citySpinner.getSelectedItem().toString());
            DataBaseHelper dataBaseHelper =new DataBaseHelper(SignUp_1172631_1171821.this,"Customers",null,1);
            dataBaseHelper.insertCustomer(newCustomer);
            Intent intent=new Intent(SignUp_1172631_1171821.this,CustomerHome_1172631_1171821.class);
            SignUp_1172631_1171821.this.startActivity(intent);
            finish();
        } });


    }
}
