package tw.edu.csie.ntut.hw6_103590054;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainFragment extends Fragment {

    public enum GameResultType {
        TYPE_1, HIDE
    }

    public interface CallbackInterface {
        public void updateGameResult(int iCountSet,int iCountPlayerWin,int iCountComWin,int iCountDraw);
        public void enableGameResult(GameResultType type);
    };

    private CallbackInterface mCallback;

    private ImageButton mImgBtnDice;
    private ImageView mImgRollingDice;
    private TextView mTxtResult;

    private int miCountSet = 0;
    private int miCountPlayerWin = 0;
    private int miCountComWin = 0;
    private int miCountDraw = 0;

    private Button mBtnShowResult1;
    private Button mBtnHiddenResult;

    private boolean mbShowResult = false;

    public MainFragment() {
    }

    private static class StaticHandler extends Handler {
        private final WeakReference<MainFragment> mActivity;

        public StaticHandler(MainFragment activity) {
            mActivity = new WeakReference<MainFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainFragment activity = mActivity.get();
            if (activity == null) return;

            int iRand = (int)(Math.random()*6 + 1);

            String s = activity.getString(R.string.result);
            switch (iRand) {
                case 1:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice01);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_win));
                    activity.miCountPlayerWin++;
                    break;
                case 2:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice02);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_win));
                    activity.miCountPlayerWin++;
                    break;
                case 3:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice03);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_draw));
                    activity.miCountDraw++;
                    break;
                case 4:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice04);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_draw));
                    activity.miCountDraw++;
                    break;
                case 5:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice05);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_lose));
                    activity.miCountComWin++;
                    break;
                case 6:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice06);
                    activity.mTxtResult.setText(s + activity.getString(R.string.player_lose));
                    activity.miCountComWin++;
                    break;
            }

        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement GameFragment.CallbackInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用inflater物件的inflate()方法取得介面佈局檔，並將最後的結果傳回給系統
       return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 必須先呼叫getView()取得程式畫面物件，然後才能呼叫它的
        // findViewById()取得介面物件
        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);
//        mImgBtnScissors = (ImageButton) getView().findViewById(R.id.imgBtnScissors);
        mImgBtnDice = (ImageButton) getView().findViewById(R.id.imgBtnDice);
        mImgRollingDice = (ImageView) getView().findViewById(R.id.imgViewComPlay);

        // 以下介面元件是在另一個Fragment中，因此必須呼叫所屬的Activity
        // 才能取得這些介面元件
/*
        mEdtCountSet = (EditText) getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getActivity().findViewById(R.id.edtCountDraw);
*/

//        mImgBtnScissors.setOnClickListener(imgBtnScissorsOnClick);
//        mImgBtnStone.setOnClickListener(imgBtnStoneOnClick);
        mImgBtnDice.setOnClickListener(imgBtnDiceOnClick);

        mBtnShowResult1 = (Button) getView().findViewById(R.id.btnShowResult1);
//        mBtnShowResult2 = (Button) getView().findViewById(R.id.btnShowResult2);
        mBtnHiddenResult = (Button) getView().findViewById(R.id.btnHiddenResult);

        mBtnShowResult1.setOnClickListener(btnShowResult1OnClick);
//        mBtnShowResult2.setOnClickListener(btnShowResult2OnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);
    }

    private View.OnClickListener imgBtnDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 從程式資源中取得動畫檔，設定給ImageView物件，然後開始播放。
            Resources res = getResources();
            final AnimationDrawable animDraw =
                    (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mImgRollingDice.setImageDrawable(animDraw);
            animDraw.start();

            // 啟動background thread進行計時。
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();
            int iComPlay = (int)(Math.random()*6 + 1);

            miCountSet++;
//            mEdtCountSet.setText(String.valueOf(miCountSet));
//
//            // 1 - scissors, 2 - stone, 3 - net.
//            if (iComPlay == 1) {
//                mImgRollingDice.setImageResource(R.drawable.dice01);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_win));
//                miCountPlayerWin++;
////                mEdtCountComWin.setText(String.valueOf(miCountComWin));
//            }if (iComPlay == 2) {
//                mImgRollingDice.setImageResource(R.drawable.dice02);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_win));
//                miCountPlayerWin++;
////                mEdtCountComWin.setText(String.valueOf(miCountComWin));
//            }
//            else if (iComPlay == 3) {
//                mImgRollingDice.setImageResource(R.drawable.dice03);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_draw));
//                miCountDraw++;
////                mEdtCountDraw.setText(String.valueOf(miCountDraw));
//            }
//            else if (iComPlay == 4) {
//                mImgRollingDice.setImageResource(R.drawable.dice04);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_draw));
//                miCountDraw++;
////                mEdtCountDraw.setText(String.valueOf(miCountDraw));
//            }
//            else if (iComPlay == 5) {
//                mImgRollingDice.setImageResource(R.drawable.dice05);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_lose));
//                miCountComWin++;
////                mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
//            }
//            else {
//                mImgRollingDice.setImageResource(R.drawable.dice06);
//                mTxtResult.setText(getString(R.string.result) +
//                        getString(R.string.player_lose));
//                miCountComWin++;
////                mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
//            }
//
            mCallback.updateGameResult(miCountSet, miCountPlayerWin,
                    miCountComWin, miCountDraw);

        }

    };

    private View.OnClickListener btnShowResult1OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_1);
        }
    };

    private View.OnClickListener btnHiddenResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.HIDE);
        }
    };

}
