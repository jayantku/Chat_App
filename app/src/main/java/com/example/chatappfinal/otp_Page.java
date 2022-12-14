package com.example.chatappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.concurrent.TimeUnit;

public class otp_Page extends AppCompatActivity {
    Button button1,button2,button3;
    TextView textView;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    String verificationId;
 FirebaseAuth auth;
 FirebaseDatabase firebaseDatabase;
 FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_page);
        editText1=findViewById(R.id.editTextPhone1);
        editText2=findViewById(R.id.editTextPhone2);
        editText3=findViewById(R.id.editTextPhone3);
        editText4=findViewById(R.id.editTextPhone4);
        editText5=findViewById(R.id.editTextPhone5);
        editText6=findViewById(R.id.editTextPhone6);
        textView=findViewById(R.id.textView7);
        button1=findViewById(R.id.button5);
        button2=findViewById(R.id.button);
        button3=findViewById(R.id.button4);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        verificationId=getIntent().getStringExtra("verificationId");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().trim().isEmpty()
                || editText2.getText().toString().trim().isEmpty()
                ||editText3.getText().toString().trim().isEmpty()
                ||editText4.getText().toString().trim().isEmpty()
                ||editText5.getText().toString().trim().isEmpty()
                ||editText6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(otp_Page.this, "Please Enter The Valid Code", Toast.LENGTH_SHORT).show();
                }
                String code=
                        editText1.getText().toString()+ editText2.getText().toString()+ editText3.getText().toString()+ editText4.getText().toString()+ editText5.getText().toString()+ editText6.getText().toString();
                if(verificationId!=null)
                {
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(otp_Page.this, "OTP Registration Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(otp_Page.this,Home_app.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);  }
                                    else 
                                    {
                                        Toast.makeText(otp_Page.this, "The Verification Code Entered Was Invalid", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }

            }



        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(otp_Page.this,MainActivity.class);
                startActivity(intent);
            }
        });

        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("phonenumber")
        ));
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ getIntent().getStringExtra("phonenumber"),60, TimeUnit.SECONDS,
                        otp_Page.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(otp_Page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }




                        }

                );
            }
        });

        setupotpinputs();


    }
    private void setupotpinputs()
    {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    editText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    editText3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    editText4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    editText5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    editText6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}