package home.skwmium.skilltest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import home.skwmium.skilltest.R;
import home.skwmium.skilltest.common.App;
import home.skwmium.skilltest.databinding.ActivityCharacterBinding;
import home.skwmium.skilltest.model.dto.Character;
import home.skwmium.skilltest.model.dto.RealmString;
import home.skwmium.skilltest.utils.L;
import home.skwmium.skilltest.utils.Utils;
import io.realm.RealmList;
import rx.Subscription;

import static home.skwmium.skilltest.utils.Const.SEASON_PREFIX_LENGHT;

public class ActivityCharacter extends BaseActivity {
    public static final String ARG_CHARACTER_URL = "character_url";

    public static void start(@NonNull Context context, @NonNull String characterUrl) {
        Intent intent = new Intent(context, ActivityCharacter.class);
        intent.putExtra(ARG_CHARACTER_URL, characterUrl);
        context.startActivity(intent);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String characterUrl;
    private ActivityCharacterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            characterUrl = savedInstanceState.getString(ARG_CHARACTER_URL);
        else
            characterUrl = getIntent().getExtras().getString(ARG_CHARACTER_URL, "");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character);
        initToolbar();
        loadData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_CHARACTER_URL, characterUrl);
    }

    @OnClick(R.id.button_character_father)
    public void onFatherClicked(View view) {
        L.e("onFatherClicked");
        start(this, binding.getCharacter().getFatherUrl());
    }

    @OnClick(R.id.button_character_mother)
    public void onMotherClicked(View view) {
        L.e("onMotherClicked");
        start(this, binding.getCharacter().getMotherUrl());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private void loadData() {
        Subscription subscription = App.getInst().getModel()
                .getCharacter(characterUrl)
                .subscribe(
                        character -> {
                            binding.setCharacter(character);
                            showDiedMessageIfNeed(character);
                        },
                        throwable -> L.e(throwable),
                        () -> L.e("onCompleted"));
        addSubscription(subscription);
    }

    private void showDiedMessageIfNeed(@Nullable Character character) {
        if (character == null || Utils.isNullOrEmpty(character.getDied()))
            return;
        RealmList<RealmString> tvSeries = character.getTvSeries();
        RealmString season = tvSeries.get(tvSeries.size() - 1);
        if (tvSeries.isEmpty() || season == null) {
            showSnackbar(R.string.character_message_died);
        } else {
            String seasonStr = season.string.substring(SEASON_PREFIX_LENGHT);
            showSnackbar(getString(R.string.character_message_died_in, seasonStr));
        }
    }
}
