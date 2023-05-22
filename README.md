# README
Crypo-CLI is a replacement for spring-cloud-cli which is no longer supported with spring boot 3.*.

## Howto build
### Classic JAR
```mvn verify```  
Produces `target/crypto-cli-0.0.1-SNAPSHOT.jar`

### Native Executable
(!) Requires existing GraalVM installation.  
```mvn -Pnative native:compile```  
Produces `./target/crypto-cli`

## Usage
### Interactive
```./target/crypto-cli```  
Use `help` for usage information.

### Encryption
```
./target/crypto-cli encrypt --message "Hello" --key "test"
db13bc5a9a7e919a4f95508ae2558b6c8c92b5e75a83bea4dd0d5802030ab064
```
or just:
```
./target/crypto-cli encrypt "Hello" "test"
db13bc5a9a7e919a4f95508ae2558b6c8c92b5e75a83bea4dd0d5802030ab064
```

### Decryption
```
./target/crypto-cli decrypt 6d3de18155ea5faa717ecd34e9443d3f73867664fc8977829c4382c4878fcf1c "test"
Hello
```
