import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuestionPanel extends JPanel implements ActionListener {
    private static final int ANSWERS_NUMBER = 4;
    private static final int CORRECT_ANSWER_POINTS = 10;
    private static final int WRONG_ANSWER_POINTS = 5;
    private ArrayList<Question> questions;
    private int questionNum;
    private int points;
    private String correctAns;
    private JPanel questionPanel;
    private JPanel answersPanel;
    private JPanel buttonPanel;
    private JButton startNewGameButton = new JButton("New Game");
    private JButton finishGameButton = new JButton("Finish Game");
    private ButtonGroup group;
    private JRadioButton[] responses = new JRadioButton[4];
    private JLabel qLabel = new JLabel();
    private Timer timer;

    public QuestionPanel(ArrayList<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
        initPanels();
        initTimer();
        initButtons();
        showNextQuestion();
        add(questionPanel, BorderLayout.NORTH);
        add(answersPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initButtons() {
        startNewGameButton.addActionListener(this);
        finishGameButton.addActionListener(this);
        buttonPanel.add(startNewGameButton);
        buttonPanel.add(finishGameButton);
    }

    private void initPanels() {
        setLayout(new BorderLayout());
        questionPanel = new JPanel();
        answersPanel = new JPanel();
        group = new ButtonGroup();
        buttonPanel = new JPanel();
        questionPanel.add(qLabel);
        answersPanel.setLayout(new GridLayout(ANSWERS_NUMBER, 1));
        for (int i = 0; i < ANSWERS_NUMBER; i++) {
            responses[i] = new JRadioButton();
            responses[i].addActionListener(this);
        }
    }

    private void initTimer() {
        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                points -= WRONG_ANSWER_POINTS;
                JOptionPane.showMessageDialog(null, "Time is up! Next question!" +
                        "\nYou lost " + WRONG_ANSWER_POINTS + " points!");
                nextQuestion();
            }
        });
        timer.start();
    }

    private void showNextQuestion() {
        timer.restart();
        group.clearSelection();
        Question q = questions.get(questionNum++);
        correctAns = q.getRightAnswer();
        qLabel.setText(q.getQuestion());
        String[] answers = {correctAns,
                q.getWrongAnswer1(),
                q.getWrongAnswer2(),
                q.getWrongAnswer3()};
        Collections.shuffle(Arrays.asList(answers));
        for (int i = 0; i < answers.length; i++) {
            responses[i].setText(answers[i]);
            group.add(responses[i]);
            answersPanel.add(responses[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src instanceof JRadioButton) {
            checkAnswer((JRadioButton) src);
        }
        if (src.equals(startNewGameButton)) {
            JOptionPane.showMessageDialog(null, "Starting new game...");
            startNewGame();
        }
        if (src.equals(finishGameButton)) {
            JOptionPane.showMessageDialog(null, "Game Finished! Total Points: " + points);
            finishGame();
        }
    }

    private void checkAnswer(JRadioButton src) {
        if (src.getText().equals(correctAns)) {
            points += CORRECT_ANSWER_POINTS;
            timer.stop();
            JOptionPane.showMessageDialog(null, "Correct Answer!!!" +
                    "\nYou got " + CORRECT_ANSWER_POINTS + " points!");
        } else {
            points -= WRONG_ANSWER_POINTS;
            timer.stop();
            JOptionPane.showMessageDialog(null, "Wrong Answer..." +
                    "\nYou lost " + WRONG_ANSWER_POINTS + " points!");
        }
        nextQuestion();
    }

    private void nextQuestion() {
        if (questionNum >= questions.size()) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Game Finished! Total Points: " + points + "\nWould You Like to play another game?",
                    "End of the game", dialogButton);

            if (dialogResult == JOptionPane.YES_OPTION) {
                startNewGame();
            } else {
                finishGame();
            }
        } else {
            showNextQuestion();
        }
    }

    private void finishGame() {
        timer.stop();
        System.exit(0);
    }

    private void startNewGame() {
        Collections.shuffle(questions);
        timer.restart();
        points = 0;
        questionNum = 0;
        showNextQuestion();
    }
}