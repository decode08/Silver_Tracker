package com.example.silver_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    TextView register;
    Button submit;
    EditText email2, password2;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submit = findViewById(R.id.Submit);
        email2 = findViewById(R.id.login_email);
        password2 = findViewById(R.id.password_login);
        register = findViewById(R.id.login_register);
      //  getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(login.this);
        progressDialog.setTitle("Your Account");
        progressDialog.setMessage("Working...");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email2.getText().toString();
                String password = password2.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(login.this, home_page.class);
//                                   intent.putExtra("email",mAuth.getCurrentUser().getEmail());
//                                   intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                    startActivity(intent);

                                    }
                                else {
                                    Toast.makeText(getApplicationContext(), "Invalid Id/Password", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }

            ;


        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);

            }


        });
//                submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(login.this, home_page.class);
//                startActivity(intent);
//
//            }
//
//
//        });
    }
}

