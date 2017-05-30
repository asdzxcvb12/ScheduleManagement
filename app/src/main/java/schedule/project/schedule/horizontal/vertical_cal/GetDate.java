package schedule.project.schedule.horizontal.vertical_cal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ghangsub on 2017-04-24.
 */

public class GetDate {
    private int mYear, mMonth, mDay;
    private long now = System.currentTimeMillis();
    private Date mDate = new Date(now);

    final SimpleDateFormat curYearFomat = new SimpleDateFormat("yyyy", Locale.KOREA);
    final SimpleDateFormat curMonthFomat = new SimpleDateFormat("MM", Locale.KOREA);
    final SimpleDateFormat curDayFomat = new SimpleDateFormat("dd", Locale.KOREA);

    public String getDay(int index) {
        ArrayList<String> day = new ArrayList<String>();
        day.add("일");
        day.add("월");
        day.add("화");
        day.add("수");
        day.add("목");
        day.add("금");
        day.add("토");
        return day.get(index);
    }

    public int getYMD(int index) {
        ArrayList<Integer> date = new ArrayList<Integer>();

        mYear = Integer.parseInt(curYearFomat.format(mDate));
        mMonth = Integer.parseInt(curMonthFomat.format(mDate));
        mDay = Integer.parseInt(curDayFomat.format(mDate));

        date.add(mYear);
        date.add(mMonth);
        date.add(mDay);

        return date.get(index);
    }
}
