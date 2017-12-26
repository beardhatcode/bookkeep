CREATE TYPE statementkind AS ENUM ('PAYS_FOR','REIMBURSES');

CREATE TABLE public.organisations (
    id int8 NOT NULL PRIMARY KEY,
    "name" varchar(75) NOT NULL
);
COMMENT ON TABLE public.organisations IS 'Organisations' ;

CREATE TABLE public.sources (
    id int8 NOT NULL PRIMARY KEY,
    "class" varchar(150) NOT NULL
);
COMMENT ON TABLE public.sources IS 'Sources' ;

CREATE TABLE public.statements (
	id int8 NOT NULL PRIMARY KEY,
	"amount" money NOT NULL,
	"sender" varchar(150) NOT NULL,
	"receiver" varchar(150) NOT NULL,
	"comment" text NULL,
	"date" timestamptz NOT NULL,
	"note" text NULL,
	"raw" text NULL,
	"source_id" int8 NOT null references sources
);
COMMENT ON TABLE public.statements IS 'Bank statements' ;

CREATE TABLE public.invoices (
	id int8 NOT NULL PRIMARY KEY,
    "name" varchar(75) NOT NULL,
    "organisation_id" int8 null references organisations,
    "type_id" int8 NOT NULL,
	"amount" money NOT NULL,
	"note" text NULL,
    "filename" varchar(200) NULL,
    "content" text NULL,
    "paid" int8 NULL,
	"raw" text NULL,
	"source_id" int8 NOT NULL
);
COMMENT ON TABLE public.invoices IS 'Invoices and receipts' ;

CREATE TABLE public.warranties (
    id int8 NOT NULL PRIMARY KEY,
    "name" varchar(75) NOT NULL,
    "receipt" int8 NULL,
    "note" text NULL
);
COMMENT ON TABLE public.warranties IS 'Warranties' ;

CREATE TABLE public.statements_relations(
    "subject" int8 NOT NULL REFERENCES statements,
    "object" int8 NOT NULL REFERENCES statements,
    "kind" statementkind NOT NULL,
    "invoice" int8 NULL REFERENCES invoices,
    PRIMARY KEY ("subject", "object")
);
COMMENT ON TABLE public.statements_relations IS 'Relations between statements' ;

CREATE TABLE public.statements_invoices(
    "statement_id" int8 NOT NULL REFERENCES statements,
    "invoice_id" int8 NOT null REFERENCES invoices,
    "note" text null,
    PRIMARY KEY ("statement_id", "invoice_id")
);
COMMENT ON TABLE public.statements_invoices IS 'invoices per statement (and reverse) N->N' ;

CREATE TABLE public.tags (
    id int8 NOT NULL PRIMARY KEY,
    "name" varchar(75) NOT NULL
);
COMMENT ON TABLE public.warranties IS 'Tags' ;