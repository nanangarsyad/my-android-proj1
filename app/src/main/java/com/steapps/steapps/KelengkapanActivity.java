package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kyleduo.switchbutton.SwitchButton;
import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class KelengkapanActivity extends BaseActivity {

    private SwitchButton swApar;
    private SwitchButton swSpillSkop;
    private SwitchButton swSpillSapuLidi;
    private SwitchButton swSpillSawDust;
    private SwitchButton swGps;
    private SwitchButton swLampuRotary;
    private SwitchButton swRambuPortable;
    private SwitchButton swKerucutPengaman;
    private SwitchButton swSegitigaPengaman;
    private SwitchButton swDongkrak;
    private SwitchButton swPitaPembatas;
    private SwitchButton swGansalRoda;
    private SwitchButton swKotakObat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelengkapan);

        swApar = (SwitchButton) findViewById(R.id.swApar);
        swSpillSkop = (SwitchButton) findViewById(R.id.swSpillSkop);
        swSpillSapuLidi = (SwitchButton) findViewById(R.id.swSpillSapuLidi);
        swSpillSawDust = (SwitchButton) findViewById(R.id.swSpillSawDust);
        swGps = (SwitchButton) findViewById(R.id.swGps);
        swLampuRotary = (SwitchButton) findViewById(R.id.swLampuRotary);
        swRambuPortable = (SwitchButton) findViewById(R.id.swRambuPortable);
        swKerucutPengaman = (SwitchButton) findViewById(R.id.swKerucutPengaman);
        swSegitigaPengaman = (SwitchButton) findViewById(R.id.swSegitigaPengaman);
        swDongkrak = (SwitchButton) findViewById(R.id.swDongkrak);
        swPitaPembatas = (SwitchButton) findViewById(R.id.swPitaPembatas);
        swGansalRoda = (SwitchButton) findViewById(R.id.swGansalRoda);
        swKotakObat = (SwitchButton) findViewById(R.id.swKotakObat);

        swApar.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_APAR));
        swSpillSkop.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_SPILL_SKOP));
        swSpillSapuLidi.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_SPILL_SAPU_LIDI));
        swSpillSawDust.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_SPILL_SAW_DUST));
        swGps.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_GPS));
        swLampuRotary.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_LAMPU_ROTARY));
        swRambuPortable.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_RAMBU_PORTABLE));
        swKerucutPengaman.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_KERUCUT_PENGAMAN));
        swSegitigaPengaman.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_SEGITIGA_PENGAMAN));
        swDongkrak.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_DONGKRAK));
        swPitaPembatas.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_PITA_PEMBATAS));
        swGansalRoda.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_GANSAL_RODA));
        swKotakObat.setCheckedImmediately(DBLocal.Form.getBooleanValue(DBKey.FORM_KELENGKAPAN_KOTAK_OBAT));
    }

    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        DBLocal.Form.putValues(
                new Object[]{DBKey.FORM_KELENGKAPAN_APAR,               swApar.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_SPILL_SKOP,         swSpillSkop.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_SPILL_SAPU_LIDI,    swSpillSapuLidi.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_SPILL_SAW_DUST,     swSpillSawDust.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_GPS,                swGps.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_LAMPU_ROTARY,       swLampuRotary.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_RAMBU_PORTABLE,     swRambuPortable.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_KERUCUT_PENGAMAN,   swKerucutPengaman.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_SEGITIGA_PENGAMAN,  swSegitigaPengaman.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_DONGKRAK,           swDongkrak.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_PITA_PEMBATAS,      swPitaPembatas.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_GANSAL_RODA,        swGansalRoda.isChecked()},
                new Object[]{DBKey.FORM_KELENGKAPAN_KOTAK_OBAT,         swKotakObat.isChecked()}
        );
        finish();
    }
}
