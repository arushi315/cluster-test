Cluster test with version Hazelcast version 3.10.1

- Run command to create executable: 
  mvn clean install 
  
- Usage: 
  java -jar -Dcluster.enabled=true/false -Dcluster.members=member1,member2,... cluster-test-1.0-SNAPSHOT-fat.jar# cluster-test


- Follow the steps to generate "IllegalStateException: Node failed to start" error: 
 
 On node1, 
 Run executable with following command:
 java -jar -Dcluster.enabled=false -Dcluster.members=member1,member2 cluster-test-1.0-SNAPSHOT-fat.jar# cluster-test
 
 On node2, 
 Run executable with following command:
 java -jar -Dcluster.enabled=true -Dcluster.members=member1,member2 cluster-test-1.0-SNAPSHOT-fat.jar# cluster-test
  