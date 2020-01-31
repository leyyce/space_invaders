package com.code_connoisseure.space_invaders.music;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a music playlist of variable length that also integrates a shuffle play functionality
 *
 * @author Yannic Wehner
 */
public class PlayList {
    /**
     * This ArrayList contains the separate music tracks contained in the playlist.
     */
    private ArrayList<Music> playList;

    /**
     * The trackCount represents how many tracks are included in the current playlist.
     */
    private int trackCount;

    /**
     * currentTrack contains the index of the track currently playing.
     */
    private int currentTrack = 0;

    /**
     * shufflePlay tells the playlist whether or not to shuffle the playlist.
     */
    private boolean shufflePlay = false;

    /**
     * playing tells the playlist whether or not a track is currently being played.
     */
    private boolean playing = false;

    public PlayList() {
        playList = new ArrayList<Music>();
        trackCount = 0;
    }

    /**
     * @param track A track that should be added to the newly created playlist.
     */
    public PlayList(Music track) {
        playList = new ArrayList<Music>();
        playList.add(track);
        trackCount++;
    }

    /**
     * @param playList An array list containing the music to which the playlist should be initialized to.
     */
    public PlayList(ArrayList<Music> playList) {
        this.playList = playList;
        trackCount = playList.size();
    }

    /**
     * Adds a new track to the playlist.
     * @param track The new track that should be added.
     * @return Returns whether or not the track was successfully added.
     */
    public boolean addTrack(Music track) {
        if (playList.contains(track))
            return false;
        playList.add(track);
        trackCount++;
        return true;
    }

    /**
     * Removes a given track from the playlist.
     * @param track The track that should be removed.
     * @return Returns whether or not the track was removed successfully.
     */
    public boolean removeTrack(Music track) {
        if (playList.contains(track)) {
            playList.remove(track);
            trackCount--;
            return true;
        }
        return false;
    }

    /**
     * Starts playing the currently selected track.
     * @return Returns whether or not the track was started successfully.
     */
    public boolean play() {
        if (trackCount > 0) {
            playList.get(currentTrack).play();
            playing = true;
            return true;
        }
        return false;
    }

    /**
     * Stops playing the currently selected track.
     * @return Returns whether or not the track was stopped successfully.
     */
    public boolean stop() {
        if (playing) {
            playList.get(currentTrack).stop();
            playing = false;
            return true;
        }
        return false;
    }

    /**
     * Skips to the next track.
     * @return Returns whether or not the track could be skipped.
     */
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

    /**
     * Skips to the previous track.
     * @return Returns whether or not the track could be skipped.
     */
    public boolean previous() {
        if (currentTrack > 0) {
            stop();
            currentTrack--;
            play();
            return true;
        }
        return false;
    }

    /**
     * @return Returns whether or not the playlist is playing at the moment.
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Sets the current track to the given index and starts playing it.
     * @param newTrack The index of the new track to play
     * @return Returns whether or not the track could be played successfully.
     */
    public boolean setCurrentTrack(int newTrack) {
        if (newTrack >= 0 && newTrack < playList.size() - 1) {
            currentTrack = newTrack;
            return true;
        }
        return false;
    }

    /**
     * Starts shuffle playing the playlist.
     * @return Returns whether or not the playlist could be shuffle played.
     */
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

    /**
     * Stops shuffle play.
     * @return Returns whether or not the shuffle play could be stopped.
     */
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

    /**
     * Updates the playlist.
     * @param delta Provided by mini2Dx.
     *              The time span between the current frame and the last frame in seconds.
     *              Might be smoothed over n frames.
     */
    public void update(float delta) {
        boolean currentTrackPlaying = playList.get(currentTrack).isPlaying();
        if (shufflePlay && playing && !currentTrackPlaying) {
            shufflePlay();
        }
        else if (playing && !currentTrackPlaying) {
            next();
        }
    }

    /**
     * @return Returns the track count of the playlist.
     */
    public int getTrackCount() {
        return trackCount;
    }
}
