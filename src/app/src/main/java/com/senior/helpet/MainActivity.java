package com.senior.helpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mainToolbar;
    private FirebaseAuth mAuth;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("HelPet");

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null) {
            sendToLogin();
        } else {
            // Update the header navigationbar for current user
            NavigationView navigationView = findViewById(R.id.nav_view);
            View navHeaderView = navigationView.getHeaderView(0);
            TextView nav_email = navHeaderView.findViewById(R.id.header_email);
            nav_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout_btn:
                logOut();
                return true;
            case R.id.action_setting_btn:

                return true;
            default:
                return false;
        }
    }

    private void logOut() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void onPPClick(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.nav_adoption:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdoptionFragment()).commit();
                break;
            case R.id.nav_found:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoundpetFragment()).commit();
                break;
            case R.id.nav_indigent:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IndigentFragment()).commit();
                break;
            case R.id.nav_lookAfter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LookafterFragment()).commit();
                break;
            case R.id.nav_lost:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LostpetFragment()).commit();
                break;
            case R.id.nav_mating:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MatingFragment()).commit();
                break;
            case R.id.nav_qa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QaFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                //finish();
                break;
            case R.id.nav_logout:
                logOut();
                break;
                /*
            case R.id.nav_profile_picture:
                Toast.makeText(this, "WORKED", Toast.LENGTH_SHORT).show();
                break;*/
            default:
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
