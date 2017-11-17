package anulom.executioner5.com3.anulom;

/**
 * Created by shriswissfed on 25/4/17.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    int i = 10;
    int icon_color = 0xFF4500, not_idd;
    String rkey, id, owner, tenant, w1, w2, news, not_id, from, content;
    Context Context;
    String report;
    long when = System.currentTimeMillis();

    @Override
    public void onReceive(Context context, Intent intent) {
        // Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

//        doc_id1= TodayDetails.ddoc;
        rkey = intent.getStringExtra("rkey");
        id = intent.getStringExtra("id");
        owner = intent.getStringExtra("owner");
        tenant = intent.getStringExtra("tenant");
        w1 = intent.getStringExtra("w1");
        w2 = intent.getStringExtra("w2");
        news = intent.getStringExtra("news");
        not_id = intent.getStringExtra("not_id");
        not_idd = Integer.valueOf(not_id);
        from = intent.getStringExtra("from");
        content = intent.getStringExtra("content");
//        System.out.println("Docid"+doc_id1);
        Intent notificationIntent = new Intent(context, StatusInfo.class);

        notificationIntent.putExtra("ReportKey", rkey);
        notificationIntent.putExtra("DocumentId", id);
        notificationIntent.putExtra("BiometricComp", owner);
        notificationIntent.putExtra("BiometricComp1", tenant);
        notificationIntent.putExtra("Reg_From_Comp", w1);
        notificationIntent.putExtra("Witness", w2);
        notificationIntent.putExtra("from", from);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(StatusInfo.class);
        stackBuilder.addNextIntent(notificationIntent);


        PendingIntent pendingIntent = stackBuilder.getPendingIntent((int) when, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.Linesound);//Here is FILE_NAME is the name of file that you want to play

        Notification notification = builder.setContentTitle("Executioner App" + " " + news + " " + rkey)
                .setContentText(content)
                .setAutoCancel(true)
                .setOngoing(false)
                .setColor(icon_color)
                .setPriority(2)
                .setSound(Uri.parse("android.resource://"
                        + context.getPackageName() + "/"
                        + R.raw.linesound))
                .setWhen(when)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                //.setSound(uri)
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) when, notification);
        System.out.println("Notified");
    }
}