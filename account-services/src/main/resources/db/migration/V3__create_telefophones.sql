create table telephones
(
    id          varchar(36) primary key,
    customer_id varchar(36),
    number      varchar(11)
);

alter table telephones
    add constraint fk_telephones_customer foreign key (customer_id) references customer (id);


