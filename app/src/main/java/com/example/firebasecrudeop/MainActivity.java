package com.example.firebasecrudeop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.firebasecrudeop.Holder.StudentViewHolder;
import com.example.firebasecrudeop.Model.Student;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference ItemRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showStudentsRecord();
    }


    void showStudentsRecord()
    {
        recyclerView = findViewById(R.id.recylcer_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students"),Student.class)
                .build();

        FirebaseRecyclerAdapter<Student, StudentViewHolder> adpter =
                new FirebaseRecyclerAdapter<Student, StudentViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull StudentViewHolder holder, int i, @NonNull Student student) {
                        holder.nametext.setText(student.getName());
                        holder.nametext.setText(student.getName());


                        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, UpdateDataActivity.class);
                                intent.putExtra("pid",student.getId());
                                startActivity(intent);

                            }
                        });

                        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Students");
                                myRef.child(student.getId()).removeValue();

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
                        StudentViewHolder holder = new StudentViewHolder(view);
                        return holder;

                    }
                };
        recyclerView.setAdapter(adpter);
        adpter.startListening();
    }


    void searchStudentsRecord(String s)
    {
        recyclerView = findViewById(R.id.recylcer_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").orderByChild("name").equalTo(s),Student.class)
                        .build();

        FirebaseRecyclerAdapter<Student, StudentViewHolder> adpter =
                new FirebaseRecyclerAdapter<Student, StudentViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull StudentViewHolder holder, int i, @NonNull Student student) {
                        holder.nametext.setText(student.getName());
                        holder.nametext.setText(student.getName());


                        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, UpdateDataActivity.class);
                                intent.putExtra("pid",student.getId());
                                startActivity(intent);

                            }
                        });

                        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Students");
                                myRef.child(student.getId()).removeValue();

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
                        StudentViewHolder holder = new StudentViewHolder(view);
                        return holder;

                    }
                };
        recyclerView.setAdapter(adpter);
        adpter.startListening();
    }















    public void Adduser(View view) {

        Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               searchStudentsRecord(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                showStudentsRecord();
                return false;
            }



        });

        return super.onCreateOptionsMenu(menu);
    }
}