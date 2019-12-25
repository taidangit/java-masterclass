package guru.springframework.springbootredis.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Getter
@Setter
@RedisHash
public class Product {

    @Id
    private String id;

    private String description;

    private BigDecimal price;

    private String imageUrl;

}

