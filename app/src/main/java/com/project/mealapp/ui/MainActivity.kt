package com.project.mealapp.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.project.mealapp.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_meals
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            val flutterEngine = FlutterEngineCache.getInstance().get("meals-module")

            if (it.itemId == R.id.nav_meals) {
                startActivity(FlutterActivity.withCachedEngine("meals-module").build(this))
            }
            val methodChannel = MethodChannel(flutterEngine?.dartExecutor, "meals.channel")
            methodChannel.setMethodCallHandler { call, result ->
                if(call.method == "meals.channel/fromFlutter"){
//                    val args: Map<String, Any>? = call.argument<Map<String, Int>>("args")
                    Toast.makeText(this, "${call.arguments}", Toast.LENGTH_SHORT).show()
                    result.success( mapOf<String, String>())
                     methodChannel.invokeMethod("meals.channel/fromNative", "Hello from Android")
                }else{
                    result.notImplemented()
                }
            }

            return@setNavigationItemSelectedListener super.onSupportNavigateUp()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}