package com.kroger.priceisright;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemsController {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String BANNER_HEADER = "X-Banner";

    @RequestMapping("/items")
    public SearchResponse getItems(@RequestParam("query") String query) {
//        List<Item> results = new ArrayList<Item>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(CORRELATION_ID_HEADER, "12345");
        headers.add(BANNER_HEADER, "Kroger");
        ResponseEntity<SearchResponse> results = restTemplate.exchange(
                URI.create("https://ecsb-dev.kroger.com/item-discovery/items/search?div=014&store=00301&count=20&q=" + query + "&sort=-rank"),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                SearchResponse.class);
//        results.add(new Item("butter"));
//        results.add(new Item("sugar"));
        return results.getBody();
    }
}
