package com.zhang.aws.sqs;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.zhang.aws.util.AmazonFactory;

public class MessageAttributesTest {
	static Logger logger=Logger.getLogger(MessageAttributesTest.class);
	/****
	 * 如何获取message的Attributes
	 * **/
	public static void main(String[] args) {
		getAttributes();
	}
	
	public static void getAttributes(){
		ReceiveMessageResult receiveMessage = getReceiveMessageResult();
		for(Message message:receiveMessage.getMessages()){
			logger.info("message body:          "+message.getBody());
			logger.info("message receiptHandle: "+message.getReceiptHandle());
			logger.info("attributes..........");
			for(Entry<String,String>entry:message.getAttributes().entrySet()){
				logger.info("key:  "+entry.getKey()+"       value:"+entry.getValue());
			}
			logger.info("message attributes...............");
			Map<String, MessageAttributeValue> messageAttributes = message.getMessageAttributes();
			for(Entry<String,MessageAttributeValue> entry:messageAttributes.entrySet()){
				logger.info("key:  "+entry.getKey()+"       value:"+entry.getValue());
			}
		}
	}
	
	public static ReceiveMessageResult getReceiveMessageResult(){
		AmazonSQS sqs = AmazonFactory.getAmazonSQS();
		GetQueueUrlResult queueUrl = sqs.getQueueUrl("queueTest");
		ReceiveMessageResult receiveMessage = sqs.receiveMessage(new ReceiveMessageRequest()
		.withQueueUrl(queueUrl.getQueueUrl())
		.withAttributeNames("All")
		.withMessageAttributeNames("All"));
		return receiveMessage;
	}

	

}
