package com.example.app.five_six_twentyfive.six;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app.R;
import com.example.app.five_six_twentyfive.CoFragment;
import com.example.app.five_six_twentyfive.DLZT_Fragment;
import com.example.app.five_six_twentyfive.GzFragment;
import com.example.app.five_six_twentyfive.Main;
import com.example.app.five_six_twentyfive.PmFragment;
import com.example.app.five_six_twentyfive.SdFragment;
import com.example.app.five_six_twentyfive.WdFragment;
import com.example.app.five_six_twentyfive.five.HJZBActivity;
import com.example.app.five_six_twentyfive.twentyfive.LKCXActivity;
import com.example.app.thirty_seven.ewm_activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 第六题：实时显示
 */
public class SSXSActivity extends AppCompatActivity implements View.OnClickListener{

    private Button toWD,toSD,toGZ,toCO2,toPM,toDL;
    private ViewPager vp;
    private WdFragment oneFragment;
    private SdFragment twoFragment;
    private GzFragment threeFragment;
    private CoFragment fouthFragmen;
    private PmFragment fiveFragmen;
    private DLZT_Fragment sixFragmen;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssxs);
        // 初始化布局
        initViews();

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(6);//ViewPager的缓存为6帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧

        //ViewPager的监听事件
        //setOnPageChangeListener
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeBgColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0==1的时候默示正在滑动，
                arg0==2的时候默示滑动完毕了，
                arg0==0的时候默示什么都没做。*/
            }
        });
    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        toWD =  findViewById(R.id.toWD);
        toSD =  findViewById(R.id.toSD);
        toGZ =  findViewById(R.id.toGZ);
        toCO2 =  findViewById(R.id.toCO2);
        toPM =  findViewById(R.id.toPM);
        toDL =  findViewById(R.id.toDL);

        toWD.setOnClickListener(this);
        toSD.setOnClickListener(this);
        toGZ.setOnClickListener(this);
        toCO2.setOnClickListener(this);
        toPM.setOnClickListener(this);
        toDL.setOnClickListener(this);

        vp = findViewById(R.id.mainViewPager);
        oneFragment = new WdFragment();
        twoFragment = new SdFragment();
        threeFragment = new GzFragment();
        fouthFragmen = new CoFragment();
        fiveFragmen = new PmFragment();
        sixFragmen = new DLZT_Fragment();

        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fouthFragmen);
        mFragmentList.add(fiveFragmen);
        mFragmentList.add(sixFragmen);
    }

    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toWD:
                vp.setCurrentItem(0, true);
                break;
            case R.id.toSD:
                vp.setCurrentItem(1, true);
                break;
            case R.id.toGZ:
                vp.setCurrentItem(2, true);
                break;
            case R.id.toCO2:
                vp.setCurrentItem(3, true);
                break;
            case R.id.toPM:
                vp.setCurrentItem(4, true);
                break;
            case R.id.toDL:
                vp.setCurrentItem(5, true);
                break;
        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     *由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeBgColor(int position) {
        switch (position) {
            case 0:
                toWD.setBackgroundResource(R.drawable.btn_bg1);
                toSD.setBackgroundResource(R.drawable.btn_bg);
                toGZ.setBackgroundResource(R.drawable.btn_bg);
                toCO2.setBackgroundResource(R.drawable.btn_bg);
                toPM.setBackgroundResource(R.drawable.btn_bg);
                toDL.setBackgroundResource(R.drawable.btn_bg);
                break;
            case 1:
                toWD.setBackgroundResource(R.drawable.btn_bg);
                toSD.setBackgroundResource(R.drawable.btn_bg1);
                toGZ.setBackgroundResource(R.drawable.btn_bg);
                toCO2.setBackgroundResource(R.drawable.btn_bg);
                toPM.setBackgroundResource(R.drawable.btn_bg);
                toDL.setBackgroundResource(R.drawable.btn_bg);
                break;
            case 2:
                toWD.setBackgroundResource(R.drawable.btn_bg);
                toSD.setBackgroundResource(R.drawable.btn_bg);
                toGZ.setBackgroundResource(R.drawable.btn_bg1);
                toCO2.setBackgroundResource(R.drawable.btn_bg);
                toPM.setBackgroundResource(R.drawable.btn_bg);
                toDL.setBackgroundResource(R.drawable.btn_bg);
                break;
            case 3:
                toWD.setBackgroundResource(R.drawable.btn_bg);
                toSD.setBackgroundResource(R.drawable.btn_bg);
                toGZ.setBackgroundResource(R.drawable.btn_bg);
                toCO2.setBackgroundResource(R.drawable.btn_bg1);
                toPM.setBackgroundResource(R.drawable.btn_bg);
                toDL.setBackgroundResource(R.drawable.btn_bg);
                break;
            case 4:
                toWD.setBackgroundResource(R.drawable.btn_bg);
                toSD.setBackgroundResource(R.drawable.btn_bg);
                toGZ.setBackgroundResource(R.drawable.btn_bg);
                toCO2.setBackgroundResource(R.drawable.btn_bg);
                toPM.setBackgroundResource(R.drawable.btn_bg1);
                toDL.setBackgroundResource(R.drawable.btn_bg);
                break;
            case 5:
                toWD.setBackgroundResource(R.drawable.btn_bg);
                toSD.setBackgroundResource(R.drawable.btn_bg);
                toGZ.setBackgroundResource(R.drawable.btn_bg);
                toCO2.setBackgroundResource(R.drawable.btn_bg);
                toPM.setBackgroundResource(R.drawable.btn_bg);
                toDL.setBackgroundResource(R.drawable.btn_bg1);
                break;
        }
    }

    public  void  menu_btn(View view){
        PopupMenu menubtn = new PopupMenu(this,view);
        // 这里的view代表popupMenu需要依附的view
        // 获取布局文件
        MenuInflater inflater = menubtn.getMenuInflater();
        // 实例化menu布局文件
        menubtn.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            /** 通过setOnMenuItemClickListener接口调用已有的menu布局 **/
            @Override
            public boolean onMenuItemClick(MenuItem item) {/** 控制item的点击事件 **/

                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.menu:
                        intent = new Intent(SSXSActivity.this, Main.class);// 页面跳转首页
                        break;
                    case R.id.hjzb:
                        intent = new Intent(SSXSActivity.this, HJZBActivity.class);// 该页面跳转环境指标页面
                        break;
                    case R.id.lkcx:
                        intent = new Intent(SSXSActivity.this, LKCXActivity.class);// 该页面跳转路况查询页面
                        break;
                    case R.id.ewm:
                        intent = new Intent(SSXSActivity.this, ewm_activity.class);// 该页面跳转二维码支付页面
                        break;
                }
                if (intent != null) {
                    SSXSActivity.this.startActivity(intent);
                    finish();
                }

                return true;
            }
        });
        inflater.inflate(R.menu.ssxs_menu,menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show(); // 显示控件
    }
}
