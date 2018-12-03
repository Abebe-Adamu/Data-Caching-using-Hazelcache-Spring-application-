# CASSANDRA

_Apache Cassandra is a key-value store and widely used in large-scale real-time internet applications, such as Netflix, Reddit, The Weather Channel._

## SPECS
### SPEED
Netflix had  a test run  designed to validate their tooling and automation scalability as well as the performance characteristics of Cassandra.The scalability was linear as shown in the chart below. Each client system generates about 17,500 write requests per second, without bottlenecks. Each client ran 200 threads to generate traffic across the cluster.Benchmarking Cassandra Scalability on AWS resulted Over a million writes per second.The automated tooling that Netflix has a Cassandra cluster consisting of 288 medium sized instances, with 96 instances in each of three EC2 availability zones in the US-East region. Using an additional 60 instances as clients they ran a workload of 1.1 million client writes per second. Data was automatically replicated across all three zones making a total of 3.3 million writes per second across the cluster.

![Cassandra Scalability](/figures/figure11.png?raw=true)

#### Per-Instance Activity
The  average activity level on each instance for each of these tests is shown below.

![Netflix Data](/figures/figure12.png?raw=true)

https://medium.com/netflix-techblog/benchmarking-cassandra-scalability-on-aws-over-a-million-writes-per-second-39f45f066c9e

### FAILOVER
Cassandra stores replicas on multiple nodes to ensure reliability and fault tolerance. A replication strategy determines the nodes where replicas are placed. The total number of replicas across the cluster is referred to as the replication factor. A replication factor of 1 means that there is only one copy of each row in the cluster. If the node containing the row goes down, the row cannot be retrieved. A replication factor of 2 means two copies of each row, where each copy is on a different node. All replicas are equally important; there is no primary or master replica. As a general rule, the replication factor should not exceed the number of nodes in the cluster. However, you can increase the replication factor and then add the desired number of nodes later.

Two replication strategies are available:

- SimpleStrategy: Use only for a single datacenter and one rack. If you ever intend more than one datacenter, use the NetworkTopologyStrategy.
- NetworkTopologyStrategy: Highly recommended for most deployments because it is much easier to expand to multiple datacenters when required by future expansion.
https://docs.datastax.com/en/cassandra/3.0/cassandra/architecture/archDataDistributeReplication.html

![Cassandra Replication](/figures/figure13.png?raw=true)

### LOAD BALANCING
Cassandra partitions data across all participating nodes in a database cluster. Each node is responsible for part of the overall database. When data is inserted ,it assigns a row key and based on this particular key cassandra assigns this row to one of the nodes in the cluster which is  responsible to manage it.

![Cassandra Partitioning](/figures/figure14.png?raw=true)

There are two basic partitioning strategies:
1. Random partitioning: this is the default and recommended strategy. partitioning data as evenly as possible across all nodes using an MD5 hash of every column family row key. This one ensures even distribution of data throughout all the nodes in the cluster.
1. Ordered partitioning: stores column family row keys in sorted order across the nodes in a database cluster. This is not  a recommended strategy, because a number of different issues can arise such as having hotspots in cluster , and not evenly distributed load balance.
https://www.datastax.com/resources/tutorials/partitioning-and-replication

### MULTITHREADED
Cassandra is designed for high concurrency so it is multithreaded.Cassandra is based off of a Staged Event Driven Architecture (SEDA).  This separates different tasks in stages that are connected by a messaging service.  Each like task is grouped into a stage having a queue and thread pool (ScheduledThreadPoolExecutor more specifically for the Java folks).  Some stages skip the messaging service and queue tasks immediately on a different stage if it exists on the same node.  Each of these queues can be backed up if execution at a stage is being over run.  This is a common indication of an issue or performance bottleneck.To demonstrate take for example a read request:

![Cassandra Process](/figures/figure15.png?raw=true)

https://blog.pythian.com/guide-to-cassandra-thread-pools/

## WILL IT WORK?
Yes , it will work.
Cassandra is  Fault tolerant: Data is automatically replicated to multiple nodes for fault-tolerance. Replication across multiple data centers is supported. Failed nodes can be replaced with no downtime.
Scalable : Some of the largest production deployments include Apple's, with over 75,000 nodes storing over 10 PB of data, Netflix (2,500 nodes, 420 TB, over 1 trillion requests per day), Chinese search engine Easou (270 nodes, 300 TB, over 800 million requests per day), and eBay (over 100 nodes, 250 TB).
Decentralized: There are no single points of failure. There are no network bottlenecks. Every node in the cluster is identical.
