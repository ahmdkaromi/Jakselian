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

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText username, email, password;
    Button edit;
    DBHelperUser dbHelperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar_profile);
        drawerLayout = findViewById(R.id.drawerLayoutProfile);
        navigationView = findViewById(R.id.navProfile);

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

        username = findViewById(R.id.editUser);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        edit = findViewById(R.id.button_profile);
        setDataToPage();

        dbHelperUser = new DBHelperUser(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase myDB = dbHelperUser.getWritableDatabase();
                myDB.execSQL("update User set username='"+username.getText().toString() +"', email='"+email.getText().toString() +"', password='"+password.getText().toString() +"' where id='1'");
                Toast.makeText(Profile.this, "Akun telah diubah", Toast.LENGTH_SHORT).show();

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
            case R.id.kamus_item:
                intent = new Intent(Profile.this, IndexUser.class);
                break;
            case R.id.premium_item:
                intent = new Intent(Profile.this, GetPremium.class);
                break;
            case R.id.logout_item:
                intent = new Intent(Profile.this, LoginActivity.class);
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