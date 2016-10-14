package home.skwmium.skilltest.ui.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import home.skwmium.skilltest.common.App;
import home.skwmium.skilltest.model.dto.House;
import home.skwmium.skilltest.ui.fragments.FragmentCharacterList;
import home.skwmium.skilltest.utils.Utils;

public class HousesPagerAdapter extends FragmentStatePagerAdapter {
    @NonNull
    private List<House> houseList;

    public HousesPagerAdapter(FragmentManager fm) {
        super(fm);
        this.houseList = new ArrayList<>();
    }

    public void putItems(@Nullable List<House> houseList) {
        if (houseList == null) return;
        this.houseList.clear();
        this.houseList.addAll(houseList);
        notifyDataSetChanged();
    }

    public int getHousePositionById(@NonNull String houseId) {
        for (int i = 0; i < getCount(); ++i) {
            if (Uri.parse(houseList.get(i).getUrl()).getLastPathSegment().equals(houseId))
                return i;
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        House house = houseList.get(position);
        return FragmentCharacterList.create(house.getUrl());
    }

    @Override
    public int getCount() {
        return houseList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int titleRes = Utils.getHouseTabTitle(houseList.get(position));
        return App.getInst().getResources().getString(titleRes);
    }
}
