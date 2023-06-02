package com.example.animal_adoption;

import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.service.impl.CartImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnimalAdoptionApplicationTests {

    @Test
    void contextLoads() {
        StringBuffer temp = new StringBuffer("123");
        System.out.println(temp);
        System.out.println(temp.toString());
    }
}
