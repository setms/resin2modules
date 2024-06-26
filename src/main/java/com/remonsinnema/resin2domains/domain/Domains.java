package com.remonsinnema.resin2domains.domain;

import com.remonsinnema.resin2domains.graph.Graph;
import com.remonsinnema.resin2domains.graph.Vertex;

import java.util.Optional;
import java.util.stream.Stream;


public class Domains extends Graph {

    public Domains() {
        super(new DomainConstraints());
    }

    public void add(Domain domain) {
        vertex(domain);
    }

    public boolean contains(Vertex vertex) {
        return domains()
                .anyMatch(m -> m.contains(vertex));
    }

    private Stream<Domain> domains() {
        return vertices().map(Domain.class::cast);
    }

    public Optional<Domain> find(Vertex vertex) {
        return domains()
                .filter(m -> m.contains(vertex))
                .findAny();
    }

}
