package com.zhang.aws.sqs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.zhang.aws.s3.S3UploadFile;
import com.zhang.aws.util.CredentialsUtil;

public class SQSQueueTest {
	static Logger logger = Logger.getLogger(SQSQueueTest.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AWSCredentials credentials = CredentialsUtil.getCredentials();
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		
		sqs.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		
		//创建消息队列
//		CreateQueueResult createQueue = sqs.createQueue("queueTest");
//		logger.info("new queueuri:"+createQueue.getQueueUrl());
		
		
		//列出queue
		ListQueuesResult listQueues = sqs.listQueues();
		for(String uri:listQueues.getQueueUrls()){
			logger.info("queue uri:"+uri);
//			GetQueueUrlResult queueUrl = sqs.getQueueUrl(uri);
		}
		
		String queueName="queueTest";
		GetQueueUrlResult queueUrl = sqs.getQueueUrl(queueName);
//		logger.info("queueUrlByName:     "+queueUrl.getQueueUrl());
//		
//		//发送消息
		Map<String,MessageAttributeValue> putmessageAttributes=new HashMap();
		putmessageAttributes.put("returnDate", new MessageAttributeValue().withStringValue("2014-10-10 22:10:56").withDataType("String"));
		SendMessageResult sendMessage = sqs.sendMessage(new SendMessageRequest(queueUrl.getQueueUrl(),"hello world2")
		.withMessageAttributes(putmessageAttributes)
		);
//		
		ReceiveMessageResult receiveMessage=null;
//		receiveMessage = sqs.receiveMessage(queueUrl.getQueueUrl());
		receiveMessage = sqs.receiveMessage(new ReceiveMessageRequest()
		.withMaxNumberOfMessages(5)
		.withQueueUrl(queueUrl.getQueueUrl())
		.withAttributeNames("All")
		.withMessageAttributeNames("All")
		);
		logger.info("message count:"+receiveMessage.getMessages().size());
		for(Message message:receiveMessage.getMessages()){
			logger.info("messageid:  "+message.getMessageId());
			logger.info("message body:  "+message.getBody());
			logger.info("message MD5OFbody:  "+message.getMD5OfBody());
			logger.info("message ReceiptHandle:  "+message.getReceiptHandle());
			
			logger.info("Attribuates..................");
			Map<String, String> attributes = message.getAttributes();
			for(Entry<String, String> entry:attributes.entrySet()){
				logger.info("key:"+entry.getKey()+"       value:"+entry.getValue());
			}
			logger.info("message Attribuates..................");
			Map<String, MessageAttributeValue> messageAttributes = message.getMessageAttributes();
			logger.info("messageAttributes count:"+messageAttributes.size()+"    MD5OfMessageAttributes:"+message.getMD5OfMessageAttributes());
			for(Entry<String,MessageAttributeValue> entry: messageAttributes.entrySet()){
				logger.info("key:"+entry.getKey()+"       value:"+entry.getValue().getStringValue());
			}
			
		}
		
		
		
		
		
		logger.info("--------------------------------");
		
	}

}
