package com.example.englishquestion.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.englishquestion.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogDAO {
    private SQLiteDatabase db;
    public CatalogDAO(Context context){
        DBHelper helper= new DBHelper(context);

        db=helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Catalog> get(String sql, String ...selectArgs){
        List<Catalog> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,selectArgs);

        while (cursor.moveToNext()){
            Catalog cat = new Catalog();
            cat.setCatID(cursor.getString(cursor.getColumnIndex("catID")));
            cat.setCatName(cursor.getString(cursor.getColumnIndex("catName")));
            cat.setBackground(cursor.getInt(cursor.getColumnIndex("background")));
            list.add(cat);
        }
        return  list;
    }

    public  List<Catalog> getAll(){
        String sql="SELECT * FROM Catalog";
        return get(sql);
    }

    public long insert(Catalog cat){
        ContentValues values = new ContentValues();
        values.put("catID", cat.getCatID());
        values.put("catName", cat.getCatName());
        values.put("background", cat.getBackground());

        return  db.insert("Catalog", null, values);
    }

    public void getCatData(){

        Catalog cat1 = new Catalog("1","Animal",1);
        insert(cat1);

        Catalog cat2 = new Catalog("2", "Fiction", 2);
        insert(cat2);

        Catalog cat3 = new Catalog("3","Ocean",3);
        insert(cat3);

        Catalog cat4 = new Catalog("4","Place",4);
        insert(cat4);

        Catalog cat5 = new Catalog("5","Home",5);
        insert(cat5);

        Catalog cat6 = new Catalog("6","Plant",6);
        insert(cat6);

        Catalog cat7 = new Catalog("7","Job",7);
        insert(cat7);

        Catalog cat8 = new Catalog("8","Transport",8);
        insert(cat8);

        Catalog cat9 = new Catalog("9", "School stationery", 9);
        insert(cat9);

        Catalog cat10 = new Catalog("10", "Weapon", 10);
        insert(cat10);
    }
}
