package com.example.brojekt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CUSTOMER(ID LONG PRIMARY KEY,NAME TEXT, PHONE TEXT,GENDER TEXT)") ;
        sqLiteDatabase.execSQL("CREATE TABLE CARS(MODEL TEXT PRIMARY KEY, MAKE TEXT, YEAR TEXT" +
                ", PRICE INT, DISTANCE INT, ACCIDENTS BOOLEAN, OFFERS BOOLEAN)");
    }
    public void insertCustomer(Customer customer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", customer.getEmail());
        contentValues.put("FIRST NAME", customer.getFirstName());
        contentValues.put("LAST NAME", customer.getLastName());
        contentValues.put("PHONE", customer.getPhone());
        contentValues.put("PASSWORD", customer.getPassword());
        contentValues.put("GENDER", customer.getGender());
        contentValues.put("COUNTRY", customer.getCountry());
        contentValues.put("CITY", customer.getCity());
        sqLiteDatabase.insert("CUSTOMER", null, contentValues);
    }

    public void deleteCustomer(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //ontentValues contentValues = new ContentValues();
        //contentValues.put("ID", customer.getID());
        sqLiteDatabase.delete("CUSTOMER", "ID=?",new String[] {id});
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