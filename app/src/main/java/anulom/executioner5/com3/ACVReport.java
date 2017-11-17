package anulom.executioner5.com3.anulom;

import java.util.ArrayList;
import java.util.List;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.SendReportService;

public class ACVReport extends AppCompatActivity implements OnItemSelectedListener {
    EditText etamount, etdistance, etappaddress;
    String rkey, docid, appadress, amount, distance, itemtransport, itemapointment, app_id, transportType, apointmentFor = "", exeid;
    Button submit;
    String umail = Login.umailid;
    List<String> categoriest;
    List<String> appointmentlist;
    private Toolbar toolbar;
    private TextView reportkey;
    private String key1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvreport);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Acvr Report");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rkey = getIntent().getStringExtra("ReportKey");

        docid = getIntent().getStringExtra("DocumentId");
        app_id = getIntent().getStringExtra("AppointmentId");

        appadress = getIntent().getStringExtra("AppointmentAddress");


        distance = getIntent().getStringExtra("Distance");

        amount = getIntent().getStringExtra("Amount");

        transportType = getIntent().getStringExtra("TransportType");

        apointmentFor = getIntent().getStringExtra("ApointmentFor");

        exeid = getIntent().getStringExtra("Executionerid");
        key1 = "Request No: " + rkey;

        etappaddress = (EditText) findViewById(R.id.etaptadd);
        reportkey = (TextView) findViewById(R.id.reportkey_text);
        reportkey.setText(key1);
        etappaddress.setText(appadress);

        etdistance = (EditText) findViewById(R.id.etdistance);
        if (distance.equals("null")) {
            etdistance.setText("");
        } else {
            etdistance.setText(distance);
        }

        etamount = (EditText) findViewById(R.id.etamount);
        if (amount.equals("null")) {
            etamount.setText("");
        } else {
            etamount.setText(amount);
        }


        submit = (Button) findViewById(R.id.btnSubmit);

        Spinner spinnerA = (Spinner) findViewById(R.id.spinner1);
        Spinner spinnert = (Spinner) findViewById(R.id.spinner2);
        spinnerA.setOnItemSelectedListener(this);
        spinnert.setOnItemSelectedListener(this);


        if (apointmentFor.equals("null")) {
//			transportType="0";
            appointmentlist = new ArrayList<String>();
            appointmentlist.clear();
            appointmentlist.add("");
            appointmentlist.add("Owner");
            appointmentlist.add("Tenant");
            appointmentlist.add("Both");
            appointmentlist.add("Witness");
        } else if (apointmentFor.equals("1")) {
            appointmentlist = new ArrayList<String>();
            appointmentlist.clear();
            appointmentlist.add("Owner");
            appointmentlist.add("Tenant");
            appointmentlist.add("Both");
            appointmentlist.add("Witness");
            appointmentlist.add("");
        } else if (apointmentFor.equals("2")) {
            appointmentlist = new ArrayList<String>();
            appointmentlist.clear();
            appointmentlist.add("Tenant");
            appointmentlist.add("Owner");
            appointmentlist.add("Both");
            appointmentlist.add("Witness");
            appointmentlist.add("");
        } else if (apointmentFor.equals("3")) {
            appointmentlist = new ArrayList<String>();
            appointmentlist.clear();
            appointmentlist.add("Both");
            appointmentlist.add("Owner");
            appointmentlist.add("Tenant");
            appointmentlist.add("Witness");
            appointmentlist.add("");
        } else if (apointmentFor.equals("4")) {
            appointmentlist = new ArrayList<String>();
            appointmentlist.clear();
            appointmentlist.add("Witness");
            appointmentlist.add("Both");
            appointmentlist.add("Owner");
            appointmentlist.add("Tenant");
            appointmentlist.add("");
        }


        if (transportType.trim().equals("null") || transportType.isEmpty()) {
//			transportType="0";
            categoriest = new ArrayList<String>();
            categoriest.clear();
            categoriest.add("");
            categoriest.add("bus");
            categoriest.add("train");
            categoriest.add("two_wheeler");
            categoriest.add("combination");

        } else if (transportType.equals("bus")) {
//			transportType="1";
            categoriest = new ArrayList<String>();
            categoriest.clear();
            categoriest.add("bus");
            categoriest.add("train");
            categoriest.add("two_wheeler");
            categoriest.add("combination");
            categoriest.add("");

        } else if (transportType.equals("train")) {
//			transportType="2";
            categoriest = new ArrayList<String>();
            categoriest.clear();
            categoriest.add("train");
            categoriest.add("bus");
            categoriest.add("two_wheeler");
            categoriest.add("combination");
            categoriest.add("");
        } else if (transportType.equals("two_wheeler")) {
//			transportType="3";
            categoriest = new ArrayList<String>();
            categoriest.clear();
            categoriest.add("two_wheeler");
            categoriest.add("bus");
            categoriest.add("train");
            categoriest.add("combination");
            categoriest.add("");
        } else if (transportType.equals("combination")) {
            //transportType="4";
            categoriest = new ArrayList<String>();
            categoriest.clear();
            categoriest.add("combination");
            categoriest.add("two_wheeler");
            categoriest.add("bus");
            categoriest.add("train");
            categoriest.add("");
        }


        ArrayAdapter<String> dataAdaptert = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay,
                categoriest);


        ArrayAdapter<String> dataAdapterA = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay, appointmentlist);

        dataAdaptert.setDropDownViewResource(R.layout.spinnertextdisplay);
        dataAdapterA.setDropDownViewResource(R.layout.spinnertextdisplay);


        spinnert.setAdapter(dataAdaptert);
        spinnerA.setAdapter(dataAdapterA);

        return;


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner1:
                itemapointment = parent.getItemAtPosition(position).toString();
                if (itemapointment.equals("")) {
                    itemapointment = "0";
                } else if (itemapointment.equals("Owner")) {
                    itemapointment = "1";
                } else if (itemapointment.equals("Tenant")) {
                    itemapointment = "2";
                } else if (itemapointment.equals("Both")) {
                    itemapointment = "3";
                } else if (itemapointment.equals("Witness")) {
                    itemapointment = "4";
                }
                break;
            case R.id.spinner2:
                itemtransport = parent.getItemAtPosition(position).toString();
                break;
            default:
        }
        update();
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // showToast("Spinner2: unselected");
    }


    private void update() {
        // TODO Auto-generated method stub
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                distance = etdistance.getText().toString();

                amount = etamount.getText().toString();
                appadress = etappaddress.getText().toString();
                DBOperation db = new DBOperation(ACVReport.this);


                String synstatus1 = "ASYNC";
                db.Update1(db, app_id, docid, appadress, exeid,
                        itemapointment, distance, amount, itemtransport, synstatus1);
                Toast.makeText(ACVReport.this, "Data insert succssfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ACVReport.this, SendReportService.class);
                startService(intent);
                finish();

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}



