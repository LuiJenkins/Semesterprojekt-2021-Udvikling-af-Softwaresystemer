/****************************************************
 *     Create Tables                              *
 ****************************************************/	

-- Producer
CREATE TABLE IF NOT EXISTS producers (
	producer_id SERIAL PRIMARY KEY,
	producerName VARCHAR(250),	-- name of the producer
	producerInfo VARCHAR(250)	-- info about the producer
	);

-- Programs	
CREATE TABLE IF NOT EXISTS programs (
	program_id SERIAL PRIMARY KEY,
	producer_id INT,			-- id of the owning producer
	programName VARCHAR(250),	-- name of the program
	playingTimeSec INT			-- playing time in seconds
	);

-- Approved
CREATE TABLE IF NOT EXISTS approved (
	program_id SERIAL PRIMARY KEY, 	-- the approved program
	status INT,						-- 1 = sent to be approved 2 = approved 0 = not approved
	approvedDate TIMESTAMP			-- time and date of approval
	);
	
-- person	
CREATE TABLE IF NOT EXISTS persons (
	person_id SERIAL PRIMARY KEY,
	personName VARCHAR(200), 	-- Name of person
	personDesc VARCHAR(250)		-- description of person
	);

 -- categorys
CREATE TABLE IF NOT EXISTS categorys (
	category_id SERIAL PRIMARY KEY,	
	categoryName VARCHAR(200),		-- Name of category
	categoryType INT,				-- the type of category 0 = do not show on template. 1 = show on template (not yet implemented)
	categoryNumber INT				-- the default number the category is in the list   (not yet implemented)
	);

-- credits
CREATE TABLE IF NOT EXISTS credits (
	program_id INT,			-- the program that the credit belongs to
	category_id INT,		-- the category the credit belongs to
	person_id INT,			-- the person in the credit
	numberInCategory INT,	-- the linenumber in the category (not yet implemented)
	PRIMARY KEY (program_id,category_id,person_id)
	);
	
-- user table
CREATE TABLE IF NOT EXISTS app_user (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(45) NOT NULL,	-- user name and password gives access to system.
	password VARCHAR(150),
	fullName VARCHAR(250),			-- full name of the user.
	userRole INT,					-- Role of the user 1 = Producer. 2 = Maintainer. 3 = Administrator
	producer_id INT
	);
/****************************************************
 *     End of create Tables                         *
 ****************************************************/	

/****************************************************
 *     Insert dummy data                            *
 ****************************************************/
-- make 4 dummy users
INSERT INTO app_user (userName, password, fullName, userRole, producer_id)
VALUES ('Morten','111111', 'Morten H. Nielsen',1,1), 	-- producer with id 1 
		('Rasmus','222222','Rasmus Bentzen Thye',2,1),	-- Maintainer
		('Jens','333333','Jens C. Toftdahl',3,1),		-- Administrator
		('Louie','444444','Louie Steen Laursen',1,2);	-- producer with id 2 
		

/****************************************************
 *     End of Insert dummy data                     *
 ****************************************************/





/*
Code to drop the different tables in the database

DROP TABLE app_user;
DROP TABLE approved;
DROP TABLE credits;
DROP TABLE persons;
DROP TABLE producers;
DROP TABLE programs;
*/


-- code to show the different tables
SELECT * FROM app_user; 
SELECT * FROM approved; 
SELECT * FROM credits; 
SELECT * FROM persons;
SELECT * FROM producers; 
SELECT * FROM programs;
