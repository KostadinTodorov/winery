--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1
-- Dumped by pg_dump version 17.1

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answers (
    an_id integer NOT NULL,
    answers character varying(20) NOT NULL
);


ALTER TABLE public.answers OWNER TO postgres;

--
-- Name: answers_an_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answers_an_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.answers_an_id_seq OWNER TO postgres;

--
-- Name: answers_an_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answers_an_id_seq OWNED BY public.answers.an_id;


--
-- Name: bottlesinventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottlesinventory (
    bi_id integer NOT NULL,
    shape character varying(15) NOT NULL,
    quantity_liters numeric(2,3) NOT NULL,
    quantity_empty_bottles integer NOT NULL,
    quantity_needed_bottles integer NOT NULL,
    answers_id integer
);


ALTER TABLE public.bottlesinventory OWNER TO postgres;

--
-- Name: bottlesinventory_bi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bottlesinventory_bi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bottlesinventory_bi_id_seq OWNER TO postgres;

--
-- Name: bottlesinventory_bi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bottlesinventory_bi_id_seq OWNED BY public.bottlesinventory.bi_id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    c_id integer NOT NULL,
    client_name character varying(30) NOT NULL,
    residence character varying(50) NOT NULL,
    phone integer NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_c_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_c_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_c_id_seq OWNER TO postgres;

--
-- Name: client_c_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_c_id_seq OWNED BY public.client.c_id;


--
-- Name: clientsorders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientsorders (
    co_id integer NOT NULL,
    client_id integer,
    wine_id integer,
    quantity_wanted_bottles integer NOT NULL,
    bottles_inventory_id integer,
    order_date date NOT NULL,
    end_date date NOT NULL,
    completion_date date,
    progress_id integer,
    end_price numeric(7,3) NOT NULL
);


ALTER TABLE public.clientsorders OWNER TO postgres;

--
-- Name: clientsorders_co_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientsorders_co_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clientsorders_co_id_seq OWNER TO postgres;

--
-- Name: clientsorders_co_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientsorders_co_id_seq OWNED BY public.clientsorders.co_id;


--
-- Name: employeescore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employeescore (
    es_id integer NOT NULL,
    person_id integer,
    arriving_late boolean NOT NULL,
    ruin_sample boolean NOT NULL,
    ruin_machines boolean NOT NULL,
    ruin_bottles boolean NOT NULL,
    underperforming boolean NOT NULL,
    smoking boolean NOT NULL,
    performing_in_time boolean NOT NULL,
    performing_excellent boolean NOT NULL,
    total_score integer NOT NULL,
    log_date date NOT NULL
);


ALTER TABLE public.employeescore OWNER TO postgres;

--
-- Name: employeescore_es_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employeescore_es_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.employeescore_es_id_seq OWNER TO postgres;

--
-- Name: employeescore_es_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employeescore_es_id_seq OWNED BY public.employeescore.es_id;


--
-- Name: faultcodes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.faultcodes (
    fc_id integer NOT NULL,
    code_name character varying(12) NOT NULL
);


ALTER TABLE public.faultcodes OWNER TO postgres;

--
-- Name: faultcodes_fc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.faultcodes_fc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.faultcodes_fc_id_seq OWNER TO postgres;

--
-- Name: faultcodes_fc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.faultcodes_fc_id_seq OWNED BY public.faultcodes.fc_id;


--
-- Name: gender; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gender (
    g_id integer NOT NULL,
    gender_type character varying(20) NOT NULL
);


ALTER TABLE public.gender OWNER TO postgres;

--
-- Name: gender_g_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gender_g_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.gender_g_id_seq OWNER TO postgres;

--
-- Name: gender_g_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.gender_g_id_seq OWNED BY public.gender.g_id;


--
-- Name: grapestypes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grapestypes (
    gt_id integer NOT NULL,
    sort character varying(10) NOT NULL,
    quantity integer NOT NULL,
    missing_quantity integer NOT NULL,
    answers_id integer
);


ALTER TABLE public.grapestypes OWNER TO postgres;

--
-- Name: grapestypes_gt_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.grapestypes_gt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.grapestypes_gt_id_seq OWNER TO postgres;

--
-- Name: grapestypes_gt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.grapestypes_gt_id_seq OWNED BY public.grapestypes.gt_id;


--
-- Name: joboccupation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.joboccupation (
    jo_id integer NOT NULL,
    occupation character varying(20) NOT NULL,
    system_access_id integer
);


ALTER TABLE public.joboccupation OWNER TO postgres;

--
-- Name: joboccupation_jo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.joboccupation_jo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.joboccupation_jo_id_seq OWNER TO postgres;

--
-- Name: joboccupation_jo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.joboccupation_jo_id_seq OWNED BY public.joboccupation.jo_id;


--
-- Name: machines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machines (
    m_id integer NOT NULL,
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
-- Name: machines_m_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.machines_m_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.machines_m_id_seq OWNER TO postgres;

--
-- Name: machines_m_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.machines_m_id_seq OWNED BY public.machines.m_id;


--
-- Name: machinetype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machinetype (
    mt_id integer NOT NULL,
    machine_type character varying(12) NOT NULL
);


ALTER TABLE public.machinetype OWNER TO postgres;

--
-- Name: machinetype_mt_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.machinetype_mt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.machinetype_mt_id_seq OWNER TO postgres;

--
-- Name: machinetype_mt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.machinetype_mt_id_seq OWNED BY public.machinetype.mt_id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    p_id integer NOT NULL,
    person_name character varying(30) NOT NULL,
    gender_id integer,
    age smallint NOT NULL,
    job_occupation_id integer,
    wine_division_id integer,
    email character varying(50) NOT NULL,
    person_password character varying(50) NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_p_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_p_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.person_p_id_seq OWNER TO postgres;

--
-- Name: person_p_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_p_id_seq OWNED BY public.person.p_id;


--
-- Name: progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.progress (
    p_id integer NOT NULL,
    status character varying(13) NOT NULL
);


ALTER TABLE public.progress OWNER TO postgres;

--
-- Name: progress_p_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.progress_p_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.progress_p_id_seq OWNER TO postgres;

--
-- Name: progress_p_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.progress_p_id_seq OWNED BY public.progress.p_id;


--
-- Name: sweetness; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sweetness (
    s_id integer NOT NULL,
    sweetness character varying(15) NOT NULL
);


ALTER TABLE public.sweetness OWNER TO postgres;

--
-- Name: sweetness_s_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sweetness_s_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sweetness_s_id_seq OWNER TO postgres;

--
-- Name: sweetness_s_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sweetness_s_id_seq OWNED BY public.sweetness.s_id;


--
-- Name: systemaccess; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.systemaccess (
    sa_id integer NOT NULL,
    access_level character varying(10) NOT NULL
);


ALTER TABLE public.systemaccess OWNER TO postgres;

--
-- Name: systemaccess_sa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.systemaccess_sa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.systemaccess_sa_id_seq OWNER TO postgres;

--
-- Name: systemaccess_sa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.systemaccess_sa_id_seq OWNED BY public.systemaccess.sa_id;


--
-- Name: wine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wine (
    w_id integer NOT NULL,
    sort character varying(30) NOT NULL,
    sweetness_id integer
);


ALTER TABLE public.wine OWNER TO postgres;

--
-- Name: wine_w_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wine_w_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wine_w_id_seq OWNER TO postgres;

--
-- Name: wine_w_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wine_w_id_seq OWNED BY public.wine.w_id;


--
-- Name: winegrapescombination; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.winegrapescombination (
    wgc_id integer NOT NULL,
    wine_id integer,
    grapes_id integer
);


ALTER TABLE public.winegrapescombination OWNER TO postgres;

--
-- Name: winegrapescombination_wgc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.winegrapescombination_wgc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.winegrapescombination_wgc_id_seq OWNER TO postgres;

--
-- Name: winegrapescombination_wgc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.winegrapescombination_wgc_id_seq OWNED BY public.winegrapescombination.wgc_id;


--
-- Name: winesinventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.winesinventory (
    wi_id integer NOT NULL,
    wine_id integer,
    bottles_inventory_id integer,
    production_date date NOT NULL,
    price_per_bottle numeric(7,2) NOT NULL,
    quantity_filled_bottles integer NOT NULL
);


ALTER TABLE public.winesinventory OWNER TO postgres;

--
-- Name: winesinventory_wi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.winesinventory_wi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.winesinventory_wi_id_seq OWNER TO postgres;

--
-- Name: winesinventory_wi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.winesinventory_wi_id_seq OWNED BY public.winesinventory.wi_id;


--
-- Name: answers an_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers ALTER COLUMN an_id SET DEFAULT nextval('public.answers_an_id_seq'::regclass);


--
-- Name: bottlesinventory bi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottlesinventory ALTER COLUMN bi_id SET DEFAULT nextval('public.bottlesinventory_bi_id_seq'::regclass);


--
-- Name: client c_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN c_id SET DEFAULT nextval('public.client_c_id_seq'::regclass);


--
-- Name: clientsorders co_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders ALTER COLUMN co_id SET DEFAULT nextval('public.clientsorders_co_id_seq'::regclass);


--
-- Name: employeescore es_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeescore ALTER COLUMN es_id SET DEFAULT nextval('public.employeescore_es_id_seq'::regclass);


--
-- Name: faultcodes fc_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faultcodes ALTER COLUMN fc_id SET DEFAULT nextval('public.faultcodes_fc_id_seq'::regclass);


--
-- Name: gender g_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gender ALTER COLUMN g_id SET DEFAULT nextval('public.gender_g_id_seq'::regclass);


--
-- Name: grapestypes gt_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grapestypes ALTER COLUMN gt_id SET DEFAULT nextval('public.grapestypes_gt_id_seq'::regclass);


--
-- Name: joboccupation jo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.joboccupation ALTER COLUMN jo_id SET DEFAULT nextval('public.joboccupation_jo_id_seq'::regclass);


--
-- Name: machines m_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines ALTER COLUMN m_id SET DEFAULT nextval('public.machines_m_id_seq'::regclass);


--
-- Name: machinetype mt_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machinetype ALTER COLUMN mt_id SET DEFAULT nextval('public.machinetype_mt_id_seq'::regclass);


--
-- Name: person p_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN p_id SET DEFAULT nextval('public.person_p_id_seq'::regclass);


--
-- Name: progress p_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress ALTER COLUMN p_id SET DEFAULT nextval('public.progress_p_id_seq'::regclass);


--
-- Name: sweetness s_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sweetness ALTER COLUMN s_id SET DEFAULT nextval('public.sweetness_s_id_seq'::regclass);


--
-- Name: systemaccess sa_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.systemaccess ALTER COLUMN sa_id SET DEFAULT nextval('public.systemaccess_sa_id_seq'::regclass);


--
-- Name: wine w_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wine ALTER COLUMN w_id SET DEFAULT nextval('public.wine_w_id_seq'::regclass);


--
-- Name: winegrapescombination wgc_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winegrapescombination ALTER COLUMN wgc_id SET DEFAULT nextval('public.winegrapescombination_wgc_id_seq'::regclass);


--
-- Name: winesinventory wi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winesinventory ALTER COLUMN wi_id SET DEFAULT nextval('public.winesinventory_wi_id_seq'::regclass);


--
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (an_id);


--
-- Name: bottlesinventory bottlesinventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottlesinventory
    ADD CONSTRAINT bottlesinventory_pkey PRIMARY KEY (bi_id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (c_id);


--
-- Name: clientsorders clientsorders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders
    ADD CONSTRAINT clientsorders_pkey PRIMARY KEY (co_id);


--
-- Name: employeescore employeescore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeescore
    ADD CONSTRAINT employeescore_pkey PRIMARY KEY (es_id);


--
-- Name: faultcodes faultcodes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faultcodes
    ADD CONSTRAINT faultcodes_pkey PRIMARY KEY (fc_id);


--
-- Name: gender gender_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gender
    ADD CONSTRAINT gender_pkey PRIMARY KEY (g_id);


--
-- Name: grapestypes grapestypes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grapestypes
    ADD CONSTRAINT grapestypes_pkey PRIMARY KEY (gt_id);


--
-- Name: joboccupation joboccupation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.joboccupation
    ADD CONSTRAINT joboccupation_pkey PRIMARY KEY (jo_id);


--
-- Name: machines machines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_pkey PRIMARY KEY (m_id);


--
-- Name: machinetype machinetype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machinetype
    ADD CONSTRAINT machinetype_pkey PRIMARY KEY (mt_id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (p_id);


--
-- Name: progress progress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress
    ADD CONSTRAINT progress_pkey PRIMARY KEY (p_id);


--
-- Name: sweetness sweetness_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sweetness
    ADD CONSTRAINT sweetness_pkey PRIMARY KEY (s_id);


--
-- Name: systemaccess systemaccess_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.systemaccess
    ADD CONSTRAINT systemaccess_pkey PRIMARY KEY (sa_id);


--
-- Name: wine wine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wine
    ADD CONSTRAINT wine_pkey PRIMARY KEY (w_id);


--
-- Name: winegrapescombination winegrapescombination_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winegrapescombination
    ADD CONSTRAINT winegrapescombination_pkey PRIMARY KEY (wgc_id);


--
-- Name: winesinventory winesinventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winesinventory
    ADD CONSTRAINT winesinventory_pkey PRIMARY KEY (wi_id);


--
-- Name: bottlesinventory bottlesinventory_answers_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottlesinventory
    ADD CONSTRAINT bottlesinventory_answers_id_fkey FOREIGN KEY (answers_id) REFERENCES public.answers(an_id);


--
-- Name: clientsorders clientsorders_bottles_inventory_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders
    ADD CONSTRAINT clientsorders_bottles_inventory_id_fkey FOREIGN KEY (bottles_inventory_id) REFERENCES public.bottlesinventory(bi_id);


--
-- Name: clientsorders clientsorders_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders
    ADD CONSTRAINT clientsorders_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(c_id);


--
-- Name: clientsorders clientsorders_progress_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders
    ADD CONSTRAINT clientsorders_progress_id_fkey FOREIGN KEY (progress_id) REFERENCES public.progress(p_id);


--
-- Name: clientsorders clientsorders_wine_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientsorders
    ADD CONSTRAINT clientsorders_wine_id_fkey FOREIGN KEY (wine_id) REFERENCES public.wine(w_id);


--
-- Name: employeescore employeescore_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeescore
    ADD CONSTRAINT employeescore_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(p_id);


--
-- Name: grapestypes grapestypes_answers_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grapestypes
    ADD CONSTRAINT grapestypes_answers_id_fkey FOREIGN KEY (answers_id) REFERENCES public.answers(an_id);


--
-- Name: joboccupation joboccupation_system_access_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.joboccupation
    ADD CONSTRAINT joboccupation_system_access_id_fkey FOREIGN KEY (system_access_id) REFERENCES public.systemaccess(sa_id);


--
-- Name: machines machines_answer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_answer_id_fkey FOREIGN KEY (answer_id) REFERENCES public.answers(an_id);


--
-- Name: machines machines_fault_code_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_fault_code_id_fkey FOREIGN KEY (fault_code_id) REFERENCES public.faultcodes(fc_id);


--
-- Name: machines machines_machine_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_machine_type_id_fkey FOREIGN KEY (machine_type_id) REFERENCES public.machinetype(mt_id);


--
-- Name: person person_gender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_gender_id_fkey FOREIGN KEY (gender_id) REFERENCES public.gender(g_id);


--
-- Name: person person_job_occupation_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_job_occupation_id_fkey FOREIGN KEY (job_occupation_id) REFERENCES public.joboccupation(jo_id);


--
-- Name: person person_wine_division_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_wine_division_id_fkey FOREIGN KEY (wine_division_id) REFERENCES public.wine(w_id);


--
-- Name: wine wine_sweetness_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wine
    ADD CONSTRAINT wine_sweetness_id_fkey FOREIGN KEY (sweetness_id) REFERENCES public.sweetness(s_id);


--
-- Name: winegrapescombination winegrapescombination_grapes_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winegrapescombination
    ADD CONSTRAINT winegrapescombination_grapes_id_fkey FOREIGN KEY (grapes_id) REFERENCES public.grapestypes(gt_id);


--
-- Name: winegrapescombination winegrapescombination_wine_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winegrapescombination
    ADD CONSTRAINT winegrapescombination_wine_id_fkey FOREIGN KEY (wine_id) REFERENCES public.wine(w_id);


--
-- Name: winesinventory winesinventory_bottles_inventory_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winesinventory
    ADD CONSTRAINT winesinventory_bottles_inventory_id_fkey FOREIGN KEY (bottles_inventory_id) REFERENCES public.bottlesinventory(bi_id);


--
-- Name: winesinventory winesinventory_wine_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.winesinventory
    ADD CONSTRAINT winesinventory_wine_id_fkey FOREIGN KEY (wine_id) REFERENCES public.wine(w_id);


--
-- PostgreSQL database dump complete
--

