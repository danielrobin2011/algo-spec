import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchedulingMain {

    class Job {
        int weight;
        int length;
    }

    public List<Job> readInputFile() throws FileNotFoundException {

        List<Job> jobs = new ArrayList<Job>();
        Scanner scanner = new Scanner(new File("src/main/resources/schedule_jobs.txt"));
        scanner.nextLine();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] arrayString = line.split(" ");
            Job job = new Job();
            job.weight = Integer.parseInt(arrayString[0]);
            job.length = Integer.parseInt(arrayString[1]);

        }
        return jobs;
    }


}
