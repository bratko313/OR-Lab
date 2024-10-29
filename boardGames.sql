--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

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
-- Name: artists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artists (
    artist_id integer NOT NULL,
    name character varying(255),
    surname character varying(255)
);


ALTER TABLE public.artists OWNER TO postgres;

--
-- Name: artists_artist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artists_artist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.artists_artist_id_seq OWNER TO postgres;

--
-- Name: artists_artist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artists_artist_id_seq OWNED BY public.artists.artist_id;


--
-- Name: boardgameartists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boardgameartists (
    board_game_id integer NOT NULL,
    artist_id integer NOT NULL
);


ALTER TABLE public.boardgameartists OWNER TO postgres;

--
-- Name: boardgamedesigners; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boardgamedesigners (
    board_game_id integer NOT NULL,
    designer_id integer NOT NULL
);


ALTER TABLE public.boardgamedesigners OWNER TO postgres;

--
-- Name: boardgames; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boardgames (
    board_game_id integer NOT NULL,
    name character varying(255),
    release_year integer,
    number_of_players character varying(16),
    age character varying(16),
    playing_time character varying(64),
    franchise_id integer,
    publisher_id integer
);


ALTER TABLE public.boardgames OWNER TO postgres;

--
-- Name: boardgames_board_game_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.boardgames_board_game_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.boardgames_board_game_id_seq OWNER TO postgres;

--
-- Name: boardgames_board_game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.boardgames_board_game_id_seq OWNED BY public.boardgames.board_game_id;


--
-- Name: designers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.designers (
    designer_id integer NOT NULL,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL
);


ALTER TABLE public.designers OWNER TO postgres;

--
-- Name: designers_designer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.designers_designer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.designers_designer_id_seq OWNER TO postgres;

--
-- Name: designers_designer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.designers_designer_id_seq OWNED BY public.designers.designer_id;


--
-- Name: franchises; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.franchises (
    franchise_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.franchises OWNER TO postgres;

--
-- Name: franchises_franchise_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.franchises_franchise_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.franchises_franchise_id_seq OWNER TO postgres;

--
-- Name: franchises_franchise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.franchises_franchise_id_seq OWNED BY public.franchises.franchise_id;


--
-- Name: publishers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishers (
    publisher_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.publishers OWNER TO postgres;

--
-- Name: publishers_publisher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.publishers_publisher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.publishers_publisher_id_seq OWNER TO postgres;

--
-- Name: publishers_publisher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.publishers_publisher_id_seq OWNED BY public.publishers.publisher_id;


--
-- Name: artists artist_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists ALTER COLUMN artist_id SET DEFAULT nextval('public.artists_artist_id_seq'::regclass);


--
-- Name: boardgames board_game_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgames ALTER COLUMN board_game_id SET DEFAULT nextval('public.boardgames_board_game_id_seq'::regclass);


--
-- Name: designers designer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.designers ALTER COLUMN designer_id SET DEFAULT nextval('public.designers_designer_id_seq'::regclass);


--
-- Name: franchises franchise_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.franchises ALTER COLUMN franchise_id SET DEFAULT nextval('public.franchises_franchise_id_seq'::regclass);


--
-- Name: publishers publisher_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers ALTER COLUMN publisher_id SET DEFAULT nextval('public.publishers_publisher_id_seq'::regclass);


--
-- Data for Name: artists; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artists (artist_id, name, surname) FROM stdin;
1	Justin	Adams
2	W.T.	Arnold
3	Anders	Finér
12	Cyrille	Daujean
13	Julien	Delval
16	Martin	Mottet
17	Miguel	Coimbra
20	Isaac	Fryxelius
21	Daniel	Fryxelius
22	William	Bricker
\.


--
-- Data for Name: boardgameartists; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.boardgameartists (board_game_id, artist_id) FROM stdin;
1	1
1	2
1	3
5	12
5	13
9	12
9	13
13	16
14	17
17	20
17	21
18	20
18	22
\.


--
-- Data for Name: boardgamedesigners; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.boardgamedesigners (board_game_id, designer_id) FROM stdin;
1	1
1	2
1	3
5	8
9	8
10	11
10	12
11	11
11	12
13	17
13	18
14	19
13	20
15	21
13	22
17	24
18	24
18	25
\.


--
-- Data for Name: boardgames; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.boardgames (board_game_id, name, release_year, number_of_players, age, playing_time, franchise_id, publisher_id) FROM stdin;
1	Arkham Horror (Third Edition)	2018	1-6	14+	120-180 minutes	2	1
5	Ticket to Ride: Europe	2005	2-5	8+	30-60 minutes	7	6
9	Ticket to Ride	2004	2-5	8+	30-60 minutes	7	6
10	Magic: The Gathering - Kaladesh	2016	2+	13+	60 minutes	8	8
11	Magic: The Gathering - Amonkhet	2017	2+	13+	60 minutes	8	8
13	The Magnificent	2019	1-4	14+	60-90 minutes	11	12
14	Cyclades	2009	2-5	13+	60-90 minutes	12	13
15	Risk	1959	2-6	10+	120 minutes	13	14
17	Terraforming Mars	2016	1-5	12+	120 minutes	15	16
18	Terraforming Mars: Colonies	2018	1-5	12+	120 minutes	15	16
\.


--
-- Data for Name: designers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.designers (designer_id, name, surname) FROM stdin;
1	Nikki	Valens
2	Richard	Launius
3	Kevin	Wilson
8	Alan R.	Moon
11	Richard	Garfield
12	Ben	Hayes
17	Kristian Amundsen	Østby
18	Eilif	Svensson
19	Bruno	Cathala
20	Ludovic	Maublanc
21	Albert	Lamorisse
22	Michael I.	Levin
24	Jacob	Fryxelius
25	Jonathan	Fryxelius
\.


--
-- Data for Name: franchises; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.franchises (franchise_id, name) FROM stdin;
2	Arkham Horror
7	Ticket to Ride
8	Magic: The Gathering
11	The Magnificent
12	Cyclades
13	Risk
15	Terraforming Mars
\.


--
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.publishers (publisher_id, name) FROM stdin;
1	Fantasy Flight Games
6	Days of Wonder
8	Wizards of the Coast
12	Aporta Games
13	Matagot
14	Parker Brothers
16	FryxGames
\.


--
-- Name: artists_artist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artists_artist_id_seq', 22, true);


--
-- Name: boardgames_board_game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.boardgames_board_game_id_seq', 18, true);


--
-- Name: designers_designer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.designers_designer_id_seq', 25, true);


--
-- Name: franchises_franchise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.franchises_franchise_id_seq', 15, true);


--
-- Name: publishers_publisher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.publishers_publisher_id_seq', 16, true);


--
-- Name: artists artists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (artist_id);


--
-- Name: boardgameartists boardgameartists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgameartists
    ADD CONSTRAINT boardgameartists_pkey PRIMARY KEY (board_game_id, artist_id);


--
-- Name: boardgamedesigners boardgamedesigners_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgamedesigners
    ADD CONSTRAINT boardgamedesigners_pkey PRIMARY KEY (board_game_id, designer_id);


--
-- Name: boardgames boardgames_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgames
    ADD CONSTRAINT boardgames_pkey PRIMARY KEY (board_game_id);


--
-- Name: designers designers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.designers
    ADD CONSTRAINT designers_pkey PRIMARY KEY (designer_id);


--
-- Name: franchises franchises_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.franchises
    ADD CONSTRAINT franchises_pkey PRIMARY KEY (franchise_id);


--
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (publisher_id);


--
-- Name: boardgameartists boardgameartists_artist_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgameartists
    ADD CONSTRAINT boardgameartists_artist_id_fkey FOREIGN KEY (artist_id) REFERENCES public.artists(artist_id);


--
-- Name: boardgameartists boardgameartists_board_game_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgameartists
    ADD CONSTRAINT boardgameartists_board_game_id_fkey FOREIGN KEY (board_game_id) REFERENCES public.boardgames(board_game_id);


--
-- Name: boardgamedesigners boardgamedesigners_board_game_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgamedesigners
    ADD CONSTRAINT boardgamedesigners_board_game_id_fkey FOREIGN KEY (board_game_id) REFERENCES public.boardgames(board_game_id);


--
-- Name: boardgamedesigners boardgamedesigners_designer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgamedesigners
    ADD CONSTRAINT boardgamedesigners_designer_id_fkey FOREIGN KEY (designer_id) REFERENCES public.designers(designer_id);


--
-- Name: boardgames boardgames_franchise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgames
    ADD CONSTRAINT boardgames_franchise_id_fkey FOREIGN KEY (franchise_id) REFERENCES public.franchises(franchise_id) ON DELETE CASCADE;


--
-- Name: boardgames fk_publisher_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgames
    ADD CONSTRAINT fk_publisher_id FOREIGN KEY (publisher_id) REFERENCES public.publishers(publisher_id);


--
-- PostgreSQL database dump complete
--

