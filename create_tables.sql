
CREATE TABLE Room (
	RoomId INT UNSIGNED NOT NULL,
	MaxOccupancy TINYINT UNSIGNED NOT NULL,
	RoomType ENUM('single', 'double', 'twin') NOT NULL,
	BedType ENUM('twin', 'twin XL', 'double', 'queen', 'king', 'CA king') NOT NULL,
	BedCount TINYINT UNSIGNED NOT NULL,
	Decor ENUM('Modern', 'Industrial', 'Nautical', 'Scandinavian', 'Bohemian', 'Rustic') NOT NULL,
	Price FLOAT NOT NULL,
	PRIMARY KEY (RoomId)
);

CREATE TABLE User(
	UserId INT UNSIGNED NOT NULL AUTO_INCREMENT,
	FirstName VARCHAR(50) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL, 
	type ENUM('manager', 'customer') NOT NULL, 
	PRIMARY KEY (UserId)
);

CREATE TABLE CreditCard(
	CardId BIGINT UNSIGNED NOT NULL,
	balance FLOAT NOT NULL,
	climit FLOAT  NOT NULL,
	PRIMARY KEY (CardId)
);

CREATE TABLE Reservation(
	ReservationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
	UserId INT UNSIGNED NOT NULL,
	CardId BIGINT UNSIGNED NOT NULL,
	CheckIn DATE NOT NULL,
	CheckOut DATE NOT NULL,
	PRIMARY KEY (ReservationId),
	FOREIGN KEY (UserId) REFERENCES User(UserId),
	FOREIGN KEY (CardId) REFERENCES CreditCard(CardId)
);

CREATE TABLE RoomsReserved(
	ReservationId INT UNSIGNED NOT NULL, 
	RoomId INT UNSIGNED NOT NULL,
	Occupants TINYINT UNSIGNED NOT NULL,
   	PRIMARY KEY (ReservationId, RoomId),
   	FOREIGN KEY (ReservationId) REFERENCES Reservation(ReservationId),
	FOREIGN KEY (RoomId) REFERENCES Room(RoomId)
);