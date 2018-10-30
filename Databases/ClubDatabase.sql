--
-- File generated with SQLiteStudio v3.2.1 on Tue Oct 30 14:52:29 2018
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: ClubInterests
CREATE TABLE ClubInterests (InterestName STRING REFERENCES Interests (Name) ON DELETE SET NULL ON UPDATE CASCADE, ClubName STRING REFERENCES Clubs (Name) ON DELETE SET NULL ON UPDATE CASCADE);
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Software Development', 'Computer Science Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Computer Science', 'Computer Science Club');

-- Table: Clubs
CREATE TABLE Clubs (Name STRING PRIMARY KEY NOT NULL, Location STRING NOT NULL, ClubEmail STRING NOT NULL);
INSERT INTO Clubs (Name, Location, ClubEmail) VALUES ('Capstone Club (Nap Club)', 'Columbia Center', 'CapstoneNapClub@plu.edu');
INSERT INTO Clubs (Name, Location, ClubEmail) VALUES ('Netflix Club', 'The Cave in UC', 'NetflixClub@plu.edu');
INSERT INTO Clubs (Name, Location, ClubEmail) VALUES ('Computer Science Club', 'Morken 203', 'CSFake@plu.edu');

-- Table: ClubUsers
CREATE TABLE ClubUsers (ClubName STRING REFERENCES Clubs (Name) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, UserEmail STRING REFERENCES Users (Email) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL);
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Netflix Club', 'Jeremy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Heng@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Jeremy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Jimmy@plu.edu');

-- Table: Event
CREATE TABLE Event (IDNumber INTEGER PRIMARY KEY UNIQUE NOT NULL, Event_Name STRING, Location STRING NOT NULL, Start_Time DATETIME NOT NULL, End_Time DATETIME NOT NULL, Repeat STRING, ClubName STRING REFERENCES Clubs (Name) ON DELETE CASCADE ON UPDATE CASCADE MATCH SIMPLE NOT NULL);
INSERT INTO Event (IDNumber, Event_Name, Location, Start_Time, End_Time, Repeat, ClubName) VALUES (1, 'Capstone Study Party', 'Morken 203', '2018-10-22 14:00:00', '2018-10-22 16:00:00', 'False', 'Computer Science Club');
INSERT INTO Event (IDNumber, Event_Name, Location, Start_Time, End_Time, Repeat, ClubName) VALUES (2, 'Lecture for Capstone', 'Rieke 103', '2018-10-23 17:00:00', '2018-10-23 19:00:00', 'False', 'Capstone Club (Nap Club)');

-- Table: Interests
CREATE TABLE Interests (Name STRING PRIMARY KEY NOT NULL UNIQUE);
INSERT INTO Interests (Name) VALUES ('Software Development');
INSERT INTO Interests (Name) VALUES ('Computer Science');
INSERT INTO Interests (Name) VALUES ('Contemporary');
INSERT INTO Interests (Name) VALUES ('Social Justice');
INSERT INTO Interests (Name) VALUES ('Art');
INSERT INTO Interests (Name) VALUES ('Drawing');
INSERT INTO Interests (Name) VALUES ('Cooking');
INSERT INTO Interests (Name) VALUES ('Fun');
INSERT INTO Interests (Name) VALUES ('Outdoors');

-- Table: UserEvents
CREATE TABLE UserEvents (EventIDNumber REFERENCES Event (IDNumber) ON DELETE SET NULL ON UPDATE SET NULL NOT NULL, ClubName REFERENCES Clubs (Name) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, UserEmail STRING REFERENCES Users (Email) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, Reply BOOLEAN);
INSERT INTO UserEvents (EventIDNumber, ClubName, UserEmail, Reply) VALUES (1, 'Computer Science Club', 'Jimmy@plu.edu', 'Yes');
INSERT INTO UserEvents (EventIDNumber, ClubName, UserEmail, Reply) VALUES (1, 'Computer Science Club', 'Jeremy@plu.edu', 'Yes');

-- Table: UserInterests
CREATE TABLE UserInterests (UserEmail STRING REFERENCES Users (Email) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, InterestName STRING REFERENCES Interests (Name) ON DELETE SET NULL ON UPDATE CASCADE);
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jeremy@plu.edu', 'Computer Science');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Outdoors');

-- Table: Users
CREATE TABLE Users (Email STRING PRIMARY KEY UNIQUE NOT NULL, Name STRING UNIQUE NOT NULL, GraduationYear INTEGER NOT NULL, StudentBool BOOLEAN NOT NULL);
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Student@plu.edu', 'Student LastName', 2020, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Wolff@plu.edu', 'David Wolff', 2009, 'False');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Heng@plu.edu', 'Hengthai Liv', 2019, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Jeremy@plu.edu', 'Jeremy Pallwitz', 2019, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Jimmy@plu.edu', 'Jimmy Nguyen', 2019, 'True');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
