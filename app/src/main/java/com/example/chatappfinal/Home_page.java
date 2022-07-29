package com.example.chatappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatappfinal.Adapters.fragmentadpter;
import com.example.chatappfinal.databinding.ActivityHomePageBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Home_page extends AppCompatActivity {
    @NonNull
    TabLayout tabLayout;
    ActivityHomePageBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        binding= ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        tabLayout=findViewById(R.id.layout11);
        binding.viewpager.setAdapter(new fragmentadpter(getSupportFragmentManager()));
        binding.layout11.setupWithViewPager(binding.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Settings:
                Toast.makeText(Home_page.this, "settings clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.GG:
                Toast.makeText(Home_page.this, "Group Chat clicked", Toast.LENGTH_SHORT).show();
            case R.id.Logout:
                auth.signOut();

                Intent intent=new Intent(Home_page.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}