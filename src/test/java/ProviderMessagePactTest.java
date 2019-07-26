import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.MessagePactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import model.Message;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

import static java.util.Collections.singletonList;

@RunWith(MessagePactRunner.class)
@Provider("Producer")
@PactFolder("pacts")
public class ProviderMessagePactTest {

    @BeforeClass
    public static void startProvider() throws IOException {
        Producer.main(new String[]{});
    }

    @PactVerifyProvider("Producer")
    String verifyMessageForOrder() {
        Message message = new Message("type", "time", 0);
        return "";
    }

    @TestTarget
    public final Target target = new AmqpTarget(singletonList(this.getClass().getPackage().getName() + ".*"));

    @State("state")
    public void providerTest() {
    }
}
