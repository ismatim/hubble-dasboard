package hubble.backend.tasksrunner.tasks;

import hubble.backend.tasksrunner.jobs.ParserJob;

public interface ParserTask extends Task {

    public ParserJob getParserJob();

    public void setParserJob(ParserJob parserJob);

}
