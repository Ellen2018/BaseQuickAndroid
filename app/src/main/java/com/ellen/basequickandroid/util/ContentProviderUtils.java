package com.ellen.basequickandroid.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容提供者工具类
 * 1.获取本地所有的图片
 * 2.获取本地所有的视频
 * 3.获取本地所有的音频
 *
 * 使用之前请申请文件读写权限
 */
public class ContentProviderUtils {

    //通过内容提供者获取本地所有图片的地址集合
    public static List<String> getIamgePathList(Context context) {
        Cursor cur = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media.DATA},
                        "",
                        new String[]{},
                        MediaStore.Images.Media.DATE_MODIFIED + " DESC");
        List<String> imagePathList = new ArrayList<>(cur.getCount());
        if (cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                //过滤掉空的图片
                if (new File(cur.getString(0)).exists()) {
                    imagePathList.add(cur.getString(0));
                }
                cur.moveToNext();
            }
        }
        cur.close();
        return imagePathList;
    }

    //通过内容提供者获取本地所有的图片地址集合
    public static List<String> getVideoPathList(Context context) {
        String[] projection = new String[]{
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION
        };
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null,
                null, null);
        List<String> videoPathList = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            String path = cursor
                    .getString(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            long duration = cursor
                    .getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
            if (duration > 0) {
                //过滤掉时长为0的视频
                videoPathList.add(path);
            }
        }
        cursor.close();
        return videoPathList;
    }

    public static List<Music> getMusicPathList(Context context) {
        List<Music> musicList = new ArrayList<>();
        Cursor c = null;
        c = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        while (c.moveToNext()) {
            String path = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));// 路径
            String name = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)); // 歌曲名
            String album = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)); // 专辑
            String artist = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)); // 作者
            long size = c.getLong(c.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));// 大小
            int duration = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));// 时长
            int musicId = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));// 歌曲的id
            int albumId = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            if (duration != 0) {
                Music music = new Music();
                music.setPath(path);
                music.setName(name);
                music.setAlbum(album);
                music.setArtist(artist);
                music.setSize(size);
                music.setDuration(duration);
                music.setMusicId(musicId);
                music.setAlbumId(albumId);
                musicList.add(music);
            }
        }
        return musicList;
    }


    public static class Music {

        private String name;
        private String singerName;
        private String path;
        private String album;
        private long size;
        private int musicId;
        private String artist;

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        private long duration;

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public int getMusicId() {
            return musicId;
        }

        public void setMusicId(int musicId) {
            this.musicId = musicId;
        }

        public int getAlbumId() {
            return albumId;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        private int albumId;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSingerName() {
            return singerName;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }


    }


}
