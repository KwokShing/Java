package combination;

import java.lang.*;

public class Combination {
	public static int getMessage(char first, char second) {
		if (first == 'A' || first == 'B') {
			if (Character.isDigit(second)) {
				System.out.println("update file");
				return 1;
			} else if (!Character.isDigit(second)) {
				System.out.println("Y");
				return 2;
			}
		} else {
			System.out.println("X");
			if (!Character.isDigit(second)) {
				System.out.println("Y");
				return 4;
			}
			return 3;
		}

		return 0;

	}
}
