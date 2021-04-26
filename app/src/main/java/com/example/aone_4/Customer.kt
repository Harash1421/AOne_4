package com.example.aone_4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Customer(context: Context) : SQLiteOpenHelper(context,"customer.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table customer (Name text, Phone text)")
        db?.execSQL("insert into customer values('John', '4512457474')")
        db?.execSQL("insert into customer values('Gabriel', '3258457451')")
        db?.execSQL("insert into customer values('Jason', '7542315479')")
        db?.execSQL("insert into customer values('Raphael', '6457845214')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

}