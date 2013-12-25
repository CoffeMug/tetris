package tetris;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tetris extends JFrame implements ActionListener {

    JLabel statusbar;
    JComboBox difficultyList;
    JButton startButton;
    JPanel startPanel;
    Board board;

    public Tetris() {

        statusbar = new JLabel(" 0");
        String[] difficultyLevels = {"easy", "normal", "hard", "crazy!"};

        difficultyList = new JComboBox(difficultyLevels);
        difficultyList.setSize(10, 10);
        difficultyList.setSelectedIndex(1);
        startPanel = new JPanel();
        JButton startButton = new JButton("start!");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);;
        add(statusbar, BorderLayout.SOUTH);
        startPanel.add(difficultyList);
        startPanel.add(startButton);
        add(startPanel, BorderLayout.NORTH);
        board = new Board(this);
        add(board);

        setSize(300, 600);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public JLabel getStatusBar() {
       return statusbar;
   }
   
   public JComboBox getComboBox(){
	   return difficultyList;
   }

   
    public static void main(String[] args) {

        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    } 
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand() == "start"){
    		
    		board.start();
    	}
    }

}
