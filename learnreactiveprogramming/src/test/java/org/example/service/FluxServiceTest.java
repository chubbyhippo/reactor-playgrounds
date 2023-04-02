package org.example.service;

import org.junit.jupiter.api.Test;

class FluxServiceTest {

    @Test
    void shouldReturnNames() {
        var service = new FluxService();
        service.namesFlux()
                .subscribe(System.out::println);
    }

}