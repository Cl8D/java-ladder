package laddergame.domain.ladder.result;

import laddergame.domain.exception.ladder.result.LadderResultCountException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LadderResult {

    private static final String DELIMITER = ",";

    private final List<LadderResultName> resultNames;

    private LadderResult(final String names, final int participantCount) {
        List<String> ladderResultNames = splitResults(names);
        ladderResultNames = trimResults(ladderResultNames);
        List<LadderResultName> resultNames = makeLadderResultNames(ladderResultNames);
        validateNameSize(resultNames, participantCount);
        this.resultNames = resultNames;
    }

    public static LadderResult create(final String resultNames, final int participantCount) {
        return new LadderResult(resultNames, participantCount);
    }

    public String getNameByPosition(final int finalPosition) {
        LadderResultName ladderResultName = resultNames.get(finalPosition);
        return ladderResultName.getName();
    }

    private List<String> splitResults(final String resultNames) {
        return Arrays.asList(resultNames.split(DELIMITER));
    }

    private List<String> trimResults(final List<String> resultNames) {
        return resultNames.stream().map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LadderResultName> makeLadderResultNames(final List<String> resultNames) {
        return resultNames.stream().map(LadderResultName::create)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateNameSize(final List<LadderResultName> ladderResultNames, final int participantCount) {
        if (ladderResultNames.size() != participantCount) {
            throw new LadderResultCountException(participantCount);
        }
    }

    public List<LadderResultName> getResultNames() {
        return List.copyOf(resultNames);
    }
}
