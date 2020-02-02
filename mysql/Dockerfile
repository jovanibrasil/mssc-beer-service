FROM mysql:5.7
LABEL maintainer="jovanibrasil@gmail.com"

ENV MYSQL_ROOT_USER=root
ENV MYSQL_ROOT_PASSWORD=root
#ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes

# All scripts in docker-entrypoint-initdb.d/ are automatically executed 
# during container startup when the database folder is empty.
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/

# Update authentication plugin
#RUN mysqld --default-authentication-plugin=mysql_native_password
