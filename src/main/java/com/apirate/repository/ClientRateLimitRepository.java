package com.apirate.repository;

import com.apirate.model.ClientRateLimit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRateLimitRepository extends MongoRepository<ClientRateLimit, String> {
    ClientRateLimit findByClientId(String clientId);
}
