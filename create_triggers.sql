DELIMITER $
CREATE TRIGGER charge_credit_card
    AFTER INSERT ON RoomsReserved
    FOR EACH ROW
    BEGIN
        SET @cost = (SELECT DATEDIFF(CheckOut, CheckIn)*Price AS cost FROM RoomsReserved rr
                        JOIN Reservation r ON rr.ReservationId = r.ReservationId
                        JOIN Room ro ON rr.RoomId = ro.RoomId
                        WHERE rr.ReservationId = NEW.ReservationId AND rr.RoomId = NEW.RoomId);
        SET @card = (SELECT CardId FROM Reservation r WHERE r.ReservationId = NEW.ReservationId);

        IF((SELECT balance FROM CreditCard WHERE CardId = @card) + @cost > (SELECT climit FROM CreditCard WHERE CardId = @card)) THEN
            SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'Check constraint failed: CreditCards.balance would be greater than CreditCards.climit';
        END IF;

        UPDATE CreditCard SET balance = balance + @cost WHERE CardId = @card;
    END$
DELIMITER ;

DELIMITER $
CREATE TRIGGER refund_credit_card
    BEFORE DELETE ON RoomsReserved
    FOR EACH ROW
    BEGIN
        SET @cost = (SELECT DATEDIFF(CheckOut, CheckIn)*Price AS cost FROM RoomsReserved rr
                        JOIN Reservation r ON rr.ReservationId = r.ReservationId
                        JOIN Room ro ON rr.RoomId = ro.RoomId
                        WHERE rr.ReservationId = OLD.ReservationId AND rr.RoomId = OLD.RoomId);
        SET @card = (SELECT CardId FROM Reservation r WHERE r.ReservationId = OLD.ReservationId);

        UPDATE CreditCard SET balance = balance - @cost WHERE CardId = @card;
    END$
DELIMITER ;