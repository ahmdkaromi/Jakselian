package com.example.jakselian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AddWordsAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText kata, arti, kalimat;
    Button add;
    TextView judul;
    DBHelper DB;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words_admin);

        toolbar = findViewById(R.id.toolbar_admin_add);
        drawerLayout = findViewById(R.id.addWordsLayout);
        navigationView = findViewById(R.id.navAddAdmin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        kata = findViewById(R.id.kata_add);
        arti = findViewById(R.id.arti_add);
        kalimat = findViewById(R.id.kalimat_add);
        add = findViewById(R.id.button_add);
        judul = findViewById(R.id.judul_add);
        DB = new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringKata = kata.getText().toString();
                String stringArti = arti.getText().toString();
                String stringKalimat = kalimat.getText().toString();

                if (stringKata.length() <=0 || stringArti.length() <=0 || stringKalimat.length() <= 0){
                    Toast.makeText(AddWordsAdmin.this, "Masukkan semua data", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkaddKamus = DB.addKamus(stringKata, stringArti, stringKalimat);
                    Toast.makeText(AddWordsAdmin.this, "Kata Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.profile_admin:
                intent = new Intent(AddWordsAdmin.this, Profile.class);
                break;
            case R.id.kamus_admin:
                intent = new Intent(AddWordsAdmin.this, IndexAdmin.class);
                break;
            case R.id.logout_admin:
                intent = new Intent(AddWordsAdmin.this, LoginActivity.class);
                finish();
                break;
            default:
                return false;
        }
        startActivity(intent);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}