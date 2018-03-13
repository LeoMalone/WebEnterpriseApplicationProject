cd "C:\Program Files\MySQL\MySQL Server 5.7\bin"
mysqldump --routines -uadmin -plastever -hlocalhost --databases lastever > C:\lastever.sql
pause