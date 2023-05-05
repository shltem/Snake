public class Food {

    private Position foodPosition;
    private final Border border;

    private Food() {
        this.border = Border.getInstance();
        newFoodPosition();
    }



    public void newFoodPosition() {
        Position position = new Position((int) (Math.random() * border.getWidth()), (int) (Math.random() * border.getHeight()));
        while (Snake.getInstance().getSnakePositions().contains(position) || position.getX() < 1 ||
                position.getX() == border.getWidth() -1 || position.getY() < 1 ||
                position.getY() == border.getHeight() -1) {
            position.setX((int) (Math.random() *  border.getWidth()));
            position.setY((int) (Math.random() * border.getHeight()));
        }
        foodPosition = position;
    }

    public Position getFoodPosition() {
        return foodPosition;
    }

    private static class SingletonHolder {
        private static final Food INSTANCE = new Food();
    }

    public static Food getInstance() {
        return Food.SingletonHolder.INSTANCE;
    }
}
