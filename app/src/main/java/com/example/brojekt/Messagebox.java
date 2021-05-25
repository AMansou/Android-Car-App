package com.example.brojekt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class Messagebox
{
    public void show(String title, final String message, final Context context)
    {
        dialog = new AlertDialog.Builder(context) // Pass a reference to your main activity here
                .setTitle("Are You Sure You Want to Reserve This Car?")
                .setMessage(message)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataBaseHelper dataBaseHelper=new DataBaseHelper(context,"CUSTOMER",null,1);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                        String currentDateandTime = sdf.format(new Date());
                        customer.setCars(customer.getCars()+message+" Date and Time: "+currentDateandTime+"%");
                        dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }
    public void show2(String title, final String message, final Context context)
    {
        dialog = new AlertDialog.Builder(context) // Pass a reference to your main activity here
                .setTitle("Are You Sure You Want to UN-Reserve This Car?")
                .setMessage(message)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataBaseHelper dataBaseHelper=new DataBaseHelper(context,"CUSTOMER",null,1);
                        int index=customer.getCars().indexOf(message);
                        customer.setCars(customer.getCars().replace(message,""));
                        customer.setCars(customer.getCars().substring(0,index)+customer.getCars().substring(index+52));
                        //customer.setCars("");
                        dataBaseHelper.updateCustomer(customer.getEmail(),customer);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }
    public void show3(String title, final String message, final Context context)
    {
        dialog = new AlertDialog.Builder(context) // Pass a reference to your main activity here
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private AlertDialog dialog;
}