package com.jsunw.player.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsunw.player.bean.PlayList;
import com.jsunw.player.bean.Song;
import com.jsunw.player.lang.CoreException;
import com.jsunw.player.util.RgbColor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunw on 16/5/13.
 */
@Service
public class MusicService {

    private static final String default_album_cover = "";
    private static final String default_album_name = "未知";
    private static final String default_artist_name = "未知";

    /**
     * 歌单信息
     * @param ids
     * @return
     */
    public List<PlayList> playLists(String[] ids) {
        List<PlayList> list = new ArrayList<>();
        for(int ii = 0, ll = ids.length; ii < ll; ii++) {
            String json = sendGet(String.format("http://music.163.com/api/playlist/detail?id=%s&updateTime=-1", ids[ii].trim()));
            JSONObject object = JSON.parseObject(json);
            int code = object.getInteger("code");
            if(code != 200) continue;
            object = object.getJSONObject("result");
            if(object == null) continue;
            String name = object.getString("name"),
                    desc = object.getString("description");
            List<String> songs, songIds;
            JSONArray array = object.getJSONArray("tracks");
            if(array != null && array.size() > 0) {
                songs = new ArrayList<String>();
                songIds = new ArrayList<String>();
                for(int i = 0, l = array.size(); i < l; i++) {
                    object = array.getJSONObject(i);
                    songs.add(object.getString("name"));
                    songIds.add(object.getString("id") + "wy");
                }
            } else {
                songs = new ArrayList<String>(0);
                songIds = new ArrayList<String>(0);
            }
            //
            PlayList playList = new PlayList();
            playList.setName(name);
            playList.setDescn(desc);
            playList.setSongIds(songIds);
            playList.setSongs(songs);
            list.add(playList);
        }
        return list;
    }

    /**
     * 歌曲详情
     * @param id
     * @return
     */
    public Song song(String id) {
        String url = String.format("http://music.163.com/api/song/detail/?id=%s&ids=[%s]", id, id);
        String json = sendGet(url);
        JSONObject object = JSON.parseObject(json);
        int code = object.getInteger("code");
        if(code != 200) throw new CoreException("网络错误");
        JSONArray array = object.getJSONArray("songs");
        if(array == null || array.size() == 0) throw new CoreException("找不到歌曲: " + id);
        JSONObject song = array.getJSONObject(0);
        //
        String albumCover = default_album_cover,
                albumName = default_album_name,
                artistName = default_artist_name,
                location = "",
                lyric = "",
                tmp;
        JSONObject album = song.getJSONObject("album");
        if(album != null) {
            if(!StringUtils.isEmpty(tmp = album.getString("picUrl"))) albumCover = tmp;
            if(!StringUtils.isEmpty(tmp = album.getString("name"))) albumName = tmp;
        }
        JSONArray artists = song.getJSONArray("artists");
        if(artists != null && artists.size() > 0) {
            if(!StringUtils.isEmpty(tmp = artists.getJSONObject(0).getString("name"))) artistName = tmp;
        }
        if(!StringUtils.isEmpty(tmp = song.getString("mp3Url"))) location = tmp;
        //
        Song s = new Song();
        s.setAlbumCover(albumCover);
        s.setAlbumName(albumName);
        s.setArtistName(artistName);
        s.setLocation(location);
        s.setLyric(lyric);
        return s;
    }

    /**
     * 歌词
     * @param songId
     * @return
     */
    public String lyric(String songId) {
        String json = sendGet(String.format("http://music.163.com/api/song/lyric?os=pc&id=%s&lv=-1&kv=-1&tv=-1", songId));
        JSONObject object = JSON.parseObject(json);
        int code = object.getInteger("code");
        if(code != 200) throw new CoreException("网络错误");
        object = object.getJSONObject("lrc");
        if(object == null) throw new CoreException("找不到歌词: " + songId);
        return object.getString("lyric")
                .replace("\r\n", "")
                .replace("\n", "")
                .replace("\r", "");
    }

    /**
     * 网络图片的主题颜色
     * @param imageUrl
     * @return
     */
    public String color(String imageUrl) {
        try {
            return RgbColor.getImageRgb(imageUrl.trim());
        } catch (IOException e) {
            return "0,0,0";
        }
    }


    /**
     * 发送GET请求
     * @param url
     * @return
     */
    private String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Referer", "http://music.163.com/");
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Cookie", "appver=1.5.0.75771;");

//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
