package anulom.executioner5.com3.anulom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.TimePicker;


import java.text.SimpleDateFormat;

import java.util.Calendar;


import android.view.View.OnClickListener;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.SendmultiPartyReport;
import anulom.executioner5.com3.anulom.services.rescheduledetails;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.RESCHEDULE;

/**
 * Created by anulom on 28/7/17.
 */

public class reschedule extends AppCompatActivity {


    private Calendar myCalendar, mcurrenttime;
    EditText edt_on_date, edt_on_time;
    Button btn_date, btn_time, update;
    Toolbar toolbar;
    String option1 = "", datevalue = " ", datevalue1 = " ", timevalue, ID2, document, appointmentid, starttime, endtime, finaltime;
    RadioButton rb1, rb2, rb3, rb4;
    DBOperation db;

    private SharedPreferences usermail;
    private String username2 = "", password2 = "";

    DatePickerDialog.OnDateSetListener date1;


    public void radio1(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        int checked1 = view.getId();
        if (checked1 == R.id.govt) {
            option1 = "Govt IGR/UID Issue";
        } else if (checked1 == R.id.cust) {
            option1 = "Request by Customer";
        } else if (checked1 == R.id.issue) {
            option1 = "Technical Issue";
        } else if (checked1 == R.id.appoin) {
            option1 = "Appointment Missed by Executioner";
        }


    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.reschedule);
        db = new DBOperation(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Reschedule");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Pay.this, SendPaymentService.class);
//                startService(intent);
                finish();
            }
        });
//        SQLiteDatabase base3 = db.getWritableDatabase();
//               base3.delete(RESCHEDULE, null, null);
//               System.out.println(" DB Deleted");

        //RelativeLayout rootView = new RelativeLayout(R.layout.reschedule);
        appointmentid = getIntent().getStringExtra("AppointmentId");
        document = getIntent().getStringExtra("DocumentId");
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");

        rb1 = (RadioButton) findViewById(R.id.govt);
        rb2 = (RadioButton) findViewById(R.id.cust);
        rb3 = (RadioButton) findViewById(R.id.issue);
        rb4 = (RadioButton) findViewById(R.id.appoin);

        update = (Button) findViewById(R.id.btnupdate2);
        btn_date = (Button) findViewById(R.id.btn_date);
        edt_on_date = (EditText) findViewById(R.id.edt_on_date);
        btn_time = (Button) findViewById(R.id.btn_time);
        edt_on_time = (EditText) findViewById(R.id.edt_on_time);
        myCalendar = Calendar.getInstance();
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date1 = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


                        edt_on_date.setText(sdf.format(myCalendar.getTime()));
                        datevalue = edt_on_date.getText().toString();

//                        System.out.println("date:" + datevalue);


                    }

                };
                new DatePickerDialog(reschedule.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mcurrenttime = Calendar.getInstance();

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(reschedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mcurrenttime.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mcurrenttime.set(Calendar.MINUTE, selectedMinute);
                        edt_on_time.setText(selectedHour + ":" + selectedMinute);
                        timevalue = edt_on_time.getText().toString();
//                        System.out.println("time:" + timevalue);
                    }
                }, mcurrenttime.get(Calendar.HOUR_OF_DAY), mcurrenttime.get(Calendar.MINUTE), true);//Yes 24 hour time


                mTimePicker.show();

            }
        });
        update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//
                SQLiteDatabase reschedule = db.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put(DBManager.TableInfo.KEYID, ID2);
                values1.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                values1.put(DBManager.TableInfo.RESCHEDULEDATE, datevalue);
                values1.put(DBManager.TableInfo.RESCHEDULETIME, timevalue);
                values1.put(DBManager.TableInfo.RES_REASON, option1);
                values1.put(DBManager.TableInfo.DocumentId, document);
                values1.put(DBManager.TableInfo.AppointmentId, appointmentid);
                long status = reschedule.insert(RESCHEDULE, null, values1);
//
                reschedule.close();
                if (GenericMethods.isConnected(getApplicationContext())) {
                    Intent i = new Intent(reschedule.this, rescheduledetails.class);
                    startService(i);
                } else {
                    System.out.println("Offline");
                }

                finish();
            }


        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // TODO Auto-generated method stub
//
//
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.reschedule, menu);
//
//        return super.onCreateOptionsMenu(menu);
//
//    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        // TODO Auto-generated method stub
//
//        super.onOptionsItemSelected(item);
//        switch (item.getItemId()) {
//            case R.id.action_refresh:
//                Intent i1 = new Intent(reschedule.this, history.class);
//                startActivity(i1);
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {


        finish();
    }


}
