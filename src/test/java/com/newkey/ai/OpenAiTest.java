package com.newkey.ai;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OpenAiTest.class)
@RunWith(SpringRunner.class)
@Slf4j
public class OpenAiTest {


    @Test
    public void testOpenAi(){


        OpenAiApi openAiApi = new OpenAiApi("https://xiaoai.plus", "sk-y8wsKGMEytYXYV4aC009D5Ca1c2b447a99E07d5951953d09");
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106).build());

        ChatClient chatClient = ChatClient.builder(chatModel).build();

        String s = "鲁迅和周树人是什么关系？";

        ChatClient.CallResponseSpec call = chatClient.prompt().advisors(new SimpleLoggerAdvisor(
                request -> "Custom request: " + request.userText(),
                response -> "Custom response: " + response.getResult()
        )).user(s).call();

        System.out.println(call.content());
    }
}
