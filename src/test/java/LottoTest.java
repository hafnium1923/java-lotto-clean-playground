
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {
    @Nested
    class LottoNumberTest {

        @Test
        @DisplayName("유효한 로또 번호 생성 테스트")
        void createValidLottoNumber() {
            int validNumber = 25;

            LottoNumber lottoNumber = new LottoNumber(validNumber);

            assertThat(lottoNumber.getNumber()).isEqualTo(validNumber);
        }

        @Test
        @DisplayName("유효하지 않은 로또 번호 생성 시 예외 발생 테스트")
        void createInvalidLottoNumber() {
            int invalidNumberLow = 0;
            int invalidNumberHigh = 46;

            assertThatThrownBy(() -> new LottoNumber(invalidNumberLow))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1부터 45 사이여야 합니다.");

            assertThatThrownBy(() -> new LottoNumber(invalidNumberHigh))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1부터 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("LottoNumber 비교 테스트")
        void compareToTest() {
            LottoNumber number10 = new LottoNumber(10);
            LottoNumber number20 = new LottoNumber(20);

            assertThat(number10.compareTo(number20)).isLessThan(0);
            assertThat(number20.compareTo(number10)).isGreaterThan(0);
            assertThat(number10.compareTo(new LottoNumber(10))).isEqualTo(0);
        }

        @Test
        @DisplayName("LottoNumber 동일성 테스트")
        void equalsAndHashCodeTest() {
            LottoNumber number15a = new LottoNumber(15);
            LottoNumber number15b = new LottoNumber(15);
            LottoNumber number25 = new LottoNumber(25);

            assertThat(number15a).isEqualTo(number15b);
            assertThat(number15a.hashCode()).isEqualTo(number15b.hashCode());

            assertThat(number15a).isNotEqualTo(number25);
        }
    }

    @Nested
    class LottoTicketTest {

        @Test
        @DisplayName("유효한 로또 티켓 생성 테스트")
        void createValidLottoTicket() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            LottoTicket ticket = new LottoTicket(numbers);

            assertThat(ticket.getNumbers()).containsExactlyElementsOf(numbers);
        }

        @Test
        @DisplayName("로또 티켓에 중복된 번호가 있으면 예외 발생 테스트")
        void createLottoTicketWithDuplicateNumbers() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(23), // 중복
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            assertThatThrownBy(() -> new LottoTicket(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("로또 티켓의 번호 개수가 6개가 아니면 예외 발생 테스트")
        void createLottoTicketWithInvalidNumberCount() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40)
            );

            assertThatThrownBy(() -> new LottoTicket(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 티켓은 6개의 번호를 가져야 합니다.");
        }

        @Test
        @DisplayName("당첨 번호와 일치하는 개수 계산 테스트")
        void countMatchingNumbersTest() {
            List<LottoNumber> ticketNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );
            LottoTicket ticket = new LottoTicket(ticketNumbers);

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(41),
                    new LottoNumber(42),
                    new LottoNumber(43)
            );

            int matchCount = ticket.countMatchingNumbers(winningNumbers);

            assertThat(matchCount).isEqualTo(3);
        }
    }


    @Nested
    class LottoResultTest {

        @Test
        @DisplayName("LottoResult 생성 및 수익률 계산 테스트")
        void createLottoResultAndCalculateRateOfReturn() {
            int match3 = 1;
            int match4 = 0;
            int match5 = 0;
            int match6 = 1;
            int purchaseAmount = 14000;

            LottoResult result = new LottoResult(match3, match4, match5, match6);

            double rateOfReturn = result.calculateRateOfReturn(purchaseAmount);

            assertThat(result.getMatch3()).isEqualTo(match3);
            assertThat(result.getMatch4()).isEqualTo(match4);
            assertThat(result.getMatch5()).isEqualTo(match5);
            assertThat(result.getMatch6()).isEqualTo(match6);
            assertThat(rateOfReturn).isEqualTo(2000005000 / 14000.0);
        }

        @Test
        @DisplayName("구매 금액이 0일 때 수익률 계산 테스트")
        void calculateRateOfReturnWithZeroPurchase() {
            int match3 = 0;
            int match4 = 0;
            int match5 = 0;
            int match6 = 0;
            int purchaseAmount = 0;

            LottoResult result = new LottoResult(match3, match4, match5, match6);

            double rateOfReturn = result.calculateRateOfReturn(purchaseAmount);

            assertThat(rateOfReturn).isEqualTo(0.0);
        }
    }
}
