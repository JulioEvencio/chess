package game.resources;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Log {

	public static void save(Exception error) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("chess-log.txt"))) {
			error.printStackTrace(writer);
		} catch (Exception e) {
			// Code
		}
	}

}
