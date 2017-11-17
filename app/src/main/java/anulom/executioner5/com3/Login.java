package anulom.executioner5.com3.anulom;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.services.GetCommentService;
import anulom.executioner5.com3.anulom.services.LocationTrackingService;

import static android.R.attr.x;
import static anulom.executioner5.com3.anulom.R.id.version;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.ATTENDEES;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.ATT_STATUS;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.PARTIES1;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.PAYMENT;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.TABLE_NAME;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.TABLE_TASK;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.TASK_NAME;


public class Login extends AppCompatActivity implements OnClickListener {
    int counter;
    Button submit, cancel;
    CheckBox check;
    public static EditText etusermailid, etpassword;
    public static String umailid = "", password = "";

    private SharedPreferences usermail;
    private String username2 = "", password2 = "";
    private Toolbar toolbar;
    String temp = "";

    private ProgressDialog pDialog;
    String startnewdate, startnewtime1, startnewtime2;


    ArrayList<String> ownername = new ArrayList<>();
    ArrayList<String> ownercontact = new ArrayList<>();
    ArrayList<String> documentid = new ArrayList<>();
    ArrayList<String> appointmentaddress = new ArrayList<>();
    ArrayList<String> executionerid = new ArrayList<>();
    ArrayList<String> appointmentid = new ArrayList<>();
    ArrayList<String> commentid = new ArrayList<>();
    ArrayList<String> Addpayid = new ArrayList<>();
    ArrayList<String> userlist = new ArrayList<>();

    ArrayList<HashMap<String, String>> getCommentDatalist = null;
    ArrayList<HashMap<String, String>> getPaymentDataList = null;
    ArrayList<HashMap<String, String>> getAlldataList = null;
    ArrayList<HashMap<String, String>> getuserdataList = null;
    JSONArray ja = null;
    JSONArray ja3 = null;
    JSONArray ja4 = null;
    JSONArray ja5 = null;
    JSONArray ja9 = null;
    JSONArray ja10 = null;
    JSONArray ja11 = null;
    JSONArray ja20 = null;

    GenericMethods response, response2;
    String APPOINTMENTURL = "", WITNESSURL = "";
    String ID1;
    DBOperation db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        db = new DBOperation(Login.this);
        arraylistclear();
        callLocationTracking();

        response = new GenericMethods();

        response2 = new GenericMethods();


        initializedata();
        setOnClickListener();
    }

    private void setOnClickListener() {
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }

    private void arraylistclear() {
        ownername.clear();
        ownercontact.clear();
        documentid.clear();
        appointmentaddress.clear();
        executionerid.clear();
        appointmentid.clear();
        commentid.clear();
        Addpayid.clear();
        userlist.clear();
    }

    private void initializedata() {

        etusermailid = (EditText) findViewById(R.id.etmailid);
        etpassword = (EditText) findViewById(R.id.etpassword);
        check = (CheckBox) findViewById(R.id.cbShowPwd);


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (check.isChecked() && etpassword.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Type  password", Toast.LENGTH_SHORT).show();

                } else {
                    if (check.isChecked()) {
                        etpassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        etpassword.setSelection(etpassword.length());

                    } else {
                        etpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etpassword.setSelection(etpassword.length());
                    }

                }
            }
        });
        //etpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        submit = (Button) findViewById(R.id.btnsubmit);
        cancel = (Button) findViewById(R.id.btncancel);
        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");
        if (!username2.equals("") && !password2.equals("")) {
            umailid = username2;
            GenericMethods.email = umailid;

//            System.out.println("name"+umailid+"paswd"+password2);
//            Intent intent = new Intent(Login.this, Payment.class);
//            startActivity(intent);
            Intent intent1 = new Intent(Login.this, NextActivity.class);
            startActivity(intent1);

            finish();
            return;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" Login");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsubmit:
                umailid = etusermailid.getText().toString().toLowerCase();
                password = etpassword.getText().toString();
                if (umailid.equals("")) {
                    Toast.makeText(Login.this, "Enter Mail Id", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.equals("")) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                new LoginDetails().execute();

                break;
            case R.id.btncancel:
                new AlertDialog.Builder(Login.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                        .setMessage("Are you sure you want to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                                System.exit(0);
                            }
                        }).setNegativeButton("No", null).show();
                break;
            case R.id.etpassword:
                counter = counter + 1;
                if (counter % 2 == 0) {
                    etpassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    etpassword.setTransformationMethod(null);
                }
            default:
        }
    }


    private class LoginDetails extends AsyncTask<String, String, String> {
        String token = "DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7";

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {

            String strResponsePost = "";

            try {
                String strJS = createServiceJson(umailid, password, token);
                strResponsePost = response.doPostRequest(GenericMethods.LOGINURL, strJS);
                Log.d("Response: ", "> " + strResponsePost);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            return strResponsePost;

        }


        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            pDialog.dismiss();

            if (result != "") {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String jStrResult = jsonObject.getString("status");

                    temp = jStrResult;
                    if (jStrResult.equals("true")) {

                        SharedPreferences.Editor editor = usermail.edit();
                        editor.putString("username", umailid);
                        editor.putString("password", password);
                        editor.commit();


//                        http://ec2-52-41-205-148.us-west-2.compute.amazonaws.com:3000//api/v1/get_exec_data?get_auth_token=DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7&exec_email=vamananulom@gmail.com&password=XXX


                    } else {
                        Toast.makeText(getBaseContext(), "Enter Valid Credentials", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
////
                Toast.makeText(getBaseContext(), "You Are Offline!!!", Toast.LENGTH_LONG).show();
            }


            username2 = usermail.getString("username", "");
            password2 = usermail.getString("password", "");

            if (umailid.equals(username2) && password.equals(password2)) {
                APPOINTMENTURL = GenericMethods.MAIN_URL+"/api/v2/get_exec_data?get_auth_token=DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7&exec_email="+username2+"&password="+password2+"&version=16.4.4";
                WITNESSURL = GenericMethods.MAIN_URL1+"/cmt/api/v1/comment_data/get_task?get_auth_token=DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7&user="+username2;


                String TAG_NAME0 = "status";
                GenericMethods.email = umailid;

//                System.out.println("name"+umailid+"paswd"+password2);

                new GetContacts().execute();
//                Intent inte1 = new Intent(Login.this,Payment.class);
//                startService(inte1);
                Intent inte = new Intent(Login.this, GetCommentService.class);
                startService(inte);

            }
//            else {
////                        pDialog.dismiss();
//                Toast.makeText(getBaseContext(), "Please Enter valid Details", Toast.LENGTH_LONG).show();
//            }


        }

    }


    private String createServiceJson(String username, String password, String token) {
        try {
            JSONObject app = new JSONObject();
            app.put("email", username);
            app.put("password", password);
            JSONObject tokenjson = new JSONObject();
            tokenjson.put("token", token);
            JSONObject main = new JSONObject();
            main.put("user_detail", app);
            main.put("auth_token", tokenjson);
            String json = "";
            json = main.toString();
            return json;

        } catch (Exception e) {
        }

        return null;
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Fetching Data...");
            pDialog.setCancelable(false);
            pDialog.show();
//            System.out.println("URL" + url);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String jsonStr = "";
            String jsonStr1 = "";
            String jsonStr2 = "";
            String jsonStr3 = "";

            try {
                jsonStr = response.doGetRequest(APPOINTMENTURL);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Response: ", "> " + jsonStr);
            getAlldataList = db.getAllData(db);
            System.out.println("Size contact " + getAlldataList.size());
            for (int i = 0; i < getAlldataList.size(); i++) {
                documentid.add(getAlldataList.get(i).get("Did"));
                appointmentid.add(getAlldataList.get(i).get("Aid"));
                ownername.add(getAlldataList.get(i).get("Oname"));
                executionerid.add(getAlldataList.get(i).get("Exid"));
            }
            getCommentDatalist = db.getCommentlist(db);
            for (int i = 0; i < getCommentDatalist.size(); i++) {
                commentid.add(getCommentDatalist.get(i).get("Cid"));
            }

            getuserdataList = db.getdataList(db);
            for (int i = 0; i < getuserdataList.size(); i++) {
                userlist.add(getuserdataList.get(i).get("UserId"));
            }
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    ja = jsonObj.getJSONArray(GenericMethods.TAG_NAME);
//                    ja1 = jsonObj.getJSONArray(GenericMethods.TAG_NAME1);
//                    ja2 = jsonObj.getJSONArray(GenericMethods.TAG_NAME2);
                    ja3 = jsonObj.getJSONArray(GenericMethods.TAG_NAME3);
                    ja4 = jsonObj.getJSONArray(GenericMethods.TAG_NAME4);
                    ja20 = jsonObj.getJSONArray(GenericMethods.TAG_NAME14);

                    for (int i = 0; i < ja.length(); i++) {
                        HashMap<String, String> mList = new HashMap<>();
                        JSONObject c = ja.getJSONObject(i);
                        String rtoken1 = c.getString(GenericMethods.TAG_RTOKEN);
                        String rkey1 = c.getString(GenericMethods.TAG_REPORT_KEY);
                        String uname1 = c.getString(GenericMethods.TAG_UNAME);
                        String email1 = c.getString(GenericMethods.TAG_UEMAIL);
                        String contact_number1 = c.getString(GenericMethods.TAG_UCONTACT);
                        String paddress1 = c.getString(GenericMethods.TAG_PADDRESS);
                        String oname1 = c.getString(GenericMethods.TAG_ONAME);
                        String ocontact1 = c.getString(GenericMethods.TAG_OCONTACT);
                        String oemail1 = c.getString(GenericMethods.TAG_OEMAIL);
                        String oaddress1 = c.getString(GenericMethods.TAG_OADDRESS);
                        String tname1 = c.getString(GenericMethods.TAG_TNAME);
                        String tcontact1 = c.getString(GenericMethods.TAG_TCONTACT);
                        String temail1 = c.getString(GenericMethods.TAG_TEMAIL);
                        String taddress1 = c.getString(GenericMethods.TAG_TADDRESS);
                        String status1 = c.getString(GenericMethods.TAG_STATUS);
                        String appdate = c.getString(GenericMethods.TAG_APPDATE);
                        String biocomp = c.getString(GenericMethods.TAG_BIO_COMP);
                        String appdate1 = c.getString(GenericMethods.TAG_APPDATE1);
                        String biocomp1 = c.getString(GenericMethods.TAG_BIO_COMP1);
                        String regfromcomp = c.getString(GenericMethods.TAG_REG_FROM_COMP);
                        String witness = c.getString(GenericMethods.TAG_WITNESS);
                        String shipadd = c.getString(GenericMethods.TAG_SHIP_ADDRESS);
                        String shipdiffadd = c.getString(GenericMethods.TAG_SHIP_DIFF_ADDRESS);
                        String strlatitude, strlongitude;
                        if (c.has(GenericMethods.TAG_CUSTOMER_LAT)) {
                            strlatitude = c.getString(GenericMethods.TAG_CUSTOMER_LAT);
                        } else strlatitude = "0";

                        if (c.has(GenericMethods.TAG_CUSTOMER_LONG)) {
                            strlongitude = c.getString(GenericMethods.TAG_CUSTOMER_LONG);
                        } else strlongitude = "0";

                        String docid = c.getString(GenericMethods.TAG_DOCID);
                        String appid = c.getString(GenericMethods.TAG_APPID);
                        String start1 = c.getString(GenericMethods.TAG_START1);
                        String start2 = c.getString(GenericMethods.TAG_START2);
                        String appaddress = c.getString(GenericMethods.TAG_APPADDRESS);
                        String exeid = c.getString(GenericMethods.TAG_EXECUTIONER_ID);
                        String appfor = c.getString(GenericMethods.TAG_APP_FOR);
                        String apptype = c.getString(GenericMethods.TAG_APPTYPE);
                        String landmark = c.getString(GenericMethods.TAG_LANDMARK);
                        String contact_person = c.getString(GenericMethods.TAG_CONTACTPERSON);
                        String post_status = c.getString(GenericMethods.TAG_POST_STATUS);
                        //System.out.println("contact:"+contact_person);
                        String distance1 = c.getString(GenericMethods.TAG_CUSTOMER_DISTANCE);
                        String amount1 = c.getString(GenericMethods.TAG_CUSTOMER_AMOUNT);
                        String transporttype1 = c.getString(GenericMethods.TAG_CUSTOMER_TRANS_TYPE);
                        JSONObject jsonatten = ja.getJSONObject(i);

                        JSONArray jsonattendees = jsonatten.getJSONArray(GenericMethods.TAG_NAME13);

                        if (jsonattendees != null) {


                            for (int j = 0; j < jsonattendees.length(); j++) {
                                JSONObject atten = jsonattendees.getJSONObject(j);

                                if (atten != null) {
                                    String name = atten.getString(GenericMethods.TAG_NAMEATTEND);
                                    String email = atten.getString(GenericMethods.TAG_EMAILATTEND);
                                    String contact = atten.getString(GenericMethods.TAG_CONTACTATTEND);
                                    //System.out.println("docid:"+i+" "+appid+" "+"name" + name + "email" + email + "contact" + contact);


                                    SQLiteDatabase db13 = db.getWritableDatabase();
                                    ContentValues values3 = new ContentValues();
                                    values3.put(DBManager.TableInfo.KEYID, ID1);
                                    values3.put(DBManager.TableInfo.nameattend, name);
                                    values3.put(DBManager.TableInfo.emailattend, email);
                                    values3.put(DBManager.TableInfo.contactattend, contact);
                                    values3.put(DBManager.TableInfo.appointattend, appid);
                                    values3.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                                    String condition3 = DBManager.TableInfo.nameattend + " =?";
                                    Cursor cursor3 = db13.query(ATTENDEES, null, condition3, new String[]{DBManager.TableInfo.emailattend}, null, null, null);
                                    long status3 = db13.insert(ATTENDEES, null, values3);
//                                    Log.d(TAG, "attendees insert : " + status3);
//                                    System.out.println("Success" + username2+appid);
                                    cursor3.close();
                                    db13.close();


                                }

                            }

                        }


                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat output1 = new SimpleDateFormat("HH:mm");
                        SimpleDateFormat output2 = new SimpleDateFormat("HH:mm");

                        java.util.Date date, time1, time2;
                        try {
                            date = sdf.parse(start1);
                            time1 = sdf.parse(start1);
                            time2 = sdf.parse(start2);
                            startnewdate = output.format(date);
                            startnewtime1 = output1.format(time1);
                            startnewtime2 = output2.format(time2);

                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (documentid.contains(docid)) {
//                            System.out.println("DOC ID API" + docid);

                            db.Update(db, docid, rtoken1, rkey1, uname1, email1, contact_number1, paddress1, oname1,
                                    ocontact1, oemail1, oaddress1, tname1, tcontact1, temail1, taddress1, status1,
                                    appdate, biocomp, appdate1, biocomp1, regfromcomp, witness, shipadd, shipdiffadd, strlatitude, strlongitude, apptype);
                        } else {
                            if (!db.DocumentID(db, docid)) {
                                db.InsertRecord(db, rtoken1, rkey1, uname1, email1, contact_number1, paddress1, oname1,
                                        ocontact1, oemail1, oaddress1, tname1, tcontact1, temail1, taddress1, status1,
                                        umailid, docid, appdate, biocomp, appdate1, biocomp1, regfromcomp, witness, shipadd,
                                        shipdiffadd, strlatitude, strlongitude, "SYNC", apptype);
                                System.out.println("docid:" + docid);
                            }
                        }
                        if (appointmentid.contains(appid)) {

                            db.Updateacvr(db, appid, docid, startnewdate, startnewtime1, startnewtime2, appaddress, exeid,
                                    appfor, distance1, amount1, transporttype1, contact_person, landmark, "", post_status);

                        } else {
                            db.InsertRecord2(db, docid, appid, startnewdate, startnewtime1, startnewtime2, appaddress,
                                    exeid, appfor, distance1, amount1, transporttype1, "SYNC", contact_person, landmark, "", post_status);
                            //System.out.println("Insert Appointment'''''''''''''''''''" + contact_person);
                        }
                    }


                    for (int l = 0; l < ja3.length(); l++) {
                        JSONObject c = ja3.getJSONObject(l);
                        String userid = c.getString(GenericMethods.TAG_USERID);
                        String username = c.getString(GenericMethods.TAG_USERNAME);
                        String email = c.getString(GenericMethods.TAG_EMAIL);
                        String role = c.getString(GenericMethods.TAG_ROLE);
                        String platformid = c.getString(GenericMethods.TAG_PLATFORMID);
                        String roleid = c.getString(GenericMethods.TAG_ROLEID);
                        String idu = c.getString(GenericMethods.TAG_IDu);

                        if (userlist.contains(userid)) {
                            db.Updateuser(db, userid, username, email, role, platformid, roleid, idu);

//                            System.out.println("Updated doc_________________" + userid);
                        } else {
                            if (!db.checkUserId(db, userid)) {
                                db.InsertRecord5(db, userid, username, email, role, platformid, roleid, idu);
                            }
                        }
                    }


                    SQLiteDatabase base = db.getWritableDatabase();
                    base.delete(PAYMENT, null, null);

                    for (int n = 0; n < ja4.length(); n++) {
                        JSONObject c = ja4.getJSONObject(n);
                        String DocumentId = c.getString(GenericMethods.TAG_DOCUMENTID);
                        String Amt = c.getString(GenericMethods.TAG_PAYAMOUNT);
                        String ReportKey = c.getString(GenericMethods.TAG_REPORTKEY);
//                                System.out.println("docid:" + c.getString(GenericMethods.TAG_DOCUMENTID));
//                                System.out.println("amount:" + c.getString(GenericMethods.TAG_PAYAMOUNT));
//                                System.out.println("ReportKey:" + c.getString(GenericMethods.TAG_REPORTKEY));

                        SQLiteDatabase db3 = db.getWritableDatabase();
                        ContentValues values3 = new ContentValues();
                        values3.put(DBManager.TableInfo.KEYID, ID1);
                        values3.put(DBManager.TableInfo.doc1, DocumentId);
                        values3.put(DBManager.TableInfo.rep1, ReportKey);
                        values3.put(DBManager.TableInfo.payamount, Amt);
                        values3.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                        String condition3 = DBManager.TableInfo.doc1 + " =?";
                        Cursor cursor3 = db3.query(PAYMENT, null, condition3, new String[]{DBManager.TableInfo.rep1}, null, null, null);
                        long status3 = db3.insert(PAYMENT, null, values3);
                        //Log.d(TAG, "Payment insert : " + status3);
//                                System.out.println("Success" + username2);
                        cursor3.close();
                        db3.close();


                    }


                    SQLiteDatabase basestatus = db.getWritableDatabase();
                    basestatus.delete(ATT_STATUS, null, null);

                    for (int n = 0; n < ja20.length(); n++) {
                        JSONObject c = ja20.getJSONObject(n);
                        String DocumentId = c.getString(GenericMethods.TAG_DOCUMENTID);
                        String att_status = c.getString(GenericMethods.Tag_att_status);
//                        System.out.println("Document Id:" + c.getString(GenericMethods.TAG_DOCUMENTID));
//                        System.out.println("Att_status:" + c.getString(GenericMethods.Tag_att_status));

                        SQLiteDatabase sqldb = db.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(DBManager.TableInfo.att_status, att_status);
                        sqldb.update(TABLE_NAME, values, DBManager.TableInfo.DocumentId + "=?", new String[]{DocumentId});
                        sqldb.close();
                        //System.out.println("hiiii"+att_status+DocumentId);
                    }


                    SQLiteDatabase base3 = db.getWritableDatabase();
                    base3.delete(PARTIES1, null, null);
//                            System.out.println("Parties DB Deleted");
                    ja5 = jsonObj.getJSONArray(GenericMethods.TAG_NAME5);


                    for (int n = 0; n < ja5.length(); n++) {
                        JSONObject c = ja5.getJSONObject(n);
                        String Document1 = c.getString(GenericMethods.TAG_DOCUMENT);
                        String Attendance = c.getString(GenericMethods.TAG_ATTENDANCE);
                        String name = c.getString(GenericMethods.TAG_NAMENEW);
                        String email = c.getString(GenericMethods.TAG_EMAILNEW);
                        String partytype = c.getString(GenericMethods.TAG_PARTYTYPE);
                        String poa = c.getString(GenericMethods.TAG_POA);
                        String contact = c.getString(GenericMethods.TAG_CONTACTNUMBER);
                        String biometric = c.getString(GenericMethods.TAG_BIOMETRIC);


                        SQLiteDatabase db4 = db.getWritableDatabase();
                        ContentValues values4 = new ContentValues();
                        values4.put(DBManager.TableInfo.KEYID, ID1);
                        values4.put(DBManager.TableInfo.DOCUMENT, Document1);
                        values4.put(DBManager.TableInfo.ATTENDANCE, Attendance);
                        values4.put(DBManager.TableInfo.NAMENEW, name);
                        values4.put(DBManager.TableInfo.EMAILNEW, email);
                        values4.put(DBManager.TableInfo.PARTYTYPE, partytype);
                        values4.put(DBManager.TableInfo.POA, poa);
                        values4.put(DBManager.TableInfo.CONTACT, contact);
                        values4.put(DBManager.TableInfo.BIOMETRIC, biometric);
                        values4.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                        String condition4 = DBManager.TableInfo.DOCUMENT + " =?";
                        Cursor cursor4 = db4.query(PARTIES1, null, condition4, new String[]{DBManager.TableInfo.ATTENDANCE}, null, null, null);
                        long status4 = db4.insert(PARTIES1, null, values4);
                        //Log.d(TAG, "Parties insert : " + status4);
//                                        System.out.println("Success" + username2);
                        cursor4.close();
                        db4.close();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//WITNESSURL
                try {
                    jsonStr2 = response2.doGetRequest(WITNESSURL);

                } catch (IOException e) {
                    e.printStackTrace();
                }
//////adhoc & witness
                Log.d("Response2: ", "> " + jsonStr2);

                SQLiteDatabase base1 = db.getWritableDatabase();
                base1.delete(TABLE_TASK, null, null);

                if (jsonStr2 != null) {

                    try {

                        JSONObject jsonObj1 = new JSONObject(jsonStr2);
                        ja9 = jsonObj1.getJSONArray(GenericMethods.TAG_NAME9);
                        for (int n = 0; n < ja9.length(); n++) {
                            JSONObject c = ja9.getJSONObject(n);
                            String witnessid = c.getString(GenericMethods.TAG_ID);
                            String comment = c.getString(GenericMethods.TAG_COMMENT);
                            String isdone = c.getString(GenericMethods.TAG_IS_DONE);
                            String doc = c.getString(GenericMethods.TAG_DOC);
                            String create = c.getString(GenericMethods.TAG_CREATE);
                            String remainder = c.getString(GenericMethods.TAG_REMAINDER);
                            String assign = c.getString(GenericMethods.TAG_ASSIGN);
                            String taskid = c.getString(GenericMethods.TAG_TASK_ID);


//////db for adhoc
                            SQLiteDatabase adhoc = db.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put(DBManager.TableInfo.KEYID, ID1);
                            values.put(DBManager.TableInfo.WITID, witnessid);
                            values.put(DBManager.TableInfo.COMMENT, comment);
                            values.put(DBManager.TableInfo.IS_DONE, isdone);
                            values.put(DBManager.TableInfo.DOC, doc);
                            values.put(DBManager.TableInfo.CREATE, create);
                            values.put(TASK_NAME, "adhoc");
                            values.put(DBManager.TableInfo.REMAINDER, remainder);
                            values.put(DBManager.TableInfo.ASSIGN, assign);
                            values.put(DBManager.TableInfo.TASK_ID, taskid);
                            values.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                            String condition = DBManager.TableInfo.DOC + " =?";
                            Cursor cursor = adhoc.query(TABLE_TASK, null, condition, new String[]{DBManager.TableInfo.TASK_ID}, null, null, null);
                            long status = adhoc.insert(TABLE_TASK, null, values);

                        System.out.println("Success" + username2 + TASK_NAME);
                            cursor.close();
                            adhoc.close();

                        }
//
//                        //witness

                        ja10 = jsonObj1.getJSONArray(GenericMethods.TAG_NAME10);
                        for (int n = 0; n < ja10.length(); n++) {
                            JSONObject c = ja10.getJSONObject(n);
                            String witnessid = c.getString(GenericMethods.TAG_ID);
                            String comment = c.getString(GenericMethods.TAG_COMMENT);
                            String isdone = c.getString(GenericMethods.TAG_IS_DONE);
                            String doc = c.getString(GenericMethods.TAG_DOC);
                            String create = c.getString(GenericMethods.TAG_CREATE);
                            String remainder = c.getString(GenericMethods.TAG_REMAINDER);
                            String assign = c.getString(GenericMethods.TAG_ASSIGN);
                            String taskid = c.getString(GenericMethods.TAG_TASK_ID);

                            SQLiteDatabase witn = db.getWritableDatabase();
                            ContentValues valuesw = new ContentValues();
                            valuesw.put(DBManager.TableInfo.KEYID, ID1);
                            valuesw.put(DBManager.TableInfo.WITID, witnessid);
                            valuesw.put(DBManager.TableInfo.COMMENT, comment);
                            valuesw.put(DBManager.TableInfo.IS_DONE, isdone);
                            valuesw.put(DBManager.TableInfo.DOC, doc);
                            valuesw.put(DBManager.TableInfo.CREATE, create);
                            valuesw.put(TASK_NAME, "Witness");
                            valuesw.put(DBManager.TableInfo.REMAINDER, remainder);
                            valuesw.put(DBManager.TableInfo.ASSIGN, assign);
                            valuesw.put(DBManager.TableInfo.TASK_ID, taskid);
                            valuesw.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                            String conditionw = DBManager.TableInfo.DOC + " =?";
                            Cursor cursorw = witn.query(TABLE_TASK, null, conditionw, new String[]{DBManager.TableInfo.TASK_ID}, null, null, null);
                            long statusw = witn.insert(TABLE_TASK, null, valuesw);
//                        Log.d(TAG, "Witness insert : " + statusw);
                        System.out.println("Success" + username2 + TASK_NAME);
                            cursorw.close();
                            witn.close();
                        }
////complaint
//


                        ja11 = jsonObj1.getJSONArray(GenericMethods.TAG_NAME11);
                        for (int n = 0; n < ja11.length(); n++) {
                            JSONObject c = ja11.getJSONObject(n);
                            String witnessid = c.getString(GenericMethods.TAG_ID);
                            String comment = c.getString(GenericMethods.TAG_COMMENT);
                            String isdone = c.getString(GenericMethods.TAG_IS_DONE);
                            String doc = c.getString(GenericMethods.TAG_DOC);
                            String create = c.getString(GenericMethods.TAG_CREATE);
                            String remainder = c.getString(GenericMethods.TAG_REMAINDER);
                            String assign = c.getString(GenericMethods.TAG_ASSIGN);
                            String taskid = c.getString(GenericMethods.TAG_TASK_ID);

                            SQLiteDatabase complaint = db.getWritableDatabase();
                            ContentValues valuesw = new ContentValues();
                            valuesw.put(DBManager.TableInfo.KEYID, ID1);
                            valuesw.put(DBManager.TableInfo.WITID, witnessid);
                            valuesw.put(DBManager.TableInfo.COMMENT, comment);
                            valuesw.put(DBManager.TableInfo.IS_DONE, isdone);
                            valuesw.put(DBManager.TableInfo.DOC, doc);
                            valuesw.put(DBManager.TableInfo.CREATE, create);
                            valuesw.put(TASK_NAME, "Complaint");
                            valuesw.put(DBManager.TableInfo.REMAINDER, remainder);
                            valuesw.put(DBManager.TableInfo.ASSIGN, assign);
                            valuesw.put(DBManager.TableInfo.TASK_ID, taskid);
                            valuesw.put(DBManager.TableInfo.KEY_LOGIN_USER, username2);
                            String conditionw = DBManager.TableInfo.DOC + " =?";
                            Cursor cursorw = complaint.query(TABLE_TASK, null, conditionw, new String[]{DBManager.TableInfo.TASK_ID}, null, null, null);
                            long statusw = complaint.insert(TABLE_TASK, null, valuesw);
//                        Log.d(TAG, "Complaint insert : " + statusw);
                        System.out.println("Success" + username2 + TASK_NAME);
                            cursorw.close();
                            complaint.close();
//
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing() || pDialog != null) {
                Intent intent = new Intent(Login.this, NextActivity.class);
                startActivity(intent);
                finish();
                pDialog.dismiss();
            }
        }

    }

    public void callLocationTracking() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        } else {
            Intent locationtrack = new Intent(Login.this,
                    LocationTrackingService.class);
            locationtrack.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startService(locationtrack);
        }

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this).setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);

                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).show();
    }
}


