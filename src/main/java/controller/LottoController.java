package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;
import view.InputView;
import view.OutputView;

import java.util.List;

import static domain.LottoResult.calculateLottoResult;

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
        int purchaseAmount = inputView.getPurchaseAmount();
        int numberOfTickets = lotto.calculateNumberOfTickets(purchaseAmount);
        List<LottoTicket> lottoTickets = lotto.generateLottoTickets(numberOfTickets);

        outputView.printPurchaseLotto(numberOfTickets, lottoTickets);

        List<LottoNumber> winningNumbers = inputView.getWinningNumbers();

        LottoResult lottoResult = calculateLottoResult(lottoTickets, winningNumbers);
        outputView.printLottoResult(lottoResult, purchaseAmount);
    }


}
