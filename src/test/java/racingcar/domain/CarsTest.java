package racingcar.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.vo.Name;
import racingcar.dto.Winners;
import racingcar.vo.MovingNumber;
import static org.assertj.core.api.Assertions.*;

public class CarsTest {

    private static final int ADVANCE_VALUE = 5;
    private static final int STOP_VALUE = 3;

    @Test
    @DisplayName("자동차의 이름으로 자동차 리스트를 생성한다")
    void newCarsByNames() {
        // given
        List<Name> names = givenCarsNames("hoho", "rich", "pobi");

        // when & then
        assertThatCode(() -> new Cars(names)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("가장 많이 전진한 자동차가 우승한다")
    void existsWinner() {
        // given
        Cars cars = new Cars(givenCarsNames("hoho", "rich", "pobi"));
        Queue<MovingNumber> movingNumbers = givenNumbers(ADVANCE_VALUE, STOP_VALUE, STOP_VALUE);

        // when
        cars.move(movingNumbers);

        // then
        Winners winners = cars.getWinners();
        assertThat(winners.getWinners()).containsExactly(Name.create("hoho"));
    }

    @Test
    @DisplayName("가장 많이 전진한 자동차가 2대일경우 2명이 공동 우승자이다")
    void existsWinners() {
        // given
        Cars cars = new Cars(givenCarsNames("hoho", "rich", "pobi"));
        Queue<MovingNumber> movingNumbers = givenNumbers(ADVANCE_VALUE, ADVANCE_VALUE, STOP_VALUE);

        // when
        cars.move(movingNumbers);

        // then
        Winners winners = cars.getWinners();
        assertThat(winners.getWinners()).containsExactly(Name.create("hoho"), Name.create("rich"));
    }

    @Test
    @DisplayName("자동차의 갯수와 랜덤한 숫자들의 갯수가 일치하지 않을경우 예외를 발새한다")
    void throwExceptionWhenCarsSizeNotEqualsNumbersSize() {
        // given
        Cars cars = new Cars(givenCarsNames("hoho", "rich", "pobi"));
        Queue<MovingNumber> movingNumbers = givenNumbers(ADVANCE_VALUE);

        // when & then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> cars.move(movingNumbers))
            .withMessage("자동차와 랜덤한 숫자들의 갯수가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("랜덤 숫자들을 음수로 생성한 경우 예외를 발생한다")
    void throwExceptionWhenNumbersNegative() {
        // given
        Cars cars = new Cars(givenCarsNames("hoho", "rich", "pobi"));

        // when && then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> cars.move(givenNumbers(-1, -1, -1)))
            .withMessage("숫자는 0과 9사이어야 합니다.");
    }

    private List<Name> givenCarsNames(String... names) {
        return Arrays.stream(names)
            .map(Name::create)
            .collect(Collectors.toList());
    }

    private Queue<MovingNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(MovingNumber::create)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
