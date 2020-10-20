package com.bychinin.tvseriescalendar.data.broadcastreceiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.data.api.tmdb

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val textView = (context as Activity).findViewById<View>(R.id.tv_network) as TextView
        val name = intent.getStringExtra(tmdb.NETWORK_NETWORK_ACTION_NAME)
        val title : String = context.resources.getString(R.string.searching_title)
        textView.text = "$title ${name}"
    }

}
