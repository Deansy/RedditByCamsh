package com.camsh.reddit;

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
        useDevLoginButton = (Button)findViewById(R.id.UseDEV_LOGIN);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameInput = (EditText)findViewById(R.id.UsernameInput);
                EditText passwordInput = (EditText)findViewById(R.id.PasswordInput);
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                RedditCookie loginCookie = redditSession.GetLoginCookie(username,password);
                Toast toast = Toast.makeText(getApplication(),loginCookie.string, Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, HomeView.class);
                startActivity(intent);
            }
        });

        useDevLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Constants.DEV_USERNAME;
                String password = Constants.DEV_PASSWORD;

                RedditCookie loginCookie = redditSession.GetLoginCookie(username,password);
                Toast toast = Toast.makeText(getApplication(),loginCookie.string, Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, HomeView.class);
                startActivity(intent);
            }
        });
    }
}
