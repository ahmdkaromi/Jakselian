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
                            ProfileData profileData = MyDB.getData(user);
                            Intent profile1 = new Intent(getApplicationContext(), ProfileAdmin.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("id", profileData.id);
                            bundle1.putString("username", profileData.username);
                            bundle1.putString("email", profileData.email);
                            bundle1.putString("password", profileData.password);
                            profile1.putExtras(bundle1);
                            startActivity(profile1);

                            Intent admin = new Intent(LoginActivity.this, IndexAdmin.class);
                            startActivity(admin);
                            finish();
                        }else if (checkvip==true){
                            ProfileData profileData = MyDB.getData(user);
                            Intent profile2 = new Intent(getApplicationContext(), ProfileVIP.class);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("id", profileData.id);
                            bundle2.putString("username", profileData.username);
                            bundle2.putString("email", profileData.email);
                            bundle2.putString("password", profileData.password);
                            profile2.putExtras(bundle2);
                            startActivity(profile2);

                            Intent vip = new Intent(LoginActivity.this, IndexVIP.class);
                            startActivity(vip);
                            finish();
                        }else{
                            ProfileData profileData = MyDB.getData(user);
                            Intent profile3 = new Intent(getApplicationContext(), Profile.class);
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("id", profileData.id);
                            bundle3.putString("username", profileData.username);
                            bundle3.putString("email", profileData.email);
                            bundle3.putString("password", profileData.password);
                            profile3.putExtras(bundle3);
                            startActivity(profile3);

                            ProfileData profileData2 = MyDB.getData(user);
                            Intent profile4 = new Intent(getApplicationContext(), GetPremium.class);
                            Bundle bundle4 = new Bundle();
                            bundle4.putString("id", profileData2.id);
                            bundle4.putString("username", profileData2.username);
                            bundle4.putString("status", profileData2.status);
                            profile4.putExtras(bundle4);
                            startActivity(profile4);

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