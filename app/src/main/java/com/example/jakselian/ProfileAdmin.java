package com.example.jakselian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ProfileAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText username, email, password;
    Button edit;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DBHelperUser dbHelperUser;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        toolbar = findViewById(R.id.toolbar_profile_admin);
        drawerLayout = findViewById(R.id.drawerLayoutProfileAdmin);
        navigationView = findViewById(R.id.navProfileAdmin);

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

        username = findViewById(R.id.editUserAdmin);
        email = findViewById(R.id.editEmailAdmin);
        password = findViewById(R.id.editPasswordAdmin);
        edit = findViewById(R.id.button_profile_admin);
        id = findViewById(R.id.id_profile_admin);
        setDataToPage();

        dbHelperUser = new DBHelperUser(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**SQLiteDatabase MyDB = dbHelperUser.getReadableDatabase();
                cursor = MyDB.rawQuery("SELECT * FROM User WHERE username = '" +
                        getIntent().getStringExtra("id") + "'",null);
                cursor.moveToFirst();*/

                SQLiteDatabase myDB = dbHelperUser.getWritableDatabase();
                myDB.execSQL("update User set username='"+username.getText().toString() +"', email='"+email.getText().toString() +"', password='"+password.getText().toString() +"' where id='2'");
                Toast.makeText(ProfileAdmin.this, "Akun telah diubah", Toast.LENGTH_SHORT).show();

                /**String stringUser = username.getText().toString();
                String stringEmail = email.getText().toString();
                String stringPass = password.getText().toString();

                MyDB.updateUser(new User(user.getId(),stringUser,stringEmail,stringPass));
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());*/
            }
        });
    }

    public void setDataToPage(){

        id.setText(getIntent().getExtras().getString("id"));
        username.setText(getIntent().getExtras().getString("username"));
        email.setText(getIntent().getExtras().getString("email"));
        password.setText(getIntent().getExtras().getString("password"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.kamus_admin:
                intent = new Intent(ProfileAdmin.this, IndexAdmin.class);
                break;
            case R.id.tambah_admin:
                intent = new Intent(ProfileAdmin.this, AddWordsAdmin.class);
                break;
            case R.id.logout_admin:
                intent = new Intent(ProfileAdmin.this, LoginActivity.class);
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