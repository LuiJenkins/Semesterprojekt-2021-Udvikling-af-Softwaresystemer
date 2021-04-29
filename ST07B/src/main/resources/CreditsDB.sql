/****************************************************
 *     Create database                              *
 ****************************************************/
CREATE DATABASE "CreditsDB"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
	
/****************************************************
 *     Create Tables                              *
 ****************************************************/	

-- Producer
CREATE TABLE IF NOT EXISTS producers (
    producer_id SERIAL PRIMARY KEY,	
	producerName VARCHAR(250),	-- name of the producer
	producerInfo VARCHAR(250) 	-- info about the producer
);

-- Programs
CREATE TABLE IF NOT EXISTS programs (
    program_id SERIAL PRIMARY KEY,	
	producer_id INT,			-- id of the owning producer
	programName VARCHAR(250),	-- name of the program
	playingTimeSec INT,			-- playing time in secunds
	FOREIGN KEY (producer_id) 
		REFERENCES producers (producer_id)
);

-- user table
CREATE TABLE IF NOT EXISTS app_user (
	user_id SERIAL PRIMARY KEY,
  	username varchar(45) NOT NULL,
  	password varchar(150) NOT NULL,
	fullName varchar(250),
  	userRole INT,
	producer_id INT,
	FOREIGN KEY (producer_id) 
		REFERENCES producers (producer_id)
);

-- Approved
CREATE TABLE IF NOT EXISTS approved (
    approved_id SERIAL PRIMARY KEY,	
	program_id INT,				 -- the approved program
	status INT, 				 -- 1 if approved 0 = not approved
	approvedBy INT,	 			 -- who has approved it, if not approved null
	approvedDate TIMESTAMP		 -- time and date it was approved
);
-- Drop table approved;

-- person
CREATE TABLE IF NOT EXISTS persons (
    person_id SERIAL PRIMARY KEY,	
	personName VARCHAR(200),	 -- Name of person
	adr_info VARCHAR(250)
);

-- catagorys
CREATE TABLE IF NOT EXISTS catagorys (
    catagory_id SERIAL PRIMARY KEY,	
	catagoryName VARCHAR(200),	-- Name of catagory
	catagoryType INT,			-- the type of catagory 0 = do not show on template. 1 = show on template 
	catagoryNumber INT			-- the default nummer the catagory is in the list
);

-- credits
CREATE TABLE IF NOT EXISTS credits (
    credit_id SERIAL PRIMARY KEY,	
	program_id INT,				-- the program that the credit belongs to
	catagory_id INT,			-- the catagory the credit belongs to
	person_id INT,				-- the person in the credit
	numberInCatagory INT,		-- the number in the catagory
	FOREIGN KEY (program_id) 
		REFERENCES programs (program_id),
	FOREIGN KEY (catagory_id) 
		REFERENCES catagorys (catagory_id),
	FOREIGN KEY (person_id) 
		REFERENCES persons (person_id)
);

-- channel
CREATE TABLE IF NOT EXISTS channels (
    channel_id SERIAL PRIMARY KEY,	
	channelName VARCHAR(200),	 -- Name of channel
	channelInfo VARCHAR(250)	 -- info about the channel
);

-- timeslot
CREATE TABLE IF NOT EXISTS timeslots (
    timeslot_id SERIAL PRIMARY KEY,	
	timeFrom TIMESTAMP,			-- time the program is set to air 
	timeTO TIMESTAMP,			-- the time the program ends
	channel_id INT,				-- the channel that airs the program
	program_id INT,				-- the program to air
	FOREIGN KEY (program_id) 
		REFERENCES programs (program_id),
	FOREIGN KEY (channel_id) 
		REFERENCES channels (channel_id)
);

/****************************************************
 *     End of create Tables                         *
 ****************************************************/	

/****************************************************
 *     Insert dummy data                            *
 ****************************************************/
-- make 2 dummy producer
INSERT INTO producers (producerName, producerInfo) 
VALUES ('Peak Production', 'tv- film- og videoproduktion plus webtv'),
		('Studio Jox','Produktion af kortfilm dokumentar mv');

-- make 5 dummy programs
INSERT INTO programs (producer_id, programName, playingTimeSec)
VALUES (1,'Mord i tågen', 1804),
		(1,'Naboerne',2560),
		(2,'Seperation',300),
    	(2,'Øst KICK-OFF',980),
		(2,'Memory of Water #1',1750);
		
-- make 13 dummy catagorys
INSERT INTO catagorys (catagoryName, catagoryType, catagoryNumber)
VALUES ('Tilrettelæggelse/instruktion',1, 1),
		('Fotograf',1,2),
		('Lyd',0,4),
    	('Lys',0,5),
    	('Billed- og lydredigering',1,6),
    	('Casting',0,7),
    	('Culourgrading',0,8),
    	('Producer',1,9),
    	('Co-producer',0,10),
    	('Scenograf',0,11),
    	('Producent',0,12),
    	('Oversættere',0,13),
		('Medvirkende',1,3);
		
-- make 17 dummy persons
INSERT INTO persons (personName, adr_info)
VALUES ('Jens J. Jensen', 'en eller an info'),
		('Lars P. Larsen',''),
		('Gunner Jerløv',''),
		('Søren Messerschmidt',''),
		('Ali Sahim',''),
		('Ivalo Mathisen',''),
		('Gunner Jerløv',''),
		('Peter Frandsen-Simonsen',''),
		('Jørgen F. Hansen',''),
		('Jael Schmidt',''),
		('Gry Piil',''),
		('Rasmus T. A. Kold',''),
		('Bo Hammerich',''),
		('Oktavia Jørgensen',''),
		('Lene Hylding',''),
    	('Paw P. Pederstrup',''),
		('Phu Wan Liu','');		

-- make 4 dummy users
INSERT INTO app_user (userName, password, fullName, userRole, producer_id)
VALUES ('Morten','111111', 'Morten H. Nielsen',1,1), 	-- producer with id 1 (Peak Production)
		('Rasmus','222222','Rasmus Bentzen Thye',2,1),	-- Maintainer
		('Jens','333333','Jens C. Toftdahl',3,1),		-- Administrator
		('Louie','444444','Louie Steen Laursen',1,2);	-- producer with id 2 (Studio Jox)

-- make 6 dummy channels
INSERT INTO channels (channelName, channelInfo)
VALUES ('TV 2','Underholdning m.m.'),
		('TV 2 NEWS','Nyhed døgnet rundt'),
		('TV 2 ZULU','TV for de unge'),
		('TV 2 CHARLIE','TV for de modne'),
		('TV 2 SPORT','Sportskanalen'),
		('TV 2 FRI','Udenliv og have');

-- make 1 dummy approvel
INSERT INTO approved (program_id, status, approvedBy, approvedDate)
VALUES (1, 1, 1, Now());		--  Program #1 approved by user #1 Morten 

-- make 8 dummy creditlines for program #1
INSERT INTO credits (program_id, catagory_id, person_id, numberInCatagory)
VALUES (1, 1, 4,1), 		-- Mord i tågen / Tilrettelæggelse/instruktion / Søren Messerschmidt #1
		(1, 1, 3,2),		-- Mord i tågen / Tilrettelæggelse/instruktion / Gunner Jerløv #2
		(1, 2, 8,1),		-- Mord i tågen / Fotograf / Peter Frandsen-Simonsen #1
		(1, 2, 9,2),		-- Mord i tågen / Fotograf / Jørgen F. Hansen #2
		(1, 2, 11,3),		-- Mord i tågen / Fotograf / Gry Piil #3
		(1, 3, 13,1),		-- Mord i tågen / Lyd / Bo Hammerich #1
		(1, 4, 6,1),		-- Mord i tågen / Lys / Ivalo Mathisen #1
		(1, 11, 12,1);		-- Mord i tågen / Producent / Rasmus T. A. Kold #1

-- make 4 dummy timeslots 
INSERT INTO timeslots (timeFrom, timeTo, channel_id, program_id)
VALUES (TO_TIMESTAMP('01-05-2021 10:20', 'DD-MM-YYYY HH24:MI'), 
		TO_TIMESTAMP('01-05-2021 10:50', 'DD-MM-YYYY HH24:MI'), 1, 1),		-- TV 2 / Mord i tågen
		(TO_TIMESTAMP('01-05-2021 10:50', 'DD-MM-YYYY HH24:MI'), 
		TO_TIMESTAMP('01-05-2021 11:33', 'DD-MM-YYYY HH24:MI'), 1, 2),		-- TV 2 / Naboerne
		(TO_TIMESTAMP('01-05-2021 11:33', 'DD-MM-YYYY HH24:MI'), 
		TO_TIMESTAMP('01-05-2021 11:40', 'DD-MM-YYYY HH24:MI'), 1, 3),		-- TV 2 / Seperation
		(TO_TIMESTAMP('01-05-2021 11:50', 'DD-MM-YYYY HH24:MI'), 
		TO_TIMESTAMP('01-05-2021 12:20', 'DD-MM-YYYY HH24:MI'), 6, 5);		-- TV 2 FRI / Memory of Water #1

/****************************************************
 *     End of Insert dummy data                     *
 ****************************************************/
 
 
/****************************************************
 *     show Tables                                  *
 ****************************************************/	
SELECT * FROM producers;		-- show all records in producers
SELECT * FROM programs;			-- show all records in programss
SELECT * FROM app_user;			-- show all records in app_user
SELECT * FROM approved;			-- show all records in approved
SELECT * FROM persons;			-- show all records in persons
SELECT * FROM catagorys;		-- show all records in catagorys
SELECT * FROM credits;			-- show all records in credits
SELECT * FROM channels;			-- show all records in channels
SELECT * FROM timeslots;		-- show all records in timeslots

