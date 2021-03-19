package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {

	public static void main(String[] args) {
		List<Integer> scores = Arrays.stream(args)
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.showScore(scores);
	}

	void showScore(List<Integer> scores) {
		List<Frame> frames = parseFrames(scores);
		int score = 0;

		for (int i = 0; i < frames.size(); i++) {
			Frame frame = frames.get(i);

			if (i < 10) {
				if (frame.isStrike()) {
					score += computeStrike(frames, score, i);
				} else if (frame.isSpare()) {
					score += computeSpare(frames, score, i);
				} else {
					score += frame.getScore1() + frame.getScore2().orElse(0);
					if(frame.isFinished()) {
						printFrameScore(i, frame, score);
					} else {
						printUnfinishedFrameScore(frames, i);
					}
				}
			}
		}

		System.out.println("---------");
		System.out.println(score);
	}

	private List<Frame> parseFrames(List<Integer> scores) {
		List<Frame> frames = new ArrayList<>();

		for (Integer currentScore : scores) {
			if (frames.isEmpty()) {
				Frame frame = new Frame(currentScore);
				frames.add(frame);
			} else {
				if (frames.get(frames.size() - 1).isFinished()) {
					Frame frame = new Frame(currentScore);
					frames.add(frame);
				} else {
					frames.get(frames.size() - 1).setScore2(currentScore);
				}
			}
		}
		return frames;
	}

	private int computeSpare(List<Frame> frames, int score, int frameIndex) {
		int spareValue;

		if (frameIndex + 1 < frames.size()) {
			spareValue = 10 + frames.get(frameIndex + 1).getScore1();
			printFrameScore(frameIndex, frames.get(frameIndex), score + spareValue);
		} else {
			spareValue = 10;
			printUnfinishedFrameScore(frames, frameIndex);
		}

		return spareValue;
	}

	private int computeStrike(List<Frame> frames, int score, int frameIndex) {
		int strikeValue;

		if (frameIndex + 1 < frames.size()) {
			Frame nextFrame = frames.get(frameIndex + 1);
			if (nextFrame.isStrike()) {
				if (frameIndex + 2 < frames.size()) {
					strikeValue = 20 + frames.get(frameIndex + 2).getScore1();
					printFrameScore(frameIndex, frames.get(frameIndex), score + strikeValue);
				} else {
					strikeValue = 20;
					printUnfinishedFrameScore(frames, frameIndex);
				}
			} else {
				strikeValue = 10 + nextFrame.getScore1() + nextFrame.getScore2().orElse(0);
				if(nextFrame.isFinished()) {
					printFrameScore(frameIndex, frames.get(frameIndex), score + strikeValue);
				} else {
					printUnfinishedFrameScore(frames, frameIndex);
				}
			}
		} else {
			strikeValue = 10;
			printUnfinishedFrameScore(frames, frameIndex);
		}

		return strikeValue;
	}

	private void printUnfinishedFrameScore(List<Frame> frames, int frameIndex) {
		System.out.println(frameIndex + 1 + ": " + frames.get(frameIndex).toString() + " = -");
	}

	private void printFrameScore(int frameIndex, Frame frame, int score) {
		System.out.println(frameIndex + 1 + ": " + frame.toString() + " = " + score);
	}
}
