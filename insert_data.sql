-- Users
/*
INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('John', 'Doe', 'admin', 'admin', 'MANAGER');

INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('Bob', 'Jones', 'bjones74', 'password', 'CUSTOMER');

INSERT INTO User(FirstName, LastName, username, password, type)
   VALUES('Mary', 'Sue', 'msue76', 'p@$$w0rD', 'CUSTOMER');
*/
-- Rooms
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (101, 1, 'SINGLE', 'TWIN', 1, 'RUSTIC', 75.25);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (102, 1, 'SINGLE', 'TWIN_XL', 1, 'MODERN', 80.02);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (103, 3, 'DOUBLE', 'QUEEN', 1, 'MODERN', 94);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (104, 4, 'DOUBLE', 'KING', 1, 'MODERN', 102.2);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (105, 2, 'TWIN', 'TWIN', 2, 'MODERN', 93);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (106, 8, 'TWIN', 'KING', 2, 'MODERN', 110);

INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (201, 2, 'DOUBLE', 'DOUBLE', 1, 'INDUSTRIAL', 95.7);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (202, 4, 'DOUBLE', 'CA_KING', 1, 'INDUSTRIAL', 100.82);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (203, 8, 'TWIN', 'CA_KING', 2, 'INDUSTRIAL', 110);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (204, 2, 'TWIN', 'TWIN_XL', 2, 'RUSTIC', 90);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (205, 1, 'SINGLE', 'TWIN_XL', 1, 'RUSTIC', 75.5);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (206, 1, 'SINGLE', 'TWIN', 1, 'INDUSTRIAL', 75);

INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (301, 1, 'SINGLE', 'TWIN_XL', 1, 'SCANDINAVIAN', 70);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (302, 3, 'DOUBLE', 'QUEEN', 1, 'SCANDINAVIAN', 78.63);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (303, 6, 'TWIN', 'QUEEN', 2, 'BOHEMIAN', 102);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (304, 4, 'DOUBLE', 'CA_KING', 1, 'SCANDINAVIAN', 99.8);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (305, 2, 'DOUBLE', 'DOUBLE', 1, 'SCANDINAVIAN', 87);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (306, 4, 'DOUBLE', 'KING', 1, 'BOHEMIAN', 109.68);

INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (401, 1, 'SINGLE', 'TWIN_XL', 1, 'NAUTICAL', 82);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (402, 2, 'TWIN', 'TWIN', 2, 'NAUTICAL', 92.96);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (403, 8, 'TWIN', 'KING', 2, 'BOHEMIAN', 115);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (404, 4, 'DOUBLE', 'KING', 1, 'NAUTICAL', 105);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (405, 2, 'TWIN', 'TWIN_XL', 2, 'BOHEMIAN', 98.6);
INSERT INTO Room (RoomId, MaxOccupancy, RoomType, BedType, BedCount, Decor, Price) VALUES (406, 6, 'TWIN', 'QUEEN', 2, 'NAUTICAL', 106);

-- Credit Cards
/*
INSERT INTO CreditCard(CardId, balance, climit) VALUES(1, 0, 10000);
INSERT INTO CreditCard(CardId, balance, climit) VALUES(2, 0, 100000);
INSERT INTO CreditCard(CardId, balance, climit) VALUES(3, 0, 1500);
*/
-- Reservations
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(2, 1, '2019-11-22', '2019-11-24');
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(3, 2, '2019-11-29', '2019-12-01');
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(3, 2, '2019-12-15', '2019-12-18');
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(3, 3, '2019-12-09', '2019-12-10');
INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(2, 1, '2019-12-12', '2019-12-16');

-- Rooms Reserved
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(1, 104, 4);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(1, 105, 2);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(2, 302, 2);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(2, 305, 1);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(3, 406, 6);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(3, 304, 3);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(3, 403, 8);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(4, 201, 1);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(4, 205, 1);
INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(5, 402, 2);
