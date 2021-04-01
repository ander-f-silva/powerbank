create table account (
    id varchar(36) primary key,
    customer_id varchar(36),
    amount decimal(10,2),
    amount_limit decimal(8,2)
);

alter table account add constraint fk_account_customer foreign key (customer_id) references customer(id);


