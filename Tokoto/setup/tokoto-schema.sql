--------------------------------------------------------
--  File created - Monday-November-07-2011   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SEQ_KATEGORI
--------------------------------------------------------

   CREATE SEQUENCE  "TOKOTO"."SEQ_KATEGORI"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 5 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_PELANGGAN
--------------------------------------------------------

   CREATE SEQUENCE  "TOKOTO"."SEQ_PELANGGAN"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_PELANGGAN_ORDER
--------------------------------------------------------

   CREATE SEQUENCE  "TOKOTO"."SEQ_PELANGGAN_ORDER"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_PRODUK
--------------------------------------------------------

   CREATE SEQUENCE  "TOKOTO"."SEQ_PRODUK"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 9 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table KATEGORI
--------------------------------------------------------

  CREATE TABLE "TOKOTO"."KATEGORI" 
   (	"ID" NUMBER(3,0), 
	"NAMA" VARCHAR2(45 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Table ORDER_PRODUK
--------------------------------------------------------

  CREATE TABLE "TOKOTO"."ORDER_PRODUK" 
   (	"ID_PELANGGAN_ORDER" NUMBER(10,0), 
	"ID_PRODUK" NUMBER(10,0), 
	"QUANTITY" NUMBER(5,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Table PELANGGAN
--------------------------------------------------------

  CREATE TABLE "TOKOTO"."PELANGGAN" 
   (	"ID" NUMBER(10,0), 
	"NAMA" VARCHAR2(45 BYTE), 
	"EMAIL" VARCHAR2(45 BYTE), 
	"PHONE" VARCHAR2(45 BYTE), 
	"ALAMAT" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Table PELANGGAN_ORDER
--------------------------------------------------------

  CREATE TABLE "TOKOTO"."PELANGGAN_ORDER" 
   (	"ID" NUMBER(10,0), 
	"JUMLAH" NUMBER(12,2), 
	"TGL_ORDER" TIMESTAMP (6) DEFAULT SYSTIMESTAMP, 
	"NO_KONFIRMASI" NUMBER(10,0), 
	"ID_PELANGGAN" NUMBER(10,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Table PRODUK
--------------------------------------------------------

  CREATE TABLE "TOKOTO"."PRODUK" 
   (	"ID" NUMBER(10,0), 
	"NAMA" VARCHAR2(45 BYTE), 
	"HARGA" NUMBER(10,2), 
	"STOK" NUMBER(10,0), 
	"KETERANGAN" VARCHAR2(50 BYTE), 
	"TGL_UPDATE" DATE DEFAULT SYSDATE, 
	"ID_KATEGORI" NUMBER(3,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
REM INSERTING into TOKOTO.KATEGORI
SET DEFINE OFF;
Insert into TOKOTO.KATEGORI (ID,NAMA) values (1,'Pria');
Insert into TOKOTO.KATEGORI (ID,NAMA) values (2,'Wanita');
Insert into TOKOTO.KATEGORI (ID,NAMA) values (3,'Pasangan');
Insert into TOKOTO.KATEGORI (ID,NAMA) values (4,'Anak');
REM INSERTING into TOKOTO.ORDER_PRODUK
SET DEFINE OFF;
Insert into TOKOTO.ORDER_PRODUK (ID_PELANGGAN_ORDER,ID_PRODUK,QUANTITY) values (1,2,1);
Insert into TOKOTO.ORDER_PRODUK (ID_PELANGGAN_ORDER,ID_PRODUK,QUANTITY) values (1,1,1);
REM INSERTING into TOKOTO.PELANGGAN
SET DEFINE OFF;
Insert into TOKOTO.PELANGGAN (ID,NAMA,EMAIL,PHONE,ALAMAT) values (1,'ziez','mr_pupu@yahoo.com','916836336','dpu 85');
REM INSERTING into TOKOTO.PELANGGAN_ORDER
SET DEFINE OFF;
Insert into TOKOTO.PELANGGAN_ORDER (ID,JUMLAH,TGL_ORDER,NO_KONFIRMASI,ID_PELANGGAN) values (1,90003,to_timestamp('06-NOV-11 10.00.16.490000000 PM','DD-MON-RR HH.MI.SS.FF AM'),248500786,1);
REM INSERTING into TOKOTO.PRODUK
SET DEFINE OFF;
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (5,'BLS A1',50000,40,'Blouse',to_date('05-NOV-11','DD-MON-RR'),2);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (6,'BLS A2',50000,40,'Blouse',to_date('05-NOV-11','DD-MON-RR'),2);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (7,'BLS A3',60000,40,'Blouse',to_date('05-NOV-11','DD-MON-RR'),2);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (8,'CAR A1',40000,40,'Cardigan',to_date('05-NOV-11','DD-MON-RR'),2);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (1,'NEV A1',45000,50,'Kaos Nevada',to_date('05-NOV-11','DD-MON-RR'),1);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (2,'NEV A2',45000,50,'Kaos Nevada',to_date('05-NOV-11','DD-MON-RR'),1);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (3,'LAR A1',65000,50,'Kemeja Larusso',to_date('05-NOV-11','DD-MON-RR'),1);
Insert into TOKOTO.PRODUK (ID,NAMA,HARGA,STOK,KETERANGAN,TGL_UPDATE,ID_KATEGORI) values (4,'LAR A2',65000,50,'Kemeja Larusso',to_date('05-NOV-11','DD-MON-RR'),1);
--------------------------------------------------------
--  DDL for Index PK_KATEGORI
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."PK_KATEGORI" ON "TOKOTO"."KATEGORI" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Index PK_ORDER_PRODUK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."PK_ORDER_PRODUK" ON "TOKOTO"."ORDER_PRODUK" ("ID_PELANGGAN_ORDER", "ID_PRODUK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Index PK_PELANGGAN
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."PK_PELANGGAN" ON "TOKOTO"."PELANGGAN" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Index PK_PELANGGAN_ORDER
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."PK_PELANGGAN_ORDER" ON "TOKOTO"."PELANGGAN_ORDER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Index PK_PRODUK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."PK_PRODUK" ON "TOKOTO"."PRODUK" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  DDL for Index SYS_C005226
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOKOTO"."SYS_C005226" ON "TOKOTO"."PELANGGAN_ORDER" ("NO_KONFIRMASI") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO" ;
--------------------------------------------------------
--  Constraints for Table KATEGORI
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."KATEGORI" ADD CONSTRAINT "PK_KATEGORI" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."KATEGORI" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."KATEGORI" MODIFY ("NAMA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ORDER_PRODUK
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."ORDER_PRODUK" ADD CONSTRAINT "PK_ORDER_PRODUK" PRIMARY KEY ("ID_PELANGGAN_ORDER", "ID_PRODUK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."ORDER_PRODUK" MODIFY ("ID_PELANGGAN_ORDER" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."ORDER_PRODUK" MODIFY ("ID_PRODUK" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."ORDER_PRODUK" MODIFY ("QUANTITY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PELANGGAN
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."PELANGGAN" ADD CONSTRAINT "PK_PELANGGAN" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."PELANGGAN" MODIFY ("NAMA" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN" MODIFY ("EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN" MODIFY ("PHONE" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN" MODIFY ("ALAMAT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PELANGGAN_ORDER
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" ADD CONSTRAINT "PK_PELANGGAN_ORDER" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" MODIFY ("ID" NOT NULL DISABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" MODIFY ("JUMLAH" NOT NULL DISABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" MODIFY ("NO_KONFIRMASI" NOT NULL DISABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" MODIFY ("ID_PELANGGAN" NOT NULL DISABLE);
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" ADD UNIQUE ("NO_KONFIRMASI")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" MODIFY ("TGL_ORDER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PRODUK
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."PRODUK" ADD CONSTRAINT "PK_PRODUK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "TOKOTO"  ENABLE;
 
  ALTER TABLE "TOKOTO"."PRODUK" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PRODUK" MODIFY ("NAMA" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PRODUK" MODIFY ("HARGA" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PRODUK" MODIFY ("STOK" NOT NULL ENABLE);
 
  ALTER TABLE "TOKOTO"."PRODUK" MODIFY ("ID_KATEGORI" NOT NULL ENABLE);

--------------------------------------------------------
--  Ref Constraints for Table ORDER_PRODUK
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."ORDER_PRODUK" ADD CONSTRAINT "FK_ORDER_PRODUK_PLGN_ORDER" FOREIGN KEY ("ID_PELANGGAN_ORDER")
	  REFERENCES "TOKOTO"."PELANGGAN_ORDER" ("ID") ENABLE;
 
  ALTER TABLE "TOKOTO"."ORDER_PRODUK" ADD CONSTRAINT "FK_ORDER_PRODUK_PRODUK" FOREIGN KEY ("ID_PRODUK")
	  REFERENCES "TOKOTO"."PRODUK" ("ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table PELANGGAN_ORDER
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."PELANGGAN_ORDER" ADD CONSTRAINT "FK_PELANGGAN_ORDER_PELANGGAN" FOREIGN KEY ("ID_PELANGGAN")
	  REFERENCES "TOKOTO"."PELANGGAN" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUK
--------------------------------------------------------

  ALTER TABLE "TOKOTO"."PRODUK" ADD CONSTRAINT "FK_PRODUK_KATEGORI" FOREIGN KEY ("ID_KATEGORI")
	  REFERENCES "TOKOTO"."KATEGORI" ("ID") ENABLE;
--------------------------------------------------------
--  DDL for Procedure RESET_SEQUENCE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "TOKOTO"."RESET_SEQUENCE" (p_seq in varchar2)
is
    l_value number;
begin
-- Select the next value of the sequence
 
    execute immediate
    'select ' || p_seq ||
    '.nextval from dual' INTO l_value;
 
-- Set a negative increment for the sequence,
-- with value = the current value of the sequence
 
    execute immediate
    'alter sequence ' || p_seq ||
    ' increment by -' || l_value || ' minvalue 0';
 
-- Select once from the sequence, to
-- take its current value back to 0
 
    execute immediate
    'select ' || p_seq ||
    '.nextval from dual' INTO l_value;
 
-- Set the increment back to 1
 
    execute immediate
    'alter sequence ' || p_seq ||
    ' increment by 1 minvalue 0';
end;

/

