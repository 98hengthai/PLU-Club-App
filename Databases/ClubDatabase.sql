--
-- File generated with SQLiteStudio v3.2.1 on Mon Dec 3 14:53:43 2018
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;

-- Table: ClubInterests
CREATE TABLE ClubInterests (InterestName STRING REFERENCES Interests (Name) ON DELETE SET NULL ON UPDATE CASCADE, ClubName STRING REFERENCES Clubs (Name) ON DELETE SET NULL ON UPDATE CASCADE);
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Software Development', 'Computer Science Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Computer Science', 'Computer Science Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Japanese Culture', 'Anime Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Outdoors', 'Outdoor Rec');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Painting', 'Art Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Ceramics', 'Art Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Drawing', 'Art Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Art', 'Art Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Improv', 'Claw Crows');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Acting', 'Claw Crows');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Anime', 'Anime Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Fun', 'Netflix Club');
INSERT INTO ClubInterests (InterestName, ClubName) VALUES ('Biology', 'Biology Club');

-- Table: Clubs
CREATE TABLE Clubs (Name STRING PRIMARY KEY NOT NULL, Location STRING NOT NULL, ClubEmail STRING NOT NULL, Description STRING);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Capstone Club', 'Columbia Center', 'CapstoneNapClub@plu.edu', NULL);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Netflix Club', 'The Cave in UC', 'NetflixClub@plu.edu', NULL);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Computer Science Club', 'Morken 203', 'CSFake@plu.edu', NULL);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Business Club', 'Morken', 'Business@plu.edu', NULL);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Outdoor Rec', 'Columbia Center', 'OREC@plu.edu', NULL);
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Art Club', 'Ingram', 'arthclub@plu.edu', 'The Art Club strives to increase the cultural knowledge and diversity on Pacific Lutheran''s campus, increase the cultural knowledge and diversity to the surrounding Parkalnd area, and encourange an affiinity towards the arts across all mediums, including but not limited to: sculpture, film, painting, fresco, ceramics, and drawing.');
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Anime Club', 'Admin', 'anime@plu.edu', 'Watch Japanese animations for the purpose of entertainment with the intent of learning cultural tropes through this storytelling medium. We will share our thoughts, feelings and interpretations of the anime in group discussion.');
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Claw Crows', 'Karen Hille Phillips Center', 'ClayCrows@plu.edu', 'Pacific Lutheran University''s premiere improv group!');
INSERT INTO Clubs (Name, Location, ClubEmail, Description) VALUES ('Biology Club', 'Rieke', 'bioclub@plu.edu', 'Biology club is a place for biology majors, biology minors, and anyone interested in biology to gather and discuss how and why biology touches our lives.');

-- Table: ClubUsers
CREATE TABLE ClubUsers (ClubName STRING REFERENCES Clubs (Name) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, UserEmail STRING REFERENCES Users (Email) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL);
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Netflix Club', 'Jeremy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Heng@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Jeremy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Computer Science Club', 'Jimmy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Capstone Club', 'Jimmy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Business Club', 'Bob@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Biology Club', 'Isabell@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Outdoor Rec', 'Jeremy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Art Club', 'Jimmy@plu.edu');
INSERT INTO ClubUsers (ClubName, UserEmail) VALUES ('Biology Club', 'Heng@plu.edu');

-- Table: Event
CREATE TABLE Event (IDNumber INTEGER PRIMARY KEY UNIQUE NOT NULL, Event_Name STRING, Location STRING NOT NULL, Start_Time DATETIME NOT NULL, End_Time DATETIME NOT NULL, Repeat STRING, ClubName STRING REFERENCES Clubs (Name) ON DELETE CASCADE ON UPDATE CASCADE MATCH SIMPLE NOT NULL);
INSERT INTO Event (IDNumber, Event_Name, Location, Start_Time, End_Time, Repeat, ClubName) VALUES (1, 'Capstone Study Party', 'Morken 203', '2018-10-22 14:00:00', '2018-10-22 16:00:00', 'False', 'Computer Science Club');
INSERT INTO Event (IDNumber, Event_Name, Location, Start_Time, End_Time, Repeat, ClubName) VALUES (2, 'Lecture for Capstone', 'Rieke 103', '2018-10-23 17:00:00', '2018-10-23 19:00:00', 'False', 'Capstone Club');

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
INSERT INTO Interests (Name) VALUES ('Japanese Culture');
INSERT INTO Interests (Name) VALUES ('Anime');
INSERT INTO Interests (Name) VALUES ('Acting');
INSERT INTO Interests (Name) VALUES ('Improv');
INSERT INTO Interests (Name) VALUES ('Biology');
INSERT INTO Interests (Name) VALUES ('Ceramics');
INSERT INTO Interests (Name) VALUES ('Painting');

-- Table: UserEvents
CREATE TABLE UserEvents (EventIDNumber REFERENCES Event (IDNumber) ON DELETE SET NULL ON UPDATE SET NULL NOT NULL, ClubName REFERENCES Clubs (Name) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, UserEmail STRING REFERENCES Users (Email) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, Reply INTEGER CONSTRAINT Maybe DEFAULT (1));
INSERT INTO UserEvents (EventIDNumber, ClubName, UserEmail, Reply) VALUES (1, 'Computer Science Club', 'Jimmy@plu.edu', 'Yes');
INSERT INTO UserEvents (EventIDNumber, ClubName, UserEmail, Reply) VALUES (1, 'Computer Science Club', 'Jeremy@plu.edu', 'Yes');

-- Table: UserInterests
CREATE TABLE UserInterests (UserEmail STRING REFERENCES Users (Email) ON DELETE SET NULL ON UPDATE CASCADE NOT NULL, InterestName STRING REFERENCES Interests (Name) ON DELETE SET NULL ON UPDATE CASCADE);
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jeremy@plu.edu', 'Computer Science');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Outdoors');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jeremy@plu.edu', 'Cooking');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Computer Science');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Software Development');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jeremy@plu.edu', 'Outdoors');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Anime');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Fun');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Jimmy@plu.edu', 'Art');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Heng@plu.edu', 'Biology');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Heng@plu.edu', 'Drawing');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Isabell@plu.edu', 'Biology');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Wolff@plu.edu', 'Computer Science');
INSERT INTO UserInterests (UserEmail, InterestName) VALUES ('Wolff@plu.edu', 'Software Development');

-- Table: Users
CREATE TABLE Users (Email STRING PRIMARY KEY UNIQUE NOT NULL, Name STRING UNIQUE NOT NULL, GraduationYear INTEGER NOT NULL, StudentBool BOOLEAN NOT NULL);
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Student@plu.edu', 'Student LastName', 2020, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Wolff@plu.edu', 'David Wolff', 2009, 'False');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Heng@plu.edu', 'Hengthai Liv', 2019, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Jeremy@plu.edu', 'Jeremy Pallwitz', 2019, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Jimmy@plu.edu', 'Jimmy Nguyen', 2019, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Bob@plu.edu', 'Billy Bob', 2021, 'True');
INSERT INTO Users (Email, Name, GraduationYear, StudentBool) VALUES ('Isabell@plu.edu', 'Isabell Allen', 2019, 'True');

PRAGMA foreign_keys = on;
