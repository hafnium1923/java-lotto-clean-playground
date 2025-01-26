import controller.LottoController;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LottoController controller = new LottoController();
        controller.run();
    }

    private LottoResult calculateLottoResult(List<LottoTicket> tickets, List<LottoNumber> winningNumbers) {
        int match3 = 0;
        int match4 = 0;
        int match5 = 0;
        int match6 = 0;

        for (LottoTicket ticket : tickets) {
            int matchCount = ticket.countMatchingNumbers(winningNumbers);
            if (matchCount == 3) {
                match3++;
            }
            if (matchCount == 4) {
                match4++;
            }
            if (matchCount == 5) {
                match5++;
            }
            if (matchCount == 6) {
                match6++;
            }
        }

        return new LottoResult(match3, match4, match5, match6);
    }
}
