package com.example.silver_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silver_tracker.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class register extends AppCompatActivity {
    TextView signin;
    Button getstarted;
    EditText email1, password1;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    // private Inflater ActivityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getstarted = findViewById(R.id.getstarted);
        email1 = findViewById(R.id.email);
        password1 = findViewById(R.id.password);
        signin = findViewById(R.id.Signin);
     //  getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(register.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Working...");

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email1.getText().toString();
                String password = password1.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(register.this, home_page.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }

            ;


        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);

            }


        });

    }
        }