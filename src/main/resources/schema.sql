CREATE TABLE userinfo (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  user_uuid uuid default random_uuid(),
  name VARCHAR(500) NOT NULL,
  email VARCHAR(500) NOT NULL,
  password VARCHAR(150) NOT NULL,
  active bit NOT NULL default 0,
  creation_date datetime NOT NULL,
  created_by VARCHAR(120) NULL,
  update_date datetime NULL,
  updated_by VARCHAR(120) NULL
);

CREATE TABLE phone (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  number VARCHAR(500) NOT NULL,
  city_code VARCHAR(500) NOT NULL,
  country_code VARCHAR(500) NOT NULL,
  userinfo_id BIGINT NOT NULL,
  active bit NOT NULL default 0,
  creation_date datetime NOT NULL,
  created_by VARCHAR(120) NULL,
  update_date datetime NULL,
  updated_by VARCHAR(120) NULL
);

ALTER TABLE phone
ADD FOREIGN KEY (userinfo_id)
REFERENCES userinfo(id);