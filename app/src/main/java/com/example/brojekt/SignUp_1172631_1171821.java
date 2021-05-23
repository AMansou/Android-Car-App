package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp_1172631_1171821 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        final TextView code = (TextView)findViewById(R.id.code);
        Button addCustomerButton = (Button) findViewById(R.id.button_Add_Customer);
        countrySpinner.setOnItemSelectedListener(this);


        addCustomerButton.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {
            DataBaseHelper dataBaseHelper =new DataBaseHelper(SignUp_1172631_1171821.this,"CUSTOMER",null,1);
            Customer newCustomer =new Customer();
            Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
            while (allCustomersCursor.moveToNext()){

                if(email.getText().toString().equals(allCustomersCursor.getString(0))) {
                    Toast.makeText(SignUp_1172631_1171821.this, " E-mail Address taken",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            //allCustomersCursor.moveToFirst();
            //fname.setText(allCustomersCursor.getString(0)+"\n");
            if(email.getText().toString().isEmpty())
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid E-mail Address",
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
            if(!cpass.getText().toString().equals(pass.getText().toString()) ) {
                Toast.makeText(SignUp_1172631_1171821.this, "Passwords Don't Match!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
                newCustomer.setPassword(pass.getText().toString());
            if(phone.getText().toString().isEmpty())
                Toast.makeText(SignUp_1172631_1171821.this, "Invalid Phone",
                        Toast.LENGTH_SHORT).show();
            else
                newCustomer.setPhone(code.getText().toString()+phone.getText().toString());


            newCustomer.setGender(genderSpinner.getSelectedItem().toString());
            newCustomer.setCountry(countrySpinner.getSelectedItem().toString());
            newCustomer.setCity(citySpinner.getSelectedItem().toString());
            newCustomer.setCars("");
            newCustomer.setFavorites("");
            newCustomer.setAdmin(false);
            dataBaseHelper =new DataBaseHelper(SignUp_1172631_1171821.this,"CUSTOMER",null,1);
            dataBaseHelper.insertCustomer(newCustomer);
            Intent intent=new Intent(SignUp_1172631_1171821.this,Login_1172631_1171821.class);
            SignUp_1172631_1171821.this.startActivity(intent);
            finish();
        } });
        Button back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SignUp_1172631_1171821.this, Login_1172631_1171821.class);
                SignUp_1172631_1171821.this.startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final TextView code = (TextView)findViewById(R.id.code);
        final Spinner countrySpinner =(Spinner) findViewById(R.id.country);
        final Spinner citySpinner =(Spinner) findViewById(R.id.city);

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
        ArrayAdapter<String> objCityArr = new ArrayAdapter<>(SignUp_1172631_1171821.this,android.R.layout.simple_spinner_item, CiOptions);
        citySpinner.setAdapter(objCityArr);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
