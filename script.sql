create database School;
use School;

create table if not exists review
(
    id           int auto_increment
    primary key,
    create_date  date         null,
    username     varchar(255) null,
    subject_code varchar(255) null,
    content      text         null
    );

create table if not exists subject
(
    id   int auto_increment
    primary key,
    name varchar(255) charset utf8 null,
    code varchar(255)              null,
    constraint code
    unique (code)
    );

create table if not exists user
(
    id        int auto_increment
    primary key,
    username  varchar(255)              null,
    password  varchar(255)              null,
    full_name varchar(255) charset utf8 null,
    address   varchar(255) charset utf8 null,
    phone     varchar(255) charset utf8 null,
    role      int                       null comment '0: admin; 1: hoc-sinh',
    constraint user_username_uindex
    unique (username)
    );

create table if not exists feedback
(
    id          int auto_increment
    primary key,
    create_date date         null,
    username    varchar(255) null,
    content     text         null,
    constraint FK_USER
    foreign key (username) references user (username)
    );

