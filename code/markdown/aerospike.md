# AEROSPIKE

## SPECS

### SPEED
Aerospike scales linearly on the horizontal axis and each node is capable of processing up to 1M TPSz the aggregate cluster top speed is built for very big and fast data. Most companies need only a fraction of this speed, but this top speed has benefit of increased stability and predictability at lower speeds.  Not only can Aerospike handle 1 Million TPS per second on a single node, it can horizontally scale.

### FAILOVER
Aerospike reliability guarantee cluster failure detection. On failure of one cluster node, Aerospike immediately recovers and reforms the cluster.Cluster nodes check each other by using the heartbeat feature and heartbeat helps nodes coordinate themselves. There is no masters node and all nodes are peers, and they track each other in the cluster.

### LOAD BALANCING
Aerospike in-memory database uses a Shared-Nothing architecture, where every node in the Aerospike cluster is identical meaning that all nodes are peers and there is no single point of failure. Partition are distributed evenly across cluster nodes. For example, if n nodes in the cluster, each node stores 1/n of the data. Data distributes evenly across the cluster nodes which means that there are no hotspots where one node handles a lot more requests than other nodes in the cluster. Aerospike ensures that random data assignment balance server load. For instance, if many last names starts with R, and R has a lot of traffic than the server handling last names beginning with X,Y or Z. Random data assignment ensures a balanced server load. In reliability, Aerospike replicates partitions on one or more nodes. One node becomes the data master for reads and writes for a partition, and others nodes store replicas.

![Aerospike Topology](/figures/figure9.png?raw=true)

### MULTITHREADED
Aerospike is multi-threaded, multi-core, high performance NoSQL solution. One can run several high throughput operations in parallel with 99% response times that are less than one millisecond.

## WILL IT WORK?
Yes, I think Aerospike will work because all nodes are peers, and data are distributed randomly and evenly across the cluster nodes.
