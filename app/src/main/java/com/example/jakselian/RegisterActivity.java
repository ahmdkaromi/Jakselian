package com.example.jakselian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, confirm;
    Button register;
    TextView sudahregist;
    DBHelperUser MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username_regist);
        email = (EditText) findViewById(R.id.email_regist);
        password = (EditText) findViewById(R.id.password_regist);
        confirm = (EditText) findViewById(R.id.confirm_regist);
        register = (Button) findViewById(R.id.button_register);
        sudahregist = (TextView) findViewById(R.id.sudahregist);
        MyDB = new DBHelperUser(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userTXT = username.getText().toString();
                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();
                String confirmTXT = confirm.getText().toString();

                if (userTXT.equals("") || emailTXT.equals("") || passwordTXT.equals("") || confirmTXT.equals(""))
                    Toast.makeText(RegisterActivity.this, "Pastikan semua input sudah terisi", Toast.LENGTH_SHORT).show();
                else{
                    if (passwordTXT.equals(confirmTXT)){
                        Boolean checkuser = MyDB.checkusername(userTXT);
                        if(checkuser==false){
                            Boolean insert = MyDB.addUser(userTXT, emailTXT, passwordTXT);
                            if (insert==true){
                                Toast.makeText(RegisterActivity.this, "Akun telah berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(RegisterActivity.this, "User sudah terdaftar! Silahkan login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password tidak cocok", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sudahregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sudahregist = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(sudahregist);
            }
        });
    }
}