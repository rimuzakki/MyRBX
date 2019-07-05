package com.rimuzakki.myrbx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMoveMember;
    private Button btnMoveSchedule;
    private Button btnMoveInventory;
    private Button btnMoveKas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveMember = (Button)findViewById(R.id.btn_move_member);
        btnMoveMember.setOnClickListener(this);

        btnMoveSchedule = (Button)findViewById(R.id.btn_move_schedule);
        btnMoveSchedule.setOnClickListener(this);

        btnMoveInventory = (Button)findViewById(R.id.btn_move_inventory);
        btnMoveInventory.setOnClickListener(this);

        btnMoveKas = (Button)findViewById(R.id.btn_move_kas);
        btnMoveKas.setOnClickListener(this);
    }

    // code perpindahan activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // pindah activity, intent explicit
            case R.id.btn_move_member:
                Intent moveMember = new Intent(MainActivity.this, ListMember.class);
                startActivity(moveMember);
                break;

            case R.id.btn_move_schedule:
                Intent moveSchedule = new Intent(MainActivity.this, ListSchedule.class);
                startActivity(moveSchedule);
                break;

            case R.id.btn_move_inventory:
                Intent moveInventory = new Intent(MainActivity.this, ListInventory.class);
                startActivity(moveInventory);
                break;

            case R.id.btn_move_kas:
                Intent moveKas = new Intent(MainActivity.this, ListKas.class);
                startActivity(moveKas);
                break;

        }
    }
}
