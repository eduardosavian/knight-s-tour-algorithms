-- create_tables.sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    "password" VARCHAR(256) NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE requesitions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    title VARCHAR NOT NULL,
    content TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE knights_tour (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    "algorithm" VARCHAR(2) NOT NULL,
    boardSize INTEGER NOT NULL,
    start_x INTEGER NOT NULL,
    start_y INTEGER NOT NULL,
    steps INTEGER,
	created_at TIMESTAMP DEFAULT NOW()
);
