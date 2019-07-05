package com.rimuzakki.myrbx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Simpan, btn_Back;
    EditText et_IdMember, et_NamaLengkap, et_Alamat, et_Notelp, et_TanggalLahir, et_TempatLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        dbHelper = new DataHelper(this);
        et_IdMember = (EditText)findViewById(R.id.etIdMember);
        et_NamaLengkap = (EditText)findViewById(R.id.etNamaLengkap);
        et_Alamat = (EditText)findViewById(R.id.etAlamat);
        et_Notelp = (EditText)findViewById(R.id.etNoTelp);
        et_TempatLahir = (EditText)findViewById(R.id.etTempatLahir);
        et_TanggalLahir = (EditText)findViewById(R.id.etTanggalLahir);
        btn_Simpan = (Button)findViewById(R.id.btnSimpanMember);
        btn_Back = (Button)findViewById(R.id.btnBackMember);

        btn_Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into member(nama_lengkap, alamat, no_telp, tempat_lahir, tanggal_lahir) VALUES('" +

                        et_NamaLengkap.getText().toString() + "','" +
                        et_Alamat.getText().toString() + "','" +
                        et_Notelp.getText().toString() + "','" +
                        et_TempatLahir.getText().toString() + "','" +
                        et_TanggalLahir.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil menambah member", Toast.LENGTH_LONG).show();
                ListMember.ma.RefreshList();
                finish();
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
