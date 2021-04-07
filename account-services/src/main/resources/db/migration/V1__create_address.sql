create table address
(
    id           varchar(36) primary key,
    public_place varchar(80) not null,
    complement   varchar(20) not null,
    low          varchar(80) not null,
    city         varchar(45) not null,
    state        varchar(2)  not null
);

