package com.example.chatappfinal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappfinal.R;
import com.example.chatappfinal.chatdetailactivity;
import com.example.chatappfinal.user;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {
    ArrayList <user> list;
    Context context;

    public userAdapter(ArrayList<user> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.smaple_show_user,parent,false);
   return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         user user=list.get(position);
         holder.username.setText(user.getName());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(context, chatdetailactivity.class);
                 intent.putExtra("user",user.getUid());
                 
                 context.startActivity(intent);


             }
         });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username,lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.usernamelist);
            lastMessage=itemView.findViewById(R.id.lastmessage);
        }
    }
}
