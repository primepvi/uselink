CREATE TABLE links (
       id BIGINT NOT NULL AUTO_INCREMENT,
       target_url VARCHAR(2048) NOT NULL,
       slug VARCHAR(255),
       accessed_at TIMESTAMP DEFAULT NULL,
       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,           
       PRIMARY KEY (id)
);