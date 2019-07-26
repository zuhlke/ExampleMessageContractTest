import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.MessagePactProviderRule;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Message;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MessagePactTest {

    @Rule
    public MessagePactProviderRule rule = new MessagePactProviderRule("Provider", this);

    @Pact(provider = "Provider", consumer = "Consumer")
    public MessagePact createPact(MessagePactBuilder builder) {
        DslPart body = new PactDslJsonBody()
                .stringType("type")
                .stringType("time")
                .integerType("id");

        return builder.given("state")
                .expectsToReceive("raul's message")
                .withContent(body)
                .toPact();
    }

    @Test
    @PactVerification(value = {"Provider", "state"}, fragment = "createPact")
    public void canParseCreateEvent() throws IOException {
        Message message = new ObjectMapper().readValue(rule.getMessage(), Message.class);
        assertEquals(message,
                new Message("string", "string", 100));
    }
}
