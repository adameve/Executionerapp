package anulom.executioner5.com3.anulom;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;

import anulom.executioner5.com3.anulom.services.Posttask;

import anulom.executioner5.com3.anulom.services.not_applicablepost;
import anulom.executioner5.com3.anulom.services.reassignpost;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.NOT_APPLICABLE;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.POST_TASK;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.REASSIGN;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.TABLE_TASK;

/**
 * Created by anulom on 26/7/17.
 */

public class taskcomment extends AppCompatActivity {

    Toolbar toolbar;
    int checked1;
    RadioButton rb1, rb2, rb3, rb4;
    TextView t1, t2, t3, t4, t5, e3;
    EditText e1, e2, e4, e5;
    Button b1;
    RelativeLayout lay1;
    Spinner sp1;
    DBOperation db;
    String mregion, comment;
    private SharedPreferences usermail;
    private String username2 = "", password2 = "";
    String task, ID2, taskid, TAG = "", option, status;

    public void radio(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        checked1 = view.getId();
        if (checked1 == R.id.rp1) {

            status = "Completed";
            option = "1";
            t1.setVisibility(View.GONE);
            e1.setVisibility(View.GONE);
            t2.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            sp1.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.INVISIBLE);
            e4.setVisibility(View.INVISIBLE);
        } else if (checked1 == R.id.sd1) {
            status = " Not Completed";
            option = "0";
            t1.setVisibility(View.VISIBLE);
            e1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            sp1.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.INVISIBLE);
            e4.setVisibility(View.INVISIBLE);
        } else if (checked1 == R.id.rp2) {
            status = "Reassign";
            t1.setVisibility(View.GONE);
            e1.setVisibility(View.GONE);
            t2.setVisibility(View.VISIBLE);
            t3.setVisibility(View.VISIBLE);
            e2.setVisibility(View.VISIBLE);
            sp1.setVisibility(View.VISIBLE);

            t5.setVisibility(View.INVISIBLE);
            e4.setVisibility(View.INVISIBLE);
        } else if (checked1 == R.id.sd2) {
            status = "Not Applicable";
            t1.setVisibility(View.INVISIBLE);
            e1.setVisibility(View.INVISIBLE);
            t2.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            sp1.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.VISIBLE);
            e4.setVisibility(View.VISIBLE);
        }


    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.taskcomments);
        db = new DBOperation(getApplicationContext());
        rb1 = (RadioButton) findViewById(R.id.rp);
        rb2 = (RadioButton) findViewById(R.id.sd);
        rb3 = (RadioButton) findViewById(R.id.rp2);
        rb4 = (RadioButton) findViewById(R.id.sd2);
        lay1 = (RelativeLayout) findViewById(R.id.relativelayout1);
        t1 = (TextView) findViewById(R.id.textView9);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.edit);
        e4 = (EditText) findViewById(R.id.edit3);
        t2 = (TextView) findViewById(R.id.text1);
        t3 = (TextView) findViewById(R.id.textView1);
        t5 = (TextView) findViewById(R.id.text3);
        b1 = (Button) findViewById(R.id.btnupdate2);
        sp1 = (Spinner) findViewById(R.id.spinner4);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Status");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        task = getIntent().getStringExtra("Value");
        taskid = getIntent().getStringExtra("task_id");
        comment = getIntent().getStringExtra("comment");
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lables);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(dataAdapter2);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mregion = parent.getItemAtPosition(position).toString();
                System.out.println("region:" + mregion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        SQLiteDatabase base1 = db.getWritableDatabase();
//        base1.delete(REASSIGN, null, null);
//        SQLiteDatabase base2 = db.getWritableDatabase();
//        base2.delete(NOT_APPLICABLE, null, null);
//        SQLiteDatabase base3 = db.getWritableDatabase();
//        base3.delete(POST_TASK, null, null);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((checked1 == R.id.rp1) || (checked1 == R.id.sd1)) {

                    SQLiteDatabase db3 = db.getWritableDatabase();
                    ContentValues values3 = new ContentValues();
                    values3.put(DBManager.TableInfo.KEYID, ID2);
                    values3.put(DBManager.TableInfo.IS_DONE, option);
                    values3.put(DBManager.TableInfo.TASK_ID, taskid);
                    values3.put(DBManager.TableInfo.REASON, e1.getText().toString());
                    values3.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition3 = DBManager.TableInfo.TASK_ID + " =?";
                    Cursor cursor3 = db3.query(POST_TASK, null, condition3, new String[]{DBManager.TableInfo.IS_DONE}, null, null, null);
                    long status3 = db3.insert(POST_TASK, null, values3);
                    Log.d(TAG, "DB insert : " + status3);
                    System.out.println("Success" + username2 + e1.getText().toString() + taskid + option);
                    cursor3.close();
                    db3.close();

                    SQLiteDatabase sqldb = db.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBManager.TableInfo.status1, status);
                    sqldb.update(TABLE_TASK, values, DBManager.TableInfo.TASK_ID + "=?", new String[]{taskid});
                    sqldb.close();

                    Intent i3 = new Intent(taskcomment.this, Posttask.class);
                    i3.putExtra("Value", task);
                    i3.putExtra("status", status);
                    startService(i3);
                    if (task.equals("Adhoc")) {
                        Intent i4 = new Intent(taskcomment.this, Nextactivity1.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Witness")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity2.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Complaint")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity3.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    }
                    Toast.makeText(taskcomment.this, " Task Status Updated!!", Toast.LENGTH_LONG).show();


//

                } else if (checked1 == R.id.rp2) {


                    SQLiteDatabase db4 = db.getWritableDatabase();
                    ContentValues values4 = new ContentValues();
                    values4.put(DBManager.TableInfo.KEYID, ID2);
                    values4.put(DBManager.TableInfo.Prev_owner, username2);
                    values4.put(DBManager.TableInfo.new_owner, mregion);
                    values4.put(DBManager.TableInfo.reassign_reason, e2.getText().toString());
                    values4.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition3 = DBManager.TableInfo.new_owner + " =?";
                    Cursor cursor4 = db4.query(REASSIGN, null, condition3, new String[]{DBManager.TableInfo.Prev_owner}, null, null, null);
                    long status3 = db4.insert(REASSIGN, null, values4);
                    Log.d(TAG, "DB insert : " + status3);
                    //System.out.println("Success" + username2 + e2.getText().toString()  + mregion);
                    cursor4.close();
                    db4.close();

                    SQLiteDatabase sqldb = db.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBManager.TableInfo.status1, status);
                    sqldb.update(TABLE_TASK, values, DBManager.TableInfo.TASK_ID + "=?", new String[]{taskid});
                    sqldb.close();


                    Intent i3 = new Intent(taskcomment.this, reassignpost.class);
                    i3.putExtra("taskid", taskid);
                    i3.putExtra("comment", comment);
                    i3.putExtra("status", status);
                    startService(i3);
                    if (task.equals("Adhoc")) {
                        Intent i4 = new Intent(taskcomment.this, Nextactivity1.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Witness")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity2.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Complaint")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity3.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    }
                    Toast.makeText(taskcomment.this, " Task Status Updated!!", Toast.LENGTH_LONG).show();


                } else if (checked1 == R.id.sd2) {


                    SQLiteDatabase db5 = db.getWritableDatabase();
                    ContentValues values5 = new ContentValues();
                    values5.put(DBManager.TableInfo.KEYID, ID2);
                    values5.put(DBManager.TableInfo.task_id1, taskid);
                    values5.put(DBManager.TableInfo.not_app, "1");
                    values5.put(DBManager.TableInfo.notapplicable_reason, e4.getText().toString());
                    values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition3 = DBManager.TableInfo.task_id1 + " =?";
                    Cursor cursor5 = db5.query(NOT_APPLICABLE, null, condition3, new String[]{DBManager.TableInfo.not_app}, null, null, null);
                    long status3 = db5.insert(NOT_APPLICABLE, null, values5);
                    Log.d(TAG, "DB insert : " + status3);
                    //System.out.println("Success" + username2 + e4.getText().toString() + taskid);
                    cursor5.close();
                    db5.close();

                    SQLiteDatabase sqldb = db.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBManager.TableInfo.status1, status);
                    sqldb.update(TABLE_TASK, values, DBManager.TableInfo.TASK_ID + "=?", new String[]{taskid});
                    sqldb.close();

                    Intent i3 = new Intent(taskcomment.this, not_applicablepost.class);
                    i3.putExtra("taskid", taskid);
                    i3.putExtra("comment", comment);
                    startService(i3);
                    if (task.equals("Adhoc")) {
                        Intent i4 = new Intent(taskcomment.this, Nextactivity1.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Witness")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity2.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    } else if (task.equals("Complaint")) {
                        Intent i4 = new Intent(taskcomment.this, NextActivity3.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i4.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i4);
                    }
                    Toast.makeText(taskcomment.this, " Task Status Updated!!", Toast.LENGTH_LONG).show();


                }


            }

        });


    }


}