Feature: Validating User post comments and albums

Scenario Outline: verify if the email is correct in the comments added by the given user
Given get the list of users with "GetUserAPI"
When retreive userID "<User>"
And get the posts from the user with "GetPostAPI"
And retreive the "email" from comments using "GetCommentAPI"
Then email validation is success

Examples:
| User   |
| Kamren |

	
Scenario: verify the respone code is 200 while retreiving the userdata
Given get the list of users with "GetUserAPI"
When retreive userID "Bret"
Then verify status code in response is 200


Scenario: Verify the given title is present in albums for the given user and verify expected server name in  header section
Given get the list of users with "GetUserAPI"
When retreive userID "Bret"
And get the albums from the user with "GetAlbumsAPI"
Then verify the title "omnis laborum odio" is present
And "server" name in response header is "cloudflare"





