CREATE TABLE PAGE (
     "ID" VARCHAR(50) NOT NULL PRIMARY KEY,
     "UNLOCKED" BOOLEAN DEFAULT FALSE NOT NULL,
     "UNLOCKED_ON" TIMESTAMP,
     "ORDER" INT NOT NULL
);
