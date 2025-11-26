# Guía de Despliegue y Ejecución

## 1. Ejecución Local

### Prerrequisitos
- Tener **Java 17** instalado.
- Tener **Maven** instalado.
- Tener **PostgreSQL** instalado y corriendo.

### Configuración de Base de Datos
1. Abre pgAdmin o tu terminal de PostgreSQL.
2. Crea una base de datos llamada `logistica`:
   ```sql
   CREATE DATABASE logistica;
   ```
3. Asegúrate de que las credenciales en `src/main/resources/application.properties` coincidan con las tuyas (usuario `postgres`, contraseña `171297` según tu configuración).

### Correr la Aplicación
1. Abre una terminal en la carpeta del proyecto.
2. Ejecuta el comando:
   ```bash
   mvn spring-boot:run
   ```
3. Accede a `http://localhost:8080`.

---

## 2. Subir a GitHub

1. Crea un nuevo repositorio vacío en GitHub (sin README, sin .gitignore).
2. En la terminal de tu proyecto, inicializa Git:
   ```bash
   git init
   ```
3. Agrega todos los archivos:
   ```bash
   git add .
   ```
4. Haz el primer commit:
   ```bash
   git commit -m "Versión inicial del proyecto Logística"
   ```
5. Vincula tu repositorio remoto (reemplaza `URL_DE_TU_REPO` con el link de GitHub):
   ```bash
   git branch -M main
   git remote add origin URL_DE_TU_REPO
   git push -u origin main
   ```

---

## 3. Despliegue en la Nube (Ejemplo: Railway / Render / Azure)

Para desplegar, necesitas una base de datos en la nube.

### Opción Recomendada: Railway.app (Más fácil)
1. Crea una cuenta en [Railway](https://railway.app/).
2. Crea un "New Project" > "Provision PostgreSQL".
3. Obtén las variables de entorno de la base de datos (Host, Database, User, Password, Port).
4. Conecta tu repositorio de GitHub a Railway.
5. En la configuración del servicio de Spring Boot en Railway, agrega las variables de entorno para reemplazar las locales:
   - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://<HOST>:<PORT>/<DATABASE>`
   - `SPRING_DATASOURCE_USERNAME`: `<USER>`
   - `SPRING_DATASOURCE_PASSWORD`: `<PASSWORD>`
6. Railway detectará el archivo `pom.xml` y desplegará automáticamente.

### Opción: Azure App Service
1. Crea una "Database for PostgreSQL" en Azure.
2. Crea un "Web App" (Java 17, Tomcat o Java SE).
3. Configura las variables de entorno ("Application Settings") en la Web App con los datos de tu BD de Azure.
4. Puedes desplegar conectando tu GitHub (Deployment Center) o usando el plugin de Maven para Azure.
