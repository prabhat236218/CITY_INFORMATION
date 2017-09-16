package com.example.prabhat.city_information;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;


public class  MainActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static EditText e1;
    private static EditText e2;
    private static Button b1;
    private static TextView t1;
    private static DBController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /// ButterKnife.inject(this);
        db=new DBController(this);
        e1 = (EditText) findViewById(R.id.input_email);
        e2 = (EditText) findViewById(R.id.input_password);
        b1 = (Button) findViewById(R.id.btn1);
        t1 = (TextView) findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }

        });
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                login();
            }

        });

    }

    public void login()
    {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
       // b1.setEnabled(false);
       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();*/

        String email = e1.getText().toString();
        String password =e2.getText().toString();

       //// * put your code of checking user details
          if(db.checkUser(email,password))
          {
              Toast.makeText(this,"you have entered  in  main application",Toast.LENGTH_LONG).show();
              startActivity(new Intent(getApplicationContext(),find.class));
          }
          else {
              Toast.makeText(this, "you have not registered yet ! please register first", Toast.LENGTH_LONG).show();
              startActivity(new Intent(getApplicationContext(),Register.class));
          }

   /*  new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 10000);*/

    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        b1.setEnabled(true);
    }
    public void onLoginSuccess() {
        b1.setEnabled(true);
        finish();
    }
    public boolean validate() {
        boolean valid = true;

        String email = e1.getText().toString();
        String password = e2.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e1.setError("enter a valid email address");
            valid = false;
        } else {
            e1.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            e2.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            e2.setError(null);
        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }


}