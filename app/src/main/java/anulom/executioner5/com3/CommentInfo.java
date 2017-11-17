package anulom.executioner5.com3.anulom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import anulom.executioner5.com3.anulom.adapter.CustomAdapter;
import anulom.executioner5.com3.anulom.database.DBOperation;

public class CommentInfo extends AppCompatActivity {

    ListView lv;
    Context context;
    private Toolbar toolbar;
    ArrayList<HashMap<String, String>> getCommentDatalist = null;


    // cursor

    DBOperation db;
    private String docid, position1;
    private String reportkey = "", value = "false";

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentinfo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Comment Info");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//		getSupportActionBar().setIcon(R.drawable.icon);
        db = new DBOperation(getApplicationContext());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        reportkey = getIntent().getStringExtra("ReportKey");
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("Position", 0);
        docid = getIntent().getStringExtra("DocumentId");
        //position1=getIntent().getStringExtra("position");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Positio", position);
        editor.putString("DOCID", docid);
        //reportkey = getIntent().getStringExtra("reportkey");
        editor.commit();
        //System.out.println("commentsize:"+db.getcomment(db).size());
        freshReload();
    }

    private void freshReload() {
//
        getCommentDatalist = db.getCommentlist(db);
        System.out.println("Comment size from get:" + getCommentDatalist.size());
        ArrayList<String> commentidnew = new ArrayList<>();
        ArrayList<String> commentusernew = new ArrayList<>();
        ArrayList<String> commentownernew = new ArrayList<>();
        ArrayList<String> customercommentsnew = new ArrayList<>();
        ArrayList<String> commentdatenew = new ArrayList<>();
        for (int i = 0; i < getCommentDatalist.size(); i++) {
            //documentidcomment.add(getCommentDatalist.get(i).get("Did"));

            System.out.println(i + " " + getCommentDatalist.get(i).get("Did") + " " + reportkey);

            if (getCommentDatalist.get(i).get("Did").equals(reportkey)) {
                value = "true";


                String cidnew = getCommentDatalist.get(i).get("Cid");
                commentidnew.add("data" + cidnew);
                //System.out.println("data1"+cidnew);
                String cusernew = getCommentDatalist.get(i).get("Cuser");
                commentusernew.add(cusernew);
                //System.out.println("data2"+cusernew);
                String cownernew = getCommentDatalist.get(i).get("Cowner");
                commentownernew.add(cownernew);
                //System.out.println("data3"+cownernew);
                String ccommentsnew = getCommentDatalist.get(i).get("Ccomments");
                customercommentsnew.add(ccommentsnew);
                //System.out.println("data4"+ccommentsnew);
                String cdatenew = getCommentDatalist.get(i).get("Cdate");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = format1.parse(cdatenew);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                commentdatenew.add(format2.format(date));

                //}

            }

        }
        if (value.equals("true")) {
            System.out.println("comment list size:" + commentusernew.size());
            context = this;
            lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(new CustomAdapter(this, commentusernew, commentdatenew, customercommentsnew, commentownernew));
        }
        if (value.equals("false")) {
            Toast.makeText(CommentInfo.this, "No Comment", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.plus, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {

            case R.id.action_plus:

                System.out.println("commfrminfo" + docid);
                Intent intent = new Intent(CommentInfo.this, MyComment.class);
                intent.putExtra("DocumentId", docid);
                intent.putExtra("Position", position1);
                intent.putExtra("reportkey", reportkey);
                startActivityForResult(intent, 1234);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        freshReload();
    }

}