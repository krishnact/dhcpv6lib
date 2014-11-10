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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.msgs.runtime.ByteArray;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class Authentication extends DhcpOptionFactory.DhcpOption { // Concrete
																	// type is
																	// Authentication

	// members variables
	// header
	public DhcpOptionHeader header;
	// protocol
	public short protocol;
	// algorithm
	public short algorithm;
	// rdm
	public short rdm;
	// replayDetection
	public ReplayDetection replayDetection;
	// authInfo
	public ByteArray authInfo;

	public Authentication() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize protocol

		// Initialize algorithm

		// Initialize rdm

		// Initialize replayDetection
		replayDetection = new ReplayDetection();
		// Initialize authInfo
		authInfo = new ByteArray();
		authInfo.setSizeType("EXTERNAL");
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read protocol
		{
			protocol = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read algorithm
		{
			algorithm = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read rdm
		{
			rdm = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read replayDetection
		retVal += replayDetection.read(istream);
		// read authInfo
		{
			authInfo.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (-11);
			authInfo.setSize(iRead);
			retVal += authInfo.read(istream);
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read protocol
		{
			protocol = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read algorithm
		{
			algorithm = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read rdm
		{
			rdm = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read replayDetection
		retVal += replayDetection.read(istream);
		// read authInfo
		{
			authInfo.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (-11);
			authInfo.setSize(iRead);
			retVal += authInfo.read(istream);
		}

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		{
			/** fix dependent sizes for header **/
		}

		{
			/** fix dependent sizes for replayDetection **/
		}
		{
			/** fix dependent sizes for authInfo **/
			header.length = ((short) authInfo.getSize() - (-11));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write protocol
		ostream.writeByte(protocol);
		retVal += 1;
		// write algorithm
		ostream.writeByte(algorithm);
		retVal += 1;
		// write rdm
		ostream.writeByte(rdm);
		retVal += 1;
		// write replayDetection
		if (replayDetection != null)
			retVal += replayDetection.write(ostream);
		// write authInfo
		{
			retVal += authInfo.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("Authentication\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write protocol
		dc.indent();
		dc.getPs().println(
				"protocol=" + protocol + "(0x" + Integer.toHexString(protocol)
						+ ")");
		// write algorithm
		dc.indent();
		dc.getPs().println(
				"algorithm=" + algorithm + "(0x"
						+ Integer.toHexString(algorithm) + ")");
		// write rdm
		dc.indent();
		dc.getPs().println(
				"rdm=" + rdm + "(0x" + Integer.toHexString(rdm) + ")");
		// write replayDetection
		if (replayDetection != null) {
			dc.indent();
			dc.getPs().println("replayDetection");
			retVal += replayDetection.dump(dc);
		}
		// write authInfo
		dc.indent();
		dc.getPs().print(
				"authInfo: " + authInfo.getSize() + "(0x"
						+ Integer.toHexString(authInfo.getSize()) + ")\n");
		this.authInfo.dump(dc);
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for header
	// public DhcpOptionHeader getHeader()
	// {
	// return header ;
	// }

	// Setter for header
	// public void setHeader(DhcpOptionHeader val)
	// {
	// this.header= val;
	// }
	// Getter for protocol
	// public short getProtocol()
	// {
	// return protocol ;
	// }

	// Setter for protocol
	// public void setProtocol(short val)
	// {
	// this.protocol= val;
	// }
	// Getter for algorithm
	// public short getAlgorithm()
	// {
	// return algorithm ;
	// }

	// Setter for algorithm
	// public void setAlgorithm(short val)
	// {
	// this.algorithm= val;
	// }
	// Getter for rdm
	// public short getRdm()
	// {
	// return rdm ;
	// }

	// Setter for rdm
	// public void setRdm(short val)
	// {
	// this.rdm= val;
	// }
	// Getter for replayDetection
	// public ReplayDetection getReplayDetection()
	// {
	// return replayDetection ;
	// }

	// Setter for replayDetection
	// public void setReplayDetection(ReplayDetection val)
	// {
	// this.replayDetection= val;
	// }
	// Getter for authInfo
	// public ByteArray getAuthInfo()
	// {
	// return authInfo ;
	// }

	// Setter for authInfo
	// public void setAuthInfo(ByteArray val)
	// {
	// this.authInfo= val;
	// }

	public void setAuthInfo(byte[] val) {
		this.authInfo.setData(val);
	}

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

	public void setHeader(DhcpOptionHeader header) {
		this.header = header;
	}

	public DhcpOptionHeader getHeader() {
		return this.header;
	}

}

// End of code