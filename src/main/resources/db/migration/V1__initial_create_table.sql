CREATE TABLE IF NOT EXISTS public.contas (
	id bigserial NOT NULL,
	data_pagamento date NULL,
	data_vencimento date NULL,
	descricao varchar(255) NULL,
	situacao varchar(255) NULL,
	valor numeric(38, 2) NULL,
	CONSTRAINT contas_pkey PRIMARY KEY (id)
);