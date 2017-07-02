package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class IdentityActivity extends AppCompatActivity {

    private TextView tvNamaLengkap;
    private TextView tvUserName;
    private TextView tvIdCard;
    private TextView tvTTL;
    private TextView tvTanggalMasuk;
    private TextView tvNoTelp;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indentity);

        tvNamaLengkap = (TextView) findViewById(R.id.tvNamaLengkap);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvIdCard = (TextView) findViewById(R.id.tvIdCard);
        tvTTL = (TextView) findViewById(R.id.tvTtl);
        tvTanggalMasuk = (TextView) findViewById(R.id.tvTanggalMasuk);
        tvNoTelp = (TextView) findViewById(R.id.tvNoTelp);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvNamaLengkap.setText(DBLocal.User.getStringValue(DBKey.USER_FULLNAME));
        tvUserName.setText(DBLocal.User.getStringValue(DBKey.USER_USERNAME));
        tvIdCard.setText(DBLocal.User.getStringValue(DBKey.USER_IDCARD));
        tvTTL.setText(DBLocal.User.getStringValue(DBKey.USER_TTL));
        tvTanggalMasuk.setText(DBLocal.User.getStringValue(DBKey.USER_TANGGAL_MASUK));
        tvNoTelp.setText(DBLocal.User.getStringValue(DBKey.USER_PHONE));
        tvEmail.setText(DBLocal.User.getStringValue(DBKey.USER_EMAIL));
    }


}
