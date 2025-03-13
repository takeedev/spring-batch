package takee.dev.SpringBatch.step;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.PlatformTransactionManager;
import takee.dev.SpringBatch.dao.TransactionDao;

@ExtendWith(MockitoExtension.class)
class StepJobTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private ItemReader<TransactionDao> reader;

    @Mock
    private ItemProcessor<TransactionDao, TransactionDao> processor;

    @Mock
    private ItemWriter<TransactionDao> writer;

    @InjectMocks
    private StepJob stepJob;

    @Test
    void testMyStepBeanCreation() {
        Step step = stepJob.myStep(jobRepository, transactionManager, reader, processor, writer);
        Assertions.assertThat(step).isNotNull();
        Assertions.assertThat(step.getName()).isEqualTo("myStep");
    }

}