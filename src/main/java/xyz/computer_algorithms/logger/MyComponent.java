package xyz.computer_algorithms.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private static final Logger logger = LoggerFactory.getLogger(MyComponent.class);

    public void print(String string) {
        logger.info(string);
    }
}