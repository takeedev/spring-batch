package takee.dev.SpringBatch.step.reader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import takee.dev.SpringBatch.dao.TransactionDao;
import takee.dev.SpringBatch.repository.TransactionRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionReaderTest {

    @Mock
    private StepExecution stepExecution;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionReader transactionReader;

    @BeforeEach
    void setUp() {
        StepSynchronizationManager.register(new StepContext(stepExecution).getStepExecution());
    }

    @Test
    void testBeforeStep_WithData() {
        String testDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<TransactionDao> mockTransactions = Arrays.asList(new TransactionDao(), new TransactionDao());

        Mockito.when(stepExecution.getJobParameters()).thenReturn(new JobParametersBuilder().addString("date", testDate).toJobParameters());
        Mockito.when(transactionRepository.searchByDate(testDate)).thenReturn(mockTransactions);

        transactionReader.beforeStep(stepExecution);

        Assertions.assertThat(transactionReader.read()).isNotNull();
        Assertions.assertThat(transactionReader.read()).isNotNull();
        Assertions.assertThat(transactionReader.read()).isNull();
    }

}