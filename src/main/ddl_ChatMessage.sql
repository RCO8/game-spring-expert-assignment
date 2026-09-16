CREATE TABLE chat_messages
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    world_id        BIGINT                NOT NULL,
    sender_nickname VARCHAR(16)           NOT NULL,
    content         VARCHAR(200)          NOT NULL,
    created_at      datetime              NULL,
    CONSTRAINT pk_chat_messages PRIMARY KEY (id)
);