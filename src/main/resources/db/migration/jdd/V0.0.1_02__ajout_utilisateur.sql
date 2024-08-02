INSERT INTO public.utilisateur
(id, "version", create_by, create_at, update_by, update_at, nom_prenoms, username, email, "password", actif, role )
VALUES(nextval('utilisateur_id_seq'), 0, 'RDD', CURRENT_TIMESTAMP, NULL, NULL,'Kouassi Amon franck', 'lezekie', 'ekouassi506@gmail.com', '$2a$10$c6rTdoMdajHowLr1oedPEOzyvgRKRPmye/MQbY8IIfcPMUXrjCP2y', true,'ADMIN');
