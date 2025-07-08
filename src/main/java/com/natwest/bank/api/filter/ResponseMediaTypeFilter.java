package com.natwest.bank.api.filter;

import static com.natwest.bank.api.constants.RequestParameters.HEADER_ACCEPT;
import static com.natwest.bank.api.constants.RequestParameters.PARAM_MEDIA_TYPE;

import com.natwest.bank.api.utils.MediaTypeUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Associate appropriate media type with default application/json with support of application/xml.
 * <br> Any other media type to be translated as application/json.
 */
@Component
public class ResponseMediaTypeFilter extends OncePerRequestFilter {

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
        final HttpServletResponse response, final FilterChain filterChain)
        throws ServletException, IOException {
        final String responseMediaType = MediaTypeUtils.getResponseMediaType(
            request.getParameter(PARAM_MEDIA_TYPE));
        filterChain.doFilter(getHttpServletRequestWrapper(request, responseMediaType), response);
    }

    private static HttpServletRequestWrapper getHttpServletRequestWrapper(
        final HttpServletRequest request, final String responseMediaType) {
        return new HttpServletRequestWrapper(request) {

            @Override
            public String getHeader(final String name) {
                if (isHeaderAccept(name)) {
                    return responseMediaType;
                }
                return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaders(final String name) {
                if (isHeaderAccept(name)) {
                    return Collections.enumeration(List.of(responseMediaType));
                }
                return super.getHeaders(name);
            }
        };
    }

    private static boolean isHeaderAccept(final String name) {
        return HEADER_ACCEPT.equalsIgnoreCase(name);
    }

}
