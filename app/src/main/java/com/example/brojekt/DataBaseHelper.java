package com.example.brojekt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DataBaseHelper extends SQLiteOpenHelper {
    public String databasePath = "";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //databasePath = context.getDatabasePath("CUSTOMER.db").getPath();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CUSTOMER(EMAIL TEXT PRIMARY KEY,FIRSTNAME TEXT," +
                "LASTNAME TEXT, PHONE TEXT, PASSWORD TEXT, GENDER TEXT, COUNTRY TEXT, CITY TEXT," +
                "ISADMIN BOOLEAN, CARS TEXT)") ;
        sqLiteDatabase.execSQL("CREATE TABLE CARS(MODEL TEXT PRIMARY KEY, MAKE TEXT , YEAR TEXT" +
                ", PRICE INT, DISTANCE INT, ACCIDENTS BOOLEAN, OFFERS BOOLEAN)");
    }
    public void insertCustomer(Customer customer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", customer.getEmail());
        contentValues.put("FIRSTNAME", customer.getFirstName());
        contentValues.put("LASTNAME", customer.getLastName());
        contentValues.put("PHONE", customer.getPhone());
        contentValues.put("PASSWORD", customer.getPassword());
        contentValues.put("GENDER", customer.getGender());
        contentValues.put("COUNTRY", customer.getCountry());
        contentValues.put("CITY", customer.getCity());
        contentValues.put("ISADMIN", customer.isAdmin());
        contentValues.put("CARS",customer.getCars());
        sqLiteDatabase.insert("CUSTOMER", null, contentValues);
    }
    public void updateCustomer(String email, Customer c){
        deleteCustomer(email);
        insertCustomer(c);
    }

    public void deleteCustomer(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("CUSTOMER", "EMAIL=?",new String[] {email});
    }

    public Customer getCustomer(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("CUSTOMER", new String[] {  "EMAIL",
                        "FIRSTNAME", "LASTNAME", "PHONE", "PASSWORD", "GENDER", "COUNTRY", "CITY", "ISADMIN","CARS" }, "EMAIL" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Customer co1 = new Customer(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5),
                cursor.getString(7), cursor.getString(8), cursor.getString(4), cursor.getString(6));
        return co1;
    }
    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMER", null);
    }

    public void insertCar(Car cars) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MODEL", cars.getModel());
        contentValues.put("MAKE", cars.getMake());
        contentValues.put("YEAR", cars.getYear());
        contentValues.put("PRICE", cars.getPrice());
        contentValues.put("DISTANCE", cars.getDistance());
        contentValues.put("ACCIDENTS", cars.isAccidents());
        contentValues.put("OFFERS", cars.isOffers());
        sqLiteDatabase.insert("CARS", null, contentValues);
    }

    public void deleteCar(String model){        //deletes a database entry with model
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("CARS", "MODEL = ?", new String[] {model});
    }

    public Cursor getAllCars() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CARS", null);
    }

}