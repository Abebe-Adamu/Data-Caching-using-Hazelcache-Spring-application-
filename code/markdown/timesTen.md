# TIMES TEN
Oracle Time Ten is a in-Memory Database technology full-featured , memory-optimized, relational database with persistence and recoverability. Times Ten provides instant responsiveness and very high throughput required by database-intensive applications. Times Ten operates on databases that fit entirely in physical memory(RAM).  Times Ten is deployed as an in-memory cache database with automatic data synchronization between Times Ten and the Oracle Database.

## SPECS

### SPEED
Times Ten provides high performance and data replication with no exception. The figure below shows a workload with a single stream replication that can sud=stain a maximum throughput of around 60,000 transactions per second. Enabling parallel replication (with parallelism = 8) delivers a peak throughput of almost 180,000 TPS (almost 3x that of single stream replication) using the default setting whereby commit ordering is enforced. Enabling the 'no commit ordering' optimization shows even better results with the peak throughput increasing to 285,000 TPS (nearly 5x that of single stream replication). Data are stored  in memory, and accessed at RAM speeds instead of network speeds, TimesTen can provide extremely fast response time…

![Times Ten Speed](/figures/figure5.png?raw=true)

TimesTen can achieve response times in the microseconds due to its in-memory architecture. With TimesTen, a transaction that reads a database record can take fewer than 2.5 microseconds, and transactions that update or insert a record can take fewer than 8 microseconds.

![Times Ten Read/Write](/figures/figure6.png?raw=true)

Figure 2 shows the response times for an application executing read and update transactions on an Intel E5-2680 @2.7GHz 2 sockets 8cores/socket system running Oracle Linux.
The graph below shows a significant reduction in application response time when using TimesTen Cache

![Times Ten Latency](/figures/figure7.png?raw=true)

#### Time Ten Scalability
TimesTen takes advantage of multiple CPUs on symmetric multiprocessor computers. Figure 3 shows the transaction throughput of TimesTen on an Intel E5-2680 @2.7GHz 2 sockets 8 cores/socket system running Oracle Linux. Each transaction executes a single SQL select (read) or update operation, as indicated. The results are shown for 1, 4, 8 and 16 concurrent processes.

![Times Ten Throughput](/figures/figure8.png?raw=true)

Time Ten highest volume workload is 100% reads tes, and measures many individual records that can be retrieved using a key lookup. Update operations require more processing than read operations, partly because changes must be logged for recovery purposes. In this result, more than 952,778 records were updated per second with 16 concurrent processes, and more than 130,375 with a single process.

### FAILOVER
TimesTen Cache will failover automatically to the standby Oracle database without data loss.
If the Oracle Database becomes inaccessible to TimesTen Cache for any reason such as network failure, hardware failure, or Oracle Database failure, TimesTen Cache is designed to be resilient to such failures. The in-memory cache will continue to be accessible to applications. Furthermore, in case of an AWT Cache Group, updates to the cache will continue to be logged in Oracle TimesTen so that once the Oracle Database becomes accessible again, the updates are propagated to it. Similarly changes to Read-Only Cache Groups that were made on the Oracle Database but not yet propagated to the in-memory cache will remain recorded on the Oracle database and will be propagated to the cache(s) once the Oracle database is accessible again.

An active standby pair includes an active master database, a standby master database, and optional read-only subscriber databases. The active master database is updated directly. The standby master database cannot be updated directly. It receives the updates from the active master database and propagates the changes to read-only subscriber databases. This arrangement ensures that the standby master database is always ahead of the read-only subscriber databases and enables rapid failover to the standby database if the active master database fails. Only one of the master databases can function as an active master database at a specific time. If the active master database fails, the role of the standby master database must be changed to active before recovering the failed database as a standby database. The replication agent must be started on the new standby master database. If the standby master database fails, the active master database replicates changes directly to the read-only subscribers. After the standby master database has recovered, it contacts the active standby database to receive any updates that have been sent to the read-only subscribers while the standby was down or was recovering. When the active and the standby master databases have been synchronized, then the standby resumes propagating changes to the subscribers.  Automatic failure detection and failover of database and applications is available through integration with Oracle Clusterware. Active standby replication can be used with the Oracle TimesTen Application-Tier Database Cache to achieve cross-tier high availability. Active standby replication is available for both read-only and updatable caches.

### LOAD BALANCING
TimesTen Cache takes full advantage of RAC’s high availability features. A RAC configuration consists of a single physical database that is accessible by several nodes. The runtime configuration on a single node is called an instance. RAC provides load balancing, high availability, and data consistency across all instances.
The replication mechanism in TimesTen is by default asynchronous. When using asynchronous replication, an application updates a master database and continues working without waiting for the updates to be received by the subscribers. The master and subscriber databases have internal mechanisms to confirm that the updates have been successfully received and committed by the subscriber. These mechanisms, which are completely invisible to the application, ensure that updates are not lost and are applied by a subscriber only once.

### MULTITHREADED
TimesTen transactions conform to the atomicity, consistency, isolation, and durability properties. These properties ensure that, in a multiuser system, each transaction operates as if it were the only transaction being executed at the time, and that the system can guarantee that the effects of a committed transaction are not lost. These are the most rigid properties required of data managers, and TimesTen ensures full conformance. A common misperception is that in-memory data managers cannot prevent data loss from system failures. In fact, the same techniques that make transactions and data durable in a conventional database are used in TimesTen. As in all transaction-oriented systems, durability is achieved through a combination of change logging and periodic refreshes of a version of the database that resides on a disk.

## WILL IT WORK?
Times Ten provides application-tier data management for performance-critical systems, optimized for blazing-fast response and real-time caching of Oracle data. Thousands of companies worldwide, including Alcatel-Lucent, Aspect, Avaya, Bank of America Merrill Lynch, Bridgewater Systems, BroadSoft, Cisco, Deutsche Börse, Ericsson, JPMorgan, KDDI, NEC, NYFIX, Smart Communications, United States Postal Office and Verizon Wireless use Oracle TimesTen in production applications.
