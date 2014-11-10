package org.himalay.tools;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeoutException;

import org.himalay.dhcpv6.RA_ServerMessage;
import org.himalay.msgs.runtime.DumpContext;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;

public class DhcpDump {
	
	CmdLine cmdArgs;
	
	public static void main(String[] args) {
		
		CmdLine cmd	= new CmdLine();
		if ( args.length < 1)
		{
			System.out.println("Usage DhcpDump <pcap file>");
			System.exit(0);
		}
		cmd.fileName	= args[0];
		DhcpDump instance	= new DhcpDump(cmd);
		
		instance.run();
	}
	
	public DhcpDump(CmdLine cmdArgs) {
		super();
		this.cmdArgs = cmdArgs;
	}

	public void run()
	{
		
		PcapHandle handle = null;
		try {
			handle = Pcaps.openOffline(cmdArgs.fileName);
		} catch (PcapNativeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if ( handle == null)
			return;
		
		int iCnt	= 0;
		boolean bContinue	 =true;
		int offset1	= 1;
		int range   = 100000;
		int iPcktId =0;
	    while  (bContinue) {
	      try {
	        Packet packet = handle.getNextPacketEx();
	        iPcktId++;
	        Timestamp ts = new Timestamp(handle.getTimestampInts() * 1000L);
	        ts.setNanos(handle.getTimestampMicros() * 1000);
	        
	        Packet ipPayload= packet.getPayload();
	        if ( ipPayload instanceof IpV6Packet)
	        {
		        Packet udpData	= ipPayload.getPayload();
		        if ( udpData instanceof UdpPacket)
		        {
		        	UdpPacket udpPacket = (UdpPacket) udpData;
		        	if ( udpPacket.getHeader().getDstPort().value() == 547 || udpPacket.getHeader().getDstPort().value() == 546)
		        	{
		    	    	iCnt++;
		    	    	if (iCnt < offset1) continue;
		    	    	if (iCnt > (offset1+range)) System.exit(0);

		        		byte[] rawUdpData	= udpData.getRawData();
		        		if (rawUdpData [8] == 12 || rawUdpData [8] == 13 )
		        		{
		        			System.out.println("Relay message " + iPcktId);
		        			RA_ServerMessage msg	= RA_ServerMessage.parse(rawUdpData, 8, rawUdpData.length- 8);
		        			try {
								msg.dump(new DumpContext());
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        		}else
		        		{
		        			System.out.println("Client Server message");
		        		}
		        	}
		        }
	        }

	      } catch (TimeoutException e) {
	      } catch (EOFException e) {
	        System.out.println("EOF");
	        break;
	      } catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bContinue = false;
		} catch (NotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bContinue = false;
		}
	    }

	    handle.close();
	}
}
