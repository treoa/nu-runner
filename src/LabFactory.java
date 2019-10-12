class LabFactory extends AssessmentFactory {

    public LabFactory(Common common) {
        super(common);
    }

    @Override
    public Assessment createAssessment(Vector2D position) {
        return new Lab(position, common.state, "Lab work", common, common.randomInt(1, 6));
    }
}
