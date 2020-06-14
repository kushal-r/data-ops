package com.criticalmass.datamatrix.helper;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Component
public class FileHelper {

  /* ---------------- Public Methods ---------------- */

  public void multipartFileToFile(MultipartFile multipartFile, Path dir) throws IOException {
    Path filePath = Paths.get(dir.toString(), multipartFile.getOriginalFilename());
    multipartFile.transferTo(filePath);
  }

  public boolean isDirEmpty(final Path directory) throws IOException {
    try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
      return !dirStream.iterator().hasNext();
    }
  }

}
