package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login_1172631_1171821 extends AppCompatActivity {
    public static Customer customer=new Customer();
    SharedPrefManager shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1172631_1171821);
        final RadioButton save=(RadioButton) findViewById(R.id.save);
        Button login=(Button) findViewById(R.id.signIn);
        final EditText email=(EditText) findViewById(R.id.email);
        final EditText password=(EditText) findViewById(R.id.password);
        shared=SharedPrefManager.getInstance(this);
        if (shared.readString("email","noValue")!="noValue") {
            email.setText(shared.readString("email", "noValue"));
            password.setText(shared.readString("password", "noValue"));
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (save.isSelected())
                {
                    shared.writeString("email",email.getText().toString());
                    shared.writeString("password",password.getText().toString());
                }


                DataBaseHelper dataBaseHelper1 =new DataBaseHelper(Login_1172631_1171821.this,"CUSTOMER",null,1);
                Cursor allCustomersCursor = dataBaseHelper1.getAllCustomers();
                int adminflag = 0;

                if(email.getText().toString().trim().equalsIgnoreCase("admin@admin.com")){
                    adminflag = 1;
                }
                while (allCustomersCursor.moveToNext()) {
                    if (allCustomersCursor.getString(0).trim().equals(email.getText().toString().trim()) && allCustomersCursor.getString(8).equals("true")){
                        adminflag = 1;
                    }
                }
                if (adminflag == 1){
                    Intent intent = new Intent( Login_1172631_1171821.this, AdminHome_1172631_1171821.class);
                    Login_1172631_1171821.this.startActivity(intent);
                    finish();
                }

                Intent intent = new Intent( Login_1172631_1171821.this, AdminHome_1172631_1171821.class);
                Login_1172631_1171821.this.startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final EditText email=(EditText) findViewById(R.id.email);
        final EditText password=(EditText) findViewById(R.id.password);
        shared=SharedPrefManager.getInstance(this);
        if (shared.readString("email","noValue")!="noValue") {
            email.setText(shared.readString("email", "noValue"));
            password.setText(shared.readString("password", "noValue"));

        }
        final RadioButton save=(RadioButton) findViewById(R.id.save);
        Button login=(Button) findViewById(R.id.signIn);
        shared=SharedPrefManager.getInstance(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper =new DataBaseHelper(Login_1172631_1171821.this,"CUSTOMER",null,1);
                Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
                while (allCustomersCursor.moveToNext()){

                    if(email.getText().toString().equals(allCustomersCursor.getString(0)) &&
                            password.getText().toString().equals(allCustomersCursor.getString(4)))
                        break;
                    if(allCustomersCursor.isLast())
                    {
                        Toast.makeText(Login_1172631_1171821.this, "Wrong Email/password combination",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                if (save.isChecked())
                {
                    shared.writeString("email",email.getText().toString());
                    shared.writeString("password",password.getText().toString());
                }

                DataBaseHelper dataBaseHelper1 =new DataBaseHelper(Login_1172631_1171821.this,"CUSTOMER",null,1);
                Cursor customercursoradmin = dataBaseHelper1.getAllCustomers();
                int adminflag = 0;

                if(email.getText().toString().trim().equalsIgnoreCase("admin@admin.com")){
                    adminflag = 1;
                }
                while (customercursoradmin.moveToNext()) {
                    if (customercursoradmin.getString(0).trim().equals(email.getText().
                            toString().trim()) && allCustomersCursor.getString(8).equals("true")){
                        adminflag = 1;
                    }
                }
                if (adminflag == 1){
                    Intent intent = new Intent( Login_1172631_1171821.this, AdminHome_1172631_1171821.class);
                    Login_1172631_1171821.this.startActivity(intent);
                    finish();
                }
                customer.setEmail(allCustomersCursor.getString(0));
                customer.setFirstName(allCustomersCursor.getString(1));
                customer.setLastName(allCustomersCursor.getString(2));
                customer.setPhone(allCustomersCursor.getString(3));
                customer.setPassword(allCustomersCursor.getString(4));
                customer.setGender(allCustomersCursor.getString(5));
                customer.setCountry(allCustomersCursor.getString(6));
                customer.setCity(allCustomersCursor.getString(7));
                customer.setAdmin(Boolean.parseBoolean(allCustomersCursor.getString(8)) );
                customer.setCars(allCustomersCursor.getString(9));
                customer.setFavorites(allCustomersCursor.getString(10));
                Intent intent = new Intent( Login_1172631_1171821.this, AdminHome_1172631_1171821.class);
                Login_1172631_1171821.this.startActivity(intent);
                finish();
            }
        });
        Button signUp=(Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_1172631_1171821.this, SignUp_1172631_1171821.class);
                Login_1172631_1171821.this.startActivity(intent);
                finish();
            }
        });

    }
}
