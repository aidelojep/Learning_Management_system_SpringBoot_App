set foreign_key_checks =0;

truncate table learning_party;
truncate table authority;
truncate table instructor;

insert into learning_party(`id`,`email`,`password`,`enabled`)
values(123, 'peter123@yahoo.com', 'march20', false),
      (124, 'peter124@yahoo.com', 'march21', false),
      (125, 'peter125@yahoo.com', 'march22', false),
      (126, 'peter126@yahoo.com', 'march23', false),
      (127, 'peter127@yahoo.com', 'march24', false);

set foreign_key_checks =1;