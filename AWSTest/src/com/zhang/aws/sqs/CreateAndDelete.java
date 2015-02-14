package com.zhang.aws.sqs;

import org.apache.log4j.Logger;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.zhang.aws.util.AmazonFactory;

public class CreateAndDelete {
	static Logger logger=Logger.getLogger(CreateAndDelete.class);
	/****
	 * 
	 * l列队的创建和删除
	 * 消息的发送 获取和删除
	 * */
	public static void main(String[] args) {
		AmazonSQS sqs = AmazonFactory.getAmazonSQS();
		//创建
		CreateQueueResult createQueue = sqs.createQueue("newQueue");
		//发送消息
		SendMessageResult sendMessage = sqs.sendMessage(createQueue.getQueueUrl(), "hello world");
		//接收消息
		ReceiveMessageResult receiveMessage = sqs.receiveMessage(createQueue.getQueueUrl());
		for(Message message:receiveMessage.getMessages()){
			logger.info("message ReceiptHandle:      "+message.getReceiptHandle());
			logger.info("message body:                "+message.getBody());
		}
		//删除消息
		sqs.deleteMessage(createQueue.getQueueUrl(), receiveMessage.getMessages().get(0).getReceiptHandle());
		
		//删除列队
		sqs.deleteQueue(createQueue.getQueueUrl());
		logger.info("----------------end-------------------");
		
	}

}
