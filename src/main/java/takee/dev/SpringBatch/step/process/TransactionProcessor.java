package takee.dev.SpringBatch.step.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import takee.dev.SpringBatch.dao.TransactionDao;

@Component
public class TransactionProcessor implements ItemProcessor<TransactionDao, TransactionDao> {

    @Override
    public TransactionDao process(TransactionDao item) {
        System.out.println("start process");
        item.setData("test set process data");
        return item;
    }

}
