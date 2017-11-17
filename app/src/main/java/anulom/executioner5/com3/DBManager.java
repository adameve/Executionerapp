package anulom.executioner5.com3.anulom.database;

import android.provider.BaseColumns;

public class DBManager {

    public DBManager() {

    }

    public static abstract class TableInfo implements BaseColumns {

        public static String DATABASE_NAME = "TaskDetails";
        public static String TABLE_NAME = "Appointment";
        public static String TABLE_NAME1 = "user";
        public static String TABLE_NAME2 = "Document2";
        public static String TABLE_DOCUMENT = "Document1";
        public static String TABLE_NAME3 = "Comments";
        public static String TABLE_NAME4 = "Add_Payments";
        public static String TABLE_NAME5 = "usertable1";
        public static String RESCHEDULE = "reschedule";
        public static String MULTIWORK = "multiwork";
        public static String PAYMENT = "Pay1";
        public static String UPDATEPAYMENT1 = "Updatepayment3";
        public static String POSTDOC = "postdoc";
        public static String TABLE_TASK = "task";
        public static String ATTENDEES = "attendees";
        public static String POST_TASK = "posttask";
        public static String REASSIGN = "reassign";
        public static String NOT_APPLICABLE = "not_applicable";
        public static String ACTUAL_TIME = "actual_time";
        public static String CALL = "call";
        public static String ATT_STATUS = "bio_status";
        public static String APPOINTMENTSLOT = "time_slot1";
        public static String APPOINTMENTBOOKING = "app_book";
        public static String UPDATEPARTY = "updateparty11";
        public static String PARTIES1 = "party1";
        public static String TABLE_REM_PAYMENT = "rem_payment1";
        public static String TABLE_DOCUMENT2 = "documentnew2";
        public static String WEEKEND = "weekend";
        public static final String TABLE_LOCATION_DETAILS = "LocationDetails";

        //USER
        public static String UserID = "uId";
        public static String UserName = "uname1";
        public static String UserEmail = "uemail";
        public static String UserPassword = "upwd";

        //document


        public static String ReportToken = "Rtoken";
        public static String ReportKey = "Rkey";
        public static String Uname = "Uname";
        public static String Umail = "Umail";
        public static String Ucontact = "Ucontact";
        public static String PropertyAddress = "Paddress";
        public static String OwnerName = "Oname";
        public static String OwnerContact = "Ocontact";
        public static String OwnerEmail = "Omail";
        public static String OwnerAddress = "Oaddress";
        public static String TenantName = "Tname";
        public static String TenantContact = "Tcontact";
        public static String TenantEmail = "Tmail";
        public static String TenantAddress = "Taddress";
        public static String LATITUDE = "latitude";
        public static String LONGITUDE = "longitude";
        public static String SYNCSTATUS = "syncstatus";
        public static String SYNCSTATUSREPORT = "acvrreportsyncstatus";
        public static String COMMENTSYNCSTATUS = "commentsyncstatus";
        public static String Status = "status";
        public static String BiometricComp = "Biocomp";
        public static String BiometricComp1 = "Biocomp1";
        public static String Appointment_Date = "Appdate";
        public static String Appointment_Date1 = "Appdate1";
        public static String APPTYPE = "app_type";
        public static String Reg_From_Comp = "reg_from_app";
        public static String Witness = "witness";
        public static String Ship_Address = "ship_add";
        public static String Ship_Diff_Address = "ship_diff_add";

        //Appointment
        public static String ID1 = "Id1";
        public static String StartDate = "Sdate";
        public static String StartTime1 = "Stime1";
        public static String StartTime2 = "Stime2";
        public static String AppointmentId = "Aid";
        public static String DocumentId = "Did";
        public static String AppointmentAddress = "Aaddress";
        public static String Executioner_id = "Exid";
        public static String AppFor = "appfor";
        public static String post_status = "post_status";
        //Comments
        public static String Comment_id = "Cid";
        public static String Comment_is_done = "Cisdone";
        public static String Comment_user = "Cuser";
        public static String Comment_owner = "Cowner";
        public static String Comment_date = "Cdate";
        public static String Comment_env = "Env";
        public static String Comments = "Ccomments";
        public static String REMINDER_DATE = "reminder_date";


        //UserRole
        public static String User_id = "userid";
        public static String User_name = "nameuser1";
        public static String Role = "role2";
        public static String Email = "email2";
        public static final String platformid = "platformid2";
        public static final String role_id = "roleid2";
        public static final String idu = "identity2";

        public static final String latitudeInput = "latitude";
        public static final String longitudeInput = "longitude";
        public static final String DATE = "date";
        public static final String LAT_LONG_ADDRESS = "LatLongAddress";

        public static final String DISTANCE = "distance";
        public static final String AMOUNT = "amount";
        public static final String APOINTMENTFOR = "appointment";
        public static final String TRANSPORTTYPE = "tranporttype";
        //Payment
        public static final String DEPOSIT = "deposit";
        public static final String ID = "id";
        public static String KEYID = "ID";
        public static String rep1 = "env";
        public static String doc1 = "document_id";
        public static String payamount = "amount";
        public static String KEY_LOGIN_USER = "Login_user";

        public static String DOCID = "document_id";
        public static String email1 = "email";
        public static String amt = "amt";
        public static String radiotype = "type";
        public static String comment1 = "comment";
        public static String date = "date";
        // new api
        public static final String DOCUMENT = "document_id";
        public static final String ATTENDANCE = "att_id";
        public static final String NAMENEW = "name";
        public static final String EMAILNEW = "email";
        public static final String PARTYTYPE = "party_type";
        public static final String POA = "poa";
        public static final String CONTACT = "contact_no";
        public static final String BIOMETRIC = "biometric";
        public static final String AMOUNT1 = "amount";
        public static final String ENV = "env";

        public static final String RESCHEDULEDATE = "rescheduledate";
        public static final String RESCHEDULETIME = "rescheduletime";
        public static final String RES_REASON = "reason1";

        public static final String DOCU = "document_id";
        public static final String ATTEND = "att_id";
        public static final String EMAIL = "email";
        public static final String PARTY = "party_type";
        public static final String BIO = "biometric";

        public static final String postdocument = "document";

        //for adhoc&witness&complaint

        public static final String WITID = "id1";
        public static final String COMMENT = "comment";
        public static final String IS_DONE = "is_done";
        public static final String DOC = "document_id";
        public static final String CREATE = "created_at";
        public static final String REMAINDER = "reminder_dt";
        public static final String EMOTION = "emotion";
        public static final String ASSIGN = "assign_by";
        public static final String TASK_ID = "task_id";
        public static final String TYPE = "type";
        public static final String TASK_NAME = "task_name";
        public static final String ADHOC = "Adhoc";
        public static final String WITNESS = "Witness";
        public static final String COMPLAINT = "Complaint";
        public static final String REASON = "reason";
        public static final String STATUS = "status1";

        //getappointmentslot
        public static final String slotid1 = "slot_id";
        public static final String starttime1 = "start_time";
        public static final String endtime1 = "end_time";
        public static final String available1 = "available";
        public static final String block1 = "block";
        public static final String discount1 = "discount";

        //postappointmentbooking

        public static final String request_no = "requestno";
        public static final String app_date = "appdate";
        public static final String timenew = "time";
        public static final String slotid = "slotid";
        public static final String division_id = "divisionid";
        public static final String region_id = "regionid";
        public static final String attendees = "attendees";
        public static final String address = "address";
        public static final String attendeesemail = "attendeesemail";
        public static final String attendeescontact = "attendeescontact";
        public static final String contactpersonemail = "contactemail";
        public static final String contactpersoncont = "contact";
        public static final String landmark = "landmark";
        public static final String contactperson = "person";
        public static final String free = "free";
        public static final String free_reason = "free_reason";
        public static final String city = "city";

        //attendees
        public static final String nameattend = "name";
        public static final String emailattend = "email";
        public static final String contactattend = "contact";
        public static final String appointattend = "appointment";

        //weekend/weekoff
        public static final String from_date = "from_date";
        public static final String to_date = "to_date";
        public static final String statusoff = "statusoff";
        public static final String reasonoff = "reasonoff";

        //reassign
        public static final String Prev_owner = "prev_owner";
        public static final String new_owner = "new_owner";
        public static final String reassign_reason = "reassign_reason";
        public static final String status1 = "status";

        //not applicable
        public static final String task_id1 = "taskidapplicable";
        public static final String not_app = "not_app";
        public static final String comment_applicable = "comment_appplicable";
        public static final String notapplicable_reason = "notaqpplicable_reason";

        //status

        public static final String reason = "reason";

        //actual_time

        public static final String actual_time = "time";
        //call

        public static final String gen_distance = "gen_distance";
        public static final String call_time = "call_time";

        //bio_content

        public static final String att_status = "att_status";

    }
}
//techanulom@789