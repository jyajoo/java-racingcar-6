package racingcar.service;

import static java.lang.String.format;
import static racingcar.constant.ErrorMessage.DUPLICATE_INPUT_ERROR;
import static racingcar.constant.ErrorMessage.EMPTY_INPUT_ERROR;
import static racingcar.constant.ErrorMessage.LENGTH_INPUT_ERROR;
import static racingcar.constant.ErrorMessage.NUMERIC_INPUT_ERROR;
import static racingcar.constant.RaceSetting.MAX_CAR_NAME_LENGTH;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationService {
    public List<String> validateCarNamesInput(List<String> carNamesInput) {
        validateEmptyInput(carNamesInput);
        validateLengthInput(carNamesInput);
        validateDuplicateInput(carNamesInput);
        return carNamesInput;
    }

    private void validateEmptyInput(List<String> carNamesInput) {
        boolean isEmptyInput = carNamesInput
                .stream()
                .anyMatch(carName -> carName.trim().isEmpty());
        if (isEmptyInput) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }

    private void validateLengthInput(List<String> carNames) {
        boolean isValidCarName = carNames
                .stream()
                .allMatch(carName -> carName.length() <= MAX_CAR_NAME_LENGTH.getValue());

        if (!isValidCarName) {
            throw new IllegalArgumentException(
                    format(LENGTH_INPUT_ERROR.getMessage(), MAX_CAR_NAME_LENGTH.getValue())
            );
        }
    }

    private void validateDuplicateInput(List<String> carNames) {
        Set<String> uniqueCarNames = new HashSet<>(carNames);
        if (uniqueCarNames.size() != carNames.size()) {
            throw new IllegalArgumentException(DUPLICATE_INPUT_ERROR.getMessage());
        }
    }

    public int validateNumericInput(String countInput) {
        try {
            return Integer.parseInt(countInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_INPUT_ERROR.getMessage());
        }
    }
}
