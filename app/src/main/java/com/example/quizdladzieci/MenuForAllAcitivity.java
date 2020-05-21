package com.example.quizdladzieci;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MenuForAllAcitivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        firebaseAuth = FirebaseAuth.getInstance();
        return true;
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MenuForAllAcitivity.this, AcitivityLogin.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout:
                Logout();
                break;
            case R.id.profileMenu:
                startActivity(new Intent(MenuForAllAcitivity.this, UserProfileActivity.class));
                break;
            case R.id.profileStats:
                startActivity(new Intent(MenuForAllAcitivity.this, Statistics.class));
                break;

        }
        return true;
    }
}
