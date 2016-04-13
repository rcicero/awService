package br.com.awServices.aws.service.ec2.vpc;

import com.amazonaws.services.ec2.model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

import br.com.awServices.aws.auth.Credentials;

/**
 * @autor rcicero
 * 
 * EBS controller
 * 
 */
@RestController
@RequestMapping("aws/ec2/vpc")
public class VpcController {
  
  /* utilizar caso o acesso seja realizado de uma maquina fora da AWS
   * private Credentials creds = new Credentials();
   * private AmazonEC2Client ec2 = new AmazonEC2Client(creds.getCredEc2());
   * IMPORTANTE: Criar User e liberar a Policy
   * para mais detalhes veja: https://aws.amazon.com/pt/iam/details/
   */
  
  private AmazonEC2 ec2 = new AmazonEC2Client();

  /**
   * Cria Volume EBS
   * 
   * @return HttpStatus + ebsResult
   */
  @RequestMapping(method = RequestMethod.POST)
  public void createVpc() {

      CreateVpcRequest request = new CreateVpcRequest();

      //TODO



     // }

  }

}

