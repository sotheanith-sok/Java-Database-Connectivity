CREATE TABLE publishers(
	publishername VARCHAR(40) NOT NULL,
	publisheraddress VARCHAR(40),
	publisherphone VARCHAR(40),
	publisheremail VARCHAR(40),
	CONSTRAINT publisher_pk PRIMARY KEY (publishername)	
);
CREATE TABLE writinggroups(
	groupname VARCHAR(40) NOT NULL,
	headwriter VARCHAR(40),
	yearformed VARCHAR(40),
	subject VARCHAR(40),
	CONSTRAINT writinggroup_pk PRIMARY KEY (groupname)
);
CREATE TABLE books(
	groupname VARCHAR(40) NOT NULL,
	booktitle VARCHAR(40) NOT NULL,
	publishername VARCHAR(40) NOT NULL,
	yearpublished VARCHAR(40),
	numberpages INT,
	CONSTRAINT books_pk PRIMARY KEY (groupname, booktitle),
	CONSTRAINT publishers_books_FK FOREIGN KEY (publishername) REFERENCES publishers(publishername),
	CONSTRAINT writinggroup_books_FK FOREIGN KEY (groupname) REFERENCES writinggroups(groupname)
);
--INSERT VALUE IN publishers--
INSERT INTO publishers (publishername, publisheraddress, publisherphone, publisheremail) VALUES
('Apple Inc.','New York, New York','617501426','apple@live.com'),
('Orange Inc.','Cape Town, South Africa','901524395','orange@hotmail.com'),
('Mango Inc.','Tokyo, Japan','444370190','mango@gmail.com');

--INSERT VALUE INTO writinggroups--
INSERT INTO writinggroups (groupname, headwriter, yearformed, subject) VALUES 
('Alpha','John','1538','English'),
('Omega','Lucy','1982','History'),
('Epsilon','Marry','2007','Chemistry'),
('Delta','Zake','2014','Physic'),
('Beta','Niko','1753','Math');


--INSERT VALUE INTO books--
INSERT INTO books (groupname, booktitle, publishername, yearpublished, numberpages)VALUES 
('Alpha','In Search of Lost Time by Marcel Proust','Orange Inc.','1563',156),
('Omega','Don Quixote by Miguel de Cervantes','Orange Inc.','1792',387),
('Delta','Ulysses by James Joyce','Orange Inc.','2017',4562),
('Beta','The Odyssey by Homer','Apple Inc.','2000',1234),
('Epsilon','War and Peace by Leo Tolstoy','Mango Inc.','1982',7453),
('Epsilon','Moby Dick by Herman Melville','Mango Inc.','1970',4125),
('Beta','The Divine Comedy by Dante Alighieri','Orange Inc.','1999',127),
('Omega','Hamlet by William Shakespeare','Mango Inc.','2003',852),
('Delta','Madame Bovary by Gustave Flaubert','Apple Inc.','2005',465),
('Alpha','The Iliad by Homer','Apple Inc.','1985',1357);