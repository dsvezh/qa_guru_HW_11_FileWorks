import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipArchiveParsingTest {

    ClassLoader cl = ZipArchiveParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого csv-файла в архиве")
    void csvParsingTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("examples.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[0]).contains("Архангельск");
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого pdf-файла в архиве")
    void pdfParsingTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("examples.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("СОГЛАСИЕ КЛИЕНТА");
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого xlsx-файла в архиве")
    void xlsxParsingTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("examples.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(0).getCell(0)
                            .getStringCellValue()).contains("Шифр, наименование методики измерений");
                }
            }
        }
    }
}
