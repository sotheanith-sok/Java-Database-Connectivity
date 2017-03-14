
-- Author:  Nicholas
-- Created: Mar 13, 2017
 
-- Create WritingGroups table
--   primary key is GroupName
CREATE TABLE writinggroups (
    groupname varchar(30) NOT NULL,
    headwriter varchar(30) NOT NULL,
    yearformed char(4),
    subject varchar(50),
    CONSTRAINT writinggroups_pk
        PRIMARY KEY (groupname)
);

-- Create Publishers table
--   primary key is PublisherName
CREATE TABLE publishers (
    publishername varchar (30) NOT NULL,
    publisheraddress varchar (30),
    publisherphone varchar (20),
    publisheremail varchar (50),
    CONSTRAINT publishers_pk
        PRIMARY KEY (publishername)
);

-- Create Books table
--   primary key is GroupName, BookTitle
--   foreign keys: GroupName (from WritingGroups) and PublisherName (from Publishers)
CREATE TABLE books (
    groupname varchar(30) NOT NULL,
    booktitle varchar(40) NOT NULL,
    publishername varchar(30) NOT NULL,
    yearpublished char(4),
    numberpages int,
    CONSTRAINT books_pk
        PRIMARY KEY (groupname, booktitle),
    CONSTRAINT books_wgfk
        FOREIGN KEY (groupname) REFERENCES writinggroups (groupname),
    CONSTRAINT books_pfk
        FOREIGN KEY (publishername) REFERENCES publishers (publishername)
);

-- add some data
INSERT INTO publishers (publishername, publisheraddress, publisherphone, publisheremail) VALUES
    ('Pwng Publishing', '1337 Pwng Ln', '1-555-1337', 'IPwnNoobs@pwng.com');

INSERT INTO writinggroups (groupname, headwriter, yearformed, subject) VALUES
    ('Noob Hunters', 'XxPwng223', '1337', 'Noob pwng of all forms');

INSERT INTO books (groupname, booktitle, publishername, yearpublished, numberpages) VALUES
    ('Noob Hunters', 'No more noob', 'Pwng Publishing', '1337', 1337),
    ('Noob Hunters', 'The little noob that couldnt', 'Pwng Publishing', '7331', 9);

--DROP TABLE books;
--DROP TABLE writinggroups;
--DROP TABLE publishers;