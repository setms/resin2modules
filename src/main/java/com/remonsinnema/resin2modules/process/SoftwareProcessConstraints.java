package com.remonsinnema.resin2modules.process;

import com.remonsinnema.resin2modules.graph.Constraints;
import com.remonsinnema.resin2modules.graph.Edge;
import com.remonsinnema.resin2modules.graph.Vertex;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;


class SoftwareProcessConstraints implements Constraints {

    private final Map<Class<? extends Vertex>, List<Class<? extends Vertex>>> allowedPredecessors = Map.of(
        Aggregate.class, List.of(Command.class),
        Command.class, List.of(Person.class, Policy.class, ExternalSystem.class),
        DomainEvent.class, List.of(Aggregate.class, ExternalSystem.class),
        ExternalSystem.class, List.of(Person.class, Event.class),
        Person.class, List.of(ReadModel.class),
        Policy.class, List.of(DomainEvent.class, Person.class, ReadModel.class),
        ReadModel.class, List.of(Event.class)
    );

    @Override
    public boolean canAddVertex(Vertex vertex) {
        return recognizedTypeOf(vertex).isPresent();
    }

    private Optional<Class<? extends Vertex>> recognizedTypeOf(Vertex vertex) {
        return allowedPredecessors.keySet()
                .stream()
                .filter(isInstance(vertex))
                .findAny();
    }

    private Predicate<Class<? extends Vertex>> isInstance(Vertex vertex) {
        return type -> type.isInstance(vertex);
    }

    @Override
    public boolean canAddEdge(Edge edge) {
        return recognizedTypeOf(edge.to())
                .map(allowedPredecessors::get)
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(isInstance(edge.from()));
    }

}
