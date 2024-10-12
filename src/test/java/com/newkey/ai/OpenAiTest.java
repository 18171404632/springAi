package com.newkey.ai;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newkey.ai.dao.OpenAiChatRepository;
import com.newkey.ai.entity.OpenAiChatEntity;
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
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@SpringBootTest(classes = OpenAiTest.class)
@RunWith(SpringRunner.class)
@Slf4j
public class OpenAiTest {


    @Autowired
    private OpenAiChatRepository openAiChatRepository;

    @Test
    public void testOpenAi(){


        OpenAiApi openAiApi = new OpenAiApi("https://xiaoai.plus", "sk-y8wsKGMEytYXYV4aC009D5Ca1c2b447a99E07d5951953d09");
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106).build());

        ChatClient chatClient = ChatClient.builder(chatModel).build();

        String user = "给我制定一个详细的一周锻炼计划，精确到每一天";



        //CassandraChatMemory.create(CassandraChatMemoryConfig.builder().withTimeToLive(Duration.ofDays(1)).build());

        Flux<String> content = chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor(
                        request -> "Custom request: " + request.userText(),
                        response -> "Custom response: " + response.getResult()))
                .user(user).stream().content();

        String assistant = content.collectList().block().stream().collect(Collectors.joining());


        OpenAiChatEntity openAiChatEntity = new OpenAiChatEntity();
        openAiChatEntity.setUserId("124325342");
        openAiChatEntity.setSessionId("51221211");

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        jsonObject.put("assistant", assistant);
        openAiChatEntity.setMessage(jsonArray.toJSONString());

        openAiChatRepository.saveAndFlush(openAiChatEntity);
    }
}
