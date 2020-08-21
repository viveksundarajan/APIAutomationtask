package resources;

public enum APIResources {

	GetUserAPI("/users"),
	GetPostAPI("/posts"),GetCommentAPI("/comments"), GetAlbumsAPI("/albums");
	
private String resource;


	APIResources(String resource) {
		this.resource=resource;
	}
	
	
	public String getResource()
	{
		return resource;
	}
}
