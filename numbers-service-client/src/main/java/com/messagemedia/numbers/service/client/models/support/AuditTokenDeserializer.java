/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.messagemedia.numbers.service.client.models.AuditToken;

import java.io.IOException;

public class AuditTokenDeserializer extends JsonDeserializer<AuditToken> {

    @Override
    public AuditToken deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return AuditToken.fromString(p.getValueAsString());
    }
}
