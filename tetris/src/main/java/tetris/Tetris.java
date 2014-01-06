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
    GuidePanel guidePanel;
    Board board;

    public Tetris() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        guidePanel = new GuidePanel(this);
		guidePanel.startButton.setActionCommand("start");
        guidePanel.startButton.addActionListener(this);

        add(guidePanel, BorderLayout.WEST);
        initBoard();
	}

	public JLabel getStatusBar() {
		return statusbar;
	}
   
	public JComboBox getComboBox(){
		return difficultyList;
	}

    private void initBoard(){
        board = new Board(this);
        setSize(500, 1000);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(board);
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