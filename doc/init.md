

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