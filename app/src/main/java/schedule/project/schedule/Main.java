package schedule.project.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import schedule.project.schedule.horizontal.HorizonMain;

public class Main extends AppCompatActivity {
    Button primaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryBtn = (Button) findViewById(R.id.primaryBtn);

        primaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent project = new Intent(getApplicationContext(), HorizonMain.class);
                startActivity(project);
            }
        });
    }
}
