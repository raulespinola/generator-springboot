package <%= packageName %>.service;

import <%= packageName %>.entity.<%= entityName %>;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.opentracing.Span;
import io.opentracing.Tracer;

import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class <%= entityName %>Service {

    private Map<Integer, <%= entityName %>> <%= entityVarName %>Map = new HashMap<>();

    private Tracer <%= entityVarName %>Tracer;

    //Add Service
    public <%= entityName %>Service(Tracer <%= entityVarName %>Tracer) {
        this.<%= entityVarName %>Tracer = <%= entityVarName %>Tracer;}
    
    /*----------------------------------------------------------------*/

    // Create
    public boolean add<%= entityName %>(<%= entityName %> <%= entityVarName %>) {
        if (<%= entityVarName %>Map.containsKey(<%= entityVarName %>.get<%= entityName %>Id())) {
            return false;
        } else {
            <%= entityVarName %>Map.put(<%= entityVarName %>.get<%= entityName %>Id(), <%= entityVarName %>);
            return true;
        }
    }

    //-------------------------------------------------------//

    // Read One
    public Optional<<%= entityName %>> get<%= entityName %>(int id) {
        return Optional.ofNullable(<%= entityVarName %>Map.get(id));
    }

    //---------------------------------------------------------//


    // Read all
    public Collection<<%= entityName %>> findAll<%= entityName %>s() {
        return <%= entityVarName %>Map.values();
    }

    //-------------------------------------------------------------//
    
    // Delete
    public boolean delete<%= entityName %>(int id, Span rootSpan) {

        Span span = <%= entityVarName %>Tracer.buildSpan("service delete <%= entityVarName %>").asChildOf(rootSpan).start();

        boolean result = false;
        if (<%= entityVarName %>Map.containsKey(id)) {
            <%= entityVarName %>Map.remove(id);
            result = true;
        }
        span.finish();
        return result;
    }



}
