package com.natwest.bank.api.cache;

import java.util.List;

/**
 * Prime cache manager interface permitting only PrimeCacheManagerImpl.
 */
public sealed interface PrimeCacheManager permits PrimeCacheManagerImpl {

    List<Integer> getPrimes(final int n);

    void updateCache(final Integer key, final List<Integer> value);
}
