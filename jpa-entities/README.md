# Understanding JPA entities lifecycle

In this demo, we will understand the JPA entities lifecycle, and we will
learn how to move an entity from one state to another using JPA's 
EntityManager methods.

# Instructions

## Starting from an existing project

Some time ago, I wrote a little tutorial about 
[using JPA, Hibernate and Derby](http://rchavarria.github.io/blog/2011/05/19/uso-de-jpa-hibernate-y-derby).
Nowadays, this tutorial is a bit outdated, but it still works. For this demo,
we will start with the basic information provided by that tutorial.

This information is basically composed of two files:

- `pom.xml`: it defines the maven project. It contains the project dependencies: 
hibernate-entitymanager, derby and junit. Their versions will be updated, but
they are still valid frameworks to develop our demo.
- `persistence.xml`: it configures a persistence context. It will basically the
same as in the old tutorial, and there is little to talk about him.

Our persistence context, basically, is configured as: Hibernate as the implementation
of JPA, Apache Derby as the database, the database will be created every time
the application starts, and it will store data in memory, not in hard disc.

## Testing the persistence of a basic entity

We will start with a very simple entity. A `Person` with a name, and an age (and
an identifier, of course). 

Here, we will use JPA annotations to configure our class as a JPA entity, its
primary key. This is how the Hibernate implemetation of JPA will store the entity
in the database.

    import javax.persistence.*;

    @Entity
    public class Person {

        @Id @GeneratedValue
        private long id;
        private String name;
        private int age;

        // getters and setters
    }

Take a look to the first version of our test. All necessary code is condensed in
one method and there is not exception handling. Those are bad practices, 
but we will use it first, and then we will refactor our test:

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-jpa");
        EntityManager em = emf.createEntityManager();
        
        Person p = new Person();
        p.setName("Foo bar");
        p.setAge(19);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();

        em.close();
        emf.close();
    }

Browse the final code in the repository to see how it looks like at the end.

2. testing persistence of a basic entity
3. persistence of a little more complicated entity
4. entity states and lifecycle

# Run

This demo run as JUnit tests, so one can see the results by running the maven
command `mvn test`. 

# Further reading

- [Usar JPA, Hibernate y Derby](http://rchavarria.github.io/blog/2011/05/19/uso-de-jpa-hibernate-y-derby):
a blog post of my own describing the basics about using JPA implemented by Hibernate 
and Apache Derby as database.
- [An excellent JPA tutorial](http://www.davidmarco.es/blog/entrada.php?id=144): 
one of the best JPA tutorial out there, in Spanish.
- [Hibernate EntityManager](http://docs.jboss.org/hibernate/core/4.0/hem/en-US/html_single): 
Hibernate implementation of JPA. 
- [What is a JPA entity?](http://docs.oracle.com/cd/E16439_01/doc.1013/e13981/undejbs003.htm): 
Oracle documentation about JPA entities. 
- [Working with JPA entities objects](http://www.objectdb.com/java/jpa/persistence/managed): 
more documentation about JPA entities.
