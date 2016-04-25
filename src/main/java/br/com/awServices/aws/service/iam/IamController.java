package br.com.awServices.aws.service.iam;

import br.com.awServices.aws.auth.Credentials;
import br.com.awServices.aws.service.iam.input.GroupModel;
import br.com.awServices.aws.service.iam.input.UserModel;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.identitymanagement.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for IAM Service
 *
 * @author rcicero
 */
@RestController
@RequestMapping("/iam")
public class IamController {

  private AmazonIdentityManagementClient amazonIdentityManagementClient =
      new AmazonIdentityManagementClient(new BasicAWSCredentials(Credentials.ADM.getAccessKey(), Credentials.ADM.getSecretKey()));

  /**
   * Cria User IAM
   *
   * @param userModel
   */
  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public
  @ResponseBody
  ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
    try {

      final CreateUserRequest createUserRequest = new CreateUserRequest(userModel.getName());
      final CreateUserResult createUserResult = this.amazonIdentityManagementClient.createUser(createUserRequest);

      return new ResponseEntity<String>(createUserResult.toString(), HttpStatus.CREATED);
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  /**
   * Cria User IAM
   *
   * @param groupModel
   */
  @RequestMapping(value = "/groups", method = RequestMethod.POST)
  public
  @ResponseBody
  ResponseEntity<String> createRole(@RequestBody GroupModel groupModel) {
    try {

      final CreateGroupRequest createGroupRequest = new CreateGroupRequest();
      createGroupRequest.setGroupName(groupModel.getName());

      final CreateGroupResult createGroupResult = this.amazonIdentityManagementClient.createGroup(createGroupRequest);

      return new ResponseEntity<String>(createGroupResult.toString(), HttpStatus.CREATED);
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  /**
   * adiciona usuario ao grupo
   *
   * @param userName
   * @param groupName
   */
  @RequestMapping(value = "/groups/add/users", method = RequestMethod.POST)
  public
  @ResponseBody
  ResponseEntity<String> addUserToGroup(@RequestParam String userName, @RequestParam String groupName) {
    try {

      final AddUserToGroupRequest addUserToGroupRequest = new AddUserToGroupRequest();
      addUserToGroupRequest.setGroupName(groupName);
      addUserToGroupRequest.setUserName(userName);

      this.amazonIdentityManagementClient.addUserToGroup(addUserToGroupRequest);

      return new ResponseEntity<String>("", HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

}
