package com.steapps.steapps;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class CodeTruckActivity extends AppCompatActivity {

    private TextView tvHeader;
    private EditText etCodeTruck;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_truck);

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        etCodeTruck = (EditText) findViewById(R.id.etCodeTruck);

        tvHeader.setText(
                "Hi, "+
                DBLocal.User.getStringValue(DBKey.USER_USERNAME) +
                "\n Input truck code bellow"
        );
        etCodeTruck.setText(DBLocal.Form.getStringValue(DBKey.FORM_TRUCK_CODE));
    }

    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        DBLocal.Form.putValue(DBKey.FORM_TRUCK_CODE, etCodeTruck.getText().toString());
        finish();
    }
}
