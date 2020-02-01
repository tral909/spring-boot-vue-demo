package ru.indorm1992.tony.springbootvuedemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.indorm1992.tony.springbootvuedemo.dto.EventType;
import ru.indorm1992.tony.springbootvuedemo.dto.ObjectType;
import ru.indorm1992.tony.springbootvuedemo.dto.WsEventDto;

import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
public class WsSender {
	private final SimpMessagingTemplate template;
	private final ObjectMapper mapper;

	// При отправке данных через web sockets, не видно аннотацию JsonView, поэтому подложим ее здесь Class view
	public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
		ObjectWriter writer = mapper
				.setConfig(mapper.getSerializationConfig())
				.writerWithView(view);

		return (EventType eventType, T payload) -> {
			String value;
			try	{
				value = writer.writeValueAsString(payload);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			template.convertAndSend(
					"/topic/activity",
					new WsEventDto(objectType, eventType, value));
		};
	}
}
