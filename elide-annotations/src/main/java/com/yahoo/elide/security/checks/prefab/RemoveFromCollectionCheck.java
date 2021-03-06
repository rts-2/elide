/*
 * Copyright 2016, Yahoo Inc.
 * Licensed under the Apache License, Version 2.0
 * See LICENSE file in project root for terms.
 */
package com.yahoo.elide.security.checks.prefab;

import com.yahoo.elide.security.ChangeSpec;
import com.yahoo.elide.security.RequestScope;
import com.yahoo.elide.security.checks.CommitCheck;

import java.util.Collection;
import java.util.Optional;

/**
 * A check designed to look at the changeSpec to determine whether a value was removed from a Collection
 *
 * @param <T> type parameter
 * @deprecated Since 2.2, use {@link com.yahoo.elide.security.checks.prefab.Collections.RemoveOnly} instead
 */
@Deprecated
public class RemoveFromCollectionCheck<T> extends CommitCheck<T> {

    @Override
    public boolean ok(T record, RequestScope requestScope, Optional<ChangeSpec> changeSpec) {
        if (changeSpec.isPresent() && changeSpec.get().getModified() instanceof Collection) {
            Collection originalCollection = (Collection) changeSpec.get().getOriginal();
            Collection modifiedCollection = (Collection) changeSpec.get().getModified();
            return (originalCollection.size() > modifiedCollection.size())
                    && (originalCollection.containsAll(modifiedCollection));
        }
        return false;
    }
}
