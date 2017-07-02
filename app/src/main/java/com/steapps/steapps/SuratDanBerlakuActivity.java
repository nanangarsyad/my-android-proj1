package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kyleduo.switchbutton.SwitchButton;
import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class SuratDanBerlakuActivity extends AppCompatActivity {

    private EditText etIjinB3;
    private EditText etSIM;
    private EditText etKIR;
    private EditText etSTNK;
    private EditText etUjiEmisi;
    private EditText etKetDokter;
    private SwitchButton swSertifikat;
    private SwitchButton swIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_dan_berlaku);

        etIjinB3 = (EditText) findViewById(R.id.etIjinB3);
        etSIM = (EditText) findViewById(R.id.etSIM);
        etKIR = (EditText) findViewById(R.id.etKIR);
        etSTNK = (EditText) findViewById(R.id.etSTNK);
        etUjiEmisi = (EditText) findViewById(R.id.etUjiEmisi);
        etKetDokter = (EditText) findViewById(R.id.etKetDokter);
        swSertifikat = (SwitchButton) findViewById(R.id.swSertifikat);
        swIdCard = (SwitchButton) findViewById(R.id.swIDCard);

        etIjinB3.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_IJIN_B3));
        etSIM.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_SIM));
        etKIR.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_KIR));
        etSTNK.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_STNK));
        etUjiEmisi.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_UJI_EMISI));
        etKetDokter.setText(DBLocal.Form.getStringValue(DBKey.FORM_SRT_BERLAKU_KET_DOKTER));
        swSertifikat.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_SRT_BERLAKU_SERTIFIKAT));
        swIdCard.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_SRT_BERLAKU_IDCARD));
    }

    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        DBLocal.Form.putValues(
                new Object[]{DBKey.FORM_SRT_BERLAKU_IJIN_B3,    etIjinB3.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_SIM,        etSIM.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_KIR,        etKIR.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_STNK,       etSTNK.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_UJI_EMISI,  etUjiEmisi.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_KET_DOKTER, etKetDokter.getText().toString()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_SERTIFIKAT, swSertifikat.isChecked()},
                new Object[]{DBKey.FORM_SRT_BERLAKU_IDCARD,     swIdCard.isChecked()}
        );
        finish();
    }
}
