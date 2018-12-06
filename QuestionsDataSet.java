import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionsDataSet {
    private ArrayList<Question> questions = new ArrayList<>();

    QuestionsDataSet(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int cnt = 0;
        String st;
        Question q = null;
        while (input.hasNext()) {
            st = input.nextLine();
            switch (cnt) {
                case 0:
                    q = new Question();
                    q.setQuestion(st);
                    break;
                case 1:
                    q.setRightAnswer(st);
                    break;
                case 2:
                    q.setWrongAnswer1(st);
                    break;
                case 3:
                    q.setWrongAnswer2(st);
                    break;
                case 4:
                    q.setWrongAnswer3(st);
                    questions.add(q);
                    break;
                default:
                    break;
            }
            if (cnt == 4) {
                cnt = 0;
            } else {
                cnt++;
            }

        }
        input.close();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
