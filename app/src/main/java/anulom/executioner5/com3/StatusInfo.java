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

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.StatusReportService;
import anulom.executioner5.com3.anulom.services.AppointmentstatusPost;

import static anulom.executioner5.com3.anulom.R.id.spinnerO;
import static anulom.executioner5.com3.anulom.R.id.spinnerT;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.MULTIWORK;


public class StatusInfo extends AppCompatActivity implements OnItemSelectedListener {
    String rkey, biocomp, biocomp1, docid, regfromcomp, witness, postStatus;
    TextView tvreqNo, t1, t2;
    EditText e1;
    Button update;
    DBOperation database;
    CheckBox check, check1, check2, check3;
    private SharedPreferences usermail;
    String ID, username2 = "", password2 = "", status, flag = "0", appointment, reason = "";
    String item, item1, item2, item3, itemS = "", item1S = "", item2S = "", item3S = "";
    private Toolbar toolbar;
    List<String> statuslist = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusinfo);
        database = new DBOperation(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Biometric Status");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//		getSupportActionBar().setIcon(R.drawable.icon);
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");
        t1 = (TextView) findViewById(R.id.text3);
        t2 = (TextView) findViewById(R.id.status2);
        final Spinner spinnerO = (Spinner) findViewById(R.id.spinnerO);
        final Spinner spinnerT = (Spinner) findViewById(R.id.spinnerT);
        final Spinner spinnerW1 = (Spinner) findViewById(R.id.spinnerw1);
        final Spinner spinnerW2 = (Spinner) findViewById(R.id.spinnerw2);
        e1 = (EditText) findViewById(R.id.edit3);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                GenericMethods.ctr2 = 0;
                GenericMethods.ctr3 = 0;

            }
        });
//
        rkey = getIntent().getStringExtra("ReportKey");
        docid = getIntent().getStringExtra("DocumentId");
        biocomp = getIntent().getStringExtra("BiometricComp");
        biocomp1 = getIntent().getStringExtra("BiometricComp1");
        regfromcomp = getIntent().getStringExtra("Reg_From_Comp");
        witness = getIntent().getStringExtra("Witness");
        appointment = getIntent().getStringExtra("appointment_id");
        postStatus = getIntent().getStringExtra("post_status");
        System.out.println("from JSON:" + postStatus);
        check = (CheckBox) findViewById(R.id.wrkcomp);
        check1 = (CheckBox) findViewById(R.id.wrkcomp1);
        check2 = (CheckBox) findViewById(R.id.wrkcomp2);
        check3 = (CheckBox) findViewById(R.id.wrkcomp3);
        final TextView biometric = (TextView) findViewById(R.id.status1);
        final TextView Ownerbio = (TextView) findViewById(R.id.tvownerbio);
        final TextView tenantbio = (TextView) findViewById(R.id.tvtenantbio);
        final TextView witness1bio = (TextView) findViewById(R.id.tvwitness1bio);
        final TextView witness2bio = (TextView) findViewById(R.id.tvwitness2bio);

        String key1 = "Request No: " + rkey;
        tvreqNo = (TextView) findViewById(R.id.tvreqno);
        tvreqNo.setText(key1);
        tvreqNo.setTextIsSelectable(true);
        update = (Button) findViewById(R.id.btnupdate);
        ArrayList<HashMap<String, String>> statuslist = database.getmultipartycheck(database);
        for (int i = 0; i < statuslist.size(); i++) {
            if (docid.equals(statuslist.get(i).get("docid"))) {
                postStatus = statuslist.get(i).get("status");
                System.out.println(postStatus);
            }
        }
        if (postStatus.equals("0")) {
            check.setChecked(true);
            biometric.setVisibility(View.VISIBLE);
            Ownerbio.setVisibility(View.VISIBLE);
            tenantbio.setVisibility(View.VISIBLE);
            witness1bio.setVisibility(View.VISIBLE);
            witness2bio.setVisibility(View.VISIBLE);
            spinnerO.setVisibility(View.VISIBLE);
            spinnerT.setVisibility(View.VISIBLE);
            spinnerW1.setVisibility(View.VISIBLE);
            spinnerW2.setVisibility(View.VISIBLE);

        }
        if (postStatus.equals("1")) {
            check1.setChecked(true);

        }
        if (postStatus.equals("2")) {
            check2.setChecked(true);

        }
        if (postStatus.equals("3")) {
            check3.setChecked(true);

        }
        if (postStatus.equals("99")) {

            check.setVisibility(View.GONE);
            check1.setVisibility(View.GONE);
            check2.setVisibility(View.GONE);
            check3.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
        }


        update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DBOperation db = new DBOperation(StatusInfo.this);
                long result = db.UpdateAllRecord(db, docid, itemS, item1S, item2S, item3S);
                //Update Successfully
//				if(itemS==""||item1S==""||item2S==""||item3S==""){
//					System.out.println("Biometric values " +itemS +" " +item1S +" " +item2S +" "+ item3S);
//				}
                if (result == 1) {
                    Toast.makeText(StatusInfo.this, "Data insert Successfull  ", Toast.LENGTH_LONG).show();
                }
                /*---------- Call  Appointment Status Web service -----------*/
                Intent intent = new Intent(StatusInfo.this, StatusReportService.class);
                startService(intent);
                finish();
                GenericMethods.ctr2 = 0;
                GenericMethods.ctr3 = 0;
                if (check.isChecked()) {

                    status = "0";
                    flag = "1";

                }
                if (check1.isChecked()) {


                    status = "1";
                    flag = "1";


                }

                if (check2.isChecked()) {

                    status = "2";
                    flag = "1";
                }

                if (check3.isChecked()) {

                    status = "3";
                    flag = "1";
                }

                if (flag == "1") {

                    SQLiteDatabase db0 = database.getWritableDatabase();
                    ContentValues values5 = new ContentValues();
                    values5.put(DBManager.TableInfo.KEYID, ID);
                    values5.put(DBManager.TableInfo.DOCU, docid);
                    values5.put(DBManager.TableInfo.AppointmentId, appointment);
                    values5.put(DBManager.TableInfo.STATUS, status);
                    values5.put(DBManager.TableInfo.reason, reason);
                    values5.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                    String condition5 = DBManager.TableInfo.DOCU + " =?";
                    Cursor cursor5 = db0.query(MULTIWORK, null, condition5, new String[]{DBManager.TableInfo.AppointmentId}, null, null, null);
                    long status5 = db0.insert(MULTIWORK, null, values5);
                    System.out.println("Postcall insert : " + status5);
                    cursor5.close();
                    db0.close();
                    Intent i2 = new Intent(StatusInfo.this, AppointmentstatusPost.class);
                    startService(i2);
                    flag = "0";

                }
                finish();


            }
        });
        final DBOperation db = new DBOperation(StatusInfo.this);
        ArrayList<HashMap<String, String>> selStatus = db.getSelectedData(db, docid);
        if (selStatus.size() > 0) {
            biocomp1 = selStatus.get(0).get("OwnBio");
            biocomp = selStatus.get(0).get("TenantBio");
            regfromcomp = selStatus.get(0).get("Wit1");
            witness = selStatus.get(0).get("Wit2");
        }
        // Spinner element


        // Spinner click listener
        spinnerO.setOnItemSelectedListener(this);
        spinnerT.setOnItemSelectedListener(this);
        spinnerW1.setOnItemSelectedListener(this);
        spinnerW2.setOnItemSelectedListener(this);

        List<String> categoriesT = new ArrayList<String>();
        if (biocomp.equals("1")) {
            categoriesT.add("Yes");
            categoriesT.add("Thumb Not Match");
            categoriesT.add("");
        } else if (biocomp.equals("0")) {
            categoriesT.add("Thumb Not Match");
            categoriesT.add("Yes");
            categoriesT.add("");
        } else {
            categoriesT.add("");
            categoriesT.add("Thumb Not Match");
            categoriesT.add("Yes");
        }

        List<String> categoriesO = new ArrayList<String>();
        if (biocomp1.equals("1")) {
            categoriesO.add("Yes");
            categoriesO.add("Thumb Not Match");
            categoriesO.add("");
        } else if (biocomp1.equals("0")) {
            categoriesO.add("Thumb Not Match");
            categoriesO.add("Yes");
            categoriesO.add("");
        } else {
            categoriesO.add("");
            categoriesO.add("Thumb Not Match");
            categoriesO.add("Yes");
        }

        List<String> categoriesW1 = new ArrayList<String>();
        if (regfromcomp.equals("1")) {
            categoriesW1.add("Yes");
            categoriesW1.add("No");
            categoriesW1.add("");
        } else if (regfromcomp.equals("0")) {
            categoriesW1.add("No");
            categoriesW1.add("Yes");
            categoriesW1.add("");
        } else {
            categoriesW1.add("");
            categoriesW1.add("No");
            categoriesW1.add("Yes");
        }

        List<String> categoriesW2 = new ArrayList<String>();
        if (witness.equals("1")) {
            categoriesW2.add("Yes");
            categoriesW2.add("No");
            categoriesW2.add("");

        } else if (witness.equals("0")) {
            categoriesW2.add("No");
            categoriesW2.add("Yes");
            categoriesW2.add("");
        } else {
            categoriesW2.add("");
            categoriesW2.add("No");
            categoriesW2.add("Yes");
        }

        List<String> categoriesPayments = new ArrayList<String>();
        categoriesPayments.add("");
        categoriesPayments.add("Stampduty Shortfall");
        categoriesPayments.add("Extra Visit");
        categoriesPayments.add("Partial Payment");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterO = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriesO);

        ArrayAdapter<String> dataAdapterT = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriesT);

        ArrayAdapter<String> dataAdapterW1 = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriesW1);

        ArrayAdapter<String> dataAdapterW2 = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriesW2);

        ArrayAdapter<String> dataAdapterPayments = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriesPayments);

        // Drop down layout style - list view with radio button
        dataAdapterO.setDropDownViewResource(R.layout.spinnertextdisplay);
        dataAdapterT.setDropDownViewResource(R.layout.spinnertextdisplay);
        dataAdapterW1.setDropDownViewResource(R.layout.spinnertextdisplay);
        dataAdapterW2.setDropDownViewResource(R.layout.spinnertextdisplay);
        dataAdapterPayments.setDropDownViewResource(R.layout.spinnertextdisplay);

        // attaching data adapter to spinner
        spinnerO.setAdapter(dataAdapterO);
        spinnerT.setAdapter(dataAdapterT);
        spinnerW1.setAdapter(dataAdapterW1);
        spinnerW2.setAdapter(dataAdapterW2);
        // spinnerPayment.setAdapter(dataAdapterPayments);
//        SQLiteDatabase dbDetails_comm = database.getWritableDatabase();
//		dbDetails_comm.delete(TABLE_CHECK_JOB, null, null);
//		System.out.println("Check table deleted");


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                System.out.println("id:" + check.isChecked() + "reason:" + reason);

                username2 = usermail.getString("username", "");
                if (check.isChecked()) {
                    t1.setVisibility(View.GONE);
                    e1.setVisibility(View.GONE);

                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);

                    biometric.setVisibility(View.VISIBLE);
                    Ownerbio.setVisibility(View.VISIBLE);
                    tenantbio.setVisibility(View.VISIBLE);
                    witness1bio.setVisibility(View.VISIBLE);
                    witness2bio.setVisibility(View.VISIBLE);
                    spinnerO.setVisibility(View.VISIBLE);
                    spinnerT.setVisibility(View.VISIBLE);
                    spinnerW1.setVisibility(View.VISIBLE);
                    spinnerW2.setVisibility(View.VISIBLE);


                } else {

                    biometric.setVisibility(View.INVISIBLE);
                    Ownerbio.setVisibility(View.INVISIBLE);
                    tenantbio.setVisibility(View.INVISIBLE);
                    witness1bio.setVisibility(View.INVISIBLE);
                    witness2bio.setVisibility(View.INVISIBLE);
                    spinnerO.setVisibility(View.INVISIBLE);
                    spinnerT.setVisibility(View.INVISIBLE);
                    spinnerW1.setVisibility(View.INVISIBLE);
                    spinnerW2.setVisibility(View.INVISIBLE);

                }

            }
        });


        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (check1.isChecked()) {

                    check.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    t1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    biometric.setVisibility(View.GONE);
                    Ownerbio.setVisibility(View.GONE);
                    tenantbio.setVisibility(View.GONE);
                    witness1bio.setVisibility(View.GONE);
                    witness2bio.setVisibility(View.GONE);
                    spinnerO.setVisibility(View.GONE);
                    spinnerT.setVisibility(View.GONE);
                    spinnerW1.setVisibility(View.GONE);
                    spinnerW2.setVisibility(View.GONE);

                } else {
                    t1.setVisibility(View.GONE);
                    e1.setVisibility(View.GONE);
                }
            }
        });


        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (check2.isChecked()) {
                    t1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);

                    check.setChecked(false);
                    check1.setChecked(false);
                    check3.setChecked(false);

                    biometric.setVisibility(View.GONE);
                    Ownerbio.setVisibility(View.GONE);
                    tenantbio.setVisibility(View.GONE);
                    witness1bio.setVisibility(View.GONE);
                    witness2bio.setVisibility(View.GONE);
                    spinnerO.setVisibility(View.GONE);
                    spinnerT.setVisibility(View.GONE);
                    spinnerW1.setVisibility(View.GONE);
                    spinnerW2.setVisibility(View.GONE);

                } else {
                    t1.setVisibility(View.GONE);
                    e1.setVisibility(View.GONE);
                }
            }

        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //username2 = usermail.getString("username", "");
                if (check3.isChecked()) {


                    check.setChecked(false);
                    check1.setChecked(false);
                    check2.setChecked(false);

                    t1.setVisibility(View.GONE);
                    e1.setVisibility(View.GONE);
                    biometric.setVisibility(View.GONE);
                    Ownerbio.setVisibility(View.GONE);
                    tenantbio.setVisibility(View.GONE);
                    witness1bio.setVisibility(View.GONE);
                    witness2bio.setVisibility(View.GONE);
                    spinnerO.setVisibility(View.GONE);
                    spinnerT.setVisibility(View.GONE);
                    spinnerW1.setVisibility(View.GONE);
                    spinnerW2.setVisibility(View.GONE);

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        GenericMethods.ctr2 = 0;
        GenericMethods.ctr3 = 0;
//                    Intent i = new Intent(NextActivity.this, Login.class);
//
//                startActivity(i);


//        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub

        switch (parent.getId()) {
            // OWNER
            case spinnerO:
                item = parent.getItemAtPosition(position).toString();
                if (item.matches("Yes")) {
                    itemS = "1";

                } else {
                    if (item.matches("Thumb Not Match")) {
                        itemS = "0";
                    } else {
                        itemS = "";
                    }
                }
                break;
            // TENANT
            case spinnerT:
                item1 = parent.getItemAtPosition(position).toString();
                if (item1.matches("Yes")) {
                    item1S = "1";
                } else {
                    if (item1.matches("Thumb Not Match")) {
                        item1S = "0";
                    } else {
                        item1S = "";
                    }
                }
                break;
            // WITNESS1
            case R.id.spinnerw1:
                item2 = parent.getItemAtPosition(position).toString();

                if (item2.matches("Yes")) {
                    item2S = "1";
                } else {
                    if (item2.matches("No")) {
                        item2S = "0";
                    } else {
                        item2S = "";
                    }

                }
                break;
            // WITNESS2
            case R.id.spinnerw2:
                item3 = parent.getItemAtPosition(position).toString();
                if (item3.matches("Yes")) {
                    item3S = "1";
                } else {
                    if (item3.matches("No")) {
                        item3S = "0";
                    } else {
                        item3S = "";
                    }

                }
                break;
            default:

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }


}