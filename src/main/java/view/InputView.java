package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            String input = scanner.nextLine();
            try {
                int amount = Integer.parseInt(input);
                if (amount > 0 && amount % Lotto.LOTTO_PRICE == 0) {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("1000원 단위 유효한 숫자를 입력해주세요. 다시 시도해주세요.");
            }
        }
    }

    public List<LottoNumber> getWinningNumbers() {
        while (true) {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String input = scanner.nextLine();
            try {
                String[] tokens = input.split(",");
                if (tokens.length != 6) {
                    throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
                }

                List<LottoNumber> winningNumbers = new ArrayList<>();
                for (String token : tokens) {
                    int num = Integer.parseInt(token.trim());
                    winningNumbers.add(new LottoNumber(num));
                }

                long distinctCount = winningNumbers.stream().distinct().count();
                if (distinctCount != 6) {
                    throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
                }

                return winningNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println("유효한 당첨 번호를 입력해주세요. 예: 1,2,3,4,5,6");
            }
        }
    }
}
