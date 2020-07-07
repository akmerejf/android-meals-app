package com.project.mealapp

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MealsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Instantiate a FlutterEngine.
        var flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("/meal-categories")
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // Cache the FlutterEngine to be used by FlutterActivity or FlutterFragment.
        FlutterEngineCache
            .getInstance()
            .put("meal-categories-feature", flutterEngine)

        flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("/meal-form")

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache
            .getInstance()
            .put("meal-form-feature", flutterEngine)
    }
}