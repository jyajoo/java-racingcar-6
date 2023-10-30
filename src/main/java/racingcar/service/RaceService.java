package racingcar.service;


import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.Race;

public class RaceService {

    private final ValidationService validationService;

    public RaceService() {
        validationService = new ValidationService();
    }

    public Cars initializeCars(List<String> carNamesInput) {
        List<String> carNames = validationService.validateCarNamesInput(carNamesInput);
        return new Cars(carNames);
    }

    public Count initializeCount(String countInput) {
        int count = validationService.validateNumericInput(countInput);
        return new Count(count);
    }

    public List<Car> runRace(Race race) {
        race.run();
        return race.getCars();
    }
}
