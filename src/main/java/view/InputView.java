package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount(final int LOTTO_PRICE) {
        boolean isValid = false;
        int amount = 0;

        do {
            System.out.println("구입금액을 입력해 주세요.");
            String input = scanner.nextLine();

            try {
                amount = Integer.parseInt(input);

                if (amount > 0 && amount % LOTTO_PRICE == 0) {
                    isValid = true;
                }
            } catch (NumberFormatException exception) {
                System.out.println("1000원 단위의 유효한 숫자를 입력해주세요. 다시 시도해주세요.");
            }
        }while(!isValid);

        return amount;
    }
}
