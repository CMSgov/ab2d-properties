package gov.cms.ab2d.properties;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static gov.cms.ab2d.properties.Constants.KEY_COL;
import static gov.cms.ab2d.properties.Constants.TABLE_NAME;
import static gov.cms.ab2d.properties.Constants.VAL_COL;

public class ReadPropertyLambda {
    private final ObjectMapper mapper = new ObjectMapper();
    private final AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public APIGatewayProxyResponseEvent readProperty(APIGatewayProxyRequestEvent request) throws JsonProcessingException {
        System.out.println("Getting from table: " + TABLE_NAME);
        ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(TABLE_NAME));
        List<PropertyDto> results = scanResult.getItems().stream()
                .map(item -> new PropertyDto(item.get(KEY_COL).getS(), item.get(VAL_COL).getS()))
                .collect(Collectors.toList());

        String jsonOutput = mapper.writeValueAsString(results);
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(jsonOutput);
        return responseEvent;
    }

    public APIGatewayProxyResponseEvent readSpecificProperty(APIGatewayProxyRequestEvent request) throws JsonProcessingException {
        String key = request.getPathParameters().get(KEY_COL);
        System.out.println(key);
        String value = getValue(key);
        String jsonOutput = mapper.writeValueAsString(new PropertyDto(key, value));
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(jsonOutput);
        return responseEvent;
    }

    public String getValue(String keyVal) {

        HashMap<String, AttributeValue> keyToGet = new HashMap<>();
        AttributeValue value = new AttributeValue();
        value.setS(keyVal);
        keyToGet.put(KEY_COL, value);

        GetItemRequest request = new GetItemRequest().withTableName(TABLE_NAME).withKey(keyToGet);
        String returnValue = "";
        try {
            Map<String, AttributeValue> returnedItem = dynamoDB.getItem(request).getItem();
            if (returnedItem != null) {
                returnValue = returnedItem.get(VAL_COL).getS();
                System.out.println(returnValue);
            } else {
                System.out.format("No item found with the key %s!\n", keyVal);
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
        }
        return returnValue;
    }
}
