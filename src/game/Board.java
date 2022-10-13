package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    
    
private static final long serialVersionUID = 1L;

//linha
static final int ROW = 600;
//coluna
static final int COLUMN = 600;

// tamanho dos quadrado quanto maior menor o numero de quadrados
static final int SIZE = 25;

static final int UNITS = (ROW*COLUMN)/SIZE;

// Timer para atrasar em milisegundos o tempo, controla a velocidade da cobra
static final int DELAY = 50;

//corpo snake
final int x[] = new int [UNITS];
final int y[] = new int [UNITS];

// comprimento do corpo inicial da cobra
int body = 6;

// inicializando o score
int score = 0;

// cordenadas da maca a ser impressa na horizontal
int appleX;

//cordenadas da maca a ser impressa na vertical
int appleY;

//cordenadas do veneno a ser impressão
int poisonX;

//cordenadas do veneno a ser impresso
int poisonY;

// char que serve para controlar a cobra e restart
char direction = 'R';

// variavel usada para contrar se o jogo esta ativado ou desativado, esse um dos inicializadores do jogo
boolean running = false;

// obejto timer usado para controlar a velocidade da cobra, o tempo de iniciar e o congelamento da tela  
Timer timer, times;

// para gerar aleatoridade aos objetos apple e poison
Random random;

// instanciação da classe  Snakcgame
SnackeGame game;

//instanciação da classe  Side 
SidePanel side;


//Contrutor Board recebe o parametro game da classe SnackGame
	public Board(SnackeGame game)  {
		
		this.game = game;
		random =  new Random();
		this.setPreferredSize(new Dimension(ROW, COLUMN));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		// inicia o jogo metodo que devolve o valor de true no running e da inicio ao movimento da snake
		startGame();
		
	}
	
	// metodo que inicia o jogo
	public void startGame( ) {
	    
	    //metodo que cria novas macas
		newApp();
		
		// metodo cria poison
		newAppPoison();
		
		running = true;
	
		// velocidade da snake
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	// metodo usado no reset para limpar a snake 
	public void clearnake() {
	    
		for (int i = 0; i < body; i++) {
			body = 5;
		}
	}
	
	
	// metodo para inicializar o jogo
	public void resetGame() {
		System.out.println("Reset Game");
		this.score = 0;
		clearnake();
		newApp();
		newAppPoison();
		startGame();
	}

	
	
	// metodo criado para impressao na tela 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawn(g);

	}
	
	// metod que imprimi na tela linhas colunas apple poison corpo da snake
	public void drawn(Graphics g) {
		
	    // se for true o jogo esta funcionando
		if(running) {
			
			g.setColor(Color.CYAN);
			for(int i=0; i < COLUMN/SIZE; i++) {
			    
			    // pelas cordenadas linhas e colunas
				g.drawLine(i* SIZE, 0, i * SIZE, COLUMN);
				g.drawLine(0, i *SIZE, i * ROW, i*SIZE);
			}
			
			
			// maçaa recebe o formato e a cor
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, SIZE, SIZE);
			
			
	         // poison recebe o formato e a cor
			g.setColor(Color.orange);
			g.fillOval(poisonX, poisonY, SIZE, SIZE);
			
			
			// snake sendo impressa cabeça
			for(int i =0; i < body; i++) {
				if( i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], SIZE, SIZE);
					
				}
				// impressão do corpo
				else {
					
					g.setColor(new Color(100, 190, 200));
					g.fillRect(x[i], y[i], SIZE, SIZE);
				}
			} // for
		
			// se for else o jogo esta parado e impresso a mensagem de 
	}else {
		
		game.gameOver(g, score);

		}
	}			
	// metodo que cria uma nova maça aletorios
	public void newApp() {
		
		appleX = random.nextInt((int) (ROW/SIZE)) * SIZE;
		appleY =  random.nextInt((int) (COLUMN/SIZE)) * SIZE;
	}

	// metodo que cria venenos aleatoria
	public void newAppPoison() {
		
		poisonX = random.nextInt((int) (ROW/SIZE)) * SIZE;
		poisonY = random.nextInt((int) (COLUMN/SIZE)) * SIZE;		
	}
	
	// metodo grow aonde a snake come e cresce
	int n = 0;
	public void grow() {
	    
		if((x[0] == appleX) && (y[0] == appleY)) {
			body++;
			n += 6;
			score = n;
			System.out.println("pontos " + score);
			System.out.println("running " + running);
			newApp();
			newAppPoison();
		}	
	}
	
	
	// metodo de usando a variavel direction segue as estrucoes de cima para baixo direota esquerda
	public void move() {
	    
		for(int i= body; i> 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch (direction) {
		case 'U': 
			y[0] = y[0] - SIZE;
			break;
			
		case 'D': 
			y[0] = y[0] + SIZE;
			break;	
		
		case 'L':
			x[0] = x[0] - SIZE;
			break;
			
		case 'R':
			x[0] = x[0] + SIZE;
			break;
		}
	}  
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			grow();
			checkCollisions();
			checkAppPoison();
		}
		// função que causa a atualizacao dos metodos
		repaint();
		
	}
	
	// metodo se a cobra come o veneno o jogo acaba fazendo o running ficando false e caido no else o game over
	public void checkAppPoison() {
        if((x[0] == poisonX) && (y[0] == poisonY)) {
            running = false;
            
        }
    }
	
	
	// se a cobra bate em uma das paredes o running da false e o jogo acaba
	public void checkCollisions() {
		
		for(int i = body; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		if(x[0] < 0) {
			running = false;
		}
		if(x[0] > ROW) {
			running = false;
		}
		if(x[0] > ROW) {
			running = false;
		}
		if(y[0] < 0) {
			running = false;
		}
		if(y[0] > COLUMN) {
			running = false;
		}
		if(y[0] > COLUMN) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	
	// metood que faz ação dos teclas inplementado tambem o metodo pause e o reset
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}	
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
				
			case KeyEvent.VK_ENTER:					

			    // stop para parar o tempo chamando a função resetar
					timer.stop();
					resetGame();
					
				break;	
			}	
		}
		
    }
}