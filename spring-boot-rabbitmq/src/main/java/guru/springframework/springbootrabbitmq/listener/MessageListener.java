package guru.springframework.springbootrabbitmq.listener;

import guru.springframework.springbootrabbitmq.domain.Product;
import guru.springframework.springbootrabbitmq.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MessageListener {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootRabbitMQApplication} for more details
     * @param message
     */
    public void receiveMessage(Map<String, Long> message) {
        log.info("Received <" + message + ">");
        Long id = message.get("id");
        Product product = productRepository.findById(id).orElse(null);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);
        productRepository.save(product);
        log.info("Message processed...");
    }
}


