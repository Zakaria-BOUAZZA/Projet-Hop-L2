import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Hop {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int DELAY = 40;

    private final JFrame frame;
    private final Field field;
    private final Axel axel;
    private Timer timer;
    private GamePanel gamePanel;
    private BarrePanel barrePanel;
    private FinPanel finPanel;
    private DebutPanel debutPanel;
    private boolean play;

    public Hop() {
        this.field = new Field(WIDTH, HEIGHT);
        this.axel = new Axel(field, WIDTH/2, HEIGHT - Field.START_ALTITUDE);
        this.gamePanel = new GamePanel(field, axel);

        this.frame = new JFrame("Hop!");
        debutPanel = new DebutPanel(field);
        frame.add(debutPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(gamePanel);
        barrePanel = new BarrePanel(axel);
        finPanel = new FinPanel(field, axel);
    }

    public void round() {
        field.update();
        axel.update();
        frame.repaint();
    }

    public boolean over() {
        return axel.getY() > HEIGHT;
    }

    public void transition(){
        frame.getContentPane().removeAll();
        frame.add(gamePanel);
        frame.add(barrePanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.requestFocusInWindow();
        play = true;
    }

    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            if(game.debutPanel.getActivation() && !game.play){
                game.transition();
            }
            if(game.play){
                game.round();
            }
            if (game.over()) {
                game.timer.stop();
                game.frame.remove(game.gamePanel);
                game.frame.remove(game.barrePanel);
                game.frame.add(game.finPanel);
                game.frame.revalidate();
                Son.lanceur();
            }
        });
        game.timer.start();
    }
}
