package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int distance;

    public Car(String name) {
        this.name = name;
        this.distance = 0;
    }

    public void move() {
        distance += Randoms.pickNumberInRange(0, 9);
    }
}
