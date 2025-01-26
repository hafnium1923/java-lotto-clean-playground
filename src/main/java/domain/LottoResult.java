package domain;

import java.util.List;

public class LottoResult {
    private final int match3;
    private final int match4;
    private final int match5;
    private final int match6;

    public LottoResult(int match3, int match4, int match5, int match6) {
        this.match3 = match3;
        this.match4 = match4;
        this.match5 = match5;
        this.match6 = match6;
    }

    public int getMatch3() {
        return match3;
    }

    public int getMatch4() {
        return match4;
    }

    public int getMatch5() {
        return match5;
    }

    public int getMatch6() {
        return match6;
    }

    public double calculateRateOfReturn(int purchaseAmount) {
        if (purchaseAmount == 0) {
            return 0.0;
        }
        long totalPrize = (match3 * 5000) + (match4 * 50000) + (match5 * 1500000) + (match6 * 2000000000L);

        return (double) totalPrize / purchaseAmount;
    }

    public static LottoResult calculateLottoResult(List<LottoTicket> tickets, List<LottoNumber> winningNumbers) {
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
