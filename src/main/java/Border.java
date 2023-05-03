public class Border {

    private int height;

    private int width;

    public void initBorder(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private static class SingletonHolder {
        private static final Border INSTANCE = new Border();
    }

    public static Border getInstance() {
        return Border.SingletonHolder.INSTANCE;
    }
}
