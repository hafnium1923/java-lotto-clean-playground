package view;

import domain.LottoResult;
import domain.LottoTicket;

import java.util.List;

public class OutputView {
    public void printPurchaseLotto(int numberOfTickets, List<LottoTicket> lottoTickets) {
        System.out.println(numberOfTickets + "개를 구매했습니다.");
        for (LottoTicket ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }

    public void printLottoResult(LottoResult result, int purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + result.getMatch3() + "개");
        System.out.println("4개 일치 (50000원)- " + result.getMatch4() + "개");
        System.out.println("5개 일치 (1500000원)- " + result.getMatch5() + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.getMatch6() + "개");
        double rateOfReturn = result.calculateRateOfReturn(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfReturn);
    }
}
