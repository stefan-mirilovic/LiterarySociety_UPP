INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10001, 'Sremska Mitrovica','Srbija', 'editor@yahoo.com', true, 'Edi', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 3,'Edi');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10002, 'Sremska Mitrovica','Srbija', 'editor1@yahoo.com', true, 'Edi', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 3,'Edi1');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10003, 'Sremska Mitrovica','Srbija', 'comitee1@maildrop.cc', true, 'Marija', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 5,'Marijic');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10004, 'Sremska Mitrovica','Srbija', 'comitee2@maildrop.cc', true, 'Nikola', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 5,'Nikolic');

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
    VALUES (4, 'History');
