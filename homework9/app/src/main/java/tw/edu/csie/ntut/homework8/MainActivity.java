package tw.edu.csie.ntut.homework8;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT = Menu.FIRST + 3,
            MENU_EXIT = Menu.FIRST + 4;

    private EditText mEdtAmount;
    private Button mBtnInput,mBtnRecord;
    private Spinner mSpnPurchasedMeal;
    private TextView mTvPurchasedDate;
    private DatePicker mDatePicker,mSpnDatePicker;
    String Year,Mon,Day;
    private ArrayList<String> data;
    private LinearLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<String>();
        Calendar TodayDate = Calendar.getInstance();    //透過Calendar取的資料
        Year = Integer.toString(TodayDate.get(Calendar.YEAR));       //一開啟軟體即取得年的數值
        Mon  = Integer.toString(TodayDate.get(Calendar.MONTH) + 1);  //一開啟軟體即取得月的數值
        Day = Integer.toString(TodayDate.get(Calendar.DAY_OF_MONTH));//一開啟軟體即取得日的數值



        mRelativeLayout = (LinearLayout) findViewById(R.id.relativeLayout);
        registerForContextMenu(mRelativeLayout);
        mSpnPurchasedMeal = (Spinner) findViewById(R.id.purchasedMeal_spn);
        mTvPurchasedDate = (TextView) findViewById(R.id.purchasedDate_tv2);
        mTvPurchasedDate.setText( Year + "/" + Mon + "/" + Day );
        mEdtAmount = (EditText) findViewById(R.id.amount_edt) ;
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mSpnDatePicker = (DatePicker) findViewById(R.id.datePicker_spn);
        mBtnInput = (Button) findViewById(R.id.input_btn);
        mBtnRecord = (Button) findViewById(R.id.record_btn);


        mSpnPurchasedMeal.setOnItemSelectedListener(spnOnItemSelect);
        mBtnInput.setOnClickListener(btnOKOnClick);
        mBtnRecord.setOnClickListener(btnRecordOnClick);

        mDatePicker.init(TodayDate.get(Calendar.YEAR),TodayDate.get(Calendar.MONTH)
                ,TodayDate.get(Calendar.DAY_OF_MONTH),
                //DatePicker年月日更改後，會觸發作以下的事情。
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                        Year = Integer.toString(year);
                        Mon  = Integer.toString(monthOfYear + 1);  //月的初始是0，所以先加 1。
                        Day = Integer.toString(dayOfMonth);
                        mSpnDatePicker.updateDate(year, monthOfYear, dayOfMonth);
                        mTvPurchasedDate.setText( Year + "/"+Mon+"/"+Day);
                    }
                });

        mSpnDatePicker.init(TodayDate.get(Calendar.YEAR),TodayDate.get(Calendar.MONTH)
                ,TodayDate.get(Calendar.DAY_OF_MONTH),
                //DatePicker年月日更改後，會觸發作以下的事情。
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                        Year = Integer.toString(year);
                        Mon  = Integer.toString(monthOfYear + 1);  //月的初始是0，所以先加 1。
                        Day = Integer.toString(dayOfMonth);
                        mDatePicker.updateDate(year, monthOfYear, dayOfMonth);
                        mTvPurchasedDate.setText( Year + "/"+Mon+"/"+Day);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂")
                .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
        subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
        menu.add(0, MENU_ABOUT, 1, "關於這個程式...")
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, MENU_EXIT, 2, "結束")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_PLAY_MUSIC:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                return true;
            case MENU_STOP_PLAYING_MUSIC:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();

                return true;
            case MENU_EXIT:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String purchaseMeal = mSpnPurchasedMeal.getSelectedItem().toString();
            String amount = mEdtAmount.getText().toString();
            try {
                //如果amount是int就做toast並且記錄
                int num =  Integer.parseInt(amount);
                Toast.makeText(view.getContext(), amount, Toast.LENGTH_SHORT).show();
                int list = data.size()+1;
                data.add(getString(R.string.item) + list + "         " + mTvPurchasedDate.getText() + "       " + mSpnPurchasedMeal.getSelectedItem().toString() + "       " + amount);
            }
            catch (NumberFormatException e)
            {
                //否則輸出錯誤
                Toast.makeText(view.getContext(),   getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener btnRecordOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, SubActivity.class);

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("DATA", data);
            it.putExtras(bundle);

            startActivity(it);
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == mRelativeLayout) {
            if (menu.size() == 0) {
                SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂");
                subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
                subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
                menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
                menu.add(0, MENU_EXIT, 2, "結束");
            }
        }
    }

    private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
