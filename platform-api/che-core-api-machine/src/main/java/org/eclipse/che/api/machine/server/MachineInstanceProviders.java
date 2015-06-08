/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.machine.server;

import org.eclipse.che.api.core.NotFoundException;
import org.eclipse.che.api.machine.server.spi.InstanceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides machines {link InstanceProvider} implementation by machine type
 *
 * @author Alexander Garagatyi
 */
public class MachineInstanceProviders {
    private final Map<String, InstanceProvider> instanceProviders;

    public MachineInstanceProviders(Set<InstanceProvider> instanceProviders) {
        this.instanceProviders = new HashMap<>(instanceProviders.size());
        for (InstanceProvider provider : instanceProviders) {
            this.instanceProviders.put(provider.getType(), provider);
        }
    }

    /**
     * Returns {link InstanceProvider} implementation by machine type
     *
     * @param machineType type of machine implementation
     * @return implementation of the machine {code InstanceProvider}
     * @throws NotFoundException if no implementation found for provided machine type
     */
    public InstanceProvider getProvider(String machineType) throws NotFoundException {
        if (instanceProviders.containsKey(machineType)) {
            return instanceProviders.get(machineType);
        }
        throw new NotFoundException(String.format("Can't find machine provider for unsupported machine type '%s'", machineType));
    }
}
