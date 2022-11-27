package com.example.jakselian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class GetPremium extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button freemium;
    TextView id, username, status;
    DBHelperUser dbHelperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_premium);

        toolbar = findViewById(R.id.toolbar_premium);
        drawerLayout = findViewById(R.id.drawerLayoutPremium);
        navigationView = findViewById(R.id.navPremium);

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

        freemium = findViewById(R.id.button_freemium);
        id = findViewById(R.id.id_freemium);
        username = findViewById(R.id.username_freemium);
        status = findViewById(R.id.status_freemium);
        setDataToPage();

        dbHelperUser = new DBHelperUser(this);

        freemium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase myDB = dbHelperUser.getWritableDatabase();
                myDB.execSQL("update User set status='vip' where username='"+username.getText().toString()+"'");
                Toast.makeText(GetPremium.this, "Akun telah diupgrade, anda harus melakukan login kembali", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GetPremium.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void setDataToPage() {

        id.setText(getIntent().getExtras().getString("id"));
        username.setText(getIntent().getExtras().getString("username"));
        status.setText(getIntent().getExtras().getString("status"));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.kamus_item:
                intent = new Intent(GetPremium.this, IndexUser.class);
                break;
            case R.id.profile_item:
                intent = new Intent(GetPremium.this, Profile.class);
                break;
            case R.id.logout_item:
                intent = new Intent(GetPremium.this, LoginActivity.class);
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