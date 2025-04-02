import com.example.demo.dao.VkBotDao;
import com.example.demo.services.VkBotService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

public class VkBotServiceTest {

    @Mock
    private VkBotDao vkBotDao;

    @InjectMocks
    private VkBotService vkBotService;

    private final String configuredToken = "vk1.a.x65f4OryBB33tPfySpJ3YWx7wHZjY31gms1FYmE0O6rjuPKG4ppR-SeOmNvL5BbFGQXWymU0XQ3z7E4zHvq68IKQuEVmLr8fEVMBSmG48x0pBluneN-g_1zQFXUfwk37T8VjEDzYeyrIKZN-GF6ofCX2qpx2WknBtFx-K2Bzdt1k74rslrttSNcVOqr-eLmF9UMOixBNBOnnNUSN4bz7tA";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(vkBotService, "vkApiToken", configuredToken);
    }

    @Test
    public void testHandleNewMessage() throws JSONException {
        String jsonStr = "{\"object\": {\"message\": {\"from_id\": 12345, \"text\": \"Привет\"}}}";
        JSONObject jsonObject = new JSONObject(jsonStr);
        vkBotService.handleNewMessage(jsonObject);

        verify(vkBotDao, times(1)).sendMessage(
                eq("12345"),
                eq("Привет"),
                eq(configuredToken)
        );
    }
}
