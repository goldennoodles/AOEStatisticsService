# Purpose
Simple Springboot Java based application for returning statisics for specified player.

## Implementations;
What does it do?

### Current
- [x] Returns match based statistics will all information populated
- [x] Steam ID user specific
- [x] Store into H2 Database
- [ ] MultiUser inputs
- [x] Cleaner Looking Json
- [ ] Some UI maaaaaaybe

### How to use?
In gitbash run these commands:
`mvn -U clean install`
then
`mvn spring-boot:run`

### To get the DATA:
In a Web Browser:
`localhost:8080/getData?user=<SteamID>`
