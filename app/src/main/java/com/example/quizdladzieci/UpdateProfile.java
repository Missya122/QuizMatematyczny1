package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class UpdateProfile extends MenuForAllAcitivity {

    private EditText newUserName, newUserEmail, newUserAge;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Toolbar toolbar;

    private ImageView updateProfilePic;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    /*private StorageReference storageReference;
    private FirebaseStorage firebaseStorage; */

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                updateProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);


        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
        newUserName = findViewById(R.id.etNameUpdate);
        newUserEmail = findViewById(R.id.etEmailUpdate);
        newUserAge = findViewById(R.id.etAgeUpdate);
        save = findViewById(R.id.btnSave);
        updateProfilePic = findViewById(R.id.ivProfileUpdate);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserEmail.setText(userProfile.getUserEmail());
                newUserAge.setText(userProfile.getUserAge());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if( validate0() )
                {
                    String name = newUserName.getText().toString();
                    String age = newUserAge.getText().toString();
                    String email = newUserEmail.getText().toString();

                    UserProfile userProfile = new UserProfile(age,email,name);

                    databaseReference.setValue(userProfile);
                    finish();
                }


            }
        });



        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserAge.setText(userProfile.getUserAge());
                newUserEmail.setText(userProfile.getUserEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        final StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(updateProfilePic);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newUserName.getText().toString();
                String age = newUserAge.getText().toString();
                String email = newUserEmail.getText().toString();

                UserProfile userProfile = new UserProfile(age, email, name);

                databaseReference.setValue(userProfile);

                StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
                UploadTask uploadTask = imageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this, "Upload failed!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(UpdateProfile.this, "Upload successful!", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });

        updateProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        }); */
    }
    private Boolean validate0(){

        Boolean result = false;

        String name = newUserName.getText().toString();
        String email = newUserEmail.getText().toString();
        String age = newUserAge.getText().toString();


        if(name.isEmpty() || email.isEmpty() || age.isEmpty()  ){
            Toast.makeText(this, "Uzupełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

}
