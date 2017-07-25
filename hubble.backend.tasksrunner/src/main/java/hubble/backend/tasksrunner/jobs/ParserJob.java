package hubble.backend.tasksrunner.jobs;

import hubble.backend.providers.parsers.interfaces.Parser;

public interface ParserJob extends org.quartz.StatefulJob {

    public Parser getParser();

    public void setParser(Parser parser);
}
