package laddergame.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static laddergame.domain.message.ErrorMessage.INVALID_DUPLICATE_NAME;
import static laddergame.domain.message.ErrorMessage.INVALID_PARTICIPANT_COUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ParticipantsTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi,pobi"})
    @DisplayName("입력받은 이름에 중복값이 존재하면, 예외가 발생한다.")
    void create_givenDuplicatedNames_thenFail(final String names)  {
        assertThatThrownBy(() -> Participants.create(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DUPLICATE_NAME.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi"})
    @DisplayName("입력받은 참여자의 수가 1명이면, 예외가 발생한다.")
    void create_givenSingleCountParticipant_thenFail(final String names) {
        assertThatThrownBy(() -> Participants.create(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PARTICIPANT_COUNT.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"pobi,jiwon,hoocu:3", "choco,jelly,pen,water:4"}, delimiter=':')
    @DisplayName("참여자 이름의 수만큼 참여자가 생성된다.")
    void size_givenParticipantNames_thenReturnParticipantCount(final String names, final int expectedSize) {
        // given
        Participants participants = Participants.create(names);

        // when
        int actualSize = participants.size();

        // then
        assertThat(actualSize).isEqualTo(expectedSize);
    }
}
