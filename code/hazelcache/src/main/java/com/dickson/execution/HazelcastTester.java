package com.dickson.execution;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class HazelcastTester {

  @Value("${hazelcast.map.data}")
  private String travelDataMap;

  IMap<Long, TravelBean> map;


  @Autowired
  HazelcastInstance instance;

  @PostConstruct
  public void init() throws IOException, SecurityException {
      map = instance.getMap(travelDataMap);
  }

  @RequestMapping("/getTravel")
  public TravelBean getTravel(@RequestParam(value="id", defaultValue = "0") String id){
    return map.get(Long.valueOf(id));
  }

  @RequestMapping("/updateTravel")
  public ResponseEntity<String> updateTravel (@RequestBody TravelBean data) {
    map.set(data.getId(),data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }





}
