package com.code_connoisseure.space_invaders.music;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Random;

public class PlayList {
    private ArrayList<Music> playList;
    private int trackCount;
    private int currentTrack = 0;
    private boolean shufflePlay = false;
    private boolean playing = false;

    public PlayList() {
        playList = new ArrayList<Music>();
        trackCount = 0;
    }

    public PlayList(Music track) {
        playList = new ArrayList<Music>();
        playList.add(track);
        trackCount++;
    }

    public PlayList(ArrayList<Music> playList) {
        this.playList = playList;
        trackCount = playList.size();
    }

    public boolean addTrack(Music track) {
        if (playList.contains(track))
            return false;
        playList.add(track);
        trackCount++;
        return true;
    }

    public boolean removeTrack(Music track) {
        if (playList.contains(track)) {
            playList.remove(track);
            trackCount--;
            return true;
        }
        return false;
    }

    public boolean play() {
        if (trackCount > 0) {
            playList.get(currentTrack).play();
            playing = true;
            return true;
        }
        return false;
    }

    public boolean stop() {
        if (playing) {
            playList.get(currentTrack).stop();
            playing = false;
            return true;
        }
        return false;
    }

    public boolean next() {
        if (shufflePlay) {
            stop();
            shufflePlay();
            return true;
        }
        else if (currentTrack < playList.size() && trackCount > 0) {
            stop();
            currentTrack++;
            play();
            return true;
        }
        return false;
    }

    public boolean previous() {
        if (currentTrack > 0) {
            stop();
            currentTrack--;
            play();
            return true;
        }
        return false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean setCurrentTrack(int newTrack) {
        if (newTrack >= 0 && newTrack < playList.size() - 1) {
            currentTrack = newTrack;
            return true;
        }
        return false;
    }

    public boolean shufflePlay() {
        if (trackCount > 0) {
            shufflePlay = true;
            Random rand = new Random();
            currentTrack = rand.nextInt(playList.size());
            play();
            return true;
        }
        return false;
    }

    public boolean stopShufflePlay() {
        if (playing) {
            if (shufflePlay) {
                shufflePlay = false;
                stop();
                return true;
            }
        }
        return false;
    }

    public void update(float delta) {
        boolean currentTrackPlaying = playList.get(currentTrack).isPlaying();
        if (shufflePlay && playing && !currentTrackPlaying) {
            shufflePlay();
        }
        else if (playing && !currentTrackPlaying) {
            next();
        }
    }

    public int getTrackCount() {
        return trackCount;
    }
}
