package com.tratao.payout;

import com.tratao.payout.models.*;
import com.tratao.xcore.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
        // TODO:: change to your config
        Config config = new Config("4de461c592274d65b0bc93a330062d9e", "4de461c592274d65b0bc93a330062d9e", "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");
        client = new Client(config);
        client.setHost("https://api-sandbox.xcurrency.com");
    }

    @Test
    public void testRate() throws Exception {
        GetRateRequest request = new GetRateRequest();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("CNY");
        RateResponseData response = client.getRate(request);

        Assertions.assertTrue(response.getRate() > 0);
    }

    @Test
    public void testPBCAreaList() {
        List<PBCAreaResponseData> data = client.getPBCAreaListData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void testOccupation() {
        List<OccupationResponseData> data = client.getOccupationData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void testCreateTransferSuccess() {
        CreateTransferRequest request = new CreateTransferRequest();

        client.createTransfer(request);
    }
}
