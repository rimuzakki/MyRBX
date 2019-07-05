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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListMember extends AppCompatActivity {
    Button btn_MemberBaru;
    String[] daftar, id_member;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbHelper;
    public static ListMember ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_member);

        btn_MemberBaru = (Button)findViewById(R.id.btnMemberBaru);

        btn_MemberBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent move_memberbaru = new Intent(ListMember.this, MemberActivity.class);
                startActivity(move_memberbaru);
            }
        });

        ma = this;
        dbHelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM member", null);
        daftar = new String[cursor.getCount()];
        id_member = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            id_member[cc] = cursor.getString(0).toString();
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = id_member[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Member", "Update Member", "Hapus Member"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListMember.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailMember.class);
                                i.putExtra("detail_member", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateMember.class);
                                in.putExtra("detail_member", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from member where id_member = '" + selection + "'");
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
