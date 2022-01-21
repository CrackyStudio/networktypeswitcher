package com.crackystudio.networktypeswitcher

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            if (Build.VERSION.SDK_INT < 29) {
                openMenu("com.android.settings", "com.android.settings.RadioInfo")
            } else {
                openMenu("com.android.phone", "com.android.phone.settings.RadioInfo")
            }
        } catch (ex: Exception) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("An error occurred. Unfortunately, some devices like yours are not compatible.")
                .setCancelable(false)
                .setPositiveButton("I UNDERSTAND") { _, _ ->
                    val uninstall = Intent(Intent.ACTION_DELETE)
                    uninstall.data = Uri.parse("package:com.crackystudio.networktypeswitcher")
                    startActivity(uninstall)
                    finish()
                }

            val incompatibilityAlert = dialogBuilder.create()
            incompatibilityAlert.setTitle("Incompatibility")
            incompatibilityAlert.show()
        }
    }

    private fun openMenu(packageName: String, className: String) {
        val menu = Intent("android.intent.action.MAIN")
        menu.setClassName(packageName, className)
        startActivity(menu)
        finish()
    }
}