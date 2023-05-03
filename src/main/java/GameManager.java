import java.io.IOException;

public class GameManager {

    private boolean gameOver = false;

    private final Snake snake;

    private final Display display;

    private final Runnable arrowKeyReader;

    private final int SLEEP_TIME = 300;

    private Thread listenerThread;
    private final int BOARD_SIZE = 15;

    public GameManager() throws IOException {
        Border border = Border.getInstance();
        this.snake = Snake.getInstance();
        border.initBorder(BOARD_SIZE * 2, BOARD_SIZE);
        this.display = new ConsoleDisplay();
        this.arrowKeyReader = new ArrowKeyReader();
        listenerThread = new Thread(arrowKeyReader);
    }
    public void play() throws InterruptedException {
        listenerThread.start();
        while (!gameOver) {
            display.drewBoard();
            snake.move();
            if(snake.isCollision()){
                display.gameOver();
                gameOver = true;
                System.exit(0);
            }
            sleep();
        }
    }
    private void getSnakeSize() {
        System.out.println("Please select snake length: ");
    }

    private void sleep() throws InterruptedException {
        if(Snake.getInstance().getDirection() == Direction.UP ||
                Snake.getInstance().getDirection() == Direction.DOWN){
            Thread.sleep(SLEEP_TIME);
        }else{
            Thread.sleep((long)(SLEEP_TIME * (0.5))); // for matching speed between directions.
        }
    }
}
