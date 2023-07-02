

```
mvn archetype:generate \
  -DgroupId=me.fani.michael \
  -DartifactId=sping-demo \
  -DarchetypeArtifactId=maven-archetype-quickstart
```

Liquibase example: https://github.com/Inkimar/liquibase-example

Create user in MySQL:

```sql
create database demo1;
CREATE USER 'demo1'@'localhost' IDENTIFIED BY '1111';
GRANT PRIVILEGE ON demo1.* TO 'demo1'@'localhost';
```

Run Liquibase migration:

```
mvn liquibase:update
```


```sql
INSERT INTO USER(USERNAME, PASSWORD, EMAIL)
VALUES
('John Doe', '1111', 'john@gmail.com'),
('Chris', '1111', 'chris@gmail.com')
;

INSERT INTO CATEGORY(ID, NAME)
VALUE
(1, 'Laptop');

INSERT INTO PRODUCT(ID, NAME, PRICE, CATEGORY_ID)
VALUES
(1, 'Thinkpad T14', 999.99, 1);

INSERT INTO PRODUCT(ID, NAME, PRICE, CATEGORY_ID)
VALUES
(2, 'Thinkpad E14', '899.99', 1);
```

```sql
insert into category (NAME) values ('Games',0);
insert into category (NAME) values ('Book',0);
insert into category (NAME) values ('Kino',0);
insert into category (NAME, parent_id) values ('Strategy', 1);
insert into category (NAME, parent_id) values ('RPG', 1);
insert into category (NAME, parent_id) values ('Syfy', 1);
insert into category (NAME, parent_id) values ('Triller', 3);
insert into category (NAME, parent_id) values ('Comedy', 3);
insert into category (NAME, parent_id) values ('Clasic', 2);
insert into category (NAME, parent_id) values ('Fantasy', 2);
insert into category (NAME, parent_id) values ('Hystory', 2);
insert into category (NAME, parent_id) values ('Novel', 2);
```

```sql
insert into product(NAME, PRICE, CATEGORY_ID) values ('Warhammer', 5.89, 4);
insert into product(NAME, PRICE, CATEGORY_ID) values ('WOW', 16.5, 5);
insert into product(NAME, PRICE, CATEGORY_ID) values ('King of bounty', 9, 4);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Warcraft III', 5.9, 4);
insert into product(NAME, PRICE, CATEGORY_ID) values ('General', 8.56, 4);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Syster act', 9.55, 8);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Jon yic', 5.39, 7);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Matrix', 5.89, 6);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Star wars', 8.95, 6);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Grand blue', 5.89, 6);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Master & Margarita', 5.55, 9);
insert into product(NAME, PRICE, CATEGORY_ID) values ('The wiqcher', 8.89, 10);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Overgreared', 16.89, 12);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Dark king', 15.3, 12);
insert into product(NAME, PRICE, CATEGORY_ID) values ('Zombie knight', 12.65, 12);
insert into product(NAME, PRICE, CATEGORY_ID) values ('1812', 4.58, 11);
```