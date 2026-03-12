import javax.swing.*;
import java.awt.*;

public class FinPanel extends JPanel{

    private final Field field;
    private final Axel axel;

    public FinPanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;
        setPreferredSize(new Dimension(field.width, field.height));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(23, 3, 13));
        g.setColor(new Color(255, 0, 0));
        Font police = new Font("BAENO", Font.BOLD, 75);
        g.setFont(police);
        g.drawString("WASTED", 90, 300);
        g.setColor(new Color(73, 142, 63));
        police = new Font("BAENO", Font.BOLD, 25);
        g.setFont(police);
        g.drawString("Niveau : " + field.getDifficulte(), 150, 340);
        g.drawString("Score : " + axel.getScore(), 150, 370);
    }
}