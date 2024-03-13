package undecided.projactmgmt.act.application.command.story;

import undecided.projactmgmt.act.model.story.Satisfactions;

public record SetValueRequest(Satisfactions fullyImplementedSatisfactions, Satisfactions notImplementedSatisfactions) {
}
