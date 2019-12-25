package guru.springframework.springbootcassandra.domain;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Table
public class Product implements Serializable {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    public Product() {
        id = UUID.randomUUID();
    }

}

