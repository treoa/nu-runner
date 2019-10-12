class QuizFactory extends AssessmentFactory {

    public QuizFactory(Common common) {
        super(common);
    }

    @Override
    public Assessment createAssessment(Vector2D position) {
        return new Quiz("Quiz", position, common.state, common, common.randomInt(4, 7));
    }
}
