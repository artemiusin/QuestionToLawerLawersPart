package com.example.askquestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView1;
    List<String> list;
    List<User> listUser;
    ArrayAdapter<String> adapter;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDataFromRTDB();
    }

    private void init() {

        listView1 = findViewById(R.id.listView1);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView1.setAdapter(adapter);
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        listUser = new ArrayList<>();
        setOnClickItem();
    }

    private void setOnClickItem() {
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = listUser.get(position);
                Intent i = new Intent(MainActivity.this,InformationActivity.class);
                i.putExtra("phone", user.phone);
                i.putExtra("question",user.question);
                startActivity(i);

            }
        });
    }

    private void getDataFromRTDB(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list.size()>0) list.clear();
                if (listUser.size()>0) list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    list.add(user.question);
                    listUser.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mRef.addValueEventListener(valueEventListener);
    }

}