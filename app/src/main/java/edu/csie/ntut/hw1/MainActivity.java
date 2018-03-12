package edu.csie.ntut.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtSex, mEdtAge;
    private TextView mTxtR;
    private Button mBtnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtSex = (EditText) findViewById(R.id.edtSex);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mTxtR = (TextView) findViewById(R.id.txtR);
        mBtnOK = (Button) findViewById(R.id.btnOK);

        mBtnOK.setOnClickListener(btnOkOnClick);

    }

    String strSug = getString(R.string.suggestion);

    private View.OnClickListener btnOkOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strSex = mEdtSex.getText().toString();
            String male = getString(R.string.male);
            String female = getString(R.string.female);

            if (strSex.equals(male))
                AgeRange(30, 35);
            else if (strSex.equals(female))
                AgeRange(28, 32);
            else
                strSug += getString(R.string.input_error);
            mTxtR.setText(strSug);
        }
    };

    void AgeRange(int not_hurry, int get_married) {
        int inputAge = Integer.parseInt(mEdtAge.getText().toString());

        if (inputAge < not_hurry)
            strSug += getString(R.string.not_hurry);
        else if (inputAge > get_married)
            strSug += getString(R.string.get_married);
        else
            strSug += getString(R.string.find_couple);

    }
}
