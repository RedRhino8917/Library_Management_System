package org.gfg.minor1.repository;

import jakarta.transaction.Transactional;
import org.gfg.minor1.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TxnRepository extends JpaRepository<Txn,Integer> {
    Txn findByTxnId(String txnId);

    @Modifying
    @Transactional
    @Query(value = "insert into txn(created_on, paid_amount, status, txn_id, updated_on, book_id, student_id) VALUES ('',100,'ISSUED', '', '', 1, 1)", nativeQuery = true)
    public void runMyQuery();
}
