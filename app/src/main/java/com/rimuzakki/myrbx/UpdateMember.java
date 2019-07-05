package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMember extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Update, btn_Back;
    EditText et_NamaLengkap, et_Alamat, et_Notelp, et_TempatLahir, et_TanggalLahir;
    TextView et_IdMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        dbHelper = new DataHelper(this);
        et_IdMember = (TextView) findViewById(R.id.etIdMember);
        et_NamaLengkap = (EditText)findViewById(R.id.etNamaLengkap);
        et_Alamat = (EditText)findViewById(R.id.etAlamat);
        et_Notelp = (EditText)findViewById(R.id.etNoTelp);
        et_TempatLahir = (EditText)findViewById(R.id.etTempatLahir);
        et_TanggalLahir = (EditText)findViewById(R.id.etTanggalLahir);
        btn_Update = (Button)findViewById(R.id.btnUpdateMember);
        btn_Back = (Button)findViewById(R.id.btnBackMember);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM member WHERE id_member = '" +
                getIntent().getStringExtra("detail_member") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            et_IdMember.setText(cursor.getString(0).toString());
            et_NamaLengkap.setText(cursor.getString(1).toString());
            et_Alamat.setText(cursor.getString(2).toString());
            et_Notelp.setText(cursor.getString(3).toString());
            et_TempatLahir.setText(cursor.getString(4).toString());
            et_TanggalLahir.setText(cursor.getString(5).toString());
        }

        btn_Update = (Button)findViewById(R.id.btnUpdateMember);
        btn_Back = (Button)findViewById(R.id.btnBackMember);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE member SET nama_lengkap='" +
                        et_NamaLengkap.getText().toString() + "', alamat='" +
                        et_Alamat.getText().toString() + "', no_telp='" +
                        et_Notelp.getText().toString() + "', tempat_lahir='" +
                        et_TempatLahir.getText().toString() + "', tanggal_lahir='" +
                        et_TanggalLahir.getText().toString() + "' WHERE id_member='" +
                        et_IdMember.getText().toString() +"'");
                Toast.makeText(getApplicationContext(), "Berhasil memperbarui data", Toast.LENGTH_LONG).show();
                ListMember.ma.RefreshList();
                finish();
            }
        });
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
