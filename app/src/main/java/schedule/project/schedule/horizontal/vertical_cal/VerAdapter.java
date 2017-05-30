package schedule.project.schedule.horizontal.vertical_cal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ghangsub on 2017-04-24.
 */

public class VerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public VerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        FmCal cal = new FmCal();
        Bundle args = new Bundle();

        args.putInt("position", position);

        cal.setArguments(args);

        return cal;
    }

    @Override
    public int getCount() {
        return mNumOfTabs * 3;
    }
}
