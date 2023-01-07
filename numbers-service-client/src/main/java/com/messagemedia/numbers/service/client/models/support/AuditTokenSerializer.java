/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.messagemedia.numbers.service.client.models.AuditToken;

import java.io.IOException;

public class AuditTokenSerializer extends JsonSerializer<AuditToken> {

    @Override
    public void serialize(AuditToken value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toJsonString());
    }
}
