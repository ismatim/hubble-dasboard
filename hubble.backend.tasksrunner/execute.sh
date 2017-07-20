#!/bin/bash
# Assure that pom.xml has the plugin already set up.
# This line execute the main method from TassksRunnerApplication class.

printf "\nPackaging Tasks Runner Application\n"
mvn package
printf "\n"
printf "\nExecuting Tasks Runner Application\n"

mvn exec:java -Dexec.mainClass="hubble.backend.tasksrunner.application.TasksRunnerApplication"

printf "\n"

