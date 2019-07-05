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

public class ListInventory extends AppCompatActivity {
    Button btn_InventoryBaru;
    String[] daftar, id;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbHelper;
    public static ListInventory ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inventory);

        btn_InventoryBaru = (Button)findViewById(R.id.btnInventoryBaru);

        btn_InventoryBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent move_inventorybaru = new Intent(ListInventory.this, InventoryActivity.class);
                startActivity(move_inventorybaru);
            }
        });

        ma = this;
        dbHelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory", null);
        daftar = new String[cursor.getCount()];
        id = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            id[cc] = cursor.getString(0).toString();
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = id[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Inventory", "Update Inventory", "Hapus Inventory"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListInventory.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailInventory.class);
                                i.putExtra("detail", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateInventory.class);
                                in.putExtra("detail", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from inventory where id_inventory = '" + selection + "'");
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
}
