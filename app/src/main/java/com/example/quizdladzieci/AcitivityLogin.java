package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AcitivityLogin extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_login);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);

        Info.setText("Liczba pozostałych prób: 5");

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null ) {
            finish();
            startActivity(new Intent(AcitivityLogin.this, FirstScreenActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();

                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AcitivityLogin.this, RegistrationActivity.class));
            }
        });



    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Sprawdzam login i hasło..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(AcitivityLogin.this, "Logowanie poprawne! ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AcitivityLogin.this, FirstScreenActivity.class));
                } else {
                    Toast.makeText(AcitivityLogin.this, "Niepoprawny email lub hasło, spróbuj ponownie", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Liczba pozostałych prób: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0 )
                    {
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }


}
