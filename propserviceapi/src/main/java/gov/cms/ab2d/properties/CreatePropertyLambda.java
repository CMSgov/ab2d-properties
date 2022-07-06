package gov.cms.ab2d.properties;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static gov.cms.ab2d.properties.Constants.KEY_COL;
import static gov.cms.ab2d.properties.Constants.TABLE_NAME;
import static gov.cms.ab2d.properties.Constants.VAL_COL;

public class CreatePropertyLambda {
    private ObjectMapper mapper = new ObjectMapper();
    private DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    public APIGatewayProxyResponseEvent createProperty(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        PropertyDto property = mapper.readValue(request.getBody(), PropertyDto.class);

        Table table = dynamoDB.getTable(TABLE_NAME);
        Item item = new Item().withPrimaryKey(KEY_COL, property.getKey())
                .withString(VAL_COL, property.getValue());
        table.putItem(item);

        StatusResponseDto results = new StatusResponseDto(200, "Property created: " + property.getKey());
        String jsonOutput = mapper.writeValueAsString(results);
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(jsonOutput);
        return responseEvent;
    }
}
