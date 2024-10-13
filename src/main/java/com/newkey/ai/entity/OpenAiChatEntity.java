/**
 * Project:TODO ADD PROJECT NAME
 * Modify Information:
 * ================================================================
 * Author         Date           Description
 * ------------   ----------      --------------------------------
 * qiliu3        2024/10/12         TODO:
 * ================================================================
 * Copyright (c) 银联商务股份有限公司 www.chinaums.com
 */
package com.newkey.ai.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_openai_chat_info")
public class OpenAiChatEntity {

    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会话id
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 会话title
     */

    @Column(name = "title")
    private String title;


    /**
     * 会话内容
     */

    @Column(name = "message")
    private String message;

}
