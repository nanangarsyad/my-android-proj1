package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.steapps.steapps.db.DBServer;
import com.steapps.steapps.db.Status;

import net.rimoto.intlphoneinput.IntlPhoneInput;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etUserName;
    private EditText etIdCard;
    private EditText etTTL;
    private EditText etTanggalMasuk;
    private EditText etPassword1;
    private EditText etPassword2;
    private IntlPhoneInput phoneInput;
    private EditText etEmailAddres;
    private CheckBox cbAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etIdCard = (EditText) findViewById(R.id.etIDCard);
        etTTL = (EditText) findViewById(R.id.etTTL);
        etTanggalMasuk = (EditText) findViewById(R.id.etTanggalMasuk);
        etPassword1 = (EditText) findViewById(R.id.etPassword1);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        phoneInput = (IntlPhoneInput) findViewById(R.id.cxPhoneInput);
        etEmailAddres = (EditText) findViewById(R.id.etEmailAddress);
        cbAgreement = (CheckBox) findViewById(R.id.cbAgreement);
    }

    public void onClickBtnRegister(View view) {
        if (!cbAgreement.isChecked()) {
            Toast.makeText(this, "You must checked the agreement", Toast.LENGTH_LONG).show();
            return;
        }
        String pass1 = etPassword1.getText().toString();
        String pass2 = etPassword2.getText().toString();
        if (!pass1.equals(pass2)) {
            Toast.makeText(this, "Password not same", Toast.LENGTH_LONG).show();
            etPassword1.setText(null);
            etPassword2.setText(null);
            return;
        }
        Status status = DBServer.register(
            etFullName.getText().toString(),
            etIdCard.getText().toString(),
            pass1,
            etUserName.getText().toString(),
            phoneInput.getText(),
            etEmailAddres.getText().toString(),
            etTTL.getText().toString(),
            etTanggalMasuk.getText().toString()
        );

        if (status.isSucces()) {
            Toast.makeText(this, status.message, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, status.message, Toast.LENGTH_SHORT).show();
        }
    }
}
