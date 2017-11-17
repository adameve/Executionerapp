package anulom.executioner5.com3.anulom;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.SendmultiPartyReport;
import anulom.executioner5.com3.anulom.services.AppointmentstatusPost;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.MULTIWORK;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.PARTIES1;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.POSTDOC;


/**
 * Created by anulom on 11/7/17.
 */

public class newstatusinfo extends AppCompatActivity {

    public static final int i = 0;
    String item = "", document, rkey, email, value, status, appointment, reason = "";
    int count = 0, pos, ID, n_parties = 0, ctr1 = 0, n, k;
    DBOperation db;
    String flag = "0", postStatus = "";
    EditText slottime;
    TextView tv2;

    CheckBox check1, check2, check3, check4;
    private SharedPreferences usermail;
    private String username2 = "", password2 = "";
    String ID1;
    ArrayList<HashMap<String, String>> getAlldataList = null;
    //    ArrayList<HashMap<String, String>> biometricList = new ArrayList<HashMap<String, String>>();
//    HashMap<String, String> getbiometricDetails = new HashMap<String, String>();
    List<String> itemlist = new ArrayList<String>();
    List<Integer> poslist = new ArrayList<Integer>();
    List<Integer> IDlist = new ArrayList<Integer>();
    List<String> attendancelist = new ArrayList<String>();
    List<String> partytypelist = new ArrayList<String>();
    List<String> emaillist = new ArrayList<String>();
    // List<String> biometriclist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBOperation(this);
        document = getIntent().getStringExtra("document_id");
        rkey = getIntent().getStringExtra("rkey");
        getAlldataList = db.getPartiesReport(db);
        final LinearLayout rootView = new LinearLayout(this);
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");
        document = getIntent().getStringExtra("document_id");
        appointment = getIntent().getStringExtra("appointment_id");
        postStatus = getIntent().getStringExtra("post_status");

        check1 = new CheckBox(this);
        check1.setText("Visit Not Done");
        check1.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));

        check2 = new CheckBox(this);
        check2.setText("Door Step Visit Done");
        check2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));

        check3 = new CheckBox(this);
        check3.setText("No Show");
        check3.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));
        check4 = new CheckBox(this);
        check4.setText("Cancelled By Customer");
        check4.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));

        TextView t = new TextView(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        final TextView tv1 = new TextView(this);
        final TextView reason1 = new TextView(this);
        tv1.setText("Request No:" + rkey);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.parseColor("#000080"));


        tv2 = new TextView(this);
        tv2.setText(" Appointment Status:");
        tv2.setTextColor(Color.parseColor("#000080"));
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));


        final TextView tv3 = new TextView(this);
        tv3.setText(" Biometric Status:");
        tv3.setTextColor(Color.parseColor("#000080"));
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));
        tv3.setVisibility(View.GONE);



        slottime = new EditText(this);
        slottime.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) slottime.getLayoutParams();
        params1.setMargins(40, 20, 110, 10);
        slottime.setLayoutParams(params1);
        slottime.setVisibility(View.GONE);

        reason1.setText(" Reason:");
        reason1.setTextColor(Color.parseColor("#000080"));
        reason1.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_size_medium));
        reason1.setVisibility(View.GONE);

        final Button update1 = new Button(this);
        update1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        update1.setGravity(Gravity.CENTER);
        update1.setText("UPDATE");

        update1.setBackgroundColor(Color.parseColor("#ffa500"));
        update1.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) update1.getLayoutParams();
        params.setMargins(50, 20, 50, 10);
        update1.setLayoutParams(params);


        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqldb1 = db.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put(DBManager.TableInfo.postdocument, document);
                int count = sqldb1.update(POSTDOC, values1, DBManager.TableInfo.postdocument + "=?", new String[]{document});
                Toast.makeText(newstatusinfo.this, "Data insert Successfull  ", Toast.LENGTH_LONG).show();
                sqldb1.close();


                if (count == 0) {
                    SQLiteDatabase dbb = db.getWritableDatabase();
                    ContentValues valuess = new ContentValues();
                    valuess.put(DBManager.TableInfo.KEYID, ID1);
                    valuess.put(DBManager.TableInfo.postdocument, document);
                    valuess.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String conditionn = DBManager.TableInfo.postdocument + " =?";
                    Cursor cursorr = dbb.query(DBManager.TableInfo.POSTDOC, null, conditionn, new String[]{DBManager.TableInfo.postdocument}, null, null, null);
                    long statuss = dbb.insert(DBManager.TableInfo.POSTDOC, null, valuess);

                    System.out.println("Post doc db Updated");
                    cursorr.close();
                    dbb.close();
                }
                //System.out.println("documentnew"+document+""+"username"+username2);

                for (int i = 0; i < poslist.size(); i++) {
                    //System.out.println("Poslist:" + poslist.size());
                    if (itemlist.get(i).equals("Yes")) {

                        itemlist.remove(i);
                        itemlist.add(i, "true");
                    } else if (itemlist.get(i).equals("Thumb Not Match") || itemlist.get(i).equals("No")) {

                        itemlist.remove(i);
                        itemlist.add(i, "false");
                    } else {

                        itemlist.remove(i);
                        itemlist.add(i, "null");
                    }
                    //System.out.println("item:"+itemlist.get(i));


                    SQLiteDatabase sqldb = db.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBManager.TableInfo.BIOMETRIC, itemlist.get(i));
                    int count2 = sqldb.update(PARTIES1, values, DBManager.TableInfo.ATTENDANCE + "=?", new String[]{attendancelist.get(i)});
                    Toast.makeText(newstatusinfo.this, "Data insert Successfull  ", Toast.LENGTH_LONG).show();
                    sqldb.close();


                    SQLiteDatabase db7 = db.getWritableDatabase();
                    ContentValues values5 = new ContentValues();
                    values5.put(DBManager.TableInfo.KEYID, ID1);
                    values5.put(DBManager.TableInfo.DOCU, document);
                    values5.put(DBManager.TableInfo.ATTEND, attendancelist.get(i));
                    values5.put(DBManager.TableInfo.EMAIL, emaillist.get(i));
                    values5.put(DBManager.TableInfo.PARTY, partytypelist.get(i));
                    values5.put(DBManager.TableInfo.BIO, itemlist.get(i));
                    values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition5 = DBManager.TableInfo.DOCU + " =?";
                    Cursor cursor5 = db7.query(DBManager.TableInfo.UPDATEPARTY, null, condition5, new String[]{DBManager.TableInfo.ATTEND}, null, null, null);
                    long status5 = db7.insert(DBManager.TableInfo.UPDATEPARTY, null, values5);
                    System.out.println( "Parties insert : " + status5);
                    cursor5.close();
                    db7.close();
                }

                itemlist.clear();
                poslist.clear();
                IDlist.clear();
                attendancelist.clear();
                partytypelist.clear();
                emaillist.clear();


                if (GenericMethods.isConnected(getApplicationContext())) {
                    Intent i1 = new Intent(anulom.executioner5.com3.anulom.newstatusinfo.this, SendmultiPartyReport.class);
                    startService(i1);
                } else {
                    System.out.println("Offline");
                }

                if (check1.isChecked()) {

                    //System.out.println("1 check");
                    status = "0";
                    flag = "1";
                    reason = slottime.getText().toString();
                } else if (check2.isChecked()) {
                    //System.out.println("2 check");
                    status = "1";
                    flag = "1";

                } else if (check3.isChecked()) {
                    //System.out.println("3 check");
                    status = "2";
                    flag = "1";
                    reason = slottime.getText().toString();
                } else if (check4.isChecked()) {
                    //System.out.println("4 check");
                    status = "3";
                    flag = "1";

                }


                if (flag == "1") {

                    SQLiteDatabase sqldb2 = db.getWritableDatabase();
                    ContentValues values2 = new ContentValues();
                    values2.put(DBManager.TableInfo.STATUS, status);
                    int count4 = sqldb2.update(MULTIWORK, values2, DBManager.TableInfo.DOCU + "=?", new String[]{document});
                    Toast.makeText(newstatusinfo.this, "Data insert Successfull  ", Toast.LENGTH_LONG).show();
                    sqldb2.close();

                    if (count4 == 0) {

                        SQLiteDatabase db0 = db.getWritableDatabase();
                        ContentValues values5 = new ContentValues();
                        values5.put(DBManager.TableInfo.KEYID, ID1);
                        values5.put(DBManager.TableInfo.DOCU, document);
                        values5.put(DBManager.TableInfo.AppointmentId, appointment);
                        values5.put(DBManager.TableInfo.STATUS, status);
                        values5.put(DBManager.TableInfo.reason, reason);
                        values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                        String condition5 = DBManager.TableInfo.AppointmentId + " =?";
                        Cursor cursor5 = db0.query(MULTIWORK, null, condition5, new String[]{DBManager.TableInfo.AppointmentId}, null, null, null);
                        long status5 = db0.insert(MULTIWORK, null, values5);
                        cursor5.close();
                        db0.close();
                        Intent i2 = new Intent(newstatusinfo.this, AppointmentstatusPost.class);
                        startService(i2);
                        finish();
                        flag = "0";
                    }
                } else {
                    System.out.println("Updated");
                }


            }
        });

        ScrollView m_Scroll = new ScrollView(this);
        m_Scroll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Toolbar toolbar = new Toolbar(this);
        toolbar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        toolbar.setBackgroundColor(Color.parseColor("#ffa500"));
        toolbar.setTitle("Biometric Status");
        toolbar.setTitleTextColor(Color.WHITE);
        rootView.addView(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.addView(tv1);
        rootView.addView(t);
        rootView.addView(tv2);
        rootView.addView(check2);
        rootView.addView(check1);
        rootView.addView(check3);
        rootView.addView(check4);
        rootView.addView(reason1);
        rootView.addView(slottime);
        rootView.addView(tv3);
        rootView.addView(t1);
        rootView.addView(update1);
        m_Scroll.addView(rootView);
        setContentView(m_Scroll);

        ArrayList<HashMap<String, String>> statuslist = db.getmultipartycheck(db);
        for (int i = 0; i < statuslist.size(); i++) {
            if (document.equals(statuslist.get(i).get("docid"))) {
                postStatus = statuslist.get(i).get("status");
                //System.out.println(postStatus);
            }
        }
        if (postStatus.equals("0")) {
            check1.setChecked(true);

        }
        if (postStatus.equals("1")) {
            check2.setChecked(true);
            tv3.setVisibility(View.VISIBLE);
            update1.setVisibility(View.GONE);
            displayfunction(rootView);
        }
        if (postStatus.equals("2")) {
            check3.setChecked(true);

        }
        if (postStatus.equals("3")) {
            check4.setChecked(true);

        }
        if (postStatus.equals("99")) {

            check1.setVisibility(View.GONE);
            check2.setVisibility(View.GONE);
            check3.setVisibility(View.GONE);
            check4.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                //System.out.println("id:"+check1.isChecked());

                if (check1.isChecked()) {
                    reason1.setVisibility(View.VISIBLE);
                    slottime.setVisibility(View.VISIBLE);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    tv3.setVisibility(View.GONE);
                    update1.setVisibility(View.VISIBLE);
                    removefunction(rootView);

                } else {
                    reason1.setVisibility(View.INVISIBLE);
                    slottime.setVisibility(View.INVISIBLE);
                }
            }
        });


        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (check2.isChecked()) {
                    reason1.setVisibility(View.GONE);
                    slottime.setVisibility(View.GONE);
                    check1.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    tv3.setVisibility(View.VISIBLE);
                    update1.setVisibility(View.GONE);
                    //System.out.println("hiiiiiiii");
                    displayfunction(rootView);

                } else {

                    tv3.setVisibility(View.GONE);
                    update1.setVisibility(View.VISIBLE);
                    removefunction(rootView);
                }
            }
        });


        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (check3.isChecked()) {
                    reason1.setVisibility(View.VISIBLE);
                    slottime.setVisibility(View.VISIBLE);
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check4.setChecked(false);
                    //disp_flag="1";
                    tv3.setVisibility(View.GONE);
                    update1.setVisibility(View.VISIBLE);
                    removefunction(rootView);

                } else {
                    reason1.setVisibility(View.INVISIBLE);
                    slottime.setVisibility(View.INVISIBLE);
                }
            }
        });

        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (check4.isChecked()) {
                    reason1.setVisibility(View.GONE);
                    slottime.setVisibility(View.GONE);
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    //disp_flag="1";
                    tv3.setVisibility(View.GONE);
                    update1.setVisibility(View.VISIBLE);
                    removefunction(rootView);
                }
            }
        });


    }

    public void displayfunction(LinearLayout rootView) {
        int o_count = 0, t_count = 0, w_count = 0, aw_count = 0, poa_count = 0;


        for (int j = 0; j < getAlldataList.size(); j++) {
            //System.out.println("display");
            TextView tv = new TextView(this);
            tv.setId(n);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_medium));
            tv.setTextColor(Color.parseColor("#545454"));

            List<String> values = new ArrayList<String>();
            if (getAlldataList.get(j).get("document_id").equals(document)) {
                if (getAlldataList.get(j).get("party_type").equals("1") && Integer.valueOf(db.getPartiesReport(db).get(j).get("poa")) > 0) {
                    System.out.println("No spinner");
                } else {
                    n_parties = n_parties + 1;
                    Spinner spin = new Spinner(this);
                    spin.setId(j);
                    spin.setGravity(Gravity.CENTER);
                    spin.setBackgroundResource(R.drawable.ic_down);
                    // spin.
                    if (getAlldataList.get(j).get("party_type").equals("1")) {
                        o_count += 1;

                        tv.setText("  Owner" + String.valueOf(o_count) + "-" + db.getPartiesReport(db).get(j).get("name"));

                        if (getAlldataList.get(j).get("biometric").equals("null")) {
                            values.add("Select");
                            values.add("Yes");
                            values.add("Thumb Not Match");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("false")) {
                            values.add("Thumb Not Match");
                            values.add("Yes");
                            values.add("Select");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("true")) {
                            values.add("Yes");
                            values.add("Thumb Not Match");
                            values.add("Select");
                        }
                    }
                    if (getAlldataList.get(j).get("party_type").equals("2")) {
                        t_count += 1;
                        tv.setText("  Tenant" + String.valueOf(t_count) + "-" + db.getPartiesReport(db).get(j).get("name"));
                        if (getAlldataList.get(j).get("biometric").equals("null")) {
                            values.add("Select");
                            values.add("Yes");
                            values.add("Thumb Not Match");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("false")) {
                            values.add("Thumb Not Match");
                            values.add("Yes");
                            values.add("Select");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("true")) {
                            values.add("Yes");
                            values.add("Thumb Not Match");
                            values.add("Select");
                        }
                    }
                    if (getAlldataList.get(j).get("party_type").equals("3")) {
                        w_count += 1;
                        tv.setText("  Witness" + String.valueOf(w_count) + "-" + db.getPartiesReport(db).get(j).get("name"));
                        if (getAlldataList.get(j).get("biometric").equals("null")) {
                            values.add("Select");
                            values.add("Yes");
                            values.add("No");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("false")) {
                            values.add("No");
                            values.add("Yes");
                            values.add("Select");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("true")) {
                            values.add("Yes");
                            values.add("No");
                            values.add("Select");
                        }
                    }
                    if (getAlldataList.get(j).get("party_type").equals("4")) {
                        poa_count += 1;
                        tv.setText("  POA" + String.valueOf(poa_count) + "-" + db.getPartiesReport(db).get(j).get("name"));
                        if (getAlldataList.get(j).get("biometric").equals("null")) {
                            values.add("Select");
                            values.add("Yes");
                            values.add("Thumb Not Match");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("false")) {
                            values.add("Thumb Not Match");
                            values.add("Yes");
                            values.add("Select");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("true")) {
                            values.add("Yes");
                            values.add("Thumb Not Match");
                            values.add("Select");
                        }
                    }
                    if (getAlldataList.get(j).get("party_type").equals("5")) {
                        aw_count += 1;
                        tv.setText("  Anulom Witness" + String.valueOf(aw_count) + "-" + db.getPartiesReport(db).get(j).get("name"));
                        if (getAlldataList.get(j).get("biometric").equals("null")) {
                            values.add("Select");
                            values.add("Yes");
                            values.add("No");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("false")) {
                            values.add("No");
                            values.add("Yes");
                            values.add("Select");
                        }
                        if (getAlldataList.get(j).get("biometric").equals("true")) {
                            values.add("Yes");
                            values.add("No");
                            values.add("Select");
                        }
                    }
                    rootView.addView(tv);


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.witnesslay, values);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spin.setAdapter(adapter);
                    rootView.addView(spin);
                }
            }
        }

        for (int x = 0; x < getAlldataList.size(); x++) {

            if (getAlldataList.get(x).get("document_id").equals(document)) {

                if (getAlldataList.get(x).get("party_type").equals("1") && Integer.valueOf(db.getPartiesReport(db).get(x).get("poa")) > 0) {
                } else {
                    Spinner sp = (Spinner) findViewById(x);
                    sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            count = count + 1;
                            value = "false";
                            item = parent.getSelectedItem().toString();
                            pos = position;
                            ID = parent.getId();

                            if (count > n_parties) {

                                if (IDlist.size() == 0) {

                                    itemlist.add(item);
                                    poslist.add(pos);
                                    IDlist.add(ID);

                                    attendancelist.add(getAlldataList.get(ID).get("att_id"));
                                    emaillist.add(getAlldataList.get(ID).get("email"));
                                    partytypelist.add(getAlldataList.get(ID).get("party_type"));
                                    //System.out.println("1st insert");

                                } else if (IDlist.size() > 0) {

                                    for (int z = 0; z < IDlist.size(); z++) {
                                        if (ID == IDlist.get(z)) {
                                            IDlist.remove(z);
                                            itemlist.remove(z);
                                            poslist.remove(z);
                                            attendancelist.remove(z);
                                            emaillist.remove(z);
                                            partytypelist.remove(z);
                                            itemlist.add(z, item);
                                            poslist.add(z, pos);
                                            IDlist.add(z, ID);
                                            attendancelist.add(z, getAlldataList.get(ID).get("att_id"));
                                            emaillist.add(z, getAlldataList.get(ID).get("email"));
                                            partytypelist.add(z, getAlldataList.get(ID).get("party_type"));
                                            value = "true";
                                        }
                                    }
                                    if (value.equals("false") && (IDlist.size() > 0) && (IDlist.size() < getAlldataList.size())) {

                                        //if(IDlist.size()<getAlldataList.size()) {
                                        itemlist.add(item);
                                        poslist.add(pos);
                                        IDlist.add(ID);
                                        attendancelist.add(getAlldataList.get(ID).get("att_id"));
                                        emaillist.add(getAlldataList.get(ID).get("email"));
                                        partytypelist.add(getAlldataList.get(ID).get("party_type"));
                                    }
                                }
                            } else {
                                System.out.println("No insert required");
                            }
                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        }

        Button update2 = new Button(this);
        update2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        update2.setGravity(Gravity.CENTER);
        update2.setText("UPDATE");
        update2.setId(k);
        update2.setBackgroundColor(Color.parseColor("#ffa500"));
        update2.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) update2.getLayoutParams();
        params.setMargins(50, 20, 50, 10);
        update2.setLayoutParams(params);
        rootView.addView(update2);
        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase dbb = db.getWritableDatabase();
                ContentValues valuess = new ContentValues();
                valuess.put(DBManager.TableInfo.KEYID, ID1);
                valuess.put(DBManager.TableInfo.postdocument, document);
                valuess.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                String conditionn = DBManager.TableInfo.postdocument + " =?";
                Cursor cursorr = dbb.query(DBManager.TableInfo.POSTDOC, null, conditionn, new String[]{DBManager.TableInfo.KEY_LOGIN_USER}, null, null, null);
                long statuss = dbb.insert(DBManager.TableInfo.POSTDOC, null, valuess);
                cursorr.close();
                dbb.close();

                for (int i = 0; i < poslist.size(); i++) {
                    //System.out.println("Poslist:" + poslist.size());
                    if (itemlist.get(i).equals("Yes")) {

                        itemlist.remove(i);
                        itemlist.add(i, "true");
                    } else if (itemlist.get(i).equals("Thumb Not Match") || itemlist.get(i).equals("No")) {

                        itemlist.remove(i);
                        itemlist.add(i, "false");
                    } else {

                        itemlist.remove(i);
                        itemlist.add(i, "null");
                    }

                    SQLiteDatabase sqldb = db.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBManager.TableInfo.BIOMETRIC, itemlist.get(i));
                    int count1 = sqldb.update(PARTIES1, values, DBManager.TableInfo.ATTENDANCE + "=?", new String[]{attendancelist.get(i)});
                    Toast.makeText(newstatusinfo.this, "Data insert Successfull  ", Toast.LENGTH_LONG).show();
                    sqldb.close();

                    SQLiteDatabase db7 = db.getWritableDatabase();
                    ContentValues values5 = new ContentValues();
                    values5.put(DBManager.TableInfo.KEYID, ID1);
                    values5.put(DBManager.TableInfo.DOCU, document);
                    values5.put(DBManager.TableInfo.ATTEND, attendancelist.get(i));
                    values5.put(DBManager.TableInfo.EMAIL, emaillist.get(i));
                    values5.put(DBManager.TableInfo.PARTY, partytypelist.get(i));
                    values5.put(DBManager.TableInfo.BIO, itemlist.get(i));
                    values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition5 = DBManager.TableInfo.DOCU + " =?";
                    Cursor cursor5 = db7.query(DBManager.TableInfo.UPDATEPARTY, null, condition5, new String[]{DBManager.TableInfo.ATTEND}, null, null, null);
                    long status5 = db7.insert(DBManager.TableInfo.UPDATEPARTY, null, values5);
                    cursor5.close();
                    db7.close();
                }

                itemlist.clear();
                poslist.clear();
                IDlist.clear();
                attendancelist.clear();
                partytypelist.clear();
                emaillist.clear();

                if (GenericMethods.isConnected(getApplicationContext())) {
                    Intent i1 = new Intent(anulom.executioner5.com3.anulom.newstatusinfo.this, SendmultiPartyReport.class);
                    startService(i1);
                } else {
                    System.out.println("Offline");
                    finish();
                }

                if (check1.isChecked()) {

                    //System.out.println("1 check");
                    status = "0";
                    flag = "1";
                    reason = slottime.getText().toString();
                } else if (check2.isChecked()) {
                    //System.out.println("2 check");
                    status = "1";
                    flag = "1";

                } else if (check3.isChecked()) {
                    //System.out.println("3 check");
                    status = "2";
                    flag = "1";
                    reason = slottime.getText().toString();
                } else if (check4.isChecked()) {
                    //System.out.println("4 check");
                    status = "3";
                    flag = "1";

                }


                if (flag == "1") {
                    SQLiteDatabase db0 = db.getWritableDatabase();
                    ContentValues values5 = new ContentValues();
                    values5.put(DBManager.TableInfo.KEYID, ID1);
                    values5.put(DBManager.TableInfo.DOCU, document);
                    values5.put(DBManager.TableInfo.AppointmentId, appointment);
                    values5.put(DBManager.TableInfo.STATUS, status);
                    values5.put(DBManager.TableInfo.reason, reason);
                    values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition5 = DBManager.TableInfo.DOCU + " =?";
                    Cursor cursor5 = db0.query(MULTIWORK, null, condition5, new String[]{DBManager.TableInfo.AppointmentId}, null, null, null);
                    long status5 = db0.insert(MULTIWORK, null, values5);
                    //System.out.println( "Partieswork insert : "+status5+reason+status);
                    cursor5.close();
                    db0.close();
                    Intent i2 = new Intent(newstatusinfo.this, AppointmentstatusPost.class);
                    startService(i2);
                    flag = "0";
                }
                finish();
            }
        });


    }

    public void removefunction(LinearLayout rootView) {

        for (int j = 0; j < getAlldataList.size(); j++) {

            TextView tv = (TextView) findViewById(n);

            rootView.removeView(tv);
            List<String> values = new ArrayList<String>();
            if (getAlldataList.get(j).get("document_id").equals(document)) {
                if (getAlldataList.get(j).get("party_type").equals("1") && Integer.valueOf(db.getPartiesReport(db).get(j).get("poa")) > 0) {
                    System.out.println("No spinner");
                } else {
                    n_parties = n_parties + 1;
                    Spinner spin = (Spinner) findViewById(j);
                    rootView.removeView(spin);
                }
            }
        }

        Button update2 = (Button) findViewById(k);
        rootView.removeView(update2);

    }

}





