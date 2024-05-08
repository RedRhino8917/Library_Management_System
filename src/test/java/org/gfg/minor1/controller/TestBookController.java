package org.gfg.minor1.controller;

import org.gfg.minor1.model.Book;
import org.gfg.minor1.service.BookService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {BookController.class})
public class TestBookController {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBook() throws Exception {
        when(bookService.createBook(any())).thenReturn(new Book());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "book");
        jsonObject.put("cost", "100");
        jsonObject.put("bookNo", "no");
        RequestBuilder requestBuilder = post("/book/create").contentType(MediaType.APPLICATION_JSON).content(String.valueOf(jsonObject));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
