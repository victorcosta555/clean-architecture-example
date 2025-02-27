package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.application.ports.out.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.coffeeshop.application.textFitures.order.OrderTestFactory.anOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestResourceTest
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Orders orders;

    @Test
    void payOrder() throws Exception {
        var order = orders.saveOrder(anOrder());

        mockMvc.perform(put("/api/v1/payment/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                         {
                            "cardHolderName": "Michael Faraday",
                            "cardNumber": "11223344",
                            "expiryMonth": 12,
                            "expiryYear": 2023
                        }
                        """))
                .andExpect(status().isOk());
    }
}
