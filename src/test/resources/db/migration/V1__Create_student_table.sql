create table if not exists  ITEM (
  ID INT NOT NULL,
  DESCRIPTION varchar(30) DEFAULT NULL,
  NAME varchar(30) DEFAULT NULL,
  PRICE double DEFAULT NULL,
  PRIMARY KEY (ID)
  )

