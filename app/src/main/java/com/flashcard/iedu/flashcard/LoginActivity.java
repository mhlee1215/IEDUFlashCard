package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    public void gologin (View view) {
        Button btnlogin = (Button) view.findViewById(R.id.LOG_IN_Button);
        EditText loginusernameEditText = (EditText) findViewById(R.id.loginusername);
        String userName = loginusernameEditText.getText().toString();
        EditText loginpasswordEditText = (EditText) findViewById(R.id.loginpassword);
        String password = loginpasswordEditText.getText().toString();
        System.out.println("loginusername:"+userName);
        System.out.println("loginpassword"+password);

        User user = new User();
        user.setEmail(loginusernameEditText.getText().toString());
        user.setPassword(loginpasswordEditText.getText().toString());

        Connection conn = new Connection(this, user);
        conn.doInBackground(user);
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
                    User userQuery = new User();
                    userQuery.setEmail(((User) arg0[0]).getEmail());
                    User userData = UserService.readUserData(userQuery);
                    //System.out.println("USERDATA" + userData);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("USER_ID", userData.getId());
                    editor.commit();

                    //i.putExtra("USER_ID", userData.getId());
                    context.startActivity(i);
                }else{
                        Toast.makeText(context, "login failed", Toast.LENGTH_LONG).show();
                    }
                    //i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_FAIL);


            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
