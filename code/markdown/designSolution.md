# GEMFIRE
After much research we opted to build a proof-of-concept using Apache Geode (open-source Pivotal Gemfire).  Geode is a in-memory data grid that boasts great performance and reliability.  In contrast to in-memory databases such as Redis, Geode consists of nodes that are exact replicas of other nodes in the cluster.  Requests are divided out to the nodes by load balancers referred to as "Locators."  With the horizontal scaling providing the speed that we need, and the load balancers preventing the creation of hotspots, we were able to build a proof-of-concept that could provide us with information that could be extrapolated and used to create a cost estimate.

_As seen in the figure below, the proof-of-concept was built using two identical servers being fed by a single locator._
![Topology](/figures/figure16.png?raw=true)

### Redis Adapter
Geode has built in the ability for clients to connect to a Geode cluster and handle Redis requests.  This allows for a much smoother transition away from Redis as well as a much cheaper alternative to rebuilding the clients to accommodate the new infrastructure.  

## PERFORMANCE
To test the proof-of-concept, YCSB (Yahoo Cloud Serving Benchmark) was used to provide load to the system and report statistics.  The tests were run on hosts with the following specifications:

| Benchmark | Operating System    | Kernel     | CPU           | Clock Speed | Cores/Threads | RAM     | Geode Version | YCSB Version | Java Version |
|-----------|---------------------|------------|---------------|-------------|---------------|---------|---------------|--------------|--------------|
| Test 1    | Ubuntu 17.10 64 bit | 4.13.0-31  | Intel i5-4670 | 3.4GHz      | 4/4           | 32GB    | 1.5           | 0.14         | 1.8.0_121    |
| Test 2    | Ubuntu 17.10 64 bit | 4.13.0-31  | Intel i5-4670 | 3.4GHz      | 4/4           | 32GB    | 1.5           | 0.14         | 1.8.0_121    |
| Test 3    | Ubuntu 16.04 64 bit | 4.4.0-1054 | AWS t2.medium | N/A         | 2/2           | 4GB     | 1.5           | 0.14         | 1.8.0_121    |
| Test 4    | Ubuntu 16.04 64 bit | 4.4.0-1054 | AWS t2.xlarge | N/A         | 4/4           | 16GB    | 1.5           | 0.14         | 1.8.0_121    |
| Test 5    | Ubuntu 16.04 64 bit | 4.4.0-1054 | AWS r4.xlarge | N/A         | 4/4           | 32GB    | 1.5           | 0.14         | 1.8.0_121    |

With the benchmark data, a rough estimate of the infrastructure needed to meet the goal of 100 billion requests per month (~38580 requests/sec).  Below is performance data pulled from the benchmark results.

| Benchmark | Servers | Locators | Runtime(ms) | Throughput(ops/sec) | Average Latency(us) | Minimum Latency(us) | Maximum Latency(us) | 95thPercentileLatency(us) | 99thPercentileLatency(us) |
|-----------|---------|----------|-------------|---------------------|---------------------|---------------------|---------------------|---------------------------|---------------------------|
| Test 1    | 2       | 1        | 2960        | 337.8378378378378   | 1627.232            | 231                 | 114175              | 6343                      | 10655                     |
| Test 2    | 2       | 1        | 2533        | 394.78878799842084  | 1358.581            | 237                 | 115007              | 5615                      | 7575                      |
| Test 3    | 2       | 1        | 3782        | 264.41036488630357  | 2141.277            | 350                 | 227583              | 8415                      | 14999                     |
| Test 4    | 2       | 1        | 3153        | 317.1582619727244   | 1572.941            | 360                 | 162303              | 6379                      | 10431                     |
| Test 5    | 2       | 1        | 2754        | 363.10820624546113  | 1204.801            | 219                 | 141439              | 4919                      | 6623                      |

### Benchmark Notes
**Test 1:**
1. First Benchmark
1. Bare metal hardware
1. Hardware has a generous amount of RAM and probably enough threads to be efficient
1. Network configured for WLAN
1. Both servers and locator were hosted on the same machine

**Test 2:**
1. Same hardware/software as first Benchmark
1. Hardware has a generous amount of RAM and probably enough threads to be efficient
1. Reconfigured network for Gigabit intranet which should be the cause of less Latency and higher Throughput
1. Both servers and locator were hosted on the same machine

**Test 3:**
1. Set up proof-of-concept on an AWS t2.medium
1. I believe that the CPU throughput will be a bottleneck for the full proof-of-concept on a single host (servers and locator may be too much)
1. Network performance listed as low to moderate
1. Both servers and locator were hosted on the same machine

**Test 4:**
1. Set up proof-of-concept on a AWS t2.xlarge
1. Has same number of threads as Test 2 but is using vCPUs
1. Less RAM than Test 2
1. Network performance listed as moderate
1. Both servers and locator were hosted on the same machine

**Test: 5**
1. Memory optimized AWS Instance
1. Network up to 10 Gigabit
1. Has lowest latency but not highest in throughput (second to bare metal 4th gen i5)
1. Both servers and locator were hosted on the same machine

*For more details on benchmarks, raw output can be found under /ksucapstone18/raw/*

## FIVE YEAR COST PLAN
### Infrastructure
| AWS Instance Type | Price/hr | Number of Instances | Yearly Cost | 5 Year Cost   |
|-------------------|----------|---------------------|-------------|---------------|
| r4.xlarge         | 0.266    | 318                 | $740,990.88 | $3,704,954.40 |

### Development
| Number of Teams | Developers/Teams | Average Developer Salary | Yearly       | 5 Year Cost  |
|-----------------|------------------|--------------------------|--------------|--------------|
| 3               | 5                | $80,000.00               | 1,200,000.00 | 6,000,000.00 |

### Support
| Number of Teams | Developers/Teams | Average Developer Salary | Yearly       | 5 Year Cost  |
|-----------------|------------------|--------------------------|--------------|--------------|
| 3               | 5                | $80,000.00               | 1,200,000.00 | 6,000,000.00 |

### Software
| Software | Price/hr | Number of Instances | Yearly Cost     | 5 Year Cost     |
|----------|----------|---------------------|-----------------|-----------------|
| Geode    | $0.00    | 318                 | $0.00           | $0.00           |
| Gemfire  | $1.00    | 318                 | $2,785,680.00   | $13,928,400.00  |

### Total 5 Year Cost: 29,633,354.40
