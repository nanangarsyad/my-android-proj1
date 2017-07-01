package com.steapps.steapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.steapps.steapps.db.DBLocal;
import com.steapps.steapps.db.DBServer;

public class MainActivity extends AppCompatActivity {

    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
    }

    public void onClickBtnLogout(View view) {
        Status status = DBServer.logout();
        Toast.makeText(this, status.message, Toast.LENGTH_LONG).show();
        if (status.isSucces()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    public void onClickBtnIdentity(View view) {
    }

    public void onClickBtnApdDriver(View view) {
    }

    public void onClickBtnKelengkapan(View view) {
    }

    public void onClickBtnPlacard(View view) {
    }

    public void onClickBtnSuratDanBerlaku(View view) {
    }

    public void onClickBtnCodeTruck(View view) {
    }

    public void onClickBtnSend(View view) {
    }
}
