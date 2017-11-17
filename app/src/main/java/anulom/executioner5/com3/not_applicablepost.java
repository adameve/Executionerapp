package anulom.executioner5.com3.anulom.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import anulom.executioner5.com3.anulom.GenericMethods;
import anulom.executioner5.com3.anulom.Login;
import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.fragment.CompletedDetails;
import anulom.executioner5.com3.anulom.fragment.NewDetails;
import anulom.executioner5.com3.anulom.fragment.OlderDetails;
import anulom.executioner5.com3.anulom.fragment.TodayDetails;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.NOT_APPLICABLE;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.TABLE_TASK;

public class not_applicablepost extends Service {
    private SharedPreferences sharedPreferences;
    DBOperation db;

    public String umail = Login.umailid;
    GenericMethods mResponse;
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF8");
    String username3, status = "1", not_app, reason, exec_email, taskid1, stat;
    int count = 0;
    private String username2 = "", comment = "";
    JSONArray appointment = new JSONArray();
    String value = "";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        value = intent.getStringExtra("Value");
        comment = intent.getStringExtra("comment");
        stat = intent.getStringExtra("status");
        mResponse = new GenericMethods();

        if (GenericMethods.isConnected(getApplicationContext())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new MyAsyncTask()
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else {
                new MyAsyncTask().execute();


            }
        }
        return START_STICKY;


    }

    public void onCreate() {


        // TODO Auto-generated method stub
        super.onCreate();


    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {


        @Override
        protected Double doInBackground(String... params) {
            DBOperation db = new DBOperation(getApplicationContext());
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            username2 = sharedPreferences.getString("username", "");
            username3 = username2;
            ArrayList<HashMap<String, String>> not_applicabledetails = db.getnot_applicable(db);


            try {

                for (int i = 0; i < not_applicabledetails.size(); i++) {

                    exec_email = not_applicabledetails.get(i).get("exec_email");
                    not_app = not_applicabledetails.get(i).get("not_app");
                    reason = not_applicabledetails.get(i).get("reason_applicable");
                    taskid1 = not_applicabledetails.get(i).get("task_id");
                    System.out.println(not_applicabledetails.size() + taskid1 + comment);
                    postData(taskid1, exec_email, not_app, comment, reason);

                }


            } catch (Exception e) {

                // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }

            }
            return null;

        }

        protected void onPostExecute(Double result) {
            stopSelf();

            if (TodayDetails.thisToday != null) {
                TodayDetails.thisToday.reFreshReload();
            }
            if (OlderDetails.thisOlderDetails != null) {
                OlderDetails.thisOlderDetails.reFreshReload();
            }
            if (NewDetails.thisnewDetails != null) {
                NewDetails.thisnewDetails.reFreshReload();
            }
            if (CompletedDetails.thiscompleteDetails != null) {
                CompletedDetails.thiscompleteDetails.reFreshReload();
            }
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        public void postData(String taskid, String exec_email, String not_app, String comment, String reason) {

            DBOperation db = new DBOperation(getApplicationContext());
            String token = "DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7";
            String URL = GenericMethods.MAIN_URL1+"/communicator/api/v1/comment_data/task_not_app";


            try {


                JSONObject app = new JSONObject();
                app.put("task_id", taskid);
                app.put("user", exec_email);
                app.put("not_app", not_app);
                app.put("comment", comment);
                app.put("reason", reason);


                JSONObject tokenjson = new JSONObject();
                tokenjson.put("token", token);
                JSONObject main = new JSONObject();
                main.put("comment", app);
                main.put("auth_token", tokenjson);
                String json = "";
                json = main.toString();
                System.out.println("jsonfinal:" + json);
                String strResponsePost = mResponse.doPostRequest(URL, json);

                if (!strResponsePost.equals("")) {
                    JSONObject jResult = new JSONObject(strResponsePost);
                    String strStatus = jResult.getString("status");

                    if (strStatus.equals(status)) {

                        SQLiteDatabase sqldb = db.getWritableDatabase();

                        int count = sqldb.delete(NOT_APPLICABLE, DBManager.TableInfo.task_id1 + "=?", new String[]{taskid});
                        System.out.println(" DB ROW DELETED SUCCESS:" + count);
//
                        int count1 = sqldb.delete(TABLE_TASK, DBManager.TableInfo.TASK_ID + "=?", new String[]{taskid});
                        System.out.println(" DB ROW DELETED SUCCESS:" + count1);

                    }

                }
                System.out.println("**********Post Completed************");

            } catch (JSONException e) {

                // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }

            } catch (ClientProtocolException e) {

                // TODO Auto-generated catch block
            } catch (IOException e) {

            }
        }


    }


}
