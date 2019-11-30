package aoc2019

@Grab('org.springframework.boot:spring-boot-starter-webflux:2.2.1.RELEASE')

import java.util.concurrent.CompletableFuture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.web.context.WebServerApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

import reactor.core.publisher.Mono

@SpringBootApplication
class DemoApplication {

    // tag::helloWorld[]
    @RestController
    static class HelloWorldController {

        @GetMapping("/")
        def index() {
            Mono.just("Hello, World")
        }
    }
    // end::helloWorld[]

    // tag::callingTheGreeter[]
    static class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

        @Override
        void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

            def applicationContext = applicationReadyEvent.getApplicationContext();

            def port = ((WebServerApplicationContext) applicationContext).getWebServer().getPort();            
            def webClient = WebClient.builder().baseUrl(sprintf("http://localhost:%d", port)).build();
            webClient
                    .get().uri("/")
                    .exchange()
                    .flatMap({ r -> r.bodyToMono(String.class) })
                    .doAfterTerminate({ -> CompletableFuture.runAsync(applicationContext.&close) })
                    .subscribe(System.out.&println)
        }
    }
    // end::callingTheGreeter[]

    // tag::puttingItTogether[]
    static void main(String[] args) {

        def builder = new SpringApplicationBuilder(DemoApplication.class)
        builder
                .listeners(new ApplicationReadyListener())
                .run(args)
    }
    // end::puttingItTogether[]
}
