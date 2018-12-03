# HAZELCAST
Hazelcast is an open source in-memory data grid that provides a **shared nothing** architecture that shares and distributes data across a cluster of servers.
Hazelcast provides multiple ways for you use IMDG, depending on your deployment strategies, security aspects, or usage patterns. You can use Hazelcast as a client-server or an embedded architecture.
![Hazelcast Embedded](/figures/figure1.png?raw=true)
#### TOPOLOGY
![Hazelcast Topology](/figures/figure2.png?raw=true)
##### Shared Nothing Architecture:
Hazelcast clusters do not have a master member. Each cluster member is configured to be the same in terms of functionality. This peer-to-peer network relationship with connections to all members is optimal for speed.

The information provided in this section was derived from the source(s) below:
https://hazelcast.com/use-cases/imdg/

## SPECS

### SPEED
The images below capture the 2016 benchmark test results that compares the performance of clustered Redis (v.3.0.7) vs clustered Hazelcast (v.3.6.1).

Most up-to-date benchmark comparisons between a Redis 3.2.8 cluster and a Hazelcast IMDG 3.8 cluster can be found at the following link:

https://hazelcast.com/resources/benchmark-redis-vs-hazelcast/
(Graphs aren’t as easy to see and interpret as the ones shown from  Redis (v.3.0.7) vs Hazelcast (v.3.6.1)).

![Hazelcast Throughput](/figures/figure3.png?raw=true)
![Hazelcast Latency](/figures/figure4.png?raw=true)

### FAILOVER
Hazelcast shards are called Partitions. By default, Hazelcast has 271 partitions. The partitions themselves are distributed equally among the members of the cluster. Hazelcast also creates the backups of partitions and distributes them among members for redundancy.

Partitions are memory segments that can contain hundreds or thousands of data entries each, depending on the memory capacity of your system. Each Hazelcast partition can have multiple replicas, which are distributed among the cluster members. One of the replicas becomes the primary and other replicas are called backups. Cluster member which owns primary replica of a partition is called partition owner. When you read or write a particular data entry, you transparently talk to the owner of the partition that contains the data entry.

#### How is data partitioned?

Hazelcast distributes data entries into the partitions using a hashing algorithm. Given a object key or an object name:

we serialize (convert into a byte array), hash the byte array and mod the hash result with the number of partitions

The result of - mod(hash result, partition count) - is the partition in which the data will be stored.  This partition is considered the partition ID. For all members that are in the cluster, the partition ID for a given key will always be the same.

#### Partition table

When you start a member, a partition table is created within it. This table stores the partition IDs and the cluster member to which they belong to.

The oldest member (first member created in the cluster) periodically sends the partition table to all cluster members. In this way each member is informed about any changes to partition ownership.

For example, partition ownership would change if the oldest member dies. Subsequently, the second to oldest member would take over.

By default, Hazelcast clients will connect to all members and know about the cluster partition table to route requests to the correct node, preventing additional/unnecessary trips.

The information provided for this section was derived from the source(s) below:
(http://docs.hazelcast.org/docs/latest-development/manual/html/Hazelcast_Overview/Data_Partitioning.html)



### LOAD BALANCING
There are two situations where the balance between the number of threads performing operations and the number of operations being executed can become disproportionate:

Asynchronous calls: With async calls, the system may be flooded with new requests.

Asynchronous backups: The asynchronous backups may be piling up.

To prevent the system from crashing, Hazelcast provides back pressure. Back pressure works by:

- Limiting the number of concurrent operation invocations
- Periodically making an async backup sync

Memberside:

Back pressure is disabled by default, to enable it we need to use the following system property:

 _hazelcast.backpressure.enabled_

To control the number of concurrent invocations, you can configure the number of invocations allowed per partition using the following system property:

_hazelcast.backpressure.max.concurrent.invocations.per.partition_

Client Side:

To prevent the system at the client side from overloading, we can apply a constraint on the number of concurrent invocations. We can use the following system property at the client side for this purpose:

_Hazelcast.client.max.concurrent.invocations_

The information provided for this section was derived from the source(s) below:
(http://docs.hazelcast.org/docs/latest-development/manual/html/Performance/Back_Pressure.html)

### MULTITHREADED
Hazelcast uses highly optimized, multithreaded clients and servers, and uses asynchronous I/O, meaning that each thread owns its partitions, so there is no contention.

On each cluster member, the I/O threading is split up in 3 types of I/O threads:

- I/O thread for the accept requests
- I/O thread to read data from the other members/clients
- I/O thread to write data to other members/clients

Hazelcast periodically scans utilization of each I/O thread and can decide to migrate a connection to a new thread if the existing thread is servicing a disproportionate number of I/O events.  

The scanning interval is customizable through configuring the hazelcast.io.balancer.interval.seconds system property. (default interval is 20 seconds)

The information provided in this section was derived from the source(s) below:
(http://docs.hazelcast.org/docs/latest-development/manual/html/Performance/Threading_Model/I:O_Threading.html)
(https://hazelcast.com/resources/benchmark-redis-vs-hazelcast/)

## WILL IT WORK?

#### Hazelcast is Simple

Hazelcast is written in Java with no other dependencies. It exposes the same API from the familiar Java util package, exposing the same interfaces. Just add hazelcast.jar to your classpath and you can quickly enjoy JVMs clustering and start building scalable applications.

#### Hazelcast is Peer-to-Peer

Unlike many NoSQL solutions, Hazelcast is peer-to-peer. There is no master and slave; there is no single point of failure. All members store equal amounts of data and do equal amounts of processing. You can embed Hazelcast in your existing application or use it in client and server mode where your application is a client to Hazelcast members.

#### Hazelcast is Scalable

Hazelcast is designed to scale up to hundreds and thousands of members. Simply add new members and they will automatically discover the cluster and will linearly increase both memory and processing capacity. The members maintain a TCP connection between each other and all communication is performed through this layer.

#### Hazelcast is Fast

Hazelcast stores everything in-memory. It is designed to perform very fast reads and updates.

#### Hazelcast is Redundant

Hazelcast keeps the backup of each data entry on multiple members. On a member failure, the data is restored from the backup and the cluster will continue to operate without downtime.

Hazelcast's Distinctive Strengths:
Hazelcast is open source.

Hazelcast is only a JAR file. You do not need to install software.

Hazelcast is a library, it does not impose an architecture on Hazelcast users.

Hazelcast provides out-of-the-box distributed data structures, such as Map, Queue, MultiMap, Topic, Lock and Executor.

There is no "master," meaning no single point of failure in a Hazelcast cluster; each member in the cluster is configured to be functionally the same.

When the size of your memory and compute requirements increase, new members can be dynamically joined to the Hazelcast cluster to scale elastically.

Data is resilient to member failure. Data backups are distributed across the cluster. This is a big benefit when a member in the cluster crashes as data will not be lost.

Members are always aware of each other unlike in traditional key-value caching solutions.

You can build your own custom-distributed data structures using the Service Programming Interface (SPI) if you are not happy with the data structures provided.


The information provided for this section pertains to the source(s) below:
(http://docs.hazelcast.org/docs/latest/manual/html-single/)
(http://docs.hazelcast.org/docs/latest-development/manual/html/Hazelcast_Overview/Why_Hazelcast.html)
