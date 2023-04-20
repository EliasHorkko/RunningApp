-- Create users table
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

-- Insert sample data into users table
INSERT INTO users (name, username, email, password) 
VALUES ('Example', 'Example123', '123@example.com', 'password123');

-- Create events table
CREATE TABLE IF NOT EXISTS events (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  description TEXT NOT NULL,
  user_id BIGINT NOT NULL REFERENCES users(id)
);

-- Insert sample data into events table
INSERT INTO events (name, date, description, user_id) 
VALUES ('Running Marathon', '2023-05-15', 'Join the annual running marathon!', 1);

-- Create participants table
CREATE TABLE IF NOT EXISTS participants (
  id SERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  event_id BIGINT NOT NULL REFERENCES events(id)
);

-- Insert sample data into participants table
INSERT INTO participants (user_id, event_id) 
VALUES (1, 1);
