package undecided.common.entity.generator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NodeIdGenerator {

    private static long nodeId;

    static {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            nodeId = Math.abs((long) hostName.hashCode());
        } catch (UnknownHostException e) {
            nodeId = (int) (Math.random() * (Math.pow(2, 10) - 1));
        }
        nodeId = nodeId & 1023;
    }

    public static long generateNodeId() {
        return nodeId;

    }
}
