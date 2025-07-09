package com.natwest.bank.api.utils;

import static com.natwest.bank.api.constants.RequestParameters.MEDIA_TYPE_JSON;
import static com.natwest.bank.api.constants.RequestParameters.MEDIA_TYPE_XML;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

/**
 * Utils to process mediaType parameter.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MediaTypeUtils {

    /**
     * Sanitize media type.
     *
     * @param mediaType {@link String}
     * @return {@link String}
     */
    public static String resolveMediaType(final String mediaType) {
        return MEDIA_TYPE_XML.equalsIgnoreCase(mediaType) ? MEDIA_TYPE_XML : MEDIA_TYPE_JSON;
    }

    /**
     * Identify response media type.
     *
     * @param responseMediaType {@link String} client input
     * @return HTTP supported media type.
     */
    public static String getResponseMediaType(final String responseMediaType) {
        return MEDIA_TYPE_XML.equalsIgnoreCase(responseMediaType) ? MediaType.APPLICATION_XML_VALUE
            : MediaType.APPLICATION_JSON_VALUE;
    }
}
