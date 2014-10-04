package net.tools.search;

/**
 * Basic system interface.
 *
 */
public class Console {
	
	private String[] options;

	public Console(String[] options) {
		this.options = options;
		System.out.println("Console has been launched");
	}
	
	public String[] getOptions() {
		return options;
	}

	public static void main(String[] args) {
		new Console(args);
	}

}
