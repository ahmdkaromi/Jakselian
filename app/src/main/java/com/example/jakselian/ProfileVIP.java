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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ProfileVIP extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText username, email, password;
    Button edit;
    DBHelperUser dbHelperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_vip);

        toolbar = findViewById(R.id.toolbar_profile_vip);
        drawerLayout = findViewById(R.id.drawerLayoutProfileVIP);
        navigationView = findViewById(R.id.navProfileVIP);

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

        username = findViewById(R.id.editUserVIP);
        email = findViewById(R.id.editEmailVIP);
        password = findViewById(R.id.editPasswordVIP);
        edit = findViewById(R.id.button_profile_vip);
        setDataToPage();

        dbHelperUser = new DBHelperUser(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase myDB = dbHelperUser.getWritableDatabase();
                myDB.execSQL("update User set username='"+username.getText().toString() +"', email='"+email.getText().toString() +"', password='"+password.getText().toString() +"' where id='3'");
                Toast.makeText(ProfileVIP.this, "Akun telah diubah", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setDataToPage(){

        username.setText(getIntent().getExtras().getString("username"));
        email.setText(getIntent().getExtras().getString("email"));
        password.setText(getIntent().getExtras().getString("password"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.kamus_item_vip:
                intent = new Intent(ProfileVIP.this, IndexVIP.class);
                break;
            case R.id.logout_item_vip:
                intent = new Intent(ProfileVIP.this, LoginActivity.class);
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