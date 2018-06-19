package tw.edu.csie.ntut.hw7;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainFragment extends Fragment {

    public enum GameResultType {
        TYPE_1, HIDE
    }

    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface CallbackInterface {
        void updateGameResult(int iCountSet,
                              int iCountPlayerWin,
                              int iCountComWin,
                              int iCountDraw);
        void enableGameResult(GameResultType type);
    }

    private CallbackInterface mCallback;
    public final StaticHandler mHandler = new StaticHandler(this);

    private static int iRand;
    private static TextView mTxtResult;
    private ImageView mImgRollingDice;
    private TextView mTxtDiceResult;

    // 新增統計遊戲局數和輸贏的變數
    private static int miCountSet = 0;
    private static int miCountPlayerWin = 0;
    private static int miCountComWin = 0;
    private static int miCountDraw = 0;

    private Button mBtnShowResult,
            mBtnRollDice,
            mBtnHiddenResult,
            mBtnJump;

    // Use static Handler to avoid memory leaks.
    private static class StaticHandler extends Handler {
        private final WeakReference<MainFragment> mFragment;

        public StaticHandler(MainFragment fragment) {
            mFragment = new WeakReference<MainFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            MainFragment activity = mFragment.get();
            if (activity == null) return;

            iRand = (int)(Math.random()*6 + 1);

            String s = activity.getString(R.string.dice_result);
            activity.mTxtDiceResult.setText(s + iRand);

            miCountSet++;
            if (iRand>4){
                mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_lose));
                miCountComWin++;
            }
            else if(iRand<3){
                mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_win));
                miCountPlayerWin++;
            }
            else {
                mTxtResult.setText(activity.getString(R.string.result) +
                        activity.getString(R.string.player_draw));
                miCountDraw++;
            }

            switch (iRand) {
                case 1:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice01);
                    break;
                case 2:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice02);
                    break;
                case 3:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice03);
                    break;
                case 4:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice04);
                    break;
                case 5:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice05);
                    break;
                case 6:
                    activity.mImgRollingDice.setImageResource(R.drawable.dice06);
                    break;
            }
        }
    }

    public MainFragment() {
        // Required empty public constructor
    }

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
        mTxtResult =  getView().findViewById(R.id.txtResult);

        mBtnRollDice = getView().findViewById(R.id.btnRollDice);
        mBtnShowResult =  getView().findViewById(R.id.btnShowResult);
        mBtnHiddenResult =  getView().findViewById(R.id.btnHiddenResult);
        mBtnJump=getView().findViewById(R.id.btnJump);
        mImgRollingDice = getView().findViewById(R.id.imgRollingDice);
        mTxtDiceResult = getView().findViewById(R.id.txtDiceResult);

        mBtnRollDice.setOnClickListener(btnRollDiceOnClick);
        mBtnShowResult.setOnClickListener(btnShowResultOnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);
        mBtnJump.setOnClickListener(btnJumpOnClick);
    }

    private View.OnClickListener btnRollDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {

            String s = getString(R.string.dice_result);
            mTxtDiceResult.setText(s);

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
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();

            // 計時的另一種作法。
//            mHandler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    animDraw.stop();
//                    mHandler.sendMessage(mHandler.obtainMessage());
//                }
//            }, 5000);

            mCallback.updateGameResult(miCountSet, miCountPlayerWin,
                    miCountComWin, miCountDraw);
        }
    };

    private  View.OnClickListener btnJumpOnClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it =new Intent();
            it.setClass(getActivity() , MainActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("KEY_miCountSet",miCountSet);
            bundle.putInt("KEY_miCountPlayerWin",miCountPlayerWin);
            bundle.putInt("KEY_miCountComWin",miCountComWin);
            bundle.putInt("KEY_miCountDraw",miCountDraw );
            it.putExtras(bundle);

            startActivity(it);
        }
    };



    private View.OnClickListener btnShowResultOnClick =  new View.OnClickListener() {
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
