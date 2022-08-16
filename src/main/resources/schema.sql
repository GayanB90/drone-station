CREATE TABLE drone (
    serial_no          VARCHAR NOT NULL PRIMARY KEY,
    model             INTEGER NOT NULL,
    state             INTEGER NOT NULL,
    current_payload_id  VARCHAR,
    battery_level      DOUBLE
);

CREATE TABLE current_payload (
    payload_id                VARCHAR NOT NULL PRIMARY KEY,
    received_time             TIMESTAMP NOT NULL
);

CREATE TABLE drone_payload (
    drone             VARCHAR NOT NULL,
    payload_id        VARCHAR NOT NULL
);

ALTER TABLE drone_payload
    ADD FOREIGN KEY (drone)
    REFERENCES drone(serial_no);

ALTER TABLE drone_payload
    ADD FOREIGN KEY (payload_id)
    REFERENCES current_payload(payload_id);

CREATE TABLE medication (
    code              VARCHAR NOT NULL PRIMARY KEY,
    name              VARCHAR NOT NULL,
    weight            DOUBLE DEFAULT 0,
    image             VARCHAR,
    payload_id                VARCHAR NOT NULL
);

ALTER TABLE medication
    ADD FOREIGN KEY (payload_id)
    REFERENCES current_payload(payload_id);