# README
Spring-Crypto-CLI is a replacement for spring-cloud-cli with its encryption and decryption functionalities which is no 
longer supported with spring boot 3.*:      
https://github.com/spring-cloud/spring-cloud-cli/issues/180  

It uses the spring security crypto module to do the encryption as done by spring-cloud-cli:  
https://docs.spring.io/spring-security/reference/features/integrations/cryptography.html

## How to build and run
Build with:  
```mvn verify```    
Run with:  
```java -jar ./target/spring-crypto-cli-1.1.0-SNAPSHOT-jar-with-dependencies.jar ```

### Alias
For simplicity, we recommend to register an alias like the following in your `~/.profile`:  
`alias spring-crypto-cli='java -jar ./bin/spring-crypto-cli-1.1.0-SNAPSHOT-jar-with-dependencies.jar'`

## Usage
The examples below will use this alias.
### Encryption
```
spring-crypto-cli encrypt --message "Hello" --key "test"
```
returns something like:
```
db13bc5a9a7e919a4f95508ae2558b6c8c92b5e75a83bea4dd0d5802030ab064
```

### Decryption
```
spring-crypto-cli decrypt --message "6d3de18155ea5faa717ecd34e9443d3f73867664fc8977829c4382c4878fcf1c" --key "test"
```
returns:
```
Hello
```
