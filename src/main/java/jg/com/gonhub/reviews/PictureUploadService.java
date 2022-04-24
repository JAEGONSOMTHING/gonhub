package jg.com.gonhub.reviews;



import jg.com.gonhub.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PictureUploadService {
    private final PictureRepository pictureRepository;
    private String BASIC_FILE_PATH = "C:\\upload\\";

    @Transactional
    public Long pictureUpload(User user , MultipartFile file){

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID()+"."+extension;

        try (
                FileOutputStream fos = new FileOutputStream(BASIC_FILE_PATH + newFileName);
                InputStream is = file.getInputStream();) {
            int readCount = 0;
            byte[] buffer = new byte[1024];

            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);

            }
        } catch (Exception ex) {
            throw new RuntimeException("file Save Error");
        }

        Picture picture = Picture.builder()
                .originalName(file.getOriginalFilename())
                .filepath(BASIC_FILE_PATH + newFileName).build();
        Picture save = pictureRepository.save(picture);

        return save.getId();
    }

}
