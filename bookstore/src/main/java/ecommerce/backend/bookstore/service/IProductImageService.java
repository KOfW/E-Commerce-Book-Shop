package ecommerce.backend.bookstore.service;

import ecommerce.backend.bookstore.dto.request.ProductImageRequest;
import ecommerce.backend.bookstore.dto.response.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductImageService {
    public ProductImageResponse uploadImage(ProductImageRequest request, MultipartFile file) throws IOException;
    public boolean deleteImage(Long productId, Long imageId);
    public void updateImage(Long productId, Long imageId, String newImageUrl);
    public String getImageUrl(Long productId, Long imageId);
    public List<String> getAllImages(Long productId);
    public void deleteAllImages(Long productId);
}
