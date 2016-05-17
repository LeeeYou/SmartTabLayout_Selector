package com.example.leeeyou.smarttablayout_selector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.List;

public class VenueFragment extends Fragment {

    List<String> mCityList;

    int pagePosition;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_venue, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromParentActivity();
        initUI(view);
    }

    private void initUI(View view) {
        ListView listView_venue = (ListView) view.findViewById(R.id.listView_venue);
        listView_venue.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, mCityList));
        listView_venue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemView, int itemPosition, long id) {
                VenueActivity parentActivity = (VenueActivity) getActivity();
                parentActivity.hideViewPager();

                View tabAt = parentActivity.getSmartTabLayout().getTabAt(pagePosition);
                TextView customTextView = (TextView) tabAt.findViewById(R.id.custom_tab_text);
                customTextView.setText(mCityList.get(itemPosition));
            }
        });
    }

    private void getDataFromParentActivity() {
        pagePosition = FragmentPagerItem.getPosition(getArguments());
        mCityList = getArguments().getStringArrayList("data");
    }

}
