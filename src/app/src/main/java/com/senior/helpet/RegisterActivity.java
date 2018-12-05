package com.senior.helpet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.senior.helpet.model.Pet;
import com.senior.helpet.model.User;

import java.util.ArrayList;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {

    private EditText regEmailText;
    private EditText regPassText;
    private EditText regConfirmPassText;
    private EditText regName;
    private EditText regSurname;
    private Button regBtn;
    private Button regLoginBtn;
    private ProgressBar regProgress;

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        regEmailText = (EditText) findViewById(R.id.reg_email);
        regPassText = (EditText) findViewById(R.id.reg_password);
        regConfirmPassText = (EditText) findViewById(R.id.reg_confirm_pass);
        regName = (EditText) findViewById(R.id.reg_name);
        regSurname = (EditText) findViewById(R.id.reg_surname);
        regBtn = (Button) findViewById(R.id.reg_btn);
        regLoginBtn = (Button) findViewById(R.id.reg_login_btn);
        regProgress = (ProgressBar) findViewById(R.id.reg_progress);

        regLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regEmailText.getText().toString();
                String pass = regPassText.getText().toString();
                String confirmPass = regConfirmPassText.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirmPass)) {
                    if(pass.equals(confirmPass)) {
                        regProgress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    String email = regEmailText.getText().toString();
                                    String name = regName.getText().toString();
                                    String surname = regSurname.getText().toString();
                                    String uid = mAuth.getUid();
                                    ArrayList<Pet> pets = new ArrayList<Pet>();
                                    writeNewUser(uid, name, surname, email, pets);
                                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                                }
                                regProgress.setVisibility(View.INVISIBLE);
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Confirm Password and Password Field doesn't match.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void writeNewUser(String userId, String name, String surname, String email, ArrayList<Pet> pets) {
        User user = new User(userId, name, surname, email, pets);
        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
