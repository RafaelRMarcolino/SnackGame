package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;

public class SnackeGame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	Board board = new Board(this);
	SidePanel side = new SidePanel(null);
	
	
	// classe que é acionada apos o running ser falso que que cai no else do running, mostrando a mensadem de pontos 
	// e game over, parametro X recebe a variavel pontos  
	public void gameOver(Graphics g, int pontos ) {
		
	    g.setColor(Color.blue);
		g.setFont(new Font("Helvetica", Font.BOLD, 60));
		FontMetrics fonte2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (Board.ROW - fonte2.stringWidth("Game Over"))/2, Board.COLUMN/4);

		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics fonte3 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (Board.ROW - fonte3.stringWidth("Game Over"))/2, Board.COLUMN/4);

		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 50));
		FontMetrics font4 = getFontMetrics(g.getFont());
		g.drawString("Se voce morreu envenenado", 
				(Board.ROW - font4.stringWidth("Se voce morreu envenenado"))/3, Board.COLUMN/3);

		g.setColor(Color.white);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		g.drawString("   Voce tem mais uma chance  ", 
				(Board.ROW - font4.stringWidth("Voce ainda tem uma chance "))/2, Board.COLUMN/2);
		
		  g.setColor(Color.blue);
	        g.setFont(new Font("Helvetica", Font.BOLD, 60));
	        FontMetrics fonte1 = getFontMetrics(g.getFont());
	        g.drawString("Pontos: " + pontos , (Board.ROW - fonte1.stringWidth("Pontos: " + pontos ))/2,  900/2);
	        
	}
	
	// Janela para mostrar a view do jogo tamanho da tela dois paineis 
	
	
	public void SnackeView() {
		setLayout(new BorderLayout());
		this.setTitle("Snake Game Rafael Reinaldo Marcolino");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);
		
		
		this.board = new Board(this);
		this.side = new SidePanel(this);
		

		add(board, BorderLayout.CENTER);
		add(side, BorderLayout.EAST);


		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}
