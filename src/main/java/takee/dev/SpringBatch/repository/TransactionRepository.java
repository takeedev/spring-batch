package takee.dev.SpringBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import takee.dev.SpringBatch.dao.TransactionDao;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {

    List<TransactionDao> searchByDate(Date date);

}
