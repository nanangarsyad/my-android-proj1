package com.steapps.steapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;
import com.steapps.steapps.db.DBServer;
import com.steapps.steapps.db.Status;

import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseActivity {

    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUserName = (TextView) findViewById(R.id.tvUserName);

        tvUserName.setText(DBLocal.User.getStringValue(DBKey.USER_USERNAME));
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
        startActivity(new Intent(this, IdentityActivity.class));
    }

    public void onClickBtnApdDriver(View view) {
        startActivity(new Intent(this, APDDriverActivity.class));
    }

    public void onClickBtnKelengkapan(View view) {
        startActivity(new Intent(this, KelengkapanActivity.class));
    }

    public void onClickBtnPlacard(View view) {
        startActivity(new Intent(this, PlacardActivity.class));
    }

    public void onClickBtnSuratDanBerlaku(View view) {
        startActivity(new Intent(this, SuratDanBerlakuActivity.class));
    }

    public void onClickBtnCodeTruck(View view) {
        startActivity(new Intent(this, CodeTruckActivity.class));
    }

    public void onClickBtnSend(View view) {
        DBServer.sendFormToServer(status -> {
            if (status.isSucces()) {
                Toasty.success(this, status.message, Toast.LENGTH_LONG).show();
            } else {
                Toasty.error(this, status.message, Toast.LENGTH_LONG).show();
            }

        });
    }

    public void onClickBtnClear(View view) {
        DBLocal.Form.clearAll();
        String msg = "Success. Form cleared.";
        Toasty.success(this, msg , Toast.LENGTH_LONG).show();
    }
}
