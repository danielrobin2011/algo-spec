import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class SchedulingMain {

    class Job {

        double weight;
        double length;
        double score;

        Job(double weight, double length) {
            this.weight = weight;
            this.length = length;
            this.score = (weight / length); // change strategy here '-' or '/'
        }

        double getWeight() {
            return this.weight;
        }

        double getScore() {
            return this.score;
        }

        @Override
        public String toString(){
            return "weight: "+this.weight+", length: "+this.length;
        }

    }

    public List<Job> readInputFile() throws FileNotFoundException {
        List<Job> jobs = new ArrayList<Job>();
        Scanner scanner = new Scanner(new File("src/main/resources/schedule_jobs.txt"));
        scanner.nextLine();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] arrayString = line.split(" ");
            int weight = Integer.parseInt(arrayString[0]);
            int length = Integer.parseInt(arrayString[1]);
            Job job = new Job(weight, length);
            jobs.add(job);
        }
        return jobs;
    }

    public BigDecimal calculateWeightedCompletionTime(List<Job> jobs) {

        BigDecimal result = new BigDecimal(0);
        long cumulativeTime = 0;
        for (Job job : jobs) {
            cumulativeTime += job.length;
            result = result.add(BigDecimal.valueOf(job.weight * cumulativeTime));
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {

        SchedulingMain schedulingMain = new SchedulingMain();
        List<Job> jobs = schedulingMain.readInputFile();
        Collections.sort(jobs, Comparator.comparing(Job::getScore)
                .thenComparing(Job::getWeight)
                .reversed()
        );
        BigDecimal result = schedulingMain.calculateWeightedCompletionTime(jobs);
        System.out.println(result);
        // for w - l = 69119377652
        // for w / l = 67311454237
    }

}
