import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebutPanel extends JPanel implements ActionListener {

    private final Field field;
    private boolean activation;

    public DebutPanel(Field field){
        this.field = field;
        setPreferredSize(new Dimension(field.width, field.height));
        JButton b = new JButton("DEMARRER");
        b.setPreferredSize(new Dimension(125, 40));
        b.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.CENTER, 200, 325));
        add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image i = new ImageIcon("Fond2.jpg").getImage();
        g.drawImage(i, 0, 0, field.width, field.height, this);
        g.setFont(new Font("BAENO", Font.BOLD, 95));
        g.setColor(new Color(255, 45, 0));
        g.drawString("Bounce", 60, 190);
        g.drawString("Vice City", 25, 280);
        g.setFont(new Font("Namaku", Font.PLAIN, 15));
        g.setColor(new Color(0, 0, 0));
        g.drawString("Developpe par", 10, 15);
        g.drawString("Zakaria BOUAZZA", 10, 30);
        g.drawString("& Ata DALAN", 10, 45);
    }

    public void actionPerformed(ActionEvent e){
        activation = true;
    }

    public boolean getActivation(){
        return activation;
    }
}