public class Question {
    private String question;
    private String rightAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public Question() {
    }

    @Override
    public String toString() {
        return "Question: " + question +
                "\n rightAnswer: " + rightAnswer +
                "\n wrongAnswer1: " + wrongAnswer1 +
                "\n wrongAnswer2: " + wrongAnswer2 +
                "\n wrongAnswer3: " + wrongAnswer3 + "\n";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }
}
