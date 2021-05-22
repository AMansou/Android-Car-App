package com.example.brojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class AdminPage_1172631_1171821 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_1172631_1171821);
        navigationView = findViewById(R.id.navigation_view_admin);
        drawerLayout = findViewById(R.id.admin_drawer);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_customer:{
                System.out.println("delete customer");
                break;
            }
            case R.id.add_admin:{
                System.out.println("add admin");
                break;
            }
            case R.id.view_reserves:{
                System.out.println("reserves");
                break;
            }
            case R.id.logout_admin:{
                System.out.println("log admin out");
                break;
            }
        }
        return false;
    }
}