package main.resources.models;

public class tokenAuth {
  private String grant_type;
  private String client_id;
  private String client_secret;
  public tokenAuth(String grant_type, String client_id, String client_secret)
  {
    this.grant_type = grant_type;
    this.client_id = client_id;
    this.client_secret = client_secret;
  }
}
