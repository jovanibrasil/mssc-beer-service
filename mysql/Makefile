stop:
	- docker stop mysql-database
clean: stop
	- docker rm mysql-database
build:
	docker build -t mysql-database .
run: clean
	docker run -p 3306:3306 -p 33060:33060 --name=mysql-database --network net -d mysql-database
start:
	docker start mysql-database 
bash:
	docker container exec -i -t mysql-database bash

# mysql -u root -p