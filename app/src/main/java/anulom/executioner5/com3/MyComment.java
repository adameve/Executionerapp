package anulom.executioner5.com3.anulom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.SendCommentService;


public class MyComment extends AppCompatActivity implements OnItemSelectedListener {

    // Spinner element
    Spinner spinner, spinner_remimnder;
    DBOperation db;

    EditText etcomment, edt_on_date;
    String docid = "", owner = "", comment = "", reminder = "";
    Button submit, btn_date;
    String isdone = "0";
    //    String username = Login.umailid;
    String username = Login.umailid;
    private String current_Date = "";
    private Calendar myCalendar;

    private String commentID = "1011";
    private Toolbar toolbar;
    private String reportkey = "", position = "";
    private TextView requestno;

    DatePickerDialog.OnDateSetListener date1;
    private String reminderdate = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycomment);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Owner Comment");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//		getSupportActionBar().setIcon(R.drawable.icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner_remimnder = (Spinner) findViewById(R.id.spinner_remimnder);
        etcomment = (EditText) findViewById(R.id.etcomment);
        submit = (Button) findViewById(R.id.btnsubmit);
        requestno = (TextView) findViewById(R.id.text_requestno);
        btn_date = (Button) findViewById(R.id.btn_date);
        edt_on_date = (EditText) findViewById(R.id.edt_on_date);
        myCalendar = Calendar.getInstance();

        docid = getIntent().getStringExtra("DocumentId");
        position = getIntent().getStringExtra("Position");

        System.out.println("commfrmmycomment" + docid);
        reportkey = getIntent().getStringExtra("reportkey");
        String rpotkey = "Request No: " + reportkey;
        requestno.setText(rpotkey);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner_remimnder.setOnItemSelectedListener(this);

        // loading spinner with newly added data
        loadSpinnerData();
        loadspinnerRemimnderData();
        btn_date.setOnClickListener(new OnClickListener() {
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
                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        edt_on_date.setText(sdf.format(myCalendar.getTime()));

                    }

                };
                new DatePickerDialog(MyComment.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    /**
     * Function to load the spinner data from SQLite database
     */
    private void loadSpinnerData() {
        // database handler
        DBOperation db = new DBOperation(getApplicationContext());
//         Spinner Drop down elements
        List<String> lables = db.getAllLabels();
        lables.add(0, "");
        // Creating SearchItemAdapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinnertextdisplay);

        // attaching data SearchItemAdapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void loadspinnerRemimnderData() {


//         Spinner Drop down elements
        List<String> spinnerItem = new ArrayList<String>();
        spinnerItem.add("");
        spinnerItem.add("Morning");
        spinnerItem.add("Afternoon");
        spinnerItem.add("Evening");
        // Creating SearchItemAdapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinnertextdisplay, spinnerItem);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinnertextdisplay);

        // attaching data SearchItemAdapter to spinner
        spinner_remimnder.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner:
                owner = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner_remimnder:
                reminder = parent.getItemAtPosition(position).toString();
                break;
        }

        update();

    }

    private void update() {
        // TODO Auto-generated method stub
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                comment = etcomment.getText().toString();
                String edittextDate = edt_on_date.getText().toString();
                String remindertype = "";


                if (comment.equals("")) {
                    Toast.makeText(MyComment.this, "Please enter comment", Toast.LENGTH_LONG).show();
                } else {
//                    System.out.println("Comment....................: " + comment);
//                    System.out.println("docidcomment:"+docid);

                    current_Date = GenericMethods.getCurrentDate();

                    if (reminder.equals("Morning")) {
                        remindertype = "09:00";
                    }
                    if (reminder.equals("Afternoon")) {
                        remindertype = "12:00";
                    }
                    if (reminder.equals("Evening")) {
                        remindertype = "16:00";
                    }
                    if (!edittextDate.equals("")) {

                        reminderdate = edittextDate + " " + remindertype;
                    }

                    DBOperation db = new DBOperation(MyComment.this);
                    String commentID = randomInteger(0, 999999);

                    while (db.checkCommentID(db, commentID)) {
                        commentID = randomInteger(0, 999999);
                    }
                    System.out.println("commfrmcomment" + docid + " " + reportkey);
                    db.InsertRecord3(db, reportkey, comment, username, owner, commentID, isdone, current_Date, GenericMethods.AsyncStatus, reminderdate);
                    Toast.makeText(MyComment.this, "Data insert Successfully  ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MyComment.this, SendCommentService.class);
                    startService(intent);
                    finish();
//                    Intent intent1 = new Intent(MyComment.this, CommentInfo.class);
//                   GenericMethods.comm_position=position;
//                    GenericMethods.commdoc=docid;
//                    GenericMethods.commkey=reportkey;
//                    startActivityForResult(intent1, 12345);
                }

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public String randomInteger(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum + "";
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}