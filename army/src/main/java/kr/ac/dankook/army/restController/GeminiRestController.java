package kr.ac.dankook.army.restController;

import kr.ac.dankook.army.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class GeminiRestController {

    private final GeminiService geminiService;

    @PostMapping("/question")
    public ResponseEntity<?> getAIResponse(@RequestParam("question") String question) {
        try {
            return ResponseEntity.ok().body(geminiService.getContents(question));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
