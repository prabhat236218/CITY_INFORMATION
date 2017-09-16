package com.example.prabhat.city_information;

/**
 * Created by prabhat on 27/8/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBController extends SQLiteOpenHelper {
    private static final String databasename = "UserManager"; // Dtabasename

    private static final int versioncode = 1; //versioncode of the database

    private static final String tablename = "user";  // tablename

    private static final String user_name = "user_name";  // column name
   // private static final String mobile= "mobile";
    private static final String  email_id = "email_id";
    private static final String password_id = "password"; // column name
  ///  private static final String dob = "dob";


    public DBController(Context context) {
        super(context, databasename, null, versioncode);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + user_name+ " text, " +email_id+ " text, " + password_id +" text)";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + tablename;
        database.execSQL(query);
        onCreate(database);
    }

    public boolean addUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(user_name,user.getName());

        value.put(email_id,user.getEmail());
      //  value.put(mobile,user.getMobile());
        value.put(password_id,user.getPassword());
      ///  value.put(dob,user.getDob());
        db.insert(tablename,null,value);

        db.close();
        return true;
    }
    public boolean  checkUser(String email)
    {

        String[] colum=new String[]{user_name};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=email_id+" = ?";
        String[] selectionArgs={email};

        Cursor cursor = db.query(tablename, //Table to query
                colum,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount>0)return true;
        return false;
    }

    public boolean  checkUser(String email,String password)
    {

        String[] colum=new String[]{user_name};

        SQLiteDatabase db=this.getReadableDatabase();

        String selection=email_id+" = ?"+ " AND "+password_id+" = ? " ;

        String[] selectionArgs={email,password};

        Cursor cursor = db.query(tablename, //Table to query
                colum,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cnt = cursor.getCount();

        cursor.close();

        db.close();

        if(cnt>=1)return true;

        return false;
    }




}