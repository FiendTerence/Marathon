package com.example.terence.marathon;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.terence.fragment.Fragment_1;
import com.example.terence.fragment.Fragment_2;
import com.example.terence.fragment.Fragment_all;

import javax.xml.transform.OutputKeys;


public class MainActivity extends FragmentActivity {

    public static final String ARGUMENTS_NAME = "arg";
    public static String[] tabTitle={"All","起點(牌樓)","長春祠","燕子洞","九曲洞","天祥","終點"};
    private ImageView imageView;
    private ImageButton title_ib_right;
    private TextView title_text;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private int indicatorWidth;
    private LayoutInflater mInflater;
    private TabFragmentPagerAdapter mAdapter;
    private int currentIndicatorLeft = 0;
    private RelativeLayout rl;
    private HorizontalScrollView mHSV;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title_ib_right = (ImageButton)findViewById(R.id.title_ib_right);
        title_text = (TextView)findViewById(R.id.title_text);
        rl = (RelativeLayout) findViewById(R.id.rl_nav);
        mHSV = (HorizontalScrollView) findViewById(R.id.hsv);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        imageView = (ImageView) findViewById(R.id.image_view);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        setListener();
        initView();

        title_ib_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlertDialog(v);
            }
        });
    }

    private void ShowAlertDialog(View v){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_layout,(ViewGroup) findViewById(R.id.dialog));
        new AlertDialog.Builder(this)
                .setTitle("搜尋跑者")
                .setView(layout)
                .setPositiveButton("搜尋", null)
                .show();
        /*
        AlertDialog.Builder MyAlerDialog = new AlertDialog.Builder(this);
        MyAlerDialog.setTitle("我是標題").setMessage("123");
        MyAlerDialog.setMessage("我是內容");

        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                //如果不做任何事情 就會直接關閉 對話方塊
                }
            };;
        MyAlerDialog.setNeutralButton("中間按鈕",OkClick );

        MyAlerDialog.show();
        */
    }

    //監聽ViewPager和RadioGroup並進行相對顯示
    public void setListener(){

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {     //ViewPager監聽頁面滑動

            @Override
            public void onPageSelected(int position) {      //當滑動的頁面後（顯示完成），要執行的事項

                if(mRadioGroup!=null && mRadioGroup.getChildCount()>position){
                    ((RadioButton)mRadioGroup.getChildAt(position)).performClick();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {        //當滑動頁面時（滑動中），要執行的事項

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {        //頁面當前狀態，在滑動或停止滑動時執行的事項

            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {       //RadioGroup單選組監聽

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (mRadioGroup.getChildAt(checkedId) != null) {

                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft,
                            ((RadioButton) mRadioGroup.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);

                    imageView.startAnimation(animation);    //ImgeView 跟隨頁面一起換
                    mViewPager.setCurrentItem(checkedId);    //ViewPager  跟隨頁面一起切換

                    //紀錄當下標籤距最左側的距離
                    currentIndicatorLeft = ((RadioButton) mRadioGroup.getChildAt(checkedId)).getLeft();
                    //
                    mHSV.smoothScrollTo(
                            (checkedId > 1 ? ((RadioButton) mRadioGroup.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) mRadioGroup.getChildAt(2)).getLeft(), 0);
                }
            }
        });
    }


    public void initView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        indicatorWidth = dm.widthPixels / 4;    //設定一次顯示四個Tab的大小

        LayoutParams cursor_Params = imageView.getLayoutParams();
        cursor_Params.width = indicatorWidth;       // 初始化 滑動下標的寬
        imageView.setLayoutParams(cursor_Params);

        //取得布局填充器
        mInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);

        //建立Tab的view
        initNavigationHSV();

        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    //設定Tab上的View，先執行清除在建立
    private void initNavigationHSV() {

        mRadioGroup.removeAllViews();        //清除所有View

        //依照tabTitle所設定的陣列來建立我們的Tab項目
        for(int i=0;i<tabTitle.length;i++){
            System.out.println("tab:"+i);
            RadioButton rb = (RadioButton) mInflater.inflate(R.layout.radiogroup_item, null);
            rb.setId(i);
            rb.setText(tabTitle[i]);
            rb.setLayoutParams(new LayoutParams(indicatorWidth,
                    LayoutParams.MATCH_PARENT));

            mRadioGroup.addView(rb);
        }

    }

    //FragmentPager的Adapter，用來指定各個Tab的頁面內容
    public static class TabFragmentPagerAdapter extends FragmentPagerAdapter{

        public TabFragmentPagerAdapter(FragmentManager fm){
            super(fm);
        }
        //依照目前所指定到的fn來給予fn頁面
        @Override
        public Fragment getItem(int arg0){
            Fragment ft = null;
            switch (arg0){
                case 0:
                    ft = new Fragment_all();
                    break;
                case 1:
                    ft = new Fragment_1();
                    break;
                case 2:
                    ft = new Fragment_1();
                    break;
                default:        //其他的先暫用ft_2來呈現
                    ft = new Fragment_all();

                    Bundle args = new Bundle();
                    args.putString(ARGUMENTS_NAME, tabTitle[arg0]);
                    ft.setArguments(args);

                    break;
            }
            return ft;
        }

        @Override
        public int getCount(){
            return tabTitle.length;
        }

    }

}


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
