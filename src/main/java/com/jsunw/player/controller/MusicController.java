package com.jsunw.player.controller;

import com.alibaba.fastjson.JSON;
import com.jsunw.player.bean.PlayList;
import com.jsunw.player.bean.Song;
import com.jsunw.player.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sunw on 16/5/13.
 */
@RestController
@RequestMapping("jsunw/api")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 歌单列表
     * @param ids
     * @return
     */
    @RequestMapping("playlist")
    public String getPlayList(String ids) {
        List<PlayList> playLists = musicService.playLists(ids.split(",|\\|"));
        return String.format("var wenkmList = %s", JSON.toJSONString(playLists));
    }

    /**
     * 歌曲详情
     * @param id
     * @param callback
     * @return
     */
    @RequestMapping("song")
    public String getSongInfo(String id, String callback) {
        Song song = musicService.song(id);
        return String.format("%s(%s)", callback, JSON.toJSONString(song));
    }

    /**
     * 歌词
     * @param lrc 歌曲ID
     * @return
     */
    @RequestMapping("lyric")
    public String lyric(String lrc) {
        String lyric = musicService.lyric(lrc)+"''";
        return String.format("var cont = '%s';", lyric.replace("'", "\\'"));
    }

    /**
     * 主题颜色
     * @param url
     * @return
     */
    @RequestMapping("color")
    public String color(String url) {
        return String.format("var cont = '%s';", musicService.color(url).replace("'", "\\'"));
    }

}
