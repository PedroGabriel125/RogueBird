import java.awt.Graphics2D;

public interface GameState {
    void onEnter();
    void update();
    void render(Graphics2D g, float alpha);
    void onExit();
}
