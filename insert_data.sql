--Users
INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('John', 'Doe', 'admin', 'admin', 'MANAGER');

INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('Bob', 'Jones', 'bjones74', 'password', 'CUSTOMER');

INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('Mary', 'Sue', 'msue76', 'p@$$w0rD', 'CUSTOMER');

--Rooms
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (11, 8, 'SINGLE', 'QUEEN', 2, 'MODERN', 110);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (12, 4, 'SINGLE', 'KING', 1, 'BOHEMIAN', 85);

--Credit Cards
INSERT INTO CreditCard(CardId, balance, climit) VALUES(1, 0, 10000);
INSERT INTO CreditCard(CardId, balance, climit) VALUES(2, 0, 100000);

--Reservations
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(2, 1, '2019-11-22', '2019-11-24');
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(3, 2, '2019-11-29', '2019-12-01');

--Rooms Reserved
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(1, 11, 4);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(1, 12, 2);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(2, 11, 2);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(2, 12, 1);

