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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null ) {
            MenuItem menuItem = menu.findItem(R.id.logout);
            MenuItem menuItem2 = menu.findItem(R.id.profileMenu);
            MenuItem menuItem3 = menu.findItem(R.id.profileStats);
            menuItem.setVisible(false);//gone
            menuItem2.setVisible(false);
            menuItem3.setVisible(false);


        }
        return true;
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
