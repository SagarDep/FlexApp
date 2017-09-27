package jp.co.flexapp.presentation.activity;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import jp.co.flexapp.R;
import jp.co.flexapp.common.enums.TabType;
import jp.co.flexapp.common.util.TwitterUtils;
import jp.co.flexapp.infla.db.FlexDatabase;
import jp.co.flexapp.presentation.fragment.BasePageFragment;
import jp.co.flexapp.presentation.fragment.FbPageFragment;
import jp.co.flexapp.presentation.fragment.InstaPageFragment;
import jp.co.flexapp.presentation.fragment.TwitterPageFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TwitterPageFragment.OnFragmentInteractionListener, InstaPageFragment.OnFragmentInteractionListener,
        FbPageFragment.OnFragmentInteractionListener, BasePageFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoomDatabase database = Room.databaseBuilder(getApplicationContext(), FlexDatabase.class, "FLEX_DB").build();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);
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

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);

        if (!TwitterUtils.hasAccessToken(this)) {
            Intent intent = new Intent(getApplication(), TwitterOAuthActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        int tabType = getIntent().getIntExtra("TAB_TYPE", 99);
        switch (tabType) {
            case 0:
                tabLayout.getTabAt(TabType.TWITTER.getValue()).select();
                break;
            case 1:
                tabLayout.getTabAt(TabType.FACEBOOK.getValue()).select();
                break;
            case 2:
                tabLayout.getTabAt(TabType.INSTAGRAM.getValue()).select();
                break;
            default:
                break;
        }
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

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
