
  CREATE TABLE "M_USER" 
   (	"ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"DEPARTMENTNAME" VARCHAR2(20 BYTE), 
	"EMPLOYEEID" VARCHAR2(20 BYTE), 
	"DELFLAG" VARCHAR2(20 BYTE), 
	"CREDATE" VARCHAR2(20 BYTE), 
	"CRETIME" VARCHAR2(20 BYTE), 
	"CREPERSON" VARCHAR2(20 BYTE), 
	"CREPROID" VARCHAR2(20 BYTE), 
	 CONSTRAINT "M_USER_PK" PRIMARY KEY ("ID")
   );
   
   
  CREATE SEQUENCE  "SEQ_M_USER"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
 

  CREATE OR REPLACE TRIGGER "TRG_M_USER" 
  BEFORE INSERT ON M_USER
  FOR EACH ROW
  BEGIN
    SELECT SEQ_M_USER.nextval into :new.id from DUAL;
  END;


/
ALTER TRIGGER "TRG_M_USER" ENABLE;
 
