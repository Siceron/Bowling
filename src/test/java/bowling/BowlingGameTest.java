package bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	void unfinishedGame(){
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(Arrays.asList(10, 1));

		String expected = "1: X = -\n" +
						  "2: 1 = -\n" +
						  "---------\n" +
						  "12\n";

		assertEquals(expected, outContent.toString());
	}

	@Test
	void completeGameWithStrikeAndSpare() {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(Arrays.asList(10, 10, 10, 7, 2, 8, 2, 0, 9, 10, 7, 3, 9, 0, 10, 10, 8));

		String expected = "1: X = 30\n" +
						  "2: X = 57\n" +
						  "3: X = 76\n" +
						  "4: 7, 2 = 85\n" +
						  "5: 8, / = 95\n" +
						  "6: -, 9 = 104\n" +
						  "7: X = 124\n" +
						  "8: 7, / = 143\n" +
						  "9: 9, - = 152\n" +
						  "10: X = 180\n" +
						  "---------\n" +
						  "180\n";

		assertEquals(expected, outContent.toString());
	}

	@Test
	void givenExample1() {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(Arrays.asList(1, 1, 9, 1));

		String expected = "1: 1, 1 = 2\n" +
						  "2: 9, / = -\n" +
						  "---------\n" +
						  "12\n";

		assertEquals(expected, outContent.toString());
	}

	@Test
	void givenExample2() {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(Arrays.asList(1, 1, 9, 1, 10));

		String expected = "1: 1, 1 = 2\n" +
						  "2: 9, / = 22\n" +
						  "3: X = -\n" +
						  "---------\n" +
						  "32\n";

		assertEquals(expected, outContent.toString());
	}

	@Test
	void givenExample3() {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(Arrays.asList(1, 1, 9, 1, 10, 1, 1, 9, 0, 5, 3));

		String expected = "1: 1, 1 = 2\n" +
						  "2: 9, / = 22\n" +
						  "3: X = 34\n" +
						  "4: 1, 1 = 36\n" +
						  "5: 9, - = 45\n" +
						  "6: 5, 3 = 53\n" +
						  "---------\n" +
						  "53\n";

		assertEquals(expected, outContent.toString());
	}
}