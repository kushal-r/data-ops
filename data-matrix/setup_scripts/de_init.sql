CREATE USER 'neo'@'localhost' IDENTIFIED BY 'trinity@123';

GRANT ALL PRIVILEGES ON *.* TO 'neo'@'localhost' WITH GRANT OPTION;

CREATE USER 'neo'@'%' IDENTIFIED BY 'trinity@123';

GRANT ALL PRIVILEGES ON *.* TO 'neo'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;

create database data_matrix;


