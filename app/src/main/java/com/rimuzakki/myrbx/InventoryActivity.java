package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InventoryActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Simpan, btn_Back;
    EditText et_NamaBarang, et_JenisBarang, et_Qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        dbHelper = new DataHelper(this);
        et_NamaBarang = (EditText)findViewById(R.id.etNamaBarang);
        et_JenisBarang = (EditText)findViewById(R.id.etJenisBarang);
        et_Qty = (EditText)findViewById(R.id.etQty);
        btn_Simpan = (Button)findViewById(R.id.btnSimpanInventory);
        btn_Back = (Button)findViewById(R.id.btnBackInventory);

        btn_Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO inventory(nama_inventory, jenis_inventory, qty_inventory) VALUES('" +
                        et_NamaBarang.getText().toString() + "','" +
                        et_JenisBarang.getText().toString() + "','" +
                        et_Qty.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Inventory ditambahkan", Toast.LENGTH_LONG).show();
                ListInventory.ma.RefreshList();
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
