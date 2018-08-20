package com.exc.louis.itunesapp.gson;

import com.google.gson.annotations.SerializedName;

public class SongInformation {
    @SerializedName("wrapperType")
    private String wrapperType;

    @SerializedName("kind")
    private String kind;

    @SerializedName("artistId")
    private long artistId;

    @SerializedName("collectionId")
    private long collectionId;

    @SerializedName("trackId")
    private long trackId;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("collectionName")
    private String collectionName;

    @SerializedName("trackCensoredName")
    private String trackCensoredName;

    @SerializedName("trackName")
    private String trackName;

    @SerializedName("collectionCensoredName")
    private String collectionCensoredName;

    @SerializedName("artistViewUrl")
    private String artistViewUrl;

    @SerializedName("collectionViewUrl")
    private String collectionViewUrl;

    @SerializedName("trackViewUrl")
    private String trackViewUrl;

    @SerializedName("previewUrl")
    private String previewUrl;

    @SerializedName("artworkUrl30")
    private String artworkUrl30;

    @SerializedName("artworkUrl60")
    private String artworkUrl60;

    @SerializedName("artworkUrl100")
    private String artworkUrl100;

    @SerializedName("collectionPrice")
    private float collectionPrice;

    @SerializedName("trackPrice")
    private float trackPrice;

    @SerializedName("collectionExplicitness")
    private String collectionExplicitness;

    @SerializedName("trackExplicitness")
    private String trackExplicitness;

    @SerializedName("discCount")
    private long discCount;

    @SerializedName("discNumber")
    private long discNumber;

    @SerializedName("trackCount")
    private long trackCount;

    @SerializedName("trackNumber")
    private long trackNumber;

    @SerializedName("trackTimeMillis")
    private long trackTimeMillis;

    @SerializedName("country")
    private String country;

    @SerializedName("currency")
    private String currency;

    @SerializedName("primaryGenreName")
    private String primaryGenreName;

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public float getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(float trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public long getDiscCount() {
        return discCount;
    }

    public void setDiscCount(long discCount) {
        this.discCount = discCount;
    }

    public long getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(long discNumber) {
        this.discNumber = discNumber;
    }

    public long getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(long trackCount) {
        this.trackCount = trackCount;
    }

    public long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(long trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(long trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
}
