# Redis Property
spring.data.redis.host=redis-16348.c114.us-east-1-4.ec2.redns.redis-cloud.com
spring.data.redis.port=16348
spring.data.redis.password=UcxmmlJKBes1VdJvbfIYSqbNxss7p3z6
# dependecy 
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>3.17.6</version>
        </dependency>

# What is Redis Used for?
We can use Redis in the following forms.

1) In-Memory Database: As an In-Memory database, We will get some empty memory to perform database operations. Moreover, it acts as No-SQL database and there are No Tables, No Sequences, No Joins concept. We can store data in the form of String, Hash Operations***, List, Set etc. In-built services will be available.

2) Cache: We can also use Redis as a Cache to increase our application performance.

3) Message Broker(MQ): Another use of Redis is as a Message Broker.

In real time application, Redis is popular for a Cache Manager as compared to database & message broker. As a cache manager, it reduces network calls and improves the performance of an application.

# What is Redis Cache?
Redis Cache is nothing but a Cache Management feature offered by Redis. Redis is normally used as a cache to store repeatedly accessed data in memory so that the user can feel the better performance of the application. The Redis Cache offers various features like how long you want to keep data, and which data to remove first, and some other bright caching models.

# What is Redis Database?
Redis Database is an in-memory database that persists on disk. It means when we use Redis Database, we occupy a memory on the disk to use as a Database. The data model is key-value, but many several kind of values are supported such as Strings, Lists, Sets, Sorted Sets, Hashes, Streams, HyperLogLogs, Bitmaps etc.

# What is Redis Server?
The full form of Redis is REmote DIctionary Server. When we use Redis in any form such as database, cache or Message Broker, we need to download a Redis Server in our system. People in the industry just call it Redis Server.
Generally, there are four important annotations that we apply to implement Redis Cache feature in our application. They are as below:

# @EnableCaching 
We apply this annotation at the main class (starter class) of our application in order to tell Spring Container that we need a caching feature in our application.

# @Cacheable 
@Cacheable is used to fetch (retrieve) data from the DB to the application and store in Redis Cache. We apply it on the methods that get (retrieve) data from DB. @Cacheable requires a return value of the method that adds or updates data in the cache.

The @Cacheable annotation offers us to use attributes. For example, we can provide a cache name by using the value or cacheNames attribute. We can also define the key attribute of the annotation that uniquely identifies each entry in the cache. If we do not specify the key, Spring utilizes its default mechanism to create the key. Moreover, we can also apply a condition in the annotation by using the condition attribute.

# @CachePut 
We use @CachePut in order to update data in the Redis Cache while there is any update of data in DB. We apply it on the methods that make modifications in DB.

# @CacheEvict 
We use @CacheEvict in order to remove the data in the Redis Cache while there is any removal of data in DB. We apply it on the methods that delete data from DB. It can be used with void methods.

# Details of Use-case 
I created a CRUD application using REST. Let’s assume an entity ‘Invoice’. For that our entity class is Invoice.java. In order to create a complete REST Application, we will have Controller, Service & Repository layers as per industry best practices. Once we complete developing the Invoice REST Application, we will further apply annotations on certain methods to get the benefits of Redis Cache. This is the step by step approach to implement the Redis Cache in our application. However, we are going to provide the complete code.

# When to Use @Caching Annotation?
Suppose we want to apply multiple caching annotations on a particular method, let’s try it with an example.

@CacheEvict("Invoice") 
@CacheEvict(value="Invoice", key="#invId")
public void deleteInvoice(Integer invId) {
    ....
}

The above code would have a compilation error, since Java does not allow multiple annotations of the same type to be declared for a given method. In this case we should use @Caching annotation. For example, below code demonstrates the concept.

@Caching(
  evict = {@CacheEvict("Invoice"), @CacheEvict(value="Invoice", key="#invId")
}) 
public void deleteInvoice(Integer invId) {
     ....
}
Similarly, if we have annotations of different type, we can group them using @Caching annotation for better readability. However, below code will not have any compilation error. For example,

@CacheEvict(value = "usersList", allEntries = true)
@CachePut(value = "user", key = "#user.getUserId()")
public User updateUser(User user) {
    ....
}
The above code can be converted as below:

@Caching(
   evict = {@CacheEvict(value = "usersList", allEntries = true)},
   put   = {@CachePut(value = "user", key = "#user.getUserId()")}
) 
public User updateUser(User user) {
   ....
}

# How to Implement Conditional Caching using Annotations?
If we have some requirement when we need to cache data only on a particular condition, we can parameterize our annotation with two parameters: ‘condition’ and ‘unless’. They accept a SpEL expression and ensures that the results are cached based on evaluating that expression. This kind of conditional caching can be useful for managing large amount of results. For example, let’s observe one example from each parameter.

Below Example demonstrates the concept of ‘condition’ parameter.

@CachePut(value="invoices", condition="#invoice.amount>=2496") 
public String getInvoice(Invoice invoice) {
    ...
}
Now, let’s see the concept of ‘unless’ parameter from the example below:

@CachePut(value="invoices", unless="#result.length()<24") 
public String getInvoice(Invoice invoice) {
    ...
}


