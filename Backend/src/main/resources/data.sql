INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (1, 'Sremska Mitrovica','Srbija', 'editor@yahoo.com', true, 'Edi', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 3,'Edi');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (2, 'Sremska Mitrovica','Srbija', 'editor1@yahoo.com', true, 'Edi', '$2y$10$ZZ2O5tExTIbunC8EmUGPkORPsFDiFi908Y9BMeq1y7AOHLW/i2zUa', 3,'Edi1');

INSERT INTO public.genre(
	id, name)
	VALUES (1, 'Horror');

INSERT INTO public.genre(
	id, name)
	VALUES (2, 'Drama');

INSERT INTO public.genre(
    id, name)
    VALUES (3, 'Sci Fi');
