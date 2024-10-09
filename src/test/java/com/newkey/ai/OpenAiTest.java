package com.newkey.ai;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OpenAiTest.class)
@RunWith(SpringRunner.class)
public class OpenAiTest {


    @Test
    public void testOpenAi(){

        OpenAiChatModel chatModel = new OpenAiChatModel(new OpenAiApi("https://xiaoai.plus", "sk-y8wsKGMEytYXYV4aC009D5Ca1c2b447a99E07d5951953d09"));

        String s = "如何查看你是chatGpt的哪个版本？";
        String call = chatModel.call(s);
        System.out.println(call);
    }
}
