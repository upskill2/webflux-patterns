package com.webfluxpattern.section_04.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfluxpattern.section_04.dto.OrchestrationRequestContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugUtil {
    public static void printContext (OrchestrationRequestContext context)  {
        ObjectMapper mapper = new ObjectMapper ();
        mapper.findAndRegisterModules ();
        final String value;
        try {
            value = mapper.writerWithDefaultPrettyPrinter ().writeValueAsString (context);
        } catch (JsonProcessingException e) {
            throw new RuntimeException (e);
        }
        log.info ("{}", value);
    }
}
