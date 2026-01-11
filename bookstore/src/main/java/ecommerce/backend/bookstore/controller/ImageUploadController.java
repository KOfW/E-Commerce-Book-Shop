//package ecommerce.backend.bookstore.controller;
//
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.Instant;
//
//@RestController
//@RequestMapping("/api/upload")
//@CrossOrigin
//public class ImageUploadController {
//
//    @Value("${image.upload.directory}")
//    private String imageDirectory;
//
//    @RequestMapping("/image")
//    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file,
//                                              @RequestParam("imageName") String name) {
//        makeDirectoryIfNotExist(imageDirectory);
//
//        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//        if (!isValidExtension(extension)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid file type. Allowed types: jpg, png, jpeg.");
//        }
//
//        String uniqueName = name + "_" + Instant.now().toEpochMilli() + "." + extension;
//        Path fileNamePath = Paths.get(imageDirectory, uniqueName);
//
//        try {
//            Files.write(fileNamePath, file.getBytes());
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Uploaded image: " + fileNamePath.getFileName());
//        } catch (IOException e) {
//            e.printStackTrace(); // Log the exception
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Image upload failed: " + e.getMessage());
//        }
//    }
//
//    private void makeDirectoryIfNotExist(String imageDirectory) {
//        File directory = new File(imageDirectory);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//    }
//
//    private boolean isValidExtension(String extension) {
//        return extension.equalsIgnoreCase("jpg") ||
//                extension.equalsIgnoreCase("png") ||
//                extension.equalsIgnoreCase("jpeg");
//    }
//}