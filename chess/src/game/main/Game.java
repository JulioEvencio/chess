package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int SCALE = 1;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private final BufferedImage renderer;

	public Game() {
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		JFrame frame = new JFrame();

		frame.setTitle("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
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

		// Code

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
		}
	}

	public static void main(String[] args) {
		new Thread(new Game()).start();
	}

}
