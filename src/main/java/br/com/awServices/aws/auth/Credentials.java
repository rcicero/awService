package br.com.awServices.aws.auth;

/**
 * dicionario de credenciais
 *
 * @author rcicero
 *
 * IMPORTANTE: Criar User e liberar a Policy
 * para mais detalhes veja: https://aws.amazon.com/pt/iam/details/
 *
 */
public enum Credentials {

  EC2("accessKey", "secretKey"),
  RDS("accessKey","secretKey"),
  ADM("accessKey","secretKey")

  ;

  private final String accessKey;

  private final String secretKey;

  private Credentials(final String accessKey, final String secretKey) {
    this.accessKey = accessKey;
    this.secretKey = secretKey;
  }

  public String getAccessKey(){
    return this.accessKey;
  }

  public String getSecretKey(){
    return this.secretKey;
  }

}
