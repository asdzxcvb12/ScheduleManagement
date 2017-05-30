package schedule.project.schedule.horizontal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import schedule.project.schedule.R;
import schedule.project.schedule.horizontal.vertical_cal.GetDate;

/**
 * Created by ghangsub on 2017-04-24.
 */

public class HorizonMain extends AppCompatActivity{
    private final int mNumOfPage = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("달력").setIcon(R.drawable.sunny));
        tabLayout.addTab(tabLayout.newTab().setText("일정관리").setIcon(R.drawable.cloudy));
        tabLayout.addTab(tabLayout.newTab().setText("가계부").setIcon(R.drawable.rainy));
        tabLayout.addTab(tabLayout.newTab().setText("설정").setIcon(R.drawable.snowy));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.horizon_pager);
        final HoAdapter adapter = new HoAdapter
                (getSupportFragmentManager(), mNumOfPage, getApplicationContext());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //if(tab.getPosition() == 3)
                //tabLayout.getTabAt(tab.getPosition()).setIcon(R.drawable.snowy);
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}

            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });


    }
}
