package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassActivity extends AppCompatActivity {

    EditText name;
    Button add;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("class");

        name =  (EditText) findViewById(R.id.add_class_name);
        add =  (Button) findViewById(R.id.add_class_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name.getText().toString()).child("name").setValue(name.getText().toString());
                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(name.getText().toString()).child("attendance").setValue("false");

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),"value add sucess",Toast.LENGTH_LONG).show();
                name.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        });


    }
}
