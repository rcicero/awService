package br.com.awServices.aws.ec2.ebs.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.amazonaws.services.ec2.model.VolumeType;

import br.com.awServices.credentials.Credentials;

/**
 * @autor rcicero
 * 
 * EBS controller
 * 
 */
@RestController
public class EbsController {

  private Credentials creds = new Credentials();
  
  //IMPORTANTE: Criar User e liberar a Policy
  //para mais detalhes veja: https://aws.amazon.com/pt/iam/details/
  // Cria um objeto EC2
  private AmazonEC2 ec2 = new AmazonEC2Client(creds.getCredEc2());


  /**
   * Cria Volume EBS
   * 
   * @return HttpStatus + ebsResult
   */
  @RequestMapping(value = "aws/ebs/{idVolume}", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<String> createEbs() {
    try{
    CreateVolumeRequest volume = new CreateVolumeRequest();

    volume.setSize(1);
    volume.setAvailabilityZone("us-east-1a");
    volume.setVolumeType(VolumeType.Gp2);
    volume.setEncrypted(true);

    CreateVolumeResult volumeResult = ec2.createVolume(volume);

    return new ResponseEntity<String>(volumeResult.toString(), HttpStatus.CREATED);
    }catch(Exception ex){
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  /**
   * 
   * Delete Volume EBS
   * 
   * @param idVolume
   * @return ResponseEntity
   */
  @RequestMapping(value = "aws/ebs/{idVolume}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<String> deleteEbs(@PathVariable String idVolume) {

    try {

      // modelo para excluir o ebs
      DeleteVolumeRequest ebs = new DeleteVolumeRequest();
      ebs.setVolumeId(idVolume);

      // deleta o EBS
      this.ec2.deleteVolume(ebs);

      return new ResponseEntity<String>(HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

  }

  /**
   * 
   * Create Snapshot
   * 
   * @param idVolume
   * @return ResponseEntity
   */
  @RequestMapping(value = "aws/ebs/{idVolume}/snapshot", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<String> createSnapshot(@PathVariable String idVolume) {

    try {

      // modelo para criar o snapshot
      CreateSnapshotRequest snapshot = new CreateSnapshotRequest();
      snapshot.setVolumeId(idVolume);
      snapshot.setDescription("Snapshot from: "+idVolume);
      
      // cria o snapshot
      CreateSnapshotResult snapResult = this.ec2.createSnapshot(snapshot);

      return new ResponseEntity<String>(snapResult.toString(), HttpStatus.OK);
      
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }
  
  /**
   * 
   * Delete Snapshot
   * 
   * @param idVolume
   * @return ResponseEntity
   */
  @RequestMapping(value = "aws/ebs/snapshot/{idSnapshot}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<String> deleteSnapshot(@PathVariable String idSnapshot) {

    try {

      // modelo para deletar o snapshot
      DeleteSnapshotRequest snapshot = new DeleteSnapshotRequest();
      snapshot.setSnapshotId(idSnapshot);
      
      // deleta o snapshot
      this.ec2.deleteSnapshot(snapshot);

      return new ResponseEntity<String>(HttpStatus.OK);
      
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }
  
  /**
   * 
   * Create Volume from Snapshot
   * 
   * @param idVolume
   * @return ResponseEntity
   */
  @RequestMapping(value = "aws/ebs/snapshot/{idSnapshot}", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<String> createVolumeFromSnapshot(@PathVariable String idSnapshot) {

    try {

      // modelo para deletar o snapshot
      CreateVolumeRequest ebs = new CreateVolumeRequest();
      ebs.setSnapshotId(idSnapshot);
      ebs.setAvailabilityZone("us-east-1a");
      // deleta o snapshotgit
      CreateVolumeResult volume = this.ec2.createVolume(ebs);

      return new ResponseEntity<String>(volume.toString(), HttpStatus.OK);
      
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }
  
  @RequestMapping(value = "/status",method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<String> getStatus() {
  
    return new ResponseEntity<String>("Olá!!! Eu estou bem. :)", HttpStatus.OK);
   
  }

}
