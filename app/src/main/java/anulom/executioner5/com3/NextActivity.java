package anulom.executioner5.com3.anulom;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import anulom.executioner5.com3.anulom.services.Posttask;
import anulom.executioner5.com3.anulom.services.SendmultiPartyReport;
import anulom.executioner5.com3.anulom.services.SendPaymentService;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.fragment.CompletedDetails;
import anulom.executioner5.com3.anulom.fragment.NewDetails;
import anulom.executioner5.com3.anulom.fragment.OlderDetails;
import anulom.executioner5.com3.anulom.fragment.TodayDetails;
import anulom.executioner5.com3.anulom.services.GetAllDataList;
import anulom.executioner5.com3.anulom.services.GetCommentService;
import anulom.executioner5.com3.anulom.services.SendCommentService;
import anulom.executioner5.com3.anulom.services.SendReportService;
import anulom.executioner5.com3.anulom.services.StatusReportService;
import anulom.executioner5.com3.anulom.services.call;
import anulom.executioner5.com3.anulom.services.fetchactualtime;
import anulom.executioner5.com3.anulom.services.not_applicablepost;
import anulom.executioner5.com3.anulom.services.postappointmentbooking;
import anulom.executioner5.com3.anulom.services.reassignpost;
import anulom.executioner5.com3.anulom.services.rescheduledetails;

import static anulom.executioner5.com3.anulom.GenericMethods.TABLE_LAST_UPDATE;
import static anulom.executioner5.com3.anulom.Login.umailid;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.MULTIWORK;

public class NextActivity extends AppCompatActivity {

    String umail = umailid;
    EditText etusermailid = Login.etusermailid;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DBOperation database;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date, ID,val="";
    Context context;
    TextView text1;
    ProgressDialog pd;
    Button but1, but2, but3, but4;
    public static ProgressDialog pDialog;
    private ProgressDialog pDialog1;
    Menu menu;
    //    android.widget.SearchView searchView;
    SearchManager searchManager;
    SearchView searchView;
    private SharedPreferences usermail;
    private SharedPreferences Date1;
    private String username2 = "", password2 = "";
    public static NextActivity thisnext = null;
    DBOperation db;
    Timer singleTask = new Timer();
    int startdelay = 5000;
    // Tab titles

    //search
    SearchView sv;
    int i = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        callPhone();
//        System.out.println("values on create"+GenericMethods.value+" "+GenericMethods.toastmsg);
        if (GenericMethods.value.equals("false") && GenericMethods.toastmsg.equals("true")) {
            System.out.println("NO VALUE ENTERED");
            Toast.makeText(getApplicationContext(), "No Records Found", Toast.LENGTH_LONG).show();
            GenericMethods.toastmsg = "false";
        }


        database = new DBOperation(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("  Anulom");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        text1 = (TextView) findViewById(R.id.textView1);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        but1 = (Button) findViewById(R.id.button1);
        but2 = (Button) findViewById(R.id.button2);
        but3 = (Button) findViewById(R.id.button3);
        but4 = (Button) findViewById(R.id.button4);

        tabLayout.setupWithViewPager(viewPager);






        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");

        Date1=PreferenceManager.getDefaultSharedPreferences(this);
        String currentdate=Date1.getString("Date","");
        text1.setText("  " +currentdate);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tabTextView.setTextColor(Color.parseColor("#ffa500"));
            tab.setCustomView(relativeLayout);
            tab.select();


        }
        viewPager.setCurrentItem(0, false);


        // if(GenericMethods.value.equals("false")) {

        handleIntent(getIntent());
        //}


    }

    private void handleIntent(Intent intent) {
//        System.out.println("Search");
//        GenericMethods.value = "false";

        if (GenericMethods.value.equals("false")) {


//            System.out.println("Search Entered");

            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

                String query = intent.getStringExtra(SearchManager.QUERY);
//                i = i + 1;
                //  if("nwp_50056".contains(query.toLowerCase())) {
//                    System.out.println("Search Q new" + query.toLowerCase());
                //  }
//            System.out.println("Search value"+GenericMethods.value);

                //if (GenericMethods.search_pos == 1) {
                // System.out.println("Search from internal if");
                GenericMethods.value = "true";
                GenericMethods.rkeyfn = query.toLowerCase();
                pDialog = new ProgressDialog(NextActivity.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();
//                new manual_search().execute();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NextActivity.this, "SEARCH STARTED", Toast.LENGTH_SHORT).show();

                        new manual_search().execute();
                    }
                });


//                System.out.println("Search Q" + query + (i));

//            if(GenericMethods.todayyy=="true"||GenericMethods.olderrr=="true"||GenericMethods.newww=="true"||GenericMethods.completeddd=="true")
//            {
//                System.out.println("from intent if");


//                new Task().execute(NextActivity.this);
//                Intent service = new Intent(getApplicationContext(), SearchService.class);
//                startService(service);
//                pDialog = new ProgressDialog(NextActivity.this);
//                pDialog.setMessage("Searching...");
//
//                pDialog.setCancelable(false);
//                pDialog.show();
            }
//            else {
//                GenericMethods.value="false";
//                System.out.println("from intent else");
//                setContentView(R.layout.nodetails);
//            }
//}
//            System.out.println("Search pos"+GenericMethods.search_pos+" "+GenericMethods.value+" "+GenericMethods.rkeyfn);


//            }
        }
    }

    //    public void appointment(View v){
//
//
//        Intent i = new Intent(NextActivity.this, NextActivity.class);
//        startActivity(i);
//
//    }
    public void adhoc(View v) {
        db = new DBOperation(NextActivity.this);
        //System.out.println("getadhoc:"+db.getadhocdetails(db));
        if (db.getadhocdetails(db).size() == 0) {
            Intent i = new Intent(NextActivity.this, adhocremainder.class);
            startActivity(i);


        } else {

            Intent i = new Intent(NextActivity.this, Nextactivity1.class);
            startActivity(i);
        }
    }

    public void witness(View v) {
        db = new DBOperation(NextActivity.this);
        if (db.getWitnessdetails(db).size() == 0) {
            Intent i = new Intent(NextActivity.this, adhocremainder.class);
            startActivity(i);


        } else {

            Intent i = new Intent(NextActivity.this, NextActivity2.class);
            startActivity(i);
        }
    }

    public void complaint(View v) {
        db = new DBOperation(NextActivity.this);
        if (db.getComplaintdetails(db).size() == 0) {
            Intent i = new Intent(NextActivity.this, adhocremainder.class);
            startActivity(i);


        } else {

            Intent i = new Intent(NextActivity.this, NextActivity3.class);
            startActivity(i);
        }
    }


    class manual_search extends AsyncTask<String, Integer, Double> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            System.out.println("processor " + Runtime.getRuntime().availableProcessors());

        }

        @Override
        protected Double doInBackground(String... params) {


            String rkey1 = "";
            db = new DBOperation(NextActivity.this);


            //today list operation
            for (int i = 0; i < db.getAllTodayList(db).size(); i++) {
                if (db.getAllTodayList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("a")) {
                    rkey1 = "a" + db.getAllTodayList(db).get(i).get("rkey").substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyif:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                } else if (db.getAllTodayList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = db.getAllTodayList(db).get(i).get("rkey").substring(0, 3).toLowerCase() + db.getAllTodayList(db).get(i).get("rkey").substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }
                //System.out.println("tname:"+db.getAllOlderist(db).get(i).get("tname").toLowerCase());
                if (db.getAllTodayList(db).get(i).get("rkey").toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || db.getAllTodayList(db).get(i).get("oname").toLowerCase().contains(GenericMethods.rkeyfn) || db.getAllTodayList(db).get(i).get("tname").toLowerCase().contains(GenericMethods.rkeyfn)) {
                    //        GenericMethods.disp="Today";
                    GenericMethods.todayyy = "true";
//                    System.out.println("today :" + GenericMethods.todayyy);
                }
            }

            //older list operation
            // if (GenericMethods.disp.equals("")) {
            for (int i = 0; i < db.getAllOlderist(db).size(); i++) {

                if (db.getAllOlderist(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + db.getAllOlderist(db).get(i).get("rkey").substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyif:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                } else if (db.getAllOlderist(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = db.getAllOlderist(db).get(i).get("rkey").substring(0, 3).toLowerCase() + db.getAllOlderist(db).get(i).get("rkey").substring(4);
                    if (db.getAllOlderist(db).get(i).get("rkey").equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }
                //System.out.println("tname:"+db.getAllOlderist(db).get(i).get("tname").toLowerCase());
                if (db.getAllOlderist(db).get(i).get("rkey").toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || db.getAllOlderist(db).get(i).get("oname").toLowerCase().contains(GenericMethods.rkeyfn) || db.getAllOlderist(db).get(i).get("tname").toLowerCase().contains(GenericMethods.rkeyfn)) {
//                        GenericMethods.disp = "Older";
                    GenericMethods.olderrr = "true";
//                    System.out.println("older:" + GenericMethods.olderrr);
                }
            }
            // }

            // completed list operation
            for (int i = 0; i < db.getAllList(db).size(); i++) {
                if (db.getAllList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + db.getAllList(db).get(i).get("rkey").substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyif:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                } else if (db.getAllList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = db.getAllList(db).get(i).get("rkey").substring(0, 3).toLowerCase() + db.getAllList(db).get(i).get("rkey").substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }
                //System.out.println("tname:"+db.getAllOlderist(db).get(i).get("tname").toLowerCase());
                if (db.getAllList(db).get(i).get("rkey").toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || db.getAllList(db).get(i).get("oname").toLowerCase().contains(GenericMethods.rkeyfn) || db.getAllList(db).get(i).get("tname").toLowerCase().contains(GenericMethods.rkeyfn)) {
                    GenericMethods.completeddd = "true";
//                    System.out.println("complete:" + GenericMethods.completeddd);
                }
            }
            //   }

            //new list operation
            for (int i = 0; i < db.getAllNewList(db).size(); i++) {
                if (db.getAllNewList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("a")) {
                    rkey1 = "a" + db.getAllNewList(db).get(i).get("rkey").substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
//                        System.out.println("rkeyif:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                } else if (db.getAllNewList(db).get(i).get("rkey").substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = db.getAllNewList(db).get(i).get("rkey").substring(0, 3).toLowerCase() + db.getAllNewList(db).get(i).get("rkey").substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                        System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }
                //System.out.println("tname:"+db.getAllOlderist(db).get(i).get("tname").toLowerCase());
                if (db.getAllNewList(db).get(i).get("rkey").toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || db.getAllNewList(db).get(i).get("oname").toLowerCase().contains(GenericMethods.rkeyfn) || db.getAllNewList(db).get(i).get("tname").toLowerCase().contains(GenericMethods.rkeyfn)) {
                    GenericMethods.newwww = "true";
//                    System.out.println("new:" + GenericMethods.newwww);
                }
            }
//            System.out.println("processor new " + Runtime.getRuntime().availableProcessors());

            if (GenericMethods.newwww.equals("true") || GenericMethods.completeddd.equals("true") || GenericMethods.olderrr.equals("true") || GenericMethods.todayyy.equals("true")) {

                pDialog.dismiss();
                Intent i = new Intent(NextActivity.this, SearchActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            } else {
//                System.out.println("oldervalue:" + GenericMethods.olderrr);

                pDialog.dismiss();
                no_value();
            }

            //  }
            return null;
        }

        protected void onPostExecute(Double result) {


//            if (pDialog.isShowing() || pDialog != null) {

//            }

        }
    }

    void no_value() {
        new manual_search().cancel(true);
        GenericMethods.value = "false";
        GenericMethods.toastmsg = "true";
        Intent i = new Intent(NextActivity.this, NextActivity.class);

        startActivity(i);
    }

    private void setupViewPager(ViewPager viewPager) {
        //System.out.println("from viewpager"+" "+GenericMethods.olderrr+" "+GenericMethods.todayyy+" "+GenericMethods.newwww+" "+GenericMethods.completeddd);
        //viewDetails();
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        System.out.println("from viewpager normal");
        db = new DBOperation(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayDetails(), "Today" + "(" + db.getAllTodayList(db).size() + ")");
        adapter.addFragment(new OlderDetails(), "Older" + "(" + db.getAllOlderist(db).size() + ")");
        adapter.addFragment(new NewDetails(), "New" + "(" + db.getAllNewList(db).size() + ")");
        adapter.addFragment(new CompletedDetails(), "Completed" + "(" + db.getAllList(db).size() + ")");
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        handleIntent(getIntent());

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        //search function

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        menu.findItem(R.id.login_id).setTitle(username2);
        return super.onCreateOptionsMenu(menu);


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {

            case R.id.action_refresh:


                if (GenericMethods.isConnected(getApplicationContext())) {
                    calendar = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("MMM dd, hh:mm a");
                    Date = simpleDateFormat.format(calendar.getTime());
                    SharedPreferences.Editor editor = Date1.edit();
                    editor.putString("Date", Date);
                    editor.commit();
                    text1.setText("  " +Date);



                    SQLiteDatabase sqldb = database.getWritableDatabase();
                    int count = sqldb.delete(MULTIWORK, null, null);
                    System.out.println("CHECK PARTY DB ROW DELETED SUCCESS:" + count);
//                    System.out.println("Refresh date inserted");
                    db = new DBOperation(NextActivity.this);


                    ArrayList<HashMap<String, String>> listofcommment = db.getcomment(db);
                    ArrayList<HashMap<String, String>> acvrreportlist = db.getAcvrReport(db);
                    ArrayList<HashMap<String, String>> statusreportlist = db.getSyncStatusReport(db);
                    boolean flag = false;
                    if (listofcommment.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, SendCommentService.class);
                        startService(intent);
                        flag = true;


                    }
                    if (acvrreportlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, SendReportService.class);
                        startService(intent);
                        flag = true;
                    }
                    if (statusreportlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, StatusReportService.class);
                        startService(intent);
                        flag = true;

                    }
                    ArrayList<HashMap<String, String>> paymentlist = db.getPaymentReport(db);
//                    System.out.println("paymentlist size:"+paymentlist.size());
                    if (paymentlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, SendPaymentService.class);
                        startService(intent);
                        flag=true;
                    }
//
                    ArrayList<HashMap<String, String>> partieslist = db.getPartypost(db);
//                    System.out.println("partieslistsize:" + partieslist.size());
                    if (partieslist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, SendmultiPartyReport.class);
                        startService(intent);
                        flag=true;
                    }

                    ArrayList<HashMap<String, String>> appointmentlist = db.postappointment(db);

                    if (appointmentlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, postappointmentbooking.class);
                        startService(intent);
                        flag=true;
                    }

                    ArrayList<HashMap<String, String>> calllist = db.calldetails(db);

                    if (calllist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, call.class);
                        startService(intent);
                        flag=true;
                    }

                    ArrayList<HashMap<String, String>> reschedulelist = db.getrescheduledetails(db);

                    if (reschedulelist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, rescheduledetails.class);
                        startService(intent);
                        flag=true;
                    }

                    ArrayList<HashMap<String, String>> fetchactualtimelist = db.getactualtime(db);

                    if (fetchactualtimelist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, fetchactualtime.class);
                        startService(intent);
                        flag=true;
                    }
                    ArrayList<HashMap<String, String>> posttasklist = db.getposttaskdetails(db);

                    if (posttasklist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, Posttask.class);
                        startService(intent);
                        flag=true;
                    }
                    ArrayList<HashMap<String, String>> notassignlist = db.getnot_applicable(db);

                    if (notassignlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, not_applicablepost.class);
                        startService(intent);
                        flag=true;
                    }
                    ArrayList<HashMap<String, String>> reassignlist = db.getreassign(db);

                    if (reassignlist.size() > 0) {
                        Intent intent = new Intent(NextActivity.this, reassignpost.class);
                        startService(intent);
                        flag=true;
                    }




                    if (flag) {
                        AlertDialog alertDialog = new AlertDialog.Builder(NextActivity.this).create();
                        alertDialog.setMessage("Need To Sync All Data");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    } else {

                        pDialog = new ProgressDialog(NextActivity.this);
                        pDialog.setMessage("Fetching Data...");

                        pDialog.setCancelable(false);
                        pDialog.show();

                        Intent service = new Intent(getApplicationContext(), GetAllDataList.class);
                        startService(service);
                        Intent inte = new Intent(getApplicationContext(), GetCommentService.class);
                        startService(inte);
                    }

                    singleTask.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("Refresh over");
                            GenericMethods.ctr2 = 0;
                            GenericMethods.ctr3 = 0;



                        }


                    }, startdelay);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check internet connection.", Toast.LENGTH_LONG).show();
                }


                return true;

            case R.id.abt:
                Intent i3 = new Intent(getApplicationContext(), about.class);
                startActivity(i3);
                return true;


//            case R.id.weekend:
//                Intent i2 = new Intent(NextActivity.this, weekendoff.class);
//                startActivity(i2);
//
//
//                this.finish();
//                return true;

            case R.id.log_out:

                usermail = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = usermail.edit();
                editor.remove("username");
                editor.remove("password");
                editor.apply();

                GenericMethods.ctr2 = 0;
                GenericMethods.ctr3 = 0;
                Intent i = new Intent(getApplicationContext(), Login.class);

                startActivity(i);


        }
        return super.onOptionsItemSelected(item);
    }


    public void closeProgressBar() {
        pDialog.cancel();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (singleTask != null) {

            singleTask.cancel();
        }
    }

    @Override
    protected void onPostResume() {

        super.onPostResume();
        thisnext = this;
        GenericMethods.pd_value = "true";
    }

    @Override
    protected void onPause() {
        super.onPause();
        thisnext = null;
        GenericMethods.pd_value = "null";
    }

    public void callPhone() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
        }

       else  if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Granted");

        }

    }

}