import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToeGame {

    private static final PlayerPanel players = new PlayerPanel();
    private static final GameBoard board = new GameBoard(players);
    
    public static JFrame createGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setSize(700, 700);
        f.add(board, BorderLayout.CENTER);
        f.add(players, BorderLayout.NORTH);
        
        JButton newGame = new JButton("Start New Game");
        newGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                board.newGame();
            }
            });
        
        f.add(newGame, BorderLayout.SOUTH);
        String name = JOptionPane.showInputDialog("Please Enter your name", "Player 1");
        players.changePlOneName(name);
        name = JOptionPane.showInputDialog("Please Enter your name", "Player 2");
        players.changePlTwoName(name);
        return f;
    }
    
    public static void main(String[] args) {
        JFrame f = createGUI();
        f.setVisible(true);
        
    }

}
