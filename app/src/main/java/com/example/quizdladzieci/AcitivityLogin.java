package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuInflater;
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
import com.google.firebase.auth.PhoneMultiFactorAssertion;

public class AcitivityLogin extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private TextView withoutLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_login);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);
        withoutLogin = (TextView) findViewById(R.id.tvWithoutLogin);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
       forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
       invalidateOptionsMenu();


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

                if( validate0()) {

                    validate(Name.getText().toString(), Password.getText().toString());
                }


            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AcitivityLogin.this, RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcitivityLogin.this, PasswordActivity.class));
            }
        });

        withoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AcitivityLogin.this, FirstScreenActivity.class));




            }
        });

    }
    private Boolean validate0(){

        Boolean result = false;

        String name = Name.getText().toString();
        String password = Password.getText().toString();


        if(name.isEmpty() || password.isEmpty()  ){
            Toast.makeText(this, "Uzupełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Sprawdzam login i hasło..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkEmailVerification();
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

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(AcitivityLogin.this, FirstScreenActivity.class));

        /*if(emailflag){
           finish();
           startActivity(new Intent(AcitivityLogin.this, FirstScreenActivity.class));
      }else{
           Toast.makeText(this, "Sprawdź swoją skrzynkę email", Toast.LENGTH_SHORT).show();
           firebaseAuth.signOut();
        }*/
    }


}
