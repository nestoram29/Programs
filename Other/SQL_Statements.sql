/*Compiled list of SQL statements used in Database Theory. */

INSERT INTO Product
	SELECT maker, model+1100, 'laptop' FROM Product WHERE type = 'pc';
INSERT INTO Laptop
	SELECT model+1100, speed, ram, hd, 17, price+500 FROM PC;

SELECT name FROM ships WHERE name IN (
SELECT class FROM classes WHERE displacement > 35000
);

SELECT class, displacement, numGuns FROM classes WHERE class IN (
	SELECT class FROM ships WHERE name IN (
		SELECT ship FROM outcomes WHERE battle = 'Guadalcanal'
	)
)

SELECT name FROM ships 
UNION
SELECT ship FROM outcomes;

SELECT distinct c1.country FROM classes as c1 JOIN classes as c2 on c1.country = c2.country AND c1.type != c2.type

SELECT COUNT(class) FROM classes;

SELECT AVG(numGuns) FROM classes;

SELECT country FROM classes WHERE numGuns = ANY (
SELECT MAX(numGuns) FROM classes
)

SELECT model, speed, hd FROM PC WHERE price < 1000;

SELECT model, speed AS gigahertz, hd as gigabytes FROM PC WHERE price < 1000;

SELECT DISTINCT maker FROM Product WHERE type = 'printer';

SELECT model, ram, screen FROM Laptop WHERE price > 1500;

SELECT * FROM Printer WHERE color = 1;

SELECT model, hd FROM PC WHERE speed = 3.2 AND price < 2000;

SELECT DISTINCT R.maker FROM Product as R WHERE EXISTS (
	SELECT P.model FROM PC as P WHERE P.speed >= 3.0 AND P.model = R.model);

SELECT DISTINCT maker FROM Product WHERE model IN(
	SELECT model FROM PC WHERE speed >= 3.0); 


SELECT model FROM Printer WHERE price = ANY (
	SELECT MAX(price) FROM Printer);
	
SELECT model FROM Printer WHERE price > ALL (
	SELECT P1.price FROM Printer AS P1 JOIN Printer AS P2 ON P1.price < P2.price); 


SELECT model FROM Laptop WHERE speed < ALL (
	SELECT speed FROM PC);

SELECT model FROM Laptop WHERE EXISTS (
	SELECT Laptop.speed FROM Laptop JOIN (
		SELECT MIN(speed) AS SPEED FROM PC
	) AS PC ON Laptop.speed < Pspeed); 


SELECT model FROM (
	SELECT model, price FROM PC WHERE price = ANY (
		SELECT MAX(price) FROM PC)
	UNION
	SELECT model, price FROM Printer WHERE price = ANY (
		SELECT MAX(price) FROM  Printer)
	UNION
	SELECT model, price FROM Laptop WHERE price = ANY (
		SELECT MAX(price) FROM Laptop)
) AS temp
WHERE price IN (
	SELECT MAX(price) FROM (
		SELECT model, price FROM PC WHERE price = ANY (
			SELECT MAX(price) FROM PC)
		UNION
		SELECT model, price FROM Printer WHERE price = ANY (
			SELECT MAX(price) FROM  Printer)
		UNION
		SELECT model, price FROM Laptop WHERE price = ANY (
			SELECT MAX(price) FROM Laptop)
) AS temp2
);

SELECT model FROM (
	SELECT model, price FROM PC WHERE price = All (
		SELECT MAX(price) FROM PC)
	UNION
	SELECT model, price FROM Printer WHERE price = ALL (
		SELECT MAX(price) FROM  Printer)
	UNION
	SELECT model, price FROM Laptop WHERE price = ALL (
		SELECT MAX(price) FROM Laptop)
) AS temp
WHERE price = ANY (
	SELECT MAX(price) FROM (
		SELECT model, price FROM PC WHERE price = ALL (
			SELECT MAX(price) FROM PC)
		UNION
		SELECT model, price FROM Printer WHERE price = ALL (
			SELECT MAX(price) FROM  Printer)
		UNION
		SELECT model, price FROM Laptop WHERE price = All (
			SELECT MAX(price) FROM Laptop)
) AS temp2
);


SELECT maker FROM Product WHERE model IN (
	SELECT model FROM Printer WHERE price IN (
		SELECT MIN(price) AS price FROM Printer WHERE price IN (SELECT price FROM Printer WHERE color = '1')
	)
);

SELECT DISTINCT maker FROM Product 
JOIN Printer ON Product.model = Printer.model 
WHERE Printer.color = '1' AND price = ANY (SELECT MIN(price) FROM Printer); 


SELECT maker FROM Product WHERE model IN (
	SELECT model FROM PC WHERE speed IN (
		SELECT MAX(speed) as speed FROM PC WHERE ram IN (
			SELECT MIN(ram) as ram FROM PC
		)
	)
	AND ram IN (
		SELECT MIN(ram) as ram FROM PC
	)
);

SELECT maker FROM Product WHERE model = ANY (
	SELECT model FROM PC WHERE speed = ANY (
		SELECT MAX(speed) as speed FROM PC WHERE ram = (
			SELECT MIN(ram) as ram FROM PC
		)
	)
	AND ram = (
		SELECT MIN(ram) as ram FROM PC
	)
);

INSERT INTO Product VALUES ('C', '1100', 'pc');

INSERT INTO PC VALUES ('1100', 3.2, 1024, 180, 2499); 

INSERT INTO Product
	SELECT maker, model+1100, 'laptop' FROM Product WHERE type = 'pc';
INSERT INTO Laptop
	SELECT model+1100, speed, ram, hd, 17, price+500 FROM PC; 


DELETE FROM PC WHERE hd < 100; 


DELETE FROM Laptop WHERE model = ANY (
	SELECT model FROM Product WHERE maker = ANY (
		SELECT DISTINCT maker FROM Product WHERE type = 'laptop' AND maker != ALL (
			SELECT DISTINCT maker FRom Product WHERe type = 'printer'
		)
	) AND type = 'laptop'
);
DELETE FROM Product WHERE model = ANY (
	SELECT model FROM Product WHERE maker = ANY (
		SELECT DISTINCT maker FROM Product WHERE type = 'laptop' AND maker != ALL (
			SELECT DISTINCT maker FRom Product WHERe type = 'printer'
		)
	) AND type = 'laptop'
); 


UPDATE Product SET maker = 'A' WHERE maker = 'B'; 


UPDATE PC SET ram = ram * 2, hd = hd + 60; 


UPDATE Laptop SET screen = screen + 1, price = price - 100 WHERE model = ANY (
	SELECT model FROM Product WHERE maker = 'B' AND type = 'laptop'
); 
