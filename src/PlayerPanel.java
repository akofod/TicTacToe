import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int plOneWins = 0;
    private int plTwoWins = 0;
    private String plOneName;
    private String plTwoName;
    JLabel score;
    JLabel player1;
    JLabel player2;
    
    /**
     * Constructor for PlayerPanel
     */
    public PlayerPanel() {
        this.setLayout(new GridLayout(1, 3));
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setAlignmentX(CENTER_ALIGNMENT);
        player1 = new JLabel("Player 1: ");
        player2 = new JLabel("Player 2: ");
        score = new JLabel("0 - 0");        
        this.add(player1);
        this.add(score);
        this.add(player2);
    }
    
    /**
     * Change the name of player one
     * @param name
     */
    public void changePlOneName(String name){
        this.plOneName = name;
        setPlOneName();
    }
    
    /**
     * Change the name of player two
     * @param name
     */
    public void changePlTwoName(String name){
        this.plTwoName = name;
        setPlTwoName();
    }
    
    /**
     * Set the name of player one in the header
     */
    public void setPlOneName() {
        player1.setText("Player 1: " + plOneName);
    }
    
    /**
     * Set the name of player two in the header
     */
    public void setPlTwoName() {
        player2.setText("Player 2: " + plTwoName);
    }
    
    /**
     * Updates the score display in the header
     */
    public void updateScore() {
        score.setText(plOneWins + " - " + plTwoWins);
    }
    
    /**
     * Adds a win to the player's count
     * @param player
     */
    public void addWin(char player) {
        if (player == 'O') {
            plOneWins++;
        }
        if (player == 'X') {
            plTwoWins++;
        }
        updateScore();
    }
    
    /**
     * Gets the player's name for the given char
     * @param player
     * @return the player's name
     */
    public String getPlayerName(char player) {
        if (player == 'O') {
            return plOneName;
        }
        else {
            return plTwoName;
        }
    }
}
