package ru.bank.account;

import com.sun.tools.classfile.ConstantPool;
import org.bitbucket.radistao.test.annotation.BeforeAllMethods;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bank.account.entity.JPA.AccountRepo;
import ru.bank.account.generate.GenerateDao;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestTransferP2P {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    GenerateDao generateDao;

    @Autowired
    AccountRepo accountRepo;


    @BeforeAllMethods
    public void init() {
        generateDao.generateDao();
    }

    @Test
    public void shouldBeOk() throws Exception {
        this.mockMvc.perform(post("/transferMoney").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"rquid\": \"1234567\", \n" +
                        "\t\"accountNumberFrom\": \"02345678901234567890\", \n" +
                        "\"accountNumberTo\": \"12345678901234567890\",\n" +
                        "\t\"money\": 10\n" +
                        "}"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldBeClientError() throws Exception {
        this.mockMvc.perform(post("/transferMoney").contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountNumberTo\": \"12345678901234567890\",\n" +
                        "\t\"money\": 100\n" +
                        "}"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}