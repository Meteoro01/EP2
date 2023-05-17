/*CREAMOS LAS TABLAS*/
CREATE TABLE Tareas (
    IdTarea INT PRIMARY KEY,
    IdProyecto INT,
    IdUsuario INT,
    TituloTarea VARCHAR(100),
    DescripcionTarea VARCHAR(255),
    FechaVencimiento DATE,
    Estado VARCHAR(20),
    FOREIGN KEY (IdProyecto) REFERENCES Proyectos(IdProyecto),
    FOREIGN KEY (IdUsuario) REFERENCES usuario (IdUsuario)
);
CREATE TABLE Proyectos (
    IdProyecto INT PRIMARY KEY,
    IdUsuario INT,
    TituloProyecto VARCHAR(50),
    DescripcionProyecto VARCHAR(255),
    FOREIGN KEY (IdUsuario) REFERENCES usuario (IdUsuario)
);
CREATE TABLE usuario (
  IdUsuario INT NOT NULL AUTO_INCREMENT,
  Nombres  VARCHAR(50) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  Correo VARCHAR(50) NOT NULL,
  Contraseña VARCHAR(50) NOT NULL,
  PRIMARY KEY (IDUsuario)
);
/*INSERTAMOS LOS DATOS A LAS TABLAS*/
INSERT INTO Tareas (IdTarea, IdProyecto, IdUsuario, TituloTarea, DescripcionTarea, FechaVencimiento, Estado)
VALUES
    (1, 1, 1, 'Tarea 1', 'Descripción de la Tarea 1', '2023-05-01', 'Pendiente'),
    (2, 1, 2,  'Tarea 2', 'Descripción de la Tarea 2', '2023-05-02', 'En progreso'),
    (3, 2, 1,  'Tarea 3', 'Descripción de la Tarea 3', '2023-05-03', 'Completada'),
    (4, 2, 2,  'Tarea 4', 'Descripción de la Tarea 4', '2023-05-04', 'Pendiente');

INSERT INTO Proyectos (IdProyecto, IdUsuario, TituloProyecto, DescripcionProyecto)
VALUES
    (1, 1, 'Proyecto A', 'Descripción del Proyecto A'),
    (2, 1, 'Proyecto B', 'Descripción del Proyecto B'),
    (3, 2, 'Proyecto C', 'Descripción del Proyecto C');
    
INSERT INTO usuario (IDUsuario,Nombres,fecha_nacimiento, Correo)
VALUES 
(1,'Luis','1990-05-10', 'luis.garcia@gmail.com'),
(2,'Ana', '1992-07-22', 'ana.perez@hotmail.com');