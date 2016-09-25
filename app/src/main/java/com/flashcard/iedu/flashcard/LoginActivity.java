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
import com.wang.avi.AVLoadingIndicatorView;


import edu.iedu.flashcard.dao.domain.User;
import edu.iedu.flashcard.service.UserService;

/**
 * VIEW_2
 */

public class LoginActivity extends AppCompatActivity {


    AVLoadingIndicatorView loadingMark;
    //Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        loadingMark = (AVLoadingIndicatorView)findViewById(R.id.avloadingIndicatorView);

        Button btnlogin = (Button)findViewById(R.id.gologin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gologin();
            }
        });

        Button btnsignup = (Button)findViewById(R.id.gosignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gosignup();
            }
        });
    }

    public void gologin () {

        loadingMark.setVisibility(View.VISIBLE);



        //Button btnlogin = (Button) view.findViewById(R.id.LOG_IN_Button);
        EditText loginusernameEditText = (EditText) findViewById(R.id.loginusername);
        String userName = loginusernameEditText.getText().toString();
        EditText loginpasswordEditText = (EditText) findViewById(R.id.loginpassword);
        String password = loginpasswordEditText.getText().toString();
        System.out.println("loginusername: " + userName);
        System.out.println("loginpassword: " + password);

        User user = new User();
        user.setEmail(loginusernameEditText.getText().toString());
        user.setPassword(loginpasswordEditText.getText().toString());

        Connection conn = new Connection(this, user);
        conn.execute(user);


    }

    public void gosignup (){
        Intent i = new Intent(this, SignupActivity.class);
        this.startActivity(i);
    }

    public void removeLoading(){
        loadingMark.setVisibility(View.GONE);
    }

    public void showLoginFail(){
        Toast.makeText(this, "login failed", Toast.LENGTH_LONG).show();
    }

    private class Connection extends AsyncTask {

        boolean isLoginSuccess;
        Context context;
        User user;

        public Connection(Context context, User user){
            this.context = context;
            this.user = user;
            this.isLoginSuccess = false;
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



                    isLoginSuccess = true;
                    //i.putExtra("USER_ID", userData.getId());
                    context.startActivity(i);
                }else{

                    }
                    //i.putExtra(SIGNUP_RESULT, SIGNUP_RESULT_FAIL);


            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            ((LoginActivity)context).removeLoading();

            if(isLoginSuccess){

            }else{
                ((LoginActivity)context).showLoginFail();
            }
        }
    }
}
