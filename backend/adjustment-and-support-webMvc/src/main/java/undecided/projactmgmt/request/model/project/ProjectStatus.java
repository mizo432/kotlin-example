package undecided.projactmgmt.request.model.project;

public enum ProjectStatus {
    UNDEFINED(0),
    ACCEPTED(1),
    ESTIMATED(2),
    PROGRESSED(3),
    IN_REVIEW(4),
    DONE(7),
    FROZEN(8);

    private final int id;

    ProjectStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
