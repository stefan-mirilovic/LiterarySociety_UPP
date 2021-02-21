INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10001, 'Sremska Mitrovica','Srbija', 'editor@yahoo.com', true, 'Edi', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 3,'Edi');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10010, 'Sremska Mitrovica','Srbija', 'writer1@yahoo.com', true, 'Edi', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 1,'Edi');


INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10002, 'Sremska Mitrovica','Srbija', 'editor1@yahoo.com', true, 'Edi', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 3,'Edi1');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10003, 'Sremska Mitrovica','Srbija', 'committee1@maildrop.cc', true, 'Marija', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 5,'Marijic');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10004, 'Sremska Mitrovica','Srbija', 'committee2@maildrop.cc', true, 'Nikola', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 5,'Nikolic');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10005, 'Becej','Srbija', 'main-editor@maildrop.cc', true, 'Edward', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 8,'Editor');

INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10006, 'Novi Sad','Srbija', 'committee-leader@maildrop.cc', true, 'Squidward', '$2y$10$ovqM3ue/XJCuF1mbwt/CMOhNrTyxq.aFU8mfDzTGAddvyKS3rc/RG', 6,'Tentacles');


INSERT INTO public.user_table(
	id, city, country, email, enabled, name, password, role, surname)
	VALUES (10008, 'Sremska Mitrovica','Srbija', 'reader@yahoo.com', true, 'Reader', '$2y$10$rAUWMhHGu/3eQZvykAYudeQSBp1g.xqmyFwss8yP8dpHWHRbrKk12', 1,'Read');

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
    VALUES (32, 'Comedy');
