# README
Crypo-CLI is a replacement for spring-cloud-cli which is no longer supported with spring boot 3.*.  
See https://github.com/spring-cloud/spring-cloud-cli/issues/180.

## Howto build and run
### Classic executable JAR
Build with:
```mvn verify```    
Run with:
```java -jar ./target/spring-crypto-cli-0.0.1-SNAPSHOT.jar```

### Native Executable
⚠️ Requires existing GraalVM installation. ⚠️  
Build with:
```mvn -Pnative native:compile```  
Run with: 
`./target/spring-crypto-cli`

## Usage
The above 'run with' command was replace with `spring-crypto-cli` below.
### Interactive
To open the spring-crypto-cli interactive shell run:  
```spring-crypto-cli```  
Use `help` for usage information.

### Encryption
```
spring-crypto-cli encrypt --message "Hello" --key "test"
```
or just:
```
spring-crypto-cli encrypt "Hello" "test"
```
returns something like:
```
db13bc5a9a7e919a4f95508ae2558b6c8c92b5e75a83bea4dd0d5802030ab064
```

### Decryption
```
spring-crypto-cli decrypt "6d3de18155ea5faa717ecd34e9443d3f73867664fc8977829c4382c4878fcf1c" "test"
```
returns:
```
Hello
```
