package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class DeleteStudentsShowClass extends AppCompatActivity  {

    FirebaseDatabase database;
    DatabaseReference myRef, semRef;
    ArrayList<String>nameList, semList;
    Spinner classSpinner, semSpinner;
    ArrayAdapter<String> adapter, adapterForSem;
    String className, semName;
    Button addData;
    ActionBar toolbar;
    View backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_students_show_class);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("class");
        semRef = database.getReference("class");

        toolbar = getSupportActionBar();
        toolbar.setTitle("Delete Students");
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowHomeEnabled(true);


        nameList = new ArrayList<>();
        semList = new ArrayList<>();

        classSpinner = (Spinner) findViewById(R.id.class_spinner);
        semSpinner = (Spinner) findViewById(R.id.sem_spinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nameList);
        adapterForSem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semList);
        semSpinner.setAdapter(adapterForSem);
        addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),DeleteStudentForClass.class).putExtra("className",className).putExtra("sem",semName));
            }
        });



        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(dataSnapshot.getKey());
                className =  dataSnapshot.getKey();
                adapter.notifyDataSetChanged();
                classSpinner.setAdapter(adapter);
                // Toast.makeText(getApplicationContext(),"found",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                semList.clear();
                semRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(nameList.get(position)).child("sem").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                        semList.add(map.get("sem"));
                        adapterForSem.notifyDataSetChanged();
                        semSpinner.setAdapter(adapterForSem);
                        // Toast.makeText(getApplicationContext(),"found",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        semSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                semName = semList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
