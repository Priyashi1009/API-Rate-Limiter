package com.apirate;

import com.apirate.model.ClientRateLimit;
import com.apirate.repository.ClientRateLimitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClientRateLimitRepository repository;

    public DataLoader(ClientRateLimitRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        repository.deleteAll();

        
        repository.save(new ClientRateLimit("HOTEL-MATE", 5));
        repository.save(new ClientRateLimit("HOLA-ROOMS", 20));

        System.out.println("Sample data loaded.");
    }
}
