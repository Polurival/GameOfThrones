package home.skwmium.skilltest.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import home.skwmium.skilltest.R;
import home.skwmium.skilltest.common.App;
import home.skwmium.skilltest.utils.L;
import rx.Subscription;

import static home.skwmium.skilltest.utils.Const.SPLASH_SCREEN_DELAY;
import static java.lang.System.currentTimeMillis;

public class ActivitySplash extends BaseActivity {
    @BindView(R.id.button_try_again)
    Button buttonTryAgain;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private long startScreenTs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startScreenTs = currentTimeMillis();
        preloadData();
    }

    @OnClick(R.id.button_try_again)
    public void onClickTryAgain() {
        preloadData();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void showTryAgainAction() {
        buttonTryAgain.setVisibility(View.VISIBLE);
    }

    public void hideTryAgainAction() {
        buttonTryAgain.setVisibility(View.INVISIBLE);
    }

    public void openMainView() {
        long waitToStartMillis = SPLASH_SCREEN_DELAY - (currentTimeMillis() - startScreenTs);
        if (waitToStartMillis <= 0L) waitToStartMillis = 0L;
        new Handler().postDelayed(() -> ActivityHouses.start(this), waitToStartMillis);
    }

    private void preloadData() {
        showProgress();
        hideTryAgainAction();

        //noinspection unchecked
        Subscription subscription = App.getInst().getModel()
                .preloadData()
                .subscribe(o -> {
                        },
                        throwable -> {
                            hideProgress();
                            showSnackbar(R.string.error_loading_data);
                            showTryAgainAction();
                            L.e("error", throwable);
                        },
                        () -> {
                            L.e("onCompleted");
                            hideProgress();
                            openMainView();
                        });
        addSubscription(subscription);
    }
}