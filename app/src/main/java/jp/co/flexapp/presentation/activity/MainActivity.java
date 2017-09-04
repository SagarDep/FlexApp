package jp.co.flexapp.presentation.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import jp.co.flexapp.R;
import jp.co.flexapp.presentation.fragment.FbPageFragment;
import jp.co.flexapp.presentation.fragment.InstaPageFragment;
import jp.co.flexapp.presentation.fragment.TwitterPageFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TwitterPageFragment.OnFragmentInteractionListener, InstaPageFragment.OnFragmentInteractionListener, FbPageFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  ListView listView = (ListView) findViewById(R.id.listView);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final String[] pageTitle = {"Twitter", "Facebook", "Instagram"};

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return pageTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public Fragment getItem(int position) {
                Fragment resFragment = null;
                switch (position) {
                    case 0:
                        resFragment = TwitterPageFragment.newInstance("", null);
                        break;
                    case 1:
                        resFragment = FbPageFragment.newInstance("", null);
                        break;
                    case 2:
                        resFragment = InstaPageFragment.newInstance("", null);
                        break;
                    default:
                        resFragment = TwitterPageFragment.newInstance("", null);
                }
                return resFragment;
            }
        };

//        ArrayList<Tweet> list = new ArrayList<>();
//        Tweet tweet = new Tweet(1, R.drawable.sample_thumb, "hoge", "hogehogehoge");
//        list.add(tweet);
//        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
