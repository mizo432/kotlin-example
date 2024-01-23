package undecided.common.entity.generator;

import com.google.common.base.Objects;
import undecided.common.primitive.application.ApplicationInfo;
import undecided.common.primitive.ipadress.IpAddressProvider;

import java.net.UnknownHostException;

public class NodeIdGenerator {

    private static long nodeId;

    static {
        try {
            String hostName = IpAddressProvider.ipAddress();
            String applicationName = ApplicationInfo.name();
            Long serverPort = ApplicationInfo.port();
            nodeId = Math.abs((long) Objects.hashCode(hostName, applicationName, serverPort));
        } catch (UnknownHostException e) {
            nodeId = (int) (Math.random() * (Math.pow(2, 10) - 1));
        }
        nodeId = nodeId & 1023;
    }

    public static long generateNodeId() {
        return nodeId;

    }
}
