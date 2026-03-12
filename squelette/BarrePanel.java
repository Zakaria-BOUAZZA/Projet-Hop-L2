import javax.swing.*;
import java.awt.*;

public class BarrePanel extends JPanel {

    private final Axel axel;

    public BarrePanel(Axel axel) {
        this.axel = axel;
        setPreferredSize(new Dimension(Hop.WIDTH, 20));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(9, 246, 249));
        g.setColor(new Color(0, 0, 0, 255));
        Font police = new Font("BAENO", Font.PLAIN, 14);
        g.setFont(police);
        g.drawString("Score : " + axel.getScore(), 5, 15);
        g.drawString("Difficulte : " + axel.getField().getDifficulte(), 310, 15);
    }
}