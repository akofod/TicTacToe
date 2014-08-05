import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameBoard extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
    
    static final char BLANK = ' ', O = 'O', X = 'X';
    
    private char pos[] = {
            BLANK, BLANK, BLANK,
            BLANK, BLANK, BLANK,
            BLANK, BLANK, BLANK};
    
    private final char PLAYER_ONE = 'O';
    private final char PLAYER_TWO = 'X';
    private char currentPlayer;
    PlayerPanel playerPanel;
    private boolean gameOver;
    
    public GameBoard(PlayerPanel plPan) {
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(3, 3));
        playerPanel = plPan;
        addMouseListener(this);
        currentPlayer = PLAYER_ONE;
        gameOver = false;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(0, 0, w, h));
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(0, h/3, w, h/3));
        g2d.draw(new Line2D.Double(0, h*2/3, w, h*2/3));
        g2d.draw(new Line2D.Double(w/3, 0, w/3, h));
        g2d.draw(new Line2D.Double(w*2/3, 0, w*2/3, h));
        
        for (int i = 0; i < 9; ++i) {
            double xpos=(i % 3 + 0.5) * w / 3.0;
            double ypos=(i / 3 + 0.5) * h / 3.0;
            double xr = w / 8.0;
            double yr = h / 8.0;
            if (pos[i] == O) {
              g2d.setPaint(Color.BLUE);
              g2d.draw(new Ellipse2D.Double(xpos - xr, ypos - yr, xr * 2, yr * 2));
            }
            else if (pos[i]==X) {
              g2d.setPaint(Color.RED);
              g2d.draw(new Line2D.Double(xpos - xr, ypos - yr, xpos + xr, ypos + yr));
              g2d.draw(new Line2D.Double(xpos - xr, ypos + yr, xpos + xr, ypos - yr));
            }
        }
    }
    
    public boolean checkForWin(char player) {
        if ((pos[0] == player && pos[1] == player && pos[2] == player) ||
            (pos[3] == player && pos[4] == player && pos[5] == player) ||
            (pos[6] == player && pos[7] == player && pos[8] == player) ||
            (pos[0] == player && pos[3] == player && pos[6] == player) ||
            (pos[1] == player && pos[4] == player && pos[7] == player) ||
            (pos[2] == player && pos[5] == player && pos[8] == player) ||
            (pos[0] == player && pos[4] == player && pos[8] == player) ||
            (pos[2] == player && pos[4] == player && pos[6] == player)) {
            
            playerPanel.addWin(player);
            gameOver = true;
            String winner = playerPanel.getPlayerName(player);
            JOptionPane.showMessageDialog(this, winner + " Wins!!!");
            return true;
        }
        return false;
    }
    
    public void newGame() {
        playerPanel.updateScore();
        gameOver = false;
        currentPlayer = PLAYER_ONE;
        for (int i = 0; i < 9; i++) {
            pos[i] = BLANK;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Find location of click
        if (!gameOver) {
            int xpos = e.getX() * 3 / getWidth();
            int ypos = e.getY() * 3 / getHeight();
            int position = xpos + 3 * ypos;
        
            if (position >= 0 && position < 9 && pos[position] == BLANK) {
                pos[position] = currentPlayer;
            }
        
            repaint();
        
            if (!checkForWin(currentPlayer)) { 
                if (currentPlayer == PLAYER_ONE) {
                  currentPlayer = PLAYER_TWO;
                }
                else { 
                  currentPlayer = PLAYER_ONE;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    
}
