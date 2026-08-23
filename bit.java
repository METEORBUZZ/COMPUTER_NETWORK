import java.util.Scanner;

public class BYTE {
	private static final char FLAG = 'F';
	private static final char ESCAPE = 'E';

	public static String byteStuff(String data) {
		StringBuilder stuffed = new StringBuilder();
		stuffed.append(FLAG);

		for (char value : data.toCharArray()) {
			if (value == FLAG || value == ESCAPE) {
				stuffed.append(ESCAPE);
			}
			stuffed.append(value);
		}

		stuffed.append(FLAG);
		return stuffed.toString();
	}

	public static String byteDestuff(String frame) {
		if (frame.length() < 2 || frame.charAt(0) != FLAG
				|| frame.charAt(frame.length() - 1) != FLAG) {
			throw new IllegalArgumentException("Invalid frame: missing flag bytes");
		}

		StringBuilder destuffed = new StringBuilder();
		for (int i = 1; i < frame.length() - 1; i++) {
			char value = frame.charAt(i);
			if (value == ESCAPE) {
				if (i + 1 >= frame.length() - 1) {
					throw new IllegalArgumentException("Invalid frame: incomplete escape sequence");
				}
				i++;
				value = frame.charAt(i);
			}
			destuffed.append(value);
		}
		return destuffed.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter data: ");
		String input = scanner.nextLine();
		String stuffed = byteStuff(input);

		System.out.println("Stuffed frame:   " + stuffed);
		System.out.println("Destuffed data:  " + byteDestuff(stuffed));

		scanner.close();
	}
}
