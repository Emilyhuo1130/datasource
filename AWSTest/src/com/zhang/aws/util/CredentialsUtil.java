package com.zhang.aws.util;

import java.util.ResourceBundle;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class CredentialsUtil {
	/**
	 * @return AWSCredentials
	 * */
	public static AWSCredentials getCredentials(){
		ResourceBundle bundle=ResourceBundle.getBundle("credentials");
		AWSCredentials credentials = new BasicAWSCredentials(bundle.getString("aws_access_key_id"),bundle.getString("aws_secret_access_key"));
		return credentials;
	}
}
