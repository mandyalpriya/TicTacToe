package com.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TicTacToe extends JFrame{
	private static final long serialVersionUID = -5501201368843714777L;
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu menuTwo;
	private JMenuItem quit;
	private JMenuItem DarkMode;
	private JMenuItem LightMode;
	private JMenuItem SageMode;
	private JMenuItem subMenu;
	private JMenuItem Player1;
	private JMenuItem Player2;
	int x;
	int y;
	int l;
	int m;
	int[] array= {0,0};
	int rand1;
	int rand2;
	int moves=9;
	int playerNumber;


	public TicTacToe() {
		super();
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle( 2);
		Image icon = Toolkit.getDefaultToolkit().getImage("D:\\shrek.jpg");    
		setIconImage(icon); 
		pane=getContentPane();
		pane.setLayout(new GridLayout(3,3));
		setTitle("Tic Tac Toe");
		setSize(300,300);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		currentPlayer="X";
		board= new JButton[3][3];
		hasWinner=false;
		initializeMenuBar();
		initializeBoard();
	}


	public void initializeMenuBar() {
		menuBar= new JMenuBar();
		menu= new JMenu("File");
		menuTwo= new JMenu("Game Mode");
		DarkMode= new JMenuItem("Dark Mode");
		DarkMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setDarkMode();
			}


		});


		LightMode= new JMenuItem("Light Mode");
		LightMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLightMode();
			}

		});

		SageMode= new JMenuItem("Sage Mode");
		SageMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSageMode();
			}


		});


		subMenu=new JMenu("NewGame");
		Player1= new JMenuItem("Player Number: One");
		Player2= new JMenuItem("Player Number: Two");
		subMenu.add(Player1);
		subMenu.add(Player2);

		Player2.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				playerNumber=2;
				resetBoard();

			}
		});

		quit=new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		menu.add(subMenu);
		menu.add(quit);
		menuTwo.add(DarkMode);
		menuTwo.add(LightMode);
		menuTwo.add(SageMode);
		menuBar.add(menu);
		menuBar.add(menuTwo);
		setJMenuBar(menuBar);

	}

	private void setDarkMode() {
		getRootPane().setWindowDecorationStyle(7);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setBackground(new Color(100,100,100));
				menuBar.setBackground(Color.lightGray);
				menu.setBackground(Color.lightGray);
				menuTwo.setBackground(Color.lightGray);
				DarkMode.setBackground(Color.lightGray);
				LightMode.setBackground(Color.lightGray);
				SageMode.setBackground(Color.lightGray);
				quit.setBackground(Color.lightGray);
				subMenu.setBackground(Color.lightGray);
				Player1.setBackground(Color.lightGray);
				Player2.setBackground(Color.lightGray);
			}
		}
	}
	private void setSageMode() {
		getRootPane().setWindowDecorationStyle(8);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setBackground(new Color(250,140,0));
				menuBar.setBackground(Color.orange);
				menu.setBackground(Color.orange);
				menuTwo.setBackground(Color.orange);
				DarkMode.setBackground(Color.orange);
				LightMode.setBackground(Color.orange);
				SageMode.setBackground(Color.orange);
				quit.setBackground(Color.orange);
				subMenu.setBackground(Color.orange);
				Player1.setBackground(Color.orange);
				Player2.setBackground(Color.orange);
			}
		}
	}
	private void setLightMode() {
		getRootPane().setWindowDecorationStyle(2);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setBackground(new JButton().getBackground());
				menuBar.setBackground(null);
				menu.setBackground(null);
				menuTwo.setBackground(null);
				DarkMode.setBackground(null);
				LightMode.setBackground(null);
				SageMode.setBackground(null);
				quit.setBackground(null);
				subMenu.setBackground(null);
				Player1.setBackground(null);
				Player2.setBackground(null);
			}
		}
		setBackground(new JButton().getBackground());
	}
	
	
	private void resetBoard() {
		currentPlayer="X";
		hasWinner=false;
		moves=9;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setText("");
			}
		}
	}
	
	
	private void initializeBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				x=i;
				y=j;
				board[i][j]=new JButton();
				board[i][j].setText("");
				add(board[i][j]);
				board[i][j].addActionListener(new buttonListener ());
			}
		}

	}
	
	private void sound(){
		
		if(hasWinner==false && currentPlayer.equals("X")){
			try {
			Sequencer player= MidiSystem.getSequencer();
			player.open();

			Sequence seq= new Sequence(Sequence.PPQ,1);
			Track track= seq.createTrack();

			ShortMessage a= new ShortMessage();
			a.setMessage(144,1,70,100);

			MidiEvent turnOn= new MidiEvent(a,0);
			track.add(turnOn);
			
			ShortMessage b= new ShortMessage();
			b.setMessage(128,1,70,100);

			MidiEvent turnOff= new MidiEvent(b,0);
			track.add(turnOff);
			
			
			player.setSequence(seq);
			player.start();
			
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}else if (hasWinner==false && currentPlayer.equals("O")){
			try {
			Sequencer player= MidiSystem.getSequencer();
			player.open();

			Sequence seq= new Sequence(Sequence.PPQ,1);
			Track track= seq.createTrack();

			ShortMessage a= new ShortMessage();
			a.setMessage(144,1,80,100);

			MidiEvent turnOn= new MidiEvent(a,0);
			track.add(turnOn);
			
			ShortMessage b= new ShortMessage();
			b.setMessage(128,1,80,100);

			MidiEvent turnOff= new MidiEvent(b,0);
			track.add(turnOff);
			
			
			 player.setSequence(seq);
			player.start();
			
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}else if(hasWinner==true){
		
			try {
			Sequencer player= MidiSystem.getSequencer();
			player.open();

			Sequence seq= new Sequence(Sequence.PPQ,1);
			Track track= seq.createTrack();

			ShortMessage a= new ShortMessage();
			a.setMessage(144,1,80,100);

			MidiEvent turnOn= new MidiEvent(a,0);
			track.add(turnOn);
			
			ShortMessage b= new ShortMessage();
			b.setMessage(144,1,70,100);

			MidiEvent turnOff= new MidiEvent(b,1);
			track.add(turnOff);
			
			ShortMessage c= new ShortMessage();
			c.setMessage(144,9,45,100);

			MidiEvent turn= new MidiEvent(c,1);
			track.add(turn);
			
			 player.setSequence(seq);
			player.start();
			
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		
	}

	private class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			sound();
			if(((JButton)e.getSource()).getText().equals("") && hasWinner==false) {
				moves--;
				((JButton)e.getSource()).setText(currentPlayer);
				boolean hasWinnerCheck=hasWinner();
				if(hasWinnerCheck==true) {
					sound();
					JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" wins!   :D");
					resetBoard();
				}
				if(moves==0) {
					JOptionPane.showMessageDialog(null," It is a Draw");
					resetBoard();
				}
				togglePlayer();
			}
		} 
	}

	private void togglePlayer() {
		if(currentPlayer.equals("X")) {
			currentPlayer="O";
		}else {
			currentPlayer="X";
		}
	}

	private boolean hasWinner() {
		String Player=currentPlayer;
		if(board[0][0].getText().equals(Player) & board[0][1].getText().equals(Player) & board[0][2].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[1][0].getText().equals(Player) & board[1][1].getText().equals(Player) & board[1][2].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[2][0].getText().equals(Player) & board[2][1].getText().equals(Player) & board[2][2].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[0][0].getText().equals(Player) & board[1][0].getText().equals(Player) & board[2][0].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[0][1].getText().equals(Player) & board[1][1].getText().equals(Player) & board[2][1].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[0][2].getText().equals(Player) & board[1][2].getText().equals(Player) & board[2][2].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[0][0].getText().equals(Player) & board[1][1].getText().equals(Player) & board[2][2].getText().equals(Player)) {
			hasWinner=true;
		}
		else if(board[2][0].getText().equals(Player) & board[1][1].getText().equals(Player) & board[0][2].getText().equals(Player)) {
			hasWinner=true;
		}

		return hasWinner;

	}


public static void main(String[] args){
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			new TicTacToe();
		}
	});
}
}