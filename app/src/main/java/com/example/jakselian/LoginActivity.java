package com.example.jakselian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView belumregist;
    DBHelperUser MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        login = (Button) findViewById(R.id.button_login);
        belumregist = (TextView) findViewById(R.id.belumregist);
        MyDB = new DBHelperUser(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Pastikan semua input sudah terisi", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = MyDB.checkusernamepassword(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Berhasil masuk", Toast.LENGTH_SHORT).show();
                        Boolean checkadmin = MyDB.checkstatusadmin(user);
                        Boolean checkvip = MyDB.checkstatusvip(user);
                        if(checkadmin==true){
                            Intent admin = new Intent(LoginActivity.this, IndexAdmin.class);
                            startActivity(admin);
                            finish();
                        }else if (checkvip==true){
                            Intent vip = new Intent(LoginActivity.this, IndexVIP.class);
                            startActivity(vip);
                            finish();
                        }else{
                            Intent intent = new Intent(LoginActivity.this, IndexUser.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Akun tidak dikenali", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        belumregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent belumregist = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(belumregist);
            }
        });

    }
}