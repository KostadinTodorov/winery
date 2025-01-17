PGDMP  1    '                |            WineryDB    17.2    17.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16389    WineryDB    DATABASE     �   CREATE DATABASE "WineryDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "WineryDB";
                     postgres    false                       1259    16815 
   act_id_seq    SEQUENCE     s   CREATE SEQUENCE public.act_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.act_id_seq;
       public               postgres    false            �            1259    16642    act    TABLE     �   CREATE TABLE public.act (
    id integer DEFAULT nextval('public.act_id_seq'::regclass) NOT NULL,
    name character varying(100) NOT NULL,
    weight integer NOT NULL
);
    DROP TABLE public.act;
       public         heap r       postgres    false    259            �           0    0 	   TABLE act    COMMENT     m   COMMENT ON TABLE public.act IS 'TODO: add sequence. weight refers to the score points added or subtracted ';
          public               postgres    false    229            �            1259    16779    answers_id_seq    SEQUENCE     w   CREATE SEQUENCE public.answers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.answers_id_seq;
       public               postgres    false            �            1259    16390    answers    TABLE     �   CREATE TABLE public.answers (
    id integer DEFAULT nextval('public.answers_id_seq'::regclass) NOT NULL,
    answers character varying(20) NOT NULL
);
    DROP TABLE public.answers;
       public         heap r       postgres    false    241                       1259    16821    batch_id_seq    SEQUENCE     u   CREATE SEQUENCE public.batch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.batch_id_seq;
       public               postgres    false            �            1259    16667    batch    TABLE     �   CREATE TABLE public.batch (
    id integer DEFAULT nextval('public.batch_id_seq'::regclass) NOT NULL,
    volume integer NOT NULL,
    wine_type_id integer NOT NULL
);
    DROP TABLE public.batch;
       public         heap r       postgres    false    262            �            1259    16799    batch_storidge_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.batch_storidge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.batch_storidge_id_seq;
       public               postgres    false            �            1259    16692    batch_storidge    TABLE     �   CREATE TABLE public.batch_storidge (
    id integer DEFAULT nextval('public.batch_storidge_id_seq'::regclass) NOT NULL,
    batch_id integer NOT NULL,
    container_id integer NOT NULL
);
 "   DROP TABLE public.batch_storidge;
       public         heap r       postgres    false    251            �            1259    16791    behavior_id_seq    SEQUENCE     x   CREATE SEQUENCE public.behavior_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.behavior_id_seq;
       public               postgres    false            �            1259    16406    behavior    TABLE     �   CREATE TABLE public.behavior (
    id integer DEFAULT nextval('public.behavior_id_seq'::regclass) NOT NULL,
    employee_id integer NOT NULL,
    act_id integer NOT NULL
);
    DROP TABLE public.behavior;
       public         heap r       postgres    false    247            �            1259    16803    bottle_type_id_seq    SEQUENCE     {   CREATE SEQUENCE public.bottle_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.bottle_type_id_seq;
       public               postgres    false            �            1259    16727    bottle_type    TABLE     �   CREATE TABLE public.bottle_type (
    id integer DEFAULT nextval('public.bottle_type_id_seq'::regclass) NOT NULL,
    name character varying(35) NOT NULL,
    description character varying(200)
);
    DROP TABLE public.bottle_type;
       public         heap r       postgres    false    253            �            1259    16805    bottles_id_seq    SEQUENCE     w   CREATE SEQUENCE public.bottles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.bottles_id_seq;
       public               postgres    false            �            1259    16707    bottles    TABLE     �   CREATE TABLE public.bottles (
    id integer DEFAULT nextval('public.bottles_id_seq'::regclass) NOT NULL,
    batch_id integer NOT NULL,
    sweetness_id integer NOT NULL,
    residual_sugar smallint NOT NULL
);
    DROP TABLE public.bottles;
       public         heap r       postgres    false    254            �            1259    16785    client_id_seq    SEQUENCE     v   CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.client_id_seq;
       public               postgres    false            �            1259    16398    client    TABLE     �   CREATE TABLE public.client (
    id integer DEFAULT nextval('public.client_id_seq'::regclass) NOT NULL,
    person_id integer,
    company_id integer
);
    DROP TABLE public.client;
       public         heap r       postgres    false    244            �           0    0    TABLE client    COMMENT     \   COMMENT ON TABLE public.client IS 'person_id can be null in the case of an anon or an n/a';
          public               postgres    false    218            �            1259    16783    clients_orders_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.clients_orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.clients_orders_id_seq;
       public               postgres    false            �            1259    16402    clients_orders    TABLE     e  CREATE TABLE public.clients_orders (
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
 "   DROP TABLE public.clients_orders;
       public         heap r       postgres    false    243                        1259    16809    company_id_seq    SEQUENCE     w   CREATE SEQUENCE public.company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.company_id_seq;
       public               postgres    false            �            1259    16762    company    TABLE     �   CREATE TABLE public.company (
    id integer DEFAULT nextval('public.company_id_seq'::regclass) NOT NULL,
    address character varying(200) NOT NULL
);
    DROP TABLE public.company;
       public         heap r       postgres    false    256            �            1259    16801    container_id_seq    SEQUENCE     y   CREATE SEQUENCE public.container_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.container_id_seq;
       public               postgres    false            �            1259    16687 	   container    TABLE     �   CREATE TABLE public.container (
    id integer DEFAULT nextval('public.container_id_seq'::regclass) NOT NULL,
    space integer NOT NULL,
    name character varying(30) NOT NULL
);
    DROP TABLE public.container;
       public         heap r       postgres    false    252                       1259    16811    employee_id_seq    SEQUENCE     x   CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.employee_id_seq;
       public               postgres    false            �            1259    16612    employee    TABLE     �   CREATE TABLE public.employee (
    id integer DEFAULT nextval('public.employee_id_seq'::regclass) NOT NULL,
    person_id integer NOT NULL,
    password character varying(30) NOT NULL,
    occupation_id integer NOT NULL
);
    DROP TABLE public.employee;
       public         heap r       postgres    false    257            �            1259    16807    empty_bottles_id_seq    SEQUENCE     }   CREATE SEQUENCE public.empty_bottles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.empty_bottles_id_seq;
       public               postgres    false            �            1259    16722    empty_bottles    TABLE     �   CREATE TABLE public.empty_bottles (
    id integer DEFAULT nextval('public.empty_bottles_id_seq'::regclass) NOT NULL,
    bottle_type integer NOT NULL,
    in_stock integer NOT NULL
);
 !   DROP TABLE public.empty_bottles;
       public         heap r       postgres    false    255            �            1259    16793    fault_codes_id_seq    SEQUENCE     {   CREATE SEQUENCE public.fault_codes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.fault_codes_id_seq;
       public               postgres    false            �            1259    16410    fault_codes    TABLE     �   CREATE TABLE public.fault_codes (
    id integer DEFAULT nextval('public.fault_codes_id_seq'::regclass) NOT NULL,
    code_name character varying(12) NOT NULL
);
    DROP TABLE public.fault_codes;
       public         heap r       postgres    false    248                       1259    16819    harvest_id_seq    SEQUENCE     w   CREATE SEQUENCE public.harvest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.harvest_id_seq;
       public               postgres    false            �            1259    16657    harvest    TABLE     �   CREATE TABLE public.harvest (
    id integer DEFAULT nextval('public.harvest_id_seq'::regclass) NOT NULL,
    weight integer NOT NULL,
    sort_id integer NOT NULL
);
    DROP TABLE public.harvest;
       public         heap r       postgres    false    261            �            1259    16795    machine_type_id_seq    SEQUENCE     |   CREATE SEQUENCE public.machine_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.machine_type_id_seq;
       public               postgres    false            �            1259    16430    machine_type    TABLE     �   CREATE TABLE public.machine_type (
    id integer DEFAULT nextval('public.machine_type_id_seq'::regclass) NOT NULL,
    machine_type character varying(12) NOT NULL
);
     DROP TABLE public.machine_type;
       public         heap r       postgres    false    249            �            1259    16787    machines_id_seq    SEQUENCE     x   CREATE SEQUENCE public.machines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.machines_id_seq;
       public               postgres    false            �            1259    16426    machines    TABLE     T  CREATE TABLE public.machines (
    id integer DEFAULT nextval('public.machines_id_seq'::regclass) NOT NULL,
    machine_type_id integer,
    production_year date NOT NULL,
    fault_code_id integer,
    needs_service boolean NOT NULL,
    needs_replacement boolean NOT NULL,
    service_cost numeric(7,3) NOT NULL,
    answer_id integer
);
    DROP TABLE public.machines;
       public         heap r       postgres    false    245            �            1259    16672    mix    TABLE     u   CREATE TABLE public.mix (
    id integer NOT NULL,
    harvest_id integer NOT NULL,
    batch_id integer NOT NULL
);
    DROP TABLE public.mix;
       public         heap r       postgres    false            �            1259    16789    occupation_id_seq    SEQUENCE     z   CREATE SEQUENCE public.occupation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.occupation_id_seq;
       public               postgres    false            �            1259    16422 
   occupation    TABLE     �   CREATE TABLE public.occupation (
    id integer DEFAULT nextval('public.occupation_id_seq'::regclass) NOT NULL,
    occupation character varying(20) NOT NULL
);
    DROP TABLE public.occupation;
       public         heap r       postgres    false    246                       1259    16813    person_id_seq    SEQUENCE     v   CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public               postgres    false            �            1259    16434    person    TABLE     .  CREATE TABLE public.person (
    id integer DEFAULT nextval('public.person_id_seq'::regclass) NOT NULL,
    person_name character varying(30) NOT NULL,
    age smallint NOT NULL,
    email character varying(50) NOT NULL,
    is_male boolean NOT NULL,
    phone_number character varying(12) NOT NULL
);
    DROP TABLE public.person;
       public         heap r       postgres    false    258            �            1259    16781    progress_id_seq    SEQUENCE     x   CREATE SEQUENCE public.progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.progress_id_seq;
       public               postgres    false            �            1259    16438    progress    TABLE     �   CREATE TABLE public.progress (
    id integer DEFAULT nextval('public.progress_id_seq'::regclass) NOT NULL,
    status character varying(13) NOT NULL
);
    DROP TABLE public.progress;
       public         heap r       postgres    false    242                       1259    16817    sort_id_seq    SEQUENCE     t   CREATE SEQUENCE public.sort_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.sort_id_seq;
       public               postgres    false            �            1259    16652    sort    TABLE     �   CREATE TABLE public.sort (
    id integer DEFAULT nextval('public.sort_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE public.sort;
       public         heap r       postgres    false    260            �            1259    16797    sweetness_id_seq    SEQUENCE     y   CREATE SEQUENCE public.sweetness_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.sweetness_id_seq;
       public               postgres    false            �            1259    16442 	   sweetness    TABLE     �   CREATE TABLE public.sweetness (
    id integer DEFAULT nextval('public.sweetness_id_seq'::regclass) NOT NULL,
    category character varying(15) NOT NULL,
    le_category character varying(15)
);
    DROP TABLE public.sweetness;
       public         heap r       postgres    false    250                       1259    16823    wine_type_id_seq    SEQUENCE     y   CREATE SEQUENCE public.wine_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.wine_type_id_seq;
       public               postgres    false            �            1259    16677 	   wine_type    TABLE     �   CREATE TABLE public.wine_type (
    id integer DEFAULT nextval('public.wine_type_id_seq'::regclass) NOT NULL,
    name character varying(50)
);
    DROP TABLE public.wine_type;
       public         heap r       postgres    false    263            �          0    16642    act 
   TABLE DATA           /   COPY public.act (id, name, weight) FROM stdin;
    public               postgres    false    229   ب       �          0    16390    answers 
   TABLE DATA           .   COPY public.answers (id, answers) FROM stdin;
    public               postgres    false    217   &�       �          0    16667    batch 
   TABLE DATA           9   COPY public.batch (id, volume, wine_type_id) FROM stdin;
    public               postgres    false    232   C�       �          0    16692    batch_storidge 
   TABLE DATA           D   COPY public.batch_storidge (id, batch_id, container_id) FROM stdin;
    public               postgres    false    236   `�       �          0    16406    behavior 
   TABLE DATA           ;   COPY public.behavior (id, employee_id, act_id) FROM stdin;
    public               postgres    false    220   }�       �          0    16727    bottle_type 
   TABLE DATA           <   COPY public.bottle_type (id, name, description) FROM stdin;
    public               postgres    false    239   ��       �          0    16707    bottles 
   TABLE DATA           M   COPY public.bottles (id, batch_id, sweetness_id, residual_sugar) FROM stdin;
    public               postgres    false    237   ��       �          0    16398    client 
   TABLE DATA           ;   COPY public.client (id, person_id, company_id) FROM stdin;
    public               postgres    false    218   ԩ       �          0    16402    clients_orders 
   TABLE DATA           �   COPY public.clients_orders (id, client_id, quantity, order_date, end_date, completion_date, progress_id, end_price, wine_type_id) FROM stdin;
    public               postgres    false    219   �       �          0    16762    company 
   TABLE DATA           .   COPY public.company (id, address) FROM stdin;
    public               postgres    false    240   �       �          0    16687 	   container 
   TABLE DATA           4   COPY public.container (id, space, name) FROM stdin;
    public               postgres    false    235   +�       �          0    16612    employee 
   TABLE DATA           J   COPY public.employee (id, person_id, password, occupation_id) FROM stdin;
    public               postgres    false    228   H�       �          0    16722    empty_bottles 
   TABLE DATA           B   COPY public.empty_bottles (id, bottle_type, in_stock) FROM stdin;
    public               postgres    false    238   e�       �          0    16410    fault_codes 
   TABLE DATA           4   COPY public.fault_codes (id, code_name) FROM stdin;
    public               postgres    false    221   ��       �          0    16657    harvest 
   TABLE DATA           6   COPY public.harvest (id, weight, sort_id) FROM stdin;
    public               postgres    false    231   ��       �          0    16430    machine_type 
   TABLE DATA           8   COPY public.machine_type (id, machine_type) FROM stdin;
    public               postgres    false    224   ��       �          0    16426    machines 
   TABLE DATA           �   COPY public.machines (id, machine_type_id, production_year, fault_code_id, needs_service, needs_replacement, service_cost, answer_id) FROM stdin;
    public               postgres    false    223   ٪       �          0    16672    mix 
   TABLE DATA           7   COPY public.mix (id, harvest_id, batch_id) FROM stdin;
    public               postgres    false    233   ��       �          0    16422 
   occupation 
   TABLE DATA           4   COPY public.occupation (id, occupation) FROM stdin;
    public               postgres    false    222   �       �          0    16434    person 
   TABLE DATA           T   COPY public.person (id, person_name, age, email, is_male, phone_number) FROM stdin;
    public               postgres    false    225   0�       �          0    16438    progress 
   TABLE DATA           .   COPY public.progress (id, status) FROM stdin;
    public               postgres    false    226   M�       �          0    16652    sort 
   TABLE DATA           (   COPY public.sort (id, name) FROM stdin;
    public               postgres    false    230   j�       �          0    16442 	   sweetness 
   TABLE DATA           >   COPY public.sweetness (id, category, le_category) FROM stdin;
    public               postgres    false    227   ��       �          0    16677 	   wine_type 
   TABLE DATA           -   COPY public.wine_type (id, name) FROM stdin;
    public               postgres    false    234   ��       �           0    0 
   act_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.act_id_seq', 11, true);
          public               postgres    false    259            �           0    0    answers_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.answers_id_seq', 1, false);
          public               postgres    false    241            �           0    0    batch_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.batch_id_seq', 1, false);
          public               postgres    false    262            �           0    0    batch_storidge_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.batch_storidge_id_seq', 1, false);
          public               postgres    false    251            �           0    0    behavior_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.behavior_id_seq', 1, false);
          public               postgres    false    247            �           0    0    bottle_type_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.bottle_type_id_seq', 1, false);
          public               postgres    false    253            �           0    0    bottles_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.bottles_id_seq', 1, false);
          public               postgres    false    254            �           0    0    client_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.client_id_seq', 1, false);
          public               postgres    false    244            �           0    0    clients_orders_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.clients_orders_id_seq', 1, false);
          public               postgres    false    243            �           0    0    company_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.company_id_seq', 1, false);
          public               postgres    false    256            �           0    0    container_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.container_id_seq', 1, false);
          public               postgres    false    252            �           0    0    employee_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.employee_id_seq', 1, false);
          public               postgres    false    257            �           0    0    empty_bottles_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.empty_bottles_id_seq', 1, false);
          public               postgres    false    255            �           0    0    fault_codes_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.fault_codes_id_seq', 1, false);
          public               postgres    false    248            �           0    0    harvest_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.harvest_id_seq', 1, false);
          public               postgres    false    261            �           0    0    machine_type_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.machine_type_id_seq', 1, false);
          public               postgres    false    249            �           0    0    machines_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.machines_id_seq', 1, false);
          public               postgres    false    245            �           0    0    occupation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.occupation_id_seq', 1, false);
          public               postgres    false    246            �           0    0    person_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.person_id_seq', 1, false);
          public               postgres    false    258                        0    0    progress_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.progress_id_seq', 1, false);
          public               postgres    false    242                       0    0    sort_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.sort_id_seq', 1, false);
          public               postgres    false    260                       0    0    sweetness_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.sweetness_id_seq', 1, false);
          public               postgres    false    250                       0    0    wine_type_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.wine_type_id_seq', 1, false);
          public               postgres    false    263            �           2606    16646    act acts_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY public.act
    ADD CONSTRAINT acts_pkey PRIMARY KEY (id);
 7   ALTER TABLE ONLY public.act DROP CONSTRAINT acts_pkey;
       public                 postgres    false    229            �           2606    16481    answers answers_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.answers DROP CONSTRAINT answers_pkey;
       public                 postgres    false    217            �           2606    16671    batch batch_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.batch
    ADD CONSTRAINT batch_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.batch DROP CONSTRAINT batch_pkey;
       public                 postgres    false    232                       2606    16696 "   batch_storidge batch_storidge_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.batch_storidge DROP CONSTRAINT batch_storidge_pkey;
       public                 postgres    false    236                       2606    16731    bottle_type bottle_type_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.bottle_type
    ADD CONSTRAINT bottle_type_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.bottle_type DROP CONSTRAINT bottle_type_pkey;
       public                 postgres    false    239            	           2606    16711    bottles bottles_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.bottles DROP CONSTRAINT bottles_pkey;
       public                 postgres    false    237            �           2606    16485    client client_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public                 postgres    false    218            �           2606    16487 !   clients_orders clientsorders_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_pkey PRIMARY KEY (id);
 K   ALTER TABLE ONLY public.clients_orders DROP CONSTRAINT clientsorders_pkey;
       public                 postgres    false    219                       2606    16766    company company_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.company DROP CONSTRAINT company_pkey;
       public                 postgres    false    240                       2606    16691    container container_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.container
    ADD CONSTRAINT container_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.container DROP CONSTRAINT container_pkey;
       public                 postgres    false    235            �           2606    16616    employee employee_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public                 postgres    false    228            �           2606    16489    behavior employeescore_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT employeescore_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY public.behavior DROP CONSTRAINT employeescore_pkey;
       public                 postgres    false    220                       2606    16726     empty_bottles empty_bottles_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.empty_bottles
    ADD CONSTRAINT empty_bottles_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.empty_bottles DROP CONSTRAINT empty_bottles_pkey;
       public                 postgres    false    238            �           2606    16491    fault_codes faultcodes_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.fault_codes
    ADD CONSTRAINT faultcodes_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY public.fault_codes DROP CONSTRAINT faultcodes_pkey;
       public                 postgres    false    221            �           2606    16661    harvest harvest_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.harvest DROP CONSTRAINT harvest_pkey;
       public                 postgres    false    231            �           2606    16497    occupation joboccupation_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.occupation
    ADD CONSTRAINT joboccupation_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.occupation DROP CONSTRAINT joboccupation_pkey;
       public                 postgres    false    222            �           2606    16499    machines machines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.machines DROP CONSTRAINT machines_pkey;
       public                 postgres    false    223            �           2606    16501    machine_type machinetype_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.machine_type
    ADD CONSTRAINT machinetype_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.machine_type DROP CONSTRAINT machinetype_pkey;
       public                 postgres    false    224                       2606    16676    mix mix_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.mix DROP CONSTRAINT mix_pkey;
       public                 postgres    false    233            �           2606    16503    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public                 postgres    false    225            �           2606    16505    progress progress_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.progress
    ADD CONSTRAINT progress_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.progress DROP CONSTRAINT progress_pkey;
       public                 postgres    false    226            �           2606    16656    sort sort_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.sort
    ADD CONSTRAINT sort_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.sort DROP CONSTRAINT sort_pkey;
       public                 postgres    false    230            �           2606    16507    sweetness sweetness_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.sweetness
    ADD CONSTRAINT sweetness_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.sweetness DROP CONSTRAINT sweetness_pkey;
       public                 postgres    false    227                       2606    16681    wine_type wine_type_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.wine_type
    ADD CONSTRAINT wine_type_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.wine_type DROP CONSTRAINT wine_type_pkey;
       public                 postgres    false    234                        2606    16697 +   batch_storidge batch_storidge_batch_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id);
 U   ALTER TABLE ONLY public.batch_storidge DROP CONSTRAINT batch_storidge_batch_id_fkey;
       public               postgres    false    236    232    4863            !           2606    16702 /   batch_storidge batch_storidge_container_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.batch_storidge
    ADD CONSTRAINT batch_storidge_container_id_fkey FOREIGN KEY (container_id) REFERENCES public.container(id) NOT VALID;
 Y   ALTER TABLE ONLY public.batch_storidge DROP CONSTRAINT batch_storidge_container_id_fkey;
       public               postgres    false    236    235    4869                       2606    16682    batch batch_wine_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.batch
    ADD CONSTRAINT batch_wine_type_id_fkey FOREIGN KEY (wine_type_id) REFERENCES public.wine_type(id) NOT VALID;
 G   ALTER TABLE ONLY public.batch DROP CONSTRAINT batch_wine_type_id_fkey;
       public               postgres    false    4867    234    232                       2606    16647    behavior behavior_act_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT behavior_act_id_fkey FOREIGN KEY (act_id) REFERENCES public.act(id) NOT VALID;
 G   ALTER TABLE ONLY public.behavior DROP CONSTRAINT behavior_act_id_fkey;
       public               postgres    false    4857    229    220                       2606    16637 "   behavior behavior_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.behavior
    ADD CONSTRAINT behavior_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(id) NOT VALID;
 L   ALTER TABLE ONLY public.behavior DROP CONSTRAINT behavior_employee_id_fkey;
       public               postgres    false    228    4855    220            "           2606    16712    bottles bottles_batch_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id);
 G   ALTER TABLE ONLY public.bottles DROP CONSTRAINT bottles_batch_id_fkey;
       public               postgres    false    232    237    4863            #           2606    16717 !   bottles bottles_sweetness_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bottles
    ADD CONSTRAINT bottles_sweetness_id_fkey FOREIGN KEY (sweetness_id) REFERENCES public.sweetness(id) NOT VALID;
 K   ALTER TABLE ONLY public.bottles DROP CONSTRAINT bottles_sweetness_id_fkey;
       public               postgres    false    4853    227    237                       2606    16767    client client_company_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.company(id) NOT VALID;
 G   ALTER TABLE ONLY public.client DROP CONSTRAINT client_company_id_fkey;
       public               postgres    false    218    4879    240                       2606    16632    client client_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;
 F   ALTER TABLE ONLY public.client DROP CONSTRAINT client_person_id_fkey;
       public               postgres    false    218    4849    225                       2606    16752 /   clients_orders clients_orders_wine_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clients_orders_wine_type_id_fkey FOREIGN KEY (wine_type_id) REFERENCES public.wine_type(id) NOT VALID;
 Y   ALTER TABLE ONLY public.clients_orders DROP CONSTRAINT clients_orders_wine_type_id_fkey;
       public               postgres    false    219    234    4867                       2606    16526 +   clients_orders clientsorders_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(id);
 U   ALTER TABLE ONLY public.clients_orders DROP CONSTRAINT clientsorders_client_id_fkey;
       public               postgres    false    4835    218    219                       2606    16531 -   clients_orders clientsorders_progress_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.clients_orders
    ADD CONSTRAINT clientsorders_progress_id_fkey FOREIGN KEY (progress_id) REFERENCES public.progress(id);
 W   ALTER TABLE ONLY public.clients_orders DROP CONSTRAINT clientsorders_progress_id_fkey;
       public               postgres    false    219    4851    226                       2606    16622 $   employee employee_occupation_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_occupation_id_fkey FOREIGN KEY (occupation_id) REFERENCES public.occupation(id) NOT VALID;
 N   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_occupation_id_fkey;
       public               postgres    false    4843    228    222                       2606    16627     employee employee_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;
 J   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_person_id_fkey;
       public               postgres    false    4849    225    228            $           2606    16747 ,   empty_bottles empty_bottles_bottle_type_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.empty_bottles
    ADD CONSTRAINT empty_bottles_bottle_type_fkey FOREIGN KEY (bottle_type) REFERENCES public.bottle_type(id) NOT VALID;
 V   ALTER TABLE ONLY public.empty_bottles DROP CONSTRAINT empty_bottles_bottle_type_fkey;
       public               postgres    false    238    239    4877                       2606    16662    harvest harvest_sort_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_sort_id_fkey FOREIGN KEY (sort_id) REFERENCES public.sort(id) NOT VALID;
 F   ALTER TABLE ONLY public.harvest DROP CONSTRAINT harvest_sort_id_fkey;
       public               postgres    false    4859    230    231                       2606    16556     machines machines_answer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_answer_id_fkey FOREIGN KEY (answer_id) REFERENCES public.answers(id);
 J   ALTER TABLE ONLY public.machines DROP CONSTRAINT machines_answer_id_fkey;
       public               postgres    false    217    4833    223                       2606    16561 $   machines machines_fault_code_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_fault_code_id_fkey FOREIGN KEY (fault_code_id) REFERENCES public.fault_codes(id);
 N   ALTER TABLE ONLY public.machines DROP CONSTRAINT machines_fault_code_id_fkey;
       public               postgres    false    4841    221    223                       2606    16566 &   machines machines_machine_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.machines
    ADD CONSTRAINT machines_machine_type_id_fkey FOREIGN KEY (machine_type_id) REFERENCES public.machine_type(id);
 P   ALTER TABLE ONLY public.machines DROP CONSTRAINT machines_machine_type_id_fkey;
       public               postgres    false    224    4847    223                       2606    16742    mix mix_batch_id_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES public.batch(id) NOT VALID;
 ?   ALTER TABLE ONLY public.mix DROP CONSTRAINT mix_batch_id_fkey;
       public               postgres    false    232    233    4863                       2606    16737    mix mix_harvest_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.mix
    ADD CONSTRAINT mix_harvest_id_fkey FOREIGN KEY (harvest_id) REFERENCES public.harvest(id) NOT VALID;
 A   ALTER TABLE ONLY public.mix DROP CONSTRAINT mix_harvest_id_fkey;
       public               postgres    false    233    4861    231            �   >   x���,�����KW��SH)-�T�/-)�LI��57�Đ�̃H�p��lh�Ww� k'�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     