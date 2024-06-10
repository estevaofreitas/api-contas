CREATE TABLE IF NOT EXISTS public.contas (
	data_pagamento date NULL,
	data_vencimento date NOT NULL,
	valor numeric(38, 2) NOT NULL,
	id bigserial NOT NULL,
	descricao varchar(200) NOT NULL,
	situacao varchar(255) NOT NULL,
	CONSTRAINT contas_pkey PRIMARY KEY (id)
);