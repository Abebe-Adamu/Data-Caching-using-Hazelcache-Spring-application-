package com.dickson.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {


  @Value("${hazelcast.group.name}")
  private String group;

  @Value("${hazelcast.group.password}")
  private String password;

  @Value("${hazelcast.config.addresses}")
  private String addresses;

  @Bean
  public HazelcastInstance hazelcastInstance() {
    ClientConfig config = new ClientConfig();
    config.getGroupConfig().setName(group);
    config.getGroupConfig().setPassword(password);
    config.getNetworkConfig().addAddress(addresses);

    HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
    return client;
  }

}
