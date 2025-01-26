import domain.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {
    private final Lotto lotto = new Lotto();

    @Test
    @DisplayName("로또 티켓 수 계산 테스트")
    void calculateNumberOfTickets() {
        int amount = 14000;

        int numberOfTickets = lotto.calculateNumberOfTickets(amount);

        Assertions.assertThat(numberOfTickets).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 번호 풀 생성 테스트")
    void createNumbersPool() {
        List<Integer> numbersPool = lotto.createNumbersPool();

        Assertions.assertThat(numbersPool)
                .hasSize(45)
                .containsExactlyElementsOf(
                        List.of(
                                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                                41, 42, 43, 44, 45
                        )
                );
    }

    @Test
    @DisplayName("로또 티켓 생성 테스트")
    void generateLottoTickets() {
        int numberOfTickets = 5;

        List<List<Integer>> tickets = lotto.generateLottoTickets(numberOfTickets);

        Assertions.assertThat(tickets)
                .hasSize(numberOfTickets)
                .allSatisfy(ticket -> {
                    Assertions.assertThat(ticket)
                            .hasSize(6)
                            .isSorted()
                            .doesNotHaveDuplicates()
                            .allMatch(number -> number >= 1 && number <= 45);
                });
    }
}
