INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10001, 'Sremska Mitrovica','Srbija', 'editor@maildrop.cc', true, 'Edi', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 3,'Edi');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10010, 'Sremska Mitrovica','Srbija', 'writer1@maildrop.cc', true, 'Edi', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 0,'Edi');


INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10002, 'Sremska Mitrovica','Srbija', 'editor1@maildrop.cc', true, 'Edi', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 3,'Edi1');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10003, 'Sremska Mitrovica','Srbija', 'committee1@maildrop.cc', true, 'Marija', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 5,'Marijic');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10004, 'Sremska Mitrovica','Srbija', 'committee2@maildrop.cc', true, 'Nikola', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 5,'Nikolic');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10005, 'Becej','Srbija', 'main-editor@maildrop.cc', true, 'Edward', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 8,'Editor');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10006, 'Novi Sad','Srbija', 'committee-leader@maildrop.cc', true, 'Squidward', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 6,'Tentacles');


INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10008, 'Sremska Mitrovica','Srbija', 'reader@maildrop.cc', true, 'Reader', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 1,'Read');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10009, 'Novi Sad','Srbija', 'novisad@maildrop.cc', true, 'Reader', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 2,'Read');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10011, 'Washington DC','Pennsylvania ', 'washington@maildrop.cc', true, 'Reader', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 2,'Read');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10012, 'Beograd','Srbija', 'beograd@maildrop.cc', true, 'Reader', '$2a$10$XBU45zuPtJ3QvohDk6zDNeha9FSfatukS7VFoG0BXIYa.X/KK9rwy', 2,'Read');

INSERT INTO public.genre(
	id, name)
	VALUES (1, 'Horror');

INSERT INTO public.genre(
	id, name)
	VALUES (2, 'Drama');

INSERT INTO public.genre(
    id, name)
    VALUES (3, 'Science Fiction');

INSERT INTO public.genre(
    id, name)
    VALUES (4, 'Comedy');

INSERT INTO public.genre(
    id, name)
    VALUES (5, 'Educational');
