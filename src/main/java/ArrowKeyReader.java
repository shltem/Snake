import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class ArrowKeyReader implements KeyListener, Runnable {

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
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Snake.getInstance().setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                Snake.getInstance().setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                Snake.getInstance().setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                Snake.getInstance().setDirection(Direction.LEFT);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        start();
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

