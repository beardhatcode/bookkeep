CREATE TYPE STATEMENTKIND AS ENUM ('PAYS_FOR', 'REIMBURSES');

CREATE TABLE organisations
(
  id   BIGSERIAL   NOT NULL,
  name VARCHAR(75) NOT NULL,
  CONSTRAINT organisations_pkey
  PRIMARY KEY (id)
);

COMMENT ON TABLE organisations IS 'Organisations';

CREATE TABLE sources
(
  id    BIGSERIAL    NOT NULL,
  class VARCHAR(150) NOT NULL,
  type  VARCHAR(20)  NOT NULL,
  name  VARCHAR(20)  NOT NULL,
  CONSTRAINT sources_pkey
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX sources_name_type_uindex
  ON sources (name, type);

COMMENT ON TABLE sources IS 'Sources';

CREATE TABLE statements
(
  id        BIGSERIAL                NOT NULL,
  amount    NUMERIC(12, 2)           NOT NULL,
  sender    VARCHAR(150)             NOT NULL,
  receiver  VARCHAR(150)             NOT NULL,
  comment   TEXT,
  date      TIMESTAMP WITH TIME ZONE NOT NULL,
  note      TEXT,
  raw       TEXT,
  source_id BIGINT                   NOT NULL,
  CONSTRAINT statements_pkey
  PRIMARY KEY (id),
  CONSTRAINT statements_source_id_fkey
  FOREIGN KEY (source_id) REFERENCES sources
);

COMMENT ON TABLE statements IS 'Bank statements';

CREATE TABLE invoices
(
  id              BIGSERIAL      NOT NULL,
  name            VARCHAR(75)    NOT NULL,
  organisation_id BIGINT,
  type_id         BIGINT,
  amount          NUMERIC(12, 2) NOT NULL,
  note            TEXT,
  filename        VARCHAR(200),
  content         TEXT,
  paid            BIGINT,
  raw             TEXT,
  source_id       BIGINT         NOT NULL,
  date            TIMESTAMP WITH TIME ZONE,
  CONSTRAINT invoices_pkey
  PRIMARY KEY (id),
  CONSTRAINT invoices_organisation_id_fkey
  FOREIGN KEY (organisation_id) REFERENCES organisations
);

COMMENT ON TABLE invoices IS 'Invoices and receipts';

CREATE TABLE warranties
(
  id      BIGSERIAL   NOT NULL,
  name    VARCHAR(75) NOT NULL,
  receipt BIGINT,
  note    TEXT,
  CONSTRAINT warranties_pkey
  PRIMARY KEY (id),
  CONSTRAINT warranties_invoices_id_fk
  FOREIGN KEY (receipt) REFERENCES invoices
);

COMMENT ON TABLE warranties IS 'Tags';

CREATE TABLE statements_relations
(
  subject BIGINT        NOT NULL,
  object  BIGINT        NOT NULL,
  kind    STATEMENTKIND NOT NULL,
  invoice BIGINT,
  CONSTRAINT statements_relations_pkey
  PRIMARY KEY (subject, object),
  CONSTRAINT statements_relations_subject_fkey
  FOREIGN KEY (subject) REFERENCES statements,
  CONSTRAINT statements_relations_object_fkey
  FOREIGN KEY (object) REFERENCES statements,
  CONSTRAINT statements_relations_invoice_fkey
  FOREIGN KEY (invoice) REFERENCES invoices
);

COMMENT ON TABLE statements_relations IS 'Relations between statements';

CREATE TABLE statements_invoices
(
  statement_id BIGINT NOT NULL,
  invoice_id   BIGINT NOT NULL,
  note         TEXT,
  CONSTRAINT statements_invoices_pkey
  PRIMARY KEY (statement_id, invoice_id),
  CONSTRAINT statements_invoices_statement_id_fkey
  FOREIGN KEY (statement_id) REFERENCES statements,
  CONSTRAINT statements_invoices_invoice_id_fkey
  FOREIGN KEY (invoice_id) REFERENCES invoices
);

COMMENT ON TABLE statements_invoices IS 'invoices per statement (and reverse) N->N';

CREATE TABLE tags
(
  id   BIGSERIAL   NOT NULL,
  name VARCHAR(75) NOT NULL,
  CONSTRAINT tags_pkey
  PRIMARY KEY (id)
);


