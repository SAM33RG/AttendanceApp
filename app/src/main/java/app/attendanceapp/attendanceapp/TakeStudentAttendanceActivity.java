package app.attendanceapp.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class TakeStudentAttendanceActivity extends AppCompatActivity {

    Bundle bundle;
    Button addToFirebase;
    String name, sem;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference attendanceRef;
    TextView className;
    EditText title, date;
    Button sumbit;

    RecyclerView recyclerView;
    ArrayList<AttendanceData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_student_attendance);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
        list = new ArrayList<>();



        bundle = getIntent().getExtras();
        name = bundle.getString("className");
        sem = bundle.getString("sem");
        attendanceRef = database.getReference("attendance").child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem);


        sumbit =  (Button) findViewById(R.id.take_student_attendace_send_to_firebase);
        className =  (TextView) findViewById(R.id.take_student_attendace_class);
        className.setText(name);

        title = (EditText) findViewById(R.id.take_student_attendace_title);
        date = (EditText) findViewById(R.id.take_student_attendace_date);

        recyclerView =  (RecyclerView) findViewById(R.id.take_student_attendace_recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final AttendanceAdapter adapter =  new AttendanceAdapter(this,list,"take attendance");
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(simpleDateFormat.format(dNow));

        final String uniqueNumber = FirebaseAuth.getInstance().getUid().toString()+ft.format(dNow);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i =0 ; i< list.size();i++){
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("class").setValue(name);
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("title").setValue(title.getText().toString());
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("date").setValue(date.getText().toString());
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("id").setValue(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString());
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("attendance").child(list.get(i).rollno).child("roll no").setValue(list.get(i).rollno);
                    attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("attendance").child(list.get(i).rollno).child("name").setValue(list.get(i).name);
                    if (i==list.size()-1){
                        attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("attendance").child(list.get(i).rollno).child("status").setValue(list.get(i).status, new DatabaseReference.CompletionListener() {
                            public void onComplete(DatabaseError error, DatabaseReference ref) {
                                if (error==null){
                                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),"error: "+error,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }else {
                        attendanceRef.child(uniqueNumber + FirebaseAuth.getInstance().getUid().toString()+ " " +title.getText().toString() +" " +date.getText().toString()).child("attendance").child(list.get(i).rollno).child("status").setValue(list.get(i).status);

                    }

                }
            }
        });


        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem).child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(),"value add success\nnew student can be added",Toast.LENGTH_LONG).show();
                Map<String, String> map= (Map <String, String>) dataSnapshot.getValue();
                list.add(new AttendanceData(map.get("student roll no"),map.get("student name")));
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
                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        });


    }
}
