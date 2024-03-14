package undecided.shared.web.support;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import java.util.Map;

public class RequestDataValueProcessorAdaptor implements
        RequestDataValueProcessor {

    public String processAction(HttpServletRequest request, String action) {
        return action;
    }

    @Override
    public @NotNull String processAction(@NotNull HttpServletRequest request,
                                         @NotNull String action, @NotNull String method) {
        return action;
    }

    @Override
    public @NotNull String processFormFieldValue(@NotNull HttpServletRequest request, String name,
                                                 @NotNull String value, @NotNull String type) {
        return value;
    }

    @Override
    public Map<String, String> getExtraHiddenFields(
            @NotNull HttpServletRequest request) {
        return null;
    }

    @Override
    public @NotNull String processUrl(@NotNull HttpServletRequest request, @NotNull String url) {
        return url;
    }

}
