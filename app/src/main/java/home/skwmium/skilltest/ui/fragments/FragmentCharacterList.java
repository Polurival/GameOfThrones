package home.skwmium.skilltest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import home.skwmium.skilltest.R;
import home.skwmium.skilltest.common.App;
import home.skwmium.skilltest.ui.activities.ActivityCharacter;
import home.skwmium.skilltest.ui.adapters.AdapterCharacters;
import home.skwmium.skilltest.ui.adapters.AdapterCharacters.OnItemCLickListener;
import home.skwmium.skilltest.utils.L;
import rx.Subscription;

public class FragmentCharacterList extends BaseFragment {
    private static final String ARG_HOUSE_URL = "house_url";

    public static FragmentCharacterList create(@NonNull String houseUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_HOUSE_URL, houseUrl);

        FragmentCharacterList fragment = new FragmentCharacterList();
        fragment.setArguments(bundle);
        return fragment;
    }

    private AdapterCharacters adapterCharacters;
    private String houseUrl;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private final OnItemCLickListener onItemCLickListener = viewModel ->
            ActivityCharacter.start(getContext(), viewModel.getUrl());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterCharacters = new AdapterCharacters();
        adapterCharacters.setItemCLickListener(onItemCLickListener);

        houseUrl = getArguments().getString(ARG_HOUSE_URL, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterCharacters);
        loadData();
    }

    private void loadData() {
        //TODO handle error
        Subscription subscription = App.getInst().getModel()
                .getCharactersByHouse(houseUrl)
                .subscribe(
                        characters -> adapterCharacters.setItems(characters),
                        throwable -> L.e("error", throwable),
                        () -> L.e("onComplete"));
        addSubscription(subscription);
    }
}
