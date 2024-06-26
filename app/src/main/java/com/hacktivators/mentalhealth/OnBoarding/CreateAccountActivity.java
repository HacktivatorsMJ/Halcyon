package com.hacktivators.mentalhealth.OnBoarding;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.User;
import com.hacktivators.mentalhealth.R;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {


    EditText email,password;
    AppCompatButton signup;
    TextView back;

    FirebaseAuth firebaseAuth;

    GoogleSignInClient googleSignInClient;

    SignInButton signInButton;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        user = new User();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back);

        firebaseAuth = FirebaseAuth.getInstance();

        signInButton = findViewById(R.id.signin);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken("915367982754-3p9feldbpp3dt3gvtckojovd34e4p7pl.apps.googleusercontent.com")
                .requestEmail()
                .requestProfile()
                .build();

        googleSignInClient = GoogleSignIn.getClient(CreateAccountActivity.this,googleSignInOptions);

        signInButton.setOnClickListener(v -> {
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent,100);

        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter emailID",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
                }else {

                    user.setEmail(email.getText().toString());

                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                startActivity(new Intent(CreateAccountActivity.this,InfoActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(CreateAccountActivity.this, "Authentication failed" + Objects.requireNonNull(task.getException()).toString(),
//                                        Toast.LENGTH_SHORT).show();


                                user.setId("uniqueone");
                                Intent intent = new Intent(CreateAccountActivity.this,InfoActivity.class);
                                intent.putExtra("user", user);
                                startActivity(intent);

                            }
                        }
                    });





                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==100){
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            if(signInAccountTask.isSuccessful()){
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);

                    if(googleSignInAccount != null){
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);


                        firebaseAuth.signInWithCredential(authCredential)
                                .addOnCompleteListener(task -> {
                                    if(task.isSuccessful()){
                                        String s = "Google Authentication successful";
                                        googleSignInClient.revokeAccess();


                                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                        assert firebaseUser != null;

                                        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                                        rootRef.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @SuppressLint("CheckResult")
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()) {
                                                        startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));

                                                    }else {
                                                        startActivity(new Intent(CreateAccountActivity.this,InfoActivity.class));

                                                    }
                                                }
                                            }
                                        });






                                    }
                                });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}