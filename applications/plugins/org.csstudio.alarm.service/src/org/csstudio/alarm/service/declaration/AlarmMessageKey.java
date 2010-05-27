/*
 * Copyright (c) 2008 Stiftung Deutsches Elektronen-Synchrotron,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS.
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE.
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS,
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION,
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */
package org.csstudio.alarm.service.declaration;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Set of keys for the alarm message.
 *
 * A key has a defining name, which is used as the key string in the map which actually builds the alarm message.
 * Usually the defining name is equal to the name() of the enum, but might as well be given at construction time.
 *
 * @author jpenning
 * @author $Author$
 * @version $Revision$
 * @since 25.05.2010
 */
public enum AlarmMessageKey {
    EVENTTIME,
    NAME,
    SEVERITY,
    STATUS,
    FACILITY,
    HOST,
    TYPE,
    VALUE,
    APPLICATION_ID("APPLICATION-ID");

    private final String _definingName;

    /**
     * Use this constructor of the defining name of the key is equal to the name.
     */
    private AlarmMessageKey() {
        this(null);
    }

    /**
     * Use this constructor of the defining name of the key is different to the name.
     *
     * @param definingName
     */
    private AlarmMessageKey(final String definingName) {
        _definingName = definingName;
    }

    /**
     * Lookup the key with the given defining name.
     *
     * @param definingName
     * @return the key with the definingName
     */
    public static AlarmMessageKey findKeyWithDefiningName(final String definingName) {
        AlarmMessageKey result = null;
        for (AlarmMessageKey key : AlarmMessageKey.values()) {
            if (key.getDefiningName().equals(definingName)) {
                result = key;
                break;
            }
        }

        return result;
    }

    public String getDefiningName() {
        return (_definingName == null) ?  name() : _definingName;
    }
}
