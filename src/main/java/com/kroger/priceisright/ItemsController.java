package com.kroger.priceisright;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    private ItemService itemService;

    @RequestMapping("/items")
    public SearchResponse getItems(@RequestParam("query") String query) {
        return getItemService().search(query);
    }
}
