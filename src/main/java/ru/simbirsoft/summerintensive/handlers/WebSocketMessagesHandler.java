package ru.simbirsoft.summerintensive.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.models.Pixel;

import javax.mail.Message;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    public void sendPixel(PixelDto pixelDto) throws JsonProcessingException {
        WebSocketMessage<String> result = new TextMessage(objectMapper.writeValueAsString(pixelDto));
        for (WebSocketSession currentSession : sessions.values()) {
            try {
                currentSession.sendMessage(result);
            } catch (IOException e) {
                System.err.println("Ошибка отправки данных через сокет");
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }
}
