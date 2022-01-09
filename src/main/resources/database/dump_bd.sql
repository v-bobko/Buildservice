--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: calendar_service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.calendar_service (
    object_id integer,
    date_start date,
    date_exec date,
    who_response_id integer,
    tasks character varying(500),
    comments_of_worker character varying(500),
    comments_of_client character varying(500),
    materials character varying(300),
    photo_ref character varying(300)
);


ALTER TABLE public.calendar_service OWNER TO postgres;

--
-- Name: estimate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estimate (
    object_id integer,
    kind_of_work_id integer,
    cost_salary integer,
    cost_materials integer
);


ALTER TABLE public.estimate OWNER TO postgres;

--
-- Name: objects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.objects (
    object_id integer NOT NULL,
    client_id integer,
    name_of_object character varying(50),
    kind_of_work_id integer
);


ALTER TABLE public.objects OWNER TO postgres;

--
-- Name: objects_object_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.objects_object_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.objects_object_id_seq OWNER TO postgres;

--
-- Name: objects_object_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.objects_object_id_seq OWNED BY public.objects.object_id;


--
-- Name: projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projects (
    object_id integer,
    name_of_project character varying(100),
    reference character varying(200)
);


ALTER TABLE public.projects OWNER TO postgres;

--
-- Name: resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resources (
    user_id integer,
    login character varying(30),
    password character varying(100),
    enabled integer
);


ALTER TABLE public.resources OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    name character varying(100),
    role character varying(20),
    contacts character varying(70)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: work; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.work (
    kind_of_work_id integer NOT NULL,
    name_of_work character varying(100)
);


ALTER TABLE public.work OWNER TO postgres;

--
-- Name: work_force; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.work_force (
    worker_id integer,
    object_id integer,
    work_days integer,
    kind_of_work_id integer,
    payment integer,
    payment_for_materials integer,
    check_for_materials_ref character varying(300)
);


ALTER TABLE public.work_force OWNER TO postgres;

--
-- Name: work_kind_of_work_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.work_kind_of_work_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.work_kind_of_work_id_seq OWNER TO postgres;

--
-- Name: work_kind_of_work_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.work_kind_of_work_id_seq OWNED BY public.work.kind_of_work_id;


--
-- Name: objects object_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects ALTER COLUMN object_id SET DEFAULT nextval('public.objects_object_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Name: work kind_of_work_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work ALTER COLUMN kind_of_work_id SET DEFAULT nextval('public.work_kind_of_work_id_seq'::regclass);


--
-- Data for Name: calendar_service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.calendar_service (object_id, date_start, date_exec, who_response_id, tasks, comments_of_worker, comments_of_client, materials, photo_ref) FROM stdin;
2	2022-01-10	\N	5	Организация постоянной схемы отопления	\N	\N	\N	\N
2	2022-01-15	\N	5	Очистка системы водоснабжения механическим способом	\N	\N	\N	\N
2	2022-01-10	\N	5	Чистка систем кондиционирования	\N	\N	\N	\N
1	2022-01-25	\N	5	Очистка дымохода в гараже	\N	\N	\N	\N
\.


--
-- Data for Name: estimate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estimate (object_id, kind_of_work_id, cost_salary, cost_materials) FROM stdin;
\.


--
-- Data for Name: objects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objects (object_id, client_id, name_of_object, kind_of_work_id) FROM stdin;
1	4	Заполье	\N
2	4	пл. Конституции	\N
\.


--
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projects (object_id, name_of_project, reference) FROM stdin;
1	Дизайн проект реконструкции дома	https://drive.google.com/file/d/1OM5zXe_KEhwoXYSxjlGi8330-pP3OlSZ/view?usp=sharing
1	Проект внешнего газоснабжения	https://drive.google.com/file/d/16AOMz62RIYC_4_yEkIIkAIdl24uakQ1y/view?usp=sharing
1	Проект внутреннего газоснабжения	https://drive.google.com/file/d/1IiyjU8wXxlGjvaI0PglinypZdc_AR8Tb/view?usp=sharing
1	Ландшафтный дизайн	https://drive.google.com/file/d/1jvieFjNoEivd5wbMPwZtaZ-B8miDOA6U/view?usp=sharing
2	Проект отопления	https://drive.google.com/file/d/108AYIDb6HgzSd_C-7Q3hpxgNoWaJyoXY/view?usp=sharing
\.


--
-- Data for Name: resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.resources (user_id, login, password, enabled) FROM stdin;
1	ilyushin	{bcrypt}$2a$10$XE01ka/lAhAxivRDozd1P.8hPlIrGpKUi/A0a/K8N9/8/0G/pZane	1
2	bobko	{bcrypt}$2a$10$S.XvRAcuB2n2eZFIHTXFyeX0XqKJhDFAjO8dsrVdTAKhLyKU7t.oi	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, name, role, contacts) FROM stdin;
1	Антон Илюшин	ROLE_ADMIN	89216548205
2	Валерия Бобко	ROLE_ADMIN	89818810995
3	Илья Абрамов	ROLE_ENGINEER	null
4	Александр Крылов	ROLE_CLIENT	null
5	Станислав Баранов	ROLE_WORKER	null
6	Юрий Васин	ROLE_ENGINEER	null
\.


--
-- Data for Name: work; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.work (kind_of_work_id, name_of_work) FROM stdin;
1	Чистка вентиляции
2	Обслуживание систем водоснабжения
3	Обслуживание щитов электроснабжение
4	Устранение неисправностей
5	Очистка дымоходов
6	Чистка систем водоснабжения
\.


--
-- Data for Name: work_force; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.work_force (worker_id, object_id, work_days, kind_of_work_id, payment, payment_for_materials, check_for_materials_ref) FROM stdin;
\.


--
-- Name: objects_object_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.objects_object_id_seq', 2, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 6, true);


--
-- Name: work_kind_of_work_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.work_kind_of_work_id_seq', 6, true);


--
-- Name: objects objects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects
    ADD CONSTRAINT objects_pkey PRIMARY KEY (object_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: work work_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work
    ADD CONSTRAINT work_pkey PRIMARY KEY (kind_of_work_id);


--
-- Name: calendar_service calendar_service_object_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calendar_service
    ADD CONSTRAINT calendar_service_object_id_fkey FOREIGN KEY (object_id) REFERENCES public.objects(object_id);


--
-- Name: calendar_service calendar_service_who_response_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calendar_service
    ADD CONSTRAINT calendar_service_who_response_id_fkey FOREIGN KEY (who_response_id) REFERENCES public.users(user_id);


--
-- Name: estimate estimate_kind_of_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estimate
    ADD CONSTRAINT estimate_kind_of_work_id_fkey FOREIGN KEY (kind_of_work_id) REFERENCES public.work(kind_of_work_id);


--
-- Name: estimate estimate_object_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estimate
    ADD CONSTRAINT estimate_object_id_fkey FOREIGN KEY (object_id) REFERENCES public.objects(object_id);


--
-- Name: objects objects_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects
    ADD CONSTRAINT objects_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.users(user_id);


--
-- Name: objects objects_kind_of_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects
    ADD CONSTRAINT objects_kind_of_work_id_fkey FOREIGN KEY (kind_of_work_id) REFERENCES public.work(kind_of_work_id);


--
-- Name: projects projects_object_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_object_id_fkey FOREIGN KEY (object_id) REFERENCES public.objects(object_id);


--
-- Name: resources resources_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources
    ADD CONSTRAINT resources_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: work_force work_force_kind_of_work_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_force
    ADD CONSTRAINT work_force_kind_of_work_id_fkey FOREIGN KEY (kind_of_work_id) REFERENCES public.work(kind_of_work_id);


--
-- Name: work_force work_force_object_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_force
    ADD CONSTRAINT work_force_object_id_fkey FOREIGN KEY (object_id) REFERENCES public.objects(object_id);


--
-- Name: work_force work_force_worker_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_force
    ADD CONSTRAINT work_force_worker_id_fkey FOREIGN KEY (worker_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

