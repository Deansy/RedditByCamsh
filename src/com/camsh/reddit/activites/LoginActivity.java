package com.camsh.reddit.activites;

import com.camsh.reddit.Constants;
import com.camsh.reddit.R;
import com.camsh.reddit.R.id;
import com.camsh.reddit.R.layout;
import com.camsh.reddit.api.RedditCookie;
import com.camsh.reddit.api.RedditSession;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button loginButton;
    Button useDevLoginButton;

    RedditSession redditSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        redditSession = new RedditSession();

        loginButton = (Button)findViewById(R.id.LoginButton);
        if (Constants.DEV_MODE) {
        	useDevLoginButton = (Button)findViewById(R.id.UseDEV_LOGIN);
        }
        if (Constants.DEV_MODE == true) {
	        if (Constants.DEV_USERNAME.equals("")) {
	        	// DEV_USERNAME is missing
	        	useDevLoginButton.setAlpha(0.0f);
	        }
	        if (Constants.DEV_PASSWORD.equals("")) {
	        	// DEV_PASSWORD is missing
	        	useDevLoginButton.setAlpha(0.0f);
	        }
        }
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText usernameInput = (EditText)findViewById(R.id.UsernameInput);
                EditText passwordInput = (EditText)findViewById(R.id.PasswordInput);
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                RedditCookie loginCookie = redditSession.GetLoginCookie(username,password);
                Toast toast = Toast.makeText(getApplication(),loginCookie.string, Toast.LENGTH_SHORT);
                toast.show();
                
                Intent i= new Intent(getApplicationContext(), Subscriptions.class);
                i.putExtra("login_cookie",loginCookie.string);
                startActivity(i);
            }
        });
        if (Constants.DEV_MODE) {
	        useDevLoginButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                String username = Constants.DEV_USERNAME;
	                String password = Constants.DEV_PASSWORD;
	
	                RedditCookie loginCookie = redditSession.GetLoginCookie(username,password);
	                //Toast toast = Toast.makeText(getApplication(),loginCookie.string, Toast.LENGTH_SHORT);
	                //toast.show();
	                
	                Intent i= new Intent(getApplicationContext(), com.camsh.reddit.activites.Subscriptions.class);
	                i.putExtra("login_cookie", loginCookie.string);
	                startActivity(i);
	            }
	        });
        }
       
    }
}
