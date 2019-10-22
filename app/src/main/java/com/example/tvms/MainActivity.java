package com.example.tvms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginDriver, loginEnforcer;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDriver = this.findViewById(R.id.btn_driverLogin);
        loginEnforcer = this.findViewById(R.id.btn_enforcerLogin);
        progressDialog = new ProgressDialog(this);

        loginDriver.setOnClickListener(this);
        loginEnforcer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginDriver)
        {
//            EnforcerSharedPreferenceManager.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(this, login_driver.class));
        }if(v == loginEnforcer){
//            SharedPreferenceManager.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(this, login_enforcer.class));
        }
    }
}
