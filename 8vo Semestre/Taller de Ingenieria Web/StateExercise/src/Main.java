import State.Player;
import State.ReadyState;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.setState(new ReadyState(player));
        player.clickPlay();
        player.clickPrevious();

        player.clickLock();
        player.clickPlay();
    }
}