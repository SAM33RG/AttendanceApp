package app.attendanceapp.attendanceapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudentDataActivity extends AppCompatActivity {
    Bundle bundle;
    String name,sem;
    TextView nameTV;
    EditText studentName, studentRollNO;
    Button addToFirebase;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ActionBar toolbar;
    Boolean firstDataChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_data);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");

        firstDataChange =true;

        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Students");
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowHomeEnabled(true);

        bundle = getIntent().getExtras();
        name = bundle.getString("className");
        sem =  bundle.getString("sem");

        nameTV = (TextView) findViewById(R.id.add_student_data_class_name);
        nameTV.setText(name);

        studentName = (EditText) findViewById(R.id.add_student_data_student_name);
        studentRollNO = (EditText) findViewById(R.id.add_student_data_student_rollno);

        addToFirebase = (Button) findViewById(R.id.add_student_data_add_student);

        addToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem).child("name").setValue(name);
                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem).child("data").child(studentRollNO.getText().toString()).child("student name").setValue(studentName.getText().toString());
                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).child("sem").child(sem).child("data").child(studentRollNO.getText().toString()).child("student roll no").setValue(studentRollNO.getText().toString(),new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        Toast.makeText(getApplicationContext(),"Student Added.",Toast.LENGTH_SHORT).show();
                        studentName.setText("");
                        studentRollNO.setText("");
                    }
                });


            }
        });

        myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*if (firstDataChange){
                    firstDataChange =false;
                    return;
                }
                Toast.makeText(getApplicationContext(),"Student Added.\nTo delete student go to delete Student.",Toast.LENGTH_SHORT).show();
                studentName.setText("");
                studentRollNO.setText("");*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        });





    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
