package takee.dev.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatchApplication implements CommandLineRunner{

	private final JobLauncher jobLauncher;
	private final Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		* case custom date with parameter --> 1 2025-02-21
		* case get date from DB with parameter --> 2
		* case get date from system date with parameter --> 3
		*/
		if (args.length >= 2) {
			if (args[0].equals("1")) {
				JobParameters param = new JobParametersBuilder()
						.addString("date", args[1])
						.addLong("time", System.currentTimeMillis())
						.toJobParameters();
				JobExecution execution = jobLauncher.run(job, param);
				System.out.println("Job Execution Status: " + execution.getStatus());
			}
		} else if (args[0].equals("2")) {
			System.out.println("test 2");
		} else if (args[0].equals("3")) {
			System.out.println("test 3");
		} else {
			System.out.println("Invalid arguments. Expected at least 2 parameters or arg[0] is greater than 3");
		}
	}
}
