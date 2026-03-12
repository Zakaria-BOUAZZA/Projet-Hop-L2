import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 10;
    private static final int AXEL_HEIGHT = 10;

    private final Axel axel;
    private final Field field;

    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;
        setPreferredSize(new Dimension(field.width, field.height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image i = new ImageIcon("Fond1.jpg").getImage();
        g.drawImage(i, 0, 0, field.width, field.height, this);
        g.setColor(new Color(0, 0, 0));
        g.fillOval(axel.getX(), axel.getY() - BLOCK_HEIGHT, AXEL_WIDTH, AXEL_HEIGHT);
        for(Block b : field.getEns()){
            g.fillRect(b.getX(), b.getY(), b.getWidth(), BLOCK_HEIGHT);
        }
        for(Piece p : field.getPieces()){
            g.setColor(new Color(252, 218, 3));
            g.fillOval(p.getX(), p.getY()-Piece.PIECE_HEIGHT, Piece.PIECE_WIDTH, Piece.PIECE_HEIGHT);
            g.setColor(new Color(0, 0, 0));
            g.drawOval(p.getX(), p.getY()-Piece.PIECE_HEIGHT, Piece.PIECE_WIDTH, Piece.PIECE_HEIGHT);
            g.setFont(new Font("BAENO", Font.BOLD, 14));
            g.drawString("$", p.getX()+(Piece.PIECE_WIDTH/2)-3, p.getY()+(Piece.PIECE_HEIGHT/2)-9);
        }
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 38){
            axel.setJumping(true);
        }
        if(e.getKeyCode() == 40){
            axel.setDiving(true);
        }
        if(e.getKeyCode() == 37){
            axel.setLeft(true);
        }
        if(e.getKeyCode() == 39){
            axel.setRight(true);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == 38){
            axel.setJumping(false);
        }
        if(e.getKeyCode() == 40){
            axel.setDiving(false);
        }
        if(e.getKeyCode() == 37){
            axel.setLeft(false);
        }
        if(e.getKeyCode() == 39){
            axel.setRight(false);
        }
    }

    public void keyTyped(KeyEvent e){}

    public static int centreGravite(){
        return AXEL_WIDTH/2;
    }
}