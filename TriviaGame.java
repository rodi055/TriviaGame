import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TriviaGame {

    public static void main(String[] args) {
        QuestionsDataSet questionsDataSet = getQuestionsDataSet();

        JFrame trivia = new JFrame("Trivia questions");
        trivia.setSize(400, 300);
        JPanel questionPanel = new JPanel();
        questionPanel.add(new QuestionPanel(questionsDataSet.getQuestions()), BorderLayout.CENTER);
        trivia.add(questionPanel);
        trivia.setVisible(true);
        trivia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static QuestionsDataSet getQuestionsDataSet() {
        QuestionsDataSet questionsDataSet = null;
        try {
            return new QuestionsDataSet(new File("trivia.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(-1);
        }
        return questionsDataSet;
    }
}
