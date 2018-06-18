package tw.edu.csie.ntut.hw42;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mBtnScissors, mBtnStone, mBtnPaper;
    private TextView mTxtComPlay, mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = findViewById(R.id.txtComPlay);
        mTxtResult = findViewById(R.id.txtResult);
        mBtnScissors = findViewById(R.id.btnScissors);
        mBtnStone = findViewById(R.id.btnStone);
        mBtnPaper = findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);

    }

    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            AiPlay aiPlay= new AiPlay();
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            int mylar = 1;
            mTxtComPlay.setText(aiPlay.testPlayA(iComPlay));
            mTxtResult.setText(aiPlay.testPlayB(iComPlay, mylar));
        }
    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            AiPlay aiPlay= new AiPlay();
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            int mylar = 2;
            mTxtComPlay.setText(aiPlay.testPlayA(iComPlay));
            mTxtResult.setText(aiPlay.testPlayB(iComPlay, mylar));
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            AiPlay aiPlay= new AiPlay();
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            int mylar = 3;
            mTxtComPlay.setText(aiPlay.testPlayA(iComPlay));
            mTxtResult.setText(aiPlay.testPlayB(iComPlay, mylar));
        }
    };
}
