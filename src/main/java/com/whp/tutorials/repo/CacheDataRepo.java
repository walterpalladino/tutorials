package com.whp.tutorials.repo;

import com.whp.tutorials.model.CacheData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDataRepo extends CrudRepository<CacheData, String> {
}
