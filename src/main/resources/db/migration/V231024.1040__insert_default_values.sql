--Insert default records for testing purposes
INSERT INTO users (name, last_name, username, password) VALUES ('Christian', 'Cachaya', 'crisgeoca', '$2a$12$oAnzrgmbbDckNodCNcE/BOwjsvkBReyf4CtH6XlA2PgZ0vZS4PDbi');

SELECT @userId := id FROM users WHERE username = 'crisgeoca';
INSERT INTO authorities (authority, user_id) VALUES ('ROLE_ADMIN', @userId);