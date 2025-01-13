package State;

public class PlayingState extends State {
    public PlayingState(Player player) {
        super(player);
        System.out.println("Reproduciendo: " + player.getSongs().get(player.getCurrentSongIndex()));
    }
    @Override
    void clickLock() {
        player.setState(new LockState(player));
    }

    @Override
    void clickPlay() {
        player.setState(new ReadyState(player));
    }

    @Override
    void clickNext() {
        player.nextSong();
    }

    @Override
    void clickPrevious() {
        player.previusSong();
    }
}
