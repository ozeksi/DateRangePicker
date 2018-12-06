package com.savvi.rangepickersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SampleActivity extends AppCompatActivity {


    CalendarPickerView calendar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -10);


        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        final Locale locale = Locale.getDefault();
        calendar.init(lastYear.getTime(), nextYear.getTime(), TimeZone.getDefault(), locale, new SimpleDateFormat("LLLL yyyy", locale), new SimpleDateFormat("E", locale))
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSelectedDate(new Date());


        final SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String strdate = "22-12-2018";
        String strdate2 = "26-12-2018";

        button = (Button) findViewById(R.id.get_selected_dates);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Date> selectedDates = calendar.getSelectedDates();
                for (Date selectedDate : selectedDates) {
                    System.out.println(dateformat.format(selectedDate));
                }
            }
        });

        try {
            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            ArrayList<Date> arrayList = new ArrayList<>();
            arrayList.add(newdate);
            arrayList.add(newdate2);
            calendar.highlightDates(arrayList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> deactivate = new ArrayList<>();
        deactivate.add(6);
        deactivate.add(7);
        calendar.deactivateDates(deactivate);


        Log.d("list", calendar.getSelectedDates().toString());


    }
}
