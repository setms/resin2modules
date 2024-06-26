package com.remonsinnema.resin2domains.graph;

import java.util.UUID;


public record TestVertex(String name) implements Vertex {

    public TestVertex() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return name();
    }

}
