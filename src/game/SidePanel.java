package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SidePanel extends JPanel {
private static final long serialVersionUID = 1L;

		private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);
		
		private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);

		private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
		
		SnackeGame game;
		Board board = new Board(game);
		
		int score = board.score;

		public SidePanel(SnackeGame game) {
			this.game = game;
			
			setPreferredSize(new Dimension(400, (Board.ROW + Board.SIZE) - 20));
			setBackground(Color.DARK_GRAY);
		}
		
		private static final int STATISTICS_OFFSET = 150;
		
		private static final int CONTROLS_OFFSET = 200;
		
		private static final int MESSAGE_STRIDE = 30;
		
		private static final int SMALL_OFFSET = 30;
		
		private static final int LARGE_OFFSET = 50;
		
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawn2(g, score);
			
		}
		
		public void drawn2(Graphics g, int x) {
			
			g.setColor(Color.pink);
			
			
			g.setFont(LARGE_FONT);
			g.drawString("Snake Game" ,  getWidth() / 2 - g.getFontMetrics().stringWidth("Snake Game") / 2, 50);
			
			
			g.setFont(MEDIUM_FONT);
			g.drawString("Pontos" , SMALL_OFFSET, STATISTICS_OFFSET);
			g.drawString("Controles", SMALL_OFFSET, CONTROLS_OFFSET);
					
		
			g.setFont(SMALL_FONT);
			
			int drawY = STATISTICS_OFFSET;
			
			drawY = CONTROLS_OFFSET;

			g.drawString("Para cima: W / tecla Cima", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Para baixo: S / tecla Baixo", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Para esquerda: A / tecla Esquerda", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Para direita: D / tecla Direita", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Enter para iniciar novamente o jogo: ENTER", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
				
		}
		
		
}
