-- удаление всех таблиц
DROP TABLE workouts;
DROP TABLE inventory;
DROP TABLE models;
DROP TABLE producers;


# create table destinations (
#     id int serial default value,
#     destination_name varchar(20) not null,
#     primary key (id)
# );


create table producers (
    id int serial default value,
    name varchar(20),
    primary key (id)
);

create table models (
    id int serial default value,
    name varchar(20),
    limitation int,
    description text,
    producer_id int not null,
    primary key (id),
    foreign key (producer_id) references producers(id)
);


create table inventory (
    id int serial default value,
    pair_number int,
    note text,
    model_id int not null,
    account_id int not null,
    primary key(id),
    foreign key (model_id) references models(id),
    foreign key (account_id) references accounts(id)
);

create table  workouts (
    id int serial default value,
    workout_day date default '2022-03-12',
    distance_in_metres int,
    time_in_seconds int,
    health int(1) default 3,
    account_id int not null,
    inventory_id int,
    primary key (id),
    foreign key (inventory_id) references inventory(id)
);

-- заполенние таблицы производителей инвентаря
insert into producers (name) values ('New Balance');
insert into producers (name) values ('Saucony');
insert into producers (name) values ('Hoka');
insert into producers (name) values ('Nike');


-- заполение таблицы моделей инвентаря
insert into models(name, limitation, description, producer_id)
VALUES ('NB 1080', 600, 'для тренировок', 1);
insert into models(name, limitation, description, producer_id)
VALUES ('Saucony trail', 670, 'для трейла', 2);
insert into models(name, limitation, description, producer_id)
VALUES ('Hoka S4', 900, 'для быстрого бега', 3);
insert into models(name, limitation, description, producer_id)
VALUES ('Nike One', 400, 'для соревнований', 4);
insert into models(name, limitation, description, producer_id)
VALUES ('NB 890', 800, 'для тренировок', 1);

-- заполнение таблицы используемого инвентаря
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 1, 4);
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 2, 4);
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 3, 5);
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 4, 5);
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 5, 4);
insert into inventory (pair_number, note, model_id, account_id)
VALUES (1, 'бегаю каждый день', 5, 5);

-- заполнение таблицы тренировок
insert into workouts (distance_in_metres, time_in_seconds, health, account_id, inventory_id)
VALUES (15000, 3600, 3, 4, 1);
insert into workouts (distance_in_metres, time_in_seconds, health, account_id, inventory_id)
VALUES (20000, 4500, 2, 4, 2);
insert into workouts (distance_in_metres, time_in_seconds, health, account_id, inventory_id)
VALUES (10000, 2400, 1, 5, 3);
insert into workouts (distance_in_metres, time_in_seconds, health, account_id, inventory_id)
VALUES (12000, 3600, 4, 5, 4);

select * from inventory as i where account_id = (select id from accounts where email = 'slava@mail.ru');

