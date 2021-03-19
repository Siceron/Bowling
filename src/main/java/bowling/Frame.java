package bowling;

import java.util.Optional;

public class Frame {

	private Integer score1;
	private Integer score2;

	public Frame(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Optional<Integer> getScore2() {
		return Optional.ofNullable(score2);
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public boolean isFinished() {
		return (score1 != null && score1 == 10) || (score1 != null && score2 != null);
	}

	public boolean isSpare() {
		return score1 != null && score2 != null && score1 + score2 == 10;
	}

	public boolean isStrike() {
		return score1 != null && score1 == 10;
	}

	private String serializeScore(Integer score) {
		if (score == null || score == 0) {
			return "-";
		} else {
			return String.valueOf(score);
		}
	}

	@Override
	public String toString() {
		if (isStrike()) {
			return "X";
		} else if (isSpare()) {
			return serializeScore(score1) + ", /";
		} else {
			if(isFinished()) {
				return serializeScore(score1) + ", " + serializeScore(score2);
			} else {
				return serializeScore(score1);
			}
		}
	}
}