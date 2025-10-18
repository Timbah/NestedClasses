package Playlist;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name, artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.artist = artist;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public boolean addToPlayList(String title, LinkedList<Song> playlist) {

        Song temp = findSong(title);
        if (temp != null) {
            return playlist.add(temp);
        }
        return false;

    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {

        if (trackNumber < 1 || trackNumber > songs.size()) {
            return false;
        } else {
            Song temp = songs.get(trackNumber - 1);
            return playlist.add(temp);
        }

    }

    public boolean addSong(String title, double duration) {

        if (findSong(title) == null) {
            return songs.add(new Song(title, duration));
        }

        return false;
    }

    private Song findSong(String title) {

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }

        return null;
    }

    public void printSongs() {
        if (songs == null || songs.isEmpty()) {
            System.out.println("No songs in the album.");
            return;
        }

        System.out.println("Album: " + name + " by " + artist);
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }
}