package com.cgr.App.repository;

import com.cgr.App.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IComplaintService extends JpaRepository<Complaint, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Complaints set status='c' where id= :complaints_id")
    @Transactional
    void updateComplaint(@Param("complaints_id") Integer complaints_id);

}
