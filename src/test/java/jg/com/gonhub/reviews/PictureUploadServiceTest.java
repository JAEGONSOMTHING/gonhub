package jg.com.gonhub.reviews;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;


@SpringBootTest
class PictureUploadServiceTest {
    @Autowired
    PictureUploadService pictureUploadService;
    @Autowired
    PictureRepository pictureRepository;


    @Test
    @DisplayName("multipartfile 업로드 테스트")
    void uploadTest(){
        MockMultipartFile mockMultipartFile = new MockMultipartFile("testfile", "file.txt","text/plain","test".getBytes(StandardCharsets.UTF_8));
        Long id = pictureUploadService.pictureUpload(null, mockMultipartFile);
        Picture picture = pictureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));
        Assertions.assertThat(picture.getOriginalName()).isEqualTo("file.txt");
        File f = new File(picture.getFilepath());
        Assertions.assertThat(f.exists()).isTrue();
        f.delete();
    }


}