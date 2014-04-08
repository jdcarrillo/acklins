/*
** Confidential property of Sybase, Inc.
** (c) Copyright Sybase, Inc. 1997-2004.
** All rights reserved
*/

/*
** SQL Anywhere 5.5.02 or higher
** 
** Requires Open Server Gateway that comes with SQL Anywhere 5.5.02 or
** higher
**
**
** Tables created:
**
**      Name
**      -----------------------
**      dbo.spt_jdatatype_info
**      dbo.jdbc_function_escapes
**      dbo.spt_mda
**      dbo.spt_jtext
**      dbo.spt_collation_map
**
**
** Global temporary tables created:
**
**      Name
**      -----------------------
**      dbo.jdbc_helpkeys
**      dbo.spt_jdbc_conversion
**      dbo.jdbc_tableprivileges
**      dbo.jdbc_columnprivileges
**      dbo.jdbc_indexhelp
**      dbo.jdbc_procedurecolumns
**
**
** Stored procedures created:
**
**      Name
**      -----------------------
**      dbo.sp_mda
**      dbo.sp_jdbc_fkeys
**      dbo.sp_jdbc_convert_datatype
**      dbo.sp_jdbc_getprocedurecolumns
**      dbo.sp_jdbc_primarykey
**      dbo.sp_jdbc_stored_procedures
**      dbo.sp_jdbc_gettableprivileges
**      dbo.sp_jdbc_getcolumnprivileges
**      dbo.sp_jdbc_getbestrowidentifier
**      dbo.sp_jdbc_getversioncolumns
**      dbo.sp_jdbc_getindexinfo
**      dbo.sp_default_charset
**      dbo.sp_jdbc_datatype_info
**      dbo.sp_jdbc_function_escapes
**      dbo.sp_jdbc_escapeliteralforlike
**      dbo.sp_jdbc_tables
**      dbo.sp_jdbc_getudts
**      dbo.sp_jdbc_class_for_name
**      dbo.sp_jdbc_classes_in_jar
**
**
** File Sections for use with the jConnect IsqlApp Sample:
**
**      Section Name  Description
**      ------------- ---------------------------------------
**      CLEANUP       Removes all of the tables/sprocs
**                    created by this script.
**
*/

set quoted_identifier on
go

// Create procedure to help clean up from previous runs.
// Note that objects might have been created under "dbo" or "DBA".

if exists (select * from SYS.SYSPROCEDURE where proc_name = 'sp_jconnect_cleanup')
    drop procedure dbo.sp_jconnect_cleanup
go

/** SECTION BEGIN: CLEANUP **/
create procedure dbo.sp_jconnect_cleanup(
    name       varchar(128),
    objtype         varchar(15))
begin
    declare dbo_id  int;

    select user_id into dbo_id from SYS.SYSUSERPERM where user_name='dbo';

    if objtype = 'table' then
     if exists (select * from SYS.SYSTABLE
             where table_name = name and creator = dbo_id) then
         execute immediate 'drop table dbo.' || name
     end if;

     if exists (select * from SYS.SYSTABLE
             where table_name = name and creator = 1) then
         execute immediate 'drop table DBA.' || name
     end if;
    end if;
    if objtype = 'procedure' then
     if exists (select * from SYS.SYSPROCEDURE
             where proc_name = name and creator = dbo_id) then
         execute immediate 'drop procedure dbo.' || name
     end if;

     if exists (select * from SYS.SYSPROCEDURE
             where proc_name = name and creator = 1) then
         execute immediate 'drop procedure DBA.' || name
     end if;
    end if;
    if exists (select * from SYS.SYSPROCEDURE
              where proc_name = name and creator = dbo_id) then
          execute immediate 'drop function dbo.' || name
    end if;
end
go
/** SECTION END: CLEANUP **/

// For 6.0, we must append 'in system' to statements creating temporary tables
// to ensure they are created in SQL Anywhere's dataspace.  This would cause
// a syntax error in 5.5, so we conditionally add the string in an
// EXECUTE IMMEDIATE statement.
create variable in_system char(10)
go
if ((substring( @@version, 1, 1 ) = '6') or (db_property( 'IQStore' ) = 'ON'))
then
    set in_system = ' in SYSTEM'
end if
go

// determine the ASA version.
// asa_version = 6 for ASA6+ and IQ otherwise = 5
create variable asa_version int
go
if ((convert (int, substring( @@version, 1, 1 )) >= 6) or (patindex('%IQ%',@@version) > 0))
then
    set asa_version = 6
else
    set asa_version = 5
end if
go


// 6.0 has a fix for trim and a different behavior for substring
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jconnect_trimit', 'function' )
go
/** SECTION END: CLEANUP **/

create function dbo.sp_jconnect_trimit (@iString varchar(255))
returns varchar(255)
begin
   if (db_property('FileVersion') > 36) then
   // this is 6.0 or later
       return(trim(@iString))
   else
       return(substring(@iString, 1, length(@iString)))
   end if;
end
go

grant execute on dbo.sp_jconnect_trimit to PUBLIC
go  

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'spt_jdatatype_info', 'table' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create table dbo.spt_jdatatype_info
(
 ss_dtype    tinyint not null,
 TYPE_NAME          varchar(30)  not null unique,
 DATA_TYPE    smallint not null,
 typelength         int          not null,
 LITERAL_PREFIX     varchar(32)  null,
 LITERAL_SUFFIX     varchar(32)  null,
 CREATE_PARAMS      varchar(32)  null,
 NULLABLE           smallint     not null,
 CASE_SENSITIVE     smallint     not null,
 SEARCHABLE         smallint     not null,
 UNSIGNED_ATTRIBUTE smallint     null,
 FIXED_PREC_SCALE   smallint     not null,
 -- MONEY              smallint     not null,
 AUTO_INCREMENT     smallint     null,
 LOCAL_TYPE_NAME    varchar(128) not null,
 MINIMUM_SCALE    smallint null,
 MAXIMUM_SCALE    smallint null,
 SQL_DATA_TYPE    smallint null,
 SQL_DATETIME_SUB   smallint null,
 NUM_PREC_RADIX    smallint null,
 is_unique	    bit not null
 -- INTERVAL_PRECISION smallint null
)'
    || in_system )
end
go

begin
execute (
'create unique index jdatatype_ind on dbo.spt_jdatatype_info(TYPE_NAME)'
    || in_system )
end
go

grant select on dbo.spt_jdatatype_info to PUBLIC
go

insert into dbo.spt_jdatatype_info values
(48, 'tinyint', -6, 3, NULL, NULL, NULL, 1, 0, 2, 1, 0, 0, 'tinyint',
NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(34, 'image', -4, 2147483647, '0x', NULL, NULL, 1, 0, 1, NULL, 0, NULL,
'image', NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(34, 'long binary', -4, 2147483647, '0x', NULL, NULL, 1, 0, 1, NULL, 0, NULL,
'long binary', NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(36, 'java.lang.Object', 1111, 2147483647, '''', '''', NULL, 1, 1, 0, NULL, 0, 
NULL, 'java.lang.Object', NULL, NULL, 1111, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(37, 'varbinary', -3, 255, '0x', NULL, 'max length', 1, 0, 2, NULL, 0,
NULL, 'varbinary', NULL, NULL, NULL, NULL, NULL, 1)
go
/*
** VARBINARY keyword is provided in ASA 6.0 for compatibility with ASE.
*/
insert into dbo.spt_jdatatype_info values
(45, 'binary', -2, 255, '0x', NULL, 'length', 1, 0, 2, NULL, 0, NULL,
'binary', NULL, NULL, NULL, NULL, NULL, 1)
go
/*
** ASE's text is 'long varchar' in ASA
**
** TEXT keyword is provided in ASA 6.0 for compatibility with ASE.
*/
insert into dbo.spt_jdatatype_info values
(35, 'text', -1, 2147483647, '''', '''', NULL, 1, 1, 1, NULL, 0, NULL,
'text', NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(35, 'long varchar', -1, 2147483647, '''', '''', NULL, 1, 1, 1, NULL, 0, NULL,
'long varchar', NULL, NULL, NULL, NULL, NULL, 0)
go
insert into dbo.spt_jdatatype_info values
(50, 'bit', -7, 1, NULL, NULL, NULL, 0, 0, 2, NULL, 0, NULL,
'bit', NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(47, 'char', 1, 255, '''', '''', 'length', 1, 1, 3, NULL, 0, NULL,
'char', NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(63, 'numeric', 2, 38, NULL, NULL, 'precision,scale', 1, 0, 2, 0, 0, 0,
'numeric', 0, 38, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(55, 'decimal', 3, 38, NULL, NULL, 'precision,scale', 1, 0, 2, 0, 0, 0,
'decimal', 0, 38, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(60, 'money', 3, 12, '$', NULL, NULL, 1, 0, 2, 0, 1, 0, 'money', 4,
4, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(122, 'smallmoney', 3, 8, '$', NULL, NULL, 1, 0, 2, 0, 1, 0,
'smallmoney', 4, 4, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(56, 'int', 4, 4, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'int', NULL, NULL,
NULL, NULL, NULL, 1)
go
/* Added because in some ASA system tables int is integer. Makes it a lot 
** easier in subqueries */
insert into dbo.spt_jdatatype_info values
(56, 'integer', 4, 4, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'integer',NULL, NULL,
NULL, NULL, NULL, 0)
go
insert into dbo.spt_jdatatype_info values
(52, 'smallint', 5, 2, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'smallint',
NULL, NULL, NULL, NULL, NULL, 1)
go
/* the following rows are ASA 6.x-only, so the 1st column, ss_dtype
** must be unique to this table, but doesn't have much meaning
** outside of here. These ASA 6.x-only rows are:
** bigint, unsigned int, unsigned smallint, unsigned bigint */
insert into dbo.spt_jdatatype_info values
(64, 'bigint', -5, 8, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'bigint',
NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(57, 'unsigned int', -9, 4, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'unsigned int',
NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(53, 'unsigned smallint', -10, 2, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'unsigned smallint',
NULL, NULL, NULL, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(65, 'unsigned bigint', -11, 8, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'unsigned bigint',
NULL, NULL, NULL, NULL, NULL, 1)
go
/** end ASA-only types */
insert into dbo.spt_jdatatype_info values
(62, 'float', 8, 8, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0,
'double', NULL, NULL, NULL, NULL, 10, 1)
go
insert into dbo.spt_jdatatype_info values
(59, 'real', 7, 7, NULL, NULL, NULL, 1, 0, 2, 0, 0, 0, 'float', NULL,
NULL, NULL, NULL, 10, 1)
go
insert into dbo.spt_jdatatype_info values
(61, 'datetime', 93, 23, '''', '''', NULL, 1, 0, 3, NULL, 0, NULL,
'datetime', NULL, NULL, 93, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(58, 'smalldatetime', 93, 16, '''', '''', NULL, 1, 0, 3, NULL, 0, NULL,
'smalldatetime', NULL, NULL, 93, NULL, NULL, 1)
go
insert into dbo.spt_jdatatype_info values
(61, 'timestamp', 93, 23, '''', '''', NULL, 1, 0, 3, NULL, 0, NULL,   
'timestamp', NULL, NULL, 93, NULL, NULL, 0)
go
insert into dbo.spt_jdatatype_info values
(61, 'time', 92, 23, '''', '''', NULL, 1, 0, 3, NULL, 0, NULL,   
'time', NULL, NULL, 93, NULL, NULL, 0)
go
insert into dbo.spt_jdatatype_info values
(61, 'date', 91, 23, '''', '''', NULL, 1, 0, 3, NULL, 0, NULL,   
'date', NULL, NULL, 93, NULL, NULL, 0)
go
insert into dbo.spt_jdatatype_info values
(39, 'varchar', 12, 255, '''', '''', 'max length', 1, 1, 3, NULL, 0,
NULL, 'varchar', NULL, NULL, NULL, NULL, NULL, 1)
go
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_datatype_info', 'procedure' )
go
/** SECTION END: CLEANUP **/

set temporary option quoted_identifier = 'ON'
go
create procedure dbo.sp_jdbc_datatype_info
as
 select
     TYPE_NAME,
     DATA_TYPE,
     typelength as "PRECISION",
     LITERAL_PREFIX,
     LITERAL_SUFFIX,
     CREATE_PARAMS,
     NULLABLE,
     CASE_SENSITIVE,
     SEARCHABLE,
     UNSIGNED_ATTRIBUTE,
     FIXED_PREC_SCALE,
     AUTO_INCREMENT,
     LOCAL_TYPE_NAME,
     MINIMUM_SCALE,
     MAXIMUM_SCALE,
     SQL_DATA_TYPE,
     SQL_DATETIME_SUB,
     NUM_PREC_RADIX
    -- INTERVAL_PRECISION
 from dbo.spt_jdatatype_info
 order by DATA_TYPE
return (0)
go

grant execute on dbo.sp_jdbc_datatype_info to PUBLIC
go
/*
** This script creates a table which is used by jCONNECT to
** obtain information on this specific server types implementation
** of the various static functions for which JDBC provides escape
** sequences.
**
** Each row has two columns.  Escape_name is the name of the
** static function escape.  Map_string is a string showing how the
** function call should be sent to the server.  %i is a placeholder
** for the i'th argument to the escape.  This numbering is used
** to support skipping arguments.  Reordering of arguments is NOT
** supported.  Thus, a map string of 'foo(%2)' is ok (skips first
** argument); 'foo(%2, %1)' is not ok, at least until the driver
** changes to support this.
**
** Don't include rows for unsupported functions.
**
** Three escapes, convert, timestampadd, and timestampdiff, have
** one argument which takes special constant values.  These constants
** may also need to be mapped.  Therefore, include one row for each
** possible constant value, using the concatenation of the function name
** and the constant value as the escape_name column.  E.g.:
** convertsql_binary, convertsql_bit, convertsql_char, etc.
** DO count the constant in figuring argument numbers.  Thus,
** timestampadd(sql_tsi_second, ts, ts) gets the map string
** 'dateadd(ss, %2, %3)')
**
** Use lower case for the escape name.  Use whatever case you
** need to for the map string.
**
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_function_escapes', 'table' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create table dbo.jdbc_function_escapes (escape_name varchar(40) primary key,
    map_string varchar(40))'
    || in_system )
end
go

grant select on dbo.jdbc_function_escapes to PUBLIC
go

/* don't bother inserting rows for unsupported functions
** insert into dbo.jdbc_function_escapes values ('replace', null)
** insert into dbo.jdbc_function_escapes values ('dayofyear', 'datepart(dy, %1)')
** insert into dbo.jdbc_function_escapes values ('week', 'week(%1)')
** insert into dbo.jdbc_function_escapes values ('insert','insert(%1, %2, %3)')
** insert into dbo.jdbc_function_escapes values (timestampaddsql_tsi_frac_second,
**                                           seconds(%3, %2))
** To add fract_second we cannot use seconds/1000 because of rounding problems
**
** There's a bug in the trim function either in SA or dbos.
** Better take it out than return wrong values
** insert into dbo.jdbc_function_escapes values ('rtrim', 'rtrim(%1)')
** insert into dbo.jdbc_function_escapes values ('ltrim', 'ltrim(%1)')
**
*/
insert into dbo.jdbc_function_escapes values ('locate', 'locate (%2, %1)')
go
insert into dbo.jdbc_function_escapes values ('user', 'user_name()')
go
insert into dbo.jdbc_function_escapes values ('abs', 'abs(%1)')
go
insert into dbo.jdbc_function_escapes values ('acos', 'acos(%1)')
go
insert into dbo.jdbc_function_escapes values ('asin', 'asin(%1)')
go
insert into dbo.jdbc_function_escapes values ('atan', 'atan(%1)')
go
insert into dbo.jdbc_function_escapes values ('atan2', 'atn2(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('ceiling', 'ceiling(%1)')
go
insert into dbo.jdbc_function_escapes values ('cos', 'cos(%1)')
go
insert into dbo.jdbc_function_escapes values ('cot', 'cot(%1)')
go
insert into dbo.jdbc_function_escapes values ('degrees', 'degrees(%1)')
go
insert into dbo.jdbc_function_escapes values ('exp', 'exp(%1)')
go
insert into dbo.jdbc_function_escapes values ('floor', 'floor(%1)')
go
insert into dbo.jdbc_function_escapes values ('log', 'log(%1)')
go
insert into dbo.jdbc_function_escapes values ('log10', 'log10(%1)')
go
insert into dbo.jdbc_function_escapes values ('mod', 'mod(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('pi', 'pi()')
go
insert into dbo.jdbc_function_escapes values ('power', 'power(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('radians', 'radians(%1)')
go
insert into dbo.jdbc_function_escapes values ('rand', 'rand(%1)')
go
insert into dbo.jdbc_function_escapes values ('round', 'round(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('sign', 'sign(%1)')
go
insert into dbo.jdbc_function_escapes values ('sin', 'sin(%1)')
go
insert into dbo.jdbc_function_escapes values ('sqrt', 'sqrt(%1)')
go
insert into dbo.jdbc_function_escapes values ('tan', 'tan(%1)')
go
insert into dbo.jdbc_function_escapes values ('truncate', '"truncate"(%1,%2)')
go
insert into dbo.jdbc_function_escapes values ('ascii', 'ascii(%1)')
go
insert into dbo.jdbc_function_escapes values ('char', 'char(%1)')
go
insert into dbo.jdbc_function_escapes values ('concat', 'string(%1,%2)')
go
insert into dbo.jdbc_function_escapes values ('difference', 'difference(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('length', 'length(%1)')
go
insert into dbo.jdbc_function_escapes values ('lcase', 'lcase(%1)')
go
insert into dbo.jdbc_function_escapes values ('repeat', 'replicate(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('right', 'right(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('left', 'left(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('soundex', 'soundex(%1)')
go
insert into dbo.jdbc_function_escapes values ('space', 'space(%1)')
go
insert into dbo.jdbc_function_escapes values ('substring', 'substring(%1, %2, %3)')
go
insert into dbo.jdbc_function_escapes values ('ucase', 'ucase(%1)')
go
insert into dbo.jdbc_function_escapes values ('curdate', 'today(*)')
go
insert into dbo.jdbc_function_escapes values ('curtime', 'now(*)')
go
insert into dbo.jdbc_function_escapes values ('dayname', 'dayname(%1)')
go
insert into dbo.jdbc_function_escapes values ('dayofmonth', 'day(%1)')
go
insert into dbo.jdbc_function_escapes values ('dayofweek', 'dow(%1)')
go
insert into dbo.jdbc_function_escapes values ('hour', 'hour(%1)')
go
insert into dbo.jdbc_function_escapes values ('minute', 'minute(%1)')
go
insert into dbo.jdbc_function_escapes values ('month', 'month(%1)')
go
insert into dbo.jdbc_function_escapes values ('monthname', 'monthname(%1)')
go
insert into dbo.jdbc_function_escapes values ('now', 'now(*)')
go
insert into dbo.jdbc_function_escapes values ('quarter', 'quarter(%1)')
go
insert into dbo.jdbc_function_escapes values ('second', 'second(%1)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_second', 'seconds(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_minute', 'minutes(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_hour', 'hours(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_day', 'days(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_week', 'weeks(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_month', 'months(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_quarter', 'months(datetime(%3), %2*3)')
go
insert into dbo.jdbc_function_escapes values ('timestampaddsql_tsi_year', 'years(datetime(%3), %2)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_frac_second', 'seconds(%2, %3)*1000')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_second', 'seconds(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_minute', 'minutes(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_hour', 'hours(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_day', 'days(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_week', 'weeks(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_month', 'months(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_quarter', 'months(%2, %3) / 3')
go
insert into dbo.jdbc_function_escapes values ('timestampdiffsql_tsi_year', 'years(%2, %3)')
go
insert into dbo.jdbc_function_escapes values ('year', 'year(%1)')
go
insert into dbo.jdbc_function_escapes values ('database', 'db_name()')
go
insert into dbo.jdbc_function_escapes values ('ifnull', 'isnull(%1, %2)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_binary', 'convert(varbinary(255), %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_bit', 'convert(bit, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_char', 'convert(varchar(255), %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_date', 'date(%1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_decimal', 'convert(decimal, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_bigdecimal', 'convert(decimal,%1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_double', 'convert(double, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_float', 'convert(double, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_integer', 'convert(int, %1)')
go
/*
** XXX this is wrong, but what is right? -dlc-
** insert into dbo.jdbc_function_escapes values ('convertsql_longvarbinary',
** 'convert(longvarbinary, %1)')
*/
insert into dbo.jdbc_function_escapes values ('convertsql_longvarbinary', 'convert(varbinary(255), %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_longvarchar', 'convert(longvarchar, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_real', 'convert(real, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_smallint', 'convert(smallint, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_time', 'datetime(%1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_timestamp', 'datetime(%1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_tinyint', 'convert(tinyint, %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_varbinary', 'convert(varbinary(255), %1)')
go
insert into dbo.jdbc_function_escapes values ('convertsql_varchar', 'convert(varchar(255), %1)')
go

commit
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_function_escapes', 'procedure' )
go
/** SECTION END: CLEANUP **/

create procedure dbo.sp_jdbc_function_escapes
as
    select * from dbo.jdbc_function_escapes
go

grant execute on dbo.sp_jdbc_function_escapes to PUBLIC
go


/*
**  dbo.sp_jdbc_escapeliteralforlike
*/

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_escapeliteralforlike', 'procedure' )
go
/** SECTION END: CLEANUP **/

/*
** This is a utility procedure which takes an input string
** and places the escape character '\' before any symbol
** which needs to be a literal when used in a LIKE clause.
*/
create procedure dbo.sp_jdbc_escapeliteralforlike (INOUT @pString
varchar(255))
as
    declare @newString    varchar(255)
    declare @validEscapes varchar(255)
    declare @escapeChar   varchar(10)
    declare @pIndex       int
    declare @pLength      int
    declare @curChar      char(1)
    declare @escapeIndex  int
    declare @escapeLength int
    declare @boolEscapeIt int

    select @pLength = length(@pString)
    if (@pString is null) or (@pLength = 0)
    begin
        return
    end
    // quick way to break out when there are no escapes.
    declare @work_to_do int
    select @work_to_do =
	locate(@pString,'%')+
	locate(@pString,'_')+
	locate(@pString,'\')+
	locate(@pString,'[')+
	locate(@pString,']')
    if @work_to_do = 0
    return

    /*
    ** we will use the backslash as our escape
    ** character
    */
    select @escapeChar = '\'

    /*
    ** valid escape characters
    */
    select @validEscapes = '%_\[]'
    select @escapeLength = length(@validEscapes)

    /* start at the beginning of the string */
    select @pIndex = 1
    select @newString = ''

    while(@pIndex <= @pLength)
    begin
        /*
        ** get the next character of the string
        */
        select @curChar = substring(@pString, @pIndex, 1)

        /*
        ** loop through all of the escape characters and
        ** see if the character needs to be escaped
        */
        select @escapeIndex = 1
        select @boolEscapeIt = 0
        while(@escapeIndex <= @escapeLength)
        begin
            /* see if this is a match */
            if (substring(@validEscapes, @escapeIndex, 1) =
                @curChar)
            begin
                select @boolEscapeIt = 1
                break
            end
            /* move on to the next escape character */
            select @escapeIndex = @escapeIndex + 1
        end

        /* build the string */
        if (@boolEscapeIt = 1)
        begin
            select @newString = @newString + @escapeChar + @curChar
        end
        else
        begin
            select @newString = @newString + @curChar
        end

        /* go on to the next character in our source string */
        select @pIndex = @pIndex + 1
    end

    /* return to new string to the caller */
    select @pString = ltrim(rtrim(@newString))
go

grant execute on dbo.sp_jdbc_escapeliteralforlike to PUBLIC
go

/*
**  End of dbo.sp_jdbc_escapeliteralforlike
*/


/*
**   dbo.sp_jdbc_tables
**
*/

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_tablehelp', 'table' )
go
call dbo.sp_jconnect_cleanup( 'sp_jdbc_tables', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_tablehelp 
              (TABLE_CAT varchar(128), TABLE_SCHEM varchar(128),TABLE_NAME varchar(128),
               TABLE_TYPE varchar(64), REMARKS varchar(128) null)'
    || in_system )
end
go
create procedure dbo.sp_jdbc_tables
 @table_name  varchar(128)  = null,
 @table_owner varchar(128)  = null,
 @table_qualifier        varchar(128)  = null,
 @table_type  varchar(64) = null
as
declare @id int
declare @searchstr varchar(10)
declare @searchstr0 varchar(10)
declare @searchstr1 varchar(10)
declare @searchstr2 varchar(10)
declare @table_owner0 varchar(7)

if @table_name is null select @table_name = '%'
if @table_owner is null select @table_owner = '%'

/* 140798 return an empty result set if no tables match pattern, not error
**if (select count(*) from dbo.sysobjects where user_name(uid) like @table_owner
**                    and name like @table_name) = 0
**begin
**  raiserror 17208
**    'There is no object with the specified owner/name combination'
**  return(1)
**end
*/

if (patindex('%system%', lcase(@table_type)) > 0) 
    select @searchstr = 'TABLE'
else 
    select @searchstr = ''

if  ((patindex('%table%',lcase(@table_type)) > 0) OR
     (patindex('%base%',lcase(@table_type)) > 0))
    select @searchstr0 = 'TABLE'
else 
    select @searchstr0 = ''

if (patindex('%view%',lcase(@table_type)) > 0) 
    select @searchstr1 = 'VIEW'
else
    select @searchstr1 = ''

if (patindex('%temp%',lcase(@table_type)) > 0) 
    select @searchstr2 = '%TEMP%'
else
    select @searchstr2 = ''

if @table_type is null 
begin
    select @searchstr = '%' 
    select @searchstr0 = '%' 
    select @searchstr1 = '%' 
    select @searchstr2 = '%' 
end

if ((@searchstr = '') and (@searchstr0 = '') and (@searchstr1 = '') and 
    (@searchstr2 = '') and (@table_type is not null))
begin
        raiserror 99998 'Valid table types: TABLE, BASE, SYSTEM, VIEW, GLOBAL TEMPORARY or null'
        return(3)
end

begin transaction

/* System Tables */
if (@searchstr != '')
begin
    insert into jdbc_tablehelp
    select db_name(), creator, tname, 'SYSTEM TABLE', remarks
    from  SYS.SYSCATALOG
    where tname like @table_name ESCAPE '\' 
    and creator = 'SYS' and creator like @table_owner ESCAPE '\' 
    and tabletype = 'TABLE'
end

/* Usual Tables */
if (@searchstr0 != '')
begin
    insert into jdbc_tablehelp
    select db_name(), creator, tname, tabletype, remarks
    from  SYS.SYSCATALOG
    where tname like @table_name ESCAPE '\' 
    and creator like @table_owner ESCAPE '\' 
    and creator != 'SYS' and tabletype = 'TABLE'
end

/* Views and temptables */
if ((@searchstr1 != '') or (@searchstr2 != ''))
begin
    insert into jdbc_tablehelp
    select db_name(), creator, tname, tabletype, remarks
    from  SYS.SYSCATALOG
    where tname like @table_name ESCAPE '\' 
    and creator like @table_owner ESCAPE '\' 
    and ((tabletype like @searchstr1) or (tabletype like @searchstr2))
    and tabletype != 'TABLE'
end

select 
    TABLE_CAT = sp_jconnect_trimit(TABLE_CAT),
    TABLE_SCHEM = sp_jconnect_trimit(TABLE_SCHEM),
    TABLE_NAME = sp_jconnect_trimit(TABLE_NAME),
    TABLE_TYPE = sp_jconnect_trimit(TABLE_TYPE),
    REMARKS = sp_jconnect_trimit(REMARKS)
from jdbc_tablehelp
order by TABLE_TYPE, TABLE_SCHEM, TABLE_NAME
rollback transaction
go
grant execute on dbo.sp_jdbc_tables to PUBLIC
go

/*
**   dbo.sp_jdbc_columns
**
*/

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_columns', 'procedure' )
go
/** SECTION END: CLEANUP **/

CREATE PROCEDURE dbo.sp_jdbc_columns (          
     @table_name  varchar(128),
     @table_owner  varchar(128) = null,
     @table_qualifier varchar(128) = null,
     @column_name  varchar(128) = null )
AS
declare @tableid int
declare @columnid int
declare @id int

if @column_name is null select @column_name = '%'
if @table_name is null select @table_name = '%'
if @table_owner is null select @table_owner = '%'

select  TABLE_CAT =     sp_jconnect_trimit(db_name()),
        TABLE_SCHEM =   sp_jconnect_trimit(USER_NAME(creator)),
        TABLE_NAME =    sp_jconnect_trimit(table_name),
        COLUMN_NAME =   sp_jconnect_trimit(column_name),
        DATA_TYPE =     (select DATA_TYPE from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (
                               select if domain_name='integer'
                                      then 'int' else domain_name endif)),
	/* 109 is where the object stuff starts. Otherwise datetime is not a 
           timestamp anymore */
	TYPE_NAME =  sp_jconnect_trimit(
                           if ((user_type != null) and (user_type > 108))
			   then (select type_name from SYS.SYSUSERTYPE
                                where type_id=c.user_type)
			   else (select TYPE_NAME from dbo.spt_jdatatype_info
                                where LOCAL_TYPE_NAME = (select 
                                      if domain_name='integer' then 'int' 
                                      else domain_name endif))
			   endif),
        COLUMN_SIZE =   (select if DATA_TYPE in (12,3,2,1) then width else
                        (select typelength from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (select 
                               if domain_name='integer'
                               then 'int' else domain_name endif)) endif),
        BUFFER_LENGTH = (select if DATA_TYPE in (12,3,2,1) then width else
                        (select typelength from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (select 
                               if domain_name='integer'
                               then 'int' else domain_name endif)) endif),
        DECIMAL_DIGITS= isnull(scale,0),
        NUM_PREC_RADIX= (select NUM_PREC_RADIX from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (select 
                               if domain_name='integer'
                               then 'int' else domain_name endif)),
        NULLABLE =      (select if nulls = 'N' then 0 else 1 endif),
        REMARKS =       c.remarks,
        COLUMN_DEF =    c."default",
        SQL_DATA_TYPE = (select SQL_DATA_TYPE from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (select 
                               if domain_name='integer'
                               then 'int' else domain_name endif)),
        SQL_DATETIME_SUB=(select SQL_DATETIME_SUB from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (select 
                               if domain_name='integer'
                               then 'int' else domain_name endif)),
        CHAR_OCTET_LENGTH= (select if DATA_TYPE in (12,1,-1) then
                               width else 0 endif),
        ORDINAL_POSITION = column_id,
        IS_NULLABLE =    (select if nulls = 'N' then 'NO' else 'YES' endif)
 from SYS.SYSCOLUMN c  == SYS.SYSTABLE t, SYS.SYSDOMAIN == SYS.SYSCOLUMN c
 where t.table_name like @table_name ESCAPE '\'
 and USER_NAME(creator) like @table_owner ESCAPE '\'
 and c.column_name like @column_name ESCAPE '\'
 order by TABLE_SCHEM, TABLE_NAME, ORDINAL_POSITION     
go
grant execute on dbo.sp_jdbc_columns to PUBLIC
go

/*
**   dbo.sp_mda
**
*/

/*
** create the table of metadata access info
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'spt_mda', 'table' )
go
/** SECTION END: CLEANUP **/

/*
** querytype: 1 == RPC, 2 == LANGUAGE, 3 == NOT_SUPPORTED,
**            4 == LITERAL (boolean), 5 == LITERAL (integer),
**            6 == LITERAL (string), 7 == LITERAL (string not tokenizable)
**
** note: querytypes 4 through 6 were added in version level 4
**       of the metadata access.
** note: querytype 7 was added in version level 5 of the metadata access
**	 to fix 168844
*/
begin
execute (
'create table dbo.spt_mda (mdinfo varchar(30) not null, querytype tinyint,
    query varchar(255) null, mdaver_start tinyint,mdaver_end tinyint not null, 
    srvver_start int, srvver_end int not null)'
    || in_system )
end
go

create unique index spt_mda_ind
    on dbo.spt_mda (mdinfo, mdaver_end, srvver_end)
go

grant select on dbo.spt_mda to PUBLIC
go

insert dbo.spt_mda values ('CLASSFORNAME', 1, 'dbo.sp_jdbc_class_for_name(?)', 1, 7, 6, -1)
go
insert dbo.spt_mda values ('CLASSESINJAR', 1, 'dbo.sp_jdbc_classes_in_jar(?)', 1, 7, 6, -1)
go

insert dbo.spt_mda values ('CANRETURNJARS', 2, 'select 0', 1, 3, 0, -1)
go

insert dbo.spt_mda values ('CANRETURNJARS', 4, '0', 4, 7, 0, -1)
go

insert dbo.spt_mda values ('FUNCTIONCALL', 1, 'dbo.sp_jdbc_function_escapes', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('TYPEINFO', 1, 'dbo.sp_jdbc_datatype_info', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('TABLES', 1, 'dbo.sp_jdbc_tables(?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('COLUMNS', 1, 'dbo.sp_jdbc_columns(?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('IMPORTEDKEYS', 1, 'dbo.sp_jdbc_importkey(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('EXPORTEDKEYS', 1, 'dbo.sp_jdbc_exportkey(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('PRIMARYKEYS', 1, 'dbo.sp_jdbc_primarykey(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('PRODUCTNAME', 2, 'select property(''ProductName'')', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('ISREADONLY', 2, 'select 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ISREADONLY', 4, '0', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('ALLPROCSCALLABLE', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ALLPROCSCALLABLE', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('ALLTABLESSELECTABLE', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ALLTABLESSELECTABLE', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('COLUMNALIASING', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('COLUMNALIASING', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('IDENTIFIERQUOTE', 2, 'select ''"''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('IDENTIFIERQUOTE', 6, '"', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('ALTERTABLESUPPORT', 2, 'select 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ALTERTABLESUPPORT', 4, '1, 1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('CONNECTCONFIG', 2, 'set chained off set quoted_identifier on set textsize 2147483647', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('CONVERTSUPPORT', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CONVERTSUPPORT', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('CONVERTMAP', 1, 'dbo.sp_jdbc_convert_datatype(?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('LIKEESCAPECLAUSE', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('LIKEESCAPECLAUSE', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MULTIPLERESULTSETS', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MULTIPLERESULTSETS', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MULTIPLETRANSACTIONS', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MULTIPLETRANSACTIONS', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('NONNULLABLECOLUMNS', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('NONNULLABLECOLUMNS', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('POSITIONEDDELETE', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('POSITIONEDDELETE', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('POSITIONEDUPDATE', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('POSITIONEDUPDATE', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('STOREDPROCEDURES', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('STOREDPROCEDURES', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('SELECTFORUPDATE', 2, 'select 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SELECTFORUPDATE', 4, '0', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('CURSORTRANSACTIONS', 2, 'select 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CURSORTRANSACTIONS', 4, '0, 0', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('STATEMENTTRANSACTIONS', 2, 'select 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('STATEMENTTRANSACTIONS', 4, '1, 1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONSUPPORT', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONSUPPORT', 4, '1', 4, 7, 0, -1)
go
// note - transaction levels here match Connection.TRANSACTION* values
insert dbo.spt_mda values ('TRANSACTIONLEVELS', 2, 'select 1, 2, 4, 8', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONLEVELS', 5, '1, 2, 4, 8', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONLEVELDEFAULT', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONLEVELDEFAULT', 5, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_ISOLATION', 2, 'set temporary option isolation_level = ', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('GET_ISOLATION', 2, 'begin tran select @@isolation commit', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_ROWCOUNT', 2, 'set rowcount ?', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('GET_AUTOCOMMIT', 2, 'begin tran select @@tranchained commit', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_AUTOCOMMIT_ON', 2, 'set chained off', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_AUTOCOMMIT_OFF', 2, 'set chained on', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_READONLY_TRUE', 3, '', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_READONLY_FALSE', 3, '', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('SET_CATALOG', 3, 'use ?', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('GET_CATALOG', 2, 'select db_name()', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('NULLSORTING', 2, 'select 0, 0, 1, 0', 1, 3, 0, 7)
go
insert dbo.spt_mda values ('NULLSORTING', 2, 'select 0, 1, 0, 0', 1, 3, 8, -1)
go
insert dbo.spt_mda values ('NULLSORTING', 4, '0, 0, 1, 0', 4, 7, 0, 7)
go
insert dbo.spt_mda values ('NULLSORTING', 4, '0, 1, 0, 0', 4, 7, 8, -1)
go
insert dbo.spt_mda values ('PRODUCTVERSION', 2, 'select @@version', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('USERNAME', 2, 'select sp_jconnect_trimit(user_name())', 1, 7, 0, -1)  
go
insert dbo.spt_mda values ('FILEUSAGE', 2, 'select 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('FILEUSAGE', 4, '0, 0', 4, 7, 0, -1)
go
if ((select case_sensitivity from SYS.SYSINFO) = 'Y')
               /* Case insensitive */
begin
  insert dbo.spt_mda values ('IDENTIFIERCASES', 2, 'select 0, 0, 0, 1, 1, 0, 0, 1', 1, 3, 0, -1)
  insert dbo.spt_mda values ('IDENTIFIERCASES', 4, '0, 0, 0, 1, 1, 0, 0, 1', 4, 7, 0, -1)
end
else                /* case sensitive */
begin
  insert dbo.spt_mda values ('IDENTIFIERCASES', 2, 'select 1, 0, 0, 0, 1, 0, 0, 0', 1, 3, 0, -1)
  insert dbo.spt_mda values ('IDENTIFIERCASES', 4, '1, 0, 0, 0, 1, 0, 0, 0', 4, 7, 0, -1)
end
go
insert dbo.spt_mda values ('SQLKEYWORDS', 2, 'select value from dbo.spt_jtext where mdinfo = ''SQLKEYWORDS''', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('NUMERICFUNCTIONLIST', 2, 'select ''abs,acos,asin,atan,atan2,ceiling,cos,cot,degrees,exp,floor,log,log10,pi,power,radians,rand,round,sign,sin,sqrt,tan''', 1, 4, 0, -1)
go
insert dbo.spt_mda values ('NUMERICFUNCTIONLIST', 7, 'abs,acos,asin,atan,atan2,ceiling,cos,cot,degrees,exp,floor,log,log10,pi,power,radians,rand,round,sign,sin,sqrt,tan', 5, 7, 0, -1)
go
insert dbo.spt_mda values ('STRINGFUNCTIONLIST', 2, 'select ''ascii,char,concat,difference,length,lcase,repeat,right,soundex,space,substring,ucase''', 1, 4, 0, -1)
go
insert dbo.spt_mda values ('STRINGFUNCTIONLIST', 7, 'ascii,char,concat,difference,length,lcase,repeat,right,soundex,space,substring,ucase', 5, 7, 0, -1)
go
insert dbo.spt_mda values ('SYSTEMFUNCTIONLIST', 2, 'select ''database,ifnull,user,convert''', 1, 4, 0, -1)
go
insert dbo.spt_mda values ('SYSTEMFUNCTIONLIST', 7, 'database,ifnull,user,convert', 5, 7, 0, -1)
go
insert dbo.spt_mda values ('TIMEDATEFUNCTIONLIST', 2, 'select ''curdate,curtime,dayname,dayofmonth,dayofweek,hour,minute,month,monthname,now,quarter,second,timestampadd,timestampdiff,year''', 1, 4, 0, -1)
go
insert dbo.spt_mda values ('TIMEDATEFUNCTIONLIST', 7, 'curdate,curtime,dayname,dayofmonth,dayofweek,hour,minute,month,monthname,now,quarter,second,timestampadd,timestampdiff,year', 5, 7, 0, -1)
go
insert dbo.spt_mda values ('NULLPLUSNONNULL', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('NULLPLUSNONNULL', 4, '1', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('EXTRANAMECHARS', 2, 'select ''@#$''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('EXTRANAMECHARS', 6, '@#$', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MAXBINARYLITERALLENGTH', 2, 'select 32767', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXBINARYLITERALLENGTH', 5, '32767', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MAXCHARLITERALLENGTH', 2, 'select 32767', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXCHARLITERALLENGTH', 5, '32767', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MAXLONGVARBINARYLENGTH', 2, 'select 2147483647', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXLONGVARBINARYLENGTH', 5, '2147483647', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MAXLONGVARCHARLENGTH', 2, 'select 2147483647', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXLONGVARCHARLENGTH', 5, '2147483647', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('SCHEMAS', 1, 'dbo.sp_jdbc_getschemas', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('COLUMNPRIVILEGES', 1, 'dbo.sp_jdbc_getcolumnprivileges(?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('TABLEPRIVILEGES', 1, 'dbo.sp_jdbc_gettableprivileges(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('ROWIDENTIFIERS', 1, 'dbo.sp_jdbc_getbestrowidentifier(?,?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('VERSIONCOLUMNS', 1, 'dbo.sp_jdbc_getversioncolumns(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('KEYCROSSREFERENCE', 1, 'dbo.sp_jdbc_getcrossreferences(?,?,?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('INDEXINFO', 1, 'dbo.sp_jdbc_getindexinfo(?,?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('PROCEDURECOLUMNS', 1, 'dbo.sp_jdbc_getprocedurecolumns(?,?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('PROCEDURES', 1, 'dbo.sp_jdbc_stored_procedures(?,?,?)', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('CATALOGS', 2, 'select db_name() as TABLE_CAT', 1, 7, 0, -1)
go
insert dbo.spt_mda values ('TABLETYPES', 2, 'select ''BASE'' as TABLE_TYPE union all select ''GBL TEMP'' union all select ''VIEW''', 1, 4, 0, -1)
go
insert dbo.spt_mda values ('TABLETYPES', 2, 'select ''GLOBAL TEMPORARY'' as TABLE_TYPE union all select ''LOCAL TEMPORARY'' union all select ''SYSTEM TABLE'' union all select ''TABLE'' union all select ''VIEW'' ORDER BY 1',5 ,7, 0, -1) 
go

/* don't bother inserting rows for unsupported functions
** insert into jdbc_function_escapes values ('replace', null)
** insert into jdbc_function_escapes values ('dayofyear', 'datepart(dy, %1)')
** insert into jdbc_function_escapes values ('week', 'week(%1)')
** insert into jdbc_function_escapes values ('insert','insert(%1, %2, %3, 0, -1)')
** insert into jdbc_function_escapes values (timestampaddsql_tsi_frac_second,
**                                           seconds(%3, %2))
** To add fract_second we cannot use seconds/1000 because of rounding problems
**
** There's a bug in the trim function either in SA or dbos.
** Better take it out than return wrong values
** insert into jdbc_function_escapes values ('rtrim', 'rtrim(%1)')
** insert into jdbc_function_escapes values ('ltrim', 'ltrim(%1)')
**
*/      

/* Not available in SQL Anywhere */
insert dbo.spt_mda values ('SEARCHSTRING', 2, 'select ''\''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SEARCHSTRING', 6, '\', 4, 7, 0, -1)
go
/*
max column name length, max columns in group by,
max columns in index, max columns in order by,
max columns in select, max columns in table
*/
insert dbo.spt_mda values ('COLUMNINFO', 2, 'select 128, 64, 999, 64, 999, 999', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('COLUMNINFO', 5, '128, 64, 999, 64, 999, 999', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('MAXINDEXLENGTH', 2, 'select 32767', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXINDEXLENGTH', 5, '32767', 4, 7, 0, -1)
go
/*
supportsExpressionsInOrderBy: true
supportsOrderByUnrelated: true
*/
insert dbo.spt_mda values ('ORDERBYSUPPORT', 2, 'select 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ORDERBYSUPPORT', 4, '1, 1', 4, 7, 0, -1)
go
/*
The following DatabaseMetaData methods are defined and for SQL Anywhere
they return true=1 or false=0
*/

/*
supportsTableCorrelationNames: true
supportsDifferentTableCorrelationNames: false
*/
insert dbo.spt_mda values ('CORRELATIONNAMES', 2, 'select 1, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CORRELATIONNAMES', 4, '1, 0', 4, 7, 0, -1)
go

/*
supportsGroupBy: true
supportsGroupByUnrelated: false
supportsGroupByBeyondSelect: false
*/
insert dbo.spt_mda values ('GROUPBYSUPPORT', 2, 'select 1, 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('GROUPBYSUPPORT', 4, '1, 0, 0', 4, 7, 0, -1)
go

/*
supportsMinimumSQLGrammar: true
supportsCoreSQLGrammar: true
supportsExtendedSQLGrammar: true
*/
insert dbo.spt_mda values ('SQLGRAMMAR', 2, 'select 1, 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SQLGRAMMAR', 4, '1, 1, 1', 4, 7, 0, -1)
go

/*
supportsANSI92EntryLevelSQL: false
supportsANSI92IntermediateSQL: false
supportsANSI92FullSQL: false
*/
insert dbo.spt_mda values ('ANSI92LEVEL', 2, 'select 0, 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('ANSI92LEVEL', 4, '0, 0, 0', 4, 7, 0, -1)
go

/*
supportsIntegrityEnhancementFacility: true
*/
insert dbo.spt_mda values ('INTEGRITYENHANCEMENT', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('INTEGRITYENHANCEMENT', 4, '1', 4, 7, 0, -1)
go

/*
supportsOuterJoins: true
supportsFullOuterJoins: false
supportsLimitedOuterJoins: true
*/
insert dbo.spt_mda values ('OUTERJOINS', 2, 'select 1, 0, 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('OUTERJOINS', 4, '1, 0, 1, 1', 4, 7, 0, -1)
go

/*
SQL Anywhere's terms for 'schema', 'procedure' and 'catalog'
and how to separate them
*/
insert dbo.spt_mda values ('SCHEMATERM', 2, 'select ''owner''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SCHEMATERM', 6, 'owner', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('PROCEDURETERM', 2, 'select ''stored procedure''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('PROCEDURETERM', 6, 'stored procedure', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('CATALOGTERM', 2, 'select ''database''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CATALOGTERM', 6, 'database', 4, 7, 0, -1)
go
insert dbo.spt_mda values ('CATALOGSEPARATOR', 2, 'select ''.''', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CATALOGSEPARATOR', 6, '.', 4, 7, 0, -1)
go

/*
isCatalogAtStart: true
*/
insert dbo.spt_mda values ('CATALOGATSTART', 2, 'select 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CATALOGATSTART', 4, '1', 4, 7, 0, -1)
go

/*
supportsSchemasInDataManipulation: true
supportsSchemasInProcedureCalls: true
supportsSchemasInTableDefinitions: true
supportsSchemasInIndexDefinitions: true
supportsSchemasInPrivilegeDefinitions: false
*/
insert dbo.spt_mda values ('SCHEMASUPPORT', 2, 'select 1, 1, 1, 1, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SCHEMASUPPORT', 4, '1, 1, 1, 1, 0', 4, 7, 0, -1)
go

/*
same with catalog
*/
insert dbo.spt_mda values ('CATALOGSUPPORT', 2, 'select 1, 1, 1, 1, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('CATALOGSUPPORT', 4, '1, 1, 1, 1, 0', 4, 7, 0, -1)
go

/*
supportsUnion: true
supportsUnionAll: true
*/
insert dbo.spt_mda values ('UNIONSUPPORT', 2, 'select 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('UNIONSUPPORT', 4, '1, 1', 4, 7, 0, -1)
go

/*
supportsSubqueriesInComparisons: true
supportsSubqueriesInExists: true
supportsSubqueriesInIns: true
supportsSubqueriesInQuantifieds: true
supportsCorrelatedSubqueries: true
*/
insert dbo.spt_mda values ('SUBQUERIES', 2, 'select 1, 1, 1, 1, 1', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('SUBQUERIES', 4, '1, 1, 1, 1, 1', 4, 7, 0, -1)
go

/*
supportsDataDefinitionAndDataManipulationTransactions: true
supportsDataManipulationTransactionsOnly: false
dataDefinitionCausesTransactionCommit: false
dataDefinitionIgnoredInTransactions: false
*/
insert dbo.spt_mda values ('TRANSACTIONDATADEFINFO', 2, 'select 1, 0, 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('TRANSACTIONDATADEFINFO', 4, '1, 0, 0, 0', 4, 7, 0, -1)
go

insert dbo.spt_mda values ('MAXCONNECTIONS', 2, 'select @@max_connections', 1, 7, 0, -1)
go

/*
max cursor name length, max user name length,
max schema name length, max procedure name length,
max catalog name length
*/
insert dbo.spt_mda values ('MAXNAMELENGTHS', 2, 'select 0, 128, 128, 128, 128', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('MAXNAMELENGTHS', 5, '0, 128, 128, 128, 128', 4, 7, 0, -1)
go
/*
** max bytes in a row is 2 Gig, 0 is for 'no, that doesn't include blobs'
** ROWINFO cannot be converted into a LITERAL type since it contains
** different types for each column:  int, boolean
*/
insert dbo.spt_mda values ('ROWINFO', 2, 'select 214783647, 0', 1, 7, 0, -1)
go
/*
max length of a statement, max number of open statements
both are unlimited
*/
insert dbo.spt_mda values ('STATEMENTINFO', 2, 'select 0, 0', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('STATEMENTINFO', 5, '0, 0', 4, 7, 0, -1)
go
/*
max table name length, max tables in a select
*/
insert dbo.spt_mda values ('TABLEINFO', 2, 'select 128, 256', 1, 3, 0, -1)
go
insert dbo.spt_mda values ('TABLEINFO', 5, '128, 256', 4, 7, 0, -1)
go
/*
RSMDA.getColumnTypeName
*/
insert dbo.spt_mda values ('COLUMNTYPENAME', 1, 'dbo.sp_sql_type_name(?,?)', 1, 7, 0, -1)
go
/*
  Get the Data source specific DEFAULT CHARACTER SET
*/
insert dbo.spt_mda values ('DEFAULT_CHARSET', 1, 'dbo.sp_default_charset', 1, 7, 0, -1)
go
/*
ownUpdatesAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OWNUPDATESAREVISIBLE', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
ownDeletesAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OWNDELETESAREVISIBLE', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
ownInsertsAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: false
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OWNINSERTSAREVISIBLE', 4, '0, 0, 0', 6, 7, 0, -1)
go
/*
othersUpdatesAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OTHERSUPDATESAREVISIBLE', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
othersDeletesAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OTHERSDELETESAREVISIBLE', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
othersInsertsAreVisible (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('OTHERSINSERTSAREVISIBLE', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
updatesAreDetected (JDBC 2.0)
TYPE_FORWARD_ONLY: false
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('UPDATESAREDETECTED', 4, '0, 0, 0', 6, 7, 0, -1)
go
/*
deletesAreDetected (JDBC 2.0)
TYPE_FORWARD_ONLY: false
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('DELETESAREDETECTED', 4, '0, 0, 0', 6, 7, 0, -1)
go
/*
insertsAreDetected (JDBC 2.0)
TYPE_FORWARD_ONLY: false
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('INSERTSAREDETECTED', 4, '0, 0, 0', 6, 7, 0, -1)
go
/*
supportsBatchUpdates: true (JDBC 2.0)
*/
insert dbo.spt_mda values ('SUPPORTSBATCHUPDATES', 4, '1', 6, 7, 0, -1)
go
/*
SQA 5.x and lower - execBatchUpdatesInLoop: true
ASA 6.x and greater - execBatchUpdatesInLoop: false
*/
insert dbo.spt_mda values ('EXECBATCHUPDATESINLOOP', 4, '1', 6, 7, 0, 5)
insert dbo.spt_mda values ('EXECBATCHUPDATESINLOOP', 4, '0', 6, 7, 6, -1)
go

insert dbo.spt_mda values ('EXECPARAMETERIZEDBATCHINLOOP', 4, '1', 6, 7, 0, 7)
insert dbo.spt_mda values ('EXECPARAMETERIZEDBATCHINLOOP', 4, '0', 6, 7, 8, -1)
go

/*
 * The MAXBATCHPARAMS metadata is meaningless for any ASA < version 8.0. 
 * This is because all servers below ASA8 cannot run parameterized batches.
 * In any case, jConnect will ignore the value if the above accessor,
 * EXECPARAMETERIZEDBATCHINLOOP, is set to true.
 */
insert dbo.spt_mda values ('MAXBATCHPARAMS', 5, '2048', 6, 7, 0, -1)
go

/*
supportsResultSetType (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: true
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('SUPPORTSRESULTSETTYPE', 4, '1, 1, 0', 6, 7, 0, -1)
go
/*
supportsResultSetConcurrency(CONCUR_READ_ONLY) (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: true
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('READONLYCONCURRENCY', 4, '1, 1, 0', 6, 7, 0, -1)
go
/*
supportsResultSetConcurrency(CONCUR_UPDATABLE) (JDBC 2.0)
TYPE_FORWARD_ONLY: true
TYPE_SCROLL_INSENSITIVE: false
TYPE_SCROLL_SENSITIVE: false
*/
insert dbo.spt_mda values ('UPDATABLECONCURRENCY', 4, '1, 0, 0', 6, 7, 0, -1)
go
/*
getUDTs (JDBC 2.0)
*/  
insert dbo.spt_mda values ('UDTS', 1, 'sp_jdbc_getudts(?,?,?,?)', 6, 7, 0, -1)
go
/*
isCaseSensitive
*/
insert dbo.spt_mda values ('ISCASESENSITIVE', 2,
    'if exists (select 1 where ''A'' = ''a'') select 0 else select 1', 6, 7, 0, -1)
go

/*
 JTA support for JDBC 2.0 extensions

 XACOORDINATORTYPE returns a result set that describes
 the Sybase-type of transaction coordinator available
 on the server.  0 means no coordinator is available.
*/
insert dbo.spt_mda values ('XACOORDINATORTYPE', 2, 'select TxnStyle=0, RequiredRole=NULL, Status=0', 6, 7, 0, -1)
commit
go

/*
** create the well-known dbo.sp_mda procedure for accessing the data
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_mda', 'procedure' )
go
/** SECTION END: CLEANUP **/

/*
** clienttype 1 == JDBC
** clienttype 2 == JDBC - but only send back the minimal frequently used info.
*/
/*
** requesttype 0 == Returns the mdinfo:MDAVERSION and mdinfo:MDARELEASEID rows.
** requesttype 1 == JDBC
** requesttype 2 == JDBC - but only send back the minimal frequently used info.
**
** mdaversion
*/
create procedure dbo.sp_mda(@requesttype int, @requestversion int, @clientversion int = 0) as
begin

    declare @min_mdaversion int, @max_mdaversion int
    declare @mda_version int
    declare @srv_version int
    declare @mdaver_querytype tinyint
    declare @mdaver_query varchar(255)
    declare @original_iso_level int

    select @min_mdaversion = 1
    select @max_mdaversion = 7
    select @mda_version = @requestversion

    if(patindex('%IQ%',@@version) > 0)
    begin
       select @srv_version = 6
    end
    else
    begin
       select @srv_version = convert(int, substring(@@version, 1, 1))
    end

    /* store original isolation level so we don't alter the level that
       is already set */
    select @original_iso_level = @@isolation    
    set transaction isolation level 1

    /*
    ** if the client is requesting a version too old
    ** then we return our lowest version supported
    **
    ** the client needs to be able to just handle this
    */
    if (@requestversion < @min_mdaversion)
        begin
            select @mda_version = @min_mdaversion
        end

    /*
    ** if the client is requesting a version too new
    ** we will return our highest version available
    */
    if (@mda_version > @max_mdaversion)
        begin
            select @mda_version = @max_mdaversion
        end

    /*
    ** if the client's requested version is between 1 and 3,
    ** then the mda version returned needs to be 1.  The reason
    ** for this is the jConnect driver would pass in it's own
    ** major version number as the @requestversion.  We need to
    ** keep older version's of the driver working ok since
    ** they expect a '1' to be returned.
    */
    if (@mda_version < 4)
        begin
            select @mda_version = 1
            select @mdaver_querytype = 2
            select @mdaver_query = 'select 1'
        end
    else
        begin
            select @mdaver_querytype = 5
            select @mdaver_query = convert(varchar(255), @mda_version)
        end

    /*
    ** process the @requesttype
    */
    if (@requesttype = 0)
        begin
            select "mdinfo" = convert(varchar(30),'MDAVERSION'),
                   "querytype" = @mdaver_querytype,
                   "query" = @mdaver_query
            union
            select mdinfo, querytype, query
            from dbo.spt_mda
         where mdinfo in (
                'MDARELEASEID'
             )
        end
    else if (@requesttype = 1)
        begin
            select "mdinfo" = convert(varchar(30),'MDAVERSION'),
                   "querytype" = @mdaver_querytype,
                   "query" = @mdaver_query
            union
            select mdinfo, querytype, query
            from dbo.spt_mda
            where @mda_version >= mdaver_start
                  and @mda_version <= mdaver_end
                  and ((@srv_version >= srvver_start) 
                      and (@srv_version <= srvver_end 
                      or srvver_end = -1))
        end
    else if (@requesttype = 2)
        begin
            select "mdinfo" = convert(varchar(30),'MDAVERSION'),
                   "querytype" = @mdaver_querytype,
                   "query" = @mdaver_query
            union
            select mdinfo, querytype, query
            from dbo.spt_mda
         where mdinfo in (
                'CONNECTCONFIG',
               'SET_CATALOG',
               'SET_AUTOCOMMIT_ON',
               'SET_AUTOCOMMIT_OFF',
               'SET_ISOLATION',
               'SET_ROWCOUNT',
                'DEFAULT_CHARSET'
             )
                and @mda_version >= mdaver_start
                and @mda_version <= mdaver_end
                and ((@srv_version >= srvver_start) 
                    and (@srv_version <= srvver_end 
                    or srvver_end = -1))
        end

    /* Restore the isolation level to its original value. No need to check
       for 1, because sp_mda sets it to 1. */
    if (@original_iso_level = 0)
    begin
        set transaction isolation level 0
    end
    if (@original_iso_level = 2)
    begin
        set transaction isolation level 2
    end
    if (@original_iso_level = 3)
    begin
        set transaction isolation level 3
    end
end
go

grant execute on dbo.sp_mda to PUBLIC
go


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'spt_jtext', 'table' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create table dbo.spt_jtext (mdinfo varchar(30) unique, value text)'
    || in_system )
end
go

grant select on dbo.spt_jtext to PUBLIC
go

insert dbo.spt_jtext values ('SQLKEYWORDS', 'BINARY, BREAK, CALL, ' ||
 'CHAR_CONVERT, CHECKPOINT, COMMIT, DBA, DBSPACE, DO, ELSEIF, ENCRYPTED, ' ||
 'ENDIF, HOLDLOCK, IDENTIFIED, IF, INDEX, INOUT, INSTEAD, LOCK, LONG, ' ||
 'MEMBERSHIP, MESSAGE, MODE, MODIFY,' || ' NAMED, NOHOLDLOCK, OFF, OPTIONS, ' ||
 'OTHERS, OUT, PASSTHROUGH, PRINT, PROC, RAISERROR, READTEXT, REFERENCE, ' ||
 'RELEASE, REMOTE, RENAME, RESOURCE, RETURN, SAVE, SAVEPOINT, SCHEDULE, ' ||
 'SHARE, START, STOP, SUBTRANS, SUBTRANSACTION, SYNCHRONIZE, SYNTAX_ERROR, ' ||
 'TINYINT, TRAN, TRIGER, TRUNCATE, TSEQUAL, VALIDATE, VARBINARY, VARIABLE, ' ||
 'WHILE, WRITETEXT')
go

commit
go


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_helpkeys', 'table' )
go
call dbo.sp_jconnect_cleanup( 'sp_jdbc_fkeys', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_helpkeys (
     PKTABLE_CAT   varchar(128) null,
     PKTABLE_SCHEM varchar(128) null,
        PKTABLE_NAME  varchar(128) null,
        PKCOLUMN_NAME varchar(128) null,
        FKTABLE_CAT   varchar(128) null,
        FKTABLE_SCHEM varchar(128) null,
        FKTABLE_NAME  varchar(128) null,
        FKCOLUMN_NAME varchar(128) null,
        KEY_SEQ       tinyint null,
        UPDATE_RULE   tinyint null,
        DELETE_RULE   tinyint null,
        FK_NAME       varchar(128) null,
        PK_NAME       varchar(128) null,
        DEFERRABILITY tinyint null)'
    || in_system )
end
go

create procedure dbo.sp_jdbc_fkeys
        @pktable_name        varchar(128),
        @pktable_owner       varchar(128),
        @pktable_qualifier   varchar(128),
        @fktable_name        varchar(128),
        @fktable_owner       varchar(128),
        @fktable_qualifier   varchar(128)
as
    DELETE dbo.jdbc_helpkeys
    INSERT INTO dbo.jdbc_helpkeys ( PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME,
            PKCOLUMN_NAME, FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, FKCOLUMN_NAME,
            KEY_SEQ, UPDATE_RULE, DELETE_RULE, FK_NAME, PK_NAME, DEFERRABILITY )
    SELECT  db_name() as PKTABLE_CAT,
            user_name( PKT.creator ) as PKTABLE_SCHEM,
            PKT.table_name as PKTABLE_NAME,
            PKCOL.column_name as PKCOLUMN_NAME,
            db_name() as FKTABLE_CAT,
            user_name( FKT.creator ) as FKTABLE_SCHEM,
            FKT.table_name as FKTABLE_NAME,
            FKCOL.column_name as FKCOLUMN_NAME,
            FK.foreign_key_id as KEY_SEQ,
            COALESCE(  ( select ( if referential_action = 'C' then 0 /* cascade */
                    else if referential_action='N' then 2 /* setNull */
                    else if referential_action='D' then 4 /* default */
                    else 3 /* noAction */
                    endif endif endif )
                from SYS.SYSTRIGGER as TRG 
                where FK.foreign_table_id = TRG.foreign_table_id 
                AND FK.foreign_key_id = TRG.foreign_key_id
                AND event in ('C', 'U') )
                , 1 /* RESTRICTED */ ) as UPDATE_RULE,
            COALESCE(  ( select ( if referential_action = 'C' then 0 /* cascade */
                    else if referential_action='N' then 2 /* setNull */
                    else if referential_action='D' then 4 /* default */
                    else 3 /* noAction */ 
                    endif endif endif )
                from SYS.SYSTRIGGER as TRG 
                where FK.foreign_table_id = TRG.foreign_table_id 
                AND FK.foreign_key_id = TRG.foreign_key_id
                AND event = 'D' ) 
                , 1 /* RESTRICTED */ ) as DELETE_RULE,
            FK.role as FK_NAME,
            null as PK_NAME,
            7 /* NOT DEFERRABLE */ as DEFERRABILITY
    from SYS.SYSFOREIGNKEY as FK
    join SYS.SYSTABLE as PKT on FK.primary_table_id = PKT.table_id
    join SYS.SYSTABLE as FKT on FK.foreign_table_id = FKT.table_id
    join SYS.SYSFKCOL as COL on FK.foreign_table_id = COL.foreign_table_id 
            AND FK.foreign_key_id = COL.foreign_key_id
    join SYS.SYSCOLUMN as PKCOL on FK.primary_table_id = PKCOL.table_id 
            AND COL.primary_column_id = PKCOL.column_id
    join SYS.SYSCOLUMN as FKCOL on FK.foreign_table_id = FKCOL.table_id 
            AND COL.foreign_column_id = FKCOL.column_id
    where ( @pktable_owner is null OR PKTABLE_SCHEM like @pktable_owner ESCAPE '\')
    and ( @pktable_name is null OR PKTABLE_NAME like @pktable_name ESCAPE '\')
    AND ( @fktable_owner is null OR FKTABLE_SCHEM like @fktable_owner ESCAPE '\')
    and ( @fktable_name is NULL OR FKTABLE_NAME like @fktable_name ESCAPE '\')

go

grant execute on dbo.sp_jdbc_fkeys to PUBLIC 
go

/* dbo.sp_jdbc_exportkey */
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_exportkey', 'procedure' )
go
/** SECTION END: CLEANUP **/

CREATE PROCEDURE dbo.sp_jdbc_exportkey (
	     @table_qualifier       varchar(128) = null,
	     @table_owner           varchar(128) = null,
	     @table_name            varchar(128) = null)
as
  if (@table_owner = null)
  begin
      select @table_owner ='%'
  end

  if (@table_name = null)
  begin
    raiserror 17208  'Null is not allowed for parameter TABLE NAME '
    return(1)
  end

  begin transaction
  /* 
  ** sp_jdbc_fkeys will use @table_name in a like clause
  ** we need to escape it out to preserve tablenames which
  ** contain wildcards
  */
  exec sp_jdbc_escapeliteralforlike @table_name
  
  if (select count(*) from sysobjects 
      where user_name(uid) like @table_owner ESCAPE '\'
      and name like @table_name ESCAPE '\') = 0 
  begin
    raiserror 17208
      'There is no object with the specified owner/name combination'
    return(1)
  end
    exec dbo.sp_jdbc_fkeys @table_name, @table_owner, @table_qualifier,
                           NULL, NULL, NULL
    select * from dbo.jdbc_helpkeys
           order by FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, KEY_SEQ
  commit transaction
  delete dbo.jdbc_helpkeys
go

grant execute on dbo.sp_jdbc_exportkey to PUBLIC 
go

/* dbo.sp_jdbc_importkey */

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_importkey', 'procedure' )
go
/** SECTION END: CLEANUP **/

CREATE PROCEDURE dbo.sp_jdbc_importkey (
     @table_qualifier       varchar(128) = null,
     @table_owner           varchar(128) = null,
     @table_name            varchar(128) = null)
as
  if (@table_owner = null)
  begin
      select @table_owner ='%'
  end

  if (@table_name = null)
  begin
    raiserror 17208  'Null is not allowed for parameter TABLE NAME '
    return(1)
  end

  begin transaction
  
  /* 
  ** sp_jdbc_fkeys will use @table_name in a like clause
  ** we need to escape it out to preserve tablenames which
  ** contain wildcards
  */
  exec sp_jdbc_escapeliteralforlike @table_name

  if (select count(*) from sysobjects 
      where user_name(uid) like @table_owner ESCAPE '\'
      and name like @table_name ESCAPE '\') = 0 
  begin
    raiserror 17208
      'There is no object with the specified owner/name combination'
    return(1)
  end
    exec dbo.sp_jdbc_fkeys NULL, NULL, NULL,
                         @table_name, @table_owner, @table_qualifier
    select * from dbo.jdbc_helpkeys
           order by PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, KEY_SEQ
  commit transaction
  delete dbo.jdbc_helpkeys
go

grant execute on dbo.sp_jdbc_importkey to PUBLIC 
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getcrossreferences', 'procedure' )
go
/** SECTION END: CLEANUP **/

CREATE PROCEDURE dbo.sp_jdbc_getcrossreferences
                           @pktable_qualifier   varchar(128) = null,
                           @pktable_owner       varchar(128) = null,
                           @pktable_name        varchar(128) = null,
                           @fktable_qualifier   varchar(128) = null,
                           @fktable_owner       varchar(128) = null,
                           @fktable_name        varchar(128) = null
as
  if (@pktable_name = null or @fktable_name = null) 
  begin
    raiserror 17208  'Primary Key table name and Foreign Key table name must be given'
    return(1)
  end

  if (@pktable_name = null)
  begin
      select @pktable_name = '%'
  end
  else
  begin
      /* 
      ** sp_jdbc_fkeys will use @pktable_name in a like clause
      ** we need to escape it out to preserve tablenames which
      ** contain wildcards
      */
      exec sp_jdbc_escapeliteralforlike @pktable_name
  end

  if (@fktable_name = null)
  begin
      select @fktable_name = '%'
  end
  else
  begin
      /* 
      ** sp_jdbc_fkeys will use @fktable_name in a like clause
      ** we need to escape it out to preserve tablenames which
      ** contain wildcards
      */
      exec sp_jdbc_escapeliteralforlike @fktable_name
  end

  if (@pktable_owner = null)
  begin
      select @pktable_owner ='%'
  end

  if (@fktable_owner = null)
  begin
      select @fktable_owner ='%'
  end

  if (select count(*) from dbo.sysobjects
      where user_name(uid) like @pktable_owner ESCAPE '\'
	  and name like @pktable_name ESCAPE '\') = 0 
  begin
    raiserror 17208
      'There is no primary key object with the specified owner/name combination'
    return(1)
  end
  if (select count(*) from dbo.sysobjects
      where user_name(uid) like @fktable_owner ESCAPE '\'
      and name like @fktable_name ESCAPE '\') = 0 
  begin
    raiserror 17208
     'There is no foreign  key object with the specified owner/name combination'
    return(1)
  end
  begin transaction
    exec dbo.sp_jdbc_fkeys @pktable_name, @pktable_owner, @pktable_qualifier,
                           @fktable_name, @fktable_owner, @fktable_qualifier
    select * from jdbc_helpkeys 
    where FKTABLE_NAME like @fktable_name ESCAPE '\'
    and PKTABLE_NAME like @pktable_name ESCAPE '\'
    order by  FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, KEY_SEQ
  commit transaction
  delete dbo.jdbc_helpkeys
go

grant execute on dbo.sp_jdbc_getcrossreferences to PUBLIC 
go

/* dbo.sp_jdbc_getschemas */
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getschemas', 'procedure' )
go
/** SECTION END: CLEANUP **/


CREATE PROCEDURE dbo.sp_jdbc_getschemas
as
    select TABLE_SCHEM=name  from dbo.sysusers where suid >= 
        (if db_property( 'FileVersion' ) >= 50 then 0 else -2 endif) 
    order by name
go

grant execute on dbo.sp_jdbc_getschemas to PUBLIC
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'spt_jdbc_conversion', 'table' )
go
/** SECTION END: CLEANUP **/

/*
** create table with conversion information
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_convert_datatype', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create table dbo.spt_jdbc_conversion (datatype int primary key, 
conversion char(20))'
    || in_system )
end
go

grant select on dbo.spt_jdbc_conversion to PUBLIC
go

/* Values based on the table info from the SQL Server Ref Man Chapter 4 */
/* I was told that the conversions for SQL Anywhere are identical */

/*bit*/
insert into dbo.spt_jdbc_conversion values(0,'11111110111111110001')
go
/*integers+numerics*/
insert into dbo.spt_jdbc_conversion values(1,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(2,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(9,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(10,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(11,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(12,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(13,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(14,'11111100011111110000')
go
insert into dbo.spt_jdbc_conversion values(15,'11111100011111110000')
go
/*Binaries*/
insert into dbo.spt_jdbc_conversion values(5,'11111110111111111111')
go
insert into dbo.spt_jdbc_conversion values(4,'11111110111111111111')
go
insert into dbo.spt_jdbc_conversion values(3,'11111110111111111111')
go
/*Characters*/
insert into dbo.spt_jdbc_conversion values(6,'00011110100000001111')
go
insert into dbo.spt_jdbc_conversion values(8,'00011110100000001111')
go
insert into dbo.spt_jdbc_conversion values(19,'00011110100000001111')
go
/*Dates*/
insert into dbo.spt_jdbc_conversion values(16,'00000010000000001110')
go
insert into dbo.spt_jdbc_conversion values(17,'00000010000000001110')
go
insert into dbo.spt_jdbc_conversion values(18,'00000010000000001110')
go
/*NULL*/
insert into dbo.spt_jdbc_conversion values(7,'00000000000000000000')
go
commit
go

create procedure dbo.sp_jdbc_convert_datatype (
                                        @source int,
                                        @destination int)
as
        /* Make source non-negative */
        select @source = @source + 7
        /* Put the strange date numbers into this area between 0-19*/
        if (@source > 90)
                select @source = @source - 82

        /*Convert destination the same way*/
        /* Put the strange date numbers into this area between 0-19*/
        if (@destination > 90)
                select @destination = @destination - 82

        /* Need 8 added instead of 7 because substring starts at 1 instead */
        /* of 0 */
        select @destination = @destination + 8

        /* Check the conversion. If the bit string in the table has a 1
        ** on the place's number of the destination's value we have to
        ** return true, else false
        */
        if ((select substring(conversion,@destination,1) from
                dbo.spt_jdbc_conversion where datatype = @source) = '1')
                select 1
        else
                select 0
go

grant execute on dbo.sp_jdbc_convert_datatype to PUBLIC
go

commit
go


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_procedurecolumns', 'table' )
go
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getprocedurecolumns', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_procedurecolumns (
        PROCEDURE_CAT   varchar(32)  null,
        PROCEDURE_SCHEM varchar(32)  null,
        PROCEDURE_NAME  varchar(32)  not null,
        COLUMN_NAME     varchar(32)  not null,
        COLUMN_TYPE     smallint     not null, 
        DATA_TYPE       smallint     null,
        TYPE_NAME       varchar(32)  not null,
        "PRECISION"     int          not null,
        LENGTH          int          not null,
        SCALE           smallint     not null, 
        RADIX           smallint     not null, 
        NULLABLE        smallint     not null,
        REMARKS         varchar(255) null,
        colid           int          not null /* hidden, used for ordering */
        )'
    || in_system )    
end
go


create procedure dbo.sp_jdbc_getprocedurecolumns (
@sp_qualifier   varchar(128) = null,     /* stored procedure qualifier*/
@sp_owner       varchar(128) = null,     /* stored procedure owner */
@sp_name        varchar(128) = null,     /* stored procedure name */
@column_name    varchar(128) = null)
as
    begin transaction 
    
    DELETE dbo.jdbc_procedurecolumns

    if @sp_owner is null select @sp_owner ='%'
    if @sp_name is null select @sp_name ='%'
    if @column_name is null select @column_name='%'

    INSERT INTO dbo.jdbc_procedurecolumns (
        PROCEDURE_CAT, PROCEDURE_SCHEM, PROCEDURE_NAME, COLUMN_NAME,
        COLUMN_TYPE, DATA_TYPE, TYPE_NAME, "PRECISION", LENGTH, SCALE,
        RADIX, NULLABLE, REMARKS, colid)
    SELECT 
        PROCEDURE_CAT   = sp_jconnect_trimit(db_name()),
        PROCEDURE_SCHEM = sp_jconnect_trimit(user_name(creator)),
        PROCEDURE_NAME  = sp_jconnect_trimit(proc_name),
        COLUMN_NAME     = sp_jconnect_trimit(parm_name),

        /*
        ** values defined in java.sql.DatabaseMetaData for COLUMN_TYPE:
        **
        **	procedureColumnUnknown = 0
        **	procedureColumnIn      = 1
        **	procedureColumnInOut   = 2
        **	procedureColumnResult  = 3
        **	procedureColumnOut     = 4 -- never returned by ASA
        **	procedureColumnReturn  = 5
        */
        COLUMN_TYPE = 
          (select
               /* procedureColumnIn */ 
              (if      parm_type = 0 and parm_mode_in = 'Y' and parm_mode_out = 'N' then 1
               /* procedureColumnInOut */
               else if parm_type = 0 and parm_mode_in = 'Y' and parm_mode_out = 'Y' then 2
               /* procedureColumnResult */
               else if parm_type = 1 and parm_mode_in = 'N' and parm_mode_out = 'Y' then 3
               /* procedureColumnUnknown */
               else 0
               endif endif endif)),
        DATA_TYPE =     (select DATA_TYPE from dbo.spt_jdatatype_info
                         where LOCAL_TYPE_NAME = (
                               select if domain_name='integer'
                                      then 'int' else domain_name endif)),
        TYPE_NAME = (select if sd.domain_name = 'integer' then 'int'
                     else sp_jconnect_trimit(sd.domain_name)
                     endif),
        width as "PRECISION",
        LENGTH = (select length(parm_name)),
        scale as SCALE,
        RADIX = 0,
        NULLABLE = 2,
        REMARKS = null,
        colid = parm_id
    from SYS.SYSPROCEDURE key join SYS.SYSPROCPARM, SYS.SYSDOMAIN sd
    where proc_name like @sp_name ESCAPE '\'
        and parm_name like @column_name ESCAPE '\'
        and user_name(creator) like @sp_owner ESCAPE '\'
        and sd.domain_id = SYSPROCPARM.domain_id

    /*
    ** add 'procedureColumnReturn'
    */
    INSERT INTO dbo.jdbc_procedurecolumns (
        PROCEDURE_CAT, PROCEDURE_SCHEM, PROCEDURE_NAME, COLUMN_NAME,
        COLUMN_TYPE, DATA_TYPE, TYPE_NAME, "PRECISION", LENGTH, SCALE,
        RADIX, NULLABLE, REMARKS, colid)
    SELECT DISTINCT
        PROCEDURE_CAT   = sp_jconnect_trimit(db_name()),
        PROCEDURE_SCHEM = sp_jconnect_trimit(user_name(creator)),
        PROCEDURE_NAME  = sp_jconnect_trimit(proc_name),
        COLUMN_NAME     = 'RETURN_VALUE',
        COLUMN_TYPE     = 5, /* procedureColumnReturn */
        DATA_TYPE       = dbo.spt_jdatatype_info.DATA_TYPE,
        TYPE_NAME       = dbo.spt_jdatatype_info.TYPE_NAME,
        "PRECISION"     = st.prec,
        LENGTH          = typelength,
        SCALE           = (select (if st.scale = null then 0
                                   else st.scale endif)),
        RADIX = 0,
        NULLABLE = 0,
        REMARKS = 'procedureColumnReturn',
        colid = 0
    from SYS.SYSPROCEDURE key join SYS.SYSPROCPARM, dbo.spt_jdatatype_info, dbo.SYSTYPES st
        where ss_dtype = 56 /* int */
        and dbo.spt_jdatatype_info.TYPE_NAME = 'int'
        and st.type = ss_dtype
        and proc_name like @sp_name ESCAPE '\'
        and 'RETURN_VALUE' like @column_name ESCAPE '\'
        and user_name(creator) like @sp_owner ESCAPE '\'
        and parm_type <> 4
 

    SELECT PROCEDURE_CAT, PROCEDURE_SCHEM, PROCEDURE_NAME, COLUMN_NAME,
        COLUMN_TYPE, DATA_TYPE, TYPE_NAME, "PRECISION", LENGTH, SCALE,
        RADIX, NULLABLE, REMARKS
    FROM dbo.jdbc_procedurecolumns
    ORDER BY PROCEDURE_SCHEM, PROCEDURE_NAME, colid

    commit transaction 

    DELETE dbo.jdbc_procedurecolumns

go

grant execute on dbo.sp_jdbc_getprocedurecolumns to PUBLIC
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_primarykey', 'procedure' )
go
/** SECTION END: CLEANUP **/

create procedure dbo.sp_jdbc_primarykey
                           @table_qualifier varchar(128),
                           @table_owner         varchar(128),
                           @table_name          varchar(128)
as
declare @id int
declare @tableowner varchar(128)

if (@table_owner = null) select @table_owner ='%'

if (@table_name = null)
begin
   raiserror 17208  'Null is not allowed for parameter TABLE NAME '
   return(1)
end

if (select count(*) from dbo.sysobjects
    where user_name(uid) like @table_owner ESCAPE '\'
     and name = @table_name) = 0
begin
  raiserror 17208
    'There is no object with the specified owner/name combination'
  return(1)
end

select TABLE_CAT    = sp_jconnect_trimit(db_name()),
       TABLE_SCHEM  = sp_jconnect_trimit(user_name(t.creator)),
       TABLE_NAME   = sp_jconnect_trimit(@table_name),
       COLUMN_NAME  = sp_jconnect_trimit(column_name),
       KEY_SEQ      = column_id,
       PK_NAME      = sp_jconnect_trimit(column_name)
  from SYS.SYSCOLUMN c, SYS.SYSTABLE t
  where c.pkey='Y'
    and c.table_id = t.table_id
    and t.table_name = @table_name
    and user_name(t.creator) like @table_owner ESCAPE '\'
  order by COLUMN_NAME
go

grant execute on dbo.sp_jdbc_primarykey to PUBLIC
go


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_stored_procedures', 'procedure' )
go
/** SECTION END: CLEANUP **/

create procedure dbo.sp_jdbc_stored_procedures
@sp_qualifier   varchar(128) = null,
@sp_owner       varchar(128) = null,
@sp_name        varchar(128) = null
as

if @sp_owner is null select @sp_owner ='%'
if @sp_name is null select @sp_name ='%'

select  PROCEDURE_CAT     = sp_jconnect_trimit(db_name()),
        PROCEDURE_SCHEM   = sp_jconnect_trimit(user_name(creator)),
        PROCEDURE_NAME    = sp_jconnect_trimit(proc_name),
	num_input_params  = (select count(*) from SYSPROCPARM t2 where
                             t2.proc_id = t1.proc_id and parm_mode_in = 'Y'),
        num_output_params = (select count(*) from SYSPROCPARM t3 where
                             t3.proc_id = t1.proc_id and parm_mode_out = 'Y'),
        num_result_sets   = (select count(*) from SYSPROCPARM t4 where
                             t4.proc_id = t1.proc_id and parm_type = 4),
        REMARKS           = null,
        PROCEDURE_TYPE    = (select (if num_result_sets > 0 then 2 else 1 
                                     endif))
from SYS.SYSPROCEDURE t1
where proc_name like @sp_name ESCAPE '\'
and user_name(creator) like @sp_owner ESCAPE '\'
order by PROCEDURE_SCHEM, PROCEDURE_NAME
go


grant execute on dbo.sp_jdbc_stored_procedures to PUBLIC
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_tableprivileges', 'table' )
go
/** SECTION END: CLEANUP **/


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_gettableprivileges', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_tableprivileges (
                                TABLE_CAT varchar(128) null,
                                TABLE_SCHEM varchar(128) null,
                                TABLE_NAME varchar(128) null,
                                GRANTOR varchar(128) null,
                                GRANTEE varchar(128) null,
                                PRIVILEGE varchar(15) null,
                                IS_GRANTABLE varchar(3) null)'
    || in_system )
end
go

create procedure dbo.sp_jdbc_gettableprivileges (
                @table_qualifier varchar(128) = null,
                @table_owner varchar(128) = null,
                @table_name varchar(128)= null)
as
declare @dbname varchar(128), @schem int, @tablename varchar(128)
declare @grantor int, @grantee int, @selectauth varchar(1)
declare @insertauth varchar(1), @deleteauth varchar(1), @updateauth varchar(1)
declare @alterauth varchar(1), @referenceauth varchar(1)
declare @tableid int

if @table_owner is null select @table_owner ='%'
if @table_name is null select @table_name ='%'

select @dbname = sp_jconnect_trimit(db_name())

begin transaction

/* Table exists but there are no additional permissions granted*/ 
  declare getid cursor for
    select creator, creator, table_name
    from SYS.SYSTABLE
    where table_name like @table_name ESCAPE '\'
     and user_name(creator) like @table_owner ESCAPE '\'
    for read only
  open getid
  fetch getid into @schem, @grantor, @tablename
  while (@@sqlstatus = 0)
  begin
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'SELECT','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'UPDATE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'DELETE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'ALTER','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'REFERENCE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
      @tablename, user_name(@grantor), user_name(@grantor), 'INSERT','YES')
    fetch getid into @schem, @grantor, @tablename
  end
  close getid

if ((select count(*) from SYSTABLEPERM key join SYSTABLE where
    table_name like @table_name ESCAPE '\'
    and user_name(creator) like @table_owner ESCAPE '\') > 0)
begin
  /* There are additional permissions granted */
  declare getid2 cursor for
    select stable_id, creator, grantor, grantee, table_name, selectauth,
      insertauth, deleteauth, updateauth, alterauth, referenceauth
    from SYS.SYSTABLEPERM key join SYS.SYSTABLE
    where table_name like @table_name ESCAPE '\'
     and user_name(creator) like @table_owner ESCAPE '\'
    for read only
  open getid2
  fetch getid2 into @tableid, @schem,@grantor,@grantee,@tablename,@selectauth,
        @insertauth, @deleteauth, @updateauth, @alterauth, @referenceauth
  while (@@sqlstatus = 0)
  begin
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'SELECT','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'UPDATE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'DELETE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'ALTER','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'REFERENCE','YES')
    insert into dbo.jdbc_tableprivileges values (db_name(),user_name(@schem),
       @tablename, user_name(@grantor), user_name(@grantor), 'INSERT','YES')
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @selectauth= 'Y' or @selectauth='G' then 'SELECT' else 'null' endif,
        if @selectauth = 'G' then 'YES' else 'NO' endif
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @insertauth= 'Y' or @insertauth='G' then 'INSERT' else 'null' endif,
        if @insertauth= 'G' then 'YES' else 'NO' endif
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @deleteauth= 'Y' or @deleteauth='G' then 'DELETE' else 'null' endif,
        if @deleteauth = 'G' then 'YES' else 'NO' endif
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @updateauth= 'Y' or @updateauth='G' then 'UPDATE' else 'null' endif,
        if @updateauth = 'G' then 'YES' else 'NO' endif
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @alterauth = 'Y' or @alterauth='G' then 'ALTER' else 'null' endif,
        if @alterauth = 'G' then 'YES' else 'NO' endif
    insert into dbo.jdbc_tableprivileges
      select @dbname, user_name(@schem), @tablename, user_name(@grantor),
        user_name(@grantee),
        if @referenceauth='Y' or @referenceauth='G' then 'REFERENCE'
                                                    else 'null' endif,
        if @referenceauth = 'G' then 'YES' else 'NO' endif
    fetch getid2 into @tableid, @schem,@grantor,@grantee,@tablename,@selectauth,
      @insertauth, @deleteauth, @updateauth, @alterauth, @referenceauth
  end
  close getid2
end
select distinct * from dbo.jdbc_tableprivileges where PRIVILEGE != 'null'
  and TABLE_SCHEM like @table_owner ESCAPE '\'
  order by TABLE_SCHEM, TABLE_NAME, PRIVILEGE

commit transaction
delete dbo.jdbc_tableprivileges
go

grant execute on dbo.sp_jdbc_gettableprivileges to PUBLIC
go

/* SQL Anywhere just has the option to give UPDATE grants to columns
** all other permissions are taken over from the table permissions
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_columnprivileges', 'table' )
go
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getcolumnprivileges', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_columnprivileges (
                                TABLE_CAT varchar(128) null,
                                TABLE_SCHEM varchar(128) null,
                                TABLE_NAME varchar(128) null,
                                COLUMN_NAME varchar(128) null,
                                GRANTOR varchar(128) null,
                                GRANTEE varchar(128) null,
                                PRIVILEGE varchar(15) null,
                                IS_GRANTABLE varchar(3) null)'
    || in_system )
end
go

create procedure dbo.sp_jdbc_getcolumnprivileges (
                @table_qualifier varchar(128) = null,
                @table_owner varchar(128) = null,
                @table_name varchar(128)= null,
                @column_name varchar(128)= null)
as
declare @dbname varchar(128)
declare @schem varchar(128)
declare @grantor varchar(128)
declare @grantee varchar(128)
declare @columnname varchar(128)
declare @tableid int
declare @columnid int
declare @actual_table_name varchar(128)

if (@table_owner = null)
begin
    // owner = null should not be allowed (regarding specs) but for upward-
    // compatibility we'll leave this here
    select @table_owner = user_name()
end

exec dbo.sp_jdbc_escapeliteralforlike @table_owner

if (@table_name = null)
begin
   raiserror 17208  'Null is not allowed for parameter TABLE NAME '
   return(1)
end

select @actual_table_name = @table_name

exec dbo.sp_jdbc_escapeliteralforlike @table_name

exec dbo.sp_jdbc_escapeliteralforlike @column_name
if @column_name = null select @column_name='%'

if (select count(*) from SYS.SYSCOLUMNS
    where creator like @table_owner ESCAPE '\'
    and tname like @table_name ESCAPE '\'
    and cname like @column_name ESCAPE '\') = 0
begin
  raiserror 17208
    'There is no object with the specified owner/name combination'
  return(1)
end

select @tableid = table_id from SYS.SYSTABLE
where table_name like @table_name ESCAPE '\'
and user_name(creator) like @table_owner ESCAPE '\'


select @dbname = sp_jconnect_trimit(db_name())

begin transaction

declare getcolid cursor for
  select column_id from SYS.SYSCOLUMN
  where column_name like @column_name ESCAPE '\'
  and table_id = @tableid

open getcolid
fetch getcolid into @columnid
while (@@sqlstatus = 0)
begin
  select @columnname = column_name from SYS.SYSCOLUMN where column_id = @columnid
                       and table_id = @tableid
  select @grantor = (select user_name(creator) from SYS.SYSTABLE
                       where table_id = @tableid)
  insert into dbo.jdbc_columnprivileges values (db_name(),@grantor,
@actual_table_name, @columnname, @grantor, @grantor, 'SELECT','YES')
  insert into dbo.jdbc_columnprivileges values (db_name(),@grantor,
@actual_table_name, @columnname, @grantor, @grantor, 'UPDATE','YES')
  insert into dbo.jdbc_columnprivileges values (db_name(),@grantor,
@actual_table_name, @columnname, @grantor, @grantor, 'DELETE','YES')
  insert into dbo.jdbc_columnprivileges values (db_name(), @grantor,
@actual_table_name, @columnname, @grantor, @grantor, 'ALTER','YES')
  insert into dbo.jdbc_columnprivileges values (db_name(), @grantor,
@actual_table_name, @columnname, @grantor, @grantor, 'REFERENCE','YES')

  select @schem  = @grantor
  select @grantor= user_name from SYS.SYSUSERPERM, SYS.SYSTABLEPERM
                   where user_id = SYSTABLEPERM.grantor and
                   SYSTABLEPERM.stable_id = @tableid
  select @grantee= user_name from SYS.SYSUSERPERM,SYS.SYSTABLEPERM
                   where user_id=SYSTABLEPERM.grantee and
                   SYSTABLEPERM.stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname,@grantor, @grantee,
        if selectauth = 'Y' or selectauth='G' then 'SELECT' else 'null' endif,
        if selectauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname,@grantor,@grantee,
        if insertauth = 'Y' or insertauth='G' then 'INSERT' else 'null' endif,
        if insertauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname, @grantor, @grantee,
        if deleteauth = 'Y' or deleteauth='G' then 'DELETE' else 'null' endif,
        if deleteauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname, @grantor, @grantee,
        if updateauth = 'Y' or updateauth='G' then 'UPDATE' else 'null' endif,
        if updateauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname, @grantor, @grantee,
        if alterauth = 'Y' or alterauth='G' then 'ALTER' else 'null' endif,
        if alterauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid
  insert into dbo.jdbc_columnprivileges
    select @dbname, @schem, @actual_table_name, @columnname, @grantor, @grantee,
        if referenceauth='Y' or referenceauth='G' then 'REFERENCE'
                                                  else 'null' endif,
        if referenceauth = 'G' then 'YES' else 'NO' endif
    from SYS.SYSTABLEPERM where stable_id = @tableid

  if (select count(*) from SYSCOLPERM where table_id = @tableid) > 0
  begin
        update dbo.jdbc_columnprivileges set PRIVILEGE = 'UPDATE'
               where SYS.SYSCOLPERM.column_id = @columnid
        if (select is_grantable from SYS.SYSCOLPERM
            where table_id = @tableid and column_id = @columnid) = 'Y'
        begin
                update dbo.jdbc_columnprivileges set IS_GRANTABLE='YES'
                       where PRIVILEGE = 'UPDATE'
        end
  end
  fetch getcolid into @columnid
end
close getcolid

select distinct * from dbo.jdbc_columnprivileges where PRIVILEGE != 'null'
     and TABLE_SCHEM like @table_owner ESCAPE '\'
     and COLUMN_NAME like @column_name ESCAPE '\'
        order by COLUMN_NAME, PRIVILEGE
commit transaction
delete dbo.jdbc_columnprivileges
go
grant execute on dbo.sp_jdbc_getcolumnprivileges to PUBLIC
go

/* Get a description of a table's optimal set of columns that uniquely
** identifies a row
** In SQL Anywhere it is usually the primary key
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getbestrowidentifier', 'procedure' )
go
/** SECTION END: CLEANUP **/

create procedure dbo.sp_jdbc_getbestrowidentifier (
	 @table_qualifier       varchar(128) = null,
	 @table_owner           varchar(128) = null,
	 @table_name            varchar(128),
	 @scope                 int,
	 @nullable              smallint)
as
declare @nulls char(1)
declare @id int

if (@table_owner = null) select @table_owner ='%'
else
begin
  if (locate(@table_owner,'%') > 0)
  begin
    raiserror 17208
      'Wildcards are not allowed here. Please change value of TABLE_SCHEM'
    return(1)
  end
end


if (@table_name = null)
begin
   raiserror 17208  'Null is not allowed for parameter TABLE NAME '
   return(1)
end

exec dbo.sp_jdbc_escapeliteralforlike @table_name

if (select count(*) from dbo.sysobjects
    where user_name(uid) like @table_owner ESCAPE '\'
     and name like @table_name ESCAPE '\') = 0
begin
  raiserror 17208
      'There is no object with the specified owner/name combination'
  return
end

if (@nullable = 0)
        select @nulls = 'N'
else
        select @nulls = 'Y'

select  SCOPE =         0,
        COLUMN_NAME =   sp_jconnect_trimit(cname),
        DATA_TYPE =     (select DATA_TYPE from dbo.spt_jdatatype_info
                                where TYPE_NAME = coltype),
        TYPE_NAME =     sp_jconnect_trimit(coltype),
        COLUMN_SIZE =   length,
        BUFFER_LENGTH = length,
        DECIMAL_DIGITS= syslength,
        PSEUDO_COLUMN = 1
from  SYS.SYSCOLUMNS
where tname like @table_name ESCAPE '\'
and in_primary_key = 'Y' and nulls = @nulls 
and creator like @table_owner ESCAPE '\'
order by SCOPE
go
grant execute on dbo.sp_jdbc_getbestrowidentifier to PUBLIC
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getversioncolumns', 'procedure' )
go
call dbo.sp_jconnect_cleanup( 'jdbc_versions', 'table' )
go
/** SECTION END: CLEANUP **/
begin
execute (
'create global temporary table dbo.jdbc_versions (
     	SCOPE int null,
        COLUMN_NAME varchar(128) null,
        DATA_TYPE int null,
        TYPE_NAME varchar(128) null,
        COLUMN_SIZE int null,
        BUFFER_LENGTH int null,
        DECIMAL_DIGITS int null,
        PSEUDO_COLUMN int null)'
|| in_system )
end
go

create procedure dbo.sp_jdbc_getversioncolumns (
	     @table_qualifier       varchar(128) = null,
	     @table_owner           varchar(128) = null,
	     @table_name            varchar(128))
as
declare @id int

if (@table_owner = null) select @table_owner ='%'

if (@table_name = null)
begin
   raiserror 17208  'Null is not allowed for parameter TABLE NAME '
   return(1)
end

exec dbo.sp_jdbc_escapeliteralforlike @table_name

if (select count(*) from dbo.sysobjects
    where user_name(uid) like @table_owner ESCAPE '\'
     and name like @table_name ESCAPE '\') = 0
begin
  raiserror 17208
    'There is no object with the specified owner/name combination'
  return(1)
end

begin transaction

declare curs2 cursor for 
select table_name from SYS.SYSTABLE
where table_name like @table_name ESCAPE '\'
and user_name(creator) like @table_owner ESCAPE '\'

open curs2
fetch curs2 into @table_name
while (@@sqlstatus = 0)
begin
insert into dbo.jdbc_versions select
        0,
        sp_jconnect_trimit(cname),
        (select DATA_TYPE from dbo.spt_jdatatype_info
               where TYPE_NAME = (select if coltype='integer'
               then 'int' else coltype endif)),
        sp_jconnect_trimit(coltype),
        length,
        length,
        syslength,
        1
  from SYS.SYSCOLUMNS
  where default_value = 'autoincrement'
  and tname like @table_name ESCAPE '\'
fetch curs2 into @table_name
end
close curs2
select * from dbo.jdbc_versions

commit transaction
go

grant execute on dbo.sp_jdbc_getversioncolumns to PUBLIC
go

/* getindexinfo returns information on the indexes of a page
** if unique is set to TRUE only indexes on indexes where it's value's must
** be unique are returned.
** approximate is a little needless because SQL Anywhere guarantees
** accurate data
** Additionally SQL Anywhere doesn't have clustered indexes nor
** index statistics and no way to estimate the amount of rows if the
table_name
** is a variable. In SQL Server there is rowcnt() for that.
*/

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'jdbc_indexhelp', 'table' )
go
call dbo.sp_jconnect_cleanup( 'jdbc_indexhelp2', 'table' )
go
/** SECTION END: CLEANUP **/

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getindexinfo', 'procedure' )
go
/** SECTION END: CLEANUP **/

begin
execute (
'create global temporary table dbo.jdbc_indexhelp (icreator varchar(128), iname varchar(128),non_unique varchar(5), new_order varchar(1))'
    || in_system )
end
go

begin
execute (
'create global temporary table dbo.jdbc_indexhelp2 (TABLE_CAT varchar(128),
TABLE_SCHEM varchar(128), TABLE_NAME varchar(128), NON_UNIQUE varchar(5) null, INDEX_QUALIFIER varchar(128), INDEX_NAME varchar(128), TYPE int, ORDINAL_POSITION int, COLUMN_NAME varchar(128), ASC_OR_DESC varchar(1) null, CARDINALITY int, PAGES int, FILTER_CONDITION varchar(128) null)'
    || in_system )
end
go 

create procedure dbo.sp_jdbc_getindexinfo (
        @table_qualifier        varchar(128) = NULL,
        @table_owner            varchar(128) = NULL,
        @table_name             varchar(128),
        @unique                 varchar(5),
        @approximate            varchar(5))
as
declare @is_unique char(1)
declare @owner varchar(128)
declare @table_id int
declare @colID int

if (@unique = '1' OR @unique = 'true')
    select @is_unique = 1
else
    select @is_unique = 'false'

if (@table_owner is null)
begin
    select @table_owner ='%'
end

if (@table_name = null) 
begin
   raiserror 17208  'Null is not allowed for parameter TABLE NAME '
   return(1)
end

if (select count(*) from dbo.sysobjects
    where user_name(uid) like @table_owner ESCAPE '\'
    and name = @table_name) = 0
begin
  raiserror 17208
      'There is no object with the specified owner/name combination'
  return(1)
end


// This helps make the count column more accurate 
if (@approximate = 'false' OR @approximate = '0' ) 
begin 
 checkpoint 
end 
         
begin transaction

delete dbo.jdbc_indexhelp

insert into dbo.jdbc_indexhelp
select icreator, iname,
       if (left(trim(indextype),1) = 'N') then 1  else 0 endif,
       if (right(trim(colnames),3) = 'E') then 'D' else 'A' endif
from SYS.SYSINDEXES 
where tname = @table_name
and creator like @table_owner ESCAPE '\'

declare owner_cur cursor for 
    	select @table_owner = user_name(uid) from sysobjects 
    		where name like @table_name ESCAPE '\' 
                and user_name(uid) like @table_owner ESCAPE '\' 
    open owner_cur
    fetch owner_cur into @owner
    while (@@sqlstatus = 0)
    begin
	select @table_id = table_id from SYS.SYSTABLE 
               where table_name = @table_name and creator = user_id(@owner)
	declare colIDCursor cursor for
	    select SYS.SYSIXCOL.column_id from SYS.SYSIXCOL == SYS.SYSCOLUMN
	    where SYS.SYSIXCOL.table_id = @table_id
	open colIDCursor
	fetch colIDCursor into @colID
	while (@@sqlstatus = 0)
    	begin
            insert into dbo.jdbc_indexhelp2
            select db_name(), user_name(SYSINDEX.creator), table_name,  
            (select non_unique from dbo.jdbc_indexhelp
                               where iname = index_name and icreator = @owner),
            db_name(), index_name,
            3,  /*No clustered indeces available */
            @colID,
	    (select column_name from SYS.SYSCOLUMN == SYS.SYSTABLE 
		where SYS.SYSTABLE.table_id = @table_id and column_id =@colID),
            (select new_order from dbo.jdbc_indexhelp
                          where iname = index_name and icreator = @owner),
            count,
            0,  /* Not available */
            null
        from  SYS.SYSTABLE key join SYS.SYSINDEX
        where SYS.SYSTABLE.table_id = @table_id
	fetch colIDCursor into @colID
    end
    close colIDCursor 
    fetch owner_cur into @owner
end
close owner_cur
select distinct * from dbo.jdbc_indexhelp2
where NON_UNIQUE <> @is_unique and NON_UNIQUE is not null
order by NON_UNIQUE, TYPE, INDEX_NAME, ORDINAL_POSITION


commit transaction
delete dbo.jdbc_indexhelp
delete dbo.jdbc_indexhelp2
go

grant execute on dbo.sp_jdbc_getindexinfo to PUBLIC
go


/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_sql_type_name', 'procedure' )
go
/** SECTION END: CLEANUP **/

/*  create a procedure that will query the datasource
**  specific SYS.SYSTABLE, to retrieve the correct type
**  or user defined datatype name, based on the parameters
**  @datatype = the protocol datatype value
**  @usrtype = the data source specifc user defined datatype value
*/


CREATE PROCEDURE dbo.sp_sql_type_name (
       @datatype  TINYINT,
       @usrtype   SMALLINT)
as
BEGIN
   declare @answer varchar(255)

/* special case for types numericn and decimaln, they do not seem
** to have the correct mapping of usertype & datatype
*/
   /* SPECIAL CASE - user-defined types
    * if a usertype is greater than 100 that means it is a
    * user defined datatype, and it needs to be referenced in
    * the datasource specific systype table
    */
   if (@usrtype > 100)
   begin
      select @answer = type_name from SYS.sysusertype
         where type_id = @usrtype 
   end
   /* SPECIAL CASE - numeric and decimal
    * numericn, and decimaln are always returned for a numeric
    * and decimal value, and do not have entries in the spt_jdatatype
    * info tables, therefore need to account for them by looking in
    * system tables.
    */
   else if (@datatype = 108 or @datatype = 106)
   begin
       if (@datatype = 108)
           select @answer = 'numeric'
       else
           select @answer = 'decimal' 
   end
   /* TYPICAL CASE
    * simply check dbo.spt_jdatatype_info for
    * the predefined jdbc mapping for the types,
    * and return the row marked unique for the datatype
    * requested.
    */
   else
   begin
       select @answer = j.type_name from dbo.spt_jdatatype_info j
          where j.ss_dtype = @datatype and j.is_unique = 1
   end
   select sp_jconnect_trimit(@answer)
END
go
/* end of dbo.sp_sql_type_name */
grant execute on dbo.sp_sql_type_name to PUBLIC
go

/*
   obtain the SQL server default charset
*/
/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'spt_collation_map', 'table' )
go

/* Define a collation table that will map sqlanywher collation types
 * and normal character set types, so that we can find the
 * server's default character set
 */
begin
execute (
'create table dbo.spt_collation_map (collation varchar(15) null,
    charsetn varchar(10), number integer default autoincrement primary key)'
    || in_system )
end
go

begin
execute (
'create index collation_ind on dbo.spt_collation_map(number)'
    || in_system )
end
go


grant select on dbo.spt_collation_map to PUBLIC
go

/* iso_1's */
insert into dbo.spt_collation_map(collation, charsetn) values ('819CYR', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819DAN', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819ELL', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819ISL', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819LATIN1', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819LATIN2', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819NOR', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819RUS', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819SVE', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('819TRJ', 'iso_1')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('ISO_1', 'iso_1')
go
/* cp's */

insert into dbo.spt_collation_map(collation, charsetn) values ('850', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('437', 'cp437')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('852', 'cp852')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('860', 'cp860')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('437LATIN1', 'cp437')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('437ESP', 'cp437')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('437SVE', 'cp437')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850CYR', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850DAN', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850ELL', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850ESP', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850ISL', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850LATIN1', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850LATIN2', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850NOR', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850RUS', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850SVE', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('850TRK', 'cp850')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('852LATIN2', 'cp852')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('852CYR', 'cp852')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('855CYR', 'cp855')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('857TRK', 'cp857')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('860LATIN1', 'cp860')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('866RUS', 'cp866')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('869ELL', 'cp869')
go

/* sjis */
insert into dbo.spt_collation_map(collation, charsetn) values ('SJIS', 'sjis')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('SJIS2', 'sjis')
go
/* eucjis */
insert into dbo.spt_collation_map(collation, charsetn) values ('EUC_JAPAN', 'eucjis')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('EUC_CHINA', 'eucgb')
go
/*
insert into dbo.spt_collation_map(collation, charsetn) values ('EUC_TAIWAN', 'eucb5')
*/
insert into dbo.spt_collation_map(collation, charsetn) values ('EUC_KOREA', 'eucksc')
go
insert into dbo.spt_collation_map(collation, charsetn) values ('UTF8', 'utf8')
go
// make iso_1 the default
insert into dbo.spt_collation_map(collation, charsetn) values (null, 'iso_1')
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_default_charset', 'procedure' )
go
/** SECTION END: CLEANUP **/

/*
**  create a procedure that will query the datasource
**  specific syscharset, and sysconfigures tables, and do a join to
**  determine what is the correct charset that has been set as a default
**  on the server.
*/
create procedure dbo.sp_default_charset
as


    select charsetn as DEFAULT_CHARSET from dbo.spt_collation_map
       where ((select default_collation from SYS.SYSINFO)
              = collation)
go
/* end of dbo.sp_default_charset */
grant execute on dbo.sp_default_charset to PUBLIC
go

/** SECTION BEGIN: CLEANUP **/
call dbo.sp_jconnect_cleanup( 'sp_jdbc_getudts', 'procedure' )
go
/** SECTION END: CLEANUP **/

/* 
** JDBC 2.0
** 
** DatabaseMetaData.getUDTs(catalog, schema, typeNamePattern, int types[])
**
** NOT SUPPORTED
*/
create procedure dbo.sp_jdbc_getudts (
        @table_qualifier        varchar(128) = NULL,
        @table_owner            varchar(128) = NULL,
        @type_name_pattern      varchar(128),
        @types                  varchar(128))
as
    declare @empty_string varchar(1)
    declare @empty_int int
    
    select @empty_string = ''
    select @empty_int = 0

    /* not supported, return an empty result set */    
    select 
        TYPE_CAT = @empty_string,
        TYPE_SCHEM = @empty_string,
        TYPE_NAME = @empty_string,
        CLASS_NAME = @empty_string,
        DATA_TYPE = @empty_int,
        REMARKS = @empty_string
    where
        1 = 2
go

/* end of dbo.sp_jdbc_getudts */
grant execute on dbo.sp_jdbc_getudts to PUBLIC
go


/* These two procs are only used in ASA6 and later. */

if asa_version >= 6 then
    /** SECTION BEGIN: CLEANUP **/
    call dbo.sp_jconnect_cleanup( 'sp_jdbc_class_for_name', 'procedure' )
    /** SECTION END: CLEANUP **/
end if
go

if asa_version >= 6 then
    create procedure dbo.sp_jdbc_class_for_name (
		    @class_name varchar(255))
    begin
	select 
	    comp.contents 
	from 
	    sysjavaclass cls, sysjarcomponent comp 
	where
	    cls.class_name = @class_name and cls.component_id = comp.component_id;
    end
end if 
go
    
if asa_version >= 6 then
    grant execute on dbo.sp_jdbc_class_for_name to PUBLIC
end if
go
    
    
if asa_version >= 6 then
    /** SECTION BEGIN: CLEANUP **/
    call dbo.sp_jconnect_cleanup( 'sp_jdbc_classes_in_jar', 'procedure' )
    /** SECTION END: CLEANUP **/
end if
go    

if asa_version >= 6 then
    create procedure dbo.sp_jdbc_classes_in_jar (
		    in @jar_name varchar(255))
    begin
	select
	    sjc.class_name
	from
	    SYSJAVACLASS sjc, SYSJAR sj
	where sj.jar_name = @jar_name and sj.jar_id = sjc.jar_id and sjc.replaced_by is null;
    end
end if 
go
    
if asa_version >= 6 then
    grant execute on dbo.sp_jdbc_classes_in_jar to PUBLIC
end if
go


/** SECTION BEGIN: CLEANUP **/
drop procedure dbo.sp_jconnect_cleanup
go
drop variable in_system
go
drop variable asa_version
go
set quoted_identifier off
go
/** SECTION END: CLEANUP **/
commit transaction
go



