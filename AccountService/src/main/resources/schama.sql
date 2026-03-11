CREATE TABLE if not exists Customer(
    customer_id int AUTO_INCREMENT primary Key,
    customer_name varchar2(30) not null,
    email varchar2(50) not null,
    mobile_number varchar2(13) not null,
    created_at date not null,
    created_by varchar2(20) not null,
    updated_at date default null,
    updated_by varchar2(20) default null
);

CREATE TABLE if not exists Account(
    customer_id int not null,
    account_number int not null  primary Key,
    account_type varchar2(50) not null,
    branch_address varchar2(13) not null,
    created_at date not null,
    created_by varchar2(20) not null,
    updated_at date default null,
    updated_by varchar2(20) default null,
    foreign key (customer_id) references Customer(customer_id)
 );