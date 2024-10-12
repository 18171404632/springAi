package com.newkey.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


/**
 * Desc: TODO ADD DESC
 * <p>
 * Function:
 * <dl>
 * <dt>核心功能点1</dt>
 * <dd>核心功能点1说明</dd>
 * <dt>核心功能点2</dt>
 * <dd>核心功能点2说明</dd>
 * </dl>
 *
 * @app <服务名称英文缩写>
 * @layer <代码所在分层>
 * @refApp <依赖服务的英文缩写>
 * @author <a href="mailto:qiliu3@chinaums.com">qiliu3</a>
 * @since 2024/10/12
 * @version 2024/10/12
 */
@RestController
@RequestMapping("/ai/")
public class TestStreamController {

    @PostMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> testStream(){
        OpenAiApi openAiApi = new OpenAiApi("https://xiaoai.plus", "sk-y8wsKGMEytYXYV4aC009D5Ca1c2b447a99E07d5951953d09");
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106).build());

        ChatClient chatClient = ChatClient.builder(chatModel).build();

        String s = "给我制定一个详细的一周锻炼计划，精确到每一天";



        //CassandraChatMemory.create(CassandraChatMemoryConfig.builder().withTimeToLive(Duration.ofDays(1)).build());

        Flux<String> content = chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor(
                        request -> "Custom request: " + request.userText(),
                        response -> "Custom response: " + response.getResult()))
                .user(s).stream().content();
        return content;
    }
}
