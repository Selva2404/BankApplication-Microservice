create table if not exist card(
    card_Number int not null primary Key,
    mobile_number varcar(12) not null,
    card_Type varcar(30) not null,
    card_Limit varcar(10) not null,
    bill_Amount varcar(10) not null,
    outstanding_Amount varcar(10) not null,
    created_at date not null,
    created_by varchar2(20) not null,
    updated_at date default null,
    updated_by varchar2(20) default null
)