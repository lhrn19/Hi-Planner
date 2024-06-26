package com.example.intento;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase


class Activity_drawer_menu : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView =findViewById(R.id.navigationView)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }



        navigationView.setNavigationItemSelectedListener {
                menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.nav_calendar -> {

                        val intent_mod_bud = Intent(this, Event_main_activity::class.java)
                        startActivity(intent_mod_bud)
                        setContentView(R.layout.dashboard)
                    true

                }
                R.id.nav_account ->{
                    val intent_mod_bud = Intent(this, ConsultarUserActivity::class.java)
                    startActivity(intent_mod_bud)

                    true
                }
                R.id.nav_budget->{
                    setContentView(R.layout.budget_main)

                    val intent_mod_bud = Intent(this, Budget_main_activity::class.java)
                    startActivity(intent_mod_bud)
                    setContentView(R.layout.budget_main)

                    true
                }
                R.id.nav_logout->{
                    val dbRefCurrentUser = FirebaseDatabase.getInstance().getReference("current_user_id")
                    dbRefCurrentUser.removeValue().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Redirigir al usuario a la pantalla de inicio de sesión
                            val intent = Intent(this@Activity_drawer_menu, MainActivity::class.java)
                            // Limpiar la pila de actividades
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        } else {
                        }
                    }

                    true
                }
                else ->{false}
            }
            drawerLayout.close()
            true
        }
    }
}