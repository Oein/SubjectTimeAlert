package ind.oein.subjecttimechecker

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class  NotificationUtils(base: Context) : ContextWrapper(base) {

    val MYCHANNEL_ID = "App Alert Notification ID"
    val MYCHANNEL_NAME = "App Alert Notification"

    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    // Create channel for Android version 26+
    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(MYCHANNEL_ID, MYCHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    // Get Manager
    fun getManager() : NotificationManager {
        if (manager == null) manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(title: String, desc: String): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, (PendingIntent.FLAG_IMMUTABLE))
        return NotificationCompat.Builder(applicationContext, MYCHANNEL_ID)
            .setContentTitle(title)
            .setContentText(desc)
            .setColor(Color.YELLOW)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
            .setTimeoutAfter(1000 * 60 * 40)
            .setSilent(true)
    }
}

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title")
        var desc = intent.getStringExtra("desc")

        print("ALARM HANDLER")
        print(title)
        print(desc)

        if(title == null) return;
        if(desc == null) return;

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val notificationUtils = NotificationUtils(context)
        val notification = notificationUtils.getNotificationBuilder(title, desc).build()
        notificationUtils.getManager().notify(150, notification)
    }
}