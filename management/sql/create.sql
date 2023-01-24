CREATE TABLE prospects (
  id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  company VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  phone VARCHAR,
  stage VARCHAR NOT NULL,
  notes TEXT
);

CREATE TABLE emails (
  id serial PRIMARY KEY,
  prospect_id INTEGER REFERENCES prospects(id),
  subject VARCHAR NOT NULL,
  body TEXT NOT NULL,
  sent_at TIMESTAMP WITH TIME ZONE NOT NULL,
  response VARCHAR
);

CREATE TABLE follow_up_emails (
  id serial PRIMARY KEY,
  email_id INTEGER REFERENCES emails(id),
  subject VARCHAR NOT NULL,
  body TEXT NOT NULL,
  sent_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE social_accounts (
  id serial PRIMARY KEY,
  prospect_id INTEGER REFERENCES prospects(id),
  platform VARCHAR NOT NULL,
  username VARCHAR NOT NULL
);

