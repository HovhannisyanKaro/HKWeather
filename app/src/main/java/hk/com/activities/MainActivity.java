package hk.com.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hk.com.adapters.WeatherAdapter;
import hk.com.controllers.DBController;
import hk.com.controllers.DataController;
import hk.com.controllers.MyNetworkCallHandlerController;
import hk.com.controllers.NetworkController;
import hk.com.controllers.ViewController;
import hk.com.entities.WeatherList;
import hk.com.fragments.FewDaysWeatherFragment;
import hk.com.hkweather.R;
import hk.com.interfacies.OnRVWeatherClickListener;
import hk.com.utils.ToastUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnRVWeatherClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.recycler_view_weathers)
    RecyclerView rvWeathers;

    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        NetworkController.getNetworkController().getWeathers(this, MyNetworkCallHandlerController.getMyNetworkCallHandlerController());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setMainActivity(this);
    }

    public void setAdapter(boolean isOnline) {
        if (isOnline) {
            logicForSavingWeatherInfo();
        }



        try {
            adapter = new WeatherAdapter(this, DBController.getWeatherInfoFromSQLite(MainActivity.this), this);
            rvWeathers.setAdapter(adapter);
            rvWeathers.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void logicForSavingWeatherInfo() {
        try {
            List<WeatherList> weatherList = DataController.getDataController().getFirstWeatherData().getList();
            for (int i = 0; i < weatherList.size(); i++) {
                DBController.saveToSqlite(this, weatherList.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemCLicked(View view, WeatherList weatherList) {
        ToastUtils.t(this, weatherList.getName());
        ViewController.getViewController().replaceFragment(getSupportFragmentManager(), R.id.main_container, FewDaysWeatherFragment.newInstance());
    }
}
