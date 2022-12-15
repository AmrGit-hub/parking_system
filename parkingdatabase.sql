create table spots(
	id int not null auto_increment primary key,
    `status` nvarchar(20) not null,
    `name` nvarchar(20) not null unique
)
create table ticket(
	id int not null key auto_increment ,
    platenumber nvarchar(20) not null unique,
    entrydate time not null,
    exitdate time DEFAULT NULL,
    spotid int not null references spots(id)

)
create table shifts(
	id int not null primary key,
    shift nvarchar(50) not null unique,
    `start` time not null,
    `end` time not null
)
create table roles(
	id int not null primary key,
    `role` nvarchar(20) not null unique
)
create table user(
	id int not null key auto_increment,
    username nvarchar(50) not null ,
    `password` nvarchar(100) not null,
    roleid int not null references roles(id),
    shiftid int not null references shifts(id)
)
create table admin(
	`name` nvarchar(50) not null unique,
    `password` nvarchar(50) not null unique
)