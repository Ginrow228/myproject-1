import jdbc.NetworkDao;
import jdbc.NetworksDao;
import model.Network;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class NetworkTest {

    @Test
    public void testNetworkSave() throws ClassNotFoundException, SQLException {
        var networkToSave = new Network("some_name", "some_description");
        var networkService = new NetworksDao();
        networkService.save(networkToSave);
    }
}
