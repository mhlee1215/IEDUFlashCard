package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;


import edu.iedu.flashcard.dao.domain.User;
import edu.iedu.flashcard.service.UserService;

/**
 * VIEW_2
 */

public class LoginActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void goSignup(View view){
        EditText loginusernameEditText = (EditText) findViewById(R.id.loginusername);

        EditText loginpasswordEditText = (EditText) findViewById(R.id.loginpassword);

        User user = new User();
        user.setName(loginusernameEditText.getText().toString());
        user.setPassword(loginpasswordEditText.getText().toString());

        Connection conn = new Connection(this, user);
        conn.doInBackground();

    }
    public void gologin (View view) {
        Button btnlogin = (Button) view.findViewById(R.id.LOG_IN_Button);
        EditText loginusernameEditText = (EditText) findViewById(R.id.loginusername);
        String userName = loginusernameEditText.getText().toString();
        EditText loginpasswordEditText = (EditText) findViewById(R.id.loginpassword);
        String password = loginpasswordEditText.getText().toString();
        System.out.println("loginusername:"+userName);
        System.out.println("loginpassword"+password);
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
                int result = UserService.login(user);

                Intent i = new Intent(context, MenuActivity.class);
                if(result == User.STATUS_LOGIN_SUCCESS){
                    //i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_SUCCESS);
                }else{
                    //i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_FAIL);
                }
                context.startActivity(i);

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
