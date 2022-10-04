package ru.example.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.example.starter.PrintMethodTime;

@RestController
@RequestMapping("/api")
@Tag(name = "HelloController", description = "The Hello API")
public class HelloController {

    volatile private String hello = "hello";

    @PrintMethodTime
    @GetMapping(value = "/{name}", consumes = "application/json")
//    @Operation(summary = "Get hello with your name", tags = "name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Some 200 description",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(description = "description", defaultValue = "default value"),
                                    examples = {@ExampleObject(name = "my example 1", description = "my description 1"),
                                            @ExampleObject(name = "my example 2", description = "my description 2")})
                    })
            ,@ApiResponse(
                    responseCode = "400",
                    description = "Some 400 description",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(description = "error description", defaultValue = "default error value"),
                                    examples = {@ExampleObject(name = "error example 1", description = "error description 1"),
                                            @ExampleObject(name = "error example 2", description = "error description 2")})
                    })
    })
    public ResponseEntity<String> hello(@PathVariable String name) {
        return ResponseEntity.ok(hello + " " + name);
    }

    @PrintMethodTime
    @PostMapping
    public ResponseEntity<String> changeHello(@NonNull @RequestBody String body) {
        hello = body;
        return ResponseEntity.ok(hello);
    }
}
