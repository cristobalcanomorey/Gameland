IMPORTANTE:
Para que la aplicación funcione al context.xml del tomcat hay que añadir:	
<Resource name="jdbc/gameland" auth="Container"
type="javax.sql.DataSource" maxActive="100" maxIdle="30"
maxWait="10000" username="tofol" password="1234"
driverClassName="com.mysql.cj.jdbc.Driver"
url="jdbc:mysql://localhost:3306/gameland?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;useSSL=false" />
