package <%= packageName %>.web.controller;

import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.service.<%= entityName %>Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.ImmutableMap;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.NoSuchElementException;

import io.opentracing.Span;
import io.opentracing.Tracer;
import java.util.List;

@RestController
@RequestMapping("<%= basePath %>")
@Slf4j
@Api(value = "<%= entityName %>", description = "<%= entityName %> service")
public class <%= entityName %>Controller {

    private final <%= entityName %>Service <%= entityVarName %>Service;

    private Tracer <%= entityVarName %>Tracer;

    @Autowired
    public <%= entityName %>Controller(Tracer  <%= entityVarName %>Tracer,  
        <%= entityName %>Service <%= entityVarName %>Service) {
        this.<%= entityVarName %>Tracer = <%= entityVarName %>Tracer;
        this.<%= entityVarName %>Service = <%= entityVarName %>Service;
    }
    //----------------------------------------------------------//


    public void init() {

        // Add some <%= entityName %>s
        <%= entityVarName %>Service.add<%= entityName %>(<%= entityName %>.builder()
                .<%= entityVarName %>Id(1)
                .field1("Peres")
                .field2("John")
                .build());

        <%= entityVarName %>Service.add<%= entityName %>(<%= entityName %>.builder()
                .<%= entityVarName %>Id(2)
                .field1("Doe")
                .field2("Juan")
                .build());
    }

    //---------------------------------------------------------------//

    @ApiOperation(value = "Get <%= entityName %> ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/tutorial/1.0/<%= entityVarName %>s/{id}", method = RequestMethod.GET)
    public ResponseEntity get<%= entityName %>(@PathVariable("id") String idString) {

        <%= entityName %> <%= entityVarName %> = null;
        HttpStatus status = HttpStatus.NOT_FOUND;

        Span <%= entityVarName %>Span = <%= entityVarName %>Tracer.buildSpan("get <%= entityVarName %>").start();

        try {
            int id = Integer.parseInt(idString);
            log.info("Received Request for <%= entityVarName %> {}", id);
            <%= entityVarName %> = <%= entityVarName %>Service.get<%= entityName %>(id)
                    .orElseThrow(() -> new NoSuchElementException("<%= entityName %> not found."));

            status = HttpStatus.OK;

        } catch (NumberFormatException | NoSuchElementException nfe) {
            log.error("Error getting <%= entityVarName %>: ", nfe);
        }
        <%= entityVarName %>Span.finish();
        return new ResponseEntity<>(<%= entityVarName %>, status );
    }

    //-------------------------------------------------------------------//
    // Get All //


    @ApiOperation(value = "Get All <%= entityName %>s ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/tutorial/1.0/<%= entityVarName %>s", method = RequestMethod.GET)
    public ResponseEntity getAll<%= entityName %>s() {

        Span <%= entityVarName %>Span = <%= entityVarName %>Tracer.buildSpan("get <%= entityVarName %>s").start();

        log.info("Receive Request to Get All <%= entityName %>s");
        Collection<<%= entityName %>> <%= entityVarName %>s = 
            <%= entityVarName %>Service.findAll<%= entityName %>s();

            <%= entityVarName %>Span.finish();
        return new ResponseEntity<>(<%= entityVarName %>s, HttpStatus.OK);
    }

    // ------------------------------------------------------//
    // Add new //
    @ApiOperation(value = "Create <%= entityName %>> ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/tutorial/1.0/<%= entityVarName %>s", method = RequestMethod.POST)
    public ResponseEntity create<%= entityName %>(@RequestBody <%= entityName %> <%= entityVarName %>) {

        Span <%= entityVarName %>Span = <%= entityVarName %>Tracer.buildSpan("create <%= entityVarName %>").start();

        HttpStatus status = HttpStatus.FORBIDDEN;

        log.info("Receive Request to add <%= entityVarName %> {}", <%= entityVarName %>);
        if (<%= entityVarName %>Service.add<%= entityName %>(<%= entityVarName %>)) {
            status = HttpStatus.CREATED;
            <%= entityVarName %>Span.setTag("http.status_code", 201);
        } else {
            <%= entityVarName %>Span.setTag("http.status_code", 403);
        }
        <%= entityVarName %>Span.finish();
        return new ResponseEntity(null, status);
    }
}
