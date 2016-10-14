package home.skwmium.skilltest.common;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import home.skwmium.skilltest.model.Model;
import home.skwmium.skilltest.model.ModelImpl;
import home.skwmium.skilltest.model.network.ApiClient;
import home.skwmium.skilltest.model.network.ServiceGenerator;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static App sInstance;

    private Toast mToast;
    private Model model;

    @SuppressLint("ShowToast")
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        initModel();
        initServices();
    }

    public static App getInst() {
        return sInstance;
    }


    public void showToast(@StringRes int res) {
        showToast(getString(res));
    }

    public void showToast(@Nullable String s) {
        if (s == null || s.isEmpty()) return;
        mToast.setText(s);
        mToast.show();
    }

    public Model getModel() {
        return model;
    }

    private void initModel() {
        ApiClient apiInterface = ServiceGenerator.createApiInterface();
        model = new ModelImpl(apiInterface);
    }

    private void initServices() {
        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
