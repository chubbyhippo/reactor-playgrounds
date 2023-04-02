package org.example.service;

import org.junit.jupiter.api.Test;

class MonoServiceTest {

    @Test
    void shouldReturnNameMono() {
        var service = new MonoService();
        service.nameMono()
                .subscribe(System.out::println);
    }

}