CREATE TABLE `tbl_openai_chat_info` (
             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
             `session_id` varchar(20) NOT NULL COMMENT '会话id',
             `user_id` varchar(12) NOT NULL COMMENT '用户id',
             `title` varchar(128) DEFAULT '新的对话' COMMENT '会话title',
             `message` text,
             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
             PRIMARY KEY (`id`),
             UNIQUE KEY `user_id_session_id` (`user_id`, `session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='openAI chat表';
