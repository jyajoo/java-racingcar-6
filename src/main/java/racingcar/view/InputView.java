package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private String getInput() {
        return Console.readLine();
    }

    public List<String> getCarNamesInput() {
        String carNamesInput = getInput();
        return Arrays.stream(carNamesInput.split(","))
                .map(String::trim).toList();
    }

    public String getCountInput() {
        return getInput();
    }
}
