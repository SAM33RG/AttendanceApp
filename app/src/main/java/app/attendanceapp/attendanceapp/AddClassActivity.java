package app.attendanceapp.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child(name.getText().toString()).setValue("class added");

            }
        });


    }
}
