package anulom.executioner5.com3.anulom;

import android.app.Activity;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 6/29/2016.
 */
public class GenericMethods {

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF8");
    public static final String AsyncStatus = "ASYNC";

    //public static final String MAIN_URL = "http://ec2-35-160-159-8.us-west-2.compute.amazonaws.com:3000";
    public static final String MAIN_URL = "http://52.33.203.208:3000";
    public static final String MAIN_URL1="http://52.33.203.208";
    public static final String MAIN_URL2="http://52.33.203.208";
    public static final String URL = MAIN_URL + "/api/v1/exec_status/get_location";
    public static final String LOGINURL = MAIN_URL + "/api/v2/biometric_data/login";

    public static final String TAG_NAME0 = "status";
    public static final String TAG_NAME = "document";
    public static final String TAG_NAME1 = "comments";
    public static final String TAG_NAME2 = "add_payment";
    public static final String TAG_NAME3 = "users";
    public static final String TAG_NAME4 = "rem_payment";
    public static final String TAG_NAME5 = "parties";
    public static final String TAG_NAME8 = "parties";
    public static final String TAG_NAME6 = "rem_payment";
    public static final String TAG_NAME7 = "document";
    public static final String TAG_NAME9 = "adhoc_task";
    public static final String TAG_NAME10 = "witness_task";
    public static final String TAG_NAME11 = "complaint_task";
    public static final String TAG_NAME12 = "time_slot";
    public static final String TAG_NAME13 = "attendees";
    public static final String TAG_NAME14 = "bio_status";

    public static String email = "email";
    public static String password = "password";
    public static String document;
    public static String connection = "conn";

    public static int ctr = 0;
    public static int ctr1 = 0;
    public static int older = 0;
    public static int today = 0;
    public static int neww = 0;
    public static int completed = 0;

    public static int ctr2 = 0;
    public static int ctr3 = 0;
    public static String olderr = "0";
    public static String todayy = "0";
    public static String newww = "0";
    public static String completedd = "0";
    //comment
    public static String comm_position = "";
    public static String commkey = "";
    public static String commdoc = "";


    //search variables
    public static String value = "false";
    public static String rkeyfn;
    public static String disp = "";
    public static String olderrr = "false";
    public static String todayyy = "false";
    public static String newwww = "false";
    public static String completeddd = "false";
    public static String pd_value = "null";
    public static String toastmsg = "false";

    //for work completed and last updated
    public static final String TABLE_CHECK_JOB = "check_jobb";
    public static final String KEY_ID = "id";
    public static final String KEY_LOGIN_USER = "login_user";
    public static final String R_KEY = "r_key";
    public static final String JOB = "job";

    public static final String TABLE_LAST_UPDATE = "last_update";
    public static final String LAST_UPDATED = "last_updated";

    public static final String TAG_REPORT_KEY = "env";
    public static final String TAG_RTOKEN = "token_no";

    public static final String TAG_UNAME = "uname";
    public static final String TAG_UEMAIL = "email";
    public static final String TAG_UCONTACT = "contact_number";

    public static final String TAG_ONAME = "oname";
    public static final String TAG_OCONTACT = "ocontact";
    public static final String TAG_OEMAIL = "oemail";
    public static final String TAG_OADDRESS = "oaddress";

    public static final String TAG_TNAME = "tname";
    public static final String TAG_TCONTACT = "tcontact";
    public static final String TAG_TEMAIL = "temail";
    public static final String TAG_TADDRESS = "taddress";

    public static final String TAG_PADDRESS = "Paddress";
    public static final String TAG_POST_STATUS = "post_status";

    public static final String TAG_CUSTOMER_LAT = "latitude";
    public static final String TAG_CUSTOMER_LONG = "longitude";

    public static final String TAG_CUSTOMER_DISTANCE = "distance";
    public static final String TAG_CUSTOMER_AMOUNT = "acvr_amount";
    public static final String TAG_CUSTOMER_TRANS_TYPE = "trans_type";
    public static final String TAG_CUSTOMER_APOINTMENT_FOR = "app_for";


    public static final String TAG_STATUS = "status";

    public static final String TAG_APPDATE = "appointment_date";
    public static final String TAG_BIO_COMP = "biometric_comp";

    public static final String TAG_APPDATE1 = "appointment_date1";
    public static final String TAG_BIO_COMP1 = "biometric_comp1";

    public static final String TAG_REG_FROM_COMP = "reg_frm_comp";
    public static final String TAG_WITNESS = "witness";
    public static final String TAG_SHIP_ADDRESS = "ship_address";
    public static final String TAG_SHIP_DIFF_ADDRESS = "ship_diffadd";

    public static final String TAG_DOCID = "document_id";
    public static final String TAG_APPID = "appointment_id";
    public static final String TAG_START1 = "start_time";
    public static final String TAG_START2 = "end_time";
    public static final String TAG_APPADDRESS = "address";
    public static final String TAG_EXECUTIONER_ID = "ex_email";
    public static final String TAG_APP_FOR = "app_for";

    public static final String TAG_COMMENTS = "cust_comment";
    public static final String TAG_CUSER = "email";
    public static final String TAG_COWNER = "owner_name";
    public static final String TAG_CID = "id";
    public static final String TAG_C_IS_DONE = "is_done";
    public static final String TAG_CDATE = "created_at";
    public static final String TAG_ENV1 = "env";
    public static final String TAG_LANDMARK = "landmark";
    public static final String TAG_CONTACTPERSON = "contact_person";

    public static int i = 0;

    public static final String TAG_USERID = "user_id";
    public static final String TAG_USERNAME = "user_name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ROLE = "role";
    public static final String TAG_PLATFORMID = "platform_id";
    public static final String TAG_ROLEID = "role_id";
    public static final String TAG_IDu = "id";


    public static final String TAG_KEYID = "ID";
    public static final String TAG_REPORTKEY = "env";
    public static final String TAG_DOCUMENTID = "document_id";
    public static final String TAG_PAYAMOUNT = "amount";
    public static final String TAG_KEY_LOGIN_USER = "Login_user";
    //new API
    public static final String TAG_DOCUMENT = "document_id";
    public static final String TAG_ATTENDANCE = "att_id";
    public static final String TAG_NAMENEW = "name";
    public static final String TAG_EMAILNEW = "email";
    public static final String TAG_PARTYTYPE = "party_type";
    public static final String TAG_POA = "poa";
    public static final String TAG_APPTYPE = "app_type";
    public static final String TAG_BIOMETRIC = "biometric";
    public static final String TAG_ENV = "env";

    public static final String TAG_CONTACTNUMBER = "contact_no";


// for adhoc&witness&complaint

    public static final String TAG_ID = "id";
    public static final String TAG_COMMENT = "comment";
    public static final String TAG_IS_DONE = "is_done";
    public static final String TAG_DOC = "document_id";
    public static final String TAG_CREATE = "created_at";
    public static final String TAG_REMAINDER = "reminder_dt";
    public static final String TAG_EMOTION = "emotion";
    public static final String TAG_ASSIGN = "assign_by";
    public static final String TAG_TASK_ID = "task_id";


//appointmentslot

    public static final String TAG_SLOTID = "slot_id";
    public static final String TAG_STARTTIME = "c_start_time";
    public static final String TAG_ENDTIME = "c_end_time";
    public static final String TAG_AVAILABLE = "available";
    public static final String TAG_BLOCK = "block";
    public static final String TAG_DISCOUNT = "discount";

    //attendees
    public static final String TAG_NAMEATTEND = "name";
    public static final String TAG_EMAILATTEND = "email";
    public static final String TAG_CONTACTATTEND = "contact";

    //appointment_booking
    public static String app_value = "false";

    //bio_content
    public static String Tag_att_status = "att_status";


    public static String getCurrentDate() {
        try {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            return ft.format(dNow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean isConnected(Context applicationContext) {
        NetworkInfo networkInfo = null;
        if (applicationContext != null) {

            ConnectivityManager conMgr = (ConnectivityManager) applicationContext
                    .getSystemService(Activity.CONNECTIVITY_SERVICE);
            if (conMgr != null) {
                networkInfo = conMgr.getActiveNetworkInfo();
            }
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;

    }

    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.setConnectTimeout(60, TimeUnit.SECONDS);  //Connect timeout
        client.setReadTimeout(60, TimeUnit.SECONDS);    //Socket timeout
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
