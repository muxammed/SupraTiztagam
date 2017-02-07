package ru.supratiztagam.supratiztagam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muxammed on 26.01.2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "supratizdb";

    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "uid";
    private static final String KEY_WWWID = "wwwid";
    private static final String KEY_UNAME = "uname";
    private static final String KEY_UPASS = "upass";
    private static final String KEY_UNUMBER = "unumber";
    private static final String KEY_UTOKEN = "utoken";
    private static final String KEY_UISACTIVE = "uisactive";
    private static final String KEY_ULOGEDIN = "ulogedin";


    //TABLE LANG
    private static final String TABLE_CURLAN = "curlang";
    private static final String KEY_LANG = "lang";




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UNAME + " TEXT,"
                + KEY_UPASS + " TEXT," + KEY_UTOKEN + " TEXT,"
                + KEY_UNUMBER + " TEXT," + KEY_UISACTIVE + " TEXT,"
                + KEY_ULOGEDIN + " TEXT , " + KEY_WWWID + " INTEGER " + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_CURLANG_TABLE = "CREATE TABLE " + TABLE_CURLAN + "("
                + KEY_LANG + " INTEGER )";
        db.execSQL(CREATE_CURLANG_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURLAN);


        onCreate(db);
    }

    public void addCurLang(int lang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LANG, lang);

        db.insert(TABLE_CURLAN, null, values);
        db.close();
    }


    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int sany = cursor.getCount();
        cursor.close();

        return sany;
    }


    public int getCurLang() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CURLAN, new String[]{KEY_LANG}, null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int curlang = Integer.parseInt(cursor.getString(0));
        cursor.close();
        db.close();

        return curlang;
    }


    public int getLangCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CURLAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int sany = cursor.getCount();
        cursor.close();
        db.close();
        return sany;
    }

    public int updateCurLang(int lang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LANG, lang);

        return db.update(TABLE_CURLAN, values, null,
                null);
    }



//
//
//    public long addPastOrder(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_ONUMBER, order.get_onumber());
//        values.put(KEY_OADRESS, order.get_oadress());
//        values.put(KEY_OTYPE, order.get_otype());
//        values.put(KEY_OCOUNT, order.get_ocount());
//        values.put(KEY_DTIME, order.get_odtime());
//        values.put(KEY_OOFISID, order.get_oofisid());
//        values.put(KEY_OSTATUS, order.get_ostatus());
//        //values.put(KEY_ODELDTIME, null);
//
//        long id = db.insert(TABLE_PASTORDS, null, values);
//        db.close();
//        return id;
//
//    }
//
//    public int getPastOrdersCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_PASTORDS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        int sany = cursor.getCount();
//        cursor.close();
//        db.close();
//        return sany;
//    }
//
//    public long addOrder(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_ONUMBER, order.get_onumber());
//        values.put(KEY_OADRESS, order.get_oadress());
//        values.put(KEY_OTYPE, order.get_otype());
//        values.put(KEY_OCOUNT, order.get_ocount());
//        values.put(KEY_DTIME, order.get_odtime());
//        values.put(KEY_OOFISID, order.get_oofisid());
//        values.put(KEY_OSTATUS, order.get_ostatus());
//        //values.put(KEY_ODELDTIME, null);
//
//        long id = db.insert(TABLE_TDORDS, null, values);
//        db.close();
//        return id;
//
//    }
//
//    public List<Adress> getAllAdresses() {
//        List<Adress> adressList = new ArrayList<Adress>();
//        String selectQuery = "SELECT  * FROM " + TABLE_ADRESLER;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Adress adress = new Adress();
//                adress.set_id(Integer.parseInt(cursor.getString(0)));
//                adress.set_cnumber(cursor.getString(1));
//                adress.set_cadress(cursor.getString(2));
//                adress.set_ctype(cursor.getString(3));
//                adressList.add(adress);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//        return adressList;
//    }
//
//    public void deleteOrder(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_TDORDS, KEY_ID + " = ?", new String[]{String.valueOf(order.get_id())});
//        db.close();
//    }
//
//    public int getOrdersTdCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_TDORDS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        int sany = cursor.getCount();
//        cursor.close();
//        db.close();
//        return sany;
//    }
//
//    public void deleteAllOrdersTd() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_TDORDS, null, null);
//        db.close();
//    }
//
//    public int updateOrder(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ONUMBER, order.get_onumber());
//        values.put(KEY_OADRESS, order.get_oadress());
//        values.put(KEY_OTYPE, order.get_otype());
//        values.put(KEY_OCOUNT, order.get_ocount());
//        values.put(KEY_DTIME, order.get_odtime());
//        values.put(KEY_OOFISID, order.get_oofisid());
//        values.put(KEY_OSTATUS, order.get_ostatus());
//        values.put(KEY_ODELDTIME, order.get_odeldtime());
//
//        return db.update(TABLE_TDORDS, values, KEY_ID + " = ?",
//                new String[]{String.valueOf(order.get_id())});
//    }
//
//    public int updateOrderByOfisId(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ONUMBER, order.get_onumber());
//        values.put(KEY_OADRESS, order.get_oadress());
//        values.put(KEY_OTYPE, order.get_otype());
//        values.put(KEY_OCOUNT, order.get_ocount());
//        values.put(KEY_DTIME, order.get_odtime());
//        values.put(KEY_OOFISID, order.get_oofisid());
//        values.put(KEY_OSTATUS, order.get_ostatus());
//        values.put(KEY_ODELDTIME, order.get_odeldtime());
//
//        return db.update(TABLE_TDORDS, values, KEY_ID + " = ?",
//                new String[]{String.valueOf(order.get_id())});
//    }
//
//
//    public List<Order> getAllOrders() {
//        List<Order> ordersList = new ArrayList<Order>();
//        String selectQuery = "SELECT  * FROM " + TABLE_TDORDS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//
//
//                Order order = new Order();
//                order.set_id(Integer.parseInt(cursor.getString(0)));
//                order.set_onumber(cursor.getString(1));
//                order.set_oadress(cursor.getString(2));
//                order.set_ocount(cursor.getString(3));
//                order.set_odtime(cursor.getLong(4));
//                order.set_oofisid(cursor.getString(5));
//                order.set_otype(cursor.getString(6));
//                order.set_ostatus(cursor.getString(7));
//                order.set_odeldtime(cursor.getLong(8));
//
//                ordersList.add(order);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return ordersList;
//    }
//
//
//    public Order getOrder(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_TDORDS, new String[]{KEY_ID,
//                        KEY_ONUMBER, KEY_OADRESS, KEY_OCOUNT, KEY_DTIME, KEY_OOFISID, KEY_OTYPE, KEY_OSTATUS, KEY_ODELDTIME}, KEY_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Order order = new Order(Integer.parseInt(
//                cursor.getString(0)),
//                cursor.getString(1),
//                cursor.getString(2),
//                cursor.getString(3),
//                cursor.getLong(4),
//                cursor.getString(5),
//                cursor.getString(6),
//                cursor.getString(7),
//                cursor.getLong(8)
//
//        );
//
//        cursor.close();
//        db.close();
//
//        return order;
//    }
//
//    public Order getOrderByOfisId(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_TDORDS, new String[]{KEY_ID,
//                        KEY_ONUMBER, KEY_OADRESS, KEY_OCOUNT, KEY_DTIME, KEY_OOFISID, KEY_OTYPE, KEY_OSTATUS, KEY_ODELDTIME}, KEY_OOFISID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Order order = new Order(Integer.parseInt(
//                cursor.getString(0)),
//                cursor.getString(1),
//                cursor.getString(2),
//                cursor.getString(3),
//                cursor.getLong(4),
//                cursor.getString(5),
//                cursor.getString(6),
//                cursor.getString(7),
//                cursor.getLong(8)
//
//        );
//
//        cursor.close();
//        db.close();
//
//        return order;
//    }
//
//
//    public void addAdress(Adress adress) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_CNUMBER, adress.get_cnumber());
//        values.put(KEY_CADRESS, adress.get_cadress());
//        values.put(KEY_CTYPE, adress.get_ctype());
//        //long id = db.insert(...);
//        long id = db.insert(TABLE_ADRESLER, null, values);
//        db.close();
//    }
//
//    public Adress getAdress(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_ADRESLER, new String[]{KEY_ID,
//                        KEY_CNUMBER, KEY_CADRESS, KEY_CTYPE}, KEY_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Adress adress = new Adress(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
//
//        cursor.close();
//        db.close();
//        return adress;
//    }
//
//
//    public void deleteAll() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_ADRESLER, null, null);
//        db.close();
//    }
//
//
//    public void deleteAdress(Adress adress) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_ADRESLER, KEY_ID + " = ?", new String[]{String.valueOf(adress.get_id())});
//        db.close();
//    }
//
//    public int updateAdress(Adress adress) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_CNUMBER, adress.get_cnumber());
//        values.put(KEY_CADRESS, adress.get_cadress());
//        values.put(KEY_CTYPE, adress.get_ctype());
//
//        return db.update(TABLE_ADRESLER, values, KEY_ID + " = ?",
//                new String[]{String.valueOf(adress.get_id())});
//    }
}