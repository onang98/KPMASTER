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

public class Login_Prangkat_desa extends AppCompatActivity {

    EditText et_NIP, pas_PD;
    Button btn_loginpd;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__prangkat_desa);
        getSupportActionBar().setTitle("Login_Prangkat_desa");

        et_NIP = findViewById(R.id.et_NIP);
        pas_PD = findViewById(R.id.pas_PD);
        btn_loginpd = findViewById(R.id.btn_loginpd);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_loginpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NIP = et_NIP.getText().toString().trim();
                String password = pas_PD.getText().toString().trim();

                if (TextUtils.isEmpty(NIP)){
                    Toast.makeText(Login_Prangkat_desa.this, "silahkan masukan NIK", Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(NIP)) {
                    Toast.makeText(Login_Prangkat_desa.this, "silahkan masukan password", Toast.LENGTH_SHORT);
                    return;
                }

                if (password.length()<6){

                    Toast.makeText(Login_Prangkat_desa.this, "password terlalu pendek", Toast.LENGTH_SHORT);
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(NIP, password)
                        .addOnCompleteListener(Login_Prangkat_desa.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),Perangkat_Desa_View.class));

                                } else {
                                    Toast.makeText(Login_Prangkat_desa.this, "login gagal atau user tidak tersedia", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
    }
}
