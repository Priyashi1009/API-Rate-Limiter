package com.apirate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apiratelimiter")
public class ClientRateLimit {

    @Id
    private String clientId;
    private int requestsPerMinute;

    
    public ClientRateLimit() {}

    public ClientRateLimit(String clientId, int requestsPerMinute) {
        this.clientId = clientId;
        this.requestsPerMinute = requestsPerMinute;
    }

    
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getRequestsPerMinute() {
        return requestsPerMinute;
    }

    public void setRequestsPerMinute(int requestsPerMinute) {
        this.requestsPerMinute = requestsPerMinute;
    }
}
