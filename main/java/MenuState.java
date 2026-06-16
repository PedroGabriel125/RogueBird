import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState implements GameState {

    private final Game game;
    private int tick;
    private boolean blink = true;
    private int highScore;
    private Rectangle btnLoja = new Rectangle(148, 430, 100, 50);

    public MenuState(Game game)         { this(game, 0); }
    public MenuState(Game game, int hi) { this.game = game; this.highScore = hi; }

    @Override public void onEnter() { tick = 0; }
    @Override public void onExit()  {}

    @Override
    public void update() {
        if (++tick % 35 == 0) blink = !blink;

        Point p = game.mouse.getPosition();
        boolean click = game.mouse.isJustPressed(MouseHandler.LEFT);

        if (click && btnLoja.contains(p))
    {
        game.setState(new ShopState(game));
    }
    else if (game.keys.isJustPressed(KeyEvent.VK_SPACE)
          || game.keys.isJustPressed(KeyEvent.VK_ENTER)
          || (p != null &&
              click &&
              !btnLoja.contains(p)))
    {
        game.setState(new PlayState(game));
    }
}

    @Override
    public void render(Graphics2D g, float alpha) {
        int cx = game.width / 2;

        // ── Background ────────────────────────────────────────────────────
        g.setColor(new Color(80, 180, 240));
        g.fillRect(0, 0, game.width, game.height);
        // TODO: replace with background sprite

        // ── Ground ────────────────────────────────────────────────────────
        g.setColor(new Color(210, 170, 80));
        g.fillRect(0, game.height - PlayState.GROUND_H, game.width, PlayState.GROUND_H);
        // TODO: replace with ground sprite

        // ── Bird (preview square) ─────────────────────────────────────────
        g.setColor(new Color(255, 200, 0));
        g.fillRect(cx - PlayState.BIRD_W / 2, 240, PlayState.BIRD_W, PlayState.BIRD_H);
        // TODO: replace with bird sprite (idle frame)

        // ── título ─────────────────────────────────────────────────────────
        g.setFont(new Font("Arial", Font.BOLD, 44));
        FontMetrics fm = g.getFontMetrics();
        String title = "FLAPPY BIRD";
        g.setColor(new Color(80, 40, 0));
        g.drawString(title, cx - fm.stringWidth(title) / 2 + 3, 163);
        g.setColor(new Color(255, 220, 0));
        g.drawString(title, cx - fm.stringWidth(title) / 2, 160);

        //Shop button (temporary) -----------------------------------------------
           String botão = "LOJA";
            g.setFont(new Font("Arial" ,Font.PLAIN , 25));
            fm = g.getFontMetrics();
            g.setColor(new Color(255, 220, 0));
            g.fill(btnLoja);
            g.setColor(new Color(80, 40, 0));
            g.drawString(botão, 165, 460);

        
        // ── Prompt ────────────────────────────────────────────────────────
        if (blink) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            fm = g.getFontMetrics();
            String prompt = "SPACE / CLICK to start";
            g.setColor(Color.WHITE);
            g.drawString(prompt, cx - fm.stringWidth(prompt) / 2, 340);
        }

        // ── Best score ────────────────────────────────────────────────────
        if (highScore > 0) {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            fm = g.getFontMetrics();
            String hi = "Best: " + highScore;
            g.setColor(Color.WHITE);
            g.drawString(hi, cx - fm.stringWidth(hi) / 2, 370);
        }
            
    }
}
