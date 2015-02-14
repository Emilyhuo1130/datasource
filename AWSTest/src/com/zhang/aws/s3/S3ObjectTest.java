package com.zhang.aws.s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.Grantee;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;
import com.zhang.aws.util.AmazonFactory;

public class S3ObjectTest {
	static Logger logger=Logger.getLogger(S3ObjectTest.class);
	static String bucket="elasticbeanstalk-us-west-2-948206320069";
	static String destinationBucket="zhangyinhaobucket";
	static AmazonS3 s3 = AmazonFactory.getAmazonS3();
	static Gson gson=new Gson();
	/***
	 * 桶内数据的获取
	 * @throws IOException 
	 * **/
	public static void main(String[] args) throws IOException {
//		getS3Object();
//		uploadObject();
//		makePublicUrl();
		
//		copyObject();
		
//		setObjectNewRedirectLocation();
		
//		getObjectACL();
		setObjectACL();
//		getObjectMetadata();
		
//		listObjectFromBucket();
		
	}
	/***
	 * 从桶中获取文件
	 * */
	public static  void getS3Object() throws IOException{
		/****
		 * 如果文件是手动上传的，那么文件名就是对应的object的key的值
		 * */
		S3Object object = s3.getObject(bucket, "dir/myObjectKey4");//dir 代表文件夹
		S3ObjectInputStream objectContent = object.getObjectContent();
		InputStream inputstream=objectContent;
		saveFile(inputstream);//保存文件到磁盘上
	}
	
	/***
	 * 桶内文件列表
	 * **/
	public static void listObjectFromBucket(){
		ObjectListing listObjects = s3.listObjects(new ListObjectsRequest()
		.withBucketName(bucket)
//		.withEncodingType("utf8")
		.withPrefix("dir/")//设置前缀过滤  如果有文件夹 是 dir 可以设置取 dir文件夹下的文件
//		.withDelimiter("")//设置定界符，不太明白什么意思。。。
		);
		List<S3ObjectSummary> objectSummaries = listObjects.getObjectSummaries();
		for(S3ObjectSummary object:objectSummaries){
			logger.info("object key:         "+object.getKey());
			logger.info("object ETag:         "+object.getETag());
			logger.info("object size:         "+object.getSize());
			logger.info("object StorageClas:         "+object.getStorageClass());
			logger.info("object 最后修改日期:         "+object.getLastModified().toGMTString());
			logger.info("object 文件拥有者:         "+object.getOwner());
			logger.info("===========================================================");
		}
	}
	/***
	 * 
	 * 上传文件到桶
	 * ***/
	public static void uploadObject(){
		Path path = Paths.get("c:"+File.separator+"AmazonTest"+File.separator+"uploadFile.txt");
		PutObjectResult putObject = s3.putObject(bucket, "dir/uploadFile", path.toFile());
		logger.info("contentMD5:   "+putObject.getContentMd5());
	}
	/**
	 * 将一个object转成公共的资源,可以让AWS的其他用户可以访问
	 * **/
	public static void  makePublicUrl(){
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket,"dir/uploadFile");
		URL generatePresignedUrl = s3.generatePresignedUrl(generatePresignedUrlRequest);
		logger.info("public url:   "+generatePresignedUrl.toString());
		//获取文件的文档信息url
		AmazonS3Client amazonS3Client = AmazonFactory.getAmazonS3Client();
		String resourceUrl = amazonS3Client.getResourceUrl(bucket, "dir/uploadFile");
		logger.info("resourceUrl: "+resourceUrl);
	}
	
	/***
	 * 桶内文件的复制
	 * */
	public static void copyObject(){
		CopyObjectResult copyObject = s3.copyObject(bucket, "dir/uploadFile", destinationBucket, "sources/copyFile");
		logger.info("过期时间：   "+copyObject.getExpirationTime());
		
//		s3.copyObject(new CopyObjectRequest(bucket, "dir/uploadFile", destinationBucket, "sources/copyFile"));
	}
	
	
	/**
	 * 删除文件
	 * **/
	public static void deleteObject(){
		s3.deleteObject(destinationBucket, "sources/copyFile");
	}
	/**
	 * 重定向地址
	 * 在Metadata中可以看到
	 * */
	public static void setObjectNewRedirectLocation(){
		s3.setObjectRedirectLocation(bucket, "dir/newFile.txt", "https://s3-ap-southeast-1.amazonaws.com/zhangyinhaobucket/sources/copyFile2");
	}
	
	/***
	 * 获取文件策略信息
	 * */
	public static void getObjectACL(){
		AccessControlList objectAcl = s3.getObjectAcl(bucket, "dir/newFile.txt");
		Owner owner = objectAcl.getOwner();
		logger.info("owner:   "+owner.getDisplayName()+"    id:"+owner.getId());
		Set<Grant> grants = objectAcl.getGrants();
		for(Grant g:grants){
			Permission permission = g.getPermission();
			Grantee grantee = g.getGrantee();
			logger.info(grantee.getIdentifier());
			logger.info(grantee.getTypeIdentifier());
			logger.info("-----------------------------------------");
			logger.info("headerName:   "+permission.getHeaderName());
			logger.info("name:         "+permission.name());
			logger.info("ordinal       "+permission.ordinal());
			logger.info(permission.values());
			logger.info(gson.toJson(permission.values()));
			logger.info("===========================================");
			//["FullControl","Read","Write","ReadAcp","WriteAcp"]
			/***
			 * 四个标签出现标签则代表有对应的权限
			 * Read:可以读取文件
			 * Write：可以修改文件
			 * ReadAcp:可以读取permission的配置信息
			 * WriteAcp:可以修改permission信息
			 * 
			 * **/
			
		}
	}
	
	/***
	 * 设置文件的策略信息
	 * ***/
	public static void setObjectACL(){
		Grantee grantee=new CanonicalGrantee(bucket);
		grantee.setIdentifier("f68c35dd280ea22cd0a83128aa5d96f750d6affa3ff836cfb6165cbe792855cd");//用户id
		AccessControlList accessControlList = new AccessControlList();
		Owner owner =new Owner();
		owner.setDisplayName("zhangyinhao1234");
		owner.setId("f68c35dd280ea22cd0a83128aa5d96f750d6affa3ff836cfb6165cbe792855cd");//用户id
		accessControlList.setOwner(owner);
		/***
		 * 对单一的策略修改，只修改单个属性。
		 * */
		accessControlList.grantPermission(grantee, Permission.Read);
//		accessControlList.revokeAllPermissions(grantee);//撤销所有的文件策略规则
		/*****
		 * 设多个属性的策略。至于为什么本来有两个策略的被修改后只有一个了表示不太清楚
		 * */
//		accessControlList.grantAllPermissions(
//				new Grant(grantee,Permission.Read),
//				new Grant(grantee,Permission.ReadAcp));
		s3.setObjectAcl(bucket, "dir/newFile.txt",accessControlList);
	}
	/****
	 * 设置文件的元数据信息
	 * **/
	
	/***
	 * 获取文件的元数据信息
	 * 在页面上下拉框中的属性的值
	 * */
	public static void getObjectMetadata(){
		ObjectMetadata objectMetadata = s3.getObjectMetadata(bucket, "dir/newFile.txt");
		logger.info("ContentDisposition:    "+objectMetadata.getContentDisposition());
		logger.info("ContentEncoding:       "+objectMetadata.getContentEncoding());
		logger.info("ContentLength:         "+objectMetadata.getContentLength());
		logger.info("ContentType:           "+objectMetadata.getContentType());
		logger.info("ExpirationTimeRuleId:  "+objectMetadata.getExpirationTimeRuleId());
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void saveFile(InputStream input) throws IOException{
//		FileInputStream fis=new FileInputStream(input);
		FileOutputStream fos=new FileOutputStream("c:"+File.separator+"AmazonTest"+File.separator+"down.txt");
		int n=-1;
		byte[] b=new byte[1024];
		while((n=input.read(b))!=-1){
			fos.write(b);
		}
		input.close();
		fos.close();
	}
	private static void displayTextInputStream(InputStream input)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;

			System.out.println("    " + line);
		}
		System.out.println();
	}

}
