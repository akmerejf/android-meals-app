package com.project.mealapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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
import com.project.mealapp.ui.meals.MealCategoriesActivity
import com.project.mealapp.ui.meals.MealsFormActivity
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    private val TAG_FLUTTER_FRAGMENT = "meals-categories-fragment"
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
            if (it.itemId == R.id.nav_meals_form) {
                startActivity(
                    FlutterActivity.withCachedEngine("meal-form-feature")
                        .build(this)
//                    Intent(this, MealsFormActivity::class.java)
                )
            }
            if (it.itemId == R.id.nav_meals) {
//                val fragmentManager: FragmentManager = supportFragmentManager
//
//                // Attempt to find an existing FlutterFragment, in case this is not the
//                // first time that onCreate() was run.
//                var flutterFragment = fragmentManager
//                    .findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment?
//
//                // Create a FlutterFragment if one does not exist.
//                if (flutterFragment == null) {
//                    flutterFragment = FlutterFragment.createDefault()
//                }
//
//                fragmentManager
//                    .beginTransaction()
//                    .add(
//                        R.id.nav_host_fragment,
//                        flutterFragment,
//                        TAG_FLUTTER_FRAGMENT
//                    )
//                    .commit()
                val activity: FlutterActivity = MealCategoriesActivity()

                startActivity(
                    FlutterActivity.withCachedEngine("meal-categories-feature")
                        .build(this)
//                    Intent(this, MealCategoriesActivity::class.java)
                )
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