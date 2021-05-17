package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login_1172631_1171821 extends AppCompatActivity {
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

                Intent intent = new Intent( Login_1172631_1171821.this, CustomerHome_1172631_1171821.class);
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
                if (save.isChecked())
                {
                    shared.writeString("email",email.getText().toString());
                    shared.writeString("password",password.getText().toString());
                }

                Intent intent = new Intent( Login_1172631_1171821.this, CustomerHome_1172631_1171821.class);
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
