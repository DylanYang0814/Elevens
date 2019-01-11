import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class TensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 13;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public TensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     *
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     * false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        if (selectedCards.size() == 2) {
            return containsPairSum11(selectedCards);
        }
        if (selectedCards.size() == 4) {
            return containsQuartets(selectedCards);
        }
        return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     *
     * @return true if there is a legal play left on the board;
     * false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return containsPairSum11(cardIndexes()) || containsQuartets(cardIndexes());
    }

    /**
     * Check for an 10-pair in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 10-pair.
     * @return true if the board entries in selectedCards
     * contain an 10-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        for (int i = 0; i < selectedCards.size(); i++) {
            for (int j = 0; j < selectedCards.size(); j++) {
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 10) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean containsQuartets(List<Integer> selectedCards) {
        int tens = 0;
        int jacks = 0;
        int queens = 0;
        int kings = 0;

        for (int i = 0; i < selectedCards.size(); i++) {
            if (cardAt(selectedCards.get(i)).pointValue() == 10)
                tens++;
            else if (cardAt(selectedCards.get(i)).rank().equals("jack"))
                jacks++;
            else if (cardAt(selectedCards.get(i)).rank().equals("queen"))
                queens++;
            else if (cardAt(selectedCards.get(i)).rank().equals("king"))
                kings++;
        }
        return tens == 4 || jacks == 4 || queens == 4 || kings == 4;
    }
}
