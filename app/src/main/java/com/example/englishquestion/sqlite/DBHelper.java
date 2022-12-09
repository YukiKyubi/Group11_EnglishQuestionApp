package com.example.englishquestion.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="QUESTION";

    public static final int DB_VERSION=1;

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE Catalog(catID text primary key, catName text not null,"+
                "background integer not null ) ";

        String sqlQuestion="CREATE TABLE Question(questIndex text primary key, " +
                "questTitle text not null,"+
                "answer1 text not null,"+
                "answer2 text not null,"+
                "answer3 text not null, " +
                "answer4 text not null, " +
                "correctAnswer integer not null," +
                "atCatID text not null," +
                "questImg integer not null)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlQuestion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql= "DROP TABLE IF EXISTS Catalog";
        sqLiteDatabase.execSQL(sql);

        String sqlQuestion= "DROP TABLE IF EXISTS Question";
        sqLiteDatabase.execSQL(sqlQuestion);

        onCreate(sqLiteDatabase);
    }
}
