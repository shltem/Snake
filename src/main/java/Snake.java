import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Snake {

    private final List<Position> snakeBody = new ArrayList<>();

    private Direction direction;

    Map<Direction, Function<Position, Position>> newHeadPositionMap = new HashMap<>();
    private Snake() {
        direction = Direction.RIGHT;
        snakeBody.add(new Position(1,1)); // up left position to start the game
        initNewHeadPositionMap();
    }


    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public void move() {
        Position head = getHead();
        Position newHead;
        newHead = newHeadPositionMap.get(direction).apply(head);
        snakeBody.add(0, newHead);
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

    private final Function<Position, Position> getRightNewPosition = (currHead)->{
        return new Position(currHead.getX() + 1, currHead.getY());
    };

    private final Function<Position, Position> getLeftNewPosition = (currHead)->{
        return new Position(currHead.getX() - 1, currHead.getY());
    };

    private final Function<Position, Position> getUpNewPosition = (currHead)->{
        return new Position(currHead.getX(), currHead.getY() - 1);
    };

    private final Function<Position, Position> getDownNewPosition = (currHead)->{
        return new Position(currHead.getX(), currHead.getY() + 1);
    };

    private void initNewHeadPositionMap() {
        newHeadPositionMap.put(Direction.UP, getUpNewPosition);
        newHeadPositionMap.put(Direction.DOWN, getDownNewPosition);
        newHeadPositionMap.put(Direction.RIGHT, getRightNewPosition);
        newHeadPositionMap.put(Direction.LEFT, getLeftNewPosition);
    }

}
