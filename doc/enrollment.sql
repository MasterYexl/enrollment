create database if not exists enrollment;
use enrollment;
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
    teachers       varchar(255) not null	#该研究方向的导师集合， 以空格分开
);

create table message
(
    mid          int auto_increment
        primary key,
    from_email   varchar(32)  not null,
    to_email     varchar(32)  not null,
    message      varchar(255) not null,
    message_time datetime     not null
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
    resume          tinyint default 0 null,	#是否上传的简历，简历名为sid
    certificate      int     default 0 null,	#是否上传证书，值为证书数量，证书名为sid+1-值
    grade            varchar(255)      null,	#成绩空格分隔
    second_direction int               null,	#第二志愿对应did
    first_direction  int               null,	#第一志愿对应did
    process         int     default 0 null,	#录取进程，其值 0：第一志愿导师审核；1：第二志愿导师审核；2：未录取
    constraint student_information_student_sid_fk
        foreign key (sid) references student (sid)
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
    display           tinyint default 0 not null,	#展示状态，由管理员审核修改 0：未审核 1：审核通过
    constraint tutor_information_tutor_tid_fk
        foreign key (tid) references tutor (tid)
);

