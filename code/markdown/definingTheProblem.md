# DEFINING THE PROBLEM
In-memory database systems are used to be able to access data more quickly than retrieving it from the disk.  The reduction in latency is critical to databases that have high demands on traffic.  There are three main requirements to building a production-ready in-memory caching system: speed, failover, and load balancing.  Not every caching system has the same approach to meeting these requirements, but they typically support a common feature set that is needed to meet the industry’s needs.  

## SPEED
Overall speed requirements of the in-memory database caching system is dictated by the projected load of the system.  Their current system processes an estimated 10 billion requests per month.  It is predicted that the workload in the near future will be roughly 100 billion requests per month (38,580 requests per second).  Modern database systems use a clustered approach to support the scalability needed to meet the speed requirements.  

## FAILOVER
A very important component of any system in production is the ability to be able to recover from a system failure.  There are several ways to implement failover.  In clustered systems, it is common to have redundant nodes in the cluster that can take over for a failed node.  This method is seen in the sponsors current caching system.

## LOAD BALANCING
A secondary function of a cluster is to provide a level of load balancing on the systems.  Some caching systems implement advanced load balancing techniques while others keep it primitive.  When specific data in the cache is used much more frequently than others and no advanced load balancing technique is implemented, what is called a hotspot is created.  These hotspots are nodes that have too much traffic and not enough throughput.

Our sponsor currently uses Redis as their in-memory caching system.  Redis meets the speed requirements and supports failover, but it does not take a sophisticated approach to load balancing.  The result is hotspots in the cluster.  You can technically mitigate the is by identifying the keys that are creating the hotspot and shared them into new nodes to reduce load, but we are going to look at at more sophisticated approaches available in other technologies.
