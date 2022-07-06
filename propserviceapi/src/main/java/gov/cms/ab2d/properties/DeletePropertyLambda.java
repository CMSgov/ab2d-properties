package gov.cms.ab2d.properties;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static gov.cms.ab2d.properties.Constants.KEY_COL;
import static gov.cms.ab2d.properties.Constants.TABLE_NAME;

public class DeletePropertyLambda {

    private ObjectMapper mapper = new ObjectMapper();

    private DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    public APIGatewayProxyResponseEvent deleteProperty(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        String key = request.getPathParameters().get(KEY_COL);
        System.out.println(key);

        DeleteItemSpec itemSpec = new DeleteItemSpec().withPrimaryKey(KEY_COL, key);
        Table table = dynamoDB.getTable(TABLE_NAME);
        DeleteItemOutcome outcome = table.deleteItem(itemSpec);

        StatusResponseDto results = new StatusResponseDto(200, "Property deleted: " + key);

        String jsonOutput = mapper.writeValueAsString(results);

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(jsonOutput);
        return responseEvent;
    }
}
