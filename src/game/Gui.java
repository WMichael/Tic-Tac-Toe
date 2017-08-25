package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Gui {

	private static JFrame frmTicTacToe;
	private static JLabel lblCurrentTurn;
	private static JLabel lblXWins;
	private static JLabel lblOWins;
	private static JLabel lblDraws;
	private Image imgX = new ImageIcon(this.getClass().getResource("/img/playX.png")).getImage();
	private Image imgO = new ImageIcon(this.getClass().getResource("/img/playO.png")).getImage();
	private Game game = new Game();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmTicTacToe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmTicTacToe = new JFrame();
		frmTicTacToe.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/img/tictactoeIcon.png")));
		frmTicTacToe.setTitle("Tic Tac Toe");
		frmTicTacToe.setResizable(false);
		frmTicTacToe.setBounds(100, 100, 834, 550);
		frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicTacToe.setLocationRelativeTo(null);
		frmTicTacToe.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Tic Tac Toe");
		lblTitle.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40));
		lblTitle.setBounds(34, 28, 247, 56);
		frmTicTacToe.getContentPane().add(lblTitle);
		
		lblXWins = new JLabel("Number of X Wins: " + game.getNoOfXWins());
		lblXWins.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		lblXWins.setBounds(406, 172, 247, 56);
		frmTicTacToe.getContentPane().add(lblXWins);
		
		lblOWins = new JLabel("Number of O Wins: " + game.getNoOfOWins());
		lblOWins.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		lblOWins.setBounds(406, 209, 247, 56);
		frmTicTacToe.getContentPane().add(lblOWins);
		
		lblDraws = new JLabel("Number of Draws: 0");
		lblDraws.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		lblDraws.setBounds(406, 250, 247, 56);
		frmTicTacToe.getContentPane().add(lblDraws);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 82, 304, 2);
		frmTicTacToe.getContentPane().add(separator);
		
		JLabel lbl11 = new JLabel("");
		lbl11.setBounds(34, 121, 109, 107);
		frmTicTacToe.getContentPane().add(lbl11);
		
		JLabel lbl12 = new JLabel("");
		lbl12.setBounds(144, 121, 109, 107);
		frmTicTacToe.getContentPane().add(lbl12);
		
		JLabel lbl13 = new JLabel("");
		lbl13.setBounds(255, 121, 109, 107);
		frmTicTacToe.getContentPane().add(lbl13);
		
		JLabel lbl21 = new JLabel("");
		lbl21.setBounds(34, 232, 109, 107);
		frmTicTacToe.getContentPane().add(lbl21);
		
		JLabel lbl22 = new JLabel("");
		lbl22.setBounds(144, 232, 109, 107);
		frmTicTacToe.getContentPane().add(lbl22);
		
		JLabel lbl23 = new JLabel("");
		lbl23.setBounds(255, 232, 109, 107);
		frmTicTacToe.getContentPane().add(lbl23);
		
		JLabel lbl31 = new JLabel("");
		lbl31.setBounds(34, 344, 109, 107);
		frmTicTacToe.getContentPane().add(lbl31);
		
		JLabel lbl32 = new JLabel("");
		lbl32.setBounds(144, 344, 109, 107);
		frmTicTacToe.getContentPane().add(lbl32);
		
		JLabel lbl33 = new JLabel("");
		lbl33.setBounds(255, 344, 109, 107);
		frmTicTacToe.getContentPane().add(lbl33);
		
		lblCurrentTurn = new JLabel("It's X's Turn");
		lblCurrentTurn.setForeground(Color.RED);
		lblCurrentTurn.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		lblCurrentTurn.setBounds(406, 103, 196, 56);
		frmTicTacToe.getContentPane().add(lblCurrentTurn);
		
		// Adds a mouse listener for each label on the board.
		JLabel[][] labelArray = {{lbl11,lbl12,lbl13},{lbl21,lbl22,lbl23},{lbl31,lbl32,lbl33}}; 
		Boolean[][] booleanArray = {{true,true,true},{true,true,true},{true,true,true}}; //Array used to determine whether a tile can be pressed.
		for(int i=0; i< labelArray.length; i++) {
			for(int y=0; y< labelArray.length; y++) {
				JLabel label = labelArray[i][y];
				int col = i;
				int row = y;
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(booleanArray[col][row]) {
							booleanArray[col][row] = false; // Tile is set to false after being pressed.
							turnTaken(labelArray,booleanArray,label,col,row); // Turn is registered.
						}
						
					}
				});
			}
		}
		
		// Reset game
		JButton btnResetGame = new JButton("New Game");
		btnResetGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset(labelArray,booleanArray);
			}
		});
		btnResetGame.setBounds(409, 407, 145, 44);
		frmTicTacToe.getContentPane().add(btnResetGame);
		
		// Game Board
		JLabel lblBoard = new JLabel("");
		lblBoard.setIcon(new ImageIcon(Gui.class.getResource("/img/board.gif"))); 
		lblBoard.setBounds(34, 121, 330, 330);
		frmTicTacToe.getContentPane().add(lblBoard);
		
		// Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(566, 407, 145, 44);
		frmTicTacToe.getContentPane().add(btnExit);
		

		
	}
	
	
	//Game related methods 
	// turnTaken after each tile press.
	private void turnTaken(JLabel[][] labelArray,Boolean[][] booleanArray, JLabel label,int col, int row) {
		// Change Icon to either X or O 
		if (game.getCurrentTurn() == true) {
			label.setIcon(new ImageIcon(imgX));
		}
		else {
			label.setIcon(new ImageIcon(imgO));
		}
		
		
		
		// Call turnPlayed to change current player and update board array.
		boolean currentTurn = game.getCurrentTurn();
		String result = game.turnPlayed(col,row,currentTurn);
			if(result != "") { // If result is not "". 
				if (result == "xWin") {
					lblXWins.setText("Number of X Wins: " + game.getNoOfXWins());
				}
				else if(result == "oWin") { 
					lblOWins.setText("Number of O Wins: " + game.getNoOfOWins());
				}
				else {
					lblDraws.setText("Number of Draws: " + game.getNoOfDraws());
				}
				
				// Clear Board
				for(int i=0; i< labelArray.length; i++) {
					for (int y=0; y< labelArray.length; y++) {
						labelArray[i][y].setIcon(null);	
					}
				}
				
				// Reset boolean array to true.
				for(int i=0; i< booleanArray.length; i++) {
					for (int y=0; y< booleanArray.length; y++) {
						booleanArray[i][y] = true;
					}
				}
				
			
			
			}
		// Find current turn from game state to change Current player label. 
		if (game.getCurrentTurn() == true) {
			lblCurrentTurn.setText("It's X's Turn");
		}
		else {
			lblCurrentTurn.setText("It's O's Turn");
		}
	}
	

	// Reset game state and board
	private void reset(JLabel[][] labelArray, Boolean[][] booleanArray) {

		// Clear Board
		for(int i=0; i< labelArray.length; i++) {
			for (int y=0; y< labelArray.length; y++) {
				labelArray[i][y].setIcon(null);	
			}
		}
		
		// Reset boolean array to true.
		for(int i=0; i< booleanArray.length; i++) {
			for (int y=0; y< booleanArray.length; y++) {
				booleanArray[i][y] = true;
			}
		}
		
		// Call game state reset method
		game.reset();
		
		// Find current player turn
		if (game.getCurrentTurn() == true) {
			lblCurrentTurn.setText("It's X's Turn");
		}
		else {
			lblCurrentTurn.setText("It's O's Turn");
		}
		
		lblXWins.setText("Number of X Wins: 0");
		lblOWins.setText("Number of O Wins: 0");
		lblDraws.setText("Number of Draws: 0");
	}
}
