package com.jsunw.player.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 专辑信息
 * Created by Sunw on 16/5/14.
 */
public class PlayList {

    @JSONField(name = "song_album")
    private String name;
    @JSONField(name = "song_album1")
    private String descn;
    @JSONField(name = "song_name")
    private List<String> songs;
    @JSONField(name = "song_id")
    private List<String> songIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }
}
