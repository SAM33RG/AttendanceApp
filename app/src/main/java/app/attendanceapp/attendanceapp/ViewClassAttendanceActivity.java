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

public class ViewClassAttendanceActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<StudentData> nameList;
    RecyclerView recyclerView;
    ShowAttendanceAdapter adapter;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class_attendance);

        setContentView(R.layout.activity_view_attendance_show_sub_classes);

        bundle = getIntent().getExtras();

        final String name = bundle.getString("className");
        final String id = bundle.getString("id");
        final String sem = bundle.getString("sem");

        // Toast.makeText(this,"class: "+ name+"\nid: "+id,Toast.LENGTH_SHORT).show();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("attendance").child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem).child(id).child("attendance");
        nameList = new ArrayList<>();
        //nameList.add("123");
        recyclerView =  (RecyclerView) findViewById(R.id.all_classes_recyler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new ShowAttendanceAdapter(this,nameList);
        recyclerView.setAdapter(adapter);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(new StudentData(map.get("name"),map.get("roll no"),map.get("status")) );
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
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


}
