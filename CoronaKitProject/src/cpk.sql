DROP DATABASE cpkDb;

CREATE DATABASE cpkDb;

use cpkDb;

CREATE TABLE item_master(
	itemNo int primary key,
	itemName varchar(20) not null,
	itemDesc varChar(50) not null,
	itemPrice decimal not null
	);
	
INSERT INTO item_master VALUES
(001, "Sanitizer", "used to kill virus", 50),
(002, "Face Mask", "masks to prevent spreading of virus", 10),	
(003, "Immunity Booster", "medicine to increase immunity", 5);