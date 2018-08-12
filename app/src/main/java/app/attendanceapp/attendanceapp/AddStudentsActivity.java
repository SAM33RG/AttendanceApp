package app.attendanceapp.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class AddStudentsActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String>nameList;
    RecyclerView recyclerView;
    AllClassesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("class");
        nameList = new ArrayList<>();
        //nameList.add("123");
        recyclerView =  (RecyclerView) findViewById(R.id.all_classes_recyler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new AllClassesAdapter(this,nameList);
        recyclerView.setAdapter(adapter);

        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(map.get("name"));
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"found",Toast.LENGTH_SHORT).show();
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
