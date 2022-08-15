CREATE TABLE drone (
    serialNo          VARCHAR NOT NULL PRIMARY KEY,
    model             VARCHAR NOT NULL,
    state             VARCHAR NOT NULL,
    currentPayloadId  VARCHAR
);

CREATE TABLE payload (
    id                VARCHAR NOT NULL PRIMARY KEY
);

CREATE TABLE medication (
    code              VARCHAR NOT NULL PRIMARY KEY,
    name              VARCHAR NOT NULL,
    weight            DECFLOAT(4) DEFAULT 0,
    image             VARCHAR
);

CREATE TABLE drone_payload (
    droneSerialNo          VARCHAR NOT NULL,
    payloadId                VARCHAR NOT NULL
);

CREATE TABLE medication_payload (
    droneSerialNo          VARCHAR NOT NULL,
    medicationCode              VARCHAR NOT NULL
);