create table customer
(
    id         varchar(36) primary key,
    name       varchar(80) not null,
    document   varchar(11) not null,
    birthday   date        not null,
    address_id varchar(36) not null
);

alter table customer
    add constraint fk_customer_address foreign key (address_id) references address (id);


