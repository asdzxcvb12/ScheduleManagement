package schedule.project.schedule.horizontal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ghangsub on 2017-04-24.
 */

public class HoAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private Context mContext;

    public HoAdapter(FragmentManager fm, int NumOfTabs, Context mContext) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                FmCalendar cal = new FmCalendar(mContext);
                return cal;
            case 1:
                FmCount cnt = new FmCount(mContext);
                return cnt;
            case 2:
                FmSchedule sch = new FmSchedule(mContext);
                return sch;
            case 3:
                FmSetting set = new FmSetting(mContext);
                return set;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}