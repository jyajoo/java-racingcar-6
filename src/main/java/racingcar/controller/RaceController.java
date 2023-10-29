package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RaceService raceService;

    public RaceController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.raceService = new RaceService();
    }

    public void run() {
        Cars cars = initializeCars();
        int count = initializeCount();
        runRace(cars, count);
    }

    private int initializeCount() {
        outputView.countOutput();
        String countInput = inputView.getCountInput();
        return raceService.initializeCount(countInput);
    }

    private Cars initializeCars() {
        outputView.carNamesOutput();
        List<String> carNamesInput = inputView.getCarNamesInput();
        return raceService.initializeCars(carNamesInput);
    }

    private void runRace(Cars cars, int count) {
        Race race = new Race(cars, count);
        while (race.play()) {
            List<Car> raceCars = raceService.runRace(race);
            outputView.carsDistanceOutput(raceCars);
        }
    }
}
