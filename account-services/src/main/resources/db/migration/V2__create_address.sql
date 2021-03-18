create table address (
    id binary(16) primary key,
    customer_id binary,
    public_place varchar(80) not null,
    complement varchar(20) not null,
    low varchar(80) not null,
    city varchar(45) not null,
    state varchar(2) not null
);

alter table address add constraint fk_address_customer foreign key (customer_id) references customer(id);


