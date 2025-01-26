package controller;

import domain.Lotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Lotto lotto;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lotto = new Lotto();
    }

    public LottoController(InputView inputView, OutputView outputView, Lotto lotto) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lotto = lotto;
    }


    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount(lotto.LOTTO_PRICE);
        int numberOfTickets = lotto.calculateNumberOfTickets(purchaseAmount);
        List<List<Integer>> lottoTickets = lotto.generateLottoTickets(numberOfTickets);

        outputView.printPurchaseLotto(numberOfTickets,lottoTickets);
    }
}
