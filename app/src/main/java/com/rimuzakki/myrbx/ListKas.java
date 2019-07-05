package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class ListKas extends AppCompatActivity {
    String[] daftar, id_kas, pemasukan, pengeluaran;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor, cursorPemasukan, cursorPengeluaran;
    DataHelper dbHelper;
    public static ListKas ma;
    Button btn_KasBaru;
    TextView tv_Saldo, tv_Pemasukan, tv_Pengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kas);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        ma = this;
        dbHelper = new DataHelper(this);
        RefreshList();

        tv_Saldo = (TextView)findViewById(R.id.tvSaldo);
        tv_Pemasukan = (TextView)findViewById(R.id.tvPemasukan);
        tv_Pengeluaran = (TextView)findViewById(R.id.tvPengeluaran);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursorPemasukan = db.rawQuery("SELECT SUM(jumlah_kas) AS 'total_pemasukan' FROM kas WHERE jenis_kas = 'pemasukan'", null);
        pemasukan = new String[cursorPemasukan.getCount()];
        cursorPemasukan.moveToFirst();

        if (cursorPemasukan.getCount() > 0) {
            cursorPemasukan.moveToPosition(0);
            tv_Pemasukan.setText(cursorPemasukan.getString(0).toString());
        }

        cursorPengeluaran = db.rawQuery("SELECT SUM(jumlah_kas) AS 'total_pengeluaran' FROM kas WHERE jenis_kas = 'pengeluaran'", null);
        pengeluaran = new String[cursorPengeluaran.getCount()];
        cursorPengeluaran.moveToFirst();

        if (cursorPengeluaran.getCount() > 0) {
            cursorPengeluaran.moveToPosition(0);
            tv_Pengeluaran.setText(cursorPengeluaran.getString(0).toString());
        }

        double nilPemasukan, nilPengeluaran, saldo;
        nilPemasukan = Double.valueOf(tv_Pemasukan.getText().toString().trim());
        nilPengeluaran = Double.valueOf(tv_Pengeluaran.getText().toString().trim());

        saldo = nilPemasukan - nilPengeluaran;
        String saldo1 = String.valueOf(saldo);

        tv_Saldo.setText(formatRupiah.format(saldo));


        btn_KasBaru = (Button)findViewById(R.id.btnKasBaru);

        btn_KasBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent move_kasbaru = new Intent(ListKas.this, KasActivity.class);
                startActivity(move_kasbaru);
            }
        });

    }

    public void RefreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kas", null);
        daftar = new String[cursor.getCount()];
        id_kas = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            id_kas[cc] = cursor.getString(0).toString();
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = id_kas[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Kas", "Update Kas", "Hapus Kas"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListKas.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailKas.class);
                                i.putExtra("detail", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateKas.class);
                                in.putExtra("detail", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from kas where id_kas = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here

    }
}
