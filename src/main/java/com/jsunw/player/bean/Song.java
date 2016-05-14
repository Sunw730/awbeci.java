package com.jsunw.player.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Sunw on 16/5/14.
 */
public class Song {

    @JSONField(name = "album_cover")
    private String albumCover;
    @JSONField(name = "album_name")
    private String albumName;
    @JSONField(name = "artist_name")
    private String artistName;
    @JSONField(name = "location")
    private String location;
    @JSONField(name = "lyric")
    private String lyric;

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
