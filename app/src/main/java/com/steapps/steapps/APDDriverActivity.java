package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kyleduo.switchbutton.SwitchButton;
import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class APDDriverActivity extends AppCompatActivity {

    private SwitchButton swHelm;
    private SwitchButton swSafetyShoes;
    private SwitchButton swSafetyGlasses;
    private SwitchButton swBodyVest;
    private SwitchButton swSarungTangan;
    private SwitchButton swDuskMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apddriver);

        swHelm = (SwitchButton) findViewById(R.id.swHelm);
        swSafetyShoes = (SwitchButton) findViewById(R.id.swSafetyShoes);
        swSafetyGlasses = (SwitchButton) findViewById(R.id.swSafetyGlasses);
        swBodyVest = (SwitchButton) findViewById(R.id.swBodyVest);
        swSarungTangan = (SwitchButton) findViewById(R.id.swSarungTangan);
        swDuskMask = (SwitchButton) findViewById(R.id.swDuskMask);

        swHelm.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_APD_HELM));
        swSafetyShoes.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_APD_SAFETY_SHOES));
        swSafetyGlasses.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_APD_SAFETY_GLASSES));
        swBodyVest.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_APD_BODY_VEST));
        swSarungTangan.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_APD_SARUNG_TANGAN));
        swDuskMask.setCheckedImmediately(DBLocal.User.getBooleanValue(DBKey.FORM_APD_DUSK_MASK));
    }

    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        DBLocal.Form.putValues(
                new Object[]{DBKey.FORM_APD_HELM,           swHelm.isChecked()},
                new Object[]{DBKey.FORM_APD_SAFETY_SHOES,   swSafetyShoes.isChecked()},
                new Object[]{DBKey.FORM_APD_SAFETY_GLASSES, swSafetyGlasses.isChecked()},
                new Object[]{DBKey.FORM_APD_BODY_VEST,      swBodyVest.isChecked()},
                new Object[]{DBKey.FORM_APD_SARUNG_TANGAN,  swSarungTangan.isChecked()},
                new Object[]{DBKey.FORM_APD_DUSK_MASK,      swDuskMask.isChecked()}
        );
        finish();
    }
}

