package com.gameexpert.ws.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gameexpert.api.SessionRegistry;
import com.gameexpert.ws.NicknameHandshakeInterceptor;
import com.gameexpert.ws.WorldBroadcaster;
import com.gameexpert.ws.WorldSessionRegistry;
import com.gameexpert.ws.WsMessageContext;
import com.gameexpert.ws.dto.OnlineUsersResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import tools.jackson.databind.JsonNode;

@Component
@RequiredArgsConstructor
public class OnlineUsersWsHandler implements WsMessageHandler {
    private final WorldSessionRegistry registry;
    private final WorldBroadcaster broadcaster;

    @Override
    public String type() {
        return "onlineUsers";
    }

    @Override
    public void handle(WsMessageContext context, JsonNode message) {
        // TODO Lv 15: 현재 월드의 열린 연결에서 닉네임을 조회하고 요청자에게 응답합니다.

        // 현재 월드의 모들 연결 조회
        Collection<SessionRegistry.Entry> sesseions = registry.entries(context.worldId());

        // 접속자 목록
        List<String> users = new ArrayList<>();

        // 열린 세션 확인
        for(SessionRegistry.Entry entry : sesseions){
            WebSocketSession session = entry.session();

            if(!session.isOpen())
                continue;

            // attribute에 닉네임 조회
            Object nickname = session.getAttributes()
                    .get(NicknameHandshakeInterceptor.ATTR_NICKNAME);

            if (nickname != null)
                users.add(nickname.toString());
        }

        // 닉네임 정렬
        users.sort(String::compareTo);
        // 응답 DTO 생성
        OnlineUsersResponse response = new OnlineUsersResponse(users, users.size());
        // 요청 클라이언트 응답
        broadcaster.sendTo(context.session(), response);
    }
}
