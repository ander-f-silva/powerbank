create table address (
    id varchar(36) primary key,
    customer_id varchar(36),
    public_place varchar(80) not null,
    complement varchar(20) not null,
    low varchar(80) not null,
    city varchar(45) not null,
    state varchar(2) not null
);

alter table address add constraint fk_address_customer foreign key (customer_id) references customer(id);


