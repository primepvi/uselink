package com.uselink.api.domain.link;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "links")
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "accessed_at")
    private LocalDateTime accessedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    
    public Link() {}
    public Link(Long id, String targetUrl, LocalDateTime accessedAt, LocalDateTime createdAt) {
    	this.id = id;
    	this.targetUrl = targetUrl;
    	this.accessedAt = accessedAt;
    	this.createdAt = createdAt;
    }
    
    public Long getId() { return id; }
    public String getTargetUrl() { return targetUrl; }
    public LocalDateTime getAccessedAt() { return accessedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public void setTargetUrl(String targetUrl) {
    	this.targetUrl = targetUrl;
    }
    
    public void registerAccess() {
    	this.accessedAt = LocalDateTime.now();
    }
}
