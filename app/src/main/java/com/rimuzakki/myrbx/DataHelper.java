package com.rimuzakki.myrbx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rbx.db";
    private static final int DATABASE_VERSION = 1;
    Context c;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_member = "create table member(id_member integer primary key autoincrement, nama_lengkap text null, alamat text null, no_telp text null, tempat_lahir text null, tanggal_lahir text null);";
        Log.d("Data", "onCreate: " + sql_member);
        db.execSQL(sql_member);
//        Toast.makeText(c, "Table created", Toast.LENGTH_LONG).show();

        sql_member = "insert into member(nama_lengkap, alamat, no_telp, tempat_lahir, tanggal_lahir) VALUES ('Tes Nama', 'Mugas', '08562711435', 'Kudus', '23 Agustus 1997');";
        Log.d("Data", "onCreate: " + sql_member);
        db.execSQL(sql_member);
//        Toast.makeText(c, "Data added", Toast.LENGTH_LONG).show();

        String sql_event = "create table event(id_event integer primary key autoincrement, nama_event text null, jenis_event text null, tanggal_event text null, jam_event text null, tempat_event text null);";
        Log.d("Data", "onCreate: " + sql_event);
        db.execSQL(sql_event);


        sql_event = "insert into event(nama_event, jenis_event, tanggal_event, jam_event, tempat_event) VALUES ('Latihan Rutin', 'Latihan', '08/07/2019', '16:00', 'Stadium');";
        Log.d("Data", "onCreate: " + sql_event);
        db.execSQL(sql_event);


        String sql_inventory = "create table inventory(id_inventory integer primary key autoincrement, nama_inventory text null, jenis_inventory text null, qty_inventory text null);";
        Log.d("Data", "onCreate: " + sql_inventory);
        db.execSQL(sql_inventory);

        sql_inventory = "insert into inventory(nama_inventory , jenis_inventory, qty_inventory) VALUES ('Bola Adidas 1', 'Bola', '1');";
        Log.d("Data", "onCreate: " + sql_inventory);
        db.execSQL(sql_inventory);

        String sql_kas = "create table kas(id_kas integer primary key autoincrement, nama_kas text null, jumlah_kas integer null, jenis_kas text null, tanggal_kas text null);";
        Log.d("Data", "onCreate: " + sql_kas);
        db.execSQL(sql_kas);

        sql_kas = "insert into kas(nama_kas, jumlah_kas, jenis_kas, tanggal_kas) values ('Iuran rutin', '50000', 'pemasukan', '04/06/2019');";
        Log.d("Data", "onCreate: " + sql_kas);
        db.execSQL(sql_kas);

        sql_kas = "insert into kas(nama_kas, jumlah_kas, jenis_kas, tanggal_kas) values ('Sewa rompi', '20000', 'pengeluaran', '05/06/2019');";
        Log.d("Data", "onCreate: " + sql_kas);
        db.execSQL(sql_kas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS member");
        db.execSQL("DROP TABLE IF EXISTS event");
        db.execSQL("DROP TABLE IF EXISTS inventory");
        db.execSQL("DROP TABLE IF EXISTS kas");
        onCreate(db);
    }
}
