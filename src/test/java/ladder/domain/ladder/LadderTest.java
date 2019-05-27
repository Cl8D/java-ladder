package ladder.domain.ladder;

import ladder.domain.rule.LadderRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LadderTest {
    int countPerson;
    int ladderHeight;
    List<Line> lines;

    @BeforeEach
    public void setup() {
        countPerson = 3;
        ladderHeight = 5;
        lines = new Ladder(ladderHeight, countPerson).getLines();
    }

    @Test
    public void 사다리_사이즈_높이_일치_여부() {
        assertThat(lines.size()).isEqualTo(ladderHeight);
    }

    @Test
    public void 라인_너비_주입_확인() {
        for (int i = 0; i < lines.size(); i++) {
            assertThat(lines.get(i).getPoints().size()).isEqualTo(countPerson);
        }
    }

    @Test
    public void 최소_높이_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ladder(0, 100);
        });
    }

    @Test
    public void 도착점_찾기_모두_True() {
        int i = 0;
        Ladder ladder = new Ladder(5, 2, new ForcedTrueRule());
        assertThat(ladder.getEndPoint(0)).isEqualTo(1);
    }

}

class ForcedRule implements LadderRule {
    static int num = 0;

    @Override
    public boolean isAvailablePoint() {
        num++;
        return num != 3;
    }
}