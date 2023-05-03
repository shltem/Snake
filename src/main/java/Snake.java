import java.util.ArrayList;
import java.util.List;
public class Snake {
    private final List<Position> snakeBody = new ArrayList<>();
    private Direction direction;

    private Snake() {
        direction = Direction.RIGHT;
        snakeBody.add(new Position(1,1)); // up left position to start the game
    }


    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public void move() {
        Position head = getHead();
        Position newHead;
        switch (direction) {
            case UP:
                newHead = new Position(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Position(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Position(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Position(head.getX() + 1, head.getY());
                break;
            default:
                return;
        }

        snakeBody.add(0, newHead); // add new head at the beginning
        if(newHead.equals(Food.getInstance().getFoodPosition())){
            Food.getInstance().newFoodPosition();
        }else{
            snakeBody.remove(snakeBody.size() - 1); // remove tail
        }
    }
    private Position getHead() {
        return snakeBody.get(0);
    }

    public List<Position> getSnakePositions() {
        return snakeBody;
    }

    public boolean isCollision(){
        return isSelfCollision() || isBorderCollision();
    }
    private boolean isSelfCollision() {
            for(int i = 1; i < snakeBody.size(); ++i){
                if(snakeBody.get(i).equals(snakeBody.get(0))){
                    return true;
                }
            }
        return false;
    }
    private boolean isBorderCollision() {
        return getHead().getX() <= 0 || getHead().getX() >= Border.getInstance().getWidth() -1||
                getHead().getY() <= 0 || getHead().getY() >= Border.getInstance().getHeight() -1;
    }
    private static class SingletonHolder {
        private static final Snake INSTANCE = new Snake();
    }
    public static Snake getInstance() {
        return Snake.SingletonHolder.INSTANCE;
    }
    public Direction getDirection() {
        return direction;
    }
}
