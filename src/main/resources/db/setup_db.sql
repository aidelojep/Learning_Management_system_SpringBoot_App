create if not exists database learning_management_systemdb1;

create user if not exists 'school_user'@'localhost' identified by 'password1';
grant all privileges on learning_management_systemdb1. * to 'school_user'@'localhost';
flush privileges;