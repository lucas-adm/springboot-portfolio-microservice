CREATE TABLE comments (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    social_media VARCHAR(255),
    photo TEXT,
    date_time TIMESTAMP,
    stars INT,
    content TEXT
);