package laddergame.domain.ladder;

import laddergame.domain.exception.TypeException;
import laddergame.domain.exception.ladder.LadderHeightRangeException;
import laddergame.domain.rung.Rungs;
import laddergame.util.BooleanGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderRungsFactory {

    private static final int MIN_HEIGHT = 1;
    private static final int MAX_HEIGHT = 10_000;
    private static final int DEFAULT_COUNT = 1;
    private static final Map<BooleanGenerator, LadderRungsFactory> CACHE = new HashMap<>();

    private final BooleanGenerator rungGenerator;

    private LadderRungsFactory(final BooleanGenerator rungGenerator) {
        this.rungGenerator = rungGenerator;
    }

    static LadderRungsFactory getInstance(final BooleanGenerator rungGenerator) {
        if (CACHE.containsKey(rungGenerator)) {
            return CACHE.get(rungGenerator);
        }
        LadderRungsFactory ladderRungsFactory = new LadderRungsFactory(rungGenerator);
        CACHE.put(rungGenerator, ladderRungsFactory);
        return ladderRungsFactory;
    }

    protected List<Rungs> createLadderRungs(final String height, final int participantCount) {
        int ladderHeight = convertToLadderHeight(height);
        List<Rungs> ladder = new ArrayList<>();
        for (int i = 0; i < ladderHeight; i++) {
            ladder.add(Rungs.create(makeRungCount(participantCount), rungGenerator));
        }
        return ladder;
    }

    private int convertToLadderHeight(final String height) {
        int ladderHeight = validateHeightType(height);
        validateHeightRange(ladderHeight);
        return ladderHeight;
    }

    private int makeRungCount(final int participantCount) {
        return participantCount - DEFAULT_COUNT;
    }

    private int validateHeightType(final String height) {
        try {
            return Integer.parseInt(height);
        } catch (NumberFormatException e) {
            throw new TypeException();
        }
    }

    private void validateHeightRange(final int height) {
        if (height < MIN_HEIGHT || MAX_HEIGHT < height) {
            throw new LadderHeightRangeException(MIN_HEIGHT, MAX_HEIGHT);
        }
    }
}

