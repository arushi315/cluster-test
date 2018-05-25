package cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

@SpringBootApplication
@ComponentScan({"cluster"})
public class AppStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SPRING_CONFIG_LOCATION = "spring.config.location";
    private static final String ADDITIONAL_SPRING_CONFIG_LOCATION = "additional.spring.config.location";

    @Value("${hazelcast.port:5701}")
    private int hazelcastPort;
    @Value("${cluster.enabled}")
    private boolean clusterEnabled;
    @Value("#{'${cluster.members}'.split(',')}")
    private Set<String> clusterMembers;

    public static void main(final String... args) {
        String conf = getProperty(SPRING_CONFIG_LOCATION);
        String additionalConf = getProperty(ADDITIONAL_SPRING_CONFIG_LOCATION);
        if (StringUtils.isNotEmpty(additionalConf)) {
            conf = new StringBuilder(conf).append(",").append(additionalConf).toString();
            setProperty(SPRING_CONFIG_LOCATION, conf);
        }
        new SpringApplication(AppStarter.class).run(args);
    }

    @PostConstruct
    public void createHazelcastConfig() {
        LOGGER.info("Creating Hazelcast instance with clustering enabled flag {}", clusterEnabled);
        Config config = new Config();
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPort(hazelcastPort)
                .setPortAutoIncrement(false);

        JoinConfig join = networkConfig.getJoin();
        join.getMulticastConfig()
                .setEnabled(false);
        final TcpIpConfig tcpIpConfig = join.getTcpIpConfig()
                .setEnabled(clusterEnabled);

        if (clusterEnabled && !clusterMembers.isEmpty()) {
            clusterMembers.forEach(member -> {
                LOGGER.info("Adding member {} to config.", member);
                tcpIpConfig.addMember(member);
            });
        }
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        LOGGER.info("Hazelcast members are - {}", String.valueOf(hazelcastInstance.getCluster().getMembers()));
    }
}
