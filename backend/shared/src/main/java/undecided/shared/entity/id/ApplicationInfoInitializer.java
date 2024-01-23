package undecided.shared.entity.id;

import undecided.common.primitive.application.ApplicationInfo;

import java.util.concurrent.atomic.AtomicReference;

public class ApplicationInfoInitializer extends ApplicationInfo {
    private static final AtomicReference<String> applicationName = new AtomicReference<>();
    private static final AtomicReference<Long> port = new AtomicReference<>();

    public ApplicationInfoInitializer(String applicationName, Long port) {
        super();
        ApplicationInfoInitializer.applicationName.set(applicationName);
        ApplicationInfoInitializer.port.set(port);

    }

    public static void initialize(String applicationName, Long port) {
        ApplicationInfoInitializer instance = new ApplicationInfoInitializer(applicationName, port);
        new ApplicationInfo(instance);
    }

    public String applicationName() {
        return ApplicationInfoInitializer.applicationName.get();

    }


    public long serverPort() {
        return ApplicationInfoInitializer.port.get();
    }


}
