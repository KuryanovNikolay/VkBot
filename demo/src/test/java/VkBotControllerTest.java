import com.example.demo.controllers.VkBotController;
import com.example.demo.services.VkBotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VkBotControllerTest {

    @Mock
    private VkBotService vkBotService;

    @InjectMocks
    private VkBotController vkBotController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testHandleCallback_MessageNew() {
        String body = "{\"type\":\"message_new\", \"object\":{\"message\":{\"from_id\":12345, \"text\":\"Привет\"}}}";

        doNothing().when(vkBotService).handleNewMessage(any());

        ResponseEntity<String> response = vkBotController.handleCallback(body);

        verify(vkBotService, times(1)).handleNewMessage(any());
        assertEquals("ok", response.getBody());
    }

    @Test
    public void testHandleCallback_InvalidJson() {
        String body = "{\"type\":\"invalid_type\"}";

        ResponseEntity<String> response = vkBotController.handleCallback(body);
        assertEquals("Unsupported event type", response.getBody());
    }
}
