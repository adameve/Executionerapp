package anulom.executioner5.com3.anulom;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.SendPaymentService;

import static anulom.executioner5.com3.anulom.R.id.tvpay3;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.PAYMENT;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.UPDATEPAYMENT1;

public class Pay extends AppCompatActivity {
    private Toolbar toolbar;
    //    RelativeLayout mainlayout;
    DBOperation db;
    String option = "";
    String token = "DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7";
    int counter = 0;
    String rkey, amount, document, ID1, pay, finalamount, commentvalue, val, date;
    GenericMethods mResponse;
    java.util.Date calander;
    SimpleDateFormat simpledateformat;
    String Date;
    int value1, payment, amt;
    private String PAYMENTURL = "", TAG = "";
    TextView tv, tv1, tv2;
    EditText value, et1;
    Button update1;
    Context context;
    RadioButton rb1, rb2;
    View layout;
    private SharedPreferences usermail;
    private String username2 = "", password2 = "";

    public void radio(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        int checked1 = view.getId();
        if (checked1 == R.id.rp) {
            option = "rm";
        } else if (checked1 == R.id.sd) {
            option = "sd";
        }


    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.payinfo);
        db = new DBOperation(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Payment Info");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");

        layout = findViewById(R.id.l1);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Pay.this, SendPaymentService.class);
//                startService(intent);
                finish();
            }
        });
        rkey = getIntent().getStringExtra("ReportKey");
        document = getIntent().getStringExtra("DocumentId");
        tv1 = (TextView) findViewById(R.id.textView1);
//        mainlayout=(RelativeLayout)findViewById(R.id.l1);
        SQLiteDatabase base1 = db.getReadableDatabase();
        String query = "select * from  " + PAYMENT + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
        Cursor cursor = base1.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (rkey.equals(cursor.getString(cursor.getColumnIndex(DBManager.TableInfo.rep1)))) {
                amount = cursor.getString(cursor.getColumnIndex(DBManager.TableInfo.payamount));
            }
//            System.out.println("REPORT KEY:" + rkey);
//            System.out.println("DOCUMENT ID:" + document);
        }
        cursor.close();
        base1.close();


//            mainlayout.setVisibility(RelativeLayout.INVISIBLE);

//            System.out.println("amount" + amount);
        if (amount == "null" || amount == null || Integer.valueOf(amount) == 0) {
            layout.setVisibility(View.INVISIBLE);
            tv2 = (TextView) findViewById(R.id.nopay1);
            tv2.setText("No Outstanding Payments!!!/" +
                    "उर्वरित रक्कम नाही!!!");

//            setContentView(R.layout.nopay);

        } else {
//            setContentView(R.layout.payinfo);
            tv2 = (TextView) findViewById(R.id.nopay1);
            tv2.setVisibility(View.GONE);
            tv = (TextView) findViewById(R.id.tvreqno);
            tv.setText("Take ₹" + amount + "/- from client at the time of Execution");
            tv.setTextIsSelectable(true);
        }
        ArrayList<HashMap<String, String>> paymentlist = db.getPaymentReport(db);
        // System.out.println("paymentlist size:"+paymentlist.size());
        update1 = (Button) findViewById(R.id.btnupdate2);
        update1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calander = Calendar.getInstance().getTime();
                simpledateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                Date = simpledateformat.format(calander.getTime());

                value = (EditText) findViewById(tvpay3);
                et1 = (EditText) findViewById(R.id.editText1);
                rb1 = (RadioButton) findViewById(R.id.rp);
                rb2 = (RadioButton) findViewById(R.id.sd);
                try {
                    value1 = Integer.valueOf(value.getText().toString());


                } catch (Exception e) {
                    System.out.println("NO VALUE OR AMOUNT");
                }
                try {
                    amt = Integer.valueOf(amount);
                } catch (Exception e) {
//                    System.out.println("NO VALUE OR AMOUNT");
                }
                payment = amt - value1;
//                System.out.println("Amount:" + payment);
                pay = String.valueOf(payment);
                try {
                    commentvalue = et1.getText().toString();
                } catch (Exception e) {
//                    System.out.println("NO VALUE");
                }
//               if(value1 > amt) {
//
//                   Toast.makeText(Pay.this, " Enter valid amount ", Toast.LENGTH_LONG).show();
//               }


                SQLiteDatabase sqldb = db.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DBManager.TableInfo.payamount, pay);
                if (((value1 < amt) && (payment != amt)) || ((value1 == amt) && (payment != amt))) {
                    sqldb.update(PAYMENT, values, DBManager.TableInfo.rep1 + "=?", new String[]{rkey});

//                    System.out.println("PAYMENT UPDATED" + rkey + "" + pay);
                    Toast.makeText(Pay.this, " Payment inserted successfully!!! ", Toast.LENGTH_LONG).show();
                    sqldb.close();
                    finish();
                } else {
                    Toast.makeText(Pay.this, " ENTER AMOUNT!!", Toast.LENGTH_LONG).show();
                }
                val = String.valueOf(value1);
////////

//                System.out.println("Date:" + Date);
                SQLiteDatabase sqldb1 = db.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put(DBManager.TableInfo.KEYID, ID1);
                values1.put(DBManager.TableInfo.DOCID, document);
                values1.put(DBManager.TableInfo.email1, username2);
                values1.put(DBManager.TableInfo.amt, val);
                values1.put(DBManager.TableInfo.date, Date);
                values1.put(DBManager.TableInfo.radiotype, option);
                values1.put(DBManager.TableInfo.comment1, commentvalue);
                if (((value1 < amt) && (payment != amt)) || ((value1 == amt) && (payment != amt))) {
                    sqldb1.insert(UPDATEPAYMENT1, null, values1);
//                    System.out.println("records inserted");
//                    System.out.println(document + " " + username2 + " " + option + commentvalue + val);
                    sqldb1.close();
                } else {
                    Toast.makeText(Pay.this, " ENTER AMOUNT!!", Toast.LENGTH_LONG).show();
                }
                if (GenericMethods.isConnected(getApplicationContext()) && (payment != amt)) {
                    Intent intent = new Intent(Pay.this, SendPaymentService.class);
                    startService(intent);
                } else {

                    System.out.println("Offline");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {


        finish();
    }
}
