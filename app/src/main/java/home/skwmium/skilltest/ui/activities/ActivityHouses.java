package home.skwmium.skilltest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import home.skwmium.skilltest.R;
import home.skwmium.skilltest.common.App;
import home.skwmium.skilltest.ui.adapters.HousesPagerAdapter;
import home.skwmium.skilltest.utils.L;
import rx.Subscription;

import static home.skwmium.skilltest.utils.Const.HOUSE_ID_LANNISTER;
import static home.skwmium.skilltest.utils.Const.HOUSE_ID_STARK;
import static home.skwmium.skilltest.utils.Const.HOUSE_ID_TARGARIEN;

public class ActivityHouses extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, ActivityHouses.class);
        context.startActivity(intent);
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private HousesPagerAdapter housesPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);

        housesPagerAdapter = new HousesPagerAdapter(getFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(housesPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        navigationView.setNavigationItemSelectedListener(this);
        setActionBar(toolbar);
        loadData();
    }

    public void setActionBar(Toolbar toolbar) {
        if (toolbar != null && drawerLayout != null) {
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
            //noinspection deprecation
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_starks:
                setCurrentPage(HOUSE_ID_STARK);
                return true;
            case R.id.nav_lannisters:
                setCurrentPage(HOUSE_ID_LANNISTER);
                return true;
            case R.id.nav_targaryens:
                setCurrentPage(HOUSE_ID_TARGARIEN);
                return true;
            default:
                return false;
        }
    }

    private void setCurrentPage(@NonNull String houseId) {
        viewPager.setCurrentItem(housesPagerAdapter.getHousePositionById(houseId));
    }

    private void loadData() {
        //TODO handle error
        Subscription subscription = App.getInst().getModel().getHouses()
                .subscribe(
                        fragmentCharacterLists ->
                                housesPagerAdapter.putItems(fragmentCharacterLists),
                        throwable -> L.e("error", throwable),
                        () -> L.e("onComplete"));
        addSubscription(subscription);
    }
}