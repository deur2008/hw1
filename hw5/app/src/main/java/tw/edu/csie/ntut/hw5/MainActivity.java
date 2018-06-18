package tw.edu.csie.ntut.hw5;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;
    private ScaleAnimation scaleAnimationOut,scaleAnimationIn;
    private TranslateAnimation translateAnimationOut,translateAnimationIn;
    private RotateAnimation rotateAnimationOut,rotateAnimationIn;
    protected AlphaAnimation alphaAnimationOut,alphaAnimationIn;

    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,R.drawable.img04,
            R.drawable.img05, R.drawable.img06, R.drawable.img07,R.drawable.img08,
            R.drawable.img09, R.drawable.img10, R.drawable.img11,R.drawable.img12};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,R.drawable.img04th,
            R.drawable.img05th, R.drawable.img06th, R.drawable.img07th, R.drawable.img08th,
            R.drawable.img09th, R.drawable.img10th, R.drawable.img11th, R.drawable.img12th};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scaleAnimationOut = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimationOut.setInterpolator(new LinearInterpolator());
        scaleAnimationOut.setStartOffset(2000);
        scaleAnimationOut.setDuration(2000);

        scaleAnimationIn = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimationIn.setInterpolator(new LinearInterpolator());
        scaleAnimationIn.setStartOffset(2000);
        scaleAnimationIn.setDuration(2000);

        translateAnimationOut = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,300);
        translateAnimationOut.setInterpolator(new LinearInterpolator());
        translateAnimationOut.setStartOffset(2000);
        translateAnimationOut.setDuration(2000);

        translateAnimationIn = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,-300);
        translateAnimationIn.setInterpolator(new LinearInterpolator());
        translateAnimationIn.setStartOffset(2000);
        translateAnimationIn.setDuration(2000);

        rotateAnimationOut= new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimationOut.setInterpolator(new LinearInterpolator());
        rotateAnimationOut.setStartOffset(2000);
        rotateAnimationOut.setDuration(2000);

        rotateAnimationIn= new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimationIn.setInterpolator(new LinearInterpolator());
        rotateAnimationIn.setStartOffset(2000);
        rotateAnimationIn.setDuration(2000);

        alphaAnimationOut= new AlphaAnimation(0,1);
        alphaAnimationOut.setInterpolator(new LinearInterpolator());
        alphaAnimationOut.setStartOffset(2000);
        alphaAnimationOut.setDuration(2000);

        alphaAnimationIn= new AlphaAnimation(1,0);
        alphaAnimationIn.setInterpolator(new LinearInterpolator());
        alphaAnimationIn.setStartOffset(2000);
        alphaAnimationIn.setDuration(2000);

        mImgSwitcher =  findViewById(R.id.imgSwitcher);
        mImgSwitcher.setFactory(this);
        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        mGridView = findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOnItemClick);
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF00FF00);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.BLACK);
        return v;
    }


    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {
                    switch ((int)(Math.random()*8 + 1)) {
                        case 1:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_out));
                            break;
                        case 2:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_out));
                            break;
                        case 3:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_out));
                            break;
                        case 4:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_out));
                            break;
                        case 5:
                            mImgSwitcher.setInAnimation(translateAnimationIn);
                            mImgSwitcher.setOutAnimation(translateAnimationOut);
                            break;
                        case 6:
                            mImgSwitcher.setInAnimation(scaleAnimationIn);
                            mImgSwitcher.setOutAnimation(scaleAnimationOut);
                            break;
                        case 7:
                            mImgSwitcher.setInAnimation(rotateAnimationIn);
                            mImgSwitcher.setOutAnimation(rotateAnimationOut);
                            break;
                        case 8:
                            AnimationSet animationSet = new AnimationSet(false);
                            animationSet.addAnimation(scaleAnimationIn);
                            animationSet.addAnimation(rotateAnimationIn);
                            animationSet.addAnimation(translateAnimationIn);
                            mImgSwitcher.setInAnimation(animationSet);

                            AnimationSet animationSet1 =new AnimationSet(false);
                            animationSet1.addAnimation(scaleAnimationOut);
                            animationSet1.addAnimation(rotateAnimationOut);
                            animationSet1.addAnimation(translateAnimationOut);
                            mImgSwitcher.setOutAnimation(animationSet1);
                            break;
                    }

                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };
}
