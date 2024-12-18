//package com.company.aemss;
//
//import com.fasterxml.jackson.core.StreamWriteConstraints;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//    @Configuration
//    public class JacksonConfig {
//        @Bean
//        public ObjectMapper objectMapper() {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setDefaultStreamWriteConstraints(
//                    StreamWriteConstraints.builder()
//                            .maxNestingDepth(2000)
//                            .build()
//            );
//            return mapper;
//        }
//    }