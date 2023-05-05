import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class ArrowKeyReader implements KeyListener, Runnable {

    private final Map<Integer, Runnable> setDirectionMap = new HashMap<>();

    public ArrowKeyReader() {
        initSetDirectionMap();
    }
    public void start() {
        JFrame frame = new JFrame();
        frame.addKeyListener(this);
        frame.setVisible(true);
        try {
            while (true) {
                Thread.sleep(2); // Pausing to prevent CPU use
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { // This function is called by the frame object that listens to keyboard input.
        setDirectionMap.get(e.getKeyCode()).run();
    }

    @Override
    public void run() {
        start();
    }

    private final Runnable setDirectionUp = ()-> Snake.getInstance().setDirection(Direction.UP);
    private final Runnable setDirectionDown = ()-> Snake.getInstance().setDirection(Direction.DOWN);
    private final Runnable setDirectionRight = ()-> Snake.getInstance().setDirection(Direction.RIGHT);
    private final Runnable setDirectionLeft = ()-> Snake.getInstance().setDirection(Direction.LEFT);

    private void initSetDirectionMap() {
        setDirectionMap.put(KeyEvent.VK_UP, setDirectionUp);
        setDirectionMap.put(KeyEvent.VK_DOWN, setDirectionDown);
        setDirectionMap.put(KeyEvent.VK_RIGHT, setDirectionRight);
        setDirectionMap.put(KeyEvent.VK_LEFT, setDirectionLeft);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // We don't need to handle this event
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // We don't need to handle this event
    }

}
