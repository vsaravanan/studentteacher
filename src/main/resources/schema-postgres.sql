
create table student
(
    id bigserial not null ,
    createdBy varchar(50),
    createdTm timestamp,
    updatedBy varchar(50),
    updatedTm timestamp,
    name      varchar(100) not null,
    email     varchar(100) not null,
    constraint pkStudentId  primary key (id)
);

create table teacher
(
    id bigserial not null ,
    createdBy varchar(50),
    createdTm timestamp,
    updatedBy varchar(50),
    updatedTm timestamp,
    name      varchar(100) not null,
    email     varchar(100) not null,
    constraint pkTeacherId  primary key (id)
);


create table globalLog (
    id          bigserial not null ,
    message     varchar(1024),
    warning     varchar(1024),
    path        varchar(1024),
    input       varchar(1024),
	createdBy   varchar(50),
    createdTm   timestamp,
    description varchar(12000),
    constraint pkGlobalLog primary key (id)
);

create table studentteacher (
	studentId bigint not null,
	teacherId bigint not null,
	    createdBy varchar(50),
	    createdTm timestamp,
	    updatedBy varchar(50),
	    updatedTm timestamp,
	constraint pkStudentTeacher primary key (studentId, teacherId),
	constraint fkStudent foreign key (studentId) references student (id),
	constraint fkTeacher foreign key (teacherId) references teacher (id)
);