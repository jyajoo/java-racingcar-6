package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static racingcar.constant.ErrorMessage.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidationServiceTest {

    @Test
    @DisplayName("자동차 이름을 올바르게 입력한다.")
    void validateCarNamesInputTest() {
        ValidationService validationService = new ValidationService();
        List<String> carNamesInput = List.of("A", "AB", "ABC");
        validationService.validateCarNamesInput(carNamesInput);
    }

    @ParameterizedTest
    @DisplayName("자동차 이름이 빈 문자열이라면, 예외 처리한다.")
    @CsvSource({"'', 'AB', 'ABC'", "'A', ' ', 'ABC'", "'A', 'AB', '  '"})
    void validateCarNamesInputTest2(String carName1, String carName2, String carName3) {
        ValidationService validationService = new ValidationService();
        List<String> carNamesInput = List.of(carName1, carName2, carName3);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateCarNamesInput(carNamesInput));

        assertThat(exception.getMessage()).isEqualTo(EMPTY_INPUT_ERROR.getMessage());
    }
}