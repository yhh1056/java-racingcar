package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.vo.Name;

public class CarTest {
    @Test
    @DisplayName("이름으로 자동차 생성 테스트")
    void carTest() {
        // given
        Name testName = Name.create("test");

        // when
        Car car = new Car(testName);

        // then
        assertThat(car.getName()).isEqualTo(testName);
    }

    @ParameterizedTest
    @CsvSource(value = {"4:1", "3:0"}, delimiter = ':')
    @DisplayName("자동차 전진 테스트")
    void advanceTest(int number, int expected) {
        // given
        Car car = new Car(Name.create("test"));

        // when
        car.advance(number);

        // then
        assertThat(car.getPosition()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 10})
    void invalidAdvanceTest(int number) {
        // given
        Car car = new Car(Name.create("test"));

        // when && then
        assertThatThrownBy(() -> car.advance(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("유효한 범위가 아닙니다.");
    }

    @Test
    @DisplayName("포지션 동일성 테스트")
    void equalsPosition() {
        // given
        Car car = new Car(Name.create("test"));
        Car otherCar = new Car(Name.create("other"));

        // when
        car.advance(4);
        otherCar.advance(4);

        // then
        assertTrue(car.isEqualPosition(otherCar));
    }

    @Test
    @DisplayName("포지션이 높은 차가 양수를 반환")
    void comparePosition() {
        // given
        Car car = new Car(Name.create("test"));
        Car otherCar = new Car(Name.create("other"));

        // when
        car.advance(4);
        otherCar.advance(3);

        // then
        assertThat(car.compareTo(otherCar)).isEqualTo(1);
    }
}
