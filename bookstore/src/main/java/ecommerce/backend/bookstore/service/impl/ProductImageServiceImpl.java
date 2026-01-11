package ecommerce.backend.bookstore.service.impl;

import ecommerce.backend.bookstore.dto.request.ProductImageRequest;
import ecommerce.backend.bookstore.dto.response.ProductImageResponse;
import ecommerce.backend.bookstore.entity.ProductImage;
import ecommerce.backend.bookstore.mapper.ProductImageMapper;
import ecommerce.backend.bookstore.repository.ProductImageRepo;
import ecommerce.backend.bookstore.service.IProductImageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

@Service
public class ProductImageServiceImpl implements IProductImageService {

    @Value("${image.upload.directory}")
    private String imageDirectory;
    private final ProductImageRepo productImageRepo;
    private final ProductImageMapper imageMapper;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepo productImageRepo, ProductImageMapper imageMapper) {
        this.productImageRepo = productImageRepo;
        this.imageMapper = imageMapper;
    }


    @Override
    public ProductImageResponse uploadImage(ProductImageRequest request, MultipartFile file) throws IOException {

        makeDirectoryIfNotExist(imageDirectory);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!isValidExtension(extension)) {
            throw new RuntimeException("Invalid file type. Allowed types: jpg, png, jpeg.");
        }

        String uniqueName = request.getName() + "_" + Instant.now().toEpochMilli() + "." + extension;
        Path fileNamePath = Paths.get(imageDirectory, uniqueName);

        Files.write(fileNamePath, file.getBytes());

        ProductImage productImage = imageMapper.toEntity(request);
        productImage.setImageUrl(imageDirectory+"/"+fileNamePath.getFileName().toString());

        productImageRepo.save(productImage);

        return imageMapper.toDTO(imageMapper.toEntity(request));
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private boolean isValidExtension(String extension) {
        return extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("png") ||
                extension.equalsIgnoreCase("jpeg");
    }

    @Override
    public boolean deleteImage(Long productId, Long imageId) {
        // Logic to delete image
        return true;
    }

    @Override
    public void updateImage(Long productId, Long imageId, String newImageUrl) {
        // Logic to update image
    }

    @Override
    public String getImageUrl(Long productId, Long imageId) {
        // Logic to get image URL
        return null;
    }

    @Override
    public List<String> getAllImages(Long productId) {
        // Logic to get all images for a product
        return null;
    }

    @Override
    public void deleteAllImages(Long productId) {
        // Logic to delete all images for a product
    }
}
