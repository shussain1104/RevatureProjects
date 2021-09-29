use bankingAPI;
create table newUser
(
	id int AUTO_INCREMENT,
	accountnumber int NULL,
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	email varchar(30)NOT NULL,
	firstname varchar(20) NOT NULL,
	lastname varchar(20)NOT NULL,
	PRIMARY KEY(id)
);
create table accountinfo
(
	accountid int NOT NULL,
	balance decimal NOT NULL,
	PRIMARY KEY(accountid)
)
SELECT *
FROM accountinfo JOIN newUser name ON name.firstname = accountinfo.firstname;
DELETE FROM accountinfo ;
INSERT INTO newUser VALUES (1,123456789,'shussain1104','password','shussain1104@gmail.com','Shahreen','Hussain');
INSERT into accountinfo VALUES (1,3750.49);
SET FOREIGN_KEY_CHECKS=0;
Select * FROM accountinfo;
Select * FROM newUser;
SELECT newUser.accountnumber, accountinfo.accountid, accountinfo.balance FROM newUser,accountinfo JOIn newUser account ON account.accountnumber=accountinfo.accountid WHERE newUser.id = 1;
DELETE FROM newUser WHERE id = 1;
UPDATE newUser SET password = 'Detroit#1104', email = 'cutelove1904@gmail.com', firstname = 's', lastname = 'h' WHERE id = 1;
UPDATE accountinfo SET balance = balance + '1.58'  WHERE accountid = 123456789;
DROP accountinfo;
Drop newUser;
INSERT INTO accountinfo VALUES (100.45) WHERE accountid = 123456789;
SELECT id,accountnumber,username,password,email,firstname, lastname FROM newUser WHERE id = 1;