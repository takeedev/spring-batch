package takee.dev.SpringBatch.step.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import takee.dev.SpringBatch.dao.TransactionDao;
import takee.dev.SpringBatch.repository.TransactionRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionReader implements ItemReader<TransactionDao>, StepExecutionListener {

    private int index = 0;
    private List<TransactionDao> transactionList;
    private final TransactionRepository transactionRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        String dateParam = jobParameters.getString("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println("start reader from date: " + dateParam);
        try {
            transactionList = transactionRepository.searchByDate(dateParam);
            System.out.println("count data: " + transactionList.size());
        } catch (Exception e) {
            transactionList = new ArrayList<>();
            stepExecution.getExecutionContext().put("error", "transaction null pointer: " + e.getMessage());
        }
        index = 0;
    }

    @Override
    public TransactionDao read() {
        if (transactionList != null &&  index < transactionList.size()) {
            return transactionList.get(index++);
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String errorMessage = (String) stepExecution.getExecutionContext().get("error");
        if (errorMessage != null) {
            System.out.println("Error: " + errorMessage);
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }

}
