package at.fh.swengb.loggingviewsandactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate

import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {
        val USERNAME = "USERNAME"
        val DARKMODE = "DARKMODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences("LoggingViewsAndActivity", Context.MODE_PRIVATE)

        username_input.hint = sharedPreferences.getString("USERNAME", null)
        switch_darkmode.isChecked = sharedPreferences.getBoolean("DARKMODE", false)

        save_settings.setOnClickListener {
            val sharedPreferences = getSharedPreferences("LoggingViewsAndActivity", Context.MODE_PRIVATE)

            sharedPreferences.edit().putString("USERNAME", username_input.text.toString()).apply()
            sharedPreferences.edit().putBoolean("DARKMODE", switch_darkmode.isChecked).apply()

            when {
                switch_darkmode.isChecked -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                switch_darkmode.isChecked.not() -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                else -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
            finish()

        }
    }
}