package racingcar.domain;


import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static racingcar.constant.ErrorMessage.*;
import static racingcar.constant.RaceSetting.MAX_CAR_NAME_LENGTH;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CarsTest {

    @Test
    @DisplayName("자동차 이름 입력을 올바르게 입력한다.")
    void cars() {
        List<String> carNameInput = List.of("A", "AB", "ABC", "ABCDE");
        Cars cars = new Cars(carNameInput);
        assertThat(cars.getCars())
                .extracting(Car::getName)
                .containsExactly("A", "AB", "ABC", "ABCDE");
    }

    @ParameterizedTest
    @CsvSource({"'', A, AB", "' ', A, AB", "A, AB, ''"})
    @DisplayName("자동차 이름 중 빈 문자열이 있다면, 예외 처리한다.")
    void cars2(String carName1, String carName2, String carName3) {
        List<String> carNameInput = List.of(carName1, carName2, carName3);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Cars(carNameInput)
        );
        assertThat(exception.getMessage()).isEqualTo(EMPTY_INPUT_ERROR.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"ABCDEF, A, AB", "AB, ABCDEF, ABC", "A, AB, ABCDEFG"})
    @DisplayName("자동차 이름 중 5자보다 넘는 이름이 있다면, 예외 처리한다.")
    void cars3(String carName1, String carName2, String carName3) {
        List<String> carNameInput = List.of(carName1, carName2, carName3);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Cars(carNameInput)
        );
        assertThat(exception.getMessage()).isEqualTo(
                format(LENGTH_INPUT_ERROR.getMessage(), MAX_CAR_NAME_LENGTH.getValue())
        );
    }

    @ParameterizedTest
    @CsvSource({"A, A, AB", "A, AB, A", "A, B, B"})
    @DisplayName("자동차 이름 중 중복되는 이름이 있다면, 예외 처리한다.")
    void cars4(String carName1, String carName2, String carName3) {
        List<String> carNameInput = List.of(carName1, carName2, carName3);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Cars(carNameInput)
        );

        assertThat(exception.getMessage()).isEqualTo(DUPLICATE_INPUT_ERROR.getMessage());
    }
}