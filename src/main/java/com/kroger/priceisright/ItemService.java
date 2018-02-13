package com.kroger.priceisright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ItemService {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String BANNER_HEADER = "X-Banner";

    private RestTemplate restTemplate;

    @Autowired
    public ItemService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public SearchResponse search(String query) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(CORRELATION_ID_HEADER, "12345");
        headers.add(BANNER_HEADER, "Kroger");
        ResponseEntity<SearchResponse> results = restTemplate.exchange(
                URI.create("https://ecsb-dev.kroger.com/item-discovery/items/search?div=014&store=00301&count=20&q=" + query),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                SearchResponse.class);
//        results.add(new Item("butter"));
//        results.add(new Item("sugar"));
        return results.getBody();

    }
}
