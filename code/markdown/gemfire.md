# GEMFIRE

## SPECS
Gemfire is a multithreaded in-memory data grid that is powered by Apache. This data grid has multiple features including but is not limited to:		
- Predictable low latency
- Elastic Scale-Out
- Real-Time Event Notifications
- High Availability and Business Continuity
- Durability
- Cloud-Ready
Gemfire’s low latency feature allows it to perform at high speed levels.
Gemfire is very flexible and can be deployed in many different kinds of configurations.

![Gemfire/Geode Topology](/figures/figure10.png?raw=true)

### SCALABILITY
Scalability is achieved through dynamic partitioning of data across many members and spreading the data load uniformly across the servers. For “hot” data, you can configure the system to expand dynamically to create more copies of the data. You can also provision application behavior to run in a distributed manner in close proximity to the data it needs. If you need to support high and unpredictable bursts of concurrent client load, you can increase the number of servers managing the data and distribute the data and behavior across them to provide uniform and predictable response times. Clients are continuously load balanced to the server farm based on continuous feedback from the servers on their load conditions. With data partitioned and replicated across servers, clients can dynamically move to different servers to uniformly load the servers and deliver the best response times. You can also improve scalability by implementing asynchronous “write behind” of data changes to external data stores, like a database. This avoids a bottleneck by queuing all updates in order and redundantly. You can also conflate updates and propagate them in batch to the database.

### FAILOVER
In a multi-site installation, if the primary gateway server goes offline, a secondary gateway sender must take over primary responsibilities as the failover system. The existing secondary gateway sender detects that the primary gateway sender has gone offline, and a secondary one becomes the new primary. Because the queue is distributed, its contents are available to all gateway senders. So, when a secondary gateway sender becomes primary, it is able to start processing the queue where the previous primary left off with no loss of data.

### LOAD BALANCING
The locator is a GemFire process that tells new, connecting members where running members are located and provides load balancing for server use. You can run locators as peer locators, server locators, or both: Peer locators give joining members connection information to members already running in the locator’s distributed system. Server locators give clients connection information to servers running in the locator’s distributed system. Server locators also monitor server load and send clients to the least-loaded servers. By default, locators run as peer and server locators. You can run the locator standalone or embedded within another GemFire process. Running your locators standalone provides the highest reliability and availability of the locator service as a whole.

## WILL IT WORK?
Yes, Gemfire will work because it distributes and replicates data to many nodes, while maintaining high-performance speeds.
