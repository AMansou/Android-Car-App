package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask = new
                        ConnectionAsyncTask(MainActivity.this);

                connectionAsyncTask.execute("http://www.mocky.io/v2/5bfea5963100006300bb4d9a");
                Intent intent = new Intent( MainActivity.this, Login_1172631_1171821.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.layout);
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void fillCars(List<Car> car) {   //this function loads the cars into the sqlite database
        //System.out.println(car.get(1));
        DataBaseHelper dbhp = new DataBaseHelper(MainActivity.this,"DB", null,1);
        //SQLiteDatabase sqdb = dbhp.getWritableDatabase();
        for (int i = 0; i < car.size(); i++) {
            dbhp.insertCar(car.get(i));
        }


    }
    public void setButtonText(String text) {
        button.setText(text);
    }
}
