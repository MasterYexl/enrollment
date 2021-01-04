create table admin
(
    aid      int         not null
        primary key,
    password varchar(20) not null,
    name     varchar(20) not null,
    role     int         not null
);

create table direction
(
    did            int auto_increment
        primary key,
    direction_name varchar(30)  not null,
    teachers       varchar(255) not null
);

create table message
(
    mid          int auto_increment
        primary key,
    from_email   varchar(32)          not null,
    to_email     varchar(32)          not null,
    message      varchar(255)         not null,
    message_time datetime             not null,
    is_read      tinyint(1) default 0 not null
);

create table student
(
    sid          int auto_increment
        primary key,
    name         varchar(20) not null,
    password     varchar(20) not null,
    universe     varchar(30) not null,
    specialities varchar(30) not null,
    email        varchar(32) null
);

create table student_information
(
    sid              int               not null,
    resume           tinyint default 0 null,
    certificate      int     default 0 null,
    grade            varchar(255)      null,
    second_direction int               null,
    first_direction  int               null,
    process          int     default 0 null,
    constraint student_information_student_sid_fk
        foreign key (sid) references student (sid)
);

create table task
(
    task_id    int auto_increment
        primary key,
    owner      varchar(32)          not null,
    task_class int                  null,
    name       varchar(16)          not null,
    `describe` varchar(255)         not null,
    complete   tinyint(1) default 0 not null
);

create table tutor
(
    tid      int auto_increment
        primary key,
    name     varchar(20) null,
    password varchar(20) not null,
    email    varchar(32) null
);

create table enrollment
(
    sid int not null,
    tid int not null,
    did int not null,
    constraint did___fk
        foreign key (did) references direction (did),
    constraint sid___fk
        foreign key (sid) references student (sid),
    constraint tid___fk
        foreign key (tid) references tutor (tid)
);

create table table_name
(
    tutor_info_id     int auto_increment
        primary key,
    tid               int           not null,
    direction         varchar(32)   not null,
    enrollment_number int           not null,
    scholarship       int           not null,
    tel               varchar(11)   null,
    email             varchar(32)   null,
    `require`         varchar(255)  not null,
    display           int default 0 null,
    constraint table_name_tutor_tid_fk
        foreign key (tid) references tutor (tid)
);

create table tutor_information
(
    tutor_info_id     int auto_increment
        primary key,
    tid               int               null,
    direction         varchar(32)       not null,
    enrollment_number int               null,
    tel               varchar(11)       null,
    email             varchar(32)       null,
    scholarship       int               null,
    tutor_require     varchar(255)      not null,
    display           tinyint default 0 not null,
    constraint tutor_information_tutor_tid_fk
        foreign key (tid) references tutor (tid)
);

