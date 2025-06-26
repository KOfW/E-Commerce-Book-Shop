package ecommerce.backend.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base {
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
}
