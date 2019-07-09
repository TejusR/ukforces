package com.example.ukforces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME="favorites";
    private static final String col0="id";
    private static final String col1="cat";
    private static final String col2="lat";
    private static final String col3="lon";
    private static final String col4="streetid";
    private static final String col5="streetname";
    private static final String col6="outcat";
    private static final String col7="date";
    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String createTable="CREATE TABLE "+TABLE_NAME+"("+col0+" TEXT, "+col1+" TEXT, "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT, "+col5+" TEXT, "+col6+" TEXT, "+col7+" TEXT)";
      db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
          onCreate(db);
    }
    public boolean addData(String id,String cat,String lat,String lon,String streetid,String streetname,String outcat,String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col0,id);
        cv.put(col1,cat);
        cv.put(col2,lat);
        cv.put(col3,lon);
        cv.put(col4,streetid);
        cv.put(col5,streetname);
        cv.put(col6,outcat);
        cv.put(col7,date);
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String q="SELECT * FROM "+TABLE_NAME;
        Cursor data=db.rawQuery(q,null);
        return data;
    }


}
