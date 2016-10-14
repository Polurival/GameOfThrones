package home.skwmium.skilltest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class House extends RealmObject {
    @JsonProperty("url")
    @PrimaryKey
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;
    @JsonProperty("coatOfArms")
    private String coatOfArms;
    @JsonProperty("words")
    private String words;
    @JsonProperty("titles")
    private RealmList<RealmString> titles = new RealmList<>();
    @JsonProperty("seats")
    private RealmList<RealmString> seats = new RealmList<>();
    @JsonProperty("currentLord")
    private String currentLord;
    @JsonProperty("heir")
    private String heir;
    @JsonProperty("overlord")
    private String overlord;
    @JsonProperty("founded")
    private String founded;
    @JsonProperty("founder")
    private String founder;
    @JsonProperty("diedOut")
    private String diedOut;
    @JsonProperty("ancestralWeapons")
    private RealmList<RealmString> ancestralWeapons = new RealmList<>();
    @JsonProperty("cadetBranches")
    private RealmList<RealmString> cadetBranches = new RealmList<>();
    @JsonProperty("swornMembers")
    private RealmList<RealmString> swornMembers = new RealmList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCoatOfArms() {
        return coatOfArms;
    }

    public void setCoatOfArms(String coatOfArms) {
        this.coatOfArms = coatOfArms;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public RealmList<RealmString> getTitles() {
        return titles;
    }

    public void setTitles(RealmList<RealmString> titles) {
        this.titles = titles;
    }

    public RealmList<RealmString> getSeats() {
        return seats;
    }

    public void setSeats(RealmList<RealmString> seats) {
        this.seats = seats;
    }

    public String getCurrentLord() {
        return currentLord;
    }

    public void setCurrentLord(String currentLord) {
        this.currentLord = currentLord;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getOverlord() {
        return overlord;
    }

    public void setOverlord(String overlord) {
        this.overlord = overlord;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getDiedOut() {
        return diedOut;
    }

    public void setDiedOut(String diedOut) {
        this.diedOut = diedOut;
    }

    public RealmList<RealmString> getAncestralWeapons() {
        return ancestralWeapons;
    }

    public void setAncestralWeapons(RealmList<RealmString> ancestralWeapons) {
        this.ancestralWeapons = ancestralWeapons;
    }

    public RealmList<RealmString> getCadetBranches() {
        return cadetBranches;
    }

    public void setCadetBranches(RealmList<RealmString> cadetBranches) {
        this.cadetBranches = cadetBranches;
    }

    public RealmList<RealmString> getSwornMembers() {
        return swornMembers;
    }

    public void setSwornMembers(RealmList<RealmString> swornMembers) {
        this.swornMembers = swornMembers;
    }
}
