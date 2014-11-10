dhcpv6lib
=========

DHCPV6 Library in Java

To build:
Download code and execute 
mvn clean package

FAQ:

  Does it work?

Yes. I have used it to to successfuly acquire/renew IPv6 using DHCPV6 following http://tools.ietf.org/html/rfc3315

  Why is there no activity?
  
Because I am not always working on this. Its just a library, there are other things to do in life.

  Is there any sample program?

There is a sample program included. This prgram dumps DHCPv6 messages from given pcap file. Please execute 

mvn -f pom2.xml package 

to build it. and 

mvn -f pom2.xml exec:java -Dexec.args="path to pacp file.pcap"

To execute it.

  The library is missing so-and-so option, why is that so?
  
Because I do not know anything about it. If you send me the format and I have spare time I will add it or you are welcome to add it.

  I have found a bug, can you fix it?
Send me an email about it, I will look at it.
