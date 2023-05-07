

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
```