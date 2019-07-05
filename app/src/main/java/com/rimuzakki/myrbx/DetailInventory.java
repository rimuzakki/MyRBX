package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailInventory extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Back;
    TextView tv_IdInventory, tv_NamaBarang, tv_JenisBarang, tv_Qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inventory);

        dbHelper = new DataHelper(this);
        tv_IdInventory = (TextView)findViewById(R.id.tvIdInventory);
        tv_NamaBarang = (TextView)findViewById(R.id.tvNamaBarang);
        tv_JenisBarang = (TextView)findViewById(R.id.tvJenisBarang);
        tv_Qty = (TextView)findViewById(R.id.tvQty);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE id_inventory = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdInventory.setText(cursor.getString(0).toString());
            tv_NamaBarang.setText(cursor.getString(1).toString());
            tv_JenisBarang.setText(cursor.getString(2).toString());
            tv_Qty.setText(cursor.getString(3).toString());
        }

        btn_Back = (Button)findViewById(R.id.btnBackDetailInventory);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
