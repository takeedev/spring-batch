package takee.dev.SpringBatch.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;

@ExtendWith(MockitoExtension.class)
class JobConfigTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private Step step1;

    @InjectMocks
    private JobConfig jobConfig;

    @Test
    void testMyJobBeanCreation() {
        Job job = jobConfig.myJob(jobRepository, step1);
        Assertions.assertThat(job).isNotNull();
        Assertions.assertThat(job.getName()).isEqualTo("myJob");
    }


}