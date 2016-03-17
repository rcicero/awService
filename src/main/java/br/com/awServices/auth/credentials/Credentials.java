package br.com.awServices.auth.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * 
 * @author rcicero
 *
 */
public class Credentials {
  

  public AWSCredentials getCredEc2(){
    return new BasicAWSCredentials("Access Key",
        "Private Key");
    
  }

}
