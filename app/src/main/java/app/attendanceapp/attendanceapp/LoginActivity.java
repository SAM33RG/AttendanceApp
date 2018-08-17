package app.attendanceapp.attendanceapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button signIn, signUp,fogot;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = (Button) findViewById(R.id.login_sign_in);
        signUp = (Button) findViewById(R.id.login_sign_up);
        fogot = (Button) findViewById(R.id.login_forgot);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        fogot.setOnClickListener(this);

        email =  (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startMianActivity();
        }

    }
    @Override
    public void onClick(View view) {
        int id  = view.getId();
        switch (id){
            case R.id.login_sign_in:{
                if (email.getText().toString().compareTo("")==0||password.getText().toString().compareTo("")==0){
                    Toast.makeText(getApplicationContext(),"please fill ALL FIELDS to continue",Toast.LENGTH_SHORT).show();
                    break;
                }
                String emailString = email.getText().toString().trim();
                String passwordString =  password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "User Logged in.",
                                            Toast.LENGTH_SHORT).show();
                                    startMianActivity();

                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
            break;

            case R.id.login_sign_up:{
                if (email.getText().toString().compareTo("")==0||password.getText().toString().compareTo("")==0){
                    Toast.makeText(getApplicationContext(),"please fill ALL FIELDS to continue",Toast.LENGTH_SHORT).show();
                    break;
                }
                String emailString = email.getText().toString().trim();
                String passwordString =  password.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "User Created and Logged in.",
                                            Toast.LENGTH_SHORT).show();
                                    startMianActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
            break;
            case R.id.login_forgot:{
                if (email.getText().toString().compareTo("")==0){
                    Toast.makeText(getApplicationContext(),"please fill EMAIL to continue",Toast.LENGTH_SHORT).show();
                    break;
                }
                mAuth.sendPasswordResetEmail(email.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"email sent to your id\n",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"failed to send email",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
            break;

        }

    }
    private void startMianActivity(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
