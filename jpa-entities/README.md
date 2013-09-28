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

Browse the final code in the repository to see how it looks like at the end. Classes
involved in this step are: `Person` and `BasicPersistenceTest`.

## Persisting a more complicated entity

For this step we will use a diferent entity, just to not hide the previous one. 
The entity is called `ContactablePerson` and it has the same information as 
`Person` plus a list of phone numbers you can call her.

These phone numbers are a different entity, and the goal in this step is to 
handle more complex entities and their relationship. In this case, this
relationship is a one-to-many (one person can have many numbers). The 
`@OneToMany` annotation denotes that relationship.

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;

We use `CascadeType.ALL` to tell Hibernate to propagate all operations (persist,
remove, update,...) over the entities of the relationship. If we don't do that,
we have to do all those operations manually, and in this cases, it's 
an annoyance. There might be situations where we want to do manually, but it's
not the case here.

## Understanding entity states and lifecycle

An entity can be in one of these states:

- It does not exist yet: not a state by itself, but it's the origin of everything else.
- New: it has just been instantiated using the new operator, it is not associated with a persistence context.
- Managed: it has a persistent identity that is currently associated with a persistence context.
- Detached: it has a persistent identity that is no longer associated with a persistence context, usually because the persistence context was closed or the instance was evicted from the context.
- Removed: it has a persistent identity, associated with a persistence context, but scheduled for removal from the database.

Here is a diagram, by Oracle documentation, where one can understand in a glimpse
an entity lifecycle. 

![JPA entity lifecycle](http://docs.oracle.com/cd/E16439_01/doc.1013/e13981/img/lifeent30.gif)

In the following steps we will learn how to *move* an entity from one state to another.

### From new to managed

Using the `persist` method, quite easy:

    @Test
    public void testFromNewToManaged() {
        ContactablePerson p = createContactablePerson();

        em.persist(p);
        assertTrue("entity's state is 'managed'", em.contains(p));
    }

### From managed to detached

There are two ways of detaching an entity:

1. Using `detach` method:

    @Test
    public void testFromManagedToDetachedUsingDetachMethod() {
        ContactablePerson p = createContactablePerson();
        em.persist(p);

        em.detach(p);
        assertFalse("entity is not in persistence context", em.contains(p));
    }

2. Closing the entity manager

    @Test
    public void testFromManagedToDetachedClosingEntityManager() {
        ContactablePerson p = createContactablePerson();
        em.persist(p);

        tx.commit();
        em.close();
        
        try {
            em.contains(p);
            fail("em should be closed, and the entity shouldn't be managed by him");
        } catch (IllegalStateException e) { }
    }

### From detached to managed

### From managed to removed

    @Test
    public void testFromManagedToRemoved() {
        ContactablePerson p = createContactablePerson();
        em.persist(p);

        em.remove(p);
        assertFalse("entity has been removed and it is not managed", em.contains(p));
    }

### From removed to managed

Although this transition is not documented in the Oracle diagram above, it is possible
to move an entity to managed once it has been removed. It is up to you to decide
if this transition makes sense or it is a waste of time.



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
