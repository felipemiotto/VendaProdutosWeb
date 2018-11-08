--CRIA O BANCO
CREATE DATABASE vendasprodutoweb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

--TABELA PAIS
CREATE TABLE public.pais
(
    id uuid NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    codigo character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pais_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pais
    OWNER to postgres;

--TABELA ESTADO
CREATE TABLE public.estado
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    sigla character varying(2) COLLATE pg_catalog."default" NOT NULL,
    pais_id uuid NOT NULL,
    CONSTRAINT estado_pkey PRIMARY KEY (id),
    CONSTRAINT estado_pais_id_fkey FOREIGN KEY (pais_id)
        REFERENCES public.pais (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.estado
    OWNER to postgres;

--TABELA CIDADE
CREATE TABLE public.cidade
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    estado_id uuid NOT NULL,
    CONSTRAINT cidade_pkey PRIMARY KEY (id),
    CONSTRAINT cidade_estado_id_fkey FOREIGN KEY (estado_id)
        REFERENCES public.estado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cidade
    OWNER to postgres;

--TABELA CEP
CREATE TABLE public.cep
(
    id uuid NOT NULL,
    cep character varying(8) COLLATE pg_catalog."default" NOT NULL,
    cidade_id uuid NOT NULL,
    CONSTRAINT cep_pkey PRIMARY KEY (id),
    CONSTRAINT cep_cidade_id_fkey FOREIGN KEY (cidade_id)
        REFERENCES public.cidade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cep
    OWNER to postgres;

--TABELA CLIENTE
CREATE TABLE public.cliente
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    endereco character varying(80) COLLATE pg_catalog."default" NOT NULL,
    numero character varying(20) COLLATE pg_catalog."default",
    complemento character varying(30) COLLATE pg_catalog."default",
    bairro character varying(50) COLLATE pg_catalog."default" NOT NULL,
    fone character varying(20) COLLATE pg_catalog."default",
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(80) COLLATE pg_catalog."default",
    cep_id uuid NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT cliente_cep_id_fkey FOREIGN KEY (cep_id)
        REFERENCES public.cep (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cliente
    OWNER to postgres;

--TABELA DOCUMENTO
CREATE TABLE public.documento
(
    id uuid NOT NULL,
    codigo character varying(10) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT documento_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.documento
    OWNER to postgres;

--TABELA GRUPO
CREATE TABLE public.grupo
(
    id uuid NOT NULL,
    codigo character varying(10) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT grupo_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.grupo
    OWNER to postgres;

--TABELA VENDA
CREATE TABLE public.venda
(
    id uuid NOT NULL,
    numero character varying(10) COLLATE pg_catalog."default" NOT NULL,
    emissao date NOT NULL,
    desconto numeric(15,6) NOT NULL,
    total numeric(15,6) NOT NULL,
    cliente_id uuid NOT NULL,
    documento_id uuid,
    CONSTRAINT venda_pkey PRIMARY KEY (id),
    CONSTRAINT venda_cliente_id_fkey FOREIGN KEY (cliente_id)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT venda_documento_id_fkey FOREIGN KEY (documento_id)
        REFERENCES public.documento (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.venda
    OWNER to postgres;

--TABELA PRODUTO
CREATE TABLE public.produto
(
    id uuid NOT NULL,
    descricao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    unidade character varying(10) COLLATE pg_catalog."default" NOT NULL,
    peso numeric(8,6) NOT NULL,
    preco numeric(15,6) NOT NULL,
    custo numeric(15,6) NOT NULL,
    grupo_id uuid NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id),
    CONSTRAINT produto_grupo_id_fkey FOREIGN KEY (grupo_id)
        REFERENCES public.grupo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.produto
    OWNER to postgres;

--TABELA ITENS DA VENDA
CREATE TABLE public.itens_venda
(
    id uuid NOT NULL,
    quantidade numeric(15,6) NOT NULL,
    valor_unitario numeric(15,6) NOT NULL,
    valor_total numeric(15,6) NOT NULL,
    desconto numeric(15,6) NOT NULL,
    produto_id uuid NOT NULL,
    venda_id uuid NOT NULL,
    CONSTRAINT itens_venda_pkey PRIMARY KEY (id),
    CONSTRAINT itens_venda_produto_id_fkey FOREIGN KEY (produto_id)
        REFERENCES public.produto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT itens_venda_venda_id_fkey FOREIGN KEY (venda_id)
        REFERENCES public.venda (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.itens_venda
    OWNER to postgres;
