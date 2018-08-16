package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView logout,addClass,addStudents,takeAttendance, viewAttendance, deleteStudents;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout =  (CardView) findViewById(R.id.main_logout);
        addClass =  (CardView) findViewById(R.id.main_add_class);
        addStudents = (CardView) findViewById(R.id.main_add_student);
        takeAttendance = (CardView) findViewById(R.id.main_take_attendance);
        viewAttendance = (CardView) findViewById(R.id.main_iew_attendance);
        deleteStudents =  (CardView) findViewById(R.id.main_delete_student) ;
        logout.setOnClickListener(this);
        addClass.setOnClickListener(this);
        addStudents.setOnClickListener(this);
        takeAttendance.setOnClickListener(this);
        viewAttendance.setOnClickListener(this);
        deleteStudents.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.main_logout:{
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));

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
            case R.id.main_take_attendance:{
                startActivity(new Intent(this, TakeAttendanceAcitivity.class));

            }
            break;
            case R.id.main_iew_attendance:{
                startActivity(new Intent(this, ViewAttendanceShowClasses    .class));

            }
            break;
            case R.id.main_delete_student:{
                startActivity(new Intent(this, DeleteStudentsShowClass    .class));

            }
            break;
        }


    }
}
