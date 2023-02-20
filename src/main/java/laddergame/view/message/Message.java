package laddergame.view.message;

public enum Message {
    INPUT_PARTICIPANT_NAMES_GUIDE("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)"),
    INPUT_LADDER_MAX_HEIGHT_GUIDE("최대 사다리 높이는 몇 개인가요?"),
    INPUT_LADDER_RESULT_GUIDE("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)"),
    LADDER_RESULT_GUIDE("사다리 결과"),
    INPUT_RESULT_PARTICIPANT_NAME("결과를 보고 싶은 사람은?")
    ;

    private final String message;

    Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
