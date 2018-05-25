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
  
  
  Error trace: 
  org.springframework.beans.factory.BeanCreationException: Error creating bean wit
  h name 'appStarter': Invocation of init method failed; nested exception is java.
  lang.IllegalStateException: Node failed to start!
          at org.springframework.beans.factory.annotation.InitDestroyAnnotationBea
  nPostProcessor.postProcessBeforeInitialization(InitDestroyAnnotationBeanPostProc
  essor.java:137) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractAutowireCapableBean
  Factory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanF
  actory.java:409) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractAutowireCapableBean
  Factory.initializeBean(AbstractAutowireCapableBeanFactory.java:1622) ~[spring-be
  ans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractAutowireCapableBean
  Factory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) ~[spring-beans
  -4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractAutowireCapableBean
  Factory.createBean(AbstractAutowireCapableBeanFactory.java:483) ~[spring-beans-4
  .3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractBeanFactory$1.getOb
  ject(AbstractBeanFactory.java:312) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RE
  LEASE]
          at org.springframework.beans.factory.support.DefaultSingletonBeanRegistr
  y.getSingleton(DefaultSingletonBeanRegistry.java:230) ~[spring-beans-4.3.16.RELE
  ASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBe
  an(AbstractBeanFactory.java:308) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELE
  ASE]
          at org.springframework.beans.factory.support.AbstractBeanFactory.getBean
  (AbstractBeanFactory.java:197) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEAS
  E]
          at org.springframework.beans.factory.support.DefaultListableBeanFactory.
  preInstantiateSingletons(DefaultListableBeanFactory.java:761) ~[spring-beans-4.3
  .16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.context.support.AbstractApplicationContext.finish
  BeanFactoryInitialization(AbstractApplicationContext.java:867) ~[spring-context-
  4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.context.support.AbstractApplicationContext.refres
  h(AbstractApplicationContext.java:543) ~[spring-context-4.3.16.RELEASE.jar!/:4.3
  .16.RELEASE]
          at org.springframework.boot.SpringApplication.refresh(SpringApplication.
  java:693) ~[spring-boot-1.5.12.RELEASE.jar!/:1.5.12.RELEASE]
          at org.springframework.boot.SpringApplication.refreshContext(SpringAppli
  cation.java:360) ~[spring-boot-1.5.12.RELEASE.jar!/:1.5.12.RELEASE]
          at org.springframework.boot.SpringApplication.run(SpringApplication.java
  :303) ~[spring-boot-1.5.12.RELEASE.jar!/:1.5.12.RELEASE]
          at cluster.AppStarter.main(AppStarter.java:44) [classes!/:na]
          at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.
  0_161]
          at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.
  0_161]
          at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[na:
  1.8.0_161]
          at java.lang.reflect.Method.invoke(Unknown Source) ~[na:1.8.0_161]
          at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner
  .java:48) [cluster-test-1.0-SNAPSHOT-fat.jar:na]
          at org.springframework.boot.loader.Launcher.launch(Launcher.java:87) [cl
  uster-test-1.0-SNAPSHOT-fat.jar:na]
          at org.springframework.boot.loader.Launcher.launch(Launcher.java:50) [cl
  uster-test-1.0-SNAPSHOT-fat.jar:na]
          at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:51)
   [cluster-test-1.0-SNAPSHOT-fat.jar:na]
  Caused by: java.lang.IllegalStateException: Node failed to start!
          at com.hazelcast.instance.HazelcastInstanceImpl.<init>(HazelcastInstance
  Impl.java:140) ~[hazelcast-3.10.1.jar!/:3.10.1]
          at com.hazelcast.instance.HazelcastInstanceFactory.constructHazelcastIns
  tance(HazelcastInstanceFactory.java:196) ~[hazelcast-3.10.1.jar!/:3.10.1]
          at com.hazelcast.instance.HazelcastInstanceFactory.newHazelcastInstance(
  HazelcastInstanceFactory.java:175) ~[hazelcast-3.10.1.jar!/:3.10.1]
          at com.hazelcast.instance.HazelcastInstanceFactory.newHazelcastInstance(
  HazelcastInstanceFactory.java:125) ~[hazelcast-3.10.1.jar!/:3.10.1]
          at com.hazelcast.core.Hazelcast.newHazelcastInstance(Hazelcast.java:57)
  ~[hazelcast-3.10.1.jar!/:3.10.1]
          at cluster.AppStarter.createHazelcastConfig(AppStarter.java:67) [classes
  !/:na]
          at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.
  0_161]
          at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.
  0_161]
          at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[na:
  1.8.0_161]
          at java.lang.reflect.Method.invoke(Unknown Source) ~[na:1.8.0_161]
          at org.springframework.beans.factory.annotation.InitDestroyAnnotationBea
  nPostProcessor$LifecycleElement.invoke(InitDestroyAnnotationBeanPostProcessor.ja
  va:366) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.annotation.InitDestroyAnnotationBea
  nPostProcessor$LifecycleMetadata.invokeInitMethods(InitDestroyAnnotationBeanPost
  Processor.java:311) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          at org.springframework.beans.factory.annotation.InitDestroyAnnotationBea
  nPostProcessor.postProcessBeforeInitialization(InitDestroyAnnotationBeanPostProc
  essor.java:134) ~[spring-beans-4.3.16.RELEASE.jar!/:4.3.16.RELEASE]
          ... 23 common frames omitted