package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class DeleteStudentForClass extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<StudentData> nameList;
    RecyclerView recyclerView;
    DeleteStudentAdapter adapter;
    Bundle bundle;
    Button deleteSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_for_class);

        bundle = getIntent().getExtras();
        final String name = bundle.getString("className");
        Toast.makeText(this, "name " +name,Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
        nameList = new ArrayList<>();
        recyclerView =  (RecyclerView) findViewById(R.id.all_classes_recyler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new DeleteStudentAdapter(this,nameList,name);
        recyclerView.setAdapter(adapter);

        deleteSelected = (Button) findViewById(R.id.delete_selected);

        deleteSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameList = adapter.getList();
                for (int i=0; i<nameList.size();i++){
                    if (nameList.get(i).toBeDeleted){
                        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("data").child(nameList.get(i).rollno).removeValue();
                    }
                    if (i == nameList.size()-1){
                        finish();
                    }
                }
            }
        });

        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                nameList.add(new StudentData(map.get("student name"),map.get("student roll no")));
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                /*Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                StudentData studentData = new StudentData(map.get("student name"),map.get("student roll no"));
                Toast.makeText(getApplicationContext(),"name "+studentData.name+"rool no "+studentData.rollno,Toast.LENGTH_SHORT).show();
                nameList.remove(studentData);
                Toast.makeText(getApplicationContext(),"after"+String.valueOf(nameList.contains(studentData))+"index "+ nameList.indexOf(studentData),Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);*/
                /*startActivity(new Intent(getApplicationContext(),DeleteStudentForClass.class).putExtra("className",name));
                finish();*/
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
