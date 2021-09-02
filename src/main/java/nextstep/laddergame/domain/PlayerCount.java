package nextstep.laddergame.domain;

public class PlayerCount {

    private static final int MINIMUM_COUNT_OF_PLAYER = 2;

    private final int count;

    public PlayerCount(int count) {
        validateMinimumCount(count);
        this.count = count;
    }

    private void validateMinimumCount(int count) {
        if (count < MINIMUM_COUNT_OF_PLAYER) {
            throw new IllegalArgumentException(String.format("참여자는 두 명 이상이어야 합니다. count: %s", count));
        }
    }

    public static PlayerCount of(int count) {
        return new PlayerCount(count);
    }

    public int getCount() {
        return count;
    }
}