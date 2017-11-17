package anulom.executioner5.com3.anulom.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import anulom.executioner5.com3.anulom.GenericMethods;

import anulom.executioner5.com3.anulom.services.AppointmentstatusPost;
import anulom.executioner5.com3.anulom.services.LocationTrackingService;
import anulom.executioner5.com3.anulom.services.Posttask;
import anulom.executioner5.com3.anulom.services.SendCommentService;
import anulom.executioner5.com3.anulom.services.SendmultiPartyReport;
import anulom.executioner5.com3.anulom.services.SendPaymentService;
import anulom.executioner5.com3.anulom.services.SendReportService;
import anulom.executioner5.com3.anulom.services.StatusReportService;
import anulom.executioner5.com3.anulom.services.call;
import anulom.executioner5.com3.anulom.services.fetchactualtime;
import anulom.executioner5.com3.anulom.services.not_applicablepost;
import anulom.executioner5.com3.anulom.services.reassignpost;
import anulom.executioner5.com3.anulom.services.rescheduledetails;


/**
 * Created by Admin on 6/29/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            Intent LocationTrackingService = new Intent(context, LocationTrackingService.class);
            LocationTrackingService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(LocationTrackingService);
        }

        if (intent.getExtras() != null) {

            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(
                    ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                GenericMethods.connection = "true";
                SharedPreferences usermail = PreferenceManager.getDefaultSharedPreferences(context);
                String username2 = usermail.getString("username", "");


                if (!username2.isEmpty()) {

                    Intent LocationTrackingService1 = new Intent(context,
                            LocationTrackingService.class);
                    LocationTrackingService1
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(LocationTrackingService1);


                    Intent SendPaymentService1 = new Intent(context, SendPaymentService.class);
                    SendPaymentService1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(SendPaymentService1);

                    Intent SendPartyService1 = new Intent(context, SendmultiPartyReport.class);
                    SendPartyService1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(SendPartyService1);


                    Intent SendCommentService1 = new Intent(context, SendCommentService.class);
                    SendCommentService1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(SendCommentService1);


                    Intent SendReportService1 = new Intent(context, SendReportService.class);
                    SendReportService1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(SendReportService1);


                    Intent StatusReportService1 = new Intent(context, StatusReportService.class);
                    StatusReportService1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(StatusReportService1);

                    Intent callservice = new Intent(context, call.class);
                    callservice.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(callservice);

                    Intent appstatus = new Intent(context, AppointmentstatusPost.class);
                    appstatus.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(appstatus);

                    Intent actualtime = new Intent(context, fetchactualtime.class);
                    actualtime.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(actualtime);


                    Intent task = new Intent(context, Posttask.class);
                    task.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(task);

                    Intent notapplicable = new Intent(context, not_applicablepost.class);
                    notapplicable.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(notapplicable);

                    Intent reassign = new Intent(context, reassignpost.class);
                    reassign.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(reassign);

                    Intent resche = new Intent(context, rescheduledetails.class);
                    resche.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(resche);

//                    Intent weekend1 = new Intent(context, weekendoffdetails.class);
//                    weekend1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startService(weekend1);


                }
            }
        }
    }
}
