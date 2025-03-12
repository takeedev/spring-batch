package takee.dev.SpringBatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import takee.dev.SpringBatch.dao.TransactionDao;

@Configuration
public class StepJob {

    @Bean
    public Step myStep(JobRepository jobRepository,
                       PlatformTransactionManager transactionManager,
                       ItemReader<TransactionDao> reader,
                       ItemProcessor<TransactionDao, TransactionDao> processor,
                       ItemWriter<TransactionDao> writer) {
        return new StepBuilder("myStep", jobRepository)
                .<TransactionDao, TransactionDao>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
