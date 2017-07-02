package com.steapps.steapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.LabelToggle;
import com.steapps.steapps.db.DBKey;
import com.steapps.steapps.db.DBLocal;

public class PlacardActivity extends AppCompatActivity {

    private SingleSelectToggleGroup tglKanan;
    private SingleSelectToggleGroup tglKiri;
    private SingleSelectToggleGroup tglDepan;
    private SingleSelectToggleGroup tglBelakang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placard);

        tglKanan = (SingleSelectToggleGroup) findViewById(R.id.tglKanan);
        tglKiri = (SingleSelectToggleGroup) findViewById(R.id.tglKiri);
        tglDepan = (SingleSelectToggleGroup) findViewById(R.id.tglDepan);
        tglBelakang = (SingleSelectToggleGroup) findViewById(R.id.tglBelakang);

        setToggleCheckedIndex(tglKanan, DBLocal.Form.getIntValue(DBKey.FORM_PLACARD_KANAN));
        setToggleCheckedIndex(tglKiri, DBLocal.Form.getIntValue(DBKey.FORM_PLACARD_KIRI));
        setToggleCheckedIndex(tglDepan, DBLocal.Form.getIntValue(DBKey.FORM_PLACARD_DEPAN));
        setToggleCheckedIndex(tglBelakang, DBLocal.Form.getIntValue(DBKey.FORM_PLACARD_BELAKANG));
    }

    private int getToggleCheckedIndex(SingleSelectToggleGroup toggleGroup) {
        int idChecked = toggleGroup.getCheckedId();
        int len = toggleGroup.getChildCount();
        for (int c = 0; c < len; c++) {
            if (idChecked == toggleGroup.getChildAt(c).getId()) {
                return c;
            }
        }
        return 0;
    }

    private void setToggleCheckedIndex(SingleSelectToggleGroup toggleGroup, int index) {
        ((LabelToggle)toggleGroup.getChildAt(index)).setChecked(true);
    }


    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        DBLocal.Form.putValues(
                new Object[]{DBKey.FORM_PLACARD_KANAN,    getToggleCheckedIndex(tglKanan)},
                new Object[]{DBKey.FORM_PLACARD_KIRI,     getToggleCheckedIndex(tglKiri)},
                new Object[]{DBKey.FORM_PLACARD_DEPAN,    getToggleCheckedIndex(tglDepan)},
                new Object[]{DBKey.FORM_PLACARD_BELAKANG, getToggleCheckedIndex(tglBelakang)}
        );
        finish();

    }
}
