package app.attendanceapp.attendanceapp;

import android.support.v7.app.ActionBar;
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

public class ViewAttendanceShowSubClasses extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<DateTitle> nameList;
    RecyclerView recyclerView;
    SubClassesAdpater adapter;
    Bundle bundle;
    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_show_sub_classes);

        bundle = getIntent().getExtras();
        final String name = bundle.getString("className");
        final String sem = bundle.getString("sem");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("attendance").child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem);
        nameList = new ArrayList<>();

        toolbar = getSupportActionBar();
        toolbar.setTitle("View Attendance");
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowHomeEnabled(true);

        recyclerView =  (RecyclerView) findViewById(R.id.all_classes_recyler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new SubClassesAdpater(this,nameList,name,sem);
        recyclerView.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(new DateTitle(map.get("title"),map.get("date"),map.get("id")) );
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

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
