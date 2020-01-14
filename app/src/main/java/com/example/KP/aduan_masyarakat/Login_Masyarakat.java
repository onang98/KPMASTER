package com.example.KP.aduan_masyarakat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Masyarakat extends AppCompatActivity {


    EditText et_NIP, pas_Mas;
    Button btn_masyarakat;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__masyarakat);
        getSupportActionBar().setTitle("Login_Masyarakat");

        et_NIP = findViewById(R.id.et_NIP);
        pas_Mas = findViewById(R.id.pas_Mas);
        btn_masyarakat = findViewById(R.id.btn_masyarakat);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_masyarakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NIP = et_NIP.getText().toString().trim();
                String password = pas_Mas.getText().toString().trim();

                if (TextUtils.isEmpty(NIP)){
                    Toast.makeText(Login_Masyarakat.this, "silahkan masukan NIP", Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(NIP)) {
                    Toast.makeText(Login_Masyarakat.this, "silahkan masukan password", Toast.LENGTH_SHORT);
                    return;
                }

                if (password.length()<6){

                    Toast.makeText(Login_Masyarakat.this, "password terlalu pendek", Toast.LENGTH_SHORT);
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(NIP, password)
                        .addOnCompleteListener(Login_Masyarakat.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),Dashboard_User.class));

                                } else {
                                    Toast.makeText(Login_Masyarakat.this, "login gagal atau user tidak tersedia", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
    }
}


