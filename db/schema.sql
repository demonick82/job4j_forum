create table posts (
                       id serial primary key,
                       name varchar(2000),
                       description text,
                       time timestamp without time zone not null default now()
);