package com.newkey.ai;

import com.newkey.ai.dao.OpenAiChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAiChat {

    @Autowired
    private OpenAiChatRepository openAiChatRepository;


}
