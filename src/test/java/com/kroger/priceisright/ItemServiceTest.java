package com.kroger.priceisright;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;

import static org.junit.Assert.*;

import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

@RunWith(SpringRunner.class)
@RestClientTest(ItemService.class)
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void itemServiceShouldFetchItems() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SearchResponse responseFromService = new SearchResponse();
        responseFromService.setItems(new ArrayList<Item>());
        responseFromService.getItems().add(new Item("123", "milk", 1.99f));
        String json = mapper.writeValueAsString(responseFromService);
        this.server.expect(requestTo("https://ecsb-dev.kroger.com/item-discovery/items/search?div=014&store=00301&count=20&q=milk"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
        SearchResponse searchResponse = itemService.search("milk");
        assertEquals(1, searchResponse.getItems().size());
        assertEquals(new Float(1.09f), searchResponse.getItems().get(0).getPrice());
    }
}
