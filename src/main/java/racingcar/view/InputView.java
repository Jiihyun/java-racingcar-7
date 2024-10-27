package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.CarName;
import racingcar.dto.request.CarsRequest;
import racingcar.util.CarNameConverter;

import java.util.List;

public class InputView {

    public static final String CAR_NAME_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    public static final String TRY_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    public static final int START_TRY_COUNT = 1;
    public static final int END_TRY_COUNT = 10;

    public CarsRequest readCarNames() {
        System.out.println(CAR_NAME_INPUT_MESSAGE);
        String carNames = Console.readLine().strip();
        validateInput(carNames);
        List<CarName> cars = CarNameConverter.convert(carNames);
        return new CarsRequest(cars);
    }

    public int readTryCount() {
        System.out.println(TRY_COUNT_INPUT_MESSAGE);
        String tryCountString = Console.readLine().strip();
        validateInput(tryCountString);
        int tryCount = Integer.parseInt(tryCountString);
        validateRange(tryCount);
        return tryCount;
    }

    private static void validateRange(int tryCount) {
        if (tryCount < START_TRY_COUNT || tryCount > END_TRY_COUNT) {
            throw new IllegalArgumentException(
                    String.format("최소 %d번, 최대 %d번까지 시도 가능합니다.", START_TRY_COUNT, END_TRY_COUNT)
            );
        }
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다.");
        }
    }
}
