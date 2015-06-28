package com.kyanro.materialtabhostsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindString(R.string.hello_world)
    String HELLO_WORLD;

    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.pager)
    ViewPager mViewPager;

    @Bind(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mText.setText(HELLO_WORLD);

        TmpFragmentPagerAdapter adapter = new TmpFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(R.layout.activity_main_tab);
        }

        mViewPager.setCurrentItem(3);
    }

    private static class TmpFragmentPagerAdapter extends FragmentPagerAdapter {

        public TmpFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "tab :" + String.valueOf(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TmpFragment();
            return f;
        }

        @Override
        public int getCount() {
            return 10;
        }
    }


    public static class TmpFragment extends Fragment {
        @BindString(R.string.inner_fragment_string)
        String INNER_FRAGMENT_STRING;

        @Bind(R.id.inner_text)
        TextView innerText;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tmp_fragment, container, false);

            ButterKnife.bind(this, view);
            innerText.setText(INNER_FRAGMENT_STRING);

            return view;
        }

        @Override
        public void onDestroyView() {
            ButterKnife.unbind(this);
            super.onDestroyView();
        }
    }

}
