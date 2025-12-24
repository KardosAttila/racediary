CREATE TABLE running_records (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    distance_km DOUBLE PRECISION NOT NULL,
    duration_minutes INT NOT NULL,
    notes TEXT
);