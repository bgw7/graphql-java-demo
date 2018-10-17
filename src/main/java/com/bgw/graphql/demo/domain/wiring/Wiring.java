package com.bgw.graphql.demo.domain.wiring;

import com.bgw.graphql.demo.domain.fetcher.HouseFetcher;
import com.bgw.graphql.demo.domain.fetcher.MagicSchoolFetcher;
import com.bgw.graphql.demo.domain.fetcher.StudentFetcher;
import com.bgw.graphql.demo.domain.fetcher.WandFetcher;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wiring {
    @Autowired
    StudentFetcher studentFetcher;

    @Autowired
    WandFetcher wandFetcher;

    @Autowired
    HouseFetcher houseFetcher;

    @Autowired
    MagicSchoolFetcher magicSchoolFetcher;

    public RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",typeWiring -> typeWiring
                        .dataFetcher("magicSchool",magicSchoolFetcher.getData())
                )
                .type("MagicSchool", typeWiring -> typeWiring
                        .dataFetcher("student", studentFetcher.getData())
                )
                .type("Student",typeWiring -> typeWiring
                        .dataFetcher("wand", wandFetcher.getData())
                        .dataFetcher("house",houseFetcher.getData())
                )
                .type("Mutation",typeWiring -> typeWiring
                        .dataFetcher("enrollStudent",studentFetcher.putData())
                )
                .build();
    }
}