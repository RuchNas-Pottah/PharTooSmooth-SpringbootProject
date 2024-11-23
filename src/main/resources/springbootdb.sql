create database springbootdb;
use springbootdb;

  create table Users
(
  userEmail VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  role      VARCHAR(255) NOT NULL,
  PRIMARY KEY (userEmail)
);
INSERT INTO Users VALUES('admin@admin.com','password','admin');

create table customers(
id int primary key auto_increment,
customer_email varchar(255) not null,
f_name varchar(100),
l_name varchar(100),
phone_no varchar(255) not null,
dob date,
address varchar(150)
);
-- alter table customers add customer_email varchar(255) not null;
alter table customers modify column f_name varchar(100) not null;
alter table customers modify column l_name varchar(100) not null;
alter table customers modify column dob date not null;
alter table customers modify column address varchar(150) not null;

create table categories(
cat_id int primary key auto_increment,
cat_name varchar(100)
);

create table medication_stock(
id int primary key auto_increment,
name varchar(100),
stock_quantity int,
price int,
dosage_strength int,
category_id int
);

create table prescriptions(
id int primary key auto_increment,
customer_id int,
doctor_id int,
date_of_prescription date,
status bool default false,
prescription_image longblob
);

create table prescribed_medicines(
id int primary key auto_increment,
prescription_id int,
medication_id int,
quantity int
);

create table orders(
id int primary key auto_increment,
customer_id int,
order_date date,
prescription_id int,
total_amount int,
address varchar(150)
);

alter table medication_stock
add constraint fk_medication_categories
foreign key (category_id) references categories (cat_id);

alter table prescriptions
add constraint fk_prescriptions_customers
foreign key (customer_id) references customers (id);

alter table prescribed_medicines
add constraint fk_prescribedMedicines_prescriptions
foreign key (prescription_id) references prescriptions (id);

alter table prescribed_medicines
add constraint fk_prescribedMedicines_medication
foreign key (medication_id) references medication_stock (id);

alter table orders
add constraint fk_orders_customers
foreign key (customer_id) references customers (id);

alter table orders
add constraint fk_orders_prescriptions
foreign key (prescription_id) references prescriptions (id);

show tables;

INSERT INTO categories (cat_name) VALUES
    ('Antipyretics'),
    ('Analgesics'),
    ('Antibiotics'),
    ('Antihistamines'),
    ('Antiemetics'),
    ('Antifungals'),
    ('Anticoagulants'),
    ('Antacids'),
    ('Antivirals'),
    ('Antiemetics');

-- Category: Antipyretics 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('Fever-X', 100, 10, 250, 1),
    ('ThermoRelief', 80, 12, 200, 1),
    ('PyroStop', 120, 9, 180, 1),
    ('Cool-Fever', 90, 11, 210, 1),
    ('PainEaze', 70, 8, 240, 1);

-- Category: Analgesics 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('PainAway', 60, 15, 100, 2),
    ('RelaxNRelief', 85, 18, 120, 2),
    ('PainZap', 75, 14, 110, 2),
    ('EasiPain', 70, 16, 90, 2),
    ('AnalgesiX', 50, 12, 80, 2);

-- Category: Antibiotics 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('BactiGuard', 150, 25, 75, 3),
    ('AntibioCure', 120, 22, 80, 3),
    ('MicroShield', 180, 27, 100, 3),
    ('BioDefender', 140, 23, 90, 3),
    ('AntiBac', 160, 26, 110, 3);

-- Category: Antihistamines 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('AllerFix', 90, 15, 60, 4),
    ('HistamineGuard', 75, 18, 70, 4),
    ('AllerRelief', 110, 12, 50, 4),
    ('HistAnt', 80, 14, 55, 4),
    ('SinusEze', 95, 16, 65, 4);

-- Category: Antiemetics 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('NauseaBlock', 70, 20, 75, 5),
    ('EmeStop', 100, 15, 80, 5),
    ('AntiVomit', 85, 18, 90, 5),
    ('QueaseEase', 90, 14, 70, 5),
    ('EmeShield', 65, 16, 65, 5);

-- Category: Antifungals 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('FungOff', 120, 25, 100, 6),
    ('AntiFungiX', 95, 20, 80, 6),
    ('FungAway', 110, 22, 90, 6),
    ('ClearFungus', 105, 18, 75, 6),
    ('FungStopper', 130, 26, 110, 6);

-- Category: Anticoagulants 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('ClotGuard', 80, 15, 75, 7),
    ('CoagulaseX', 105, 18, 80, 7),
    ('ClotAway', 95, 14, 70, 7),
    ('AntiClot', 100, 16, 90, 7),
    ('ClotStop', 75, 12, 65, 7);

-- Category: Antacids 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('AcidEase', 110, 20, 80, 8),
    ('AntaShield', 95, 15, 75, 8),
    ('HeartburnRelief', 120, 18, 85, 8),
    ('AntaCure', 105, 14, 70, 8),
    ('GastricGuard', 100, 16, 90, 8);

-- Category: Antivirals 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('ViraStop', 80, 15, 60, 9),
    ('AntiVirusX', 95, 20, 70, 9),
    ('ViruShield', 90, 18, 80, 9),
    ('VirusBlocker', 105, 12, 55, 9),
    ('ImmunoGuard', 85, 14, 65, 9);

-- Category: Antiemetics 
INSERT INTO medication_stock (name, stock_quantity, price, dosage_strength, category_id)
VALUES
    ('NauseaBlock', 70, 20, 75, 10),
    ('EmeStop', 100, 15, 80, 10),
    ('AntiVomit', 85, 18, 90, 10),
    ('QueaseEase', 90, 14, 70, 10),
    ('EmeShield', 65, 16, 65, 10);
    
select * from categories;
select * from medication_stock;
select * from customers;
select * from orders;
select * from prescriptions;
select * from prescribed_medicines;

delete from categories;
delete from medication_stock;
delete from customers;
delete from orders;
delete from prescriptions;
delete from prescribed_medicines;

    
  
    