package anulom.executioner5.com3.anulom.database;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import anulom.executioner5.com3.anulom.GenericMethods;
import anulom.executioner5.com3.anulom.Login;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.APPOINTMENTSLOT;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.ATT_STATUS;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.DOCUMENT;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.MULTIWORK;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.NOT_APPLICABLE;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.PAYMENT;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.REASSIGN;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.UPDATEPAYMENT1;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.WEEKEND;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.gen_distance;
import static anulom.executioner5.com3.anulom.database.DBManager.TableInfo.landmark;


public class DBOperation extends SQLiteOpenHelper {

    private SharedPreferences usermail;

    public static final int database_version = 88;

    public final String CREATE_QUERY1 = "CREATE TABLE " + DBManager.TableInfo.TABLE_NAME1 + " (" + DBManager.TableInfo.UserName + " TEXT , "
            + DBManager.TableInfo.UserPassword + " TEXT , " + DBManager.TableInfo.UserEmail + " TEXT PRIMARY KEY);";


    String DOCUMENTT = "CREATE TABLE " + DBManager.TableInfo.TABLE_NAME2 + " ("
            + DBManager.TableInfo.ReportKey + " TEXT  , "
            + DBManager.TableInfo.ReportToken + " TEXT , "
            + DBManager.TableInfo.Umail + " TEXT , "
            + DBManager.TableInfo.Uname + " TEXT , "
            + DBManager.TableInfo.Ucontact + " TEXT , "
            + DBManager.TableInfo.PropertyAddress + " TEXT , "
            + DBManager.TableInfo.OwnerName + " TEXT , "
            + DBManager.TableInfo.OwnerContact + " TEXT , "
            + DBManager.TableInfo.OwnerEmail + " TEXT , "
            + DBManager.TableInfo.OwnerAddress + " TEXT , "
            + DBManager.TableInfo.TenantName + " TEXT , "
            + DBManager.TableInfo.TenantContact + " TEXT , "
            + DBManager.TableInfo.TenantEmail + " TEXT , "
            + DBManager.TableInfo.TenantAddress + " TEXT , "
            + DBManager.TableInfo.Status + " TEXT , "
            + DBManager.TableInfo.UserEmail + " TEXT , "
            + DBManager.TableInfo.BiometricComp + " TEXT , "
            + DBManager.TableInfo.Appointment_Date + " TEXT , "
            + DBManager.TableInfo.BiometricComp1 + " TEXT , "
            + DBManager.TableInfo.Appointment_Date1 + " TEXT , "
            + DBManager.TableInfo.Reg_From_Comp + " TEXT , "
            + DBManager.TableInfo.Witness + " TEXT , "
            + DBManager.TableInfo.Ship_Address + " TEXT , "
            + DBManager.TableInfo.Ship_Diff_Address + " TEXT , "
            + DBManager.TableInfo.LATITUDE + " TEXT , "
            + DBManager.TableInfo.LONGITUDE + " TEXT , "
            + DBManager.TableInfo.SYNCSTATUS + " TEXT , "
            + DBManager.TableInfo.APPTYPE + " TEXT , "
            + DBManager.TableInfo.DocumentId + " TEXT PRIMARY KEY " + ");";


    public final String CREATE_QUERY = "CREATE TABLE " + DBManager.TableInfo.TABLE_NAME
            + " (" + DBManager.TableInfo.StartDate + " TEXT ,"
            + DBManager.TableInfo.StartTime1 + " TEXT ,"
            + DBManager.TableInfo.StartTime2 + " TEXT ,"
            + DBManager.TableInfo.DocumentId + " TEXT,"
            + DBManager.TableInfo.AppointmentAddress + " TEXT,"
            + DBManager.TableInfo.AppointmentId + " TEXT PRIMARY KEY,"
            + DBManager.TableInfo.contactperson + " TEXT ,"
            + DBManager.TableInfo.landmark + " TEXT, "
            + DBManager.TableInfo.Executioner_id + " TEXT ,"
            + DBManager.TableInfo.DISTANCE + " TEXT ,"
            + DBManager.TableInfo.AMOUNT + " TEXT ,"
            + DBManager.TableInfo.TRANSPORTTYPE + " TEXT ,"
            + DBManager.TableInfo.SYNCSTATUSREPORT + " TEXT ,"
            + DBManager.TableInfo.att_status + " TEXT ,"
            + DBManager.TableInfo.post_status + " TEXT , "
            + DBManager.TableInfo.AppFor + " TEXT );";

    public final String CREATE_QUERY3 = "CREATE TABLE "
            + DBManager.TableInfo.TABLE_NAME3 + " ("
            + DBManager.TableInfo.Comment_id + " TEXT ,"
            + DBManager.TableInfo.Comments + " TEXT ,"
            + DBManager.TableInfo.Comment_user + " TEXT ,"
            + DBManager.TableInfo.Comment_owner + " TEXT ,"
            + DBManager.TableInfo.Comment_env + " TEXT,"
            + DBManager.TableInfo.Comment_is_done + " BOOLEAN,"
            + DBManager.TableInfo.SYNCSTATUS + " TEXT ,"
            + DBManager.TableInfo.REMINDER_DATE + " TEXT ,"
            + DBManager.TableInfo.Comment_date + " TEXT);";


    String sqlLocationDetails = " CREATE TABLE " + DBManager.TableInfo.TABLE_LOCATION_DETAILS
            + " (_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, "
            + DBManager.TableInfo.latitudeInput + " TEXT, " + DBManager.TableInfo.longitudeInput + " TEXT, " + DBManager.TableInfo.DATE
            + " TEXT, " + DBManager.TableInfo.UserName + " TEXT,"
            + DBManager.TableInfo.LAT_LONG_ADDRESS + " TEXT)";

    String PAYMENT_DETAILS = "CREATE TABLE " + PAYMENT + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.doc1 + " TEXT,"
            + DBManager.TableInfo.rep1 + " TEXT,"
            + DBManager.TableInfo.payamount + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT)";

    String UPDATE_PAYMENT_DETAILS = "CREATE TABLE " + UPDATEPAYMENT1 + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DOCID + " TEXT,"
            + DBManager.TableInfo.email1 + " TEXT,"
            + DBManager.TableInfo.amt + " TEXT,"
            + DBManager.TableInfo.date + " TEXT,"
            + DBManager.TableInfo.radiotype + " TEXT,"
            + DBManager.TableInfo.comment1 + " TEXT" + ")";


    String REM_PAYMENT = "CREATE TABLE " + DBManager.TableInfo.TABLE_REM_PAYMENT + "("
            + DBManager.TableInfo.KEYID + "INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DOCUMENT + " TEXT,"
            + DBManager.TableInfo.AMOUNT1 + " TEXT,"
            + DBManager.TableInfo.ENV + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String CREATE_CHECKK_TABLE = "CREATE TABLE " + GenericMethods.TABLE_CHECK_JOB + "("
            + GenericMethods.KEY_ID + " INTEGER PRIMARY KEY,"
            + GenericMethods.R_KEY + " TEXT,"
            + GenericMethods.JOB + " TEXT,"
            + GenericMethods.KEY_LOGIN_USER + " TEXT" + ")";

    String CREATE_LAST_UPDATE_TABLE = "CREATE TABLE " + GenericMethods.TABLE_LAST_UPDATE + "("
            + GenericMethods.KEY_ID + " INTEGER PRIMARY KEY,"
            + GenericMethods.LAST_UPDATED + " TEXT,"
            + GenericMethods.KEY_LOGIN_USER + " TEXT" + ")";


    String PARTIES1 = "CREATE TABLE " + DBManager.TableInfo.PARTIES1 + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DOCUMENT + " TEXT,"
            + DBManager.TableInfo.ATTENDANCE + " TEXT,"
            + DBManager.TableInfo.NAMENEW + " TEXT,"
            + DBManager.TableInfo.EMAILNEW + " TEXT,"
            + DBManager.TableInfo.PARTYTYPE + " TEXT,"
            + DBManager.TableInfo.POA + " TEXT,"
            + DBManager.TableInfo.CONTACT + " TEXT,"
            + DBManager.TableInfo.BIOMETRIC + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String PARTYUPDATE1 = "CREATE TABLE " + DBManager.TableInfo.UPDATEPARTY + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DOCU + " TEXT,"
            + DBManager.TableInfo.ATTEND + " TEXT,"
            + DBManager.TableInfo.EMAIL + " TEXT,"
            + DBManager.TableInfo.PARTY + " TEXT,"
            + DBManager.TableInfo.BIO + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String POSTUPDATE = "CREATE TABLE " + DBManager.TableInfo.POSTDOC + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.postdocument + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String POSTTASK = "CREATE TABLE " + DBManager.TableInfo.POST_TASK + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.IS_DONE + " TEXT,"
            + DBManager.TableInfo.TYPE + " TEXT,"
            + DBManager.TableInfo.REASON + " TEXT,"
            + DBManager.TableInfo.TASK_ID + " TEXT,"
            + DBManager.TableInfo.status1 + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String RESCHEDULE = "CREATE TABLE " + DBManager.TableInfo.RESCHEDULE + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.RESCHEDULEDATE + " TEXT,"
            + DBManager.TableInfo.RESCHEDULETIME + " TEXT,"
            + DBManager.TableInfo.RES_REASON + " TEXT,"
            + DBManager.TableInfo.DocumentId + " TEXT,"
            + DBManager.TableInfo.AppointmentId + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String multiwork = "CREATE TABLE " + DBManager.TableInfo.MULTIWORK + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DOCU + " TEXT,"
            + DBManager.TableInfo.AppointmentId + " TEXT,"
            + DBManager.TableInfo.STATUS + " TEXT,"
            + DBManager.TableInfo.reason + " TEXT ,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String APPOINTMENT = "CREATE TABLE " + APPOINTMENTSLOT + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.slotid1 + " TEXT,"
            + DBManager.TableInfo.starttime1 + " TEXT,"
            + DBManager.TableInfo.endtime1 + " TEXT,"
            + DBManager.TableInfo.available1 + " TEXT,"
            + DBManager.TableInfo.block1 + " TEXT,"
            + DBManager.TableInfo.discount1 + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String APPOINTMENTBOOKING = "CREATE TABLE " + DBManager.TableInfo.APPOINTMENTBOOKING + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.request_no + " TEXT,"
            + DBManager.TableInfo.slotid + " TEXT ,"
            + DBManager.TableInfo.app_date + " TEXT,"
            + DBManager.TableInfo.timenew + " TEXT,"
            + DBManager.TableInfo.division_id + " TEXT,"
            + DBManager.TableInfo.region_id + " TEXT,"
            + DBManager.TableInfo.attendees + " TEXT,"
            + DBManager.TableInfo.address + " TEXT,"
            + DBManager.TableInfo.free + " TEXT ,"
            + DBManager.TableInfo.free_reason + " TEXT,"
            + DBManager.TableInfo.attendeesemail + " TEXT ,"
            + DBManager.TableInfo.attendeescontact + " TEXT ,"
            + DBManager.TableInfo.contactpersonemail + " TEXT ,"
            + DBManager.TableInfo.contactpersoncont + " TEXT ,"
            + DBManager.TableInfo.landmark + " TEXT,"
            + DBManager.TableInfo.contactperson + " TEXT,"
            + DBManager.TableInfo.city + " TEXT ,"
            + DBManager.TableInfo.AppointmentId + " TEXT ,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String CREATE_QUERY5 = "CREATE TABLE " + DBManager.TableInfo.TABLE_NAME5 + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.User_name + " TEXT,"
            + DBManager.TableInfo.Role + " TEXT,"
            + DBManager.TableInfo.User_id + " TEXT,"
            + DBManager.TableInfo.Email + " TEXT,"
            + DBManager.TableInfo.role_id + " TEXT,"
            + DBManager.TableInfo.idu + " TEXT,"
            + DBManager.TableInfo.platformid + " TEXT"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String attendees = "CREATE TABLE " + DBManager.TableInfo.ATTENDEES + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.nameattend + " TEXT,"
            + DBManager.TableInfo.emailattend + " TEXT,"
            + DBManager.TableInfo.contactattend + " TEXT,"
            + DBManager.TableInfo.appointattend + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String WEEKENDOFF = "CREATE TABLE " + WEEKEND + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.from_date + " TEXT,"
            + DBManager.TableInfo.to_date + " TEXT,"
            + DBManager.TableInfo.statusoff + " TEXT,"
            + DBManager.TableInfo.reasonoff + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String TASK = "CREATE TABLE " + DBManager.TableInfo.TABLE_TASK + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.WITID + " TEXT,"
            + DBManager.TableInfo.COMMENT + " TEXT,"
            + DBManager.TableInfo.IS_DONE + " TEXT,"
            + DBManager.TableInfo.DOC + " TEXT,"
            + DBManager.TableInfo.CREATE + " TEXT,"
            + DBManager.TableInfo.REMAINDER + " TEXT,"
            + DBManager.TableInfo.ASSIGN + " TEXT,"
            + DBManager.TableInfo.TASK_ID + " TEXT,"
            + DBManager.TableInfo.TASK_NAME + " TEXT ,"
            + DBManager.TableInfo.ADHOC + " TEXT ,"
            + DBManager.TableInfo.WITNESS + " TEXT ,"
            + DBManager.TableInfo.COMPLAINT + " TEXT ,"
            + DBManager.TableInfo.status1 + " TEXT ,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String attendees2 = "CREATE TABLE " + DBManager.TableInfo.REASSIGN + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.Prev_owner + " TEXT,"
            + DBManager.TableInfo.new_owner + " TEXT,"
            + DBManager.TableInfo.reassign_reason + " TEXT,"
            + DBManager.TableInfo.status1 + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String attendees1 = "CREATE TABLE " + DBManager.TableInfo.NOT_APPLICABLE + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.task_id1 + " TEXT,"
            + DBManager.TableInfo.comment_applicable + " TEXT,"
            + DBManager.TableInfo.notapplicable_reason + " TEXT,"
            + DBManager.TableInfo.not_app + " TEXT,"
            + DBManager.TableInfo.status1 + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String ACTUAL_TIME = "CREATE TABLE " + DBManager.TableInfo.ACTUAL_TIME + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DocumentId + " TEXT,"
            + DBManager.TableInfo.AppointmentId + " TEXT,"
            + DBManager.TableInfo.actual_time + " TEXT,"
            + DBManager.TableInfo.call_time + " TEXT,"
            + DBManager.TableInfo.gen_distance + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    String CALL = "CREATE TABLE " + DBManager.TableInfo.CALL + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DocumentId + " TEXT,"
            + DBManager.TableInfo.AppointmentId + " TEXT,"
            + DBManager.TableInfo.gen_distance + " TEXT,"
            + DBManager.TableInfo.call_time + " TEXT,"
            + DBManager.TableInfo.actual_time + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";


    String ATT = "CREATE TABLE " + DBManager.TableInfo.ATT_STATUS + "("
            + DBManager.TableInfo.KEYID + " INTEGER PRIMARY KEY,"
            + DBManager.TableInfo.DocumentId + " TEXT,"
            + DBManager.TableInfo.att_status + " TEXT,"
            + DBManager.TableInfo.KEY_LOGIN_USER + " TEXT" + ")";

    public DBOperation(Context context) {
        super(context, DBManager.TableInfo.DATABASE_NAME, null, database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_QUERY1);
//        System.out.println("Table User Created Successfully");

        db.execSQL(DOCUMENTT);
        // System.out.println("Table Anulom Created Successfully");

        db.execSQL(CREATE_QUERY);
//        System.out.println("Table Appointment Created Successfully");
//
        db.execSQL(CREATE_QUERY3);
//        System.out.println("Table Comments Created Successfully");


        db.execSQL(PAYMENT_DETAILS);
//        System.out.println("Payment table  Created Successfully");

        db.execSQL(UPDATE_PAYMENT_DETAILS);
//        System.out.println("Update payment table  Created Successfully");

        db.execSQL(sqlLocationDetails);
//        System.out.println("Table Location Created Successfully");

        db.execSQL(CREATE_CHECKK_TABLE);
//        System.out.println("Check table  Created Successfully");

        db.execSQL(CREATE_LAST_UPDATE_TABLE);
//        System.out.println("Last Update table  Created Successfully");

        db.execSQL(PARTIES1);
//        System.out.println("Parties table  Created Successfully");

        db.execSQL(POSTUPDATE);
//        System.out.println("Post table  Created Successfully");

        db.execSQL(REM_PAYMENT);
//        System.out.println("Rem_payment table  Created Successfully");

        db.execSQL(PARTYUPDATE1);
//        System.out.println("Parties table  Created Successfully");

        db.execSQL(attendees2);
        //System.out.println("Table not_applicable Created Successfully");
        db.execSQL(attendees1);

        //System.out.println("Table reassign Created Successfully");

        db.execSQL(POSTTASK);
//        System.out.println("Post task table  Created Successfully");


        db.execSQL(CREATE_QUERY5);
//        System.out.println("Table NewUser Created Successfully");

        db.execSQL(RESCHEDULE);
//        System.out.println("Table reschedule Created Successfully");

        db.execSQL(APPOINTMENT);
//        System.out.println("Table slot Created Successfully");

        db.execSQL(APPOINTMENTBOOKING);
        //System.out.println("Table appbooking Created Successfully");

        db.execSQL(multiwork);
        //System.out.println("Table NewUser Created Successfully");

        db.execSQL(attendees);
//        System.out.println("Table attendees Created Successfully");

        db.execSQL(WEEKENDOFF);
//        System.out.println("Table weekend/off Created Successfully");
        db.execSQL(ACTUAL_TIME);
        // System.out.println("added");

        db.execSQL(TASK);

        db.execSQL(CALL);
        db.execSQL(ATT);
//        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
//
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//               System.out.println("Table Name=> "+c.getString(0));
//                c.moveToNext();
//            }
//        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

//        String ALTER_DOCUMENT_LATITUDE = "ALTER TABLE "
        //


//        String ALTER_ACVRREPORT_SYNCSTtt                                                                                                                                                                                                                                                                                                                                      ATUS = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_NAME + " ADD COLUMN " + DBManager.TableInfo.SYNCSTATUSREPORT + " TEXT";

//        String ALTER_DOCUMENT_APPTYPE = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_NAME2 + " ADD COLUMN " ;
//
//        System.out.println("Added successfully");
//        String ALTER_USER_TABLE1 = "ALTER TABLE "
//                    + DBManager.TableInfo.TABLE_NAME5 + " ADD COLUMN " +DBManager.TableInfo.role_id + " TEXT";
//
//            db.execSQL(ALTER_USER_TABLE1);
//            System.out.println("Added1 successfully");
//            String ALTER_USER_TABLE2 = "ALTER TABLE "
//                    + DBManager.TableInfo.TABLE_NAME5 + " ADD COLUMN " +DBManager.TableInfo.idu + " TEXT";
//
//            db.execSQL(ALTER_USER_TABLE2);
//            System.out.println("Added2 successfully");
//        String ALTER_USER_TABLE = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_NAME5 + " ADD COLUMN " +DBManager.TableInfo.platformid + " TEXT";
//
//        db.execSQL(ALTER_USER_TABLE);
//
//


//        String ALTER_email = "ALTER TABLE "
//                + DBManager.TableInfo.APPOINTMENTBOOKING + " ADD COLUMN " +DBManager.TableInfo.attendeesemail + " TEXT";

//        db.execSQL(ALTER_email);
//        System.out.println("ALTER_email Created Successfully");
//
//
//   String ALTER_contact12 = "ALTER TABLE "
//        + DBManager.TableInfo.APPOINTMENTBOOKING + " ADD COLUMN " ;
//
//        db.execSQL(ALTER_contact12);
//        System.out.println("ALTER_contact12 Created Successfully");

//
//
//        db.execSQL(ALTER_appoint);

//        String ALTER_contact12 = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_TASK + " ADD COLUMN " ;
//
//        db.execSQL(ALTER_contact12);
//        System.out.println("adhoc Created Successfully");
//
//
//
//        db.execSQL(ALTER_appoint);
//        System.out.println("Witness Created Successfully");
//
//        String ALTER_appoint1 = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_TASK + " ADD COLUMN " +DBManager.TableInfo.COMPLAINT + " TEXT";
//
//        db.execSQL(ALTER_appoint1);

//        String ALTER_task1 = "ALTER TABLE "
//                + DBManager.TableInfo.REASSIGN + " ADD COLUMN " +DBManager.TableInfo.status1 + " TEXT";
//        db.execSQL(ALTER_task1);
//        System.out.println(" Created2 Successfully");
//        String ALTER_task2 = "ALTER TABLE "
//                + DBManager.TableInfo.NOT_APPLICABLE + " ADD COLUMN " +DBManager.TableInfo.status1 + " TEXT";
//        db.execSQL(ALTER_task2);
//        System.out.println(" Created3 Successfully");

//        String ALTER_task = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_TASK + " ADD COLUMN " ;
//        db.execSQL(ALTER_task);
//        System.out.println(" Created1 Successfully");
//        String ALTER_email1 = "ALTER TABLE " + DBManager.TableInfo.TABLE_NAME + " ADD COLUMN "  + " TEXT";
//        db.execSQL(ALTER_email1);
//        System.out.println("ALTER_email1 Created Successfully");
//        System.out.println("added");
//String ALTER_appoint = "ALTER TABLE "
//        + DBManager.TableInfo.APPOINTMENTBOOKING + " ADD COLUMN " ;
//        db.execSQL(ALTER_appoint);
//        String ALTER_appoint1 = "ALTER TABLE "
//                + DBManager.TableInfo.APPOINTMENTBOOKING + " ADD COLUMN " +DBManager.TableInfo.AppointmentId + " TEXT";
//        String Call_time = "ALTER TABLE "
//                + DBManager.TableInfo.ACTUAL_TIME + " ADD COLUMN " + DBManager.TableInfo.call_time + " TEXT";
//        db.execSQL(Call_time);
//        String ALTER_FREE = "ALTER TABLE "
//                + DBManager.TableInfo.TABLE_NAME + " ADD COLUMN " ;
// String ALTER_REASON = "ALTER TABLE "
//        + DBManager.TableInfo.TABLE_NAME + " ADD COLUMN " ;
////S
//        db.execSQL(ALTER_REASON);
//        db.execSQL(ALTER_FREE);
//        String landmark1 = "ALTER TABLE " + DBManager.TableInfo.TABLE_NAME + " ADD COLUMN " + DBManager.TableInfo.post_status + " TEXT";
//        db.execSQL(landmark1);
//        System.out.println("Added  successfully");
//       // System.out.println("Att added successfully");
//        System.out.println("Call Created Successfully");
// String ALTER_contact = "ALTER TABLE "
//        + DBManager.TableInfo.ACTUAL_TIME + " ADD COLUMN " +DBManager.TableInfo.DocumentId + " TEXT";
//
//        db.execSQL(ALTER_contact);
//        System.out.println("ALTER_contact Created Successfully");
//
//        System.out.println("gen_distance Created Successfully");
//        if (6 >= oldVersion) {
//            db.execSQL(ALTER_DOCUMENT_LATITUDE);
//            db.execSQL(ALTER_DOCUMENT_LONGITUDE);
//            db.execSQL(ALTER_DOCUMENT_SYNCSTATUS);
//            db.execSQL(ALTER_COMMENT_SYNCSTATUS);
//            db.execSQL(ALTER_ACVRREPORT_SYNCSTATUS);
////////        }
//        if (newVersion > oldVersion) {
//
//
//        }
    }

    public void InsertRecord(DBOperation dop, String rtoken, String rkey, String username, String usermail,
                             String usercontact, String propertyadd, String oname, String ocontact, String omail, String oaddress,
                             String tname, String tcontact, String tmail, String taddress, String status, String umail1, String docid,
                             String appdate, String biocomp, String appdate1, String biocomp1, String regfromcomp, String witness,
                             String shipadd, String shdiffadd, String strlatitude, String strlongitude, String syncstatus, String apptype) {

        SQLiteDatabase sqlite = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.ReportToken, rtoken);
        cv.put(DBManager.TableInfo.ReportKey, rkey);
        cv.put(DBManager.TableInfo.Uname, username);
        cv.put(DBManager.TableInfo.Umail, usermail);
        cv.put(DBManager.TableInfo.Ucontact, usercontact);
        cv.put(DBManager.TableInfo.PropertyAddress, propertyadd);
        cv.put(DBManager.TableInfo.OwnerName, oname);
        cv.put(DBManager.TableInfo.OwnerContact, ocontact);
        cv.put(DBManager.TableInfo.OwnerEmail, omail);
        cv.put(DBManager.TableInfo.OwnerAddress, oaddress);
        cv.put(DBManager.TableInfo.TenantName, tname);
        cv.put(DBManager.TableInfo.TenantContact, tcontact);
        cv.put(DBManager.TableInfo.TenantEmail, tmail);
        cv.put(DBManager.TableInfo.TenantAddress, taddress);
        cv.put(DBManager.TableInfo.Status, status);
        cv.put(DBManager.TableInfo.UserEmail, umail1);
        cv.put(DBManager.TableInfo.DocumentId, docid);
        cv.put(DBManager.TableInfo.Appointment_Date, appdate);
        cv.put(DBManager.TableInfo.BiometricComp, biocomp);
        cv.put(DBManager.TableInfo.Appointment_Date1, appdate1);
        cv.put(DBManager.TableInfo.BiometricComp1, biocomp1);
        cv.put(DBManager.TableInfo.Reg_From_Comp, regfromcomp);
        cv.put(DBManager.TableInfo.Witness, witness);
        cv.put(DBManager.TableInfo.Ship_Address, shipadd);
        cv.put(DBManager.TableInfo.Ship_Diff_Address, shdiffadd);
        cv.put(DBManager.TableInfo.SYNCSTATUS, syncstatus);
        cv.put(DBManager.TableInfo.LATITUDE, strlatitude);
        cv.put(DBManager.TableInfo.LONGITUDE, strlongitude);
        cv.put(DBManager.TableInfo.APPTYPE, apptype);


        long res = sqlite.insert(DBManager.TableInfo.TABLE_NAME2, null, cv);


        //Log.d("Datatbase", "DOCUMENT Record Inserted successfully");

        //System.out.println("DOCUMENT................................" + res+" "+"Oname:"+oname+"tname:"+tname+contactperson);
    }

    public void InsertRecord1(DBOperation dop, String uname) {
        SQLiteDatabase sqlite = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.UserEmail, uname);
        long res = sqlite.insert(DBManager.TableInfo.TABLE_NAME1, null, cv);
        //Log.d("Datatbase", "USER Record Inserted successfully");
    }

    public void InsertRecord2(DBOperation dop, String docid, String appid, String date, String startime, String endtime,
                              String appadd, String exid, String appfor, String distance, String amount, String transporttype, String sync, String contactperson, String land, String attendeesstatus, String poststatus) {

        SQLiteDatabase sqlite = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.DocumentId, docid);
        cv.put(DBManager.TableInfo.AppointmentId, appid);
        cv.put(DBManager.TableInfo.StartDate, date);
        cv.put(DBManager.TableInfo.StartTime1, startime);
        cv.put(DBManager.TableInfo.StartTime2, endtime);
        cv.put(DBManager.TableInfo.AppointmentAddress, appadd);
        cv.put(DBManager.TableInfo.Executioner_id, exid);
        cv.put(DBManager.TableInfo.AppFor, appfor);
        cv.put(DBManager.TableInfo.DISTANCE, distance);
        cv.put(DBManager.TableInfo.AMOUNT, amount);
        cv.put(DBManager.TableInfo.TRANSPORTTYPE, transporttype);
        cv.put(DBManager.TableInfo.SYNCSTATUSREPORT, sync);
        cv.put(DBManager.TableInfo.contactperson, contactperson);
        cv.put(DBManager.TableInfo.landmark, land);
        cv.put(DBManager.TableInfo.att_status, attendeesstatus);
        cv.put(DBManager.TableInfo.post_status, poststatus);

        long res = sqlite.insert(DBManager.TableInfo.TABLE_NAME, null, cv);
        //System.out.println("doc id from insert record 2:"+docid+" "+res);
        //Log.d("Datatbase", "APPOINTMENT Record Inserted successfully");
        dop.close();

    }

    public void InsertRecord3(DBOperation dop, String envid, String custcomments, String user, String owner, String id,
                              String isdone, String cdate, String SyncStatus, String reminderdate) {

        SQLiteDatabase sqlite = dop.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBManager.TableInfo.Comment_env, envid);
        cv.put(DBManager.TableInfo.Comments, custcomments);
        cv.put(DBManager.TableInfo.Comment_user, user);
        cv.put(DBManager.TableInfo.Comment_owner, owner);
        cv.put(DBManager.TableInfo.Comment_id, id);
        cv.put(DBManager.TableInfo.Comment_is_done, isdone);
        cv.put(DBManager.TableInfo.Comment_date, cdate);
        cv.put(DBManager.TableInfo.SYNCSTATUS, SyncStatus);
        cv.put(DBManager.TableInfo.REMINDER_DATE, reminderdate);

        long res = sqlite.insert(DBManager.TableInfo.TABLE_NAME3, null, cv);

        //Log.d("Datatbase", "Comments Record Inserted successfully");
        dop.close();
    }


    public void InsertRecord5(DBOperation dop, String userid, String username, String email, String role, String platformid, String roleid, String idu) {

        SQLiteDatabase sqlite = dop.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBManager.TableInfo.User_id, userid);
        cv.put(DBManager.TableInfo.User_name, username);
        cv.put(DBManager.TableInfo.Email, email);
        cv.put(DBManager.TableInfo.Role, role);
        cv.put(DBManager.TableInfo.platformid, platformid);
        cv.put(DBManager.TableInfo.role_id, roleid);
        cv.put(DBManager.TableInfo.idu, idu);
        long res = sqlite.insert(DBManager.TableInfo.TABLE_NAME5, null, cv);
//		long res = sqlite.insertWithOnConflict(DBManager.TableInfo.TABLE_NAME5, null, cv,SQLiteDatabase.CONFLICT_REPLACE);

        //Log.d("Datatbase", "NewUser Record Inserted successfully");
//        System.out.println("Result" + cv);
//        System.out.println("Result" + res);
        dop.close();
    }


    // View

    public ArrayList<HashMap<String, String>> getAllData(DBOperation db) {

        ArrayList<HashMap<String, String>> idlist = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();

        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor cr = sqlite.rawQuery(selectQuery, null);
        if (cr.moveToFirst()) {
            cr.moveToFirst();
            do {
                HashMap<String, String> idmap = new HashMap<String, String>();
                String docidnewdb = cr.getString(cr.getColumnIndex("Did"));
                String appidnewdb = cr.getString(cr.getColumnIndex("Aid"));
                String onamedb = cr.getString(cr.getColumnIndex("Oname"));
                String exiddb = cr.getString(cr.getColumnIndex("Exid"));
                idmap.put("Did", docidnewdb);
                idmap.put("Aid", appidnewdb);
                idmap.put("Oname", onamedb);
                idmap.put("Exid", exiddb);
                idlist.add(idmap);
            } while (cr.moveToNext());
        }
        cr.close();
        return idlist;

    }

    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME5;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("email2")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public boolean validateUser(String mailid) {
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + DBManager.TableInfo.TABLE_NAME1 + " WHERE " + DBManager.TableInfo.UserEmail + "='" + mailid + "'", null);
        if (c.getCount() > 0)
            return true;
        return false;
    }

    public long UpdateAllRecord(DBOperation db, String docid, String bioSel, String editedbiocomp1, String regSel, String witSel) {
        // TODO Auto-generated method stub

        String selection = DBManager.TableInfo.DocumentId + " = ? ";
        String a[] = {docid};
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.BiometricComp1, bioSel);
        cv.put(DBManager.TableInfo.BiometricComp, editedbiocomp1);
        cv.put(DBManager.TableInfo.Reg_From_Comp, regSel);
        cv.put(DBManager.TableInfo.Witness, witSel);
        cv.put(DBManager.TableInfo.SYNCSTATUS, "ASYNC");

        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME2, cv, selection, a);
//        System.out.println(" All Record Updated Successfully");
        return result;


    }


    public void Update(DBOperation db, String docid, String rtoken1, String rkey1, String uname1, String email1,
                       String contact_number1, String paddress1, String oname1, String ocontact1, String oemail1, String oaddress1,
                       String tname1, String tcontact1, String temail1, String taddress1, String status1, String appdate,
                       String biocomp, String appdate1, String biocomp1, String regfromcomp, String witness, String shipadd,
                       String shipdiffadd, String strlatitude, String strlongitude, String apptype) {
        // TODO Auto-generated method stub

        String selection = DBManager.TableInfo.DocumentId + " = ? ";

        String a[] = {docid};

        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.ReportToken, rtoken1);
        cv.put(DBManager.TableInfo.ReportKey, rkey1);
        cv.put(DBManager.TableInfo.Uname, uname1);
        cv.put(DBManager.TableInfo.Umail, email1);
        cv.put(DBManager.TableInfo.Ucontact, contact_number1);
        cv.put(DBManager.TableInfo.PropertyAddress, paddress1);
        cv.put(DBManager.TableInfo.OwnerName, oname1);
        cv.put(DBManager.TableInfo.OwnerContact, ocontact1);
        cv.put(DBManager.TableInfo.OwnerEmail, oemail1);
        cv.put(DBManager.TableInfo.OwnerAddress, oaddress1);
        cv.put(DBManager.TableInfo.TenantName, tname1);
        cv.put(DBManager.TableInfo.TenantContact, tcontact1);
        cv.put(DBManager.TableInfo.TenantEmail, temail1);
        cv.put(DBManager.TableInfo.TenantAddress, taddress1);
        cv.put(DBManager.TableInfo.Status, status1);
        cv.put(DBManager.TableInfo.Appointment_Date, appdate);
        cv.put(DBManager.TableInfo.BiometricComp, biocomp);
        cv.put(DBManager.TableInfo.Appointment_Date1, appdate1);
        cv.put(DBManager.TableInfo.BiometricComp1, biocomp1);
        cv.put(DBManager.TableInfo.Reg_From_Comp, regfromcomp);
        cv.put(DBManager.TableInfo.Witness, witness);
        cv.put(DBManager.TableInfo.Ship_Address, shipadd);
        cv.put(DBManager.TableInfo.Ship_Diff_Address, shipdiffadd);
        cv.put(DBManager.TableInfo.LATITUDE, strlatitude);
        cv.put(DBManager.TableInfo.LONGITUDE, strlongitude);
        cv.put(DBManager.TableInfo.APPTYPE, apptype);


        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME2, cv, selection, a);

//        System.out.println(" Name Updated Successfully");
        db.close();
    }

    public void Update1(DBOperation db, String appid, String docid, String appaddress, String exeid,
                        String appfor, String distance, String amount, String transporttype, String acvrtstatuas) {
        // TODO Auto-generated method stub

        String selection = DBManager.TableInfo.AppointmentId + " = ? ";

        String a[] = {appid};
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.DocumentId, docid);
        cv.put(DBManager.TableInfo.AppointmentAddress, appaddress);
        cv.put(DBManager.TableInfo.Executioner_id, exeid);
        cv.put(DBManager.TableInfo.AppFor, appfor);
        cv.put(DBManager.TableInfo.DISTANCE, distance);
        cv.put(DBManager.TableInfo.AMOUNT, amount);
        cv.put(DBManager.TableInfo.TRANSPORTTYPE, transporttype);
        cv.put(DBManager.TableInfo.SYNCSTATUSREPORT, acvrtstatuas);


        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME, cv, selection, a);

//        System.out.println(" Appid Updated Successfully");
        db.close();
    }


    public void Updateuser(DBOperation db, String userid, String username, String email, String role, String platformid, String roleid, String idu) {
        String selection = DBManager.TableInfo.User_id + " = ? ";

        String a[] = {userid};

        SQLiteDatabase sqlite = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBManager.TableInfo.User_id, userid);
        cv.put(DBManager.TableInfo.User_name, username);
        cv.put(DBManager.TableInfo.Email, email);
        cv.put(DBManager.TableInfo.Role, role);
        cv.put(DBManager.TableInfo.platformid, platformid);
        cv.put(DBManager.TableInfo.role_id, roleid);
        cv.put(DBManager.TableInfo.idu, idu);


        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME5, cv, selection, a);

//        System.out.println(" userdataupdate succssfuly Updated Successfully");

        db.close();
    }


    public void insertLocationDetails(DBOperation dop, HashMap<String, String> map) {

        ContentValues cv = new ContentValues();
        SQLiteDatabase sqlite = dop.getWritableDatabase();
        cv.put(DBManager.TableInfo.latitudeInput, map.get(DBManager.TableInfo.latitudeInput));
        cv.put(DBManager.TableInfo.longitudeInput, map.get(DBManager.TableInfo.longitudeInput));
        cv.put(DBManager.TableInfo.DATE, map.get(DBManager.TableInfo.DATE));
        cv.put(DBManager.TableInfo.UserName, map.get(DBManager.TableInfo.UserName));

        try {
            sqlite.insert(DBManager.TableInfo.TABLE_LOCATION_DETAILS, null, cv);
            //Log.d("Datatbase", "Location Record Inserted successfully");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        dop.close();
    }

    public ArrayList<HashMap<String, String>> getLocationDetailsUsername(DBOperation dop) {

        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {
            String query = "SELECT * from " + DBManager.TableInfo.TABLE_LOCATION_DETAILS;
            SQLiteDatabase sqlite = dop.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> username = new HashMap<String, String>();

                //username.put("ID",c.getString(c.getColumnIndex("_id")));
                username.put(DBManager.TableInfo.UserName, c.getString(c.getColumnIndex(DBManager.TableInfo.UserName)));
                username.put(DBManager.TableInfo.LATITUDE, c.getString(c.getColumnIndex(DBManager.TableInfo.LATITUDE)));
                username.put(DBManager.TableInfo.LONGITUDE, c.getString(c.getColumnIndex(DBManager.TableInfo.LONGITUDE)));
                username.put(DBManager.TableInfo.DATE, c.getString(c.getColumnIndex(DBManager.TableInfo.DATE)));

                listUsername.add(username);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        dop.close();
        return listUsername;
    }

    public void closeCursor(Cursor cur) {

        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

    }

    public void delLocationDetails(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_LOCATION_DETAILS;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }


    public void delAppointment(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_NAME;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public void deluser(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_NAME1;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }


    public void delDocument(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_NAME2;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public void delComments(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_NAME3;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }


    public void delUserRole(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();
            String query = "DELETE FROM " + DBManager.TableInfo.TABLE_NAME5;
            sqlite.execSQL(query);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public ArrayList<HashMap<String, String>> getSelectedData(DBOperation db, String docid) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME2 + " where " + DBManager.TableInfo.DocumentId + "='" + docid + "'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("OwnBio", c.getString(c.getColumnIndex(DBManager.TableInfo.BiometricComp1)));
                selectiondetails.put("TenantBio", c.getString(c.getColumnIndex(DBManager.TableInfo.BiometricComp)));
                selectiondetails.put("Wit1", c.getString(c.getColumnIndex(DBManager.TableInfo.Reg_From_Comp)));
                selectiondetails.put("Wit2", c.getString(c.getColumnIndex(DBManager.TableInfo.Witness)));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getSyncStatusReport(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME2 + " where " + DBManager.TableInfo.SYNCSTATUS + "='ASYNC'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("DocId", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails.put("OwnBio", c.getString(c.getColumnIndex(DBManager.TableInfo.BiometricComp1)));
                selectiondetails.put("TenantBio", c.getString(c.getColumnIndex(DBManager.TableInfo.BiometricComp)));
                selectiondetails.put("Wit1", c.getString(c.getColumnIndex(DBManager.TableInfo.Reg_From_Comp)));
                selectiondetails.put("Wit2", c.getString(c.getColumnIndex(DBManager.TableInfo.Witness)));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getPaymentReport(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + UPDATEPAYMENT1 + " where " + DBManager.TableInfo.email1 + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.DOCID)));
                selectiondetails.put("email", c.getString(c.getColumnIndex(DBManager.TableInfo.email1)));
                selectiondetails.put("amount", c.getString(c.getColumnIndex(DBManager.TableInfo.amt)));
                selectiondetails.put("date", c.getString(c.getColumnIndex(DBManager.TableInfo.date)));
                selectiondetails.put("radiotype", c.getString(c.getColumnIndex(DBManager.TableInfo.radiotype)));
                selectiondetails.put("comment", c.getString(c.getColumnIndex(DBManager.TableInfo.comment1)));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getbiostatus(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + ATT_STATUS + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("documentId", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails.put("bio_status", c.getString(c.getColumnIndex(DBManager.TableInfo.att_status)));

                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }


    public ArrayList<HashMap<String, String>> getPaymentReport1(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + PAYMENT + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.doc1)));
                selectiondetails.put("amount", c.getString(c.getColumnIndex(DBManager.TableInfo.payamount)));
                selectiondetails.put("rkey", c.getString(c.getColumnIndex(DBManager.TableInfo.rep1)));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getreassign(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + REASSIGN + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("Prev_owner", c.getString(c.getColumnIndex(DBManager.TableInfo.Prev_owner)));
                selectiondetails.put("new_owner", c.getString(c.getColumnIndex(DBManager.TableInfo.new_owner)));
                selectiondetails.put("reassign_reason", c.getString(c.getColumnIndex(DBManager.TableInfo.reassign_reason)));
                selectiondetails.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                selectiondetails.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getnot_applicable(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + NOT_APPLICABLE + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("task_id", c.getString(c.getColumnIndex(DBManager.TableInfo.task_id1)));
                selectiondetails.put("comment_applicable", c.getString(c.getColumnIndex(DBManager.TableInfo.comment_applicable)));
                selectiondetails.put("reason_applicable", c.getString(c.getColumnIndex(DBManager.TableInfo.notapplicable_reason)));
                selectiondetails.put("not_app", c.getString(c.getColumnIndex(DBManager.TableInfo.not_app)));
                selectiondetails.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                selectiondetails.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));

                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getattendees(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + DBManager.TableInfo.ATTENDEES + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            while (c.moveToNext()) {

                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("name", c.getString(c.getColumnIndex("name")));
                selectiondetails.put("email", c.getString(c.getColumnIndex("email")));
                selectiondetails.put("contact", c.getString(c.getColumnIndex("contact")));
                selectiondetails.put("appoint", c.getString(c.getColumnIndex("appointment")));
                listUsername.add(selectiondetails);
            }

            closeCursor(c);
            sqlite1.close();

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getPartiesReport(DBOperation db) {
        ArrayList<HashMap<String, String>> listParties = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.PARTIES1 + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                selectiondetails1.put("document_id", c.getString(c.getColumnIndex(DOCUMENT)));
                selectiondetails1.put("att_id", c.getString(c.getColumnIndex(DBManager.TableInfo.ATTENDANCE)));
                selectiondetails1.put("name", c.getString(c.getColumnIndex(DBManager.TableInfo.NAMENEW)));
                selectiondetails1.put("email", c.getString(c.getColumnIndex(DBManager.TableInfo.EMAILNEW)));
                selectiondetails1.put("party_type", c.getString(c.getColumnIndex(DBManager.TableInfo.PARTYTYPE)));
                selectiondetails1.put("poa", c.getString(c.getColumnIndex(DBManager.TableInfo.POA)));
                selectiondetails1.put("contact_no", c.getString(c.getColumnIndex(DBManager.TableInfo.CONTACT)));
                selectiondetails1.put("biometric", c.getString(c.getColumnIndex(DBManager.TableInfo.BIOMETRIC)));


                listParties.add(selectiondetails1);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();

        return listParties;
    }


    public ArrayList<HashMap<String, String>> getPartypost(DBOperation db) {
        ArrayList<HashMap<String, String>> listParties11 = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.UPDATEPARTY + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails10 = new HashMap<String, String>();
                selectiondetails10.put("document_id", c.getString(c.getColumnIndex(DBManager.TableInfo.DOCU)));
                selectiondetails10.put("att_id", c.getString(c.getColumnIndex(DBManager.TableInfo.ATTEND)));
                selectiondetails10.put("email", c.getString(c.getColumnIndex(DBManager.TableInfo.EMAIL)));
                selectiondetails10.put("party_type", c.getString(c.getColumnIndex(DBManager.TableInfo.PARTY)));
                selectiondetails10.put("biometric", c.getString(c.getColumnIndex(DBManager.TableInfo.BIO)));
                selectiondetails10.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));

                listParties11.add(selectiondetails10);

            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listParties11;
    }

    public ArrayList<HashMap<String, String>> postappointment(DBOperation db) {
        ArrayList<HashMap<String, String>> listParties11 = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.APPOINTMENTBOOKING + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails10 = new HashMap<String, String>();

                selectiondetails10.put("user", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                selectiondetails10.put("request_no", c.getString(c.getColumnIndex(DBManager.TableInfo.request_no)));
                selectiondetails10.put("slot_id", c.getString(c.getColumnIndex(DBManager.TableInfo.slotid)));
                selectiondetails10.put("division_id", c.getString(c.getColumnIndex(DBManager.TableInfo.division_id)));
                selectiondetails10.put("app_date", c.getString(c.getColumnIndex(DBManager.TableInfo.app_date)));
                selectiondetails10.put("region_id", c.getString(c.getColumnIndex(DBManager.TableInfo.region_id)));
                selectiondetails10.put("free", c.getString(c.getColumnIndex(DBManager.TableInfo.free)));
                selectiondetails10.put("free_reason", c.getString(c.getColumnIndex(DBManager.TableInfo.free_reason)));
                selectiondetails10.put("attendees", c.getString(c.getColumnIndex(DBManager.TableInfo.attendees)));
                selectiondetails10.put("attendeesmail", c.getString(c.getColumnIndex(DBManager.TableInfo.attendeesemail)));
                selectiondetails10.put("attendeescontact", c.getString(c.getColumnIndex(DBManager.TableInfo.attendeescontact)));
                selectiondetails10.put("address", c.getString(c.getColumnIndex(DBManager.TableInfo.address)));
                selectiondetails10.put("landmark", c.getString(c.getColumnIndex(landmark)));
                selectiondetails10.put("contact_person", c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson)));
                selectiondetails10.put("contact_personmail", c.getString(c.getColumnIndex(DBManager.TableInfo.contactpersonemail)));
                selectiondetails10.put("contact_personcontact", c.getString(c.getColumnIndex(DBManager.TableInfo.contactpersoncont)));
                selectiondetails10.put("city", c.getString(c.getColumnIndex(DBManager.TableInfo.city)));
                selectiondetails10.put("appointment_id", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));
                selectiondetails10.put("document_id", c.getString(c.getColumnIndex(DBManager.TableInfo.timenew)));


                listParties11.add(selectiondetails10);

            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listParties11;
    }


    public List<String> getPostDoc(DBOperation db) {
        List<String> postlist = new ArrayList<String>();

        try {

            String query = "select * from " + DBManager.TableInfo.POSTDOC + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            while (c.moveToNext()) {

                postlist.add(c.getString(c.getColumnIndex(DBManager.TableInfo.postdocument)));
            }
            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return postlist;
    }


    public ArrayList<HashMap<String, String>> getadhocdetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listadhoc = new ArrayList<HashMap<String, String>>();

        try {

            //System.out.println("first try");

            String query = "select * from " + DBManager.TableInfo.TABLE_TASK + " where " + DBManager.TableInfo.ADHOC + " = '" + "Adhoc" + "'";
            //System.out.println("query"+query);
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String curdate1 = df2.format(c1.getTime());

            while (c.moveToNext()) {
                //System.out.println("first try1");

                if ((c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).equals("null")) || (c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).substring(0, 10).equals(curdate1))) {
                   // System.out.println("first try2");
                    HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                    selectiondetails1.put("id1", c.getString(c.getColumnIndex(DBManager.TableInfo.WITID)));
                    selectiondetails1.put("comment", c.getString(c.getColumnIndex(DBManager.TableInfo.COMMENT)));
                    selectiondetails1.put("is_done", c.getString(c.getColumnIndex(DBManager.TableInfo.IS_DONE)));
                    selectiondetails1.put("document_id", c.getString(c.getColumnIndex(DBManager.TableInfo.DOC)));
                    selectiondetails1.put("created_at", c.getString(c.getColumnIndex(DBManager.TableInfo.CREATE)));
                    String remainder = c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER));
                    selectiondetails1.put("reminder_dt", remainder);
                    selectiondetails1.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                    selectiondetails1.put("assign_by", c.getString(c.getColumnIndex(DBManager.TableInfo.ASSIGN)));
                    selectiondetails1.put("task_id", c.getString(c.getColumnIndex(DBManager.TableInfo.TASK_ID)));
                    listadhoc.add(selectiondetails1);
                }
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
System.out.println("adhocsize:"+listadhoc.size());
        return listadhoc;
    }

    public ArrayList<HashMap<String, String>> getWitnessdetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listadhoc = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.TABLE_TASK + " where " + DBManager.TableInfo.WITNESS + " = '" + "Witness" + "'";

            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);

            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String curdate1 = df2.format(c1.getTime());

            while (c.moveToNext()) {
                if ((c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).equals("null")) || (c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).substring(0, 10).equals(curdate1))) {

                    HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                    selectiondetails1.put("id1", c.getString(c.getColumnIndex(DBManager.TableInfo.WITID)));
                    selectiondetails1.put("comment", c.getString(c.getColumnIndex(DBManager.TableInfo.COMMENT)));
                    selectiondetails1.put("is_done", c.getString(c.getColumnIndex(DBManager.TableInfo.IS_DONE)));
                    selectiondetails1.put("document_id", c.getString(c.getColumnIndex(DBManager.TableInfo.DOC)));
                    selectiondetails1.put("created_at", c.getString(c.getColumnIndex(DBManager.TableInfo.CREATE)));
                    String remainder = c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER));
                    selectiondetails1.put("reminder_dt", remainder);
                    selectiondetails1.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                    selectiondetails1.put("assign_by", c.getString(c.getColumnIndex(DBManager.TableInfo.ASSIGN)));
                    selectiondetails1.put("task_id", c.getString(c.getColumnIndex(DBManager.TableInfo.TASK_ID)));

                    listadhoc.add(selectiondetails1);
                }
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        System.out.println("witnesssize:"+listadhoc.size());
        return listadhoc;
    }

    public ArrayList<HashMap<String, String>> getComplaintdetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listadhoc = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.TABLE_TASK + " where " + DBManager.TableInfo.COMPLAINT + " = '" + "Complaint" + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String curdate1 = df2.format(c1.getTime());
            while (c.moveToNext()) {
                if ((c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).equals("null")) || (c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER)).substring(0, 10).equals(curdate1))) {

                    HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                    selectiondetails1.put("id1", c.getString(c.getColumnIndex(DBManager.TableInfo.WITID)));
                    selectiondetails1.put("comment", c.getString(c.getColumnIndex(DBManager.TableInfo.COMMENT)));
                    selectiondetails1.put("is_done", c.getString(c.getColumnIndex(DBManager.TableInfo.IS_DONE)));
                    selectiondetails1.put("document_id", c.getString(c.getColumnIndex(DBManager.TableInfo.DOC)));
                    selectiondetails1.put("created_at", c.getString(c.getColumnIndex(DBManager.TableInfo.CREATE)));
                    String remainder = c.getString(c.getColumnIndex(DBManager.TableInfo.REMAINDER));
                    selectiondetails1.put("reminder_dt", remainder);
                    selectiondetails1.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                    selectiondetails1.put("assign_by", c.getString(c.getColumnIndex(DBManager.TableInfo.ASSIGN)));
                    selectiondetails1.put("task_id", c.getString(c.getColumnIndex(DBManager.TableInfo.TASK_ID)));

                    listadhoc.add(selectiondetails1);
                }
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        System.out.println("complaintsize:"+listadhoc.size());
        return listadhoc;
    }

    public ArrayList<HashMap<String, String>> getmultipartycheck(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + MULTIWORK + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                selectiondetails.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.DOCU)));
                selectiondetails.put("appointment_id", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));
                selectiondetails.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.STATUS)));
                selectiondetails.put("reason", c.getString(c.getColumnIndex(DBManager.TableInfo.reason)));

                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getactualtime(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from  " + DBManager.TableInfo.ACTUAL_TIME + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";

            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                selectiondetails.put("actual_time", c.getString(c.getColumnIndex(DBManager.TableInfo.actual_time)));
                selectiondetails.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails.put("call_time", c.getString(c.getColumnIndex(DBManager.TableInfo.call_time)));
                selectiondetails.put("gen_distance", c.getString(c.getColumnIndex(gen_distance)));
                selectiondetails.put("appointment_id", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));

                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }

    public ArrayList<HashMap<String, String>> getposttaskdetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listwitness = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.POST_TASK + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                selectiondetails1.put("is_done", c.getString(c.getColumnIndex(DBManager.TableInfo.IS_DONE)));
                selectiondetails1.put("reason", c.getString(c.getColumnIndex(DBManager.TableInfo.REASON)));
                selectiondetails1.put("task_id", c.getString(c.getColumnIndex(DBManager.TableInfo.TASK_ID)));
                selectiondetails1.put("status", c.getString(c.getColumnIndex(DBManager.TableInfo.status1)));
                selectiondetails1.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                listwitness.add(selectiondetails1);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();

        return listwitness;
    }


    public ArrayList<HashMap<String, String>> calldetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listwitness = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.CALL + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                selectiondetails1.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails1.put("appid", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));
                selectiondetails1.put("gen_distance", c.getString(c.getColumnIndex(gen_distance)));
                selectiondetails1.put("actual_time", c.getString(c.getColumnIndex(DBManager.TableInfo.actual_time)));
                selectiondetails1.put("call_time", c.getString(c.getColumnIndex(DBManager.TableInfo.call_time)));
                selectiondetails1.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                listwitness.add(selectiondetails1);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();

        return listwitness;
    }

    public ArrayList<HashMap<String, String>> getAppointmentslot(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();

        try {

            String query = "SELECT * from " + DBManager.TableInfo.APPOINTMENTSLOT + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("slot_id", c.getString(c.getColumnIndex(DBManager.TableInfo.slotid1)));
                selectiondetails.put("start_time", c.getString(c.getColumnIndex(DBManager.TableInfo.starttime1)));
                selectiondetails.put("End_time", c.getString(c.getColumnIndex(DBManager.TableInfo.endtime1)));
                selectiondetails.put("Available", c.getString(c.getColumnIndex(DBManager.TableInfo.available1)));
                selectiondetails.put("Block", c.getString(c.getColumnIndex(DBManager.TableInfo.block1)));
                selectiondetails.put("Discount", c.getString(c.getColumnIndex(DBManager.TableInfo.discount1)));

                listUsername.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }


    public ArrayList<HashMap<String, String>> getrescheduledetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listreschedule = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.RESCHEDULE + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String curdate1 = df2.format(c1.getTime());

            while (c.moveToNext()) {

                HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                selectiondetails1.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                selectiondetails1.put("docid", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails1.put("appointment_id", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));
                selectiondetails1.put("rescheduledate", c.getString(c.getColumnIndex(DBManager.TableInfo.RESCHEDULEDATE)));
                selectiondetails1.put("rescheduletime", c.getString(c.getColumnIndex(DBManager.TableInfo.RESCHEDULETIME)));
                selectiondetails1.put("reason1", c.getString(c.getColumnIndex(DBManager.TableInfo.RES_REASON)));
                listreschedule.add(selectiondetails1);
            }


            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();

        return listreschedule;
    }


    public ArrayList<HashMap<String, String>> getweekendoffdetails(DBOperation db) {
        ArrayList<HashMap<String, String>> listreschedule = new ArrayList<HashMap<String, String>>();

        try {

            String query = "select * from " + DBManager.TableInfo.WEEKEND + " where " + DBManager.TableInfo.KEY_LOGIN_USER + " = '" + GenericMethods.email + "'";
            SQLiteDatabase sqlite1 = db.getWritableDatabase();
            Cursor c = sqlite1.rawQuery(query, null);
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String curdate1 = df2.format(c1.getTime());

            while (c.moveToNext()) {

                HashMap<String, String> selectiondetails1 = new HashMap<String, String>();
                selectiondetails1.put("exec_email", c.getString(c.getColumnIndex(DBManager.TableInfo.KEY_LOGIN_USER)));
                selectiondetails1.put("from_date", c.getString(c.getColumnIndex(DBManager.TableInfo.from_date)));
                selectiondetails1.put("to_date", c.getString(c.getColumnIndex(DBManager.TableInfo.to_date)));
                selectiondetails1.put("status_off", c.getString(c.getColumnIndex(DBManager.TableInfo.statusoff)));
                selectiondetails1.put("reason_off", c.getString(c.getColumnIndex(DBManager.TableInfo.reasonoff)));

                listreschedule.add(selectiondetails1);
            }


            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();

        return listreschedule;
    }


    public void UpdateAppointmentStatus(DBOperation db, String docid) {

        String selection = DBManager.TableInfo.DocumentId + " = ?";

        String a[] = {docid};

        SQLiteDatabase sqlite = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBManager.TableInfo.SYNCSTATUS, "SYNC");

        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME2, cv, selection, a);

        db.close();
    }

    public ArrayList<HashMap<String, String>> getcomment(DBOperation db) {
        ArrayList<HashMap<String, String>> listComment = new ArrayList<HashMap<String, String>>();

        try {

            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME3 + " where " + DBManager.TableInfo.SYNCSTATUS + "='ASYNC'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("DocId", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_env)));
                selectiondetails.put("CustomerComment", c.getString(c.getColumnIndex(DBManager.TableInfo.Comments)));
                selectiondetails.put("CommentUser", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_user)));
                selectiondetails.put("Commentowner", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_owner)));
                selectiondetails.put("ComentID", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_id)));
                selectiondetails.put("CommentIdDone", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_is_done)));
                selectiondetails.put("CommentDate", c.getString(c.getColumnIndex(DBManager.TableInfo.Comment_date)));
                selectiondetails.put("ReminderDate", c.getString(c.getColumnIndex(DBManager.TableInfo.REMINDER_DATE)));
                listComment.add(selectiondetails);
            }

            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listComment;

    }


    public boolean checkUserId(DBOperation db, String userid) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String count = "SELECT count(*) FROM " + DBManager.TableInfo.TABLE_NAME5 + " where userid='" + userid + "'";
        Cursor mcursor = sqlite.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return true;
        } else
            return false;

    }

    public void deleteSyncComment(DBOperation db) {
        try {
            SQLiteDatabase sqlite = db.getWritableDatabase();

            sqlite.delete(DBManager.TableInfo.TABLE_NAME3, "syncstatus=?", new String[]{"SYNC"});
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCommentID(DBOperation db, String commentID) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String count = "SELECT count(*) FROM " + DBManager.TableInfo.TABLE_NAME3 + " where Cid='" + commentID + "'";
        Cursor mcursor = sqlite.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return true;
        } else
            return false;
    }

    public void UpdateCommentStatus(DBOperation db, String strCommentID) {
        String selection = DBManager.TableInfo.Comment_id + " = ?";
        String a[] = {strCommentID};

        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.SYNCSTATUS, "SYNC");
        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME3, cv, selection, a);

        db.close();
    }

    public String getUserId(DBOperation db, String userName) {
        String strUserId = "";
        try {

            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME5 + " where " + DBManager.TableInfo.Email + "='" + userName + "'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);


            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                strUserId = c.getString(c.getColumnIndex(DBManager.TableInfo.User_id));
            }
            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return strUserId;

    }

    public ArrayList<HashMap<String, String>> getAcvrReport(DBOperation db) {
        ArrayList<HashMap<String, String>> listUsername = new ArrayList<HashMap<String, String>>();
        try {
            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME + " where " + DBManager.TableInfo.SYNCSTATUSREPORT + "='ASYNC'";
            SQLiteDatabase sqlite = db.getWritableDatabase();
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put("DocId", c.getString(c.getColumnIndex(DBManager.TableInfo.DocumentId)));
                selectiondetails.put("Distance", c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE)));
                selectiondetails.put("Amount", c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT)));
                selectiondetails.put("TransportType", c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE)));
                selectiondetails.put("Appointmentid", c.getString(c.getColumnIndex(DBManager.TableInfo.AppointmentId)));
                listUsername.add(selectiondetails);
            }
            closeCursor(c);

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listUsername;
    }


    public void UpdateacvrStatus(DBOperation db, String apointmentid) {

        String selection = DBManager.TableInfo.AppointmentId + " = ?";

        String a[] = {apointmentid};

        SQLiteDatabase sqlite = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBManager.TableInfo.SYNCSTATUSREPORT, "SYNC");

        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME, cv, selection, a);

        db.close();
    }

    public void Updateacvr(DBOperation db, String appid, String docid, String startdate, String startnewtime1,
                           String startnewtime2, String appaddress, String exeid, String appfor, String distance, String amount, String transporttype, String contactperson, String land, String attendeesstatus, String poststatus) {
        // TODO Auto-generated method stub

        String selection = DBManager.TableInfo.AppointmentId + " = ? ";
        String a[] = {appid};
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBManager.TableInfo.DocumentId, docid);
        cv.put(DBManager.TableInfo.StartDate, startdate);
        cv.put(DBManager.TableInfo.StartTime1, startnewtime1);
        cv.put(DBManager.TableInfo.StartTime2, startnewtime2);
        cv.put(DBManager.TableInfo.AppointmentAddress, appaddress);
        cv.put(DBManager.TableInfo.Executioner_id, exeid);
        cv.put(DBManager.TableInfo.AppFor, appfor);
        cv.put(DBManager.TableInfo.AppointmentId, appid);
        cv.put(DBManager.TableInfo.DISTANCE, distance);
        cv.put(DBManager.TableInfo.AMOUNT, amount);
        cv.put(DBManager.TableInfo.TRANSPORTTYPE, transporttype);
        cv.put(DBManager.TableInfo.contactperson, contactperson);
        cv.put(DBManager.TableInfo.landmark, land);
        cv.put(DBManager.TableInfo.att_status, attendeesstatus);
        cv.put(DBManager.TableInfo.post_status, poststatus);
        long result = sqlite.update(DBManager.TableInfo.TABLE_NAME, cv, selection, a);

        db.close();
    }


    public boolean DocumentID(DBOperation db, String docid) {

        SQLiteDatabase sqlite = db.getWritableDatabase();
        String count = "SELECT count(*) FROM " + DBManager.TableInfo.TABLE_NAME2 + " where " + DBManager.TableInfo.DocumentId + "='" + docid + "'";
        Cursor mcursor = sqlite.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return true;
        } else
            return false;
    }

    public boolean LoginID(DBOperation db, String umailid) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String count = "SELECT count(*) FROM " + DBManager.TableInfo.TABLE_NAME1 + " where " + DBManager.TableInfo.UserEmail + "='" + umailid + "'";
        Cursor mcursor = sqlite.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return true;
        } else
            return false;


    }

    public ArrayList<HashMap<String, String>> getAllList(DBOperation db) {

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartDate;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date, date1;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));
                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));
                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));
                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                // System.out.println("con_completed:"+contactperson);
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (biocomp.equals("1") && biocom1.equals("1") && regfromcomp.equals("1") && witness.equals("1")) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }

                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();

        return getAllList;
    }

    public ArrayList<HashMap<String, String>> getAllList1(DBOperation db) {
        String rkey1 = "";

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartDate;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date, date1;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));
                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));
                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));
                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (rkey.substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + rkey.substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {

                    }
                } else if (rkey.substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = rkey.substring(0, 3).toLowerCase() + rkey.substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {

                    }
                }
//
                if ((rkey.toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || oname.toLowerCase().contains(GenericMethods.rkeyfn) || tname.toLowerCase().contains(GenericMethods.rkeyfn)) && (biocomp.equals("1") && biocom1.equals("1") && regfromcomp.equals("1") && witness.equals("1"))) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");

                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();
        return getAllList;
    }

    public ArrayList<HashMap<String, String>> getCommentlist(DBOperation db) {
        ArrayList<HashMap<String, String>> getAllCommentList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + DBManager.TableInfo.TABLE_NAME3;
        Cursor cursor = sqlite.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                HashMap<String, String> getAllCommentListMap = new HashMap<String, String>();
                String envid = cursor.getString(cursor.getColumnIndex("Env"));
                String cid = cursor.getString(cursor.getColumnIndex("Cid"));
                String cisdone = cursor.getString(cursor.getColumnIndex("Cisdone"));
                String cowner = cursor.getString(cursor.getColumnIndex("Cowner"));
                String cuser = cursor.getString(cursor.getColumnIndex("Cuser"));
                String ccomments = cursor.getString(cursor.getColumnIndex("Ccomments"));
                String cdate = cursor.getString(cursor.getColumnIndex("Cdate"));
                String commentstatus = cursor.getString(cursor.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                getAllCommentListMap.put("Did", envid);
                getAllCommentListMap.put("Cid", cid);
                getAllCommentListMap.put("Cisdone", cisdone);
                getAllCommentListMap.put("Cowner", cowner);
                getAllCommentListMap.put("Cuser", cuser);
                getAllCommentListMap.put("Ccomments", ccomments);
                getAllCommentListMap.put("Cdate", cdate);
                getAllCommentListMap.put("CommentStatus", commentstatus);
                getAllCommentList.add(getAllCommentListMap);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return getAllCommentList;
    }


    public ArrayList<HashMap<String, String>> getdataList(DBOperation db) {
        ArrayList<HashMap<String, String>> getDataList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME5;
        Cursor cursor1 = sqlite.rawQuery(selectQuery, null);

        if (cursor1.moveToFirst()) {
            int count = cursor1.getCount();


            cursor1.moveToFirst();
            do {
                HashMap<String, String> getdataListMap = new HashMap<String, String>();
                String userid = cursor1.getString(cursor1.getColumnIndex(DBManager.TableInfo.User_id));
                String username = cursor1.getString(cursor1.getColumnIndex(DBManager.TableInfo.User_name));
                String email = cursor1.getString(cursor1.getColumnIndex(DBManager.TableInfo.Email));
                String role = cursor1.getString(cursor1.getColumnIndex(DBManager.TableInfo.Role));
                getdataListMap.put("UserId", userid);
                getdataListMap.put("username", username);
                getdataListMap.put("email", email);
                getdataListMap.put("role", role);

                getDataList.add(getdataListMap);
            } while (cursor1.moveToNext());


        }
        db.close();
        return getDataList;
    }


    public ArrayList<HashMap<String, String>> getAllOlderist(DBOperation db) {

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date = null, date1 = null;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));

                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));

                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));

                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));


                String post_status = c.getString(c.getColumnIndex("post_status"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (date.before(date1) && (!biocomp.equals("1") || !biocom1.equals("1") || !regfromcomp.equals("1")
                        || !witness.equals("1"))) {

                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);


                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");

                    } else {
                        getAllListMap.put("landmark", landmark);

                    }

                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }

                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();
        return getAllList;
    }


    public ArrayList<HashMap<String, String>> getAllOlderist1(DBOperation db) {

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        String rkey1 = "";
        Date date = null, date1 = null;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));

                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));

                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));

                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (rkey.substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + rkey.substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                    }
                } else if (rkey.substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = rkey.substring(0, 3).toLowerCase() + rkey.substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                        //System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }


                if ((rkey.toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || oname.toLowerCase().contains(GenericMethods.rkeyfn) || tname.toLowerCase().contains(GenericMethods.rkeyfn)) && (date.before(date1) && (!biocomp.equals("1") || !biocom1.equals("1") || !regfromcomp.equals("1")
                        || !witness.equals("1")))) {

                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);

                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();

        return getAllList;
    }


    public ArrayList<HashMap<String, String>> getAllNewList(DBOperation db) {

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date = null, date1 = null;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));

                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));

                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));

                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                //System.out.println("con_New:"+contactperson);

                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (date.after(date1)) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();
        return getAllList;
    }

    public ArrayList<HashMap<String, String>> getAllNewList1(DBOperation db) {
        String rkey1 = "";

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date = null, date1 = null;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));

                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));

                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));

                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (rkey.substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + rkey.substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                    }
                } else if (rkey.substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = rkey.substring(0, 3).toLowerCase() + rkey.substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                    }
                }

                if ((rkey.toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || oname.toLowerCase().contains(GenericMethods.rkeyfn) || tname.toLowerCase().contains(GenericMethods.rkeyfn)) && (date.after(date1))) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();
        return getAllList;
    }

    public ArrayList<HashMap<String, String>> getAllTodayList(DBOperation db) {

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date, date1;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));
                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));
                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));
                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                //System.out.println("docid:"+docid+"appid:"+appid+"contact_today:"+contactperson);
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (sdate.equals(curdate1)) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");

                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                    //System.out.println("contacttoday:"+contact_person);
                }


            } while (c.moveToNext());

        }
        db.close();
        //System.out.println("getalltodaylistsize:"+getAllList.size());
        return getAllList;
    }

    public ArrayList<HashMap<String, String>> getAllTodayList1(DBOperation db) {
        String rkey1 = "";

        ArrayList<HashMap<String, String>> getAllList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase sqlite = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME + " t1, " + DBManager.TableInfo.TABLE_NAME2 + " t2 WHERE "
                + "t1." + DBManager.TableInfo.DocumentId + " = " + "t2." + DBManager.TableInfo.DocumentId + " AND t1."
                + DBManager.TableInfo.Executioner_id + "='" + Login.umailid + "'" + " ORDER BY " + "t1." + DBManager.TableInfo.StartTime1;

        Cursor c = sqlite.rawQuery(selectQuery, null);
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String curdate1 = df2.format(c1.getTime());
        Date date, date1;
        if (c.moveToFirst()) {

            c.moveToFirst();
            do {
                HashMap<String, String> getAllListMap = new HashMap<String, String>();
                String rtoken = c.getString(c.getColumnIndex("Rtoken"));
                String rkey = c.getString(c.getColumnIndex("Rkey"));
                String name = c.getString(c.getColumnIndex("Uname"));
                String mail = c.getString(c.getColumnIndex("Umail"));
                String contact = c.getString(c.getColumnIndex("Ucontact"));
                String padd = c.getString(c.getColumnIndex("Paddress"));
                String oname = c.getString(c.getColumnIndex("Oname"));
                String ocontact = c.getString(c.getColumnIndex("Ocontact"));
                String omail = c.getString(c.getColumnIndex("Omail"));
                String oadd = c.getString(c.getColumnIndex("Oaddress"));
                String tname = c.getString(c.getColumnIndex("Tname"));
                String tcontact = c.getString(c.getColumnIndex("Tcontact"));
                String tmail = c.getString(c.getColumnIndex("Tmail"));
                String tadd = c.getString(c.getColumnIndex("Taddress"));
                String fmail = c.getString(c.getColumnIndex("uemail"));
                String tstatus = c.getString(c.getColumnIndex("status"));
                String syncstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUS));
                String distance = c.getString(c.getColumnIndex(DBManager.TableInfo.DISTANCE));
                String amount = c.getString(c.getColumnIndex(DBManager.TableInfo.AMOUNT));
                String trasporttype = c.getString(c.getColumnIndex(DBManager.TableInfo.TRANSPORTTYPE));
                String apointmentfor = c.getString(c.getColumnIndex(DBManager.TableInfo.AppFor));
                String acvrreportstatus = c.getString(c.getColumnIndex(DBManager.TableInfo.SYNCSTATUSREPORT));
                String strlati = c.getString(c.getColumnIndex("latitude"));
                String strlongi = c.getString(c.getColumnIndex("longitude"));
                String appdate = c.getString(c.getColumnIndex("Appdate"));
                String biocomp = c.getString(c.getColumnIndex("Biocomp"));
                String appdate1 = c.getString(c.getColumnIndex("Appdate1"));
                String biocom1 = c.getString(c.getColumnIndex("Biocomp1"));
                String regfromcomp = c.getString(c.getColumnIndex("reg_from_app"));
                String witness = c.getString(c.getColumnIndex("witness"));
                String shipadd = c.getString(c.getColumnIndex("ship_add"));
                String shipdiffadd = c.getString(c.getColumnIndex("ship_diff_add"));
                String sdate = c.getString(c.getColumnIndex("Sdate"));
                String stime1 = c.getString(c.getColumnIndex("Stime1"));
                String stime2 = c.getString(c.getColumnIndex("Stime2"));
                String docid = c.getString(c.getColumnIndex("Did"));
                String appadress = c.getString(c.getColumnIndex("Aaddress"));
                String appid = c.getString(c.getColumnIndex("Aid"));
                String exid = c.getString(c.getColumnIndex("Exid"));
                String appfor = c.getString(c.getColumnIndex("appfor"));
                String apptype = c.getString(c.getColumnIndex("app_type"));
                String landmark = c.getString(c.getColumnIndex("landmark"));
                String contactperson = c.getString(c.getColumnIndex(DBManager.TableInfo.contactperson));
                String post_status = c.getString(c.getColumnIndex("post_status"));
                String bio_status = c.getString(c.getColumnIndex(DBManager.TableInfo.att_status));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(sdate);
                    date1 = dateFormat.parse(curdate1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (rkey.substring(0, 1).toLowerCase().equals("a")) {


                    rkey1 = "a" + rkey.substring(2);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                        //System.out.println("rkeyif:"+rkey1+" "+GenericMethods.rkeyfn);
                    }
                } else if (rkey.substring(0, 1).toLowerCase().equals("n")) {

                    rkey1 = rkey.substring(0, 3).toLowerCase() + rkey.substring(4);
                    if (rkey1.equals(GenericMethods.rkeyfn)) {
                        //System.out.println("rkeyelse:" + rkey1 + " " + GenericMethods.rkeyfn);
                    }
                }
                if ((rkey.toLowerCase().equals(GenericMethods.rkeyfn) || rkey1.contains(GenericMethods.rkeyfn) || oname.toLowerCase().contains(GenericMethods.rkeyfn) || tname.toLowerCase().contains(GenericMethods.rkeyfn)) && (sdate.equals(curdate1) && (!biocomp.equals("1") || !biocom1.equals("1") || !regfromcomp.equals("1")
                        || !witness.equals("1")))) {
                    getAllListMap.put("Rtoken", rtoken);
                    getAllListMap.put("rkey", rkey);
                    getAllListMap.put("name", name);
                    getAllListMap.put("mail", mail);
                    getAllListMap.put("contact", contact);
                    getAllListMap.put("padd", padd);
                    getAllListMap.put("oname", oname);
                    getAllListMap.put("ocontact", ocontact);
                    getAllListMap.put("omail", omail);
                    getAllListMap.put("oadd", oadd);
                    getAllListMap.put("tname", tname);
                    getAllListMap.put("tcontact", tcontact);
                    getAllListMap.put("tmail", tmail);
                    getAllListMap.put("tadd", tadd);
                    getAllListMap.put("fmail", fmail);
                    getAllListMap.put("tstatus", tstatus);
                    getAllListMap.put("biocomp", biocomp);
                    getAllListMap.put("biocom1", biocom1);
                    getAllListMap.put("appdate", appdate);
                    getAllListMap.put("appdate1", appdate1);
                    getAllListMap.put("regfromcomp", regfromcomp);
                    getAllListMap.put("witness", witness);
                    getAllListMap.put("shipadd", shipadd);
                    getAllListMap.put("shipdiffadd", shipdiffadd);
                    getAllListMap.put("sdate", sdate);
                    getAllListMap.put("stime1", stime1);
                    getAllListMap.put("stime2", stime2);
                    getAllListMap.put("strlati", strlati);
                    getAllListMap.put("strlongi", strlongi);
                    getAllListMap.put("docid", docid);
                    getAllListMap.put("appadress", appadress);
                    getAllListMap.put("appid", appid);
                    getAllListMap.put("appfor", appfor);
                    getAllListMap.put("exid", exid);
                    getAllListMap.put("syncstatus", syncstatus);
                    getAllListMap.put("distance", distance);
                    getAllListMap.put("amount", amount);
                    getAllListMap.put("trasporttype", trasporttype);
                    getAllListMap.put("apointmentfor", apointmentfor);
                    getAllListMap.put("acvrreportstatus", acvrreportstatus);
                    getAllListMap.put("apptype", apptype);
                    getAllListMap.put("post_status", post_status);
                    getAllListMap.put("att_status", bio_status);
                    //getAllListMap.put("biocontent",biocontent);
                    if (landmark.equals("null")) {

                        getAllListMap.put("landmark", "No details");
                    } else {
                        getAllListMap.put("landmark", landmark);
                    }
                    String[] contact_array = contactperson.split(",");
                    if (contactperson.equals("null")) {
                        getAllListMap.put("contact_person", "No details");
                        getAllListMap.put("contact_name", "No details");
                        getAllListMap.put("contact_email", "No details");
                        getAllListMap.put("contact_no", "No details");
                    } else if (contact_array.length > 1) {
                        getAllListMap.put("contact_person", "Details");
                        getAllListMap.put("contact_name", contact_array[0]);
                        getAllListMap.put("contact_email", contact_array[1]);
                        getAllListMap.put("contact_no", contact_array[2]);
                    }
                    getAllList.add(getAllListMap);
                }


            } while (c.moveToNext());

        }
        db.close();
        return getAllList;
    }

    public List<String> getReportkey() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + DBManager.TableInfo.TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("Rkey")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public ArrayList<HashMap<String, String>> getOwnerType(DBOperation db, String owner) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ArrayList<HashMap<String, String>> listOfReference = new ArrayList<HashMap<String, String>>();
        try {
            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME5 + " where " + DBManager.TableInfo.Email + "='" + owner + "'";
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {
                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put(DBManager.TableInfo.User_name, c.getString(c.getColumnIndex(DBManager.TableInfo.User_name)));
                selectiondetails.put(DBManager.TableInfo.Role, c.getString(c.getColumnIndex(DBManager.TableInfo.Role)));
                selectiondetails.put(DBManager.TableInfo.User_id, c.getString(c.getColumnIndex(DBManager.TableInfo.User_id)));
                listOfReference.add(selectiondetails);
            }
            c.close();

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listOfReference;
    }

    public ArrayList<HashMap<String, String>> getExecutionerType(DBOperation db, String username) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ArrayList<HashMap<String, String>> listOfReference = new ArrayList<HashMap<String, String>>();
        try {
            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME5 + " where " + DBManager.TableInfo.Email + "='" + username + "'";
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {

                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put(DBManager.TableInfo.User_name, c.getString(c.getColumnIndex(DBManager.TableInfo.User_name)));
                selectiondetails.put(DBManager.TableInfo.Role, c.getString(c.getColumnIndex(DBManager.TableInfo.Role)));
                selectiondetails.put(DBManager.TableInfo.User_id, c.getString(c.getColumnIndex(DBManager.TableInfo.User_id)));
                listOfReference.add(selectiondetails);
            }
            c.close();

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }
        db.close();
        return listOfReference;
    }


    public ArrayList<HashMap<String, String>> getDocumentUser(DBOperation db, String strDocId) {
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ArrayList<HashMap<String, String>> listOfReference = new ArrayList<HashMap<String, String>>();
        try {
            String query = "SELECT * from " + DBManager.TableInfo.TABLE_NAME2 + " where " + DBManager.TableInfo.ReportKey + "='" + strDocId + "'";
            Cursor c = sqlite.rawQuery(query, null);

            while (c.moveToNext()) {

                HashMap<String, String> selectiondetails = new HashMap<String, String>();
                selectiondetails.put(DBManager.TableInfo.OwnerEmail, c.getString(c.getColumnIndex(DBManager.TableInfo.OwnerEmail)));
                listOfReference.add(selectiondetails);
            }
            c.close();

        } catch (Exception e) {
            // TODO: handle exception.
            e.printStackTrace();
        }

        db.close();
        return listOfReference;
    }
}