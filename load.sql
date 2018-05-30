insert into Role values ('ROLE_ADMIN');

insert into Usuario (email, nome, senha) values ('admin', 'Administrador', '$2a$04$gJ/5Yks69c9vAG2ClRGLneW5US31Lz/dz2Krj6aTWSYkiJOv.nNAm');

insert into Usuario_Role(Usuario_email, roles_nome) values ('admin', 'ROLE_ADMIN');
