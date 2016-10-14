package home.skwmium.skilltest.utils;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import home.skwmium.skilltest.R;
import home.skwmium.skilltest.model.dto.Character;
import home.skwmium.skilltest.model.dto.House;
import home.skwmium.skilltest.model.dto.RealmString;
import io.realm.RealmList;

@SuppressWarnings("unused")
public class Utils {

    public static int getHouseIconByCharacter() {
        return R.drawable.ic_lanister;
    }

    @StringRes
    public static int getHouseTabTitle(@Nullable House house) {
        if (house == null) return -1;
        switch (Uri.parse(house.getUrl()).getLastPathSegment()) {
            case Const.HOUSE_ID_LANNISTER:
                return R.string.title_lannisters;
            case Const.HOUSE_ID_STARK:
                return R.string.title_starks;
            case Const.HOUSE_ID_TARGARIEN:
                return R.string.title_targariens;
            default:
                return -1;
        }
    }

    @DrawableRes
    public static int getHouseIconByCharacter(@Nullable Character character) {
        if (character == null) return -1;
        switch (Uri.parse(character.getHouse().getUrl()).getLastPathSegment()) {
            case Const.HOUSE_ID_LANNISTER:
                return R.drawable.ic_lanister;
            case Const.HOUSE_ID_STARK:
                return R.drawable.ic_stark;
            case Const.HOUSE_ID_TARGARIEN:
                return R.drawable.ic_targarien;
            default:
                return -1;
        }
    }

    @DrawableRes
    public static int getHouseLogoByCharacter(@Nullable Character character) {
        if (character == null || character.getHouse() == null) return -1;
        switch (Uri.parse(character.getHouse().getUrl()).getLastPathSegment()) {
            case Const.HOUSE_ID_LANNISTER:
                return R.drawable.logo_lannister;
            case Const.HOUSE_ID_STARK:
                return R.drawable.logo_stark;
            case Const.HOUSE_ID_TARGARIEN:
                return R.drawable.logo_targarien;
            default:
                return -1;
        }
    }

    @NonNull
    public static String realmStringsToString(@Nullable RealmList<RealmString> realmStrings) {
        if (realmStrings == null) return "";
        StringBuilder builder = new StringBuilder();
        for (RealmString realmString : realmStrings) {
            if (builder.length() > 0) builder.append("\n");
            builder.append(realmString.string);
        }
        return builder.toString();
    }

    // ---------- COMMON ----------
    public static boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }
}
