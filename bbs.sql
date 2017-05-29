/* ユーザーテーブル*/
create table users
(id integer primary key not null auto_increment,
login_id varchar(20) not null,
password varchar(255) not null,
name varchar(10) not null,
branch_id integer not null,
department_id integer not null,
insert_date timestamp not null,
update_date timestamp not null,
is_locked boolean not null;

/* 支店テーブル*/
create table branches(
id integer primary key not null auto_increment,
name varchar(20) not null
);

/*部署テーブル*/
create table departments(
id integer primary key not null auto_increment,
name varchar(20) not null
);

/*投稿テーブル*/
create table messages(
id integer primary key not null auto_increment,
title varchar(50) not null,
text text not null,
category varchar(10) not null,
user_id integer not null,
insert_date timestamp not null
);

/* コメントテーブル*/
create table comments(
id integer primary key not null auto_increment,
text text not null,
user_id integer not null,
message_id integer not null
insert_date timestamp not null
);

/*メッセージを表示するためのビュー*/
create view user_message as
select
	messages.id as user_id
	, users.id as message_id
	, title
	, messages.text
	, category
	, name
	, messages.insert_date
from
	users, messages
where
	users.id = messages.user_id;
	
/*コメントを表示するためのビュー*/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `user_message` AS
    select 
        `messages`.`id` AS `id`,
        `messages`.`user_id` AS `user_id`,
        `messages`.`title` AS `title`,
        `messages`.`text` AS `text`,
        `messages`.`category` AS `category`,
        `users`.`name` AS `name`,
		`users`.`branch_id` AS `branch_id`,
		`users`.`department_id` AS `department_id`,
        `messages`.`insert_date` AS `insert_date`
    from
        (`users`
        join `messages`)
    where
        (`users`.`id` = `messages`.`user_id`)