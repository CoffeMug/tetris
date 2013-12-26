package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import tetris.Shape.Tetrominoes;

public class GuidePanel extends JPanel {

    JComboBox difficultyList;
    JButton startButton;
    int squareWidth = 25;
    int squareHeight = 25;
    int PanelTop = 20;
    Shape nexPiece;  


    final int PanelHeight = 20;

    int nexX = 3;
    int nexY = 3;


    public GuidePanel(Tetris parent) {
        String[] difficultyLevels = {"easy", "normal", "hard", "crazy!"};
        difficultyList = new JComboBox(difficultyLevels);
        difficultyList.setSize(10, 10);
        difficultyList.setSelectedIndex(1);   
		this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(difficultyList);
        startButton = new JButton("start!");
        this.add(startButton);
        nexPiece = new Shape();
	}

	public JComboBox getDifficultyList(){
        return difficultyList;
	}

	public void paint (Graphics g) {
		super.paint(g);
		Dimension size = getSize();
		if (nexPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = nexX + nexPiece.x(i);
                int y = nexY - nexPiece.y(i);
                drawSquare(g, 0 + x * squareWidth,
                           PanelTop + (PanelHeight - y - 1) * squareHeight,
                           nexPiece.getShape());
            }
        }

	
	}

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                         x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1,
                         x + squareWidth - 1, y + 1);

    }

}
