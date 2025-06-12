package com.jobportal.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.service.QnAService;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/qna")
@AllArgsConstructor
public class ChatbotAPI {

	private final QnAService qnAService;

	@PostMapping("/ask")
	public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload) {
		String question = payload.get("question");
		String answer = qnAService.getAnswer(question);

		return ResponseEntity.ok(answer);
	}
}
