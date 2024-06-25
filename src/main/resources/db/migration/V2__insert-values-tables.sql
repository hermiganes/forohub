INSERT INTO perfiles (perfil) VALUES
('ALUMNO'),
('PROFESOR'),
('ADMID'),
('ROOT');


INSERT INTO usuarios (nombre_usuario, correo, contrasena, perfil_id) VALUES
('Usuario1', 'usuario1@example.com', 'contrasena123', 1),
('Usuario2', 'usuario2@example.com', 'contrasena456', 2),
('Usuario3', 'usuario3@example.com', 'contrasena789', 3),
('Usuario4', 'usuario4@example.com', 'contrasenaabc', 4),
('Usuario5', 'usuario5@example.com', 'contrasenadef', 1);

INSERT INTO cursos (nombre_curso, categoria) VALUES
('Curso de Python', 'PYTHON'),
('Curso de Java', 'JAVA'),
('Curso de PostgreSQL', 'POSTGRESQL'),
('Curso de MySQL', 'MYSQL');