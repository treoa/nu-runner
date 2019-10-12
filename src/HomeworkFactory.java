class HomeworkFactory extends AssessmentFactory {

    public HomeworkFactory(Common common) {
        super(common);
    }

    @Override
    public Assessment createAssessment(Vector2D position) {
        return new Homework("HW", position, common.state, common, common.randomInt(1, 10));
    }
}
