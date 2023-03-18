package ind.oein.subjecttimechecker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    fun startAlarm(calendar: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, Receiver::class.java)
        intent.putExtra("title", "Hi");
        intent.putExtra("desc", "Hello");
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE + PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    /**
     * On Click Button for Cancel the previously set alarm
     */

    fun setAlarm() {
        Toast.makeText(this, "설정",Toast.LENGTH_SHORT).show()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = calendar.timeInMillis + 3000
        startAlarm(calendar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button__).setOnClickListener {
            setAlarm()
        }
    }
}