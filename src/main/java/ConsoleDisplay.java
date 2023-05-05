import java.util.ArrayList;
import java.util.List;

public class ConsoleDisplay extends Display {

    char[][] boardBuffer;
    //private final Snake snake;
    char[][] newBuffer;
    //private final Border border;

    //private final Food food;
    private Position oldFoodPosition = new Position(1,1);

    private final List<Position> oldSnakePositions = new ArrayList<>();

    public ConsoleDisplay() {
        this.border = Border.getInstance();
        this.snake = Snake.getInstance();
        this.food = Food.getInstance();
        this.boardBuffer = new char[border.getWidth()][border.getHeight()];
        this.newBuffer = new char[border.getWidth()][border.getHeight()];
        hideCursor();
        initBuffer(boardBuffer);
        initBuffer(newBuffer);
        initBoarders(newBuffer);
        drewBoard();
        clearScreen();
        printInitDisplay();
        updateDisplay();
    }

    private void printInitDisplay() {
        for (int j = 0; j < border.getHeight(); j++) {
            for (int i = 0; i < border.getWidth(); i++) {
                System.out.print(newBuffer[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    void drewBoard() {
        eraseOldBoard();
        drewNewBoard();
        updateDisplay();
        saveCurrPositions();
    }

    private void eraseOldBoard() {
        eraseOldSnake();
        eraseOldFood();
    }

    private void eraseOldFood() {
        if(oldFoodPosition != null){
            newBuffer[oldFoodPosition.getX()][oldFoodPosition.getY()] = ' ';
        }
    }

    private void eraseOldSnake() {
            for (Position pos : oldSnakePositions) {
                newBuffer[pos.getX()][pos.getY()] = ' ';
            }
    }

    private void drewNewBoard() {
        drawNewSnake();
        drawNewFood();
    }

    private void saveCurrPositions() {
        saveCurrSnakePositions();
        saveCurrFoodPositions();
    }

    private void saveCurrSnakePositions(){
        oldSnakePositions.clear();
        oldSnakePositions.addAll(snake.getSnakePositions());
    }

    private void saveCurrFoodPositions(){
        oldFoodPosition.setX(food.getFoodPosition().getX());
        oldFoodPosition.setY(food.getFoodPosition().getY());
    }

    void initBuffer(char[][] buffer){
        for(int i = 0; i < border.getWidth(); ++i){
            for(int j = 0; j < border.getHeight(); ++j){
                buffer[i][j] = ' ';
            }
        }
    }

    private void initBoarders(char[][] Buffer) {
        for (int i = 0; i < border.getWidth(); ++i) {
            Buffer[i][0] = '*';
            Buffer[i][border.getHeight() - 1] = '*';
        }
        for (int i = 0; i < border.getHeight(); ++i) {
            Buffer[0][i] = '*';
            Buffer[border.getWidth() - 1][i] = '*';
        }
    }

    public void drawNewSnake() {
        for (Position pos : snake.getSnakePositions()) {
            newBuffer[pos.getX()][pos.getY()] = '0';
        }
    }

    private void drawNewFood() {
        newBuffer[food.getFoodPosition().getX()][food.getFoodPosition().getY()] = '@';
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void updateDisplay() {
        for (int j = 0; j < border.getHeight(); j++) {
            for (int i = 0; i < border.getWidth(); i++) {
                if (boardBuffer[i][j] != newBuffer[i][j]) {
                    boardBuffer[i][j] = newBuffer[i][j];
                    moveCursor(i,j);
                    System.out.print(newBuffer[i][j]);
                    System.out.flush();
                }
            }
        }
    }

    @Override
    public void gameOver() {
        clearScreen();
        moveCursor(1,1);
        System.out.println("*******GAME OVER********");
        showCursor();
    }

    public void moveCursor(int i, int j) {
        System.out.print("\033[" + (j +1) + ";" + (i +1) + "H");
    }

    public void hideCursor() {
        System.out.print("\033[?25l");
    }

    public void showCursor() {
        System.out.print("\033[?25h");
    }
}