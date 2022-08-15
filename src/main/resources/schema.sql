CREATE TABLE drone (
    serial_no          VARCHAR NOT NULL PRIMARY KEY,
    model             INTEGER NOT NULL,
    state             INTEGER NOT NULL,
    current_payload_id  VARCHAR,
    battery_level      DOUBLE
);

CREATE TABLE current_payload (
    payload_id                VARCHAR NOT NULL PRIMARY KEY,
    drone_serial_no          VARCHAR NOT NULL UNIQUE
);

CREATE TABLE medication (
    code              VARCHAR NOT NULL PRIMARY KEY,
    name              VARCHAR NOT NULL,
    weight            DOUBLE DEFAULT 0,
    image             VARCHAR,
    payload_id                VARCHAR NOT NULL
);

CREATE TABLE medication_payload (
    droneSerial_no          VARCHAR NOT NULL,
    medication_code              VARCHAR NOT NULL
);