# account-web-service
##Instruction to run the application
-checkout the account-web-service application from github
-run maven command: mvn spring-boot:run
-the web service api will be available under http://localhost:8400/


Please note that account web service sends requests to transaction service and account service. Please make sure that all three servers should be all up and running in order to see the APIs in actions

For demo purpose, the accounts link in the home page is linked to a dummy customer. 
Any request to invalid customer id will return this dummy customer data. 

The design is that customer should login using OAuth2 to be authenticated and be authorised to view his own account. 
The authentication and authorisation function will return a token to enable customer to view and perform certain actions on 
his/her accounts. However, due to limit time constraint on development time, this function has not been implemented. 