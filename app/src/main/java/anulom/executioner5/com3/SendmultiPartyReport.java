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
import java.util.List;

import anulom.executioner5.com3.anulom.GenericMethods;
import anulom.executioner5.com3.anulom.Login;
import anulom.executioner5.com3.anulom.database.DBManager;
import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.fragment.CompletedDetails;
import anulom.executioner5.com3.anulom.fragment.NewDetails;
import anulom.executioner5.com3.anulom.fragment.OlderDetails;
import anulom.executioner5.com3.anulom.fragment.TodayDetails;

import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.POSTDOC;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.UPDATEPARTY;

public class SendmultiPartyReport extends Service {
    private SharedPreferences sharedPreferences;
    DBOperation db;

    public String umail = Login.umailid;
    GenericMethods mResponse;
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF8");
    String username3, status = "1", document, attendance, email, partytype, biometric, execemail, doc;
    int count = 0;
    private String username2 = "";
    JSONArray party = new JSONArray();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId) {

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
            ArrayList<HashMap<String, String>> partiesarray = new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> partieslist = db.getPartypost(db);
            List<String> postdoclist = db.getPostDoc(db);
            System.out.println("sizefromservice:" + partieslist.size());

            try {

                for (int j = 0; j < postdoclist.size(); j++) {
                    doc = postdoclist.get(j);
                    for (int i = 0; i < partieslist.size(); i++) {

                        document = partieslist.get(i).get("document_id");

                        if (document.equals(doc)) {

                            System.out.println("Doc:"+"j:"+j+"i:"+i+document+" "+doc);
                            attendance = partieslist.get(i).get("att_id");
                            email = partieslist.get(i).get("email");
                            partytype = partieslist.get(i).get("party_type");
                            biometric = partieslist.get(i).get("biometric");
                            execemail = partieslist.get(i).get("exec_email");

                            if (biometric.equals("true")) {
                                biometric = "1";
                            } else if (biometric.equals("false")) {
                                biometric = "0";
                            } else {
                                biometric = "";
                            }
                            JSONObject parties = new JSONObject();

                            parties.put("docid", document);
                            parties.put("att_id", attendance);
                            parties.put("email", email);
                            parties.put("party_type", partytype);
                            parties.put("biometric", biometric);
                            party.put(parties);
                            System.out.println("party:"+party);
                            count = count + 1;


                            // TODO Auto-generated catch block

                        }

                    }

                    if (party.length()>0) {
                        postData(document, execemail, party, doc);
                        for (j = party.length() - 1; j > 0; j--) {
                            System.out.println("hiii");

                            party.remove(j);
                        }
                        System.out.println("doc from if:"+doc+"party length:"+party.length());


                    } else {

                        System.out.println("no post");
                    }


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

        public void postData(String document, String execemail, JSONArray party, String doc) {

            DBOperation db = new DBOperation(getApplicationContext());
            String token = "DOtUBMhv5pk51tl0D37uBcezq85cXNN7hZQ7";
            String URL = GenericMethods.MAIN_URL + "/api/v2/biometric_data/biometric_update";

            try {


                JSONObject app = new JSONObject();
                app.put("exec_email", execemail);
                app.put("docid", document);
                JSONObject tokenjson = new JSONObject();
                tokenjson.put("token", token);
                JSONObject main = new JSONObject();
                main.put("appointment", app);
                main.put("parties", party);
                main.put("auth_token", tokenjson);
                String json = "";
                json = main.toString();
                System.out.println("jsonfinal:"+main);


                String strResponsePost = mResponse.doPostRequest(URL, json);

                if (!strResponsePost.equals("")) {
                    JSONObject jResult = new JSONObject(strResponsePost);
                    String strStatus = jResult.getString("status");
                    System.out.println("status:"+strStatus);

                    if (strStatus.equals("1")) {

                        SQLiteDatabase sqldb = db.getWritableDatabase();

                        int count = sqldb.delete(UPDATEPARTY, DBManager.TableInfo.DOCU + "=?", new String[]{document});
                        System.out.println("partydb:"+count);

                        int count1 = sqldb.delete(POSTDOC, DBManager.TableInfo.postdocument + "=?", new String[]{doc});
                        System.out.println("postdocdb:"+count1);


                    }

                }

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
