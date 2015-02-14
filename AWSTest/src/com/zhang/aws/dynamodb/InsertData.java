package com.zhang.aws.dynamodb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.zhang.aws.util.AmazonFactory;

public class InsertData {
	static Logger logger=Logger.getLogger(InsertData.class);
	static AmazonDynamoDBClient dynamoDB=AmazonFactory.getAmazonDynamoDBClient();
	static String tableName="ProductCatalog";
	public static void main(String[] args) {
//		putitem();

//		getitem();
		
		updateItem();
	}
	/***
	 * 添加数据
	 * **/
	public static void putitem(){
		Map<String,AttributeValue> item=new HashMap();
		item.put("id", new AttributeValue().withS("102"));
		item.put("name", new AttributeValue().withS("lisi"));
		item.put("age", new AttributeValue().withS("34"));
		item.put("address", new AttributeValue().withS("中心信息发展"));
		PutItemResult putItem = dynamoDB.putItem(new PutItemRequest()
		.withTableName(tableName)
		.withItem(item)
		);
		logger.info(""+putItem.getAttributes());
	}
	
	/***
	 * 获取数据
	 * **/
	public static void getitem(){
		Map<String,AttributeValue> key=new HashMap();
		key.put("id", new AttributeValue().withS("102"));
		GetItemResult item = dynamoDB.getItem(tableName, key);
		Map<String, AttributeValue> item2 = item.getItem();
		for(Entry<String, AttributeValue> i:item2.entrySet()){
			logger.info("key:   "+i.getKey()+"     value:"+i.getValue());
		}
	}
	
	/**
	 * 更新数据
	 * ***/
	public static void updateItem(){
		Map<String,AttributeValue> key=new HashMap();
		key.put("id", new AttributeValue().withS("101"));
		Map<String, AttributeValueUpdate> attributeUpdates=new HashMap();
		attributeUpdates.put("age", new AttributeValueUpdate().withValue(new AttributeValue().withS("33")));
		attributeUpdates.put("address",new AttributeValueUpdate().withValue( new AttributeValue().withS("中江路")));
		UpdateItemResult updateItem = dynamoDB.updateItem(tableName, key, attributeUpdates);
		logger.info("------------update--------------");
	}

}
