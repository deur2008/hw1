package edu.csie.ntut.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText mEdtSex, mEdtAge;
    private TextView mTxtR;
    private Button mBtnOK;

    private String strSug,printOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast toast = Toast.makeText(this, "Toast置中顯示", Toast.LENGTH_LONG);
        mEdtSex = (EditText) findViewById(R.id.edtSex);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mTxtR = (TextView) findViewById(R.id.txtR);
        mBtnOK = (Button) findViewById(R.id.btnOK);

        strSug = getString(R.string.suggestion);
        mBtnOK.setOnClickListener(btnOkOnClick);
    }


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
            {
                printOut = strSug;
                Toast.makeText(v.getContext(),  getString(R.string.input_error_sex), Toast.LENGTH_SHORT).show();
            }


            mTxtR.setText(printOut);
        }
    };

    private void AgeRange(int not_hurry, int get_married) {

        //int inputAge = Integer.parseInt(mEdtAge.getText().toString());
        String inputAge = mEdtAge.getText().toString();
        try {
            int num = Integer.parseInt(inputAge);

            if (num < not_hurry)
                printOut = strSug+getString(R.string.not_hurry);
            else if (num > get_married)
                printOut = strSug+getString(R.string.get_married);
            else
                printOut = strSug+getString(R.string.find_couple);
        }
        catch (NumberFormatException e)
        {
            printOut = strSug;
            Toast.makeText(this, getString(R.string.input_error_age), Toast.LENGTH_SHORT).show();
        }

    }
}
