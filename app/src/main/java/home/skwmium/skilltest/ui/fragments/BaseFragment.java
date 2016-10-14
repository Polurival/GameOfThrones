package home.skwmium.skilltest.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import home.skwmium.skilltest.ui.activities.BaseActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@SuppressWarnings("unused")
public abstract class BaseFragment extends Fragment {
    private CompositeSubscription compositeSubscription;
    private Unbinder mUnbinder;

    public <T extends View> T $(@IdRes int id) {
        //noinspection unchecked,ConstantConditions
        return (T) getView().findViewById(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public Context getContext() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return super.getContext();
        else
            return getActivity();
    }

    @Nullable
    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    public void showSnackbar(@StringRes int res) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null)
            baseActivity.showSnackbar(res);
    }

    public void showProgress() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null)
            getBaseActivity().showProgress();
    }

    public void hideProgress() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null)
            getBaseActivity().hideProgress();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}
