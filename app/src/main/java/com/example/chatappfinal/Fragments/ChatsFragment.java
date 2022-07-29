package com.example.chatappfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chatappfinal.Adapters.userAdapter;
import com.example.chatappfinal.databinding.FragmentChatsBinding;
import com.example.chatappfinal.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {



    public ChatsFragment() {
        // Required empty public constructor
    }
   FragmentChatsBinding binding;
    ArrayList<user> list=new ArrayList<>();
    FirebaseDatabase database;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentChatsBinding.inflate(inflater,container,false);
        database=FirebaseDatabase.getInstance();
        userAdapter adapter=new userAdapter(list,getContext());
        binding.chatrecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        binding.chatrecyclerview.setLayoutManager(layoutManager);
        database.getReference().child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();
            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
           user user=dataSnapshot.getValue(com.example.chatappfinal.user.class);
           user.setUid(dataSnapshot.getKey());
           if(!user.getUid().equals(FirebaseAuth.getInstance().getUid()))
           {
               list.add(user);
           }

           list.add(user);

            }
            adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}