# 🏥 Sistema de Administración de Citas Médicas

Este proyecto es una aplicación de escritorio desarrollada en **Java**, que simula un sistema de administración de citas para un consultorio clínico.  
Permite gestionar doctores, pacientes, citas médicas y usuarios del sistema con control de acceso mediante autenticación.

---

## Instalación y configuración

### Requisitos previos
- **Java JDK 17** o superior instalado en tu sistema.
- **IntelliJ IDEA** (recomendado) o cualquier IDE compatible con Java.
- Acceso a una terminal o consola para ejecutar el programa.

### Instalación
1. Clona este repositorio en tu equipo local:
   ```bash
    git clone https://github.com/tu-usuario/EvidenciaFinal.git

2. Abre el proyecto en IntelliJ IDEA.

3. Asegúrate de que la estructura del proyecto sea la siguiente:
    ```css
    EvidenciaFinal/
    ├── src/
    │   ├── Cita.java
    │   ├── Doctor.java
    │   ├── Paciente.java
    │   ├── Usuario.java
    │   └── SistemaCitas.java
    ├── db/
    │   ├── citas.csv
    │   ├── doctores.csv
    │   ├── pacientes.csv
    │   └── usuarios.csv
    ├── .gitignore
    └── README.md

4. Si no existen los archivos dentro de la carpeta db/, el programa los creará automáticamente al ejecutarse.

### Compilación como FAT JAR

Para garantizar la portabilidad del programa:

1. Ve a File → Project Structure → Artifacts.

2. Crea un nuevo artefacto: JAR → From modules with dependencies...

3. Selecciona la clase principal Main y marca la opción Extract to the target JAR.

4. Compila el proyecto desde el menú: Build → Build Artifacts → Build.

5. El archivo ejecutable quedará en:
    ```bash
    out/artifacts/EvidenciaFinal_FAT/EvidenciaFinal.jar

Para ejecutar el programa:
    
    java -jar EvidenciaFinal.jar

## Uso del programa

### Inicio de sesión

- Al iniciar, el sistema solicita identificador y contraseña.
- El usuario por defecto con privilegios de administrador es:
  ```makefile
      Usuario: Admin
      Contraseña: 0000

### Funcionalidades principales

Desde el menú principal podrás:

1. Dar de alta doctores (ID, nombre, especialidad).

2. Dar de alta pacientes (ID, nombre).

3. Crear citas médicas (fecha, hora, motivo, doctor, paciente).

4. Visualizar listados de doctores, pacientes, citas o usuarios.

5. Eliminar doctores, pacientes, citas o usuarios.

6. Guardar automáticamente toda la información en archivos CSV.

**Solo el usuario Admin puede crear o eliminar usuarios del sistema.**

### Persistencia de datos

Los datos se almacenan en formato CSV dentro de la carpeta db/.
Si los archivos son eliminados, el sistema los regenerará al iniciar.

## Créditos

Desarrollado por:

David Huerta Vitela

Estudiante de Ingenieria Industrial y de Sistemas

Evidencia Final de Simulación de Sistemas de Información



Asesoría técnica:
Mtro. José Alfredo Jiménez Hernández

## Licencia

Este proyecto está licenciado bajo la MIT License.
Puedes usarlo, modificarlo y distribuirlo libremente siempre que se otorgue el crédito correspondiente al autor original.
    ```vbnet

    MIT License
    
    Copyright (c) 2025 David Huerta Vitela

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

## Nota final

Este sistema fue diseñado con fines académicos para demostrar la aplicación práctica de conceptos de programación orientada a objetos, manejo de archivos, autenticación, y gestión de datos persistentes en Java.
   
