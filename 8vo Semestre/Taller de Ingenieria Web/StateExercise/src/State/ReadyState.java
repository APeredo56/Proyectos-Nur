package State;

public class ReadyState extends State {

    public ReadyState(Player player) {
        super(player);
    }
    @Override
    void clickLock() {
        player.setState(new LockState(player));
    }

    @Override
    void clickPlay() {
        player.setState(new PlayingState(player));
    }

    @Override
    void clickNext() {
        System.out.println("Primero debe reproducir una cancion");
    }

    @Override
    void clickPrevious() {
        System.out.println("Primero debe reproducir una cancion");
    }
}
