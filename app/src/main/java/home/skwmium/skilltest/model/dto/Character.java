package home.skwmium.skilltest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character extends RealmObject {
    @JsonProperty("url")
    @PrimaryKey
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("culture")
    private String culture;
    @JsonProperty("born")
    private String born;
    @JsonProperty("died")
    private String died;
    @JsonProperty("titles")
    private RealmList<RealmString> titles = new RealmList<>();
    @JsonProperty("aliases")
    private RealmList<RealmString> aliases = new RealmList<>();
    @JsonProperty("father")
    private String fatherUrl;
    @JsonProperty("mother")
    private String motherUrl;
    @JsonProperty("spouse")
    private String spouse;
    @JsonProperty("allegiances")
    private RealmList<RealmString> allegiances = new RealmList<>();
    @JsonProperty("books")
    private RealmList<RealmString> books = new RealmList<>();
    @JsonProperty("povBooks")
    private RealmList<RealmString> povBooks = new RealmList<>();
    @JsonProperty("tvSeries")
    private RealmList<RealmString> tvSeries = new RealmList<>();
    @JsonProperty("playedBy")
    private RealmList<RealmString> playedBy = new RealmList<>();

    @JsonIgnore
    private House house;
    @JsonIgnore
    private Character mother;
    @JsonIgnore
    private Character father;


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public RealmList<RealmString> getTitles() {
        return titles;
    }

    public void setTitles(RealmList<RealmString> titles) {
        this.titles = titles;
    }

    public RealmList<RealmString> getAliases() {
        return aliases;
    }

    public void setAliases(RealmList<RealmString> aliases) {
        this.aliases = aliases;
    }

    public String getFatherUrl() {
        return fatherUrl;
    }

    public void setFatherUrl(String fatherUrl) {
        this.fatherUrl = fatherUrl;
    }

    public String getMotherUrl() {
        return motherUrl;
    }

    public void setMotherUrl(String motherUrl) {
        this.motherUrl = motherUrl;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public RealmList<RealmString> getAllegiances() {
        return allegiances;
    }

    public void setAllegiances(RealmList<RealmString> allegiances) {
        this.allegiances = allegiances;
    }

    public RealmList<RealmString> getBooks() {
        return books;
    }

    public void setBooks(RealmList<RealmString> books) {
        this.books = books;
    }

    public RealmList<RealmString> getPovBooks() {
        return povBooks;
    }

    public void setPovBooks(RealmList<RealmString> povBooks) {
        this.povBooks = povBooks;
    }

    public RealmList<RealmString> getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(RealmList<RealmString> tvSeries) {
        this.tvSeries = tvSeries;
    }

    public RealmList<RealmString> getPlayedBy() {
        return playedBy;
    }

    public void setPlayedBy(RealmList<RealmString> playedBy) {
        this.playedBy = playedBy;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Character getMother() {
        return mother;
    }

    public void setMother(Character mother) {
        this.mother = mother;
    }

    public Character getFather() {
        return father;
    }

    public void setFather(Character father) {
        this.father = father;
    }
}
