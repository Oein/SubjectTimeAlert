package ind.oein.subjecttimechecker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

class BootReceiver : BroadcastReceiver() {
    private fun startAlarm(calendar: Calendar, context: Context, title: String, desc: String, index: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        intent.putExtra("title", title);
        intent.putExtra("desc", desc);
        val pendingIntent = PendingIntent.getBroadcast(context, index, intent, PendingIntent.FLAG_IMMUTABLE + PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    override fun onReceive(pContext: Context, intent: Intent) {
        val cal = Calendar.getInstance();
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if(dayOfWeek < 0) return;
        if(dayOfWeek > 4) return;
        val subjects = Constants.subjects[dayOfWeek];
        for (i in 0 until subjects.size) {
            val subject = subjects[i];
            val tim = Constants.times[i];

            startAlarm(tim, pContext, subject.title, subject.desc,i + 1)
        }
        startAlarm(Calendar.getInstance(), pContext, "교과시간 알림기","가 실행중 입니다.", 0)
    }
}