import java.util.ArrayList;
import java.util.List;

public class ConsoleDisplay extends Display {

    char[][] boardBuffer;

    char[][] newBuffer;

    private Position oldFoodPosition = new Position(1,1);

    private final List<Position> oldSnakePositions = new ArrayList<>();

    public ConsoleDisplay() {
        this.boardBuffer = new char[Border.getInstance().getWidth()][Border.getInstance().getHeight()];
        this.newBuffer = new char[Border.getInstance().getWidth()][Border.getInstance().getHeight()];
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
        for (int j = 0; j < Border.getInstance().getHeight(); j++) {
            for (int i = 0; i < Border.getInstance().getWidth(); i++) {
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
        oldSnakePositions.addAll(Snake.getInstance().getSnakePositions());
    }

    private void saveCurrFoodPositions(){
        oldFoodPosition.setX(Food.getInstance().getFoodPosition().getX());
        oldFoodPosition.setY(Food.getInstance().getFoodPosition().getY());
    }

    void initBuffer(char[][] buffer){
        for(int i = 0; i < Border.getInstance().getWidth(); ++i){
            for(int j = 0; j < Border.getInstance().getHeight(); ++j){
                buffer[i][j] = ' ';
            }
        }
    }

    private void initBoarders(char[][] Buffer) {
        for (int i = 0; i < Border.getInstance().getWidth(); ++i) {
            Buffer[i][0] = '*';
            Buffer[i][Border.getInstance().getHeight() - 1] = '*';
        }
        for (int i = 0; i < Border.getInstance().getHeight(); ++i) {
            Buffer[0][i] = '*';
            Buffer[Border.getInstance().getWidth() - 1][i] = '*';
        }
    }

    public void drawNewSnake() {
        for (Position pos : Snake.getInstance().getSnakePositions()) {
            newBuffer[pos.getX()][pos.getY()] = '0';
        }
    }

    private void drawNewFood() {
        newBuffer[Food.getInstance().getFoodPosition().getX()][Food.getInstance().getFoodPosition().getY()] = '@';
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void updateDisplay() {
        for (int j = 0; j < Border.getInstance().getHeight(); j++) {
            for (int i = 0; i < Border.getInstance().getWidth(); i++) {
                if (boardBuffer[i][j] != newBuffer[i][j]) {
                    boardBuffer[i][j] = newBuffer[i][j];
                    // Move the cursor to the corresponding position on the console
                    moveCursor(i,j); // move cursor to (i,j)
                    // Output the new character
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