
create table student
(
    id bigint not null auto_increment,
    createdBy varchar(50),
    createdTm datetime(6),
    updatedBy varchar(50),
    updatedTm datetime(6),
    name      varchar(100) not null,
    email     varchar(100) not null,
    constraint primary key pkStudentId (id)
);

create table teacher
(
    id bigint not null auto_increment,
    createdBy varchar(50),
    createdTm datetime(6),
    updatedBy varchar(50),
    updatedTm datetime(6),
    name      varchar(100) not null,
    email     varchar(100) not null,
    constraint primary key pkTeacherId (id)
);


create table globalLog (
    id          bigint not null auto_increment,
    message     varchar(1024),
    warning     varchar(1024),
    path        varchar(1024),
    input       varchar(1024),
	createdBy   varchar(50),
    createdTm   datetime,
    description varchar(12000),
    constraint pkGlobalLog primary key (id)
);

create table studentteacher (
	studentId bigint not null,
	teacherId bigint not null,
	    createdBy varchar(50),
        createdTm datetime(6),
        updatedBy varchar(50),
        updatedTm datetime(6),
	constraint pkStudentTeacher primary key (studentId, teacherId),
	constraint fkStudent foreign key (studentId) references student (id),
	constraint fkTeacher foreign key (teacherId) references teacher (id)
);