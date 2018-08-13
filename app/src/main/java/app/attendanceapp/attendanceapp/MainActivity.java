package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logout,addClass,addStudents,takeAttendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout =  (Button) findViewById(R.id.main_logout);
        addClass =  (Button) findViewById(R.id.main_add_class);
        addStudents = (Button) findViewById(R.id.main_add_student);
        takeAttendance = (Button) findViewById(R.id.main_take_attandance);
        logout.setOnClickListener(this);
        addClass.setOnClickListener(this);
        addStudents.setOnClickListener(this);
        takeAttendance.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.main_logout:{
                FirebaseAuth.getInstance().signOut();
                finish();

            }
            break;
            case R.id.main_add_class:{
                startActivity(new Intent(this, AddClassActivity.class));
            }
            break;
            case R.id.main_add_student:{
                startActivity(new Intent(this, AddStudentsActivity.class));

            }
            break;
            case R.id.main_take_attandance:{
                startActivity(new Intent(this, TakeAttendanceAcitivity.class));

            }
            break;
        }


    }
}
