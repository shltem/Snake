public class Food {
    private Position foodPosition;
    private Food() {
        newFoodPosition();
    }

    public void newFoodPosition() {
        Position position = new Position((int) (Math.random() * Border.getInstance().getWidth()), (int) (Math.random() * Border.getInstance().getHeight()));
        while (Snake.getInstance().getSnakePositions().contains(position) || position.getX() < 1 ||
                position.getX() == Border.getInstance().getWidth() -1 || position.getY() < 1 ||
                position.getY() == Border.getInstance().getHeight() -1) {
            position.setX((int) (Math.random() *  Border.getInstance().getWidth()));
            position.setY((int) (Math.random() * Border.getInstance().getHeight()));
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
