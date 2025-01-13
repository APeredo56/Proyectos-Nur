package State;

public class LockState extends State {
    public LockState(Player player) {
        super(player);
    }

    @Override
    void clickLock() {
        player.setState(new ReadyState(player));
    }

    @Override
    void clickPlay() {
        System.out.println("Reproductor Bloqueado");
    }

    @Override
    void clickNext() {
        System.out.println("Reproductor Bloqueado");
    }

    @Override
    void clickPrevious() {
        System.out.println("Reproductor Bloqueado");
    }
}
