package undecided.shared.web.support;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import java.util.*;

public class CompositeRequestDataValueProcessor implements
        RequestDataValueProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            CompositeRequestDataValueProcessor.class);

    private final List<RequestDataValueProcessor> processors;

    private final List<RequestDataValueProcessor> reversedProcessors;

    public CompositeRequestDataValueProcessor(
            RequestDataValueProcessor... processors) {
        LOGGER.trace("CompositeRequestDataValueProcessor(RequestDataValueProcessor...)");
        this.processors = Collections.unmodifiableList(Arrays.asList(
                processors));
        List<RequestDataValueProcessor> reverse = Arrays.asList(processors);
        Collections.reverse(reverse);
        this.reversedProcessors = Collections.unmodifiableList(reverse);
    }

    @Override
    public @NotNull String processAction(@NotNull HttpServletRequest request, @NotNull String action,
                                         @NotNull String method) {
        LOGGER.trace("#processAction(HttpServletRequest, String, String)");

        String result = action;
        for (RequestDataValueProcessor processor : processors) {
            result = processor.processAction(request, action, method);
            if (!action.equals(result)) {
                break;
            }
        }

        return result;
    }

    @Override
    public @NotNull String processFormFieldValue(@NotNull HttpServletRequest request, String name,
                                                 @NotNull String value, @NotNull String type) {
        LOGGER.trace("#processFormFieldValue(HttpServletRequest, String, String)");

        String result = value;
        for (RequestDataValueProcessor processor : processors) {
            result = processor.processFormFieldValue(request, name, value,
                    type);
            if (!value.equals(result)) {
                break;
            }
        }

        return result;
    }

    @Override
    public Map<String, String> getExtraHiddenFields(
            @NotNull HttpServletRequest request) {
        LOGGER.trace("#getExtraHiddenFields(HttpServletRequest)");

        Map<String, String> result = new LinkedHashMap<String, String>();
        for (RequestDataValueProcessor processor : reversedProcessors) {
            Map<String, String> map = processor.getExtraHiddenFields(request);
            if (map != null) {
                result.putAll(map);
            }
        }
        return result;
    }

    @Override
    public @NotNull String processUrl(@NotNull HttpServletRequest request, @NotNull String url) {
        LOGGER.trace("#processUrl(HttpServletRequest, String)");
        String result = url;
        for (RequestDataValueProcessor processor : processors) {
            result = processor.processUrl(request, url);
            if (!url.equals(result)) {
                break;
            }
        }
        return result;
    }

}
