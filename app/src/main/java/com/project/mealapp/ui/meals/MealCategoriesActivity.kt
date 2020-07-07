package com.project.mealapp.ui.meals

import android.content.Intent
import com.project.mealapp.ui.home.MainActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MealCategoriesActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "meals.channel"
        ).setMethodCallHandler { call, result ->
            if (call.method == "meals.showMealDetail") {
                startActivity(Intent(this, MainActivity::class.java))
//                val args = mapOf("session_id" to user.SessionId)
                result.success(null)
            } else {
                result.error("ERROR_CODE", "Falha", null)
            }
            // Note: this method is invoked on the main thread.
            // TODO
        }
    }
}