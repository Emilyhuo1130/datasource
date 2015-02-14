package com.zhang.aws.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.ResourceBundle;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3MethodTest {

	public static void main(String[] args) throws AmazonServiceException, AmazonClientException, IOException {
		ResourceBundle bundle=ResourceBundle.getBundle("credentials");
		AWSCredentials credentials=null;
		credentials=new BasicAWSCredentials(bundle.getString("aws_access_key_id"), bundle.getString("aws_secret_access_key"));
		AmazonS3 s3=new AmazonS3Client(credentials);
		String bucket="elasticbeanstalk-us-west-2-948206320069";
		String key="dir/ObjectKey4";//dir/表示文件夹名。
//		s3.putObject(bucket, key, createSampleFile());

		ObjectListing listObjects = s3.listObjects(new ListObjectsRequest().withBucketName(bucket).withPrefix("my"));
		
		List<S3ObjectSummary> objectSummaries = listObjects.getObjectSummaries();
		for(S3ObjectSummary summary:objectSummaries){
			System.out.println(summary.getKey()+"-----"+summary.getSize());
		}
		
		
		System.out.println("-------------------------------------");
		
//		S3Object object = s3.getObject(bucket, key);
//		InputStream objectContent = (InputStream)object.getObjectContent();
//		object.setKey("1231");
//		boolean requesterCharged = object.isRequesterCharged();
//		System.out.println(requesterCharged);
		
	}

	
	private static File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqwqw123232\n");
        writer.close();

        return file;
    }
}
