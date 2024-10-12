package com.newkey.ai.dao;

import com.newkey.ai.entity.OpenAiChatEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**

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
public interface OpenAiChatRepository extends MyJpaRepository<OpenAiChatEntity, String>{

    /**
     * 根据用户id查询所有会话
     * @param userId
     * @return
     */
    List<OpenAiChatEntity> findAllByUserId(String userId);


    /**
     * 保存数据
     * @param entity
     * @return
     */
    @Override
    @Transactional(value = "transactionManagerCur")
    OpenAiChatEntity saveAndFlush(OpenAiChatEntity entity);
}
