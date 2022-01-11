package com.example.firebasecrudeop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);




    }






    void StoreUserInfo(String username, String cnic,int age, float cgpa)
    {


        String  id = "ID-"+String.valueOf(new Random().nextInt(12000)+600);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Ref;
        Ref  = database.getReference().child("Students");


        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> userdataMap = new HashMap<>();
                userdataMap.put("id",id);
                userdataMap.put("name", username);
                userdataMap.put("CNIC", cnic);
                userdataMap.put("Age", age);

                userdataMap.put("CGPA", cgpa);

                Ref.child(id).setValue(userdataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddUserActivity.this, "Congrats, Student Data Added ", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddUserActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }

    public void Adduserinfo(View view) {

        StoreUserInfo("Abdullah", "341013335224",22,3.0f);

    }
}