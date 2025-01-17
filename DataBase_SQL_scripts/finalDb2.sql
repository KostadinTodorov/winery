--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: act_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.act_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.act_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: act; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.act (
    id integer DEFAULT nextval('public.act_id_seq'::regclass) NOT NULL,
    name character varying(100) NOT NULL,
    weight integer NOT NULL
);


ALTER TABLE public.act OWNER TO postgres;

--
-- Name: TABLE act; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.act IS 'TODO: add sequence. weight refers to the score points added or subtracted ';


--
-- Name: answers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.answers_id_seq OWNER TO postgres;

--
-- Name: answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answers (
    id integer DEFAULT nextval('public.answers_id_seq'::regclass) NOT NULL,
    answers character varying(20) NOT NULL
);


ALTER TABLE public.answers OWNER TO postgres;

--
-- Name: batch_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batch_id_seq OWNER TO postgres;

--
-- Name: batch; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch (
    id integer DEFAULT nextval('public.batch_id_seq'::regclass) NOT NULL,
    volume integer NOT NULL,
    wine_type_id integer NOT NULL
);


ALTER TABLE public.batch OWNER TO postgres;

--
-- Name: batch_storidge_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batch_storidge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batch_storidge_id_seq OWNER TO postgres;

--
-- Name: batch_storidge; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch_storidge (
    id integer DEFAULT nextval('public.batch_storidge_id_seq'::regclass) NOT NULL,
    batch_id integer NOT NULL,
    container_id integer NOT NULL
);


ALTER TABLE public.batch_storidge OWNER TO postgres;

--
-- Name: behavior_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.behavior_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.behavior_id_seq OWNER TO postgres;

--
-- Name: behavior; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.behavior (
    id integer DEFAULT nextval('public.behavior_id_seq'::regclass) NOT NULL,
    employee_id integer NOT NULL,
    act_id integer NOT NULL
);


ALTER TABLE public.behavior OWNER TO postgres;

--
-- Name: bottle_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bottle_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bottle_type_id_seq OWNER TO postgres;

--
-- Name: bottle_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottle_type (
    id integer DEFAULT nextval('public.bottle_type_id_seq'::regclass) NOT NULL,
    name character varying(35) NOT NULL,
    description character varying(200),
    stock integer NOT NULL
);


ALTER TABLE public.bottle_type OWNER TO postgres;

--
-- Name: bottles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bottles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bottles_id_seq OWNER TO postgres;

--
-- Name: bottles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottles (
    id integer DEFAULT nextval('public.bottles_id_seq'::regclass) NOT NULL,
    batch_id integer NOT NULL,
    sweetness_id integer NOT NULL,
    residual_sugar smallint NOT NULL,
    filled integer NOT NULL,
    bottle_type integer NOT NULL
);


ALTER TABLE public.bottles OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_id_seq OWNER TO postgres;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer DEFAULT nextval('public.client_id_seq'::regclass) NOT NULL,
    person_id integer,
    company_id integer
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: TABLE client; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.client IS 'person_id can be null in the case of an anon or an n/a';


--
-- Name: clients_orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clients_orders_id_seq OWNER TO postgres;

--
-- Name: clients_orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients_orders (
    id integer DEFAULT nextval('public.clients_orders_id_seq'::regclass) NOT NULL,
    client_id integer,
    quantity integer NOT NULL,
    order_date date NOT NULL,
    end_date date NOT NULL,
    completion_date date,
    progress_id integer,
    end_price numeric(7,3) NOT NULL,
    wine_type_id integer NOT NULL
);


ALTER TABLE public.clients_orders OWNER TO postgres;

--
-- Name: company_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.company_id_seq OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    id integer DEFAULT nextval('public.company_id_seq'::regclass) NOT NULL,
    address character varying(200) NOT NULL
);


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: container_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.container_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.container_id_seq OWNER TO postgres;

--
-- Name: container; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.container (
    id integer DEFAULT nextval('public.container_id_seq'::regclass) NOT NULL,
    space integer NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.container OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.employee_id_seq OWNER TO postgres;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id integer DEFAULT nextval('public.employee_id_seq'::regclass) NOT NULL,
    person_id integer NOT NULL,
    password character varying(30) NOT NULL,
    occupation_id integer NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: empty_bottles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empty_bottles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empty_bottles_id_seq OWNER TO postgres;

--
-- Name: fault_codes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fault_codes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fault_codes_id_seq OWNER TO postgres;

--
-- Name: fault_codes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fault_codes (
    id integer DEFAULT nextval('public.fault_codes_id_seq'::regclass) NOT NULL,
    code_name character varying(12) NOT NULL
);


ALTER TABLE public.fault_codes OWNER TO postgres;

--
-- Name: harvest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.harvest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.harvest_id_seq OWNER TO postgres;

--
-- Name: harvest; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.harvest (
    id integer DEFAULT nextval('public.harvest_id_seq'::regclass) NOT NULL,
    weight integer NOT NULL,
    sort_id integer NOT NULL
);


ALTER TABLE public.harvest OWNER TO postgres;

--
-- Name: machine_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.machine_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.machine_type_id_seq OWNER TO postgres;

--
-- Name: machine_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machine_type (
    id integer DEFAULT nextval('public.machine_type_id_seq'::regclass) NOT NULL,
    machine_type character varying(12) NOT NULL
);


ALTER TABLE public.machine_type OWNER TO postgres;

--
-- Name: machines_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.machines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.machines_id_seq OWNER TO postgres;

--
-- Name: machines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machines (
    id integer DEFAULT nextval('public.machines_id_seq'::regclass) NOT NULL,
    machine_type_id integer,
    production_year date NOT NULL,
    fault_code_id integer,
    needs_service boolean NOT NULL,
    needs_replacement boolean NOT NULL,
    service_cost numeric(7,3) NOT NULL,
    answer_id integer
);


ALTER TABLE public.machines OWNER TO postgres;

--
-- Name: mix; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mix (
    id integer NOT NULL,
    harvest_id integer NOT NULL,
    batch_id integer NOT NULL
);


ALTER TABLE public.mix OWNER TO postgres;

--
-- Name: occupation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.occupation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.occupation_id_seq OWNER TO postgres;

--
-- Name: occupation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.occupation (
    id integer DEFAULT nextval('public.occupation_id_seq'::regclass) NOT NULL,
    occupation character varying(20) NOT NULL
);


ALTER TABLE public.occupation OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.person_id_seq OWNER TO postgres;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer DEFAULT nextval('public.person_id_seq'::regclass) NOT NULL,
    person_name character varying(30) NOT NULL,
    age smallint NOT NULL,
    email character varying(50) NOT NULL,
    is_male boolean NOT NULL,
    phone_number character varying(12) NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.progress_id_seq OWNER TO postgres;

--
-- Name: progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.progress (
    id integer DEFAULT nextval('public.progress_id_seq'::regclass) NOT NULL,
    status character varying(13) NOT NULL
);


ALTER TABLE public.progress OWNER TO postgres;

--
-- Name: sort_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sort_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sort_id_seq OWNER TO postgres;

--
-- Name: sort; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sort (
    id integer DEFAULT nextval('public.sort_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.sort OWNER TO postgres;

--
-- Name: sweetness_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sweetness_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sweetness_id_seq OWNER TO postgres;

--
-- Name: sweetness; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sweetness (
    id integer DEFAULT nextval('public.sweetness_id_seq'::regclass) NOT NULL,
    category character varying(15) NOT NULL,
    le_category character varying(15)
);


ALTER TABLE public.sweetness OWNER TO postgres;

--
-- Name: wine_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wine_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wine_type_id_seq OWNER TO postgres;

--
-- Name: wine_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wine_type (
    id integer DEFAULT nextval('public.wine_type_id_seq'::regclass) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.wine_type OWNER TO postgres;

--
-- Data for Name: act; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.act (id, name, weight) FROM stdin;
8	smoking on duty outside	-75
9	smoking on duty inside	-150
10	working hard	25
14	killed his colleague by drowning him into a barrel full of wine	-10000
\.


--
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.answers (id, answers) FROM stdin;
\.


--
-- Data for Name: batch; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch (id, volume, wine_type_id) FROM stdin;
\.


--
-- Data for Name: batch_storidge; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch_storidge (id, batch_id, container_id) FROM stdin;
\.


--
-- Data for Name: behavior; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.behavior (id, employee_id, act_id) FROM stdin;
\.


--
-- Data for Name: bottle_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bottle_type (id, name, description, stock) FROM stdin;
\.


--
-- Data for Name: bottles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bottles (id, batch_id, sweetness_id, residual_sugar, filled, bottle_type) FROM stdin;
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (id, person_id, company_id) FROM stdin;
\.


--
-- Data for Name: clients_orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients_orders (id, client_id, quantity, order_date, end_date, completion_date, progress_id, end_price, wine_type_id) FROM stdin;
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company (id, address) FROM stdin;
\.


--
-- Data for Name: container; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.container (id, space, name) FROM stdin;
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, person_id, password, occupation_id) FROM stdin;
1	1	46	1
\.


--
-- Data for Name: fault_codes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fault_codes (id, code_name) FROM stdin;
\.


--
-- Data for Name: harvest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.harvest (id, weight, sort_id) FROM stdin;
\.


--
-- Data for Name: machine_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machine_type (id, machine_type) FROM stdin;
\.


--
-- Data for Name: machines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machines (id, machine_type_id, production_year, fault_code_id, needs_service, needs_replacement, service_cost, answer_id) FROM stdin;
\.


--
-- Data for Name: mix; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mix (id, harvest_id, batch_id) FROM stdin;
\.


--
-- Data for Name: occupation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.occupation (id, occupation) FROM stdin;
1	CEO
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, person_name, age, email, is_male, phone_number) FROM stdin;
1	Joe Biden	1000	letsgobranden@mail.com	f	1010101010
\.


--
-- Data for Name: progress; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.progress (id, status) FROM stdin;
\.


--
-- Data for Name: sort; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sort (id, name) FROM stdin;
\.


--
-- Data for Name: sweetness; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sweetness (id, category, le_category) FROM stdin;
\.


--
-- Data for Name: wine_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wine_type (id, name) FROM stdin;
\.


--
-- Name: act_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.act_id_seq', 14, true);


--
-- Name: answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answers_id_seq', 1, false);


--
-- Name: batch_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_id_seq', 1, false);


--
-- Name: batch_storidge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batch_storidge_id_seq', 1, false);


--
-- Name: behavior_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.behavior_id_seq', 1, false);


--
-- Name: bottle_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bottle_type_id_seq', 1, false);


--
-- Name: bottles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bottles_id_seq', 1, false);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 1, false);


--
-- Name: clients_orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_orders_id_seq', 1, false);


--
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_id_seq', 1, false);


--
-- Name: container_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.container_id_seq', 1, false);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 1, true);


--
-- Name: empty_bottles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empty_bottles_id_seq', 1, false);


--
-- Name: fault_codes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fault_codes_id_seq', 1, false);


--
-- Name: harvest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.harvest_id_seq', 1, false);


--
-- Name: machine_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.machine_type_id_seq', 1, false);


--
-- Name: machines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.machines_id_seq', 1, false);


--
-- Name: occupation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.occupation_id_seq', 1, true);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 1, true);


--
-- Name: progress_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.progress_id_seq', 1, false);


--
-- Name: sort_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sort_id_seq', 1, false);


--
-- Name: sweetness_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sweetness_id_seq', 1, false);


--
-- Name: wine_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wine_type_id_seq', 1, false);


--
-- Name: act acts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.act
    ADD CONSTRAINT acts_pkey PRIMARY KEY (id);


--
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- Name: batch batch_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch
    ADD CONSTRAINT batch_pkey PRIMARY KEY (id);


--
-- Name: batch_storidge batch_storidge_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_pkey PRIMARY KEY (id);


--
-- Name: bottle_type bottle_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottle_type
    ADD CONSTRAINT bottle_type_pkey PRIMARY KEY (id);


--
-- Name: bottles bottles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: clients_orders clientsorders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_pkey PRIMARY KEY (id);


--
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- Name: container container_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.container
    ADD CONSTRAINT container_pkey PRIMARY KEY (id);


--
-- Name: employee employee_person_id_person_id1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_person_id_person_id1_key UNIQUE (person_id) INCLUDE (person_id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: behavior employeescore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT employeescore_pkey PRIMARY KEY (id);


--
-- Name: fault_codes faultcodes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fault_codes
    ADD CONSTRAINT faultcodes_pkey PRIMARY KEY (id);


--
-- Name: harvest harvest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_pkey PRIMARY KEY (id);


--
-- Name: occupation joboccupation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occupation
    ADD CONSTRAINT joboccupation_pkey PRIMARY KEY (id);


--
-- Name: machines machines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_pkey PRIMARY KEY (id);


--
-- Name: machine_type machinetype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machine_type
    ADD CONSTRAINT machinetype_pkey PRIMARY KEY (id);


--
-- Name: mix mix_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: progress progress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress
    ADD CONSTRAINT progress_pkey PRIMARY KEY (id);


--
-- Name: sort sort_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sort
    ADD CONSTRAINT sort_pkey PRIMARY KEY (id);


--
-- Name: sweetness sweetness_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sweetness
    ADD CONSTRAINT sweetness_pkey PRIMARY KEY (id);


--
-- Name: wine_type wine_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wine_type
    ADD CONSTRAINT wine_type_pkey PRIMARY KEY (id);


--
-- Name: batch_storidge batch_storidge_batch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: batch_storidge batch_storidge_container_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_container_id_fkey FOREIGN KEY (container_id) REFERENCES public.container(id) NOT VALID;


--
-- Name: batch batch_wine_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch
    ADD CONSTRAINT batch_wine_type_id_fkey FOREIGN KEY (wine_type_id) REFERENCES public.wine_type(id) NOT VALID;


--
-- Name: behavior behavior_act_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT behavior_act_id_fkey FOREIGN KEY (act_id) REFERENCES public.act(id) NOT VALID;


--
-- Name: behavior behavior_employee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT behavior_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(id) NOT VALID;


--
-- Name: bottles bottles_batch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: bottles bottles_bottle_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_bottle_type_fkey FOREIGN KEY (bottle_type) REFERENCES public.bottle_type(id) NOT VALID;


--
-- Name: bottles bottles_sweetness_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_sweetness_id_fkey FOREIGN KEY (sweetness_id) REFERENCES public.sweetness(id) NOT VALID;


--
-- Name: client client_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.company(id) NOT VALID;


--
-- Name: client client_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;


--
-- Name: clients_orders clients_orders_wine_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clients_orders_wine_type_id_fkey FOREIGN KEY (wine_type_id) REFERENCES public.wine_type(id) NOT VALID;


--
-- Name: clients_orders clientsorders_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: clients_orders clientsorders_progress_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_progress_id_fkey FOREIGN KEY (progress_id) REFERENCES public.progress(id);


--
-- Name: employee employee_occupation_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_occupation_id_fkey FOREIGN KEY (occupation_id) REFERENCES public.occupation(id) NOT VALID;


--
-- Name: employee employee_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;


--
-- Name: harvest harvest_sort_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_sort_id_fkey FOREIGN KEY (sort_id) REFERENCES public.sort(id) NOT VALID;


--
-- Name: machines machines_answer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_answer_id_fkey FOREIGN KEY (answer_id) REFERENCES public.answers(id);


--
-- Name: machines machines_fault_code_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_fault_code_id_fkey FOREIGN KEY (fault_code_id) REFERENCES public.fault_codes(id);


--
-- Name: machines machines_machine_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_machine_type_id_fkey FOREIGN KEY (machine_type_id) REFERENCES public.machine_type(id);


--
-- Name: mix mix_batch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id) NOT VALID;


--
-- Name: mix mix_harvest_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_harvest_id_fkey FOREIGN KEY (harvest_id) REFERENCES public.harvest(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

