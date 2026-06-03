import java.awt.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public static final int LEFT = 1, MIDDLE = 2, RIGHT = 3;

    private volatile int x, y;
    private final boolean[] held         = new boolean[6];
    private final boolean[] justPressed  = new boolean[6];
    private final boolean[] justReleased = new boolean[6];

    @Override public synchronized void mousePressed(MouseEvent e) {
        int b = e.getButton();
        if (b < held.length) { held[b] = true; justPressed[b] = true; }
    }
    @Override public synchronized void mouseReleased(MouseEvent e) {
        int b = e.getButton();
        if (b < held.length) { held[b] = false; justReleased[b] = true; }
    }
    public synchronized Point getPosition() { return new Point(x, y); }
    @Override public void mouseMoved(MouseEvent e)   { x = e.getX(); y = e.getY(); }
    @Override public void mouseDragged(MouseEvent e) { x = e.getX(); y = e.getY(); }
    @Override public void mouseClicked(MouseEvent e)  {}
    @Override public void mouseEntered(MouseEvent e)  {}
    @Override public void mouseExited(MouseEvent e)   {}

    public int getX() { return x; }
    public int getY() { return y; }

    public synchronized boolean isHeld(int b)        { return b < held.length && held[b]; }
    public synchronized boolean isJustPressed(int b) {
        if (b >= held.length || !justPressed[b]) return false;
        justPressed[b] = false; return true;
    }
    public synchronized boolean isJustReleased(int b) {
        if (b >= held.length || !justReleased[b]) return false;
        justReleased[b] = false; return true;
    }
}
