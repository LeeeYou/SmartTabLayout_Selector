package com.example.leeeyou.smarttablayout_selector;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

/**
 * https://github.com/ogaclejapan/SmartTabLayout/issues/5
 */
public class VenueActivity extends AppCompatActivity {

    ArrayList<String> mCityList;
    ArrayList<String> mSportList;
    ArrayList<String> mSortList;

    Bundle bundle_cityData;
    Bundle bundle_sportData;
    Bundle bundle_sortData;

    ViewPager mViewPager;
    SmartTabLayout mSmartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        initData();

        initUI();
    }

    private void initUI() {
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_icon_and_text, tab, false));

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mSmartTabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems
                        .with(this)
                        .add("全城", VenueFragment.class, bundle_cityData)
                        .add("羽毛球", VenueFragment.class, bundle_sportData)
                        .add("智能排序", VenueFragment.class, bundle_sortData)
                        .create()
        );

        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPager);

        mSmartTabLayout.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                showViewPager();
            }
        });
    }

    private void initData() {
        //you can get data from your server or internet
        mCityList = new ArrayList<>();
        mSportList = new ArrayList<>();
        mSortList = new ArrayList<>();

        mCityList.add("南山区");
        mCityList.add("罗湖区");
        mCityList.add("宝安区");
        mCityList.add("龙岗区");
        mCityList.add("福田区");

        mSportList.add("足球");
        mSportList.add("篮球");
        mSportList.add("网球");
        mSportList.add("羽毛球");
        mSportList.add("乒乓球");

        mSortList.add("智能排序");
        mSortList.add("价格最少");
        mSortList.add("人气最高");
        mSortList.add("离我最近");

        bundle_cityData = new Bundle();
        bundle_cityData.putStringArrayList("data", mCityList);

        bundle_sportData = new Bundle();
        bundle_sportData.putStringArrayList("data", mSportList);

        bundle_sortData = new Bundle();
        bundle_sortData.putStringArrayList("data", mSortList);
    }

    public void hideViewPager() {
        if (mViewPager != null) {
            mViewPager.setVisibility(View.GONE);
        }
    }

    public void showViewPager() {
        if (mViewPager != null) {
            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    public SmartTabLayout getSmartTabLayout() {
        return mSmartTabLayout;
    }

}
