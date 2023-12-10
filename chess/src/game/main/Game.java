package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import game.chess.ChessMatch;
import game.chess.exceptions.ChessException;
import game.strings.StringError;
import game.strings.StringGame;

public class Game extends Canvas implements Runnable, MouseListener {

	private static final long serialVersionUID = 1L;
	
	public static final String VERSION = "0.1";

	public static final int SCALE = 1;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private final BufferedImage renderer;

	private ChessMatch chessMatch;

	public Game() {
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		JFrame frame = new JFrame();

		frame.setTitle(StringGame.Chess);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EmptyBorder(0, 10, 0, 10));
		frame.setJMenuBar(menuBar);

		JMenu menuGame = new JMenu(StringGame.Game);
		menuBar.add(menuGame);

		JMenuItem menuItemNewGame = new JMenuItem(StringGame.NewGame);
		menuItemNewGame.addActionListener((ActionEvent) -> {
			Object[] options = { StringGame.Confirm, StringGame.Cancel };
			int dialogResult = JOptionPane.showOptionDialog(this, StringGame.DoYouWantToStartANewGameTheCurrentGameWillBeLost, StringGame.NewGame, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			
			if(dialogResult == JOptionPane.YES_OPTION){
				this.initChessMatch();
			}
		});
		menuGame.add(menuItemNewGame);

		JMenu menuAbout = new JMenu(StringGame.About);
		menuBar.add(menuAbout);

		JMenuItem menuItemInfo = new JMenuItem(StringGame.Info);
		menuItemInfo.addActionListener((ActionEvent) -> {
			JOptionPane.showMessageDialog(this, StringGame.InfoMessage, StringGame.About, JOptionPane.INFORMATION_MESSAGE);
		});
		menuAbout.add(menuItemInfo);
		
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try {
			Image imageIcon = ImageIO.read(getClass().getResource("/sprites/icon.png"));
			frame.setIconImage(imageIcon);
		} catch (Exception e) {
			Game.exitWithError(StringError.AnUnexpectedErrorOccurred);
		}

		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.initChessMatch();
	}

	private void initChessMatch() {
		try {
			this.chessMatch = new ChessMatch();
		} catch (Exception e) {
			Game.exitWithError(StringError.AnUnexpectedErrorOccurred);
		}
	}

	private void tick() {
		// Code
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		this.chessMatch.render(render);

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(renderer, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);

		// Code

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		while (true) {
			try {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;

				if (delta >= 1) {
					this.tick();
					this.render();

					delta--;
				}

				if (System.currentTimeMillis() - timer >= 1000) {
					timer = System.currentTimeMillis();
				}
			} catch (Exception e) {
				Game.exitWithError(StringError.AnUnexpectedErrorOccurred);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Code
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			if (!this.chessMatch.isCheckmate()) {
				this.chessMatch.mouseReleased(e);
			}
		} catch (ChessException chessError) {
			Game.exitWithError(chessError.getMessage());
		} catch (Exception chessError) {
			Game.exitWithError(StringError.AnUnexpectedErrorOccurred);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Code
	}

	public static void exit() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.Error, JOptionPane.ERROR_MESSAGE);
		Game.exit();
	}

}
