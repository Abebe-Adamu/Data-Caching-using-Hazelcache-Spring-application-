# HAZELCAST
As an alternative we opted to build a secondary proof of concept application using Hazelcast. Hazelcast is a open-sourced data grid that eliminates single points of failure by caching frequently accessed data in-memory and across an elastically scalable data grid. Like Geode, Hazelcast consists of cluster members that have a peer-to-peer relationship with connections to each node within the cluster. Hazelcast provides back pressure for load balancing purposes. This is done by limiting the number of concurrent operation invocations or periodically making an asynchronized backup synchronization. With the linear scaling providing the speed that we need, and the load balancers preventing the creation of hotspots, we were able to build a secondary proof-of-concept that could provide us with the information that could be extrapolated and used to create a cost estimate. 

## PERFORMANCE
To test the proof-of-concept we created a java class called TravelBean, and provided it with airline-related data attributes. Then we created "get" and "set" methods for each data attribute and provided load to the system by serializing an object of that class into a byte array to be hashed. Then we took the modulus of the hashed result times the number of partitions (9) to obtain our partition ID (UID). This UID is linked with our Travelbean object. We used restfulAPI to request the data.  

|Req Per Sec for Duration of Perf Test| Perf Test Duration | Total Cache Size | Updates to Cache Per Sec |
|-------------------------------------|--------------------|------------------|--------------------------|
|1000                                 | 4 hours            | 450 GB           | 100                      |


|Avr Get Time| Avr Put Time | Avr Get Time for 15,000 Req |
|------------|--------------|-----------------------------|
|8ms         | 11ms         | 27ms                        |




## FIVE YEAR COST PLAN
### Infrastructure 
| AWS Instance Type | Price/hr | Number of Instances | Yearly Cost  | 5 Year Cost   |
|-------------------|----------|---------------------|--------------|---------------|
| r4.xlarge         | 0.266     |  12                |   $27,961.92 |    $139,809.60|

### Developement 
| Number of Teams | Developers/Teams | Average Developer Salary  | Yearly        | 5 Year Cost   |
|-----------------|------------------|---------------------------|---------------|---------------|
|3                | 5                | $100,000                  | $1,500,000.00 | $7,500,000,00 |

### Support  
| Number of Teams | Developers/Teams | Average Developer Salary  | Yearly        | 5 Year Cost   |
|-----------------|------------------|---------------------------|---------------|---------------|
|3                | 5                | $100,000                  | $1,500,000.00 | $7,500,000,00 |



Software – Open Source license requires no cost, but is reflected in negligible increase in cost of development teams and BAU Support

### Total 5 Year Cost: $15,195,236.35



