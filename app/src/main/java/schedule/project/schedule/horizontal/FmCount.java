package schedule.project.schedule.horizontal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schedule.project.schedule.R;

@SuppressLint("ValidFragment")
public class FmCount extends Fragment {

    private View view;
    private Context mContext;
    private String TagMSG = "TagMSG";

    public FmCount(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_count, container, false);

        Log.d(TagMSG, "Message");

        return view;
    }
}
