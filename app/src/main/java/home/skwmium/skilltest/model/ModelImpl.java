package home.skwmium.skilltest.model;

import java.util.Arrays;
import java.util.List;

import home.skwmium.skilltest.model.dto.Character;
import home.skwmium.skilltest.model.dto.House;
import home.skwmium.skilltest.model.network.ApiClient;
import home.skwmium.skilltest.utils.Const;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Action1;

import static home.skwmium.skilltest.model.SchedulerProvider.DEFAULT;
import static home.skwmium.skilltest.utils.Const.HOUSE_ID_LANNISTER;
import static home.skwmium.skilltest.utils.Const.HOUSE_ID_STARK;
import static home.skwmium.skilltest.utils.Const.HOUSE_ID_TARGARIEN;

@SuppressWarnings("TryFinallyCanBeTryWithResources")
public class ModelImpl implements Model {
    private ApiClient apiClient;

    public ModelImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable preloadData() {
        if (isModelLoaded())
            return Observable.empty();

        return Observable.from(getHouseIds())
                .flatMap(houseId -> apiClient.houseGet(houseId))
                .flatMap(this::processPreloadHouseCharacters)
                .doOnCompleted(this::setModelIsLoaded)
                .compose(DEFAULT.applySchedulers());
    }

    @Override
    public Observable<List<House>> getHouses() {
        return Observable.defer(() -> {
            Realm realm = Realm.getDefaultInstance();
            try {
                RealmResults<House> result = realm
                        .where(House.class)
                        .findAll();
                List<House> houseList = realm.copyFromRealm(result);
                return Observable.just(houseList);
            } finally {
                realm.close();
            }
        }).compose(DEFAULT.applySchedulers());
    }

    @Override
    public Observable<List<Character>> getCharactersByHouse(String houseLink) {
        return Observable.defer(() -> {
            Realm realm = Realm.getDefaultInstance();
            try {
                RealmResults<Character> result = realm
                        .where(Character.class)
                        .contains("allegiances.string", houseLink, Case.INSENSITIVE)
                        .findAll();
                List<Character> characterList = realm.copyFromRealm(result);
                return Observable.just(characterList);
            } finally {
                realm.close();
            }
        }).compose(DEFAULT.applySchedulers());
    }

    @Override
    public Observable<Character> getCharacter(String characterLink) {
        return Observable.defer(() -> {
            Realm realm = Realm.getDefaultInstance();
            try {
                Character result = realm
                        .where(Character.class)
                        .contains("url", characterLink, Case.INSENSITIVE)
                        .findFirst();
                return Observable.just(realm.copyFromRealm(result));
            } finally {
                realm.close();
            }
        }).compose(DEFAULT.applySchedulers());
    }


    // ---------- UTIL ----------
    private Observable<List<Character>> processPreloadHouseCharacters(House house) {
        final Action1<List> cacheRealmObjects = realmObjects -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObjects);
            realm.commitTransaction();
            realm.close();
        };

        return Observable.from(house.getSwornMembers())
                .filter(s -> s != null && !s.isEmpty())
                .distinct()
                .flatMap(characterLink -> apiClient.characterGet(characterLink.string))
                .flatMap(this::processWithParentsRecursive)
                .doOnNext(character -> character.setHouse(house))
                .toList()
                .doOnNext(cacheRealmObjects);
    }

    //TODO check if parent already cached
    private Observable<Character> processWithParentsRecursive(Character character) {
        final Observable<Character> currentCharacter = Observable.just(character);

        final Observable<Character> motherObservable = Observable
                .just(character.getMotherUrl())
                .filter(s -> s != null && !s.isEmpty())
                .flatMap(characterLink -> apiClient.characterGet(characterLink))
                .doOnNext(character::setMother)
                .flatMap(this::processWithParentsRecursive);

        final Observable<Character> fatherObservable = Observable
                .just(character.getFatherUrl())
                .filter(s -> s != null && !s.isEmpty())
                .flatMap(characterLink -> apiClient.characterGet(characterLink))
                .doOnNext(character::setFather)
                .flatMap(this::processWithParentsRecursive);

        return Observable.merge(
                currentCharacter,
                motherObservable,
                fatherObservable);
    }

    private List<String> getHouseIds() {
        return Arrays.asList(HOUSE_ID_LANNISTER, HOUSE_ID_STARK, HOUSE_ID_TARGARIEN);
    }

    private void setModelIsLoaded() {
        PreferencesManager.getInst().put(Const.PREF_MODEL_IS_LOADED, true);
    }

    private boolean isModelLoaded() {
        //TODO check real storage status
        return PreferencesManager.getInst().get(Const.PREF_MODEL_IS_LOADED, false);
    }
}
