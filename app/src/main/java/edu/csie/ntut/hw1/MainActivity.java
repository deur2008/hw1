package edu.csie.ntut.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Spinner mSpnSex;
    private RadioGroup mRadGrp;
    private RadioButton mRadBtnAgeRange1;
    private RadioButton mRadBtnAgeRange2;
    private RadioButton mRadBtnAgeRange3;
    private TextView mTxtNumFamily;
    private NumberPicker mNumPkrFamily;
    private Button mBtnOK;
    private TextView mTxtSug;

    private String spnSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpnSex = (Spinner) findViewById(R.id.spnSex);
        mRadGrp = (RadioGroup) findViewById(R.id.radGrpAge);
        mRadBtnAgeRange1 = (RadioButton) findViewById(R.id.ageRange1);
        mRadBtnAgeRange2 = (RadioButton) findViewById(R.id.ageRange2);
        mRadBtnAgeRange3 = (RadioButton) findViewById(R.id.ageRange3);
        mTxtNumFamily = (TextView) findViewById(R.id.FamilyMemberText);
        mNumPkrFamily = (NumberPicker) findViewById(R.id.NumberPicker);
        mNumPkrFamily.setMinValue(1);
        mNumPkrFamily.setMaxValue(20);
        mNumPkrFamily.setValue(3);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mSpnSex.setOnItemSelectedListener(spnOnItemSelect);
        mNumPkrFamily.setOnValueChangedListener(numPkrFamilyOnValueChange);
        mBtnOK.setOnClickListener(btnOkOnClick);
    }


    private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spnSelected = parent.getSelectedItem().toString();
            if (spnSelected==getString(R.string.male)) {
                mRadBtnAgeRange1.setText(getString(R.string.below30));
                mRadBtnAgeRange2.setText(getString(R.string.between30to40));
                mRadBtnAgeRange3.setText(getString(R.string.over40));
            }
            else if (spnSelected==getString(R.string.female)){
                mRadBtnAgeRange1.setText(getString(R.string.below28));
                mRadBtnAgeRange2.setText(getString(R.string.between28to35));
                mRadBtnAgeRange3.setText(getString(R.string.over35));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private NumberPicker.OnValueChangeListener numPkrFamilyOnValueChange = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mTxtNumFamily.setText(String.valueOf(newVal));
        }
    };

    private View.OnClickListener btnOkOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MarriageSuggestion ms = new MarriageSuggestion();
            String strSex = mSpnSex.getSelectedItem().toString();
            int iAgeRange = 0;
            if (mRadGrp.getCheckedRadioButtonId()== R.id.ageRange1) iAgeRange = 1;
            else if (mRadGrp.getCheckedRadioButtonId()== R.id.ageRange2) iAgeRange = 2;
            else if (mRadGrp.getCheckedRadioButtonId()== R.id.ageRange3) iAgeRange = 3;
            else Toast.makeText(v.getContext(), getString(R.string.input_error_age), Toast.LENGTH_SHORT).show();
            mTxtSug.setText(ms.getSuggestion(strSex, iAgeRange, mNumPkrFamily.getValue()));
        }
    };

}
