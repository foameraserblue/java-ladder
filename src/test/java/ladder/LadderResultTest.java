package ladder;

import ladder.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderResultTest {
    private Players players;
    private Ladder ladder;
    private Prize prize;

    @BeforeEach
    void ladder() {
        List<Line> lines = new ArrayList<>();
        players = Players.of(Arrays.asList("red", "blue", "black"));
        prize = Prize.of(Arrays.asList("꽝", "1000", "5000"));

        int index = 0;

        Point point1 = new Point(index++, new Direction(false, true));
        Point point2 = new Point(index++, new Direction(true, false));
        Point point3 = new Point(index++, new Direction(false, false));

        Line ladderLine1 = new Line(Arrays.asList(point1, point2, point3));

        index = 0;
        Point point4 = new Point(index++, new Direction(false, false));
        Point point5 = new Point(index++, new Direction(false, true));
        Point point6 = new Point(index++, new Direction(true, false));

        Line ladderLine2 = new Line(Arrays.asList(point4, point5, point6));

        lines.add(ladderLine1);
        lines.add(ladderLine2);

        ladder = new Ladder(lines);
    }

    @DisplayName("참가자의 실행 결과를 반환한다.")
    @Test
    void prizeResult() {
        LadderResult ladderResult = new LadderResult(players.count(), ladder);

        assertThat(ladderResult.playersPrize(new PrizePlayer(players, new Player("red")), prize)).isEqualTo("5000");
        assertThat(ladderResult.playersPrize(new PrizePlayer(players, new Player("blue")), prize)).isEqualTo("꽝");
        assertThat(ladderResult.playersPrize(new PrizePlayer(players, new Player("black")), prize)).isEqualTo("1000");
    }

    @DisplayName("모든 참가자의 실행 결과를 반환한다.")
    @Test
    void prizeResultAll() {
        LadderResult ladderResult = new LadderResult(players.count(), ladder);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("red", "5000");
        resultMap.put("blue", "꽝");
        resultMap.put("black", "1000");

        assertThat(ladderResult.playersPrizeAll(new PrizePlayer(players), prize)).containsExactlyEntriesOf(resultMap);
    }
}