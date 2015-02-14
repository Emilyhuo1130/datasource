package com.zhang.aws.util;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
public class AmazonFactory {
	static Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
	/***
	 * @return AmazonSQS
	 * */
	public static AmazonSQS getAmazonSQS(){
		AmazonSQS sqs = new AmazonSQSClient(CredentialsUtil.getCredentials());
		sqs.setRegion(region);
		return sqs;
	}
	/***
	 * @return AmazonS3
	 * */
	public static AmazonS3 getAmazonS3(){
		AmazonS3 s3=getAmazonS3Client();
		return s3;
	}
	/**
	 * @return AmazonS3Client
	 * */
	public static AmazonS3Client getAmazonS3Client(){
		return new AmazonS3Client(CredentialsUtil.getCredentials());
	}
	/***
	 * @return AmazonDynamoDBClient
	 * */
	public static AmazonDynamoDBClient getAmazonDynamoDBClient(){
		AmazonDynamoDBClient dynamoDBClienr= new AmazonDynamoDBClient(CredentialsUtil.getCredentials());
		dynamoDBClienr.setRegion(region);
		return dynamoDBClienr;
	}
	/***
	 * @return AmazonDynamoDB
	 * */
	public static AmazonDynamoDB getAmazonDynamoDB(){
		AmazonDynamoDB dynamoDB =getAmazonDynamoDBClient();
		return dynamoDB;
	}
}
