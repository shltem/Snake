package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//public class KeyListener implements Runnable{
//    private final Snake snake;
//    private static final int ESCAPE_CHAR = 27;
//    private static final int CSI_CHAR = 91;
//    private static final int UP_ARROW_CODE = 65;
//    private static final int DOWN_ARROW_CODE = 66;
//    private static final int RIGHT_ARROW_CODE = 67;
//    private static final int LEFT_ARROW_CODE = 68;
//    private static final int QUIT_CODE = 113;
//    private Direction direction;  // 0=UP, 1=RIGHT, 2=DOWN, 3=LEFT
//    private final Console console;


    public class ArrowKeyReader implements KeyListener, Runnable {

        public ArrowKeyReader() {
        }

        public void start() {
            System.out.println("Press arrow keys (up/down/left/right) to see output.");
            try {
                while (true) {
                    Thread.sleep(100); // Pause for a bit to prevent consuming too much CPU time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // We don't need to handle this event
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    System.out.println("Up arrow pressed");
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("Down arrow pressed");
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Right arrow pressed");
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("Left arrow pressed");
                    break;
                default:
                    // Handle other keys if necessary
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // We don't need to handle this event
        }

        @Override
        public void run() {
            keyPressed();
        }
    }


