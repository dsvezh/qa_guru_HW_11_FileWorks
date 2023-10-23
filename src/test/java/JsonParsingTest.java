import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Beeline;

import java.io.InputStream;
import java.io.InputStreamReader;


public class JsonParsingTest {

    ClassLoader cl = JsonParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка содержимого оыщт-файла")
    void jsonParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("beeline.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Beeline beeline = objectMapper.readValue(reader, Beeline.class);
            Assertions.assertThat(beeline.region.label).isEqualTo("Москва");
            Assertions.assertThat(beeline.tariffs).size().isBetween(1, 5);
            Assertions.assertThat(beeline.tariffs.get(0).name).isEqualTo("для дома 100");
            Assertions.assertThat(beeline.tariffs.get(1).fee.textValue).isEqualTo("на 3 месяца");
        }
    }
}
