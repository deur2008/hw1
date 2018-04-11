package edu.csie.ntut.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button mBtnOK;
    private TextView mTxtSug, mTxtHob;
    private RadioGroup mRadiogroup;
    private Spinner mSpnMale, mSpnFemale;
    private String selectedAge;

    private CheckBox[] mycheckbox = new CheckBox[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtHob = (TextView) findViewById(R.id.txtHobby);
        mSpnMale = (Spinner) findViewById(R.id.maleSpinner);
        mSpnFemale = (Spinner) findViewById(R.id.femaleSpinner);
        mRadiogroup = (RadioGroup) findViewById(R.id.radGrp);

        mycheckbox[0] = (CheckBox) findViewById(R.id.checkBoxMusic);
        mycheckbox[1] = (CheckBox) findViewById(R.id.checkBoxSing);
        mycheckbox[2] = (CheckBox) findViewById(R.id.checkBoxDance);
        mycheckbox[3] = (CheckBox) findViewById(R.id.checkBoxTravel);
        mycheckbox[4] = (CheckBox) findViewById(R.id.checkBoxReading);
        mycheckbox[5] = (CheckBox) findViewById(R.id.checkBoxWriting);
        mycheckbox[6] = (CheckBox) findViewById(R.id.checkBoxClimbing);
        mycheckbox[7] = (CheckBox) findViewById(R.id.checkBoxSwim);
        mycheckbox[8] = (CheckBox) findViewById(R.id.checkBoxFishing);
        mycheckbox[9] = (CheckBox) findViewById(R.id.checkBoxJogging);
        mycheckbox[10] = (CheckBox) findViewById(R.id.checkBoxPhoto);
        mycheckbox[11] = (CheckBox) findViewById(R.id.checkBoxPhoto);
        mycheckbox[12] = (CheckBox) findViewById(R.id.checkBoxEating);
        mycheckbox[13] = (CheckBox) findViewById(R.id.checkBoxDrawing);

        mSpnMale.setOnItemSelectedListener(spnOnItemSelect);
        mSpnFemale.setOnItemSelectedListener(spnOnItemSelect);
        mBtnOK.setOnClickListener(btnOKOnClick);
    }

    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MarriageSuggestion ms = new MarriageSuggestion();
            String hob = getString(R.string.my_hobby);
            String sex = new String();
            int age = 0;

            switch (mRadiogroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonMale:
                    age = mSpnMale.getSelectedItemPosition()+1;
                    sex = getString(R.string.male);
                    break;
                case R.id.radioButtonFemale:
                    age = mSpnFemale.getSelectedItemPosition()+1;
                    sex = getString(R.string.female);
                    break;
            }

            for(int i=0;i<14;i++)
                if (mycheckbox[i].isChecked()) hob += (mycheckbox[i].getText().toString() + " ");

            mTxtSug.setText(ms.getSuggestion(sex, age));
            mTxtHob.setText(hob);
        }
    };

    private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            selectedAge = adapterView.getSelectedItem().toString();
            switch (selectedAge) {
                case "maleAgeRange1":
                    break;
                case "maleAgeRange2":
                    break;
                case "maleAgeRange3":
                    break;
                case "femaleAgeRange1":
                    break;
                case "femaleAgeRange2":
                    break;
                case "femaleAgeRange3":
                    break;

            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    };
}
