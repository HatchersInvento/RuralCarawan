package com.hatchers.ruralcaravane.user_login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Pref_Manager.PrefManager;
import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.user_login.apihelper.Login_ApiHelper;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText edtName, edtPassword;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
        clickListners();


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.LoginStatusColor));
        }
    }

    private void initialization() {
        prefManager = new PrefManager(this);
        edtName = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginBtn);
    }

    private void clickListners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setUserInfo()) {
                    ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Login");
                    progressDialog.show();
                    Login_ApiHelper.userLoginApi(LoginActivity.this, progressDialog);
                }
            }
        });
    }

    private boolean setUserInfo() {
        if (edtName.getText().toString().equalsIgnoreCase("") || edtPassword.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Invalid User Name or password", Toast.LENGTH_LONG).show();
            return false;
        } else {
            prefManager.setUserName(edtName.getText().toString());
            prefManager.setPassword(edtPassword.getText().toString());
            return true;
        }
    }
}
