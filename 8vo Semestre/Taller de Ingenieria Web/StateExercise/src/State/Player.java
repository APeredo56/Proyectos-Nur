package State;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private State state;
    private List<String> songs;
    private int currentSongIndex = 0;

    public Player() {
        this.songs = new ArrayList<>();
        this.songs.add("Wake me up");
        this.songs.add("Waiting for love");
        this.songs.add("The nights");
        this.songs.add("Hey brother");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<String> getSongs() {
        return songs;
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void clickLock() {
        state.clickLock();
    }

    public void clickPlay() {
        state.clickPlay();
    }

    public void clickNext() {
        state.clickNext();
    }

    public void clickPrevious() {
        state.clickPrevious();
    }

    public void nextSong() {
        if (currentSongIndex == songs.size() - 1) {
            currentSongIndex = 0;
        } else {
            currentSongIndex++;
        }
        System.out.println("Reproduciendo: " + songs.get(currentSongIndex));
    }

    public void previusSong() {
        if (currentSongIndex == 0) {
            currentSongIndex = songs.size() - 1;
        } else {
            currentSongIndex--;
        }
        System.out.println("Reproduciendo: " + songs.get(currentSongIndex));
    }

}
