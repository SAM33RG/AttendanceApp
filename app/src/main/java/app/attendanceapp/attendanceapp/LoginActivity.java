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
    private Button signIn, signUp;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = (Button) findViewById(R.id.login_sign_in);
        signUp = (Button) findViewById(R.id.login_sign_up);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

        email =  (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startMianActivity();
        }
        //updateUI(currentUser);

    }


    @Override
    public void onClick(View view) {
        int id  = view.getId();
        switch (id){
            case R.id.login_sign_in:{
                String emailString = email.getText().toString();
                String passwordString =  password.getText().toString();
                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "User Logged in.",
                                            Toast.LENGTH_SHORT).show();
                                    startMianActivity();

                                    // updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                   // updateUI(null);
                                }

                                // ...
                            }
                        });
            }
            break;

            case R.id.login_sign_up:{
                String emailString = email.getText().toString().trim();
                String passwordString =  password.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "User Created and Logged in.",
                                            Toast.LENGTH_SHORT).show();
                                    startMianActivity();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage().toString(),
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
            break;

        }

    }
    private void startMianActivity(){
        startActivity(new Intent(this,MainActivity.class));
    }
}
