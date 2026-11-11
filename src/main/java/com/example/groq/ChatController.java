package com.example.groq;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class ChatController {

	private final RestClient client;
	private final String model;

	public ChatController(@Value("${groq.api-key}") String apiKey, @Value("${groq.model}") String model) {
		this.model = model;
		this.client = RestClient.builder().baseUrl("https://api.groq.com/openai/v1")
				.defaultHeader("Authorization", "Bearer " + apiKey).build();
	}

	record ChatRequest(String message) {
	}

	@PostMapping("/chat")
	public Map<String, String> chat(@RequestBody ChatRequest req) {
		Map<String, Object> body = Map.of("model", model, "messages",
				List.of(Map.of("role", "user", "content", req.message())));

		Map<?, ?> res = client.post().uri("/chat/completions").body(body).retrieve().body(Map.class);

		Map<?, ?> msg = (Map<?, ?>) ((Map<?, ?>) ((List<?>) res.get("choices")).get(0)).get("message");
		return Map.of("reply", (String) msg.get("content"));
	}
}
