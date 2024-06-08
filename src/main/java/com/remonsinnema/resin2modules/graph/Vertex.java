package com.remonsinnema.resin2modules.graph;


public interface Vertex extends Comparable<Vertex> {

    String name();

    default int compareTo(Vertex that) {
        return this.name().compareTo(that.name());
    }

}
