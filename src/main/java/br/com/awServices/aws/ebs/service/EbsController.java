package br.com.awServices.aws.ebs.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.amazonaws.services.ec2.model.VolumeType;

import br.com.awServices.credentials.Credentials;

/**
 * @autor rcicero
 * 
 * 
 */
@RestController
@RequestMapping("aws/ebs")
public class EbsController {

  private Credentials creds = new Credentials();
  
  //Cria um objeto EC2 
  private AmazonEC2 ec2 = new AmazonEC2Client(creds.getCredEc2());
  
  
  /**
   * Cria o EBS
   * 
   * @return HttpStatus + ebsResult
   */
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<String> createEbs() {
    
    CreateVolumeRequest volume = new CreateVolumeRequest();
    
    volume.setSize(1);
    volume.setAvailabilityZone("us-east-1a");
    volume.setVolumeType(VolumeType.Gp2);
    volume.setEncrypted(true);
    
    CreateVolumeResult volumeResult = ec2.createVolume(volume);
   
    return new ResponseEntity<String>(volumeResult.toString(), HttpStatus.CREATED);
    
  }

  @RequestMapping(value = "/{idVolume}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<String> deleteEbs(@PathVariable String idVolume) {
    
    try{
      
      //modelo para excluir o ebs
      DeleteVolumeRequest ebs = new DeleteVolumeRequest();
      ebs.setVolumeId(idVolume);
      
      //deleta o EBS
      this.ec2.deleteVolume(ebs);
      
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(Exception ex){
      System.out.println(ex);
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
   
  }

}
