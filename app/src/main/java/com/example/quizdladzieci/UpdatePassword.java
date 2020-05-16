package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePassword extends MenuForAllAcitivity {

    private Button update;
    private EditText newPassword;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        update = findViewById(R.id.btnUpdatePassword);
        newPassword = findViewById(R.id.etNewPassword);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                if( validate0())
                {
                    String userPasswordNew = newPassword.getText().toString();
                    firebaseUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UpdatePassword.this, "Hasło zmienione", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(UpdatePassword.this, "Wystąpił błąd", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });


    }

    private Boolean validate0(){

        Boolean result = false;

        String password = newPassword.getText().toString();

        if(password.isEmpty()  ){
            Toast.makeText(this, "Wpisz nowe hasło!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }
}
