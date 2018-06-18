package tw.edu.csie.ntut.hw5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;    // 儲存程式的執行環境。
    private Integer[] miImgArr; 	 // 儲存影像縮圖id陣列。

    public ImageAdapter(Context context, Integer[] imgArr) {
        mContext = context;
        miImgArr = imgArr;
    }

    @Override
    public int getCount() {
        return miImgArr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView v;

        if (convertView == null) {
            v = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            v.setLayoutParams(new GridView.LayoutParams(params));
            v.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            v.setPadding(5, 5, 5, 5);
        }
        else
            v = (ImageView) convertView;

        v.setImageResource(miImgArr[position]);

        return v;
    }
}
