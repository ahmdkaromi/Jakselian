package com.example.jakselian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class IndexUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_user);

        toolbar = findViewById(R.id.toolbar_user);
        drawerLayout = findViewById(R.id.drawerLayoutUser);
        navigationView = findViewById(R.id.navUser);

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

        recyclerView = findViewById(R.id.rvUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        List<Kamus> kamus = dbHelper.getListKata();

        if (kamus.size()>0){
            KamusAdapterUser kamusAdapterUser = new KamusAdapterUser(kamus, IndexUser.this);
            recyclerView.setAdapter(kamusAdapterUser);
        }else{
            Toast.makeText(this, "Tidak ada kata didalam database", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.profile_item:
                intent = new Intent(IndexUser.this, Profile.class);
                break;
            case R.id.premium_item:
                intent = new Intent(IndexUser.this, GetPremium.class);
                break;
            case R.id.logout_item:
                intent = new Intent(IndexUser.this, LoginActivity.class);
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