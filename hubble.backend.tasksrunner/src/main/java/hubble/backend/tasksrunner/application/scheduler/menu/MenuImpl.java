package hubble.backend.tasksrunner.application.scheduler.menu;

import hubble.backend.tasksrunner.application.scheduler.SchedulerUserCommands;
import java.util.Scanner;
import org.quartz.SchedulerException;

public class MenuImpl implements Menu {

    private Scanner input = new Scanner(System.in);
    private SchedulerUserCommands scheduler;

    public MenuImpl(SchedulerUserCommands paramScheduler) throws SchedulerException {
        scheduler = paramScheduler;
    }

    @Override
    public void execute() {

        try {
            boolean show = true;
            while (show) {
                show = this.displayMenu();
            }

            System.exit(0);
        } catch (SchedulerException ex) {
            System.out.println(ex);
        }
    }

    private boolean displayMenu() throws SchedulerException {
        System.out.println("-- Actions --");
        System.out.println(
                "Select an option: \n"
                + "  1) Run\n"
                + "  2) Stop Tasks Runner\n"
                + "  3) Print Information \n"
                + "  4) Exit\n "
        );

        int selection = input.nextInt();
        input.nextLine();

        switch (selection) {
            case 1:
                this.run();
                break;
            case 2:
                this.stop();
                break;
            case 3:
                this.print();
                break;
            case 4:
                this.exit();
                return false;
            default:
                System.out.println("Invalid selection.");
                break;
        }
        return true;
    }

    public void exit() {
        scheduler.shutdown();
        System.out.println("Exiting...");
    }

    public void print() {
        scheduler.print();
    }

    public void stop() {
        scheduler.pause();
    }

    public void run() {
        // and start it off
        scheduler.start();
    }
}
