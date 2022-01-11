package com.example.firebasecrudeop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

public class UpdateDataActivity extends AppCompatActivity {

    String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

       id =  getIntent().getStringExtra("pid");
    }





    void StoreUserInfo(String username, String cnic,int age, float cgpa)
    {



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Ref;
        Ref  = database.getReference().child("Students");


        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> userdataMap = new HashMap<>();
                userdataMap.put("name", username);
                userdataMap.put("CNIC", cnic);
                userdataMap.put("Age", age);

                userdataMap.put("CGPA", cgpa);

                Ref.child(id).updateChildren(userdataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(UpdateDataActivity.this, "Congrats, Student Data Updated ", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateDataActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }

    public void Updateuserinfo(View view) {

        StoreUserInfo("Abdullah", "341013335224",21,3.0f);

    }
}