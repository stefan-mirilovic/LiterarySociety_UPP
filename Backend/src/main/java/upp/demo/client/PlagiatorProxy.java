package upp.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import upp.demo.dto.PlagiarismResultDTO;

@FeignClient(name = "plagiator-service", url = "http://localhost:9091/api")
public interface PlagiatorProxy {
    @PostMapping(value = "/file/upload/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlagiarismResultDTO uploadNewPaper(@RequestPart MultipartFile file);


    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlagiarismResultDTO uploadExistingPaper(@RequestPart MultipartFile file);

    @DeleteMapping(value = "/papers/{id}")
    public void deletePaper(@PathVariable Long id);
}
