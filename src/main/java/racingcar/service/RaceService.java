package racingcar.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Race;

public class RaceService {
    public Cars initializeCars(List<String> carNamesInput) {
        List<String> carNames = validateCarNamesInput(carNamesInput);
        return new Cars(carNames);
    }

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
            throw new IllegalArgumentException();
        }
    }

    private void validateLengthInput(List<String> carNames) {
        boolean isInvalidCarName = carNames
                .stream()
                .anyMatch(carName -> carName.length() > 5);

        if (isInvalidCarName) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateInput(List<String> carNames) {
        Set<String> uniqueCarNames = new HashSet<>(carNames);
        if (uniqueCarNames.size() != carNames.size()) {
            throw new IllegalArgumentException();
        }
    }

    public int initializeCount(String countInput) {
        return validateNumeric(countInput);
    }

    private int validateNumeric(String countInput) {
        try {
            return Integer.parseInt(countInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<Car> runRace(Race race) {
        race.run();
        return race.getCars();
    }
}
