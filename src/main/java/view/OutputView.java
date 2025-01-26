package view;

import java.util.List;

public class OutputView {
    public void printPurchaseLotto(int numberOfTickets, List<List<Integer>> lottoTickets) {
        System.out.println(numberOfTickets + "개를 구매했습니다.");
        for (List<Integer> ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }
}
