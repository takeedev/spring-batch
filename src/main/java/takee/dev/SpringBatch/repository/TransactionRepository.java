package takee.dev.SpringBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import takee.dev.SpringBatch.dao.TransactionDao;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {

    @Query(value = "SELECT * FROM transaction_qr WHERE date_trans = :date ", nativeQuery = true)
    List<TransactionDao> searchByDate(@Param("date") String date);

}
