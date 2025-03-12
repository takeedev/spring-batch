package takee.dev.SpringBatch.step.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import takee.dev.SpringBatch.dao.TransactionDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

@Component
public class TransactionWriter implements ItemWriter<TransactionDao> {

    private static final String FILE_PATH = "output/transactions";

    @Override
    public void write(Chunk<? extends TransactionDao> chunk) throws Exception {
        System.out.println("Start writing chunk");
        checkFolder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + LocalDate.now() + ".txt", true))) {
            for (TransactionDao transactionDao : chunk) {
                String transactionData = formatTransaction(transactionDao);
                writer.write(transactionData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Error writing to file", e);
        }
    }

    private static void checkFolder() {
        File directory = new File("output");
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Output directory created.");
            } else {
                System.out.println("Failed to create output directory.");
            }
        }
    }

    private String formatTransaction(TransactionDao transactionDao) {
        return String.format("%s, %.2f, %s, %s",
                transactionDao.getTranID(),
                transactionDao.getAmount(),
                transactionDao.getData(),
                transactionDao.getDateTrans());
    }
}
