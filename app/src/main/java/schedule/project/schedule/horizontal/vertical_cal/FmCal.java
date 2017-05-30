package schedule.project.schedule.horizontal.vertical_cal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.icu.util.ChineseCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import schedule.project.schedule.R;

/**
 * Created by ghangsub on 2017-04-24.
 */

public class FmCal extends Fragment {
    private View view;
    private GetDate getDate = new GetDate();

    private LinearLayout ll;
    private TextView MM;
    private int position = 0 , mMonth = 0, mDay = 0, getDay, getFrsDay, lastCurrentDay = 1, ColorDay, chinaYear, chinaMonth, chinaDay, chk;
    private static int mYear = 0, cMonth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mYear == 0) {
            mYear = getDate.getYMD(0);
            mMonth = getDate.getYMD(1);
        }

        if (!getArguments().isEmpty()) {
            position = getArguments().getInt("position");
            mMonth = (position % 12) + 1;
            Log.d("TAG.date","cMonth : "+String.valueOf(cMonth)+"\nmMonth : "+String.valueOf(mMonth));
            if((cMonth == 10 && mMonth == 1) || (cMonth == 12 && mMonth == 1) || (cMonth == 11 && mMonth == 2)
                    || (cMonth == 12 && mMonth == 3)) mYear++;
            if((cMonth == 1 && mMonth == 12) || (cMonth == 1 && mMonth == 10) || (cMonth == 2 && mMonth == 11)
                    || (cMonth == 3 && mMonth == 12)) mYear--;

            cMonth = mMonth;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_cal, container, false);

        Log.d("체크체크", "mYear : "+ mYear + "mMonth : " + mMonth + "Position : " + position);
        mDay = getDate.getYMD(2);

        MM = (TextView) view.findViewById(R.id.MM);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        drawView();
        return view;
    }

    public void drawView() {
        final Calendar mCal = Calendar.getInstance();
        final ChineseCalendar chinaCal = new ChineseCalendar();

        mCal.set(Calendar.YEAR, mYear);
        mCal.set(Calendar.MONTH, mMonth - 1);
        mCal.set(Calendar.DATE, 1);
        Date date = new Date(mCal.getTimeInMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Log.d("TAG.date", "date : "+format.format(date));

        getFrsDay = mCal.get(Calendar.DAY_OF_WEEK); // 1일 요일
        chk = mCal.get(Calendar.DAY_OF_WEEK);
        getDay = mCal.getActualMaximum(mCal.DAY_OF_MONTH); // 마지막 날짜

        mCal.set(Calendar.DATE, mCal.getActualMaximum(mCal.DAY_OF_MONTH));

        MM.setText(mMonth+"月");

        for(int i = 0; i < 7; i++) {
            LinearLayout item = new LinearLayout(view.getContext());
            item.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams paramss
                    = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            paramss.weight = 1;

            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(22, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;

            for(int j = 0; j < 7; j++) {
                final TextView addDate = new TextView(view.getContext());
                final TextView addTx = new TextView(view.getContext());
                addTx.setEnabled(true);
                if(i == 0) {
                    addDate.setText(getDate.getDay(j));
                } else if(getFrsDay > 1) {
                    addTx.setText("  ");
                    addTx.setEnabled(false);
                    getFrsDay--;
                } else {
                    if(lastCurrentDay <= getDay) {
                        mCal.set(Calendar.DATE, lastCurrentDay);

                        ColorDay = mCal.get(Calendar.DAY_OF_WEEK); // 1일 요일

                        addTx.setText(String.valueOf(lastCurrentDay));
                        if(ColorDay == 1) {
                            addTx.setTextColor(getResources().getColor(R.color.red));
                        } else if(ColorDay == 7) {
                            addTx.setTextColor(getResources().getColor(R.color.blue));
                        } else {
                            addTx.setTextColor(getResources().getColor(R.color.gray));
                        }

                        if(lastCurrentDay == mDay) {
                            if(mMonth == getDate.getYMD(1))
                                addTx.setTextColor(getResources().getColor(R.color.pink));
                        }
                        lastCurrentDay++;
                    } else {
                        addTx.setEnabled(false);
                        addTx.setText("  ");
                    }
                }
                addTx.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                        alert.setTitle(String.valueOf(mYear)+"年 "+String.valueOf(mMonth)+"月 "+addTx.getText().toString()+"日");
                        alert.setMessage("일정을 추가 하시겠습니까?");


                        // Set an EditText view to get user input

                        alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                addTx.setBackgroundColor(getResources().getColor(R.color.white));

                                dialog.dismiss();
                                // Do something with value!
                            }
                        });

                        alert.setNegativeButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        final AlertDialog.Builder alert2 = new AlertDialog.Builder(view.getContext());
                                        dialog.dismiss();
                                        alert2.setTitle("TEST");
                                        alert2.setMessage("TESTEST");


                                        alert2.setPositiveButton("TEST", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                addTx.setBackgroundColor(getResources().getColor(R.color.skyblue));

                                                dialog.dismiss();
                                                // Do something with value!
                                            }
                                        });
                                        alert2.show();
                                    }
                                });

                        alert.show();

                        return true;
                    }
                });

                addTx.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mCal.set(Calendar.YEAR, mYear);
                        mCal.set(Calendar.MONTH, mMonth - 1);
                        mCal.set(Calendar.DATE, Integer.parseInt(addTx.getText().toString()));

                        chinaCal.setTimeInMillis(mCal.getTimeInMillis());

                        chinaYear = chinaCal.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
                        chinaMonth = chinaCal.get(ChineseCalendar.MONTH) + 1;
                        chinaDay = chinaCal.get(ChineseCalendar.DAY_OF_MONTH);
                        Toast.makeText(view.getContext(), "양력 : "+String.valueOf(mYear)+"年 "+String.valueOf(mMonth)+"月 "+addTx.getText().toString()+"日"
                                +"\n음력 : "+String.valueOf(chinaYear)+"年 "+String.valueOf(chinaMonth)+"月 "+String.valueOf(chinaDay)+"日"+"\nposition"+String.valueOf(position)+
                                "\n첫요일 : "+chk, Toast.LENGTH_SHORT).show();
                    }
                });
                addDate.setTextColor(getResources().getColor(R.color.black));
                addDate.setTextSize(20);
                addDate.setGravity(Gravity.CENTER);
                addDate.setLayoutParams(params);

                addTx.setTextSize(20);
                addTx.setGravity(Gravity.CENTER);
                addTx.setLayoutParams(params);
                if(i == 0) {
                    item.addView(addDate);
                } else {
                    item.addView(addTx);
                }
            }
            item.setLayoutParams(paramss);
            ll.addView(item);

        }
    }

    public static void set_mYear(int mYear) {
        FmCal.mYear = mYear;
    }
}