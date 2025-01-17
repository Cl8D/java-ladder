package laddergame.controller;

import laddergame.controller.dto.ResultParticipantsDto;
import laddergame.domain.ladder.Ladder;
import laddergame.domain.ladder.result.LadderResult;
import laddergame.domain.ladder.result.LadderResultName;
import laddergame.domain.participant.Participant;
import laddergame.domain.participant.Participants;
import laddergame.util.BooleanGenerator;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static laddergame.view.message.Message.EXIT_INPUT_MESSAGE;
import static laddergame.view.message.Message.LADDER_RESULT_GUIDE;

public class LadderGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BooleanGenerator rungGenerator;

    public LadderGameController(final InputView inputView, final OutputView outputView, final BooleanGenerator rungGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.rungGenerator = rungGenerator;
    }

    public void start() {
        Participants participants = createParticipants();
        int participantSize = participants.size();
        LadderResult ladderResult = createLadderResult(participantSize);

        Ladder ladder = createLadder(participantSize);
        printLadderResult(participants, ladder, ladderResult);

        List<String> ladderResultNames = getLadderResultNames(participantSize, ladderResult, ladder);
        printGameResultWithRetry(participants, ladderResultNames);
    }

    private Participants createParticipants() {
        return inputView.getInputWithRetry(() -> {
            String participantNames = inputView.getParticipantNames();
            return Participants.create(participantNames);
        });
    }

    private Ladder createLadder(final int participantSize) {
        return inputView.getInputWithRetry(() -> {
            String maxLadderHeight = inputView.getMaxLadderHeight();
            return Ladder.create(maxLadderHeight, participantSize, rungGenerator);
        });
    }

    private LadderResult createLadderResult(final int participantSize) {
        return inputView.getInputWithRetry(() -> {
            String resultNames = inputView.getLadderResultNames();
            return LadderResult.create(resultNames, participantSize);
        });
    }

    private void printLadderResult(final Participants participants, final Ladder ladder, final LadderResult ladderResult) {
        OutputView.print(System.lineSeparator() + LADDER_RESULT_GUIDE.getMessage() + System.lineSeparator());
        List<String> participantNames = getParticipantNames(participants);
        List<String> ladderResultNames = getLadderResultNames(ladderResult);
        outputView.printParticipantNames(participantNames);
        outputView.printLadder(ladder.getLadderRungs(), participantNames);
        outputView.printLadderResultNames(participantNames, ladderResultNames);
    }

    private List<String> getLadderResultNames(final int participantSize, final LadderResult ladderResult, final Ladder ladder) {
        List<String> ladderResultNames = new ArrayList<>();
        for (int participantOrder = 0; participantOrder < participantSize; participantOrder++) {
            int finalPosition = ladder.startGame(participantOrder);
            String ladderResultName = ladderResult.getNameByPosition(finalPosition);
            ladderResultNames.add(ladderResultName);
        }
        return ladderResultNames;
    }

    private List<String> getParticipantNames(final Participants participants) {
        return participants.getParticipants()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> getLadderResultNames(final LadderResult ladderResult) {
        return ladderResult.getResultNames()
                .stream()
                .map(LadderResultName::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private void printGameResultWithRetry(final Participants participants, final List<String> ladderResultNames) {
        ResultParticipantsDto resultParticipantsDto;
        do {
            resultParticipantsDto = getResultParticipantsDto(participants);
            outputView.printLadderGameResult(resultParticipantsDto, ladderResultNames);
        } while (resultParticipantsDto.isProceed());
    }

    private ResultParticipantsDto getResultParticipantsDto(final Participants participants) {
        return inputView.getInputWithRetry(() -> {
            String resultParticipantName = inputView.getResultParticipantName();
            return makeResultParticipantsDto(participants, resultParticipantName);
        });
    }

    private ResultParticipantsDto makeResultParticipantsDto(final Participants participants, final String resultParticipantName) {
        if (resultParticipantName.equalsIgnoreCase(EXIT_INPUT_MESSAGE.getMessage())) {
            return ResultParticipantsDto.create(Collections.emptyList(), false);
        }
        List<Participant> resultParticipants = participants.getResultParticipants(resultParticipantName);
        return ResultParticipantsDto.create(resultParticipants, true);
    }
}
