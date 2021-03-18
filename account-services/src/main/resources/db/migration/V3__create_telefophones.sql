create table telephones (
    id binary(16) primary key,
    customer_id binary,
    number varchar(11)
);

alter table telephones add constraint fk_telephones_customer foreign key (customer_id) references customer(id);


