package com.kroger.priceisright;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ActiveProfiles(profiles = { "unittest" })
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class })
public abstract class PriceisrightApplicationWebTests extends PriceisrightApplicationTests
{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).defaultRequest(get("/")).build();
    }

    public MockMvc getMockMvc()
    {
        return mockMvc;
    }
}