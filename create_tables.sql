CREATE TABLE Room (
	__RoomId__ INT UNSIGNED NOT NULL,
	MaxOccupancy TINYINT UNSIGNED NOT NULL,
	RoomType ENUM('single', 'double', 'twin') NOT NULL,
	BedType ENUM('twin', 'twin XL', 'double', 'queen', 'king', 'CA king') NOT NULL,
	BedCount TINYINT UNSIGNED NOT NULL,
	Decor ENUM('Modern', 'Industrial', 'Nautical', 'Scandinavian', 'Bohemian', 'Rustic') NOT NULL,
	Price FLOAT NOT NULL,
	PRIMARY KEY (__RoomId__)
);

CREATE TABLE User(
	__UserId__ INT UNSIGNED NOT NULL AUTO_INCREMENT,
	FirstName VARCHAR(50) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL, 
	type ENUM('manager', 'customer') NOT NULL, 
	PRIMARY KEY (__UserId__)
);

CREATE TABLE CreditCard(
	__CardId__ BIGINT UNSIGNED NOT NULL,
	balance FLOAT NOT NULL,
	climit FLOAT  NOT NULL,
	PRIMARY KEY (__CardId__)
);

CREATE TABLE Reservation(
	__ReservationId__ INT UNSIGNED NOT NULL AUTO_INCREMENT,
	UserId INT UNSIGNED NOT NULL,
	CardId BIGINT UNSIGNED NOT NULL,
	CheckIn DATE NOT NULL,
	CheckOut DATE NOT NULL,
	Occupants TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (__ReservationId__),
	FOREIGN KEY (UserId) REFERENCES User(__UserId__),
	FOREIGN KEY (CardId) REFERENCES CreditCard(__CardId__)
);

CREATE TABLE RoomsReserved(
	ReservationId INT UNSIGNED NOT NULL, 
	RoomId INT UNSIGNED NOT NULL,
	FOREIGN KEY (ReservationId) REFERENCES Reservation(__ReservationId__),
	FOREIGN KEY (RoomId) REFERENCES Room(__RoomId__)
)
