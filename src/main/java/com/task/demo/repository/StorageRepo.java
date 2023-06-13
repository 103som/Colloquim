package com.task.demo.repository;

import com.task.demo.entity.Storage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends CrudRepository<Storage, Long> {
    @Query("SELECT c.id FROM Storage c WHERE c.address = :address")
    Integer findByAddress(String address);
    Storage findById(Integer id);
}
