package takee.dev.SpringBatch.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "transaction_qr")
public class TransactionDao {

    @Id
    @Column(name = "transID")
    private UUID tranID;

    @Column(name = "data")
    private String data;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private Date date;

}
