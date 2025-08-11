package com.github.jing332.alistflutter.model

import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.github.jing332.alistflutter.AListService
import com.github.jing332.alistflutter.R
import com.github.jing332.alistflutter.SwitchServerActivity

object ShortCuts {
    private inline fun <reified T> buildIntent(context: Context): Intent {
        val intent = Intent(context, T::class.java)
        intent.action = Intent.ACTION_VIEW
        return intent
    }

    private fun buildStartServiceShortcut(context: Context): ShortcutInfoCompat {
        val intent = Intent(context, SwitchServerActivity::class.java).apply {
            action = "START_ALIST_SERVICE"
            putExtra("action", "start")
        }
        
        return ShortcutInfoCompat.Builder(context, "alist_start")
            .setShortLabel(context.getString(R.string.start_service))
            .setLongLabel(context.getString(R.string.start_alist_service))
            .setIcon(IconCompat.createWithResource(context, R.drawable.openlist_start))
            .setIntent(intent)
            .build()
    }

    private fun buildStopServiceShortcut(context: Context): ShortcutInfoCompat {
        val intent = Intent(context, SwitchServerActivity::class.java).apply {
            action = "STOP_ALIST_SERVICE"
            putExtra("action", "stop")
        }
        
        return ShortcutInfoCompat.Builder(context, "alist_stop")
            .setShortLabel(context.getString(R.string.stop_service))
            .setLongLabel(context.getString(R.string.stop_alist_service))
            .setIcon(IconCompat.createWithResource(context, R.drawable.openlist_stop))
            .setIntent(intent)
            .build()
    }



    fun updateShortcutsBasedOnServiceState(context: Context) {
        val shortcuts = if (AListService.isRunning) {
            listOf(
                buildStopServiceShortcut(context),
            )
        } else {
            listOf(
                buildStartServiceShortcut(context),
            )
        }
        
        ShortcutManagerCompat.setDynamicShortcuts(context, shortcuts)
    }

    fun buildShortCuts(context: Context) {
        updateShortcutsBasedOnServiceState(context)
    }
}