package com.example.chatappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Home_app extends AppCompatActivity {
    EditText editText,editText2;
    Button button;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        editText2=findViewById(R.id.editTextPhone7);
        editText=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.button);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=editText.getText().toString();
                String number=editText2.getText().toString();
                String uId=auth.getUid();
                user user=new user(uId,name,number);
                firebaseDatabase.getReference()
                        .child("user")
                        .child(auth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Home_app.this, "Registered successful ", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Home_app.this,Home_page.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}