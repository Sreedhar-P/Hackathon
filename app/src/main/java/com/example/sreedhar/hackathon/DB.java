package com.example.sreedhar.hackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_TABLE";
    public static final String COL_1="email";
    public static final String COL_2="psw";
    public DB(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+"(email INTEGER,psw TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,email);
        contentValues.put(COL_2,password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean check(String email,String psw)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String s="select email,psw from "+TABLE_NAME;
        Cursor rs=sqLiteDatabase.rawQuery(s,null);
        s="" ;
        String a1=email+" "+psw;
        while(rs.moveToNext())
        {
            s=rs.getString(rs.getColumnIndex(DB.COL_1))+" "+rs.getString(rs.getColumnIndex(DB.COL_2));
            if(s.equals(a1)){
                //Toast.makeText(this,"Success!!",Toast.LENGTH_SHORT).show();
               return true;
            }
        }
        return false;

    }

    public String get(String s1)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String s="select email,psw from "+TABLE_NAME;
        Cursor rs=sqLiteDatabase.rawQuery(s,null);
        s="" ;
        while(rs.moveToNext())
        {
            s=rs.getString(rs.getColumnIndex(DB.COL_1));
            if(s.equals(s1)){
                return rs.getString(rs.getColumnIndex(DB.COL_2));
            }
        }
        return null;
    }
}
