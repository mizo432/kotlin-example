package undecided.shared.web.token.transaction;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * {@code HandlerMethodArgumentResolver} to handle {@code TransactionTokenContext}
 */
public class TransactionTokenContextHandlerMethodArgumentResolver implements
        HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return TransactionTokenContext.class.equals(parameter
                .getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        return webRequest.getAttribute(
                TransactionTokenInterceptor.TOKEN_CONTEXT_REQUEST_ATTRIBUTE_NAME,
                RequestAttributes.SCOPE_REQUEST);
    }
}
