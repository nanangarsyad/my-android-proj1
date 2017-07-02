package com.steapps.steapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.steapps.steapps.db.DBLocal;
import com.steapps.steapps.db.DBServer;
import com.steapps.steapps.db.Status;

public class LoginActivity extends AppCompatActivity {

    private EditText etIDcard;
    private EditText etPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBLocal.prepare(this);
        DBServer.prepare(this);

        if (DBLocal.User.isAlreadyLoggedIn()) {
            startMainActivity();
            return;
        }

        setContentView(R.layout.activity_login);
        etIDcard = (EditText) findViewById(R.id.etIDcard);
        etPasword = (EditText) findViewById(R.id.etPassword);

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onClickBtnRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onClickBtnLogin(View view) {
        Status status = DBServer.login(etIDcard.getText().toString(),etPasword.getText().toString());
        if (status.isSucces()) {
            startMainActivity();
        } else {
            Toast.makeText(this, status.message, Toast.LENGTH_LONG).show();
        }
    }
}
