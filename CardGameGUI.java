import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


/**
 * This class provides a GUI for solitaire games related to Elevens.
 */
public class CardGameGUI extends JFrame implements ActionListener {


    /** Height of the game frame. */
    private static final int DEFAULT_HEIGHT = 302;
    /** Width of the game frame. */
    private static final int DEFAULT_WIDTH = 800;
    /** Width of a card. */
    private static final int CARD_WIDTH = 73;
    /** Height of a card. */
    private static final int CARD_HEIGHT = 97;
    /** Row (y coord) of the upper left corner of the first card. */
    private static final int LAYOUT_TOP = 30;
    /** Column (x coord) of the upper left corner of the first card. */
    private static final int LAYOUT_LEFT = 30;
    /** Distance between the upper left x coords of
     *  two horizonally adjacent cards. */
    private static final int LAYOUT_WIDTH_INC = 100;
    /** Distance between the upper left y coords of
     *  two vertically adjacent cards. */
    private static final int LAYOUT_HEIGHT_INC = 125;
    /** y coord of the "Replace" button. */
    private static final int BUTTON_TOP = 30;
    /** x coord of the "Replace" button. */
    private static final int BUTTON_LEFT = 570;
    /** Distance between the tops of the "Replace" and "Restart" buttons. */
    private static final int BUTTON_HEIGHT_INC = 50;
    /** y coord of the "n undealt cards remain" label. */
    private static final int LABEL_TOP = 160;
    /** x coord of the "n undealt cards remain" label. */
    private static final int LABEL_LEFT = 540;
    /** Distance between the tops of the "n undealt cards" and
     *  the "You lose/win" labels. */
    private static final int LABEL_HEIGHT_INC = 35;

    /** The board (Board subclass). */
    private Board board;

    /** The main panel containing the game components. */
    private JPanel panel;
    /** The Replace button. */
    private JButton replaceButton;
    /** The Restart button. */
    private JButton restartButton;
    /** The "number of undealt cards remain" message. */
    private JLabel statusMsg;
    /** The "you've won n out of m games" message. */
    private JLabel totalsMsg;
    /** The card displays. */
    private JLabel[] displayCards;
    /** The win message. */
    private JLabel winMsg;
    /** The loss message. */
    private JLabel lossMsg;
    /** The coordinates of the card displays. */
    private Point[] cardCoords;

    /** kth element is true iff the user has selected card #k. */
    private boolean[] selections;
    /** The number of games won. */
    private int totalWins;
    /** The number of games played. */
    private int totalGames;


    /**
     * Initialize the GUI.
     * @param gameBoard is a <code>Board</code> subclass.
     */
    public CardGameGUI(Board gameBoard) {
        board = gameBoard;
        totalWins = 0;
        totalGames = 0;

        // Initialize cardCoords using 5 cards per row
        cardCoords = new Point[board.size()];
        int x = LAYOUT_LEFT;
        int y = LAYOUT_TOP;
        for (int i = 0; i < cardCoords.length; i++) {
            cardCoords[i] = new Point(x, y);
            if (i % 5 == 4) {
                x = LAYOUT_LEFT;
                y += LAYOUT_HEIGHT_INC;
            } else {
                x += LAYOUT_WIDTH_INC;
            }
        }

        selections = new boolean[board.size()];
        initDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }

    /**
     * Run the game.
     */
    public void displayGame() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    /**
     * Draw the display (cards and messages).
     */
    public void repaint() {
        for (int k = 0; k < board.size(); k++) {
            String cardImageFileName =
                imageFileName(board.cardAt(k), selections[k]);
            URL imageURL = getClass().getResource(cardImageFileName);
            if (imageURL == null) {
                // Try the other case extension
                String altFileName = cardImageFileName.replace(".gif", ".GIF");
                if (altFileName.equals(cardImageFileName)) {
                    altFileName = cardImageFileName.replace(".GIF", ".gif");
                }
                imageURL = getClass().getResource(altFileName);
            }
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                displayCards[k].setIcon(icon);
                displayCards[k].setVisible(true);
            } else {
                throw new RuntimeException(
                    "Card image not found: \"" + cardImageFileName + "\"");
            }
        }
        statusMsg.setText(board.deckSize()
            + " undealt cards remain.");
        statusMsg.setVisible(true);
        totalsMsg.setText("You've won " + totalWins
             + " out of " + totalGames + " games.");
        totalsMsg.setVisible(true);
        pack();
        panel.repaint();
    }

    /**
     * Initialize the display.
     */
    private void initDisplay()    {
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set a subtle gradient background
                setBackground(Color.decode("#F5F5F5"));
            }
        };

        // If board object's class name follows the standard format
        // of ...Board or ...board, use the prefix for the JFrame title
        String className = board.getClass().getSimpleName();
        int classNameLen = className.length();
        int boardLen = "Board".length();
        String boardStr = className.substring(classNameLen - boardLen);
        if (boardStr.equals("Board") || boardStr.equals("board")) {
            int titleLength = classNameLen - boardLen;
            setTitle(className.substring(0, titleLength));
        }

        // Calculate number of rows of cards (5 cards per row)
        // and adjust JFrame height if necessary
        int numCardRows = (board.size() + 4) / 5;
        int height = DEFAULT_HEIGHT;
        if (numCardRows > 2) {
            height += (numCardRows - 2) * LAYOUT_HEIGHT_INC;
        }

        this.setSize(new Dimension(DEFAULT_WIDTH, height));
        panel.setLayout(null);
        panel.setPreferredSize(
            new Dimension(DEFAULT_WIDTH - 20, height - 20));
        displayCards = new JLabel[board.size()];
        for (int k = 0; k < board.size(); k++) {
            displayCards[k] = new JLabel();
            panel.add(displayCards[k]);
            displayCards[k].setBounds(cardCoords[k].x, cardCoords[k].y,
                                        CARD_WIDTH, CARD_HEIGHT);
            displayCards[k].addMouseListener(new MyMouseListener());
            selections[k] = false;
        }
        replaceButton = new JButton();
        replaceButton.setText("Replace");

        // Enhanced styling for Replace button - Pink theme to match hearts/diamonds
        replaceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        replaceButton.setBackground(Color.decode("#FF69B4")); // Hot pink
        replaceButton.setForeground(Color.WHITE);
        replaceButton.setFocusPainted(false);
        replaceButton.setBorder(null); // No border
        replaceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panel.add(replaceButton);
        replaceButton.setBounds(BUTTON_LEFT, BUTTON_TOP, 100, 35);
        replaceButton.addActionListener(this);

        restartButton = new JButton();
        restartButton.setText("Restart");
        
        // Enhanced styling for Restart button - Dark theme to match spades/clubs
        restartButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        restartButton.setBackground(Color.decode("#2C2C2C")); // Dark charcoal
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setBorder(null); // No border
        restartButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        panel.add(restartButton);
        restartButton.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 100, 35);
        restartButton.addActionListener(this);

        // Calculate Y position for bottom row of cards
        int bottomRowY = LAYOUT_TOP + LAYOUT_HEIGHT_INC; // Y position of bottom row
        
        statusMsg = new JLabel(
            board.deckSize() + " undealt cards remain.");
        statusMsg.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusMsg.setForeground(Color.decode("#333333"));
        panel.add(statusMsg);
        statusMsg.setBounds(BUTTON_LEFT - 100, bottomRowY + 20, 200, 20);

        winMsg = new JLabel();
        winMsg.setBounds(BUTTON_LEFT - 100, bottomRowY + 50, 200, 30);
        winMsg.setFont(new Font("Segoe UI", Font.BOLD, 25));
        winMsg.setForeground(Color.decode("#4CAF50"));
        winMsg.setText("🎉 You win! 🎉");
        panel.add(winMsg);
        winMsg.setVisible(false);

        lossMsg = new JLabel();
        lossMsg.setBounds(BUTTON_LEFT - 100, bottomRowY + 50, 200, 30);
        lossMsg.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lossMsg.setForeground(Color.decode("#F44336"));
        lossMsg.setText("😔 Sorry, you lose.");
        panel.add(lossMsg);
        lossMsg.setVisible(false);

        totalsMsg = new JLabel("You've won " + totalWins
            + " out of " + totalGames + " games.");
        totalsMsg.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        totalsMsg.setForeground(Color.decode("#333333"));
        totalsMsg.setBounds(BUTTON_LEFT - 100, bottomRowY + 45, 200, 20);
        panel.add(totalsMsg);

        if (!board.anotherPlayIsPossible()) {
            signalLoss();
        }

        pack();
        getContentPane().add(panel);
        getRootPane().setDefaultButton(replaceButton);
        panel.setVisible(true);
    }

    /**
     * Deal with the user clicking on something other than a button or a card.
     */
    private void signalError() {
        Toolkit t = panel.getToolkit();
        t.beep();
    }

    /**
     * Returns the image that corresponds to the input card.
     * Image names have the format "[Rank][Suit].GIF" or "[Rank][Suit]S.GIF",
     * for example "aceclubs.GIF" or "8heartsS.GIF". The "S" indicates that
     * the card is selected.
     *
     * @param c Card to get the image for
     * @param isSelected flag that indicates if the card is selected
     * @return String representation of the image
     */
    private String imageFileName(Card c, boolean isSelected) {
        if (c == null) {
            return "sanriocards/back1.gif";
        }
        return "sanriocards/" + c.toString() + (isSelected? "S" : "") + ".GIF";
    }

    /**
     * Respond to a button click (on either the "Replace" button
     * or the "Restart" button).
     * @param e the button click action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(replaceButton)) {
            // Gather all the selected cards.
            List<Integer> selection = new ArrayList<Integer>();
            for (int k = 0; k < board.size(); k++) {
                if (selections[k]) {
                    selection.add(new Integer(k));
                }
            }
            // Make sure that the selected cards represent a legal replacement.
            if (!board.isLegal(selection)) {
                signalError();
                return;
            }
            for (int k = 0; k < board.size(); k++) {
                selections[k] = false;
            }
            // Do the replace.
            board.replaceSelectedCards(selection);
            if (board.isEmpty()) {
                signalWin();
            } else if (!board.anotherPlayIsPossible()) {
                signalLoss();
            }
            repaint();
        //restart
        } else if (e.getSource().equals(restartButton)) {
            board.newGame();
            getRootPane().setDefaultButton(replaceButton);
            winMsg.setVisible(false);
            lossMsg.setVisible(false);
            if (!board.anotherPlayIsPossible()) {
                signalLoss();
                lossMsg.setVisible(true);
            }
            for (int i = 0; i < selections.length; i++) {
                selections[i] = false;
            }
            repaint();
        } else {
            signalError();
            return;
        }
    }

    /**
     * Display a win.
     */
    private void signalWin() {
        getRootPane().setDefaultButton(restartButton);
        winMsg.setVisible(true);
        totalWins++;
        totalGames++;
    }

    /**
     * Display a loss.
     */
    private void signalLoss() {
        getRootPane().setDefaultButton(restartButton);
        lossMsg.setVisible(true);
        totalGames++;
    }

    /**
     * Receives and handles mouse clicks.  Other mouse events are ignored.
     */
    private class MyMouseListener implements MouseListener {

        /**
         * Handle a mouse click on a card by toggling its "selected" property.
         * Each card is represented as a label.
         * @param e the mouse event.
         */
        public void mouseClicked(MouseEvent e) {
            for (int k = 0; k < board.size(); k++) {
                if (e.getSource().equals(displayCards[k])
                        && board.cardAt(k) != null) {
                    selections[k] = !selections[k];
                    repaint();
                    return;
                }
            }
            signalError();
        }

        /**
         * Ignore a mouse exited event.
         * @param e the mouse event.
         */
        public void mouseExited(MouseEvent e) {
        }

        /**
         * Ignore a mouse released event.
         * @param e the mouse event.
         */
        public void mouseReleased(MouseEvent e) {
        }

        /**
         * Ignore a mouse entered event.
         * @param e the mouse event.
         */
        public void mouseEntered(MouseEvent e) {
        }

        /**
         * Ignore a mouse pressed event.
         * @param e the mouse event.
         */
        public void mousePressed(MouseEvent e) {
        }
    }
}
