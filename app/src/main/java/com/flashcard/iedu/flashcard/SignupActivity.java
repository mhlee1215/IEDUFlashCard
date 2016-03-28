package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.flashcard.iedu.flashcard.R;

import edu.iedu.flashcard.dao.domain.User;
import edu.iedu.flashcard.service.UserService;

/**
 * VIEW_3
 */

public class SignupActivity extends AppCompatActivity {
    public static final int SIGNUP_RESULT_SUCCESS = 1;
    public static final int SIGNUP_RESULT_FAIL = 2;
    public static final String SIGNUP_RESULT = "SIGN_UP_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void onCheckboxClicked(View view) {
    }

    public void goSignup(View view){
        EditText usernameEditText = (EditText) findViewById(R.id.username);

        EditText emailEditText = (EditText) findViewById(R.id.email);

        EditText passwordEditText = (EditText) findViewById(R.id.password);

        User user = new User();
        user.setName(usernameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        Connection conn = new Connection(this, user);
        conn.doInBackground();
    }

    private class Connection extends AsyncTask {

        Context context;
        User user;

        public Connection(Context context, User user){
            this.context = context;
            this.user = user;
        }

        @Override
        protected Object doInBackground(Object... arg0) {
            try {
                boolean result = UserService.addUser(user);

                Intent i = new Intent(context, MainActivity.class);
                if(result){
                    i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_SUCCESS);
                }else{
                    i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_FAIL);
                }
                context.startActivity(i);

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }



}
