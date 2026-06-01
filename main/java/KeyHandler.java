import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler extends KeyAdapter {

    private final Set<Integer> down          = new HashSet<>();
    private final Set<Integer> justPressed   = new HashSet<>();
    private final Set<Integer> justReleased  = new HashSet<>();
    private final Set<Integer> pressEdge     = new HashSet<>();
    private final Set<Integer> releaseEdge   = new HashSet<>();

    @Override public synchronized void keyPressed(KeyEvent e) {
        if (down.add(e.getKeyCode())) pressEdge.add(e.getKeyCode());
    }
    @Override public synchronized void keyReleased(KeyEvent e) {
        down.remove(e.getKeyCode());
        releaseEdge.add(e.getKeyCode());
    }

    public synchronized void update() {
        justPressed .clear(); justPressed .addAll(pressEdge);   pressEdge  .clear();
        justReleased.clear(); justReleased.addAll(releaseEdge); releaseEdge.clear();
    }

    public synchronized boolean isHeld(int k)        { return down.contains(k);         }
    public synchronized boolean isJustPressed(int k) { return justPressed.contains(k);  }
    public synchronized boolean isJustReleased(int k){ return justReleased.contains(k); }
}
