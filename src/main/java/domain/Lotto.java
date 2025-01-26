package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public int calculateNumberOfTickets(int amount) {
        return amount / LOTTO_PRICE;
    }

    public List<List<Integer>> generateLottoTickets(int numberOfTickets) {
        List<List<Integer>> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            List<Integer> ticket = generateSingleLottoTicket();
            tickets.add(ticket);
        }

        return tickets;
    }

    private List<Integer> generateSingleLottoTicket() {
        List<Integer> numbersPool = createNumbersPool();
        Collections.shuffle(numbersPool);
        List<Integer> ticket = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            ticket.add(numbersPool.get(i));
        }
        Collections.sort(ticket);

        return ticket;
    }

    public List<Integer> createNumbersPool() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            numbers.add(number);
        }

        return numbers;
    }
}
