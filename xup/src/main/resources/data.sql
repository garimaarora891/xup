DROP TABLE IF EXISTS xup_monitoring;

CREATE TABLE xup_monitoring (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  frequency VARCHAR(250) not NULL,
  duration INT not NULL,
  status VARCHAR(250) ,
  count INT,
  UNIQUE KEY url_unique (url)
);