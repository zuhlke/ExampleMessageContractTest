import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws IOException {
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }

        try {
            for (int i = 0; i < 1000000; i++) {
                producer.send(new ProducerRecord<>(
                        "fast-messages",
                        String.format(Locale.US, "{\"type\":\"test\", \"time\":%.3f, \"id\":%d}", System.nanoTime() * 1e-9, i)));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
