package com.github.jing332.alistflutter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.jing332.alistflutter.utils.ToastUtils.toast

@RequiresApi(Build.VERSION_CODES.N)
class OpenListTileService : TileService() {

    private val serviceStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateTileState()
        }
    }

    override fun onTileAdded() {
        super.onTileAdded()
        updateTileState()
    }

    override fun onStartListening() {
        super.onStartListening()
        registerServiceStateReceiver()
        updateTileState()
    }

    override fun onStopListening() {
        super.onStopListening()
        unregisterServiceStateReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterServiceStateReceiver()
    }

    override fun onClick() {
        super.onClick()
        
        // 启停服务
        if (AListService.isRunning) {
            startService(Intent(this, AListService::class.java).apply {
                action = AListService.ACTION_SHUTDOWN
            })
            toast(getString(R.string.alist_shut_downing))
        } else {
            // 显式启动，确保在应用未运行时也能启动服务
            val serviceIntent = Intent(this, AListService::class.java)
            
            // 对于Android 8.0+，使用startForegroundService确保服务能启动
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
            toast(getString(R.string.alist_starting))
        }
        
    }

    private fun registerServiceStateReceiver() {
        val filter = IntentFilter(AListService.ACTION_STATUS_CHANGED)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(serviceStateReceiver, filter)
    }

    private fun unregisterServiceStateReceiver() {
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(serviceStateReceiver)
    }

    private fun updateTileState() {
        val tile = qsTile ?: return
        
        if (AListService.isRunning) {
            tile.state = Tile.STATE_ACTIVE
            tile.label = getString(R.string.app_name)
            tile.icon = Icon.createWithResource(this, R.drawable.openlist_logo)
            tile.contentDescription = getString(R.string.alist_server_running)
        } else {
            tile.state = Tile.STATE_INACTIVE
            tile.label = getString(R.string.app_name)
            tile.icon = Icon.createWithResource(this, R.drawable.openlist_logo)
            tile.contentDescription = getString(R.string.shutdown)
        }
        
        tile.updateTile()
    }
}