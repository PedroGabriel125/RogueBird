import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class ShopState implements GameState{

    private final Game game;
    private boolean blink = true;
    private int tick; 

    //── Itens ────────────────────────────────────────────────────
    private Rectangle item1;
    private Rectangle item2;
    private Rectangle item3;

    
    public ShopState(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        if (++tick % 35 == 0) blink = !blink;
        if (game.keys.isJustPressed(KeyEvent.VK_ESCAPE))
            game.setState(new MenuState(game));
    }

    @Override
    public void render(Graphics2D g, float alpha) {
        int cx = game.width / 2;
        // ── Background ────────────────────────────────────────────────────
        g.setColor(new Color(80, 180, 240));
        g.fillRect(0, 0, game.width, game.height);
        
         //──Shop title (temporary)────────────────────────────────────────────────────
        g.setColor(new Color(80, 180, 240));
        g.fillRect(0, 0, game.width, game.height);
        // TODO: replace with background sprite

     
        g.setColor(new Color(210, 170, 80));
        g.fillRect(0, game.height - PlayState.GROUND_H, game.width, PlayState.GROUND_H);
        // TODO: replace with ground sprite

        String title = "LOJA";
        g.setFont(new Font("Arial", Font.BOLD, 44));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(new Color(80, 40, 0));
        g.drawString(title, cx - fm.stringWidth(title) / 2 + 3, 63);
        g.setColor(new Color(255, 220, 0));
        g.drawString(title, cx - fm.stringWidth(title) / 2, 60);

        // --Back button text (temporary) --
        if(blink) {
            g.setFont(new Font("Arial" ,Font.PLAIN , 15));
            g.setColor(Color.WHITE);
            g.drawString("ESC to back", 20, 590);
        }
    
    }

    @Override
    public void onExit(){

    }

    @Override 
    public void onEnter(){
    tick = 0;
    }
    
}
