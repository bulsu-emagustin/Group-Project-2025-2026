CREATE DATABASE Recycling;
USE Recycling

CREATE LOGIN contributor_user WITH PASSWORD = 'Password123!';

Create user contributor_user
For login contributor_user;

GRANT SELECT, INSERT
ON Contributions 
TO contributor_user;

GRANT SELECT
ON Transactions
TO contributor_user;

Create table Admins (

Username VARCHAR(100),
Primary key(Username),

Password int

);

CREATE TABLE Contributions (
    
    ContributionID INT IDENTITY(1,1) PRIMARY KEY,

    StudentNo INT NOT NULL,
    MaterialType NVARCHAR(50) NOT NULL,
    Quantity INT NOT NULL
   
);

CREATE TABLE Transactions (

    StudentNo INT,
    ContributionID INT,

    Department VARCHAR(100) DEFAULT 'Information Technology',
    TransactionID INT IDENTITY(1,1) PRIMARY KEY,
    CollectionDate DATETIME2 DEFAULT GETDATE(),

    FOREIGN KEY(ContributionID) REFERENCES Contributions(ContributionID)
);

INSERT INTO Contributions(StudentNo, MaterialType, Quantity) VALUES

(2024104677, 'Plastic', 12),
(2024104677, 'Plastic', 20),
(2024104677, 'Paper', 32),
(2024104677, 'Paper', 90),
(2024104678, 'Metal', 2),
(2024104678, 'Metal', 3),
(2024104678, 'Glass', 12),
(2024104679, 'Organic', 30),
(2024104679, 'E-Waste', 3),
(2024104679, 'Textile', 19);

INSERT INTO Admins (Username, Password) VALUES
('Akira', 1234),
('Ernest', 1234)
