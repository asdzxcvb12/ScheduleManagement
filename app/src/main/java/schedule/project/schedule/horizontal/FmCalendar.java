package schedule.project.schedule.horizontal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import schedule.project.schedule.R;
import schedule.project.schedule.horizontal.vertical_cal.FmCal;
import schedule.project.schedule.horizontal.vertical_cal.GetDate;
import schedule.project.schedule.horizontal.vertical_cal.VerAdapter;

/**
 * Created by ghangsub on 2017-04-24.
 */

@SuppressLint("ValidFragment")
public class FmCalendar extends Fragment {
    private Context mContext;
    private View view;

    private int mNumOfPage = 800, mMonth;
    private int currentPosition;
    private static int mYear=0, cMonth;

    private TextView yyyy;

    private GetDate getDate = new GetDate();

    public FmCalendar (Context mContext) {
        this.mContext = mContext;
    }

    public void onCreate(Bundle saveInstanceSate) {
        super.onCreate(saveInstanceSate);
        if(mYear == 0)  {
            mYear = getDate.getYMD(0);
            mMonth = getDate.getYMD(1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_calendar, container, false);

        yyyy = (TextView) view.findViewById(R.id.yyyy);
        currentPosition = mNumOfPage + mNumOfPage/2 + (mMonth-1);

        final VerticalViewPager viewPager = (VerticalViewPager) view.findViewById(R.id.vertical_pager);
        final VerAdapter adapter = new VerAdapter(getChildFragmentManager(), mNumOfPage);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(currentPosition);
        viewPager.setVerticalScrollbarPosition(currentPosition);

        yyyy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(mContext, "현재..\n"+mYear+"年 "+mMonth+"月 "+getDate.getYMD(2)+"日\nPosition : " + currentPosition, Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position < mNumOfPage)
                    viewPager.setCurrentItem(position + mNumOfPage, false);
                else if(position >= mNumOfPage*2)
                    viewPager.setCurrentItem(position - mNumOfPage, false);
            }

            @Override
            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
                mMonth = (position % 12) + 1;

                if((cMonth == 10 && mMonth == 1) || (cMonth == 12 && mMonth == 1) || (cMonth == 11 && mMonth == 2)
                        || (cMonth == 12 && mMonth == 3)) mYear++;
                if((cMonth == 1 && mMonth == 12) || (cMonth == 1 && mMonth == 10) || (cMonth == 2 && mMonth == 11)
                        || (cMonth == 3 && mMonth == 12)) mYear--;

                cMonth = mMonth;

                yyyy.setText(String.valueOf(mYear)+"年");
            }

            @Override public void onPageScrollStateChanged(int state) {}
        });

        return view;
    }

}
