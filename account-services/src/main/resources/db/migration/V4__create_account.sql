create table account (
    id binary(16) primary key,
    customer_id binary,
    amount decimal(10,2),
    amount_limit decimal(8,2)
);

alter table account add constraint fk_account_customer foreign key (customer_id) references customer(id);


