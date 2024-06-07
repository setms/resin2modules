package com.remonsinnema.resin2modules.process;

import com.remonsinnema.resin2modules.graph.Vertex;

import java.util.Collection;


public record Aggregate(String name, Collection<String> dataItems) implements Vertex, DataContainer {

}
