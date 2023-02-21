package laddergame.domain.ladder_result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LadderResult {

    private static final String DELIMITER = ",";
    private static final String LADDER_RESULT_SIZE = "사다리 결과의 개수는";
    private static final String INVALID_LADDER_RESULT_SIZE = "[ERROR] %s %d개여야 합니다.";

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

    public List<String> getResultNamesByPosition(final List<Integer> ladderResultPositions) {
        return ladderResultPositions.stream()
                .map(position -> resultNames.get(position).getName())
                .collect(Collectors.toUnmodifiableList());
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
            throw new IllegalArgumentException(String.format(INVALID_LADDER_RESULT_SIZE, LADDER_RESULT_SIZE, participantCount));
        }
    }

    public List<LadderResultName> getResultNames() {
        return List.copyOf(resultNames);
    }
}