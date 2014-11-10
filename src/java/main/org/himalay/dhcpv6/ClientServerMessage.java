/**
 Copyright (C) 2014 by
 Krishna C Tripathi, Johns Creek, GA
 All rights reserved.
 
  This file is part of DHCPv6 Library.

    DHCPv6 Library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    DHCPv6 Library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DHCPv6 Library.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.himalay.dhcpv6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.dhcpv6.Constants;
import org.himalay.dhcpv6.DhcpOptionFactory;
import org.himalay.dhcpv6.InterfaceId;
import org.himalay.dhcpv6.OptionRawOption;
import org.himalay.dhcpv6._ClientServerMessage;
import org.himalay.dhcpv6._DuidLL;
import org.himalay.dhcpv6._DuidLLT;
import org.himalay.dhcpv6.DhcpOptionFactory.DhcpOption;
import org.himalay.msgs.runtime.ArrayList;
import org.himalay.msgs.runtime.BinMessage;

public class ClientServerMessage extends _ClientServerMessage{



	public byte[] toByteArray()
	{
		try {
			ByteArrayOutputStream baos	= new ByteArrayOutputStream();
			DataOutputStream dos	= new DataOutputStream(baos);
			this.write(dos);
			dos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new byte[0];
	}
	
	public static byte[] toByteArray(BinMessage binMsg)
	{

			try {
				ByteArrayOutputStream baos	= new ByteArrayOutputStream();
				DataOutputStream dos	= new DataOutputStream(baos);
				binMsg.write(dos);
				dos.flush();
				return baos.toByteArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new byte[0];

	}
	
	public ClientServerMessage addRawOption(int type, byte[] data)
	{
		OptionRawOption rOption	= new OptionRawOption();
		rOption.getHeader().setMessageType(type);
		rOption.rawData.setData( data);
		this.addToOptions(rOption);
		return this;
	}

	public ClientServerMessage addClientId( byte[] data)
	{
		ClientId cid	= new ClientId();
		cid.getHeader().setMessageType(Constants.OPTION_CLIENTID);
		try {
			cid.read(new DataInputStream(new ByteArrayInputStream(data)));
			this.addToOptions(cid);
			return this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public DhcpOption getOption(int type)
	{
		return getOption(this.options, type);
	}
	
	public static DhcpOption getOption(ArrayList<DhcpOptionFactory.DhcpOption> options, int type)
	{
		for (DhcpOption anOption: options)
		{
			if ( anOption != null && anOption.
					getHeader().
					getMessageType() == type)
				return anOption;
		}
		
		return null;
	}
	
	public byte[] getMacAddressFrom_OPTION_CLIENTID()
	{
		DhcpOption dOption	= getOption(Constants.OPTION_CLIENTID);
		if (dOption != null)
		{
			ClientId cid= (ClientId) dOption;
			if ( cid.duid != null)
			{
				if ( cid.duid instanceof _DuidLL)
				{
					return ((_DuidLL)cid.duid).llAddress.buffer.getData();
				}else if( cid.duid instanceof _DuidLLT)
				{
					return ((_DuidLLT)cid.duid).llAddress.buffer.getData();
				}
			}
		}
		return null;
	}
	
	public static byte[] getMacAddressFrom_OPTION_INTERFACE_ID( ArrayList<DhcpOptionFactory.DhcpOption> options)
	{
		DhcpOption dOption	= getOption(options, Constants.OPTION_INTERFACE_ID);
		if (dOption != null)
		{
			InterfaceId anOption= (InterfaceId) dOption;
			if ( anOption.opaqueData != null)
			{
				byte [] data	= anOption.opaqueData.getData();
				if ( data.length > 18 )
				{
					byte[] retVal = new byte[6];
					System.arraycopy(data, 12, retVal, 0, 6);
					return retVal;
				}
			}
		}
		return null;
	}
	
	public byte[] getMacAddressFrom_OPTION_INTERFACE_ID()
	{
		DhcpOption dOption	= getOption(Constants.OPTION_INTERFACE_ID);
		if (dOption != null)
		{
			InterfaceId anOption= (InterfaceId) dOption;
			if ( anOption.opaqueData != null)
			{
				byte [] data	= anOption.opaqueData.getData();
				if ( data.length > 18 )
				{
					byte[] retVal = new byte[6];
					System.arraycopy(data, 12, retVal, 0, 6);
					return retVal;
				}
			}
		}
		return null;
	}
	
	public void read(byte[] data, int offset, int length) {
		ByteArrayInputStream bais	= new ByteArrayInputStream(data, offset, length);
		DataInputStream dis	= new DataInputStream(bais);
		try {
			this.read(dis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ClientServerMessage parse(byte[] data, int offset, int length)
	{
		ClientServerMessage retVal = new ClientServerMessage();
		retVal.read(data, offset, length);
		return retVal;
	}
	
	public static ClientServerMessage parse(byte[] data)
	{
		return parse(data,0,data.length);
	}
	
	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int i) {
		this.transactionId	= 0x00FFFFFF & i;
	}

	public byte[] getClientMac() {
		byte[] retVal	= null;
		retVal = this.getMacAddressFrom_OPTION_INTERFACE_ID();
		if ( retVal == null)
		{
			retVal = this.getMacAddressFrom_OPTION_CLIENTID();
		}
		return retVal;
	}
	

}
