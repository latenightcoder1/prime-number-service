package com.natwest.bank.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Read cache related configuration from properties file.
 *
 * @param expiryHours {@link Integer} cache expiry
 * @param maxSize     {@link Integer} max cache size
 */
@ConfigurationProperties(prefix = "cache")
public record CacheProperties(int expiryHours, int maxSize) {

}

