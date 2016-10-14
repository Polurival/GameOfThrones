package home.skwmium.skilltest.model.dto;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

public class RealmString extends RealmObject {
    @NonNull
    public String string;

    public RealmString() {
        string = "";
    }

    public RealmString(@NonNull String string) {
        this.string = string;
    }

    public boolean isEmpty() {
        return string.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        return string.equals(obj);
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }
}