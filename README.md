# JAVA SPRINGFRAMEWORK API REST by João Pivatto

 In this repository, you will find an example of a REST API created using Java SpringBoot Framework with SpringSecurity. Besides, the database is also included in the repository. All users created have the passwork encrypted, but if you want to have access, the password is "1234".

 ## Class Diagram

```mermaid
classDiagram
    class Person {
        int id
        String name
        String gender
        Date birth_date
    }

    class Order {
        int id
        int person_id
    }

    class OrderItem {
        int id
        int order_id
        int product_id
        int quantity
    }

    class Product {
        int id
        String name
        String description
        double price
    }

    class User {
        int id
        String login
        String password
        String name
    }

    class Role {
        int id
        String authority
    }

    class UserAuthorities {
        int user_id
        int authorities_id
    }

    %% Relações
    Person  -->  Order : places
    Order  -->  OrderItem : contains
    Product -->  OrderItem : referenced in
    User -->  UserAuthorities : has
    Role  --> UserAuthorities : assigned to

```
