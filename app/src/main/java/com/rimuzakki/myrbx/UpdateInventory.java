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

public class UpdateInventory extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn_Update, btn_Back;
    EditText et_NamaBarang, et_JenisBarang, et_Qty;
    TextView tv_IdInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);

        dbHelper = new DataHelper(this);
        tv_IdInventory = (TextView)findViewById(R.id.tvIdInventory);
        et_NamaBarang = (EditText)findViewById(R.id.etNamaBarang);
        et_JenisBarang = (EditText)findViewById(R.id.etJenisBarang);
        et_Qty = (EditText)findViewById(R.id.etQty);
        btn_Update = (Button)findViewById(R.id.btnUpdateInventory);
        btn_Back = (Button)findViewById(R.id.btnBackUpdateInventory);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE id_inventory = '" +
                getIntent().getStringExtra("detail") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tv_IdInventory.setText(cursor.getString(0).toString());
            et_NamaBarang.setText(cursor.getString(1).toString());
            et_JenisBarang.setText(cursor.getString(2).toString());
            et_Qty.setText(cursor.getString(3).toString());
        }

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE inventory SET nama_inventory='" +
                        et_NamaBarang.getText().toString() + "', jenis_inventory='" +
                        et_JenisBarang.getText().toString() + "', qty_inventory='" +
                        et_Qty.getText().toString() + "' WHERE id_inventory='" +
                        tv_IdInventory.getText().toString() +"'");
                Toast.makeText(getApplicationContext(), "Berhasil memperbarui data", Toast.LENGTH_LONG).show();
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
