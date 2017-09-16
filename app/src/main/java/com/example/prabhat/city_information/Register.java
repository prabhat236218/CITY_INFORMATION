package com.example.prabhat.city_information;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static EditText e3;
    private static EditText e4;
    private static EditText e5;
   private static Button btn2;
    private static TextView t2;
    private static DBController db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e3=(EditText)findViewById(R.id.input_name);
        e4=(EditText)findViewById(R.id.input_email);
        e5=(EditText)findViewById(R.id.input_password);
        btn2=(Button)findViewById(R.id.btn2);
        t2=(TextView)findViewById(R.id.textView2);
        db=new DBController(this);
        t2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

        });
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                signup();
            }

        });



    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

      ///  btn2.setEnabled(false);

        /*final ProgressDialog progressDialog = new ProgressDialog(Register.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();*/

        String name = e3.getText().toString();
        String email = e4.getText().toString();
        String password = e5.getText().toString();

        // TODO: Implement your own signup logic here.
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        if(db.addUser(user)) {
            Toast.makeText(this, "you have registered succesfully", Toast.LENGTH_LONG);
            startActivity(new Intent(getApplicationContext(),find.class));
        }
       /* new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }


    public void onSignupSuccess() {
        btn2.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn2.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = e3.getText().toString();
        String email = e4.getText().toString();
        String password = e5.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            e3.setError("at least 3 characters");
            valid = false;
        } else {
            e3.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e4.setError("enter a valid email address");
            valid = false;
        } else {
            e4.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            e5.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            e5.setError(null);
        }

        return valid;
    }



}
