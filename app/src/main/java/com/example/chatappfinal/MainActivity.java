package com.example.chatappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        editText= findViewById(R.id.editTextPhone);


       FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        if (user1 == null) {
//If not signed in
            
        } else {
//if signed in


            Intent i = new Intent(MainActivity.this, Home_page.class);
            startActivity(i);
            finish();
       }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().trim().isEmpty()){

                    Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ editText.getText().toString(),60, TimeUnit.SECONDS,
                        MainActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Intent intent=new Intent(MainActivity.this,otp_Page.class);
                                intent.putExtra("phonenumber",editText.getText().toString());

                                intent.putExtra("verificationId",VerificationId);
                                startActivity(intent);

                            }
                        }

                );


            }
        });
    }
}