package app.attendanceapp.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class DeleteStudentsShowClass extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> nameList;
    RecyclerView recyclerView;
    AllClassesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_students_show_class);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
        nameList = new ArrayList<>();
        //nameList.add("123");
        recyclerView =  (RecyclerView) findViewById(R.id.all_classes_recyler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new AllClassesAdapter(this,nameList,"delete students");
        recyclerView.setAdapter(adapter);


        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(map.get("name").toString());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
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


}
